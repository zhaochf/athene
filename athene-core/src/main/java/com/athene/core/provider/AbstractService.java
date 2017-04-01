/**
 * 
 */
package com.athene.core.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.athene.ParameterUtils;
import com.athene.provider.api.Service;

import Ice.Current;
import Ice.DispatchStatus;
import Ice.ObjectImpl;
import IceInternal.BasicStream;
import IceInternal.Incoming;

/**
 * @author zhaochf
 *
 */
public abstract class AbstractService extends ObjectImpl implements Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3010942133746398616L;
	
	private final String[] ids = new String[2];
	
	private final Map<String, Method> operationMappings = Collections.synchronizedMap(new HashMap<>());

	/**
	 * 
	 */
	public AbstractService() {
		
		// init distributed service ids and operations
		resolveServiceId();
		resolveOperationMappings();
	}
	
	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#__dispatch(IceInternal.Incoming, Ice.Current)
	 */
	@Override
	public DispatchStatus __dispatch(Incoming in, Current current) {
		if ("ice_id".equals(current.operation)) {
			return ___ice_id(this, in, current);
		} else if ("ice_ids".equals(current.operation)) {
			return ___ice_ids(this, in, current);
		} else if ("ice_isA".equals(current.operation)) {
			return ___ice_isA(this, in, current);
		} else if ("ice_ping".equals(current.operation)) {
			return ___ice_ping(this, in, current);
		} else if (!operationMappings.containsKey(current.operation)) {
			throw new Ice.OperationNotExistException(current.id, current.facet, current.operation);
		}
		
		return invoke(operationMappings.get(current.operation), in, current);
	}

	/**
	 * 
	 * @param method
	 * @param in
	 * @param current
	 * @return
	 */
	private Ice.DispatchStatus invoke(Method method, IceInternal.Incoming in, Ice.Current current) {
		
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object result = null;
		try {
			if (parameterTypes == null || parameterTypes.length == 0) {
				result = method.invoke(this);
			} else {
				Object[] parameterValues = new Object[parameterTypes.length];
				IceInternal.BasicStream bs = in.startReadParams();
				for (int i = 0; i < parameterTypes.length; i++) {
					parameterValues[i] = ParameterUtils.readParameter(bs, parameterTypes[i]);
				}
				in.endReadParams();
				result = method.invoke(this, parameterValues);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if (void.class.isAssignableFrom(method.getReturnType())) {
			in.__writeEmptyParams();
		} else {
			IceInternal.BasicStream bs = in.__startWriteParams(Ice.FormatType.DefaultFormat);
			ParameterUtils.writeParameter(bs, method.getReturnType(), result);
			in.__endWriteParams(true);
		}

		return Ice.DispatchStatus.DispatchOK;
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_isA(java.lang.String)
	 */
	@Override
	public boolean ice_isA(String s) {
		return Arrays.binarySearch(this.ids, s) > 0;
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_isA(java.lang.String, Ice.Current)
	 */
	@Override
	public boolean ice_isA(String s, Current current) {
		return Arrays.binarySearch(this.ids, s) > 0;
	}
	
	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_ids()
	 */
	@Override
	public String[] ice_ids() {
		return this.ids;
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_ids(Ice.Current)
	 */
	@Override
	public String[] ice_ids(Current current) {
		return this.ids;
	}
	
	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_id()
	 */
	@Override
	public String ice_id() {
		return this.ids[1];
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#ice_id(Ice.Current)
	 */
	@Override
	public String ice_id(Current current) {
		return this.ids[1];
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#__writeImpl(IceInternal.BasicStream)
	 */
	@Override
	protected void __writeImpl(BasicStream os) {
		os.startWriteSlice(ice_staticId(), -1, true);
		os.endWriteSlice();
	}

	/* (non-Javadoc)
	 * @see Ice.ObjectImpl#__readImpl(IceInternal.BasicStream)
	 */
	@Override
	protected void __readImpl(BasicStream is) {
		is.startReadSlice();
		is.endReadSlice();
	}

	private void resolveServiceId() {
		this.ids[0] = serviceDefaultId;
		this.ids[1] = getClass().getName().replace(".", "::");
	}

	private void resolveOperationMappings() {
		List<Method> methods = Arrays.asList(getClass().getMethods());
		methods.forEach((method) -> {
			this.operationMappings.put(method.getName(), method);
		});
	}
}
