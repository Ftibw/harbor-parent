package com.whxm.harbor.activity.material.service;

import com.whxm.harbor.bean.BizActivityMaterial;
import com.whxm.harbor.common.bean.Result;

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
    List<BizActivityMaterial> getBizActivityMaterialList();

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
     * @param bizActivityMaterial  新活动材料数据
     * @return  添加操作结果
     */
    Result addBizActivityMaterial(BizActivityMaterial bizActivityMaterial);
}
