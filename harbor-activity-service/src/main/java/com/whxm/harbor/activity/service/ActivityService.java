package com.whxm.harbor.activity.service;

import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;

/**
 * 活动服务
 */
public interface ActivityService {
    /**
     * 根据活动ID获取活动数据
     *
     * @param bizActivityId 活动ID
     * @return 活动数据
     */
    BizActivity getBizActivity(Integer bizActivityId);

    /**
     * 获取活动列表
     *
     * @return list
     * @param pageQO
     */
    PageVO<BizActivity> getBizActivityList(PageQO<BizActivity> pageQO);

    /**
     * 根据ID删除活动
     *
     * @param bizActivityId 活动ID
     * @return ret
     */
    Result deleteBizActivity(Integer bizActivityId);

    /**
     * 修改活动数据
     *
     * @param bizActivity 活动数据新值
     * @return ret
     */
    Result updateBizActivity(BizActivity bizActivity);

    /**
     * 新增活动数据
     * @param bizActivity  新活动数据
     * @return  添加操作结果
     */
    Result addBizActivity(BizActivity bizActivity);
}
