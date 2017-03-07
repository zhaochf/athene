/**
 * 
 */
package org.springframework.data.jpa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.athene.data.domain.TreeEntity;

/**
 * @author zhaochf
 *
 */
@NoRepositoryBean
public interface TreeJpaRepository<T extends TreeEntity, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * Insert node for tree entity
	 * 
	 * @param entity
	 */
	public T insertNode(T entity);
	
	/**
	 * Delete tree node by entity id
	 * 
	 * @param entityId
	 */
	public void deleteNode(ID id);
	
	/**
	 * Find children by entity id and first level
	 * 
	 * @param entityId
	 * @return
	 */
	public List<T> findChildren(ID id);
	
	/**
	 * Find all children by entity id
	 * 
	 * @param entityId
	 * @return
	 */
	public List<T> findAllChildren(ID id);
	
}
