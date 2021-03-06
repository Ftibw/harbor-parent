package com.whxm.harbor.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizShop;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizShopMapper;
import com.whxm.harbor.shop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Resource
    private BizShopMapper bizShopMapper;

    @Override
    public BizShop getBizShop(String bizShopId) {

        BizShop bizShop = null;
        try {
            bizShop = bizShopMapper.selectByPrimaryKey(bizShopId);

        } catch (Exception e) {

            logger.error("ID为{}的商铺,获取报错", bizShopId);

            throw new RuntimeException();
        }

        return bizShop;
    }

    @Override
    public List<BizShop> getBizShopList() {

        PageHelper.startPage(0, 1);

        List<BizShop> shopList = null;
        try {
            shopList = bizShopMapper.getBizShopList();

            logger.info("商铺列表 获取成功");

        } catch (Exception e) {

            logger.error("商铺列表 获取错误", e);

            throw new RuntimeException();
        }

        return shopList;
    }

    @Override
    public Result triggerBizShop(String bizShopId) {
        Result ret = null;

        if (null != bizShopId) {

            try {
                BizShop bizShop = getBizShop(bizShopId);

                bizShop.setIsShopEnabled(bizShop.getIsShopEnabled() ^ 1);

                ret = updateBizShop(bizShop);

            } catch (Exception e) {

                logger.error("ID为{}的商铺 状态(启用/停用)变更报错", bizShopId);

                throw new RuntimeException();
            }
        } else {
            logger.error("商铺ID为空");

            ret = new Result(HttpStatus.NOT_FOUND.value(), "商铺ID为空", null);
        }

        return ret;
    }

    @Override
    public Result updateBizShop(BizShop bizShop) {
        Result ret = null;

        if (null == bizShop
                || "".equals(bizShop.getShopId())
                || null == bizShop.getShopId()) {

            logger.error("商铺数据不存在");
            return new Result(HttpStatus.OK.value(), "商铺数据不存在", null);
        }

        try {
            int affectRow = bizShopMapper.updateByPrimaryKeySelective(bizShop);

            logger.info("ID为{}的商铺 修改成功", bizShop.getShopId());

            ret = new Result("成功修改" + affectRow + "行");

        } catch (Exception e) {

            logger.error("ID为{}的商铺 修改错误", bizShop.getBizFormatId());

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizShop(BizShop bizShop, List<Map<String, Object>> pictureList) {

        Result ret = null;
        Integer SHOP_ENABLED = 1;

        if (null != bizShop) {

            String shopId = UUID.randomUUID().toString().replace("-", "");

            bizShop.setShopId(shopId);
            bizShop.setIsShopEnabled(SHOP_ENABLED);
            bizShop.setAddShopTime(new Date());

            try {
                int affectRow = bizShopMapper.insert(bizShop);

                int affectRow2 = bizShopMapper.insertShopPictures(shopId, pictureList);

                ret = new Result("新增" + affectRow + "行商铺记录,新增" + affectRow2 + "行商铺图片记录");

            } catch (Exception e) {

                logger.error("商铺数据 添加错误");

                throw new RuntimeException();
            }

        } else {
            logger.error("要添加的商铺数据为空");

            ret = new Result("商铺数据为空");
        }

        return ret;
    }

}
