package com.wpy.blog.filter;

import com.wpy.blog.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 判断用户session状态
 */
public class SessionFilter implements Filter {

    //存放系统没有Session，但是需要访问的url，像这样的连接需要放行
    List<String> list = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        list.add("/index.jsp");
        list.add("/image.jsp");
        list.add("/login.jsp");
        list.add("/system/elecMenuAction_menuHome.do");
        list.add("/error.jsp");
        list.add("/blogController/index.do");
        list.add("/blogController/article.do");
        list.add("/userController/login.do");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();

        //如果当前访问的链接是定义list中存放的链接，此时需要放行
        String path = request.getServletPath();
        if(list.contains(path)){
            filterChain.doFilter(request, response);
            return;
        }

        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;

        }

        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;


    }

    @Override
    public void destroy() {

    }
}
