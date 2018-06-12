package com.whxm.harbor.screensaver.service;


import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;

public interface ScreensaverService {
    /**
     * 根据屏保ID获取屏保数据
     *
     * @param screensaverId 屏保ID
     * @return 屏保数据
     */
    BizScreensaver getBizScreensaver(Integer screensaverId);

    /**
     * 获取屏保列表
     *
     * @return list
     * @param pageQO
     */
    PageVO<BizScreensaver> getBizScreensaverList(PageQO<BizScreensaver> pageQO);

    /**
     * 根据ID删除屏保
     *
     * @param screensaverId 屏保ID
     * @return ret
     */
    Result deleteBizScreensaver(Integer screensaverId);

    /**
     * 修改屏保数据
     *
     * @param screensaver 屏保数据新值
     * @return ret
     */
    Result updateBizScreensaver(BizScreensaver screensaver);

    /**
     * 新增屏保数据
     *
     * @param screensaver            新屏保数据
     * @param screensaverMaterialIds 新屏保的屏保素材
     * @return 添加操作结果
     */
    Result addBizScreensaver(BizScreensaver screensaver, Integer[] screensaverMaterialIds);

    /**
     * 给选择的终端发布屏保
     * @param screensaverId 要发布的屏保ID
     * @param terminalIds   终端ID列表
     * @return  发布结果
     */
    Result publishScreensaver(Integer screensaverId,String[] terminalIds);
}
