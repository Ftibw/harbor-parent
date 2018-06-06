package com.whxm.harbor.shop.service;

import com.whxm.harbor.bean.BizShop;
import com.whxm.harbor.common.bean.Result;

import java.util.List;

/**
 * 商铺服务
 */
public interface ShopService {
    /**
     * 根据商铺ID获取商铺数据
     *
     * @param bizShopId 商铺ID
     * @return 商铺数据
     */
    BizShop getBizShop(String bizShopId);

    /**
     * 获取商铺列表
     *
     * @return list
     */
    List<BizShop> getBizShopList();

    /**
     * 根据ID删除商铺
     *
     * @param bizShopId 商铺ID
     * @return ret
     */
    Result deleteBizShop(String bizShopId);

    /**
     * 修改商铺数据
     *
     * @param bizShop 商铺数据新值
     * @return ret
     */
    Result updateBizShop(BizShop bizShop);
}
