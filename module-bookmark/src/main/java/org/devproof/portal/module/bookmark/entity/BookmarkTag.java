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

import org.devproof.portal.core.module.tag.entity.AbstractTag;
import org.devproof.portal.module.bookmark.BookmarkConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Carsten Hufe
 */
@Entity
@Table(name = "bookmark_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = BookmarkConstants.ENTITY_CACHE_REGION)
public class BookmarkTag extends AbstractTag<Bookmark> {
    private static final long serialVersionUID = 1L;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Bookmark> referencedObjects;

    @Override
    public List<Bookmark> getReferencedObjects() {
        return referencedObjects;
    }

    @Override
    public void setReferencedObjects(List<Bookmark> referencedObjects) {
        this.referencedObjects = referencedObjects;
    }

}
