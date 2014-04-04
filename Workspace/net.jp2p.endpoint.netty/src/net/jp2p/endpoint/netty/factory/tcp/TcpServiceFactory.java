/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.factory.tcp;

import net.jp2p.container.ContainerFactory;
import net.jp2p.container.component.IJp2pComponent;
import net.jp2p.container.context.Jp2pContext;
import net.jp2p.container.factory.AbstractFilterFactory;
import net.jp2p.container.factory.filter.ComponentCreateFilter;
import net.jp2p.container.factory.filter.IComponentFactoryFilter;
import net.jp2p.container.properties.IJp2pProperties;
import net.jp2p.container.properties.IJp2pPropertySource;
import net.jp2p.jxta.transport.TransportPropertySource;
import net.jxta.impl.endpoint.netty.NettyTransport;

public class TcpServiceFactory extends AbstractFilterFactory<NettyTransport>{

	public static final String S_TCP_SERVICE = "TcpService";
	public static final String S_TCP_PROTOCOL = "tcp";

	@Override
	public String getComponentName() {
		return S_TCP_SERVICE;
	}
	
	@Override
	protected IJp2pPropertySource<IJp2pProperties> onCreatePropertySource() {
		TransportPropertySource source = new TransportPropertySource( S_TCP_SERVICE, super.getParentSource());
		return source;
	}

	@Override
	protected IComponentFactoryFilter createFilter() {
		return new ComponentCreateFilter<IJp2pComponent<NettyTransport>, ContainerFactory>( BuilderEvents.COMPONENT_CREATED, Jp2pContext.Components.JP2P_CONTAINER.toString(), this );
	}

	@Override
	protected TcpModule onCreateComponent(IJp2pPropertySource<IJp2pProperties> source ) {
		TcpModuleFactory hmf = new TcpModuleFactory( (TransportPropertySource) source );
		hmf.createModules();
		return new TcpModule(( TransportPropertySource ) source );
	}	 
}