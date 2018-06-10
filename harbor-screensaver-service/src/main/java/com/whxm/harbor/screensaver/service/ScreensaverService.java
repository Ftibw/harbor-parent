package com.whxm.harbor.screensaver.service;


import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.common.bean.Result;

import java.util.List;

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
     */
    List<BizScreensaver> getBizScreensaverList();

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
     * @param screensaver  新屏保数据
     * @return  添加操作结果
     */
    Result addBizScreensaver(BizScreensaver screensaver);


}
