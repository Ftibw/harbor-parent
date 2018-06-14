package com.whxm.harbor.activity.material.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.activity.material.service.ActivityMaterialService;
import com.whxm.harbor.bean.BizActivityMaterial;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.conf.UrlConfig;
import com.whxm.harbor.mapper.BizActivityMaterialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

            if (null == activityMaterial)
                logger.info("ID为{}的活动素材不存在", bizActivityMaterialId);

        } catch (Exception e) {

            logger.error("活动素材ID为{}的数据 获取报错", bizActivityMaterialId);

            throw new RuntimeException();
        }

        return activityMaterial;
    }

    @Override
    public PageVO<BizActivityMaterial> getBizActivityMaterialList(PageQO<BizActivityMaterial> pageQO) {

        PageVO<BizActivityMaterial> pageVO = null;
        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            List<BizActivityMaterial> list = bizActivityMaterialMapper.getBizActivityMaterialList(pageQO.getCondition());

            list.forEach(item -> item.setActivityMaterialImgPath(
                    urlConfig.getUrlPrefix()
                            + item.getActivityMaterialImgPath()
            ));

            pageVO.setList(list);

            pageVO.setTotal(page.getTotal());

        } catch (Exception e) {
            logger.error("活动素材列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
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
            bizActivityMaterial.setActivityId(null);

            int affectRow = bizActivityMaterialMapper.insert(bizActivityMaterial);

            ret = new Result("活动素材数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("活动素材数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Autowired
    private UrlConfig urlConfig;

    @Override
    public List<BizActivityMaterial> getMaterialListByActivityId(Integer activityId) {

        List<BizActivityMaterial> list = null;

        try {
            BizActivityMaterial activityMaterial = new BizActivityMaterial();

            activityMaterial.setActivityId(activityId);

            list = bizActivityMaterialMapper.getBizActivityMaterialList(activityMaterial);

            list.forEach(item -> item.setActivityMaterialImgPath(
                    urlConfig.getUrlPrefix()
                            + item.getActivityMaterialImgPath()
            ));

        } catch (Exception e) {

            logger.error("活动素材数据列表 获取报错", e);

            throw new RuntimeException();
        }

        return list;
    }
}
