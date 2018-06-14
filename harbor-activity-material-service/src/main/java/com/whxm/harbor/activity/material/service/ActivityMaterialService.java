package com.whxm.harbor.activity.material.service;

import com.whxm.harbor.bean.BizActivityMaterial;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;

import java.util.List;

/**
 * 活动材料服务
 */
public interface ActivityMaterialService {
    /**
     * 根据活动材料ID获取活动材料数据
     *
     * @param bizActivityMaterialId 活动材料ID
     * @return 活动材料数据
     */
    BizActivityMaterial getBizActivityMaterial(Integer bizActivityMaterialId);

    /**
     * 获取活动材料列表
     *
     * @return list
     */
    PageVO<BizActivityMaterial> getBizActivityMaterialList(PageQO<BizActivityMaterial> pageQO);

    /**
     * 根据ID删除活动材料
     *
     * @param bizActivityMaterialId 活动材料ID
     * @return ret
     */
    Result deleteBizActivityMaterial(Integer bizActivityMaterialId);

    /**
     * 修改活动材料数据
     *
     * @param bizActivityMaterial 活动材料数据新值
     * @return ret
     */
    Result updateBizActivityMaterial(BizActivityMaterial bizActivityMaterial);

    /**
     * 新增活动材料数据
     *
     * @param bizActivityMaterial 新活动材料数据
     * @return 添加操作结果
     */
    Result addBizActivityMaterial(BizActivityMaterial bizActivityMaterial);

    /**
     * 根据活动ID获取活动素材列表
     *
     * @param activityId 活动ID
     * @return 活动素材列表
     */
    List<BizActivityMaterial> getMaterialListByActivityId(Integer activityId);
}
