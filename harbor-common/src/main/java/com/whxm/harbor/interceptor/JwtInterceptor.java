//package com.whxm.harbor.common.interceptor;
//
//import com.whxm.harbor.utils.JwtUtil;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////@Component
//public class JwtInterceptor  extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new ServletException("invalid Authorization header");
//        }
//        //取得token
//        String token = authHeader.substring(7);
//        try {
//            JwtUtil.checkToken(token);
//            return true;
//        } catch (Exception e) {
//            throw new ServletException(e.getMessage());
//        }
//    }
//}