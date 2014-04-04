/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty;


import net.jp2p.chaupal.jxta.module.ModuleFactoryRegistrator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public static final String S_BUNDLE_ID = "net.jp2p.endpoint.netty";
	
	private static ModuleFactoryRegistrator mfr;
	
	@Override
	public void start(BundleContext context) throws Exception {
		mfr = new ModuleFactoryRegistrator(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		mfr.unregister();
		mfr = null;
	}
	
	public static ModuleFactoryRegistrator getModuleFactoryRegistrator(){
		return mfr;
	}
}