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
package org.devproof.portal.module.comment.service;

import org.devproof.portal.core.module.common.service.CrudService;
import org.devproof.portal.module.comment.entity.Comment;

import java.util.List;

/**
 * @author Carsten Hufe
 */
public interface CommentService extends CrudService<Comment, Integer> {
    /**
     * Returns a new instance of Comment
     *
     * @return new instance of {@link org.devproof.portal.module.comment.entity.Comment}
     */
    Comment newCommentEntity();

    /**
     * Marks the comment as deleted (visible = false)
     */
    void rejectComment(Comment comment);

    /**
     * Marks the comment as reviewed
     */
    void acceptComment(Comment comment);

    /**
     * Saves a new comment and sends notification emails
     */
    void saveNewComment(Comment comment, UrlCallback urlCallback);

    /**
     * Returns the number of comments for the given module and module content id
     */
    long findNumberOfComments(String moduleName, String moduleContentId);

    /**
     * Report a violation
     */
    void reportViolation(Comment comment, UrlCallback urlCallback, String reporterIp);

    /**
     * Finds all available module names 
     */
    List<String> findAllModuleNames();
}
