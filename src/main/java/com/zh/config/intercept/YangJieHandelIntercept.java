package com.zh.config.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangjie
 * 2019/3/26 21:59
 * 登录状态的检查拦截器
 */
public class YangJieHandelIntercept implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Object loginName = httpServletRequest.getSession().getAttribute("loginUser");

        if (loginName == null) {
            //回到登录器，获取转发器
            httpServletRequest.setAttribute("msg", "请先登录");
            httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest, httpServletResponse);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
