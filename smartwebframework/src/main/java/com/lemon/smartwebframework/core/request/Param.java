package com.lemon.smartwebframework.core.request;

import java.util.Map;

/**
 * 请求参数实体类
 * @author yanan
 *
 */
public class Param {
	private Map<String,Object> parametermap;
	
	public Param(Map<String,Object> parametermap) {
		this.parametermap = parametermap;
	}

	public Map<String, Object> getParametermap() {
		return parametermap;
	}
	
}
