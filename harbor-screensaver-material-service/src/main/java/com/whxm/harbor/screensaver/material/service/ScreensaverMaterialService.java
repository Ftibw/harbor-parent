package com.whxm.harbor.screensaver.material.service;


import com.whxm.harbor.bean.BizScreensaverMaterial;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;

public interface ScreensaverMaterialService {
    /**
     * 根据屏保素材ID获取屏保素材数据
     *
     * @param screensaverMaterialId 屏保素材ID
     * @return 屏保素材数据
     */
    BizScreensaverMaterial getBizScreensaverMaterial(Integer screensaverMaterialId);

    /**
     * 获取屏保素材列表
     *
     * @return list
     * @param pageQO
     */
    PageVO<BizScreensaverMaterial> getBizScreensaverMaterialList(PageQO<BizScreensaverMaterial> pageQO);

    /**
     * 根据ID删除屏保素材
     *
     * @param screensaverMaterialId 屏保素材ID
     * @return ret
     */
    Result deleteBizScreensaverMaterial(Integer screensaverMaterialId);

    /**
     * 修改屏保素材数据
     *
     * @param screensaverMaterial 屏保素材数据新值
     * @return ret
     */
    Result updateBizScreensaverMaterial(BizScreensaverMaterial screensaverMaterial);

    /**
     * 新增屏保素材数据
     * @param screensaverMaterial  新屏保素材数据
     * @return  添加操作结果
     */
    Result addBizScreensaverMaterial(BizScreensaverMaterial screensaverMaterial);
}
