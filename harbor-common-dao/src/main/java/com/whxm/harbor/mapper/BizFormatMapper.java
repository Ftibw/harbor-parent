package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizFormat;

import java.util.List;

public interface BizFormatMapper {
    int deleteByPrimaryKey(Integer bizFormatId);

    int insert(BizFormat record);

    int insertSelective(BizFormat record);

    BizFormat selectByPrimaryKey(Integer bizFormatId);

    int updateByPrimaryKeySelective(BizFormat record);

    int updateByPrimaryKey(BizFormat record);

    List<BizFormat> getBizFormatList(BizFormat condition);

    BizFormat selectIdByNumber(String bizFormatNumber);
}