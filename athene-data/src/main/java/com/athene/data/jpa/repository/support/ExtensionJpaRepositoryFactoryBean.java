/**
 * 
 */
package com.athene.data.jpa.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 
 * @author zhaochf
 *
 */
public class ExtensionJpaRepositoryFactoryBean <T extends Repository<S, ID>, S, ID extends Serializable>
		extends org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean<T, S, ID> {

	public ExtensionJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean#createRepositoryFactory(javax.persistence.EntityManager)
	 */
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new ExtensionJpaRepositoryFactory(entityManager);
	}
	
}
