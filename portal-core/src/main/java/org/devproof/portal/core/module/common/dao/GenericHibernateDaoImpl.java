/*
 * Copyright 2009-2010 Carsten Hufe devproof.org
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
package org.devproof.portal.core.module.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.devproof.portal.core.module.common.entity.BaseEntity;
import org.devproof.portal.core.module.common.util.PortalUtil;
import org.devproof.portal.core.module.user.service.UsernameResolver;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Implementation of the generic dao
 * 
 * @author Carsten Hufe
 * 
 * @param <T>
 *            entity type
 * @param <PK>
 *            primary key type
 */
public class GenericHibernateDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements
		GenericDao<T, PK> {
	private static final Log LOG = LogFactory.getLog(GenericHibernateDaoImpl.class);
	private UsernameResolver usernameResolver;
	private Class<T> type;

	public GenericHibernateDaoImpl(Class<T> type) {
		this.type = type;
		LOG.debug("Constructor GenericHibernateDaoImpl");
	}

	@SuppressWarnings(value = "unchecked")
	public T findById(PK id) {
		return (T) getSession().get(this.type, id);
	}

	@SuppressWarnings(value = "unchecked")
	public List<T> findAll() {
		return getSession().createQuery("Select distinct(e) from " + type.getSimpleName() + " e").list();
	}

	public void save(T entity) {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(getSessionFactory());
		if (holder.getTransaction() == null) {
			holder.setTransaction(holder.getSession().beginTransaction());
		}
		LOG.debug("save " + type);

		if (entity instanceof BaseEntity) {
			BaseEntity base = (BaseEntity) entity;
			// only works in the request
			String username = usernameResolver.getUsername();
			LOG.debug("BaseEntity " + entity + "set creation date and user");
			if (base.getCreatedAt() == null) {
				base.setCreatedAt(PortalUtil.now());
			}
			if (base.getCreatedBy() == null) {
				base.setCreatedBy(username);
			}
			base.setModifiedAt(PortalUtil.now());
			base.setModifiedBy(username);
		}
		getSession().merge(entity);
	}

	@Override
	public void refresh(T entity) {
		getSession().refresh(entity);
	}

	public void delete(T entity) {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(getSessionFactory());
		if (holder.getTransaction() == null) {
			LOG.debug("No transaction found, start one.");
			holder.setTransaction(holder.getSession().beginTransaction());
		}
		getSession().delete(entity);
	}

	public Object executeFinder(String query, Object[] queryArgs, Class<?> returnType, Integer firstResults,
			Integer maxResults) {
		String tmpQuery = query;
		if (query.contains("$TYPE")) {
			tmpQuery = tmpQuery.replace("$TYPE", type.getSimpleName());
		}
		Query q = this.getSession().createQuery(tmpQuery);
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				q.setParameter(i, queryArgs[i]);
			}
		}
		if (firstResults != null) {
			q.setFirstResult(firstResults);
		}
		if (maxResults != null) {
			q.setMaxResults(maxResults);
		}
		if (Collection.class.isAssignableFrom(returnType)) {
			return q.list();
		} else {
			return q.uniqueResult();
		}
	}

	public void executeUpdate(String query, Object[] queryArgs) {
		String tmpQuery = query;
		if (query.contains("$TYPE")) {
			tmpQuery = tmpQuery.replace("$TYPE", type.getSimpleName());
		}
		Query q = getSession().createQuery(tmpQuery);
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				q.setParameter(i, queryArgs[i]);
			}
		}
		q.executeUpdate();
	}

	public Class<T> getType() {
		return type;
	}

	@Required
	public void setType(Class<T> type) {
		this.type = type;
	}

	@Required
	public void setUsernameResolver(UsernameResolver usernameResolver) {
		this.usernameResolver = usernameResolver;
	}
}
