/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.servlethttp.context;

import net.jp2p.container.context.Jp2pContext;
import net.jp2p.container.factory.IPropertySourceFactory;
import net.jp2p.container.properties.IJp2pProperties;
import net.jp2p.container.properties.IJp2pPropertySource;
import net.jp2p.container.properties.IJp2pWritePropertySource;
import net.jp2p.container.properties.IPropertyConvertor;
import net.jp2p.container.utils.StringStyler;
import net.jp2p.container.utils.Utils;
import net.jp2p.container.xml.IJp2pHandler;
import net.jp2p.endpoint.servlethttp.factory.HttpPropertySource;
import net.jp2p.endpoint.servlethttp.factory.HttpServiceFactory;
import net.jp2p.jxta.context.IJxtaContext;
import net.jxta.peergroup.IModuleDefinitions.DefaultModules;
import net.jxta.platform.ModuleClassID;

public class HttpContext implements IJxtaContext {

	public static final String S_HTTP_CONTEXT = "http";

	public HttpContext() {
	}

	@Override
	public String getName() {
		return S_HTTP_CONTEXT;
	}

	@Override
	public String[] getSupportedServices() {
		String[] names = new String[1];
		names[0] = HttpPropertySource.S_HTTP_SERVICE;
		return names;
	}

	
	@Override
	public ModuleClassID[] getSupportedModuleClassIDs() {
		ModuleClassID[] ids = new ModuleClassID[ 1];
		ids[0] = DefaultModules.getModuleClassID( DefaultModules.HTTP );
		return ids;
	}

	/**
	 * Returns true if the given component name is valid for this context
	 * @param componentName
	 * @return
	 */
	@Override
	public boolean isValidComponentName( String contextName, String componentName ){
		if( !Utils.isNull( contextName ) && !Jp2pContext.isContextNameEqual( S_HTTP_CONTEXT, contextName ))
			return false;
		String compName = StringStyler.styleToEnum( componentName );
		compName = StringStyler.prettyString( compName );
		for( String name: getSupportedServices() ){
			if( name.equals( compName ))
				return true;
		}
		return false;
	}

	/**
	 * Change the factory to a Chaupal factory if required and available
	 * @param factory
	 * @return
	 */
	@Override
	public IPropertySourceFactory getFactory( String componentName ){
		if( !isValidComponentName( S_HTTP_CONTEXT, componentName))
			return null;
		
		IPropertySourceFactory factory = new HttpServiceFactory();
		return factory;
	}

	@Override
	public Object createValue( String componentName, IJp2pProperties id ){
		return null;
	}
	
	/**
	 * Get the default factory for this container
	 * @param parent
	 * @param componentName
	 * @return
	 */
	public IPropertyConvertor<String, Object> getConvertor( IJp2pWritePropertySource<IJp2pProperties> source ){
		return null;
	}

	@Override
	public IJp2pHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyConvertor<String, Object> getConvertor(
			IJp2pPropertySource<IJp2pProperties> source) {
		// TODO Auto-generated method stub
		return null;
	}
}
