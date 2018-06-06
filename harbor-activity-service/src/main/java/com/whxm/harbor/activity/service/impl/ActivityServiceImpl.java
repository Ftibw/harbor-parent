package com.whxm.harbor.activity.service.impl;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private BizActivityMapper bizActivityMapper;

    @Override
    public BizActivity getBizActivity(Integer bizActivityId) {

        Assert.notNull(bizActivityId, "活动ID不能为空");

        BizActivity bizActivity = bizActivityMapper.selectByPrimaryKey(bizActivityId);

        return bizActivity;
    }

    @Override
    public List<BizActivity> getBizActivityList() {

        List<BizActivity> activityList = bizActivityMapper.getBizActivityList();

        return activityList;
    }

    @Override
    public Result deleteBizActivity(Integer bizActivityId) {

        Assert.notNull(bizActivityId, "活动ID不能为空");

        BizActivity activity = new BizActivity();

        activity.setActivityId(bizActivityId);

        activity.setIsDeleted(0);

        updateBizActivity(activity);

        return null;
    }

    @Override
    public Result updateBizActivity(BizActivity bizActivity) {

        Assert.notNull(bizActivity, "活动不能为空");

        Assert.notNull(bizActivity.getActivityId(), "活动ID不能为空");

        int affectRow = bizActivityMapper.updateByPrimaryKeySelective(bizActivity);

        return null;
    }

    @Override
    public Result addBizActivity(BizActivity bizActivity) {

        Assert.notNull(bizActivity, "活动不能为空");

        int affectRow = bizActivityMapper.insert(bizActivity);

        return null;
    }
}
