/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.netty.factory.http;

import net.jp2p.jxta.transport.TransportPropertySource;
import net.jxta.peergroup.IModuleDefinitions;
import net.jxta.platform.ModuleClassID;

public class Http2Module extends HttpModule {

    public Http2Module( TransportPropertySource source ) {
		super(source);
	}
   
	@Override
	public ModuleClassID getModuleClassID() {
		return IModuleDefinitions.http2ProtoClassID;
	}
}
