/**
 * 
 */
package com.athene.data.domain;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * Entity utils
 * 
 * @author zhaochf
 *
 */
public enum EntityHelper {
	
	INSTANCE;
	
	private static final Logger logger = LoggerFactory.getLogger(EntityHelper.class);
	
	private static final String ID_NAME = "id";
	
	private PropertyUtilsBean propertyUtilsBean;
	
	public static final Set<Class<?>> classes = new HashSet<Class<?>>();
	
	static {
		classes.add(String.class);
		classes.add(Date.class);
		classes.add(java.sql.Date.class);
		classes.add(LocalDate.class);
	}

	/**
	 * @param propertyUtilsBean
	 */
	private EntityHelper() {
		this.propertyUtilsBean = new PropertyUtilsBean();
	}
	
	public Map<String, Object> getSimpleProperties(Object entity) {
		final  Map<String, Object> result = new HashMap<>();
		Optional<PropertyDescriptor[]> optional = Optional.of(propertyUtilsBean.getPropertyDescriptors(entity));
		optional.ifPresent((propertyDescriptors) -> {
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String name = propertyDescriptor.getName();
				try {
					if (isMappingField(propertyDescriptor)) {
							result.put(name, propertyUtilsBean.getProperty(entity, name));
					}
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					logger.error(e.getMessage());
				}
			}
		});

		return result;
	}
	
	public Map<String, Object> getProperties(AbstractEntity entity) {
		Optional<Map<String, Object>> optional = Optional.of(getSimpleProperties(entity));
		if (entity.isCompositeId() && optional.isPresent()) {
			optional.get().put(ID_NAME, entity.getId());
		}
		
		return optional.get();
	}
	
	public Set<String> getPropertyNames(AbstractEntity entity) {
		Optional<Map<String, Object>> optional = Optional.of(getProperties(entity));
		return optional.isPresent() ? optional.get().keySet() : null;
	}
	
	public Collection<Object> getPropertyValues(Object entity) {
		Optional<Map<String, Object>> optional = Optional.of(getSimpleProperties(entity));
		return optional.isPresent() ? optional.get().values() : null;
	}
	
	public Collection<Object> getPropertyValues(AbstractEntity entity) {
		Optional<Map<String, Object>> optional = Optional.of(getProperties(entity));
		return optional.isPresent() ? optional.get().values() : null;
	}
	
	public Object[] getPropertyValueArray(Object entity) {
		Optional<Collection<Object>> optional = Optional.of(getPropertyValues(entity));
		return optional.isPresent() ? optional.get().toArray() : null; 
	}
	
	public Object[] getPropertyValueArray(AbstractEntity entity) {
		Optional<Collection<Object>> optional = Optional.of(getPropertyValues(entity));
		return optional.isPresent() ? optional.get().toArray() : null; 
	}
	
	
	public int entityHashCode(AbstractEntity entity) {
		Optional<Object[]> optional = Optional.of(getPropertyValueArray(entity));
		return optional.isPresent() ? Objects.hash(optional.get()) : entity.hashCode();
	}
	
	public boolean entityEquals(AbstractEntity entity, AbstractEntity other) {
		return entity.getId().hashCode() == other.getId().hashCode();
	}
	
	public String entityToString(String clazz, AbstractEntity entity) {
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(clazz);
		Optional.of(getProperties(entity)).ifPresent((properties) -> {
			toStringHelper.add(ID_NAME, entity.getId());
			properties.forEach((key, value) -> {
				if (ID_NAME.equals(key)) {
					return;
				}
				toStringHelper.add(key, value);
			});

		});
		return toStringHelper.toString();
	}
	
	public boolean isMappingField(PropertyDescriptor propertyDescriptor) {
		return (propertyDescriptor.getPropertyType().isPrimitive() || 
				classes.contains(propertyDescriptor.getPropertyType())) && 
				Optional.ofNullable(propertyDescriptor.getReadMethod()).isPresent() && 
				Optional.ofNullable(propertyDescriptor.getWriteMethod()).isPresent();
	}
	
}
