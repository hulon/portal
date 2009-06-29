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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Page;
import org.devproof.portal.core.config.PageConfiguration;
import org.devproof.portal.core.module.common.locator.PageLocator;
import org.devproof.portal.core.module.common.util.PortalUtil;
import org.devproof.portal.core.module.modulemgmt.entity.ModuleLinkEntity;
import org.devproof.portal.core.module.modulemgmt.service.ModuleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Carsten Hufe
 */
public class MainNavigationRegistryImpl implements MainNavigationRegistry, InitializingBean {
	private PageLocator pageLocator;
	private ModuleService moduleService;

	private final List<Class<? extends Page>> pages = new ArrayList<Class<? extends Page>>();

	@Override
	public List<Class<? extends Page>> getRegisteredPages() {
		// immutable
		return Collections.unmodifiableList(this.pages);
	}

	@Override
	public void registerPage(final Class<? extends Page> page) {
		this.pages.add(page);
	}

	@Override
	public void registerPages(final List<Class<? extends Page>> pages) {
		for (final Class<? extends Page> page : pages) {
			registerPage(page);
		}
	}

	@Override
	public void clearRegistry() {
		this.pages.clear();
	}

	@Override
	public void removePage(final Class<? extends Page> page) {
		this.pages.remove(page);
	}

	@Override
	public void buildNavigation() {
		clearRegistry();
		Collection<PageConfiguration> confs = this.pageLocator.getPageConfigurations();
		List<ModuleLinkEntity> links = this.moduleService.findAllVisibleMainNavigationLinks();
		for (ModuleLinkEntity link : links) {
			PageConfiguration conf = PortalUtil.getConfigurationByPageName(confs, link.getPageName());
			if (conf != null) {
				registerPage(conf.getPageClass());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		buildNavigation();
	}

	public void setPageLocator(final PageLocator pageLocator) {
		this.pageLocator = pageLocator;
	}

	public void setModuleService(final ModuleService moduleService) {
		this.moduleService = moduleService;
	}

}