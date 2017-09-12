package com.elwan.todo.common;

import java.util.List;

import com.elwan.todo.exception.APIException;

public abstract class AppUtil {
	
	public static void throwExceptionIfNull(Object o, String msg) throws APIException {
		if(o == null)
			throw new APIException(msg);
	}

	public static void throwExceptionIfNullOrEmpty(List list, String msg) throws APIException {
		if(list == null || list.size() == 0)
			throw new APIException(msg);
	}

}
