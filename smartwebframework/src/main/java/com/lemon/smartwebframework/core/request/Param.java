package com.lemon.smartwebframework.core.request;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.Part;

/**
 * 请求参数实体类
 * @author yanan
 *
 */
public class Param {
	private Map<String,Object> parametermap;
	
	private Collection<Part> parts;
	
	public Param(Map<String,Object> parametermap,Collection<Part> parts) {
		this.parametermap = parametermap;
		this.parts = parts;
	}

	public Map<String, Object> getParametermap() {
		return parametermap;
	}

	public Collection<Part> getParts() {
		return parts;
	}
}
