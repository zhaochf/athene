/**
 * 
 */
package org.springframework.data.jpa.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.TreeJpaRepository;
import org.springframework.data.jpa.repository.query.ExtensionJpaQueryLookupStrategy;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

/**
 * @author zhaochf
 *
 */
public class ExtensionJpaRepositoryFactory
		extends org.springframework.data.jpa.repository.support.JpaRepositoryFactory {

	private final EntityManager entityManager;
	private final QueryExtractor extractor;
	private final CrudMethodMetadataPostProcessor crudMethodMetadataPostProcessor;

	public ExtensionJpaRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
		this.extractor = PersistenceProvider.fromEntityManager(entityManager);
		this.crudMethodMetadataPostProcessor = new CrudMethodMetadataPostProcessor();
		
		addRepositoryProxyPostProcessor(crudMethodMetadataPostProcessor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.support.JpaRepositoryFactory#
	 * getQueryLookupStrategy(org.springframework.data.repository.query.
	 * QueryLookupStrategy.Key,
	 * org.springframework.data.repository.query.EvaluationContextProvider)
	 */
	@Override
	protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
		return ExtensionJpaQueryLookupStrategy.create(entityManager, key, extractor, evaluationContextProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.support.JpaRepositoryFactory#
	 * getTargetRepository(org.springframework.data.repository.core.
	 * RepositoryInformation)
	 */
	@Override
	protected Object getTargetRepository(RepositoryInformation information) {
		if (information.getRepositoryBaseClass().isAssignableFrom(TreeJpaRepository.class)) {
			TreeJpaRepositorySupport<?, ?> repository = getTargetTreeJpaRepository(information, entityManager);
			repository.setRepositoryMethodMetadata(crudMethodMetadataPostProcessor.getCrudMethodMetadata());

			return repository;
		}
		
		return super.getTargetRepository(information);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.support.JpaRepositoryFactory#
	 * getRepositoryBaseClass(org.springframework.data.repository.core.
	 * RepositoryMetadata)
	 */
	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return TreeJpaRepository.class.isAssignableFrom(metadata.getRepositoryInterface()) ? TreeJpaRepositorySupport.class
				: super.getRepositoryBaseClass(metadata);
	}

	/**
	 * 
	 * @param information
	 * @param entityManager
	 * @return
	 */
	protected <T, ID extends Serializable> TreeJpaRepositorySupport<?, ?> getTargetTreeJpaRepository(
			RepositoryInformation information, EntityManager entityManager) {
		
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());

		return getTargetRepositoryViaReflection(information, entityInformation, entityManager);
	}

}
