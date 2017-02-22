/**
 * 
 */
package com.athene.data.domain;

import java.io.Serializable;

/**
 * @author zhaochf
 *
 */
public abstract class AbstractEntity implements Entity {

	/**
	 * 
	 * @return
	 */
	public abstract Serializable getId();
	
	

	/* (non-Javadoc)
	 * @see com.athene.data.domain.Entity#isCompositeId()
	 */
	@Override
	public boolean isCompositeId() {
		return getId().getClass().isAssignableFrom(CompositeId.class);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return EntityHelper.INSTANCE.entityHashCode(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (this == null || obj == null) {
			return false;
		}
		
		if (obj instanceof AbstractEntity) {
			AbstractEntity other = (AbstractEntity) obj;
			return EntityHelper.INSTANCE.entityEquals(this, other);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return EntityHelper.INSTANCE.entityToString(this.getClass().getName(), this);
	}
	
}
