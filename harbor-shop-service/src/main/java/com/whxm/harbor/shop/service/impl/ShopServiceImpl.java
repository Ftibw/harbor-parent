package com.whxm.harbor.shop.service.impl;

import com.whxm.harbor.bean.BizShop;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.shop.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Override
    public BizShop getBizShop(String bizShopId) {
        return null;
    }

    @Override
    public List<BizShop> getBizShopList() {
        return null;
    }

    @Override
    public Result deleteBizShop(String bizShopId) {
        return null;
    }

    @Override
    public Result updateBizShop(BizShop bizShop) {
        return null;
    }
}
