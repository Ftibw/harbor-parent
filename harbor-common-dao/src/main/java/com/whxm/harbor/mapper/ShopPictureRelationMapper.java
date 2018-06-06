package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.ShopPictureRelation;

public interface ShopPictureRelationMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(ShopPictureRelation record);

    int insertSelective(ShopPictureRelation record);

    ShopPictureRelation selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(ShopPictureRelation record);

    int updateByPrimaryKey(ShopPictureRelation record);
}