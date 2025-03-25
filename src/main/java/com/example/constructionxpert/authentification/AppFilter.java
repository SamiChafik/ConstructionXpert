package com.example.constructionxpert.authentification;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AppFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String action = req.getServletPath();
        if(  "/".equals(action) || "/login".equals(action) || "/Login.jsp".equals(action) || "/accueil.jsp".equals(action) || action.startsWith("/resources/") ){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{

            Object isLoggedObj = req.getSession().getAttribute("isLoggedIn");
            if (isLoggedObj != null && (Boolean) isLoggedObj) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}