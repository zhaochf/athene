/**
 * 
 */
package com.athene.data.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author zhaochf
 *
 */
public abstract class CompositeId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5606825151361473167L;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(EntityHelper.INSTANCE.getPropertyValueArray(this));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Optional.of(EntityHelper.INSTANCE.getSimpleProperties(this)).ifPresent((properties -> {
			properties.keySet().forEach((key) -> {
				sb.append(String.format("%s=%s, ", key, properties.get(key)));
			});
		}));
		
		return String.format("{%s}", sb.delete(sb.length() - 2, sb.length()).toString());
	}
	
}
