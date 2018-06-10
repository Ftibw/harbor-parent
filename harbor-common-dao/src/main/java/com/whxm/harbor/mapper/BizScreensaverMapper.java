package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizScreensaver;

import java.util.List;

public interface BizScreensaverMapper {
    int deleteByPrimaryKey(Integer screensaverId);

    int insert(BizScreensaver record);

    int insertSelective(BizScreensaver record);

    BizScreensaver selectByPrimaryKey(Integer screensaverId);

    int updateByPrimaryKeySelective(BizScreensaver record);

    int updateByPrimaryKey(BizScreensaver record);

    List<BizScreensaver> getBizScreensaverList();
}