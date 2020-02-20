package com.java.mvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionConfig {
	String path;
	Object actionBean;
	String methodName = "execute";

	public ActionConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionConfig(Object actionBean, String methodName) {
		super();
		this.actionBean = actionBean;
		this.methodName = methodName;
	}

	public ActionConfig(Object actionBean) {
		super();
		this.actionBean = actionBean;
		this.methodName = "execute";
	}

	public Object getActionBean() {
		return actionBean;
	}

	public void setActionBean(Object actionBean) {
		this.actionBean = actionBean;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Method getMethodByName() {
		Class[] paramTypes = new Class[] { HttpServletRequest.class,
				HttpServletResponse.class };
		Class actionClass = actionBean.getClass();

		// Action actionBean=(Action) action;

		// ActionForward af=actionBean.execute(req, resp);
		try {
			Method m = actionClass.getMethod(methodName, paramTypes);
			return m;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
