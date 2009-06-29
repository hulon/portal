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
package org.devproof.portal.module.article;

import org.apache.wicket.ResourceReference;

/**
 * @author Carsten Hufe
 */
public class ArticleConstants {
	private ArticleConstants() {
	}

	final public static String PAGEBREAK = "<!-- pagebreak -->";
	final public static String CONF_ARTICLES_PER_PAGE = "articles_per_page";
	final public static String CONF_BOX_NUM_LATEST_ARTICLES = "box_num_latest_articles";
	final public static ResourceReference REF_ARTICLE_CSS = new ResourceReference(ArticleConstants.class, "css/article.css");
}
