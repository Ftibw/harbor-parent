package com.whxm.harbor.controller;

import com.whxm.harbor.bean.User;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.utils.MD5Util;
import com.whxm.harbor.user.service.UserService;
import com.whxm.harbor.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.whxm.harbor.utils.TokenUtils.chaos;
import static com.whxm.harbor.utils.TokenUtils.order;

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

    @ApiOperation("登陆接口,token有效时间为2小时")
    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {

        Result ret = null;

        if (null == user.getUserLoginname()) {
            return new Result(HttpStatus.UNAUTHORIZED.value(), "用户名不能为空", null);
        }

        if (null == user.getUserPassword()) {
            return new Result(HttpStatus.UNAUTHORIZED.value(), "用户密码不能为空", null);
        }

        User info = userService.login(user);

        if (null != info) {

            if (info.getUserPassword().equals(MD5Util.MD5(user.getUserPassword()))) {

                String userId = info.getUserId();

                String salt = UUID.randomUUID().toString().replace("-", "");

                //设置String序列化器
                StringRedisSerializer serializer = new StringRedisSerializer();

                redisTemplate.setKeySerializer(serializer);

                redisTemplate.setValueSerializer(serializer);

                //以userId为key避免登陆状态冗余,以盐为value始终维持最新的登陆状态
                redisTemplate.boundValueOps(userId).set(salt, 2, TimeUnit.HOURS);

                //将userId和盐搅拌生成token
                ret = new Result(chaos(userId, salt));

            } else

                ret = new Result(HttpStatus.UNAUTHORIZED.value(), "密码错误", null);
        } else {

            ret = new Result(HttpStatus.UNAUTHORIZED.value(), "该用户不存在", null);
        }

        return ret;
    }

    @ApiOperation("刷新token")
    @GetMapping("/token")
    public Result token(
            @ApiParam(name = "token", value = "token值", required = true) String token) {
        Result ret = null;

        if (token != null) {
            //设置String序列化器
            StringRedisSerializer serializer = new StringRedisSerializer();

            redisTemplate.setKeySerializer(serializer);

            redisTemplate.setValueSerializer(serializer);

            String userId = order(token);

            //从redis获取盐信息
            String salt = (String) redisTemplate.boundValueOps(userId).get();

            if (null != salt && salt.equals(TokenUtils.salt(token))) {

                String newSalt = UUID.randomUUID().toString().replace("-", "");

                redisTemplate.boundValueOps(userId).set(newSalt, 2, TimeUnit.HOURS);

                ret = new Result(chaos(userId, newSalt));

            } else
                ret = new Result(HttpStatus.UNAUTHORIZED.value(), "token无效", null);
        } else
            ret = new Result(HttpStatus.UNAUTHORIZED.value(), "未登陆", null);

        return ret;
    }

    @ApiOperation("用户登出")
    @GetMapping("/logout")
    public Result logout(@ApiParam(name = "token", value = "token值", required = true) String token) {
        Result ret = null;

        try {

            redisTemplate.delete(order(token));

            ret = new Result("登出成功");

        } catch (Exception e) {

            logger.error("登出报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "登出报错", null);
        }

        return ret;
    }

}
