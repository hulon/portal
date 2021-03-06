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
package org.devproof.portal.core.module.configuration.repository;

import org.devproof.portal.core.config.GenericRepository;
import org.devproof.portal.core.module.common.annotation.Query;
import org.devproof.portal.core.module.common.repository.CrudRepository;
import org.devproof.portal.core.module.configuration.entity.Configuration;

import java.util.List;

/**
 * @author Carsten Hufe
 */
@GenericRepository("configurationRepository")
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
    @Query("Select c from Configuration c")
    List<Configuration> findAll();

    @Query("Select distinct(c.group) from Configuration c where c.group not like 'hidden' order by c.group")
    List<String> findConfigurationGroups();

    @Query("Select c from Configuration c where c.group = ? and c.key not like 'hidden.%' order by c.description")
    List<Configuration> findConfigurationsByGroup(String group);
}
