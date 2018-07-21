package com.lemon.smartwebframework.core.request;

import java.util.HashMap;
import java.util.Map;

public class View {
	private String path;
	private Map<String,Object> model;
	public View(String path, Map<String, Object> model) {
		super();
		this.path = path;
		this.model = model;
	}
	public View(String path) {
		super();
		this.path = path;
		this.model = new HashMap<String,Object>();
	}
	public View addModel(String key,Object value) {
		this.model.put(key, value);
		return this;
	}
	public String getPath() {
		return path;
	}
	public Map<String, Object> getModel() {
		return model;
	}
}
