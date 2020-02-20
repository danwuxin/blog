package com.java.mvc;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        // 实现映射
        /**
         * Servlet初始化的时候实现集合关系的映射
         *
         */
		/*ActionMapping.addMap("add", new AddAction());
		ActionMapping.addMap("delete", new DeleteAction());*/
        //在web.xml文件中配置
        String iPath = getInitParameter("configPath"); //内部路径，从/WEB-INF开始
        String sysPath = getServletContext().getRealPath(iPath);//转化成系统路径
        System.out.println(sysPath);
        ActionMapping.initConfig(sysPath);//调用ActionMapping中的initConfig(sysPath)
        /**
         * 功能  Servlet初始化的时候实现集合关系的映射
         * 1从配置文件中获取请求的路径
         * 2转化成系统路径
         * 3调用ActionMapping中的initConfig(sysPath)
         *
         * */

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doProcess(req, resp);
    }

    protected void doProcess(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestPath = req.getServletPath();

        if (requestPath.startsWith("/")) {
            requestPath = requestPath.substring(1);
        }
        if (requestPath.endsWith(".do")) {
            requestPath = requestPath.substring(0, requestPath.length() - 3); // 去掉.do
        }
        // 3根据请求路径的不同，获取不同的JavaBean
        System.out.println(requestPath);
        ActionConfig actionConfig = ActionMapping.getAction(requestPath);
        if (actionConfig == null) {
            resp.sendError(404, "你所请求的Action不存在！");
            return;
        }

        // 调用目标方法
//		Action actionBean = (Action) action;
        Object actionBean = actionConfig.getActionBean();
        try {
//			ActionForward af = actionBean.exectue(req, resp);

            Method m = actionConfig.getMethodByName();
            ActionForward af = (ActionForward) m.invoke(actionBean, req, resp);
            // 4、根据调用的结果，跳转到目标界面
            if (af == null)// 说明没有返回结果
            {
                return;
            }

            if (af.isForward) {// 请求转发
                req.getRequestDispatcher(af.targetUrl).forward(req, resp);
                return;
            } else // 重定向
            {
                resp.sendRedirect(req.getContextPath() + af.targetUrl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doProcess(req, resp);
    }

}
