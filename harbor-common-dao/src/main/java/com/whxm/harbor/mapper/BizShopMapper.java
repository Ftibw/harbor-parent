package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizShop;

public interface BizShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(BizShop record);

    int insertSelective(BizShop record);

    BizShop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(BizShop record);

    int updateByPrimaryKeyWithBLOBs(BizShop record);

    int updateByPrimaryKey(BizShop record);
}