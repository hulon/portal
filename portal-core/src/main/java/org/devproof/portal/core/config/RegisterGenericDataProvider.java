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
package org.devproof.portal.core.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marking the generic dataprovider interfaces
 *
 * @author Carsten Hufe
 */
@Documented
@Component
@Target(TYPE)
@Retention(RUNTIME)
public @interface RegisterGenericDataProvider {

    /**
     * No query class
     */
    public static final class NO_QUERY {}

    /**
     * Spring bean id
     */
    String value();

    /**
     * sort property
     */
    String sortProperty();

    /**
     * sorting
     */
    boolean sortAscending() default true;

    /**
     * hql count query e.g. count(*)
     */
    String countQuery() default "";

    /**
     * hqql prefetch query
     */
    String[] prefetch() default {};

    /**
     * The query class if one exists
     */
    Class<?> queryClass() default NO_QUERY.class;
}
