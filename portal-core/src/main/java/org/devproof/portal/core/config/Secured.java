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
package org.devproof.portal.core.config;

import org.apache.wicket.authorization.Action;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a protected page or wicket component
 *
 * @author Carsten Hufe
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Secured {

    /**
     * Rights, which are required to see or access a page
     */
    String[] value();

    /*
     * Hides the linked component if the right is missing, otherwise it just disables the link
     */
    String action() default Action.RENDER;
}
