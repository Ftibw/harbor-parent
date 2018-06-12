package com.whxm.harbor.controller;

import com.whxm.harbor.bean.User;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.utils.MD5Util;
import com.whxm.harbor.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Api(value = "API - BusinessUserController", description = "用户 Controller")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping("/users")
    public Result getUsers(PageQO<User> pageQO, User condition) {

        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<User> pageVO = userService.getUserList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("用户列表 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户列表 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("获取用户")
    @GetMapping("/user/{ID}")
    public Result getUser(
            @ApiParam(name = "ID", value = "用户的ID", required = true)
            @PathVariable("ID") String userId
    ) {
        Result ret = null;
        User user = null;
        try {
            user = userService.getUser(userId);

            ret = new Result(user);

        } catch (Exception e) {
            logger.error("用户数据 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("修改用户")
    @PutMapping("/user")
    public Result updateUser(@RequestBody User user) {
        Result ret = null;
        try {
            ret = userService.updateUser(user);
        } catch (Exception e) {

            logger.error("用户数据 修改报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户数据 修改报错", null);
        }

        return ret;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/user/{ID}")
    public Result delUser(
            @ApiParam(name = "ID", value = "用户的ID", required = true)
            @PathVariable("ID") String userId
    ) {
        Result ret = null;
        try {
            ret = userService.deleteUser(userId);
        } catch (Exception e) {

            logger.error("用户数据 删除报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户数据 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加用户")
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        Result ret = null;
        try {
            //32位加密
            user.setUserPassword(MD5Util.MD5(user.getUserPassword()));

            ret = userService.addUser(user);

        } catch (Exception e) {
            logger.error("用户 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户 添加报错", null);
        }
        return ret;
    }


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @ApiOperation("登陆接口,token有效时间为30分钟")
    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {


        Result ret = null;

        User info = userService.login(user);

        if (null != info) {

            if (info.getUserPassword().equals(MD5Util.MD5(user.getUserPassword()))) {

                String token = UUID.randomUUID().toString().replace("-", "");
                //设置序列化器
                redisTemplate.setKeySerializer(new StringRedisSerializer());

                redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                // key以String方式存储
                // value以json字符串形式存储
                redisTemplate.boundValueOps(token).set(info, 30, TimeUnit.MINUTES);

                ret = new Result(token);

            } else

                ret = new Result(HttpStatus.UNAUTHORIZED.value(), "密码错误", null);
        } else {

            ret = new Result(HttpStatus.UNAUTHORIZED.value(), "该用户不存在", null);
        }

        return ret;
    }

    @ApiOperation("刷新token")
    @PostMapping("/token")
    public Result token(
            @ApiParam(name = "token", value = "token值", required = true) String token) {
        Result ret = null;

        if (token != null) {
            //设置序列化器
            redisTemplate.setKeySerializer(new StringRedisSerializer());

            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));

            //从redis获取user信息
            User user = (User) redisTemplate.boundValueOps(token).get();

            if (null != user) {
                String newToken = UUID.randomUUID().toString().replace("-", "");

                redisTemplate.boundValueOps(token).set(user, 30, TimeUnit.MINUTES);

                ret = new Result(newToken);

            } else
                ret = new Result(HttpStatus.UNAUTHORIZED.value(), "token过期了", null);
        } else
            ret = new Result(HttpStatus.UNAUTHORIZED.value(), "未登陆", null);

        return ret;
    }

}
