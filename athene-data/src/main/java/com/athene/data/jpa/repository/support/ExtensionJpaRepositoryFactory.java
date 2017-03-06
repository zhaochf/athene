/**
 * 
 */
package com.athene.data.jpa.repository.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.ExtensionJpaQueryLookupStrategy;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

/**
 * @author zhaochf
 *
 */
public class ExtensionJpaRepositoryFactory extends org.springframework.data.jpa.repository.support.JpaRepositoryFactory {

	private final EntityManager entityManager;
	private final QueryExtractor extractor;
	
	public ExtensionJpaRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
		this.extractor = PersistenceProvider.fromEntityManager(entityManager);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactory#getQueryLookupStrategy(org.springframework.data.repository.query.QueryLookupStrategy.Key, org.springframework.data.repository.query.EvaluationContextProvider)
	 */
	@Override
	protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
		return ExtensionJpaQueryLookupStrategy.create(entityManager, key, extractor, evaluationContextProvider);
	}
}
