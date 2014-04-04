/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.servlethttp.osgi;


import java.util.logging.Logger;

import net.jxse.osgi.service.IServerStarter;

import org.eclipselabs.osgi.ds.broker.service.AbstractPalaver;
import org.eclipselabs.osgi.ds.broker.service.AbstractPetitioner;
import org.eclipselabs.osgi.ds.broker.service.ParlezEvent;

public class ServerStarterPetitioner extends AbstractPetitioner<String, String, IServerStarter>
{
	public static final String S_JXTA = "Jxta";

	private static ServerStarterPetitioner attendee = new ServerStarterPetitioner();
	
	private IServerStarter starter;

	
	private ServerStarterPetitioner() {
		super( new Palaver());
	}
	
	public static ServerStarterPetitioner getInstance(){
		return attendee;
	}

	
	public IServerStarter getStarter() {
		return starter;
	}

	@Override
	protected void onDataReceived( ParlezEvent<IServerStarter> event ) {
		  super.onDataReceived( event );
		  this.starter = event.getData();
		  Logger.getLogger( this.getClass().getName() ).info("\n\nJetty Server for JXTA found!\n\n");
	}

	/**
	 * The palaver contains the conditions for attendees to create an assembly. In this case, the attendees must
	 * pass a string identifier (the package id) and provide a token that is equal
	 * @author Kees
	 *
	 */
	private static class Palaver extends AbstractPalaver<String>{

		public static final String S_IJXTACONTAINER_PACKAGE_ID = "org.osgi.jxta.service.ijxtaservicecomponent";
		public static final String S_IJXTA_TOKEN = "org.osgi.jxse.server.token";
		
		protected Palaver() {
			super(S_IJXTACONTAINER_PACKAGE_ID);
		}

		@Override
		public String giveToken() {
			return S_IJXTA_TOKEN;
		}

		@Override
		public boolean confirm(Object token) {
			return ( token.equals(S_IJXTA_TOKEN ));
		}	
	}
}