package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizActivityMaterial;

import java.util.List;

public interface BizActivityMaterialMapper {
    int deleteByPrimaryKey(Integer activityMaterialId);

    int insert(BizActivityMaterial record);

    int insertSelective(BizActivityMaterial record);

    BizActivityMaterial selectByPrimaryKey(Integer activityMaterialId);

    BizActivityMaterial selectMaterialWithActivityType(Integer activityMaterialId);

    int updateByPrimaryKeySelective(BizActivityMaterial record);

    int updateByPrimaryKey(BizActivityMaterial record);

    List<BizActivityMaterial> getBizActivityMaterialList(BizActivityMaterial condition);
}