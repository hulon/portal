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
package org.devproof.portal.module.blog.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.devproof.portal.core.module.common.CommonConstants;
import org.devproof.portal.core.module.common.dataprovider.SortableQueryDataProvider;
import org.devproof.portal.core.module.common.page.TemplatePage;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.core.module.feed.provider.FeedProvider;
import org.devproof.portal.core.module.role.entity.RoleEntity;
import org.devproof.portal.module.blog.BlogConstants;
import org.devproof.portal.module.blog.entity.BlogEntity;
import org.devproof.portal.module.blog.page.BlogPage;
import org.devproof.portal.module.blog.query.BlogQuery;
import org.springframework.beans.factory.annotation.Required;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * @author Carsten Hufe
 */
public class BlogFeedProvider implements FeedProvider {
	private SortableQueryDataProvider<BlogEntity> blogDataProvider;
	private ConfigurationService configurationService;

	@Override
	public SyndFeed getFeed(final RequestCycle rc, final RoleEntity role) {
		SyndFeed feed = new SyndFeedImpl();
		feed.setTitle(getFeedName());
		feed.setLink(rc.urlFor(BlogPage.class, new PageParameters()).toString());
		// must be set for RSS2 feed
		feed.setDescription(getFeedName());
		Integer maxNumber = configurationService.findAsInteger(BlogConstants.CONF_BLOG_ENTRIES_IN_FEED);
		setRoleForDataProviderQuery(role);
		Iterator<? extends BlogEntity> iterator = blogDataProvider.iterator(0, maxNumber);
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		SyndEntry entry;
		SyndContent description;
		while (iterator.hasNext()) {
			BlogEntity blogEntity = iterator.next();
			entry = new SyndEntryImpl();
			entry.setTitle(blogEntity.getHeadline());
			entry.setLink(rc.urlFor(BlogPage.class, new PageParameters("id=" + blogEntity.getId())).toString());
			entry.setPublishedDate(blogEntity.getModifiedAt());
			description = new SyndContentImpl();
			description.setType("text/plain");
			String content = blogEntity.getContent().replaceAll("<(.|\n)*?>", "");
			description.setValue(StringUtils.abbreviate(content, 200));
			entry.setDescription(description);
			entries.add(entry);
		}
		feed.setEntries(entries);
		return feed;
	}

	private void setRoleForDataProviderQuery(final RoleEntity role) {
		BlogQuery query = new BlogQuery();
		query.setRole(role);
		blogDataProvider.setQueryObject(query);
	}

	@Override
	public List<Class<? extends TemplatePage>> getSupportedFeedPages() {
		List<Class<? extends TemplatePage>> pages = new ArrayList<Class<? extends TemplatePage>>();
		pages.add(BlogPage.class);
		return pages;
	}

	@Override
	public String getFeedName() {
		String pageTitle = configurationService.findAsString(CommonConstants.CONF_PAGE_TITLE);
		String feedName = configurationService.findAsString(BlogConstants.CONF_BLOG_FEED_TITLE);
		return pageTitle + " - " + feedName;
	}

	@Required
	public void setBlogDataProvider(final SortableQueryDataProvider<BlogEntity> blogDataProvider) {
		this.blogDataProvider = blogDataProvider;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
}