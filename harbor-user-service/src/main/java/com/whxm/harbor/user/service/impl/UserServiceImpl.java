package com.whxm.harbor.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.User;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.mapper.UserMapper;
import com.whxm.harbor.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {

        User po = null;

        try {
            po = userMapper.selectUserLoginInfo(user);

        } catch (Exception e) {

            logger.error("用户信息 查询报错", e);
        }

        return po;
    }

    @Override
    public User getUser(String userId) {
        User user = null;

        try {
            user = userMapper.selectByPrimaryKey(userId);

        } catch (Exception e) {

            logger.error("ID为{}的用户数据 获取报错", userId, e);

            throw new RuntimeException();
        }

        return user;
    }

    @Override
    public PageVO<User> getUserList(PageQO<User> pageQO) {

        PageVO<User> pageVO = null;

        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(userMapper.getUserList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());

        } catch (Exception e) {

            logger.error("用户列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public Result deleteUser(String userId) {

        Result ret = null;

        try {
            userMapper.deleteByPrimaryKey(userId);

            ret = new Result("ID为的" + userId + "用户成功删除");

        } catch (Exception e) {

            logger.error("用户数据 删除报错", e);

            throw new RuntimeException();
        }


        return ret;
    }

    @Override
    public Result addUser(User user) {

        Result ret = null;

        try {
            user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));

            int affectRow = userMapper.insert(user);

            ret = new Result("用户数据 成功添加" + affectRow + "行");

        } catch (Exception e) {

            logger.error("用户数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateUser(User user) {

        Result ret = null;

        try {
            int affectRow = userMapper.updateByPrimaryKeySelective(user);

            ret = new Result("用户数据 修改成功 影响了" + affectRow + "行");

        } catch (Exception e) {

            logger.error("用户数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

}
