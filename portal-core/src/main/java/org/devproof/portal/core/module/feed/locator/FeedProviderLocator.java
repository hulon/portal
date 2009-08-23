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
package org.devproof.portal.core.module.feed.locator;

import java.util.Collection;

import org.devproof.portal.core.module.feed.provider.FeedProvider;

/**
 * Locates feed providers
 * 
 * @author Carsten Hufe
 * 
 */
public interface FeedProviderLocator {
	/**
	 * Returns the feed providers of all modules
	 */
	public Collection<FeedProvider> getFeedProviders();
}
