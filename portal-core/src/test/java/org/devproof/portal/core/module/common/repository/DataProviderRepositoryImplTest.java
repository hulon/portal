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
package org.devproof.portal.core.module.common.repository;

import org.apache.wicket.PageParameters;
import org.devproof.portal.core.module.common.CommonConstants;
import org.devproof.portal.core.module.common.annotation.BeanJoin;
import org.devproof.portal.core.module.common.annotation.BeanQuery;
import org.devproof.portal.core.module.common.query.SearchQuery;
import org.devproof.portal.core.module.email.entity.EmailTemplate;
import org.devproof.portal.core.module.role.entity.Role;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Carsten Hufe
 */
public class DataProviderRepositoryImplTest {
	private DataProviderRepositoryImpl<EmailTemplate> impl;
	private SessionFactory sessionFactory;
	private Session session;
	private Query query;

	@Before
	public void setUp() throws Exception {
		sessionFactory = createMock(SessionFactory.class);
		session = createMock(Session.class);
		impl = new DataProviderRepositoryImpl<EmailTemplate>();
		impl.setSessionFactory(sessionFactory);
		query = createMock(Query.class);
		expect(sessionFactory.getCurrentSession()).andReturn(session);
	}

	@Test
	public void testFindById() {
		EmailTemplate expectedTemplates = newEmailTemplate();
		expect(session.get(EmailTemplate.class, 1)).andReturn(expectedTemplates);
		replay(sessionFactory, session);
		EmailTemplate newTemplate = impl.findById(EmailTemplate.class, 1);
		assertEquals(expectedTemplates, newTemplate);
		verify(session, sessionFactory);
	}

	@Test
	public void testFindAll_byClass() {
		List<EmailTemplate> expectedTemplates = Arrays.asList(newEmailTemplate());
		expect(session.createQuery("Select e from EmailTemplate e")).andReturn(query);
		expect(query.setCacheable(true)).andReturn(query);
		expect(query.setCacheMode(null)).andReturn(query);
		expect(query.setCacheRegion(CommonConstants.QUERY_CORE_CACHE_REGION)).andReturn(query);
		expect(query.list()).andReturn(expectedTemplates);
		replay(sessionFactory, session, query);
		List<EmailTemplate> templates = impl.findAll(EmailTemplate.class);
		assertEquals(expectedTemplates.get(0).getId(), templates.get(0).getId());
		verify(session, sessionFactory, query);
	}

	@Test
	public void testFindAll_byClassLimited() {
		List<EmailTemplate> expectedTemplates = Arrays.asList(newEmailTemplate());
		expect(session.createQuery("Select e from EmailTemplate e")).andReturn(query);
		expect(query.setCacheable(true)).andReturn(query);
		expect(query.setCacheMode(null)).andReturn(query);
		expect(query.setCacheRegion(CommonConstants.QUERY_CORE_CACHE_REGION)).andReturn(query);
		expect(query.setFirstResult(20)).andReturn(query);
		expect(query.setMaxResults(10)).andReturn(query);
		expect(query.list()).andReturn(expectedTemplates);
		replay(sessionFactory, session, query);
		List<EmailTemplate> templates = impl.findAll(EmailTemplate.class, 20, 10);
		assertEquals(expectedTemplates.get(0).getId(), templates.get(0).getId());
		verify(session, sessionFactory, query);
	}

	@Test
	public void testFindAllWithQuery() {
		List<EmailTemplate> expectedTemplates = Arrays.asList(newEmailTemplate());
		TestQuery testQuery = new TestQuery();
		testQuery.setAllTextFields("foobar");
		List<String> prefetch = Arrays.asList("prefetched_field");
		expect(
				session.createQuery("Select e from EmailTemplate e"
						+ "  left join fetch e.prefetched_field  left join e.allRights vr left join e.tags t"
						+ "  where e.headline like ? order by e.subject ASC")).andReturn(query);
		expect(query.setParameter(0, "foobar")).andReturn(query);
		expect(query.list()).andReturn(expectedTemplates);
		expect(query.setCacheable(true)).andReturn(query);
		expect(query.setCacheMode(null)).andReturn(query);
		expect(query.setCacheRegion(CommonConstants.QUERY_CORE_CACHE_REGION)).andReturn(query);
		expect(query.setFirstResult(20)).andReturn(query);
		expect(query.setMaxResults(10)).andReturn(query);
		replay(sessionFactory, session, query);
		List<EmailTemplate> templates = impl.findAllWithQuery(EmailTemplate.class, "subject", true, 20, 10,
				testQuery, prefetch);
		assertEquals(expectedTemplates.get(0).getId(), templates.get(0).getId());
		verify(session, sessionFactory, query);
	}

	@Test
	public void testGetSize_byBeanQuery() {
		TestQuery testQuery = new TestQuery();
		testQuery.setAllTextFields("foobar");
		expect(
				session.createQuery("Select count(e) from EmailTemplate e"
						+ "  left join e.allRights vr left join e.tags t" + "  where e.headline like ?")).andReturn(
				query);
		expect(query.setParameter(0, "foobar")).andReturn(query);
		expect(query.uniqueResult()).andReturn(2l);
		replay(sessionFactory, session, query);
		long size = impl.getSize(EmailTemplate.class, testQuery);
		assertEquals(2, size);
		verify(session, sessionFactory, query);
	}

	@Test
	public void testGetSize_withCountQuery() {
		TestQuery testQuery = new TestQuery();
		testQuery.setAllTextFields("foobar");
		expect(
				session.createQuery("Select count(something) from EmailTemplate e"
						+ "  left join e.allRights vr left join e.tags t" + "  where e.headline like ?")).andReturn(
				query);
		expect(query.setParameter(0, "foobar")).andReturn(query);
		expect(query.uniqueResult()).andReturn(2l);
		replay(sessionFactory, session, query);
		long size = impl.getSize(EmailTemplate.class, "count(something)", testQuery);
		assertEquals(2, size);
		verify(session, sessionFactory, query);
	}

	private EmailTemplate newEmailTemplate() {
		EmailTemplate expectedConfig = new EmailTemplate();
		expectedConfig.setId(1);
		return expectedConfig;
	}

	@BeanJoin("left join e.allRights vr left join e.tags t")
	public static class TestQuery implements SearchQuery {
		private static final long serialVersionUID = 1L;
		private Integer id;
		private Role role;
		private String allTextFields;

		@BeanQuery("vr in(select rt from Role r join r.rights rt where r = ?)")
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		@BeanQuery("e.headline like ?")
		public String getAllTextFields() {
			return allTextFields;
		}

		public void setAllTextFields(String allTextFields) {
			this.allTextFields = allTextFields;
		}

		@BeanQuery("e.id = ?")
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public PageParameters getPageParameters() {
			return new PageParameters();
		}
	}
}
