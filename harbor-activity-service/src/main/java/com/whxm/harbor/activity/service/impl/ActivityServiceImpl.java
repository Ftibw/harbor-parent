package com.whxm.harbor.activity.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.mapper.BizActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Resource
    private BizActivityMapper bizActivityMapper;

    @Override
    public BizActivity getBizActivity(Integer bizActivityId) {

        BizActivity bizActivity = null;

        try {
            bizActivity = bizActivityMapper.selectByPrimaryKey(bizActivityId);

            if (null == bizActivity) {
                logger.info("错误活动ID", bizActivity);
            }
        } catch (Exception e) {

            logger.error("活动数据 获取报错", e);

            throw new RuntimeException();
        }

        return bizActivity;
    }

    @Override
    public PageVO<BizActivity> getBizActivityList(PageQO<BizActivity> pageQO) {
        PageVO<BizActivity> pageVO = null;

        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(bizActivityMapper.getBizActivityList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());

        } catch (Exception e) {
            logger.error("活动列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public Result deleteBizActivity(Integer bizActivityId) {
        Result ret = null;

        Integer ACTIVITY_DELETED = 0;

        try {
            BizActivity activity = new BizActivity();

            activity.setActivityId(bizActivityId);

            activity.setIsDeleted(ACTIVITY_DELETED);

            updateBizActivity(activity);

            logger.info("ID为{}的活动 删除成功", bizActivityId);

            ret = new Result("删除成功");

        } catch (Exception e) {

            logger.error("活动数据 删除错误", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizActivity(BizActivity bizActivity) {
        Result ret = null;

        try {

            int affectRow = bizActivityMapper.updateByPrimaryKeySelective(bizActivity);

            ret = new Result("活动数据修改了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("活动数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizActivity(BizActivity bizActivity) {
        Result ret = null;

        try {
            int affectRow = bizActivityMapper.insert(bizActivity);

            ret = new Result("活动数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("活动数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
