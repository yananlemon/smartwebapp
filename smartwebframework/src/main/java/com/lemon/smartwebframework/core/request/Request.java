package com.lemon.smartwebframework.core.request;

public class Request {
	private String requestMethod;	// 请求方法
	private String requestPath;		// 请求路径
	
	/**
	 * 
	 * @param requestMethod
	 * @param requestPath
	 */
	public Request(String requestMethod, String requestPath) {
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestMethod == null) ? 0 : requestMethod.hashCode());
		result = prime * result + ((requestPath == null) ? 0 : requestPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (requestMethod == null) {
			if (other.requestMethod != null)
				return false;
		} else if (!requestMethod.equals(other.requestMethod))
			return false;
		if (requestPath == null) {
			if (other.requestPath != null)
				return false;
		} else if (!requestPath.equals(other.requestPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [requestMethod=" + requestMethod + ", requestPath=" + requestPath + "]";
	}
	
	
	
}
