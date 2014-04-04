/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.module;

import net.jp2p.chaupal.jxta.module.AbstractModuleLoaderService;
import net.jp2p.endpoint.netty.factory.http.Http2Module;
import net.jp2p.endpoint.netty.factory.http.HttpPropertySource;
import net.jp2p.endpoint.netty.factory.tcp.TcpModule;

import net.jp2p.jxta.module.IModuleLoader;
import net.jp2p.jxta.transport.TransportPropertySource;
import net.jxse.module.IJxtaModuleService;
import net.jxta.platform.Module;

public class ModuleLoaderService<T extends Module> extends AbstractModuleLoaderService<T> {

	@SuppressWarnings("unchecked")
	@Override
	public void setModuleLoaderService(IModuleLoader<T> service) {
		TransportPropertySource source = new HttpPropertySource();
		service.addModule( (IJxtaModuleService<T>) new Http2Module( source ));
		service.addModule( (IJxtaModuleService<T>) new TcpModule( source ));
	}
}
