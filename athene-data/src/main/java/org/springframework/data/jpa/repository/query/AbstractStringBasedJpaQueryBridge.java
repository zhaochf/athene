/**
 * 
 */
package org.springframework.data.jpa.repository.query;

import javax.persistence.EntityManager;

import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author zhaochf
 *
 */
public abstract class AbstractStringBasedJpaQueryBridge extends AbstractStringBasedJpaQuery {

	public AbstractStringBasedJpaQueryBridge(JpaQueryMethod method, EntityManager em, String queryString,
			EvaluationContextProvider evaluationContextProvider, SpelExpressionParser parser) {
		super(method, em, queryString, evaluationContextProvider, parser);
	}

}
