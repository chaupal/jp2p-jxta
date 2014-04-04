/*******************************************************************************
 * Copyright (c) 2014 Chaupal.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *******************************************************************************/
package net.jp2p.endpoint.servlethttp.osgi;

import org.eclipselabs.osgi.ds.broker.service.AbstractAttendeeProviderComponent;

/**
 * Makes the service container accessible for the IDE
 * @author keesp
 *
 */
public class OsgiComponent extends AbstractAttendeeProviderComponent {

	@Override
	public void initialise() {
		super.addAttendee( ServerStarterPetitioner.getInstance() );
	}

	@Override
	public void finalise() {
		super.finalise();
	}
}