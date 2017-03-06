/**
 * 
 */
package org.springframework.data.jpa.repository.query;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.query.NamedQuery;
import org.springframework.data.jpa.repository.query.PartTreeJpaQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.util.Assert;

/**
 * 
 * @author zhaochf
 *
 */
public final class ExtensionJpaQueryLookupStrategy {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ExtensionJpaQueryLookupStrategy() {}
	
	/**
	 * Base class for {@link QueryLookupStrategy} implementations that need access to an {@link EntityManager}.
	 * 
	 * @author Oliver Gierke
	 * @author Thomas Darimont
	 */
	private abstract static class AbstractQueryLookupStrategy implements QueryLookupStrategy {

		private final EntityManager em;
		private final QueryExtractor provider;

		/**
		 * Creates a new {@link AbstractQueryLookupStrategy}.
		 * 
		 * @param em
		 * @param extractor
		 * @param evaluationContextProvider
		 */
		public AbstractQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			this.em = em;
			this.provider = extractor;
		}

		/* 
		 * (non-Javadoc)
		 * @see org.springframework.data.repository.query.QueryLookupStrategy#resolveQuery(java.lang.reflect.Method, org.springframework.data.repository.core.RepositoryMetadata, org.springframework.data.projection.ProjectionFactory, org.springframework.data.repository.core.NamedQueries)
		 */
		@Override
		public final RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory,
				NamedQueries namedQueries) {
			return resolveQuery(new JpaQueryMethod(method, metadata, factory, provider), em, namedQueries);
		}

		protected abstract RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries);
	}

	/**
	 * {@link QueryLookupStrategy} to create a query from the method name.
	 * 
	 * @author Oliver Gierke
	 * @author Thomas Darimont
	 */
	private static class CreateQueryLookupStrategy extends AbstractQueryLookupStrategy {

		private final PersistenceProvider persistenceProvider;

		public CreateQueryLookupStrategy(EntityManager em, QueryExtractor extractor) {

			super(em, extractor);
			this.persistenceProvider = PersistenceProvider.fromEntityManager(em);
		}

		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

			try {
				return new PartTreeJpaQuery(method, em, persistenceProvider);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(
						String.format("Could not create query metamodel for method %s!", method.toString()), e);
			}
		}

	}

	/**
	 * {@link QueryLookupStrategy} that tries to detect a declared query declared via {@link Query} annotation followed by
	 * a JPA named query lookup.
	 * 
	 * @author Oliver Gierke
	 * @author Thomas Darimont
	 */
	private static class DeclaredQueryLookupStrategy extends AbstractQueryLookupStrategy {

		private final EvaluationContextProvider evaluationContextProvider;

		/**
		 * Creates a new {@link DeclaredQueryLookupStrategy}.
		 * 
		 * @param em
		 * @param extractor
		 * @param evaluationContextProvider
		 */
		public DeclaredQueryLookupStrategy(EntityManager em, QueryExtractor extractor,
				EvaluationContextProvider evaluationContextProvider) {

			super(em, extractor);
			this.evaluationContextProvider = evaluationContextProvider;
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy.AbstractQueryLookupStrategy#resolveQuery(org.springframework.data.jpa.repository.query.JpaQueryMethod, javax.persistence.EntityManager, org.springframework.data.repository.core.NamedQueries)
		 */
		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

			RepositoryQuery query = ExtensionJpaQueryFactory.INSTANCE.fromQueryAnnotation(method, em, evaluationContextProvider);

			if (null != query) {
				return query;
			}

			query = ExtensionJpaQueryFactory.INSTANCE.fromProcedureAnnotation(method, em);

			if (null != query) {
				return query;
			}

			String name = method.getNamedQueryName();
			if (namedQueries.hasQuery(name)) {
				return ExtensionJpaQueryFactory.INSTANCE.fromMethodWithQueryString(method, em, namedQueries.getQuery(name),
						evaluationContextProvider);
			}

			query = NamedQuery.lookupFrom(method, em);

			if (null != query) {
				return query;
			}

			throw new IllegalStateException(
					String.format("Did neither find a NamedQuery nor an annotated query for method %s!", method));
		}
	}

	/**
	 * {@link QueryLookupStrategy} to try to detect a declared query first (
	 * {@link org.springframework.data.jpa.repository.Query}, JPA named query). In case none is found we fall back on
	 * query creation.
	 * 
	 * @author Oliver Gierke
	 * @author Thomas Darimont
	 */
	private static class CreateIfNotFoundQueryLookupStrategy extends AbstractQueryLookupStrategy {

		private final DeclaredQueryLookupStrategy lookupStrategy;
		private final CreateQueryLookupStrategy createStrategy;

		/**
		 * Creates a new {@link CreateIfNotFoundQueryLookupStrategy}.
		 * 
		 * @param em
		 * @param extractor
		 * @param createStrategy
		 * @param lookupStrategy
		 * @param evaluationContextProvider
		 */
		public CreateIfNotFoundQueryLookupStrategy(EntityManager em, QueryExtractor extractor,
				CreateQueryLookupStrategy createStrategy, DeclaredQueryLookupStrategy lookupStrategy) {

			super(em, extractor);

			this.createStrategy = createStrategy;
			this.lookupStrategy = lookupStrategy;
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy.AbstractQueryLookupStrategy#resolveQuery(org.springframework.data.jpa.repository.query.JpaQueryMethod, javax.persistence.EntityManager, org.springframework.data.repository.core.NamedQueries)
		 */
		@Override
		protected RepositoryQuery resolveQuery(JpaQueryMethod method, EntityManager em, NamedQueries namedQueries) {

			try {
				return lookupStrategy.resolveQuery(method, em, namedQueries);
			} catch (IllegalStateException e) {
				return createStrategy.resolveQuery(method, em, namedQueries);
			}
		}
	}

	/**
	 * Creates a {@link QueryLookupStrategy} for the given {@link EntityManager} and {@link Key}.
	 * 
	 * @param em must not be {@literal null}.
	 * @param key may be {@literal null}.
	 * @param extractor must not be {@literal null}.
	 * @param evaluationContextProvider must not be {@literal null}.
	 * @return
	 */
	public static QueryLookupStrategy create(EntityManager em, Key key, QueryExtractor extractor,
			EvaluationContextProvider evaluationContextProvider) {

		Assert.notNull(em, "EntityManager must not be null!");
		Assert.notNull(extractor, "QueryExtractor must not be null!");
		Assert.notNull(evaluationContextProvider, "EvaluationContextProvider must not be null!");

		switch (key != null ? key : Key.CREATE_IF_NOT_FOUND) {
			case CREATE:
				return new CreateQueryLookupStrategy(em, extractor);
			case USE_DECLARED_QUERY:
				return new DeclaredQueryLookupStrategy(em, extractor, evaluationContextProvider);
			case CREATE_IF_NOT_FOUND:
				return new CreateIfNotFoundQueryLookupStrategy(em, extractor, new CreateQueryLookupStrategy(em, extractor),
						new DeclaredQueryLookupStrategy(em, extractor, evaluationContextProvider));
			default:
				throw new IllegalArgumentException(String.format("Unsupported query lookup strategy %s!", key));
		}
	}
}
