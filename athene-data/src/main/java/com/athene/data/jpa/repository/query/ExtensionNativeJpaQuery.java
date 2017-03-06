/**
 * 
 */
package com.athene.data.jpa.repository.query;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.query.AbstractStringBasedJpaQueryBridge;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 
 * @author zhaochf
 *
 */
public final class ExtensionNativeJpaQuery extends AbstractStringBasedJpaQueryBridge {

	public ExtensionNativeJpaQuery(JpaQueryMethod method, EntityManager em, String queryString,
			EvaluationContextProvider evaluationContextProvider, SpelExpressionParser parser) {
		super(method, em, queryString, evaluationContextProvider, parser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.query.AbstractStringBasedJpaQuery
	 * #createJpaQuery(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected Query createJpaQuery(String queryString) {

		Class<?> returnedType = getQueryMethod().getReturnedObjectType();
		boolean isMapReturnedType = returnedType.isAssignableFrom(Map.class);
		Query query = getQueryMethod().isQueryForEntity()
				? getEntityManager().createNativeQuery(queryString, getQueryMethod().getReturnedObjectType())
				: getEntityManager().createNativeQuery(queryString);

		if (isMapReturnedType) {
			query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}
		return query;
	}

}
