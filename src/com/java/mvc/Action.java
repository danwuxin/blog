/**
*ʱ�䣺2014-7-14����9:07:28
*
*���ߣ��Ź���
*���ܣ�TODO
*/
package com.java.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
   public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
