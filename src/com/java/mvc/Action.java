package com.java.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
   public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
