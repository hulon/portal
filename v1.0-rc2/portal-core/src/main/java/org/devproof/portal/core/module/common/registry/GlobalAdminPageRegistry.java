/*
 * Copyright 2009 Carsten Hufe devproof.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.devproof.portal.core.module.common.registry;

import java.util.List;

import org.apache.wicket.Page;
import org.devproof.portal.core.module.common.panel.GlobalAdminBoxPanel;

/**
 * Registry for {@link GlobalAdminBoxPanel} links
 * 
 * @author Carsten Hufe
 */
public interface GlobalAdminPageRegistry {
	/**
	 * Registers a admin link The language property file of the page must
	 * contain a property named "adminLinkLabel" for menu name
	 */
	public void registerGlobalAdminPage(Class<? extends Page> adminPage);

	/**
	 * Removes a admin link
	 */
	public void removeGlobalAdminPage(Class<? extends Page> adminPage);

	/**
	 * Returns all registered global admin pages
	 * 
	 */
	public List<Class<? extends Page>> getRegisteredGlobalAdminPages();

	/**
	 * Builds or rebuilds the global administration box from the database
	 */
	public void buildNavigation();
}
