/**
 * 
 */
package org.springframework.data.jpa.repository.query;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author zhaochf
 *
 */
public enum ExtensionJpaQueryFactory {

	INSTANCE;
	
	private static final SpelExpressionParser PARSER = new SpelExpressionParser();
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionJpaQueryFactory.class);
	
	/**
	 * Creates a {@link RepositoryQuery} from the given {@link QueryMethod} that is potentially annotated with
	 * {@link Query}.
	 * 
	 * @param method must not be {@literal null}.
	 * @param em must not be {@literal null}.
	 * @param evaluationContextProvider
	 * @return the {@link RepositoryQuery} derived from the annotation or {@code null} if no annotation found.
	 */
	public AbstractJpaQuery fromQueryAnnotation(JpaQueryMethod method, EntityManager em,
			EvaluationContextProvider evaluationContextProvider) {

		LOGGER.debug("Looking up query for method {}", method.getName());
		return fromMethodWithQueryString(method, em, method.getAnnotatedQuery(), evaluationContextProvider);
	}
	
	
	/**
	 * Creates a {@link RepositoryQuery} from the given {@link String} query.
	 * 
	 * @param method must not be {@literal null}.
	 * @param em must not be {@literal null}.
	 * @param queryString must not be {@literal null} or empty.
	 * @param evaluationContextProvider
	 * @return
	 */
	public AbstractJpaQuery fromMethodWithQueryString(JpaQueryMethod method, EntityManager em, String queryString,
			EvaluationContextProvider evaluationContextProvider) {

		if (queryString == null) {
			return null;
		}

		return method.isNativeQuery() ? new ExtensionNativeJpaQuery(method, em, queryString, evaluationContextProvider, PARSER)
				: new SimpleJpaQuery(method, em, queryString, evaluationContextProvider, PARSER);
	}
	
	/**
	 * Creates a {@link StoredProcedureJpaQuery} from the given {@link JpaQueryMethod} query.
	 * 
	 * @param method must not be {@literal null}.
	 * @param em must not be {@literal null}.
	 * @return
	 */
	public StoredProcedureJpaQuery fromProcedureAnnotation(JpaQueryMethod method, EntityManager em) {

		if (!method.isProcedureQuery()) {
			return null;
		}

		return new StoredProcedureJpaQuery(method, em);
	}
}
