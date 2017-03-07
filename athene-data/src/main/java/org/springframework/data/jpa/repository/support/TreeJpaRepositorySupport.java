/**
 * 
 */
package org.springframework.data.jpa.repository.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.TreeJpaRepository;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.athene.data.domain.TreeEntity;

/**
 * Tree repository implements
 * 
 * @author zhaochf
 *
 */
@Repository
@Transactional(readOnly = true)
public class TreeJpaRepositorySupport<T extends TreeEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements TreeJpaRepository<T, ID> {
	private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

	private static final String INSERT_UPDATE_RIGHT_LIMIT_QUERY_STRING = "update %s x set x.rightLimit = x.rightLimit + 2 where x.rightLimit >= :rightLimit";
	
	private static final String INSERT_UPDATE_LEFT_LIMIT_QUERY_STRING = "update %s x set x.leftLimit = x.leftLimit + 2 where x.leftLimit >= :rightLimit";
	
	private static final String DELETE_ALL_CHILDREN_QUERY_STRING = "delete from %s x where leftLimit >= :leftLimit and rightLimit <= :rightLimit";
	
	private static final String DELETE_UPDATE_PARENT_QUERY_STRING = "update %s x set x.parentId = :parentId where x.leftLimit between :leftLimit and :rightLimit";
	
	private static final String DELETE_UPDATE_RIGHT_LIMIT_QUERY_STRING = "update %s x set x.rightLimit = x.rightLimit - :limit where x.rightLimit > :rightLimit";
	
	private static final String DELETE_UPDATE_LEFT_LIMIT_QUERY_STRING = "update %s x set x.leftLimit = x.leftLimit - :limit where x.leftLimit > :leftLimit";
	
	private static final String DELETE_EXISTS_CHILDREN_QUERY_STRING = "select count(x) from %s x where x.parentId = :parentId";
	
	private static final String DELETE_UPDATE_ISLEAF_QUERY_STRING = "update %s x set x.isLeaf = :isLeaf where id = :id"; 
	
	private static final String SELECT_CHILDREN_QUERY_STRING = "select x from %s x where x.parentId = :parentId and x.id != x.parentId";
	
	private static final String SELECT_CHILDREN_ALL_QUERY_STRING = "select x from %s x where x.leftLimit between :leftLimit and :rightLimit";
	

	private final JpaEntityInformation<T, ?> entityInformation;
	private final EntityManager entityManager;

	/**
	 * @param domainClass
	 * @param em
	 */
	public TreeJpaRepositorySupport(Class<T> domainClass, EntityManager entityManager) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
	}

	/**
	 * @param entityInformation
	 * @param entityManager
	 */
	public TreeJpaRepositorySupport(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.athene.data.jpa.repository.TreeRepository#insertNode(com.athene.data.domain.TreeEntity)
	 */
	@Override
	@Transactional
	public T insertNode(T entity) {
		Assert.notNull(entity);
		
		// Get parent node entity
		T parent = find(entity.getParentId());
		Assert.notNull(parent);
		
		// Update parent is leaf false
		parent.setIsLeaf(false);
		save(parent);

		// Update tree node right and left limit
		entityManager.createQuery(QueryUtils.getQueryString(INSERT_UPDATE_RIGHT_LIMIT_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("rightLimit", parent.getRightLimit()).executeUpdate();
		
		entityManager.createQuery(QueryUtils.getQueryString(INSERT_UPDATE_LEFT_LIMIT_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("rightLimit", parent.getRightLimit()).executeUpdate();
		
		// Save entity
		entity.setIsLeaf(true);
		entity.setLeftLimit(parent.getRightLimit());
		entity.setRightLimit(parent.getRightLimit() + 1);
		entity.setLevel(parent.getLevel() + 1);
		
		return save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.athene.data.jpa.repository.TreeRepository#deleteNode(java.lang.
	 * String)
	 */
	@Override
	@Transactional
	public void deleteNode(ID id) {
		// Get will delete node
		T node = findOne(id);
		Assert.notNull(node);
		
		// Update all will delete children parent id value of root
		entityManager.createQuery(QueryUtils.getQueryString(DELETE_UPDATE_PARENT_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("parentId", TreeEntity.ROOT_ID)
			.setParameter("leftLimit", node.getLeftLimit())
			.setParameter("rightLimit", node.getRightLimit()).executeUpdate();
		
		// Delete all children
		entityManager.createQuery(QueryUtils.getQueryString(DELETE_ALL_CHILDREN_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("leftLimit", node.getLeftLimit())
			.setParameter("rightLimit", node.getRightLimit()).executeUpdate();
		
		// Update tree node right and left limit
		int limit = node.getRightLimit() - node.getLeftLimit() + 1;
		entityManager.createQuery(QueryUtils.getQueryString(DELETE_UPDATE_RIGHT_LIMIT_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("limit", limit)
			.setParameter("rightLimit", node.getRightLimit()).executeUpdate();
		
		entityManager.createQuery(QueryUtils.getQueryString(DELETE_UPDATE_LEFT_LIMIT_QUERY_STRING, entityInformation.getEntityName()))
			.setParameter("limit", limit)
			.setParameter("leftLimit", node.getLeftLimit()).executeUpdate();
		
		// process parent node is leaf value
		boolean existOtherChildren = entityManager.createQuery(QueryUtils.getQueryString(DELETE_EXISTS_CHILDREN_QUERY_STRING, entityInformation.getEntityName()), Long.class)
				.setParameter("parentId", node.getParentId()).getSingleResult() > 0;
		if (!existOtherChildren) {
			entityManager.createQuery(QueryUtils.getQueryString(DELETE_UPDATE_ISLEAF_QUERY_STRING, entityInformation.getEntityName()))
				.setParameter("isLeaf", true)
				.setParameter("id", node.getParentId()).executeUpdate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.athene.data.jpa.repository.TreeRepository#findChildren(java.lang.
	 * String)
	 */
	@Override
	public List<T> findChildren(ID id) {
		
		Assert.notNull(id);
		
		return entityManager.createQuery(QueryUtils.getQueryString(SELECT_CHILDREN_QUERY_STRING, entityInformation.getEntityName()), getDomainClass())
				.setParameter("parentId", id).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.athene.data.jpa.repository.TreeRepository#findAllChildren(java.lang.
	 * String)
	 */
	@Override
	public List<T> findAllChildren(ID id) {
		
		Assert.notNull(id);
		
		// Get will delete node
		T node = findOne(id);
		Assert.notNull(node);
		return entityManager.createQuery(QueryUtils.getQueryString(SELECT_CHILDREN_ALL_QUERY_STRING, entityInformation.getEntityName()), getDomainClass())
				.setParameter("leftLimit", node.getLeftLimit())
				.setParameter("rightLimit", node.getRightLimit()).getResultList();
	}

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 * @return
	 */
	private T find(Object id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);

		Class<T> domainType = getDomainClass();

		return entityManager.find(domainType, id);
	}
}
