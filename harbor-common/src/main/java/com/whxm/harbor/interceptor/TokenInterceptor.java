package com.whxm.harbor.interceptor;

import com.whxm.harbor.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");
        Object user = null;

        if (token != null) {
            //设置序列化器
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
            //从redis获取user信息
            user = redisTemplate.boundValueOps(token).get();
        }

        if (null == token || null == user) {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("token无效");
            // 401 状态码 没有权限访问
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }
}