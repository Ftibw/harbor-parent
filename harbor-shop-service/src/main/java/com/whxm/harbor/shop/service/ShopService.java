package com.whxm.harbor.shop.service;

import com.whxm.harbor.bean.BizShop;
import com.whxm.harbor.common.bean.Result;

import java.util.List;
import java.util.Map;

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
     * 根据ID停用/启用商铺
     *
     * @param bizShopId 商铺ID
     * @return ret
     */
    Result triggerBizShop(String bizShopId);

    /**
     * 修改商铺数据
     *
     * @param bizShop 商铺数据新值
     * @return ret
     */
    Result updateBizShop(BizShop bizShop);

    /**
     * 新增商铺数据
     *
     * @param bizShop 商铺数据新值
     * @return ret
     */
    Result addBizShop(BizShop bizShop,List<Map<String,Object>> pictureList);
}
