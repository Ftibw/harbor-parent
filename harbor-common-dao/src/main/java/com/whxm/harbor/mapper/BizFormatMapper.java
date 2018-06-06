package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizFormat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BizFormatMapper {
    int deleteByPrimaryKey(String bizFormatId);

    int insert(BizFormat record);

    int insertSelective(BizFormat record);

    BizFormat selectByPrimaryKey(String bizFormatId);

    int updateByPrimaryKeySelective(BizFormat record);

    int updateByPrimaryKey(BizFormat record);

    List<BizFormat> getBizFormatList();

    /**
     * 根据业态编号前缀查询业态数据
     * @param keyword 业态编号前缀
     * @return  List
     */
    List<BizFormat> getByNumPrefix(String keyword);
}