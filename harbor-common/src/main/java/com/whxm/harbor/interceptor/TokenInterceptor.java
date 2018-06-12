package com.whxm.harbor.interceptor;

import com.whxm.harbor.bean.User;
import com.whxm.harbor.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.whxm.harbor.utils.TokenUtils.order;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getParameter("token");

        String salt = null;

        if (token != null) {
            //设置String序列化器
            StringRedisSerializer serializer = new StringRedisSerializer();

            redisTemplate.setKeySerializer(serializer);

            redisTemplate.setValueSerializer(serializer);

            //从redis获取user信息
            salt = (String) redisTemplate.boundValueOps(order(token, TokenUtils.FACTOR)).get();
        }

        if (null == token || null == salt || !salt.equals(TokenUtils.salt(token, TokenUtils.FACTOR))) {
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