package com.whxm.harbor.activity.service.impl;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
                logger.error("错误活动ID", bizActivity);
            }
        } catch (Exception e) {
            logger.error("活动数据 获取报错", e);
            throw new RuntimeException();
        }

        return bizActivity;
    }

    @Override
    public List<BizActivity> getBizActivityList() {

        List<BizActivity> activityList = bizActivityMapper.getBizActivityList();

        return activityList;
    }

    @Override
    public Result deleteBizActivity(Integer bizActivityId) {

        BizActivity activity = new BizActivity();

        activity.setActivityId(bizActivityId);

        activity.setIsDeleted(0);

        updateBizActivity(activity);

        return null;
    }

    @Override
    public Result updateBizActivity(BizActivity bizActivity) {

        int affectRow = bizActivityMapper.updateByPrimaryKeySelective(bizActivity);

        return null;
    }

    @Override
    public Result addBizActivity(BizActivity bizActivity) {

        int affectRow = bizActivityMapper.insert(bizActivity);

        return null;
    }
}
