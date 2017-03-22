/**
 * 
 */
package com.athene.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.athene.ParameterUtils;
import com.athene.provider.api.Service;

import Ice.AsyncResult;
import Ice.Exception;
import Ice.FormatType;
import Ice.OperationMode;
import Ice.UnknownUserException;
import Ice.UserException;
import IceInternal.BasicStream;
import IceInternal.CallbackBase;
import IceInternal.OutgoingAsync;

/**
 * @author zhaochf
 *
 */
public class ConsumerProxy<T extends Service> extends Ice.ObjectPrxHelperBase implements InvocationHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369218987058000123L;
	
	private static final String[] IDS = new String[2];
	
	private static final Map<Method, String> OPERATION_MAPPINGS = Collections.synchronizedMap(new HashMap<Method, String>());
	
	static {
		IDS[0] = Service.PROVIDER_DEFAULT_ID; 
	}
	
	
	/**
	 * @param serviceClass
	 */
	public ConsumerProxy(Class<T> serviceClass) {
		Assert.isTrue(serviceClass.isInterface());
		Assert.isAssignable(Service.class, serviceClass);
		
		resolveProviderId(serviceClass);
		resolveProviderOperations(serviceClass);
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return endInvoke(method, beginInvoke(method, args, null, false, true, null));
	}
	
	private AsyncResult beginInvoke(Method method, Object[] args, Map<String, String> ctx, boolean explicitCtx, boolean synchronous, CallbackBase callback) {
		
		String opertationName = OPERATION_MAPPINGS.get(method);
		OutgoingAsync result = getOutgoingAsync(opertationName, callback);
		try {
			result.prepare(opertationName, OperationMode.Normal, ctx, explicitCtx, synchronous);
			BasicStream os = result.startWriteParams(FormatType.DefaultFormat);
			Arrays.asList(args).forEach(arg -> {
				ParameterUtils.writeParameter(os, arg.getClass(), arg);
			});
			
			result.endWriteParams();
			result.invoke();
		} catch (Exception e) {
			result.abort(e);
		}
		return result;
	}
	
	private Object endInvoke(Method method, AsyncResult asyncResult) {
		
		String opertationName = OPERATION_MAPPINGS.get(method);
		if (method.getReturnType().equals(void.class)) {
			__end(asyncResult, opertationName);
			return null;
		}
		
		OutgoingAsync result = OutgoingAsync.check(asyncResult, this, opertationName);
		try {
			if (!result.__wait()) {
				try {
					result.throwUserException();
				} catch (UserException e) {
					throw new UnknownUserException(e.ice_name(), e);
				}
			}
			
			BasicStream bs = result.startReadParams();
			Object value = ParameterUtils.readParameter(bs, method.getReturnType());
			result.endReadParams();
			return value;
		} finally {
			if (result != null) {
				result.cacheMessageBuffers();
			}
		}
	}

	private void resolveProviderId(Class<T> serviceClass) {
		IDS[1] = serviceClass.getName().replace(".", "::");
	}
	
	private void resolveProviderOperations(Class<T> serviceClass) {
		Arrays.asList(serviceClass.getMethods()).forEach((method) -> {
			String opertionName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i ++) {
				opertionName = String.format("%s:%s", opertionName, parameterTypes[i].getName());
			}
			OPERATION_MAPPINGS.put(method, "opertionName");
		});
	}
}
