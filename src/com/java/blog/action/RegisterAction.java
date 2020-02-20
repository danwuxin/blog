package com.java.blog.action;

import com.java.blog.entity.User;
import com.java.blog.service.UserService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegisterAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        //设置字符集
        request.setCharacterEncoding("utf-8");
        //接受数据  账号  姓名  密码  确认密码  头像
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String imageUrl = request.getParameter("imageUrl");
        //验证姓名数据
        if (null == name || name.trim().length() == 0) {
            request.setAttribute("errorInfo", "真实姓名不能为空。");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return ;
            return new ActionForward("/register.jsp", true);

        }
        //验证账号数据
        if (null == account || account.trim().length() == 0) {
            request.setAttribute("errorInfo", "登录账号不能为空。");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
            return new ActionForward("/register.jsp", true);
        }

        //验证密码数据
        if (null == password || password.trim().length() == 0) {
            request.setAttribute("errorInfo", "登录密码不能为空。");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
            return new ActionForward("/register.jsp", true);
        }
        //验证确认密码数据
        if (null == confirmPassword || confirmPassword.trim().length() == 0) {
            request.setAttribute("errorInfo", "登录密码不能为空。");
//					request.getRequestDispatcher("register.jsp").forward(request,
//						response);
//					return;
            return new ActionForward("/register.jsp", true);
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorInfo", "两次密码输入的不一致。");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
            return new ActionForward("/register.jsp", true);
        }

        //new 一个用户对象  将接收到的数据给new出来的用户
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        user.setImageUrl(imageUrl);
        //调用user  service层   使用service层的业务
        UserService userService = new UserService();
        boolean result = userService.register(user);
        //判断用户是否为空
        if (!result) {
            request.setAttribute("errorInfo", userService.getErrorMessage());
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
            return new ActionForward("/register.jsp", true);
        } else {
//					response.sendRedirect("index.jsp");
            return new ActionForward("/index.jsp", false);
        }
    }

}
