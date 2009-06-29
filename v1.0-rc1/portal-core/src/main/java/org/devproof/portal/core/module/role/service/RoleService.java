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
package org.devproof.portal.core.module.role.service;

import java.util.List;

import org.devproof.portal.core.module.common.service.CrudService;
import org.devproof.portal.core.module.role.entity.RoleEntity;

/**
 * @author Carsten Hufe
 */
public interface RoleService extends CrudService<RoleEntity, Integer> {
	/**
	 * Returns a new instance of role
	 * 
	 * @return new instance of role
	 */
	public RoleEntity newRoleEntity();

	/**
	 * Returns all roles ordered by description
	 * 
	 * @return list with all roles
	 */
	public List<RoleEntity> findAllOrderByDescription();
}