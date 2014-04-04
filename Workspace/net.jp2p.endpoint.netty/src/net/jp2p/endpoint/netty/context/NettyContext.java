/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.context;

import net.jp2p.container.context.AbstractJp2pContext;
import net.jp2p.container.factory.IPropertySourceFactory;
import net.jp2p.container.properties.IJp2pProperties;
import net.jp2p.container.properties.IJp2pPropertySource;
import net.jp2p.container.properties.IPropertyConvertor;
import net.jp2p.container.xml.IJp2pHandler;import net.jp2p.endpoint.netty.factory.http.HttpServiceFactory;
import net.jp2p.endpoint.netty.factory.tcp.TcpServiceFactory;
import net.jp2p.jxta.context.IJxtaContext;
import net.jxta.peergroup.IModuleDefinitions;
import net.jxta.peergroup.IModuleDefinitions.DefaultModules;
import net.jxta.platform.ModuleClassID;

public class NettyContext extends AbstractJp2pContext implements IJxtaContext {

	public static final String S_NETTY_CONTEXT = "netty";

	@Override
	public String getName() {
		return S_NETTY_CONTEXT;
	}

	@Override
	public String[] getSupportedServices() {
		String[] names = new String[2];
		names[0] = TcpServiceFactory.S_TCP_SERVICE;
		names[1] = HttpServiceFactory.S_HTTP_SERVICE;
		return names;
	}

	@Override
	public ModuleClassID[] getSupportedModuleClassIDs() {
		ModuleClassID[] ids = new ModuleClassID[3];
		ids[0] = DefaultModules.getModuleClassID( DefaultModules.TCP );
		ids[1] = DefaultModules.getModuleClassID( DefaultModules.HTTP );
		ids[2] = IModuleDefinitions.http2ProtoClassID;
		return ids;
	}

	/**
	 * Change the factory to a Chaupal factory if required and available
	 * @param factory
	 * @return
	 */
	@Override
	public IPropertySourceFactory getFactory( String componentName ){
		if( !isValidComponentName( S_NETTY_CONTEXT, componentName))
			return null;
		
		if( TcpServiceFactory.S_TCP_SERVICE.equals( componentName )){
			return new TcpServiceFactory( );
		}
		if( HttpServiceFactory.S_HTTP_SERVICE.equals( componentName )){
			return new HttpServiceFactory( );
		}
		return null;
	}

	@Override
	public Object createValue( String componentName, IJp2pProperties id ){
		return null;
	}
	
	@Override
	public IJp2pHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get the default factory for this container
	 * @param parent
	 * @param componentName
	 * @return
	 */
	@Override
	public IPropertyConvertor<String, Object> getConvertor(
			IJp2pPropertySource<IJp2pProperties> source) {
		// TODO Auto-generated method stub
		return null;
	}
}
