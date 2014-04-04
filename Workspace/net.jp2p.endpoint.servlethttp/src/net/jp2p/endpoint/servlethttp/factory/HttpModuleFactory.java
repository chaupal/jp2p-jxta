/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.servlethttp.factory;

import java.util.Collection;

import net.jp2p.chaupal.jxta.module.AbstractModuleFactory;
import net.jp2p.endpoint.servlethttp.Activator;
import net.jxse.module.IJxtaModuleService;
import net.jxta.impl.endpoint.servlethttp.ServletHttpTransport;

public class HttpModuleFactory extends AbstractModuleFactory<ServletHttpTransport> {

	public HttpModuleFactory( HttpPropertySource source ) {
		super(source, Activator.getModuleFactoryRegistrator() );
    }

	@Override
	protected void addModules(
			Collection<IJxtaModuleService<ServletHttpTransport>> modules) {
		modules.add( new HttpModule( (HttpPropertySource) super.getSource() ));
	}
}
