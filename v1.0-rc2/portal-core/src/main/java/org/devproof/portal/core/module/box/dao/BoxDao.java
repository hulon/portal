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
package org.devproof.portal.core.module.box.dao;

import java.util.List;

import org.devproof.portal.core.module.box.entity.BoxEntity;
import org.devproof.portal.core.module.common.annotation.Query;
import org.devproof.portal.core.module.common.dao.GenericDao;

/**
 * @author Carsten Hufe
 */
public interface BoxDao extends GenericDao<BoxEntity, Integer> {
	@Query("select max(b.sort) from BoxEntity b")
	public Integer getMaxSortNum();

	@Query("select b from BoxEntity b where b.sort = ?")
	public BoxEntity findBoxBySort(Integer sort);

	@Query("select b from BoxEntity b order by b.sort")
	public List<BoxEntity> findAllOrderedBySort();
}