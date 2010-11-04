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
package org.devproof.portal.module.article.service;

import org.devproof.portal.core.module.role.entity.Role;
import org.devproof.portal.module.article.entity.Article;
import org.devproof.portal.module.article.repository.ArticlePageRepository;
import org.devproof.portal.module.article.repository.ArticleRepository;
import org.devproof.portal.module.article.entity.ArticlePage;
import org.devproof.portal.module.article.entity.ArticlePageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Carsten Hufe
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private ArticlePageRepository articlePageRepository;
    private ArticleTagService articleTagService;

    @Override
    @Transactional(readOnly = true)
    public boolean existsContentId(String contentId) {
        return articleRepository.existsContentId(contentId) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAllArticlesForRoleOrderedByDateDesc(Role role, Integer firstResult, Integer maxResult) {
        return articleRepository.findAllArticlesForRoleOrderedByDateDesc(role, firstResult, maxResult);
    }

    @Override
    @Transactional(readOnly = true)
    public Article newArticleEntity() {
        Article article = new Article();
        article.setAllRights(articleRepository.findLastSelectedRights());
        return article;
    }

    @Override
    @Transactional(readOnly = true)
    public ArticlePage newArticlePageEntity(Article article, Integer page) {
        return article.newArticlePageEntity(page);
    }

    @Override
    @Transactional
    public void delete(Article entity) {
        articleRepository.delete(entity);
        articleTagService.deleteUnusedTags();
    }

    @Override
    @Transactional(readOnly = true)
    public Article findById(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Article entity) {
        articleRepository.save(entity);
        articleTagService.deleteUnusedTags();
    }

    @Override
    @Transactional(readOnly = true)
    public long getPageCount(String contentId) {
        return articlePageRepository.getPageCount(contentId);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticlePage findArticlePageByContentIdAndPage(String contentId, Integer page) {
        return articlePageRepository.findById(new ArticlePageId(contentId, page));
    }

    @Override
    @Transactional(readOnly = true)    
    public Article findByContentId(String contentId) {
        return articleRepository.findByContentId(contentId);
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setArticlePageRepository(ArticlePageRepository articlePageRepository) {
        this.articlePageRepository = articlePageRepository;
    }

    @Autowired
    public void setArticleTagService(ArticleTagService articleTagService) {
        this.articleTagService = articleTagService;
    }
}
