/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.factory.http;

import net.jp2p.container.properties.IJp2pProperties;
import net.jp2p.container.properties.IJp2pPropertySource;
import net.jp2p.container.properties.IJp2pWritePropertySource;
import net.jp2p.endpoint.netty.Activator;
import net.jp2p.jxta.transport.TransportPropertySource;

public class HttpPropertySource extends TransportPropertySource
	implements IJp2pWritePropertySource<IJp2pProperties>

{
	public static final String S_HTTP_SERVICE = "HttpService";
	public static final String S_HTTP_PROTOCOL = "http";

	public HttpPropertySource( ) {
		super( Activator.S_BUNDLE_ID, S_HTTP_SERVICE );
	}

	public HttpPropertySource( IJp2pPropertySource<IJp2pProperties> parent ) {
		super( S_HTTP_SERVICE, parent );
	}

	@Override
	public boolean validate(IJp2pProperties id, Object value) {
		return false;
	}
}
