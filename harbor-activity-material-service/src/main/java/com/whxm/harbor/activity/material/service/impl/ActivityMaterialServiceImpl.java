package com.whxm.harbor.activity.material.service.impl;

import com.github.pagehelper.PageHelper;
import com.whxm.harbor.activity.material.service.ActivityMaterialService;
import com.whxm.harbor.bean.BizActivityMaterial;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizActivityMaterialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ActivityMaterialServiceImpl implements ActivityMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityMaterialServiceImpl.class);

    @Resource
    private BizActivityMaterialMapper bizActivityMaterialMapper;

    @Override
    public BizActivityMaterial getBizActivityMaterial(Integer bizActivityMaterialId) {

        BizActivityMaterial activityMaterial = null;

        try {
            activityMaterial = bizActivityMaterialMapper.selectMaterialWithActivityType(bizActivityMaterialId);

            if (null == activityMaterial) {
                logger.error("错误活动素材ID", activityMaterial);
            }
        } catch (Exception e) {

            logger.error("活动素材ID为{}的数据 获取报错", bizActivityMaterialId);

            throw new RuntimeException();
        }

        return activityMaterial;
    }

    @Override
    public List<BizActivityMaterial> getBizActivityMaterialList() {

        List<BizActivityMaterial> activityMaterialList = null;
        try {
            PageHelper.startPage(0, 1);

            activityMaterialList = bizActivityMaterialMapper.getBizActivityMaterialList();
        } catch (Exception e) {
            logger.error("活动素材列表 获取报错", e);

            throw new RuntimeException();
        }

        return activityMaterialList;
    }

    @Override
    public Result deleteBizActivityMaterial(Integer bizActivityMaterialId) {
        Result ret = null;

        try {

            bizActivityMaterialMapper.deleteByPrimaryKey(bizActivityMaterialId);

            logger.info("ID为{}的活动素材 删除成功", bizActivityMaterialId);

            ret = new Result("删除成功");

        } catch (Exception e) {

            logger.error("活动素材ID为{}的数据 删除错误", bizActivityMaterialId);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizActivityMaterial(BizActivityMaterial bizActivityMaterial) {
        Result ret = null;

        try {

            int affectRow = bizActivityMaterialMapper.updateByPrimaryKeySelective(bizActivityMaterial);

            ret = new Result("活动素材数据修改了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("活动素材数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizActivityMaterial(BizActivityMaterial bizActivityMaterial) {
        Result ret = null;

        try {
            int affectRow = bizActivityMaterialMapper.insert(bizActivityMaterial);

            ret = new Result("活动素材数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("活动素材数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
