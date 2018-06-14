package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizTerminal;

import java.util.List;
import java.util.Map;

public interface BizTerminalMapper {
    int deleteByPrimaryKey(String terminalId);

    int insert(BizTerminal record);

    int insertSelective(BizTerminal record);

    BizTerminal selectByPrimaryKey(String terminalId);

    int updateByPrimaryKeySelective(BizTerminal record);

    int updateByPrimaryKey(BizTerminal record);

    List<BizTerminal> getBizTerminalList(BizTerminal condition);

    void delScreensaverTerminalRelation(String bizTerminalId);

    BizTerminal selectIdByNumber(String terminalNumber);

    /**
     * 检测终端是否注册
     *
     * @param params 终端编号和终端平台
     * @return 受影响行数
     */
    int updateRegisteredTime(Map<String, Object> params);

    /**
     * 获取终端及其屏保信息
     *
     * @param terminalId 终端ID
     * @return
     */
    Map<String, Object> selectTerminalWithScreensaver(String terminalId);
}