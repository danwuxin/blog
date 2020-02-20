/**
 *时间：2014-7-15下午6:01:01
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.action;

import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LogoutAction implements Action {

	
	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// 清除Cookie中的数据
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if (cookie.getName().equals("userid")) {
							cookie.setValue("");
							cookie.setMaxAge(0);
							response.addCookie(cookie);
						}
					}

				}
				request.getSession().removeAttribute("user");
				request.getSession().invalidate();
				return new ActionForward("/index.jsp",false);
			}
}
