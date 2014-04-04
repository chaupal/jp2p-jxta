/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.servlethttp.factory;

import net.jp2p.container.properties.AbstractJp2pWritePropertySource;
import net.jp2p.container.properties.IJp2pProperties;
import net.jp2p.container.properties.IJp2pPropertySource;
import net.jp2p.container.properties.IJp2pWritePropertySource;
import net.jp2p.container.utils.StringStyler;
import net.jxta.document.AdvertisementFactory;
import net.jxta.impl.protocol.HTTPAdv;

public class HttpPropertySource extends AbstractJp2pWritePropertySource
	implements IJp2pWritePropertySource<IJp2pProperties>

{
	public static final String S_HTTP_SERVICE = "HttpService";
	public static final String S_HTTP_PROTOCOL = "http";
	
	public enum HttpProperties implements IJp2pProperties{
		INCOMING_STATUS,
		INTERFACE_ADDRESS,
		OUTGOING_STATUS,
		PORT,
		PUBLIC_ADDRESS,
		TO_PUBLIC_ADDRESS_EXCLUSIVE,
		PUBLIC_ADDRESS_EXCLUSIVE;
	
		@Override
		public String toString() {
			return StringStyler.prettyString( super.toString() );
		}	

		/**
		 * Returns true if the given property is valid for this enumeration
		 * @param property
		 * @return
		 */
		public static boolean isValidProperty( IJp2pProperties property ){
			if( property == null )
				return false;
			for( HttpProperties prop: values() ){
				if( prop.equals( property ))
					return true;
			}
			return false;
		}

		public static HttpProperties convertTo( String str ){
			String enumStr = StringStyler.styleToEnum( str );
			return valueOf( enumStr );
		}
	}

	public HttpPropertySource( IJp2pPropertySource<IJp2pProperties> parent ) {
		super( S_HTTP_SERVICE, parent );
	}

	/**
	 * Get the advertisement
	 * @return
	 */
	public HTTPAdv getAdvertisement(){
		HTTPAdv httpAdv = (HTTPAdv) AdvertisementFactory.newAdvertisement(HTTPAdv.getAdvertisementType());
        httpAdv.setProtocol( HttpPropertySource.S_HTTP_PROTOCOL );
        httpAdv.setPort( (int) super.getProperty( HttpProperties.PORT));
        httpAdv.setServerEnabled(false);
        return httpAdv;
	}

	@Override
	public HttpProperties getIdFromString(String key) {
		return HttpProperties.valueOf( key );
	}

	@Override
	public boolean validate(IJp2pProperties id, Object value) {
		return false;
	}
}
