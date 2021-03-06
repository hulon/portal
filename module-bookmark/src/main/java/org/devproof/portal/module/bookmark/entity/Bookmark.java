/*
 * Copyright 2009-2011 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devproof.portal.module.bookmark.entity;

import org.devproof.portal.core.config.RegisterGenericDataProvider;
import org.devproof.portal.core.module.common.annotation.CacheQuery;
import org.devproof.portal.core.module.right.entity.Right;
import org.devproof.portal.module.bookmark.BookmarkConstants;
import org.devproof.portal.module.bookmark.query.BookmarkQuery;
import org.devproof.portal.module.deadlinkcheck.entity.BaseLink;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carsten Hufe
 */
@Entity
@Table(name = "bookmark")
@CacheQuery(region = BookmarkConstants.QUERY_CACHE_REGION)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = BookmarkConstants.ENTITY_CACHE_REGION)
@RegisterGenericDataProvider(value = "bookmarkDataProvider", sortProperty = "title", sortAscending = true, queryClass = BookmarkQuery.class)
public class Bookmark extends BaseLink {
    private static final long serialVersionUID = 1L;

    public enum Source {
        MANUAL, DELICIOUS
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private Source source = Source.MANUAL;
    @Column(name = "sync_username")
    private String syncUsername;
    @Column(name = "sync_hash")
    private String syncHash;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "bookmark_right_xref", joinColumns = @JoinColumn(name = "bookmark_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "right_id", referencedColumnName = "right_id"))
    private List<Right> allRights;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "bookmark_tag_xref", joinColumns = @JoinColumn(name = "bookmark_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tagname", referencedColumnName = "tagname"))
    private List<BookmarkTag> tags;

    @Transient
    public List<Right> getVoteRights() {
        return getRightsStartingWith(allRights, "bookmark.vote");
    }

    @Transient
    public List<Right> getVisitRights() {
        return getRightsStartingWith(allRights, "bookmark.visit");
    }

    @Transient
    public List<Right> getViewRights() {
        return getRightsStartingWith(allRights, "bookmark.view");
    }

    public List<Right> getAllRights() {
        if (allRights == null) {
            allRights = new ArrayList<Right>();
        }
        return allRights;
    }

    public void setAllRights(List<Right> allRights) {
        this.allRights = allRights;
    }

    public List<BookmarkTag> getTags() {
        return tags;
    }

    public void setTags(List<BookmarkTag> tags) {
        this.tags = tags;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSyncUsername() {
        return syncUsername;
    }

    public void setSyncUsername(String syncUsername) {
        this.syncUsername = syncUsername;
    }

    public String getSyncHash() {
        return syncHash;
    }

    public void setSyncHash(String syncHash) {
        this.syncHash = syncHash;
    }

}
