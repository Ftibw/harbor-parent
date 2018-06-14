package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizFloor;

import java.util.List;

public interface BizFloorMapper {
    int deleteByPrimaryKey(Integer floorId);

    int insert(BizFloor record);

    int insertSelective(BizFloor record);

    BizFloor selectByPrimaryKey(Integer floorId);

    int updateByPrimaryKeySelective(BizFloor record);

    int updateByPrimaryKey(BizFloor record);

    List<BizFloor> getBizFloorList(BizFloor condition);

    BizFloor selectIdByNumber(String floorNumber);
}