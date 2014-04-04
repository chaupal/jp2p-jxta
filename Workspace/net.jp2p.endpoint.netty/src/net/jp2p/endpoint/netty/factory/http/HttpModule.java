/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.factory.http;

import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jp2p.jxta.module.AbstractModuleComponent;
import net.jp2p.jxta.transport.TransportPropertySource;
import net.jxta.document.Advertisement;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.StructuredDocumentUtils;
import net.jxta.document.XMLDocument;
import net.jxta.document.XMLElement;
import net.jxta.impl.endpoint.netty.http.NettyHttpTunnelTransport;
import net.jxta.impl.peergroup.AutomaticConfigurator;
import net.jxta.impl.protocol.HTTPAdv;
import net.jxta.impl.protocol.PlatformConfig;
import net.jxta.logging.Logging;
import net.jxta.peergroup.IModuleDefinitions;
import net.jxta.platform.ModuleClassID;
import net.jxta.protocol.ModuleImplAdvertisement;
import net.jxta.protocol.TransportAdvertisement;

class HttpModule extends AbstractModuleComponent<NettyHttpTunnelTransport> {

    private final static transient Logger logger = Logger.getLogger( HttpModule.class.getName());
    
    HttpModule( TransportPropertySource source ) {
		super(source, new NettyHttpTunnelTransport());
	}

    
	@Override
	public ModuleClassID getModuleClassID() {
		return IModuleDefinitions.httpProtoClassID;
	}


	@Override
	protected ModuleImplAdvertisement onCreateAdvertisement() {
		URL url = HttpModule.class.getResource( S_RESOURCE_LOCATION );
		return getAdvertisementFromResource(url, this.getModuleClassID());
	}

	/**
	 * Get the XML document that describes the module
	 * @return
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Advertisement getAdvertisement( PlatformConfig config ){
        XMLDocument http = (XMLDocument) config.getServiceParam( getModuleClassID());
        HTTPAdv httpAdv = null;
        boolean httpEnabled = true;

        if (http != null) {
            try {
                httpEnabled = config.isSvcEnabled(getModuleClassID());

                XMLElement<?> param = null;

                Enumeration<?> httpChilds = http.getChildren(TransportAdvertisement.getAdvertisementType());

                // get the HTTPAdv from TransportAdv
                if (httpChilds.hasMoreElements()) {
                    param = (XMLElement<?>) httpChilds.nextElement();
                }

                if (null != param) {
                    httpAdv = (HTTPAdv) AdvertisementFactory.newAdvertisement(param);

                    if (httpEnabled) {
                        // check if the interface address is still valid.
                        String intf = httpAdv.getInterfaceAddress();

                        if ((null != intf) && !AutomaticConfigurator.isValidInetAddress(intf)) {
 
                            if (Logging.SHOW_CONFIG && logger.isLoggable(Level.CONFIG)) {
                                logger.config("Reconfig requested - invalid interface address");
                            }
                        }
                    }
                }
            } catch (RuntimeException advTrouble) {

                if (Logging.SHOW_WARNING && logger.isLoggable(Level.WARNING)) {
                    logger.log(Level.WARNING, "HTTP advertisement corrupted", advTrouble);
                }

                httpAdv = null;
            }
        }
        if (httpAdv == null) {
            if (Logging.SHOW_CONFIG && logger.isLoggable(Level.CONFIG)) {
                logger.config("HTTP advertisement missing, making a new one.");
            }

            int port = 0;
            // get the port from a property
            String httpPort = System.getProperty("jxta.http.port");

            if (httpPort != null) {
                try {
                    int propertyPort = Integer.parseInt(httpPort);

                    if ((propertyPort < 65536) && (propertyPort >= 0)) {
                        port = propertyPort;
                    } else {
                        if (Logging.SHOW_WARNING && logger.isLoggable(Level.WARNING)) {
                            logger.warning("Property \'jxta.http.port\' is not a valid port number : " + propertyPort);
                        }
                    }
                } catch (NumberFormatException ignored) {
                    if (Logging.SHOW_WARNING && logger.isLoggable(Level.WARNING)) {
                        logger.warning("Property \'jxta.http.port\' was not an integer : " + http);
                    }
                }
            }

            httpAdv = (HTTPAdv) AdvertisementFactory.newAdvertisement(HTTPAdv.getAdvertisementType());
            httpAdv.setProtocol("http");
            httpAdv.setPort(port);
            httpAdv.setServerEnabled(false);

            // Create new param docs that contain the updated adv
            http = (XMLDocument<?>) StructuredDocumentFactory.newStructuredDocument(MimeMediaType.XMLUTF8, "Parm");
            XMLDocument<?> httAdvDoc = (XMLDocument<?>) httpAdv.getDocument(MimeMediaType.XMLUTF8);

            StructuredDocumentUtils.copyElements(http, http, httAdvDoc);
            if (!httpEnabled) {
                http.appendChild(http.createElement("isOff"));
            }
            config.putServiceParam( getModuleClassID(), http);
        
        }
		return httpAdv;
	}
}
