package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizActivity;

import java.util.List;

public interface BizActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(BizActivity record);

    int insertSelective(BizActivity record);

    BizActivity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(BizActivity record);

    int updateByPrimaryKey(BizActivity record);

    List<BizActivity> getBizActivityList(BizActivity condition);
}