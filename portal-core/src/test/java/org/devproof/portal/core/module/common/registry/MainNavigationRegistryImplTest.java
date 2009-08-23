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
import java.util.List;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.devproof.portal.core.config.PageConfiguration;
import org.devproof.portal.core.module.common.locator.PageLocator;
import org.devproof.portal.core.module.modulemgmt.entity.ModuleLinkEntity;
import org.devproof.portal.core.module.modulemgmt.service.ModuleService;
import org.devproof.portal.core.module.user.page.LoginPage;
import org.easymock.EasyMock;

/**
 * @author Carsten Hufe
 */
public class MainNavigationRegistryImplTest extends TestCase {
	private MainNavigationRegistryImpl impl;
	private PageLocator pageLocatorMock;
	private ModuleService moduleServiceMock;

	@Override
	public void setUp() throws Exception {
		this.pageLocatorMock = EasyMock.createStrictMock(PageLocator.class);
		this.moduleServiceMock = EasyMock.createStrictMock(ModuleService.class);
		this.impl = new MainNavigationRegistryImpl();
		this.impl.setModuleService(this.moduleServiceMock);
		this.impl.setPageLocator(this.pageLocatorMock);
	}

	public void testGetRegisteredPages() {
		this.impl.registerPage(LoginPage.class);
		assertEquals(LoginPage.class, this.impl.getRegisteredPages().get(0));
	}

	public void testBuildNavigation() {
		Collection<PageConfiguration> confs = new ArrayList<PageConfiguration>();
		PageConfiguration conf = new PageConfiguration();
		conf.setPageClass(LoginPage.class);
		confs.add(conf);
		EasyMock.expect(this.pageLocatorMock.getPageConfigurations()).andReturn(confs);
		List<ModuleLinkEntity> links = new ArrayList<ModuleLinkEntity>();
		ModuleLinkEntity link = new ModuleLinkEntity();
		link.setPageName(LoginPage.class.getSimpleName());
		links.add(link);
		EasyMock.expect(this.moduleServiceMock.findAllVisibleMainNavigationLinks()).andReturn(links);
		EasyMock.replay(this.pageLocatorMock, this.moduleServiceMock);
		this.impl.buildNavigation();
		EasyMock.verify(this.pageLocatorMock, this.moduleServiceMock);
		assertEquals(LoginPage.class, this.impl.getRegisteredPages().get(0));
	}

	public void testAfterPropertiesSet() throws Exception {
		Collection<PageConfiguration> confs = new ArrayList<PageConfiguration>();
		PageConfiguration conf = new PageConfiguration();
		conf.setPageClass(LoginPage.class);
		confs.add(conf);
		EasyMock.expect(this.pageLocatorMock.getPageConfigurations()).andReturn(confs);
		List<ModuleLinkEntity> links = new ArrayList<ModuleLinkEntity>();
		ModuleLinkEntity link = new ModuleLinkEntity();
		link.setPageName(LoginPage.class.getSimpleName());
		links.add(link);
		EasyMock.expect(this.moduleServiceMock.findAllVisibleMainNavigationLinks()).andReturn(links);
		EasyMock.replay(this.pageLocatorMock, this.moduleServiceMock);
		this.impl.afterPropertiesSet();
		EasyMock.verify(this.pageLocatorMock, this.moduleServiceMock);
		assertEquals(LoginPage.class, this.impl.getRegisteredPages().get(0));
	}

	public void testRegisterPage() {
		this.impl.registerPage(LoginPage.class);
		assertEquals(this.impl.getRegisteredPages().get(0), LoginPage.class);
	}

	public void testRegisterPages() {
		List<Class<? extends Page>> pages = new ArrayList<Class<? extends Page>>();
		pages.add(LoginPage.class);
		this.impl.registerPages(pages);
		assertEquals(this.impl.getRegisteredPages().get(0), LoginPage.class);
	}

	public void testRemovePage() {
		this.impl.registerPage(LoginPage.class);
		assertEquals(1, this.impl.getRegisteredPages().size());
		this.impl.removePage(LoginPage.class);
		assertEquals(0, this.impl.getRegisteredPages().size());
	}

	public void testClearRegistry() {
		this.impl.registerPage(LoginPage.class);
		this.impl.clearRegistry();
		assertEquals(this.impl.getRegisteredPages().size(), 0);
	}
}