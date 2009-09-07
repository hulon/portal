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
package org.devproof.portal.module.download.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.wicket.RequestCycle;
import org.devproof.portal.core.module.common.CommonConstants;
import org.devproof.portal.core.module.common.dataprovider.SortableQueryDataProvider;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.core.module.role.entity.RoleEntity;
import org.devproof.portal.module.download.DownloadConstants;
import org.devproof.portal.module.download.entity.DownloadEntity;
import org.devproof.portal.module.download.page.DownloadPage;
import org.easymock.EasyMock;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * @author Carsten Hufe
 */
public class DownloadFeedProviderImplTest extends TestCase {
	private DownloadFeedProviderImpl impl;
	private SortableQueryDataProvider<DownloadEntity> dataProviderMock;
	private ConfigurationService configurationServiceMock;

	@Override
	@SuppressWarnings("unchecked")
	public void setUp() throws Exception {
		dataProviderMock = EasyMock.createStrictMock(SortableQueryDataProvider.class);
		configurationServiceMock = EasyMock.createMock(ConfigurationService.class);
		impl = new DownloadFeedProviderImpl() {
			@Override
			protected String getUrl(final RequestCycle rc) {
				return "http://url";
			}

			@Override
			protected String getUrl(final RequestCycle rc, final DownloadEntity downloadEntity) {
				return "http://url/" + downloadEntity.getId();
			}
		};
		impl.setConfigurationService(configurationServiceMock);
		impl.setDownloadDataProvider(dataProviderMock);
	}

	public void testGetFeedName() {
		EasyMock.expect(configurationServiceMock.findAsString(CommonConstants.CONF_PAGE_TITLE)).andReturn("pagetitle");
		EasyMock.expect(configurationServiceMock.findAsString(DownloadConstants.CONF_DOWNLOAD_FEED_TITLE)).andReturn(
				"feedtitle");
		EasyMock.replay(configurationServiceMock);
		assertEquals("pagetitle - feedtitle", impl.getFeedName());
		EasyMock.verify(configurationServiceMock);
	}

	public void testSupportedPages() {
		assertEquals(DownloadPage.class, impl.getSupportedFeedPages().get(0));
	}

	@SuppressWarnings("unchecked")
	public void testGetDownloadEntries() {
		DownloadEntity download = createDownload();
		Iterator it = Arrays.asList(download).iterator();
		EasyMock.expect(configurationServiceMock.findAsInteger(DownloadConstants.CONF_DOWNLOAD_ENTRIES_IN_FEED))
				.andReturn(10);
		EasyMock.expect(dataProviderMock.iterator(0, 10)).andReturn(it);
		EasyMock.replay(configurationServiceMock);
		EasyMock.replay(dataProviderMock);
		Iterator<? extends DownloadEntity> bookmarkEntries = impl.getDownloadEntries();
		assertSame(bookmarkEntries, it);
		EasyMock.verify(configurationServiceMock);
		EasyMock.verify(dataProviderMock);
	}

	public void testGenerateFeed() {
		EasyMock.expect(configurationServiceMock.findAsString(CommonConstants.CONF_PAGE_TITLE)).andReturn("pagetitle")
				.anyTimes();
		EasyMock.expect(configurationServiceMock.findAsString(DownloadConstants.CONF_DOWNLOAD_FEED_TITLE)).andReturn(
				"feedtitle").anyTimes();
		EasyMock.replay(configurationServiceMock);
		SyndFeed feed = impl.generateFeed(null);
		assertEquals("pagetitle - feedtitle", feed.getTitle());
		assertEquals("pagetitle - feedtitle", feed.getDescription());
		assertEquals("http://url", feed.getLink());
		EasyMock.verify(configurationServiceMock);
	}

	@SuppressWarnings("unchecked")
	public void testGenerateFeedEntries() {
		DownloadEntity bookmark = createDownload();
		Iterator it = Arrays.asList(bookmark).iterator();
		List<SyndEntry> generateFeedEntries = impl.generateFeedEntries(null, it);
		SyndEntry entry = generateFeedEntries.get(0);
		assertEquals("hello", entry.getTitle());
		assertEquals("http://url/" + bookmark.getId(), entry.getLink());
		assertEquals("world", entry.getDescription().getValue());
		assertEquals("text/plain", entry.getDescription().getType());
		assertEquals("maxpower", entry.getAuthor());
		assertNotNull(entry.getPublishedDate());
	}

	public void testGetFeed() {
		final List<SyndEntry> entries = new ArrayList<SyndEntry>();
		final StringBuilder callOrder = new StringBuilder();
		impl = new DownloadFeedProviderImpl() {
			@Override
			protected SyndFeed generateFeed(final RequestCycle rc) {
				callOrder.append("1");
				return new SyndFeedImpl();
			}

			@Override
			protected void setRoleForDataProviderQuery(final RoleEntity role) {
				callOrder.append("2");
			}

			@Override
			protected Iterator<? extends DownloadEntity> getDownloadEntries() {
				callOrder.append("3");
				return null;
			}

			@Override
			protected List<SyndEntry> generateFeedEntries(final RequestCycle rc,
					final Iterator<? extends DownloadEntity> iterator) {
				callOrder.append("4");
				return entries;
			}

			@Override
			protected String getUrl(final RequestCycle rc) {
				return "";
			}
		};
		impl.getFeed(null, new RoleEntity());
		assertEquals("1234", callOrder.toString());
	}

	private DownloadEntity createDownload() {
		DownloadEntity download = new DownloadEntity();
		download.setId(1);
		download.setTitle("hello");
		download.setDescription("world");
		download.setModifiedBy("maxpower");
		download.setModifiedAt(new Date());
		return download;
	}
}