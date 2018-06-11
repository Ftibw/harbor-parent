package com.whxm.harbor.user.controller;

import com.whxm.harbor.bean.User;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "API - BusinessUserController", description = "用户 Controller")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping("/Users")
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
    @GetMapping("/User/{ID}")
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
    @PutMapping("/User")
    public Result updateUser(@RequestBody User User) {
        Result ret = null;
        try {
            ret = userService.updateUser(User);
        } catch (Exception e) {

            logger.error("用户数据 修改报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户数据 修改报错", null);
        }

        return ret;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/User/{ID}")
    public Result delUser(
            @ApiParam(name = "ID", value = "用户的ID", required = true)
            @PathVariable("ID") String UserId
    ) {
        Result ret = null;
        try {
            ret = userService.deleteUser(UserId);
        } catch (Exception e) {

            logger.error("用户数据 删除报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户数据 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加用户")
    @PostMapping("/User")
    public Result addUser(@RequestBody User User) {
        Result ret = null;
        try {
            ret = userService.addUser(User);

        } catch (Exception e) {
            logger.error("用户 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户 添加报错", null);
        }
        return ret;
    }

}
