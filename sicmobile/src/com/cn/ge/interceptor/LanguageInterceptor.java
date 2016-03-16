package com.cn.ge.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 多语言拦截器
 * @name LanguageInterceptor.java
 * @author Frank
 * @time 2016-1-4下午10:23:39
 * @version 1.0
 */
public class LanguageInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4715147911939515984L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		return arg0.invoke();
	}
}
