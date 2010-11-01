/*
 * Copyright 2009-2010 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devproof.portal.module.blog.dataprovider;

import org.devproof.portal.core.config.GenericDataProvider;
import org.devproof.portal.core.module.common.dataprovider.SortableQueryDataProvider;
import org.devproof.portal.core.module.user.entity.UserEntity;
import org.devproof.portal.core.module.user.query.UserQuery;
import org.devproof.portal.module.blog.entity.BlogEntity;
import org.devproof.portal.module.blog.query.BlogQuery;

/**
 * @author Carsten Hufe
 */
@GenericDataProvider(value = "blogDataProvider", sortProperty = "createdAt", sortAscending = false)
public interface BlogDataProvider extends SortableQueryDataProvider<BlogEntity, BlogQuery> {
}