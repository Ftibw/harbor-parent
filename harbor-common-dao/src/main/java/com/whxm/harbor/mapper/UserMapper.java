package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserLoginInfo(User user);

    List<User> getUserList(User condition);
}