package com.java.mvc;

public class ActionForward {
	String targetUrl;
	boolean isForward;
	

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public ActionForward(String targetUrl, boolean isForward) {
		super();
		this.targetUrl = targetUrl;
		this.isForward = isForward;
	}

	public ActionForward(String targetUrl) {
		super();
		this.targetUrl = targetUrl;
		this.isForward = true;
	}

}
