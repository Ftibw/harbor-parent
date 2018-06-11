package com.whxm.harbor.user.service;

import com.whxm.harbor.bean.User;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;

/**
 * 用户管理
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param user 用户凭证信息
     * @return 用户数据
     */
    User getUserLoginInfo(User user);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户数据
     */
    User getUser(String userId);

    /**
     * 获取用户列表
     *
     * @param pageQO 查询条件
     * @return 查询结果
     */
    PageVO<User> getUserList(PageQO<User> pageQO);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 操作结果
     */
    Result deleteUser(String userId);

    /**
     * 添加用户
     *
     * @param user 新用户数据
     * @return 操作结果
     */
    Result addUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户的新数据
     * @return 操作结果
     */
    Result updateUser(User user);
}
