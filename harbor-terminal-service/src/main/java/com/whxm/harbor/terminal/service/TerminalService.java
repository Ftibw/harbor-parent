package com.whxm.harbor.terminal.service;

import com.whxm.harbor.bean.BizTerminal;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;

import java.util.Map;

/**
 * 终端服务
 */
public interface TerminalService {
    /**
     * 根据终端ID获取终端数据
     *
     * @param bizTerminalId 终端ID
     * @return 终端数据
     */
    BizTerminal getBizTerminal(String bizTerminalId);

    /**
     * 获取终端列表
     *
     * @param pageQO 分页查询对象
     * @return pageVO
     */
    PageVO<BizTerminal> getBizTerminalList(PageQO<BizTerminal> pageQO);

    /**
     * 根据ID停用/启用终端
     *
     * @param bizTerminalId 终端ID
     * @return ret
     */
    Result deleteBizTerminal(String bizTerminalId);

    /**
     * 修改终端数据
     *
     * @param bizTerminal 终端数据新值
     * @return ret
     */
    Result updateBizTerminal(BizTerminal bizTerminal);

    /**
     * 新增终端数据
     *
     * @param bizTerminal 终端数据新值
     * @return ret
     */
    Result addBizTerminal(BizTerminal bizTerminal);

    /**
     * 根据终端编号和终端平台获取终端ID
     *
     * @param params 终端编号和终端平台
     * @return 终端ID
     */
    Result getRegisteredTerminal(Map<String, Object> params);

    /**
     * 根据终端编号和屏保ID
     * @param params
     * @return
     */
    Map<String,Object> getTerminalScreensaverProgram(Map<String, Object> params);
}