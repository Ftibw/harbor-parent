package com.whxm.harbor.user.controller;

import com.whxm.harbor.common.bean.User;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
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
            //密码加盐
            user.setUserPassword(user.getUserPassword());

            ret = userService.addUser(user);

        } catch (Exception e) {
            logger.error("用户 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户 添加报错", null);
        }
        return ret;
    }


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public Result userLogin(User user) {

        Result ret = null;

        User info = userService.getUserLoginInfo(user);

        if (null != info) {

            String token = UUID.randomUUID().toString();
            //设置序列化器
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
            // key以String方式存储
            // value以json字符串形式存储
            redisTemplate.boundValueOps(token).set(info);

            ret = new Result(token);
        } else {

            ret = new Result(null);
        }

        return ret;
    }

}
