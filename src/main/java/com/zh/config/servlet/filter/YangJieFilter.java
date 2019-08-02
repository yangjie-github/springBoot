package com.zh.config.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yangjie
 * 2019/3/27 21:48
 */
public class YangJieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("yangjie filter process");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
