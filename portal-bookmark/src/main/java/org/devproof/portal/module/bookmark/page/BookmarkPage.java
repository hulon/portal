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
package org.devproof.portal.module.bookmark.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.app.PortalSession;
import org.devproof.portal.core.module.common.component.ExtendedLabel;
import org.devproof.portal.core.module.common.component.StatelessRatingPanel;
import org.devproof.portal.core.module.common.dataprovider.QueryDataProvider;
import org.devproof.portal.core.module.common.panel.AuthorPanel;
import org.devproof.portal.core.module.common.panel.BookmarkablePagingPanel;
import org.devproof.portal.core.module.common.panel.MetaInfoPanel;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.core.module.tag.panel.ContentTagPanel;
import org.devproof.portal.core.module.tag.service.TagService;
import org.devproof.portal.module.bookmark.BookmarkConstants;
import org.devproof.portal.module.bookmark.entity.BookmarkEntity;
import org.devproof.portal.module.bookmark.entity.BookmarkTagEntity;
import org.devproof.portal.module.bookmark.entity.BookmarkEntity.Source;
import org.devproof.portal.module.bookmark.panel.BookmarkSearchBoxPanel;
import org.devproof.portal.module.bookmark.query.BookmarkQuery;
import org.devproof.portal.module.bookmark.service.BookmarkService;
import org.devproof.portal.module.deadlinkcheck.DeadLinkCheckConstants;

/**
 * @author Carsten Hufe
 */
public class BookmarkPage extends BookmarkBasePage {

	private static final long serialVersionUID = 1L;
	@SpringBean(name = "bookmarkService")
	private BookmarkService bookmarkService;
	@SpringBean(name = "bookmarkDataProvider")
	private QueryDataProvider<BookmarkEntity> bookmarkDataProvider;
	@SpringBean(name = "bookmarkTagService")
	private TagService<BookmarkTagEntity> bookmarkTagService;
	@SpringBean(name = "configurationService")
	private ConfigurationService configurationService;

	public BookmarkPage(final PageParameters params) {
		super(params);
		final PortalSession session = (PortalSession) getSession();

		final BookmarkQuery query = new BookmarkQuery();
		if (!session.hasRight("bookmark.view")) {
			query.setRole(session.getRole());
		}
		if (!isAuthor() && configurationService.findAsBoolean(BookmarkConstants.CONF_BOOKMARK_HIDE_BROKEN)) {
			query.setBroken(false);
		}

		bookmarkDataProvider.setQueryObject(query);
		final BookmarkDataView dataView = new BookmarkDataView("listBookmark", params);
		add(dataView);
		addFilterBox(new BookmarkSearchBoxPanel("box", query, bookmarkDataProvider, this, dataView, params));
		add(new BookmarkablePagingPanel("paging", dataView, BookmarkPage.class, params));
		addTagCloudBox(bookmarkTagService, new PropertyModel<BookmarkTagEntity>(query, "tag"), BookmarkPage.class,
				params);
	}

	private class BookmarkDataView extends DataView<BookmarkEntity> {
		private static final long serialVersionUID = 1L;
		private final PageParameters params;
		private final boolean onlyOne;

		public BookmarkDataView(final String id, final PageParameters params) {
			super(id, bookmarkDataProvider);
			this.params = params;
			onlyOne = bookmarkDataProvider.size() == 1;
			setItemsPerPage(configurationService.findAsInteger(BookmarkConstants.CONF_BOOKMARKS_PER_PAGE));
		}

		@Override
		protected void populateItem(final Item<BookmarkEntity> item) {
			final BookmarkEntity bookmark = item.getModelObject();
			item.setOutputMarkupId(true);
			if (onlyOne) {
				setPageTitle(bookmark.getTitle());
			}

			final BookmarkView bookmarkViewPanel = new BookmarkView("bookmarkView", bookmark, params, isAuthor());
			if (isAuthor()) {
				bookmarkViewPanel.addOrReplace(new AuthorPanel<BookmarkEntity>("authorButtons", bookmark) {
					private static final long serialVersionUID = 1L;

					@Override
					public void onDelete(final AjaxRequestTarget target) {
						bookmarkService.delete(getEntity());
						item.setVisible(false);
						target.addComponent(item);
						target.addComponent(getFeedback());
						info(getString("msg.deleted"));
					}

					@Override
					public void onEdit(final AjaxRequestTarget target) {
						setResponsePage(new BookmarkEditPage(bookmark));
					}
				});
			}
			item.add(bookmarkViewPanel);
		}
	}

	public class BookmarkView extends Fragment {

		private static final long serialVersionUID = 1L;
		private final Model<Boolean> hasVoted = Model.of(Boolean.FALSE);

		public BookmarkView(final String id, final BookmarkEntity bookmarkEntity, final PageParameters params,
				final boolean isAuthor) {
			super(id, "bookmarkView", BookmarkPage.this);
			final PortalSession session = (PortalSession) getSession();
			final boolean voteEnabled = configurationService
					.findAsBoolean(BookmarkConstants.CONF_BOOKMARK_VOTE_ENABLED);
			final boolean allowedToVisit = session.hasRight("bookmark.visit", bookmarkEntity.getVisitRights());
			final boolean allowedToVote = session.hasRight("bookmark.vote", bookmarkEntity.getVoteRights());
			hasVoted.setObject(!allowedToVote);
			add(new WebMarkupContainer("authorButtons"));
			add(new Label("broken", BookmarkPage.this.getString("broken"))
					.setVisible(bookmarkEntity.getBroken() != null && bookmarkEntity.getBroken()));
			final BookmarkablePageLink<BookmarkRedirectPage> titleLink = new BookmarkablePageLink<BookmarkRedirectPage>(
					"titleLink", BookmarkRedirectPage.class);
			titleLink.setParameter("0", bookmarkEntity.getId());
			titleLink.setEnabled(allowedToVisit);

			titleLink.add(new Label("titleLabel", bookmarkEntity.getTitle()));
			add(titleLink);
			add(new MetaInfoPanel("metaInfo", bookmarkEntity));
			if (isAuthor && bookmarkEntity.getSource() == Source.DELICIOUS) {
				// be aware... images are stateful
				add(new Image("delicious", BookmarkConstants.REF_DELICIOUS));
			} else {
				add(new WebMarkupContainer("delicious").setVisible(false));
			}
			add(new ExtendedLabel("description", bookmarkEntity.getDescription()));
			add(new Label("hits", String.valueOf(bookmarkEntity.getHits())));
			add(new ContentTagPanel<BookmarkTagEntity>("tags", new ListModel<BookmarkTagEntity>(bookmarkEntity
					.getTags()), BookmarkPage.class, params));
			add(new StatelessRatingPanel("vote", new PropertyModel<Integer>(bookmarkEntity, "calculatedRating"), Model
					.of(5), new PropertyModel<Integer>(bookmarkEntity, "numberOfVotes"), hasVoted, true, params,
					bookmarkEntity.getId()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean onIsStarActive(final int star) {
					return star < ((int) (bookmarkEntity.getCalculatedRating() + 0.5));
				}

				@Override
				protected void onRated(final int rating) {
					if (allowedToVote) {
						hasVoted.setObject(Boolean.TRUE);
						bookmarkService.rateBookmark(rating, bookmarkEntity);
						info(BookmarkPage.this.getString("voteCounted"));
					}
				}
			}.setVisible(voteEnabled));

			final BookmarkablePageLink<?> bookmarkLink = new BookmarkablePageLink<Void>("bookmarkLink",
					BookmarkRedirectPage.class);
			bookmarkLink.add(new Image("bookmarkImage", DeadLinkCheckConstants.REF_DOWNLOAD_IMG));
			bookmarkLink.setParameter("0", bookmarkEntity.getId());
			final String labelKey = allowedToVisit ? "visitNow" : "loginToVisit";
			final Label bookmarkLinkLabel = new Label("bookmarkLinkLabel", BookmarkPage.this.getString(labelKey));
			this.add(bookmarkLink.add(bookmarkLinkLabel).setEnabled(allowedToVisit));
		}
	}
}