package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizTerminal;

import java.util.List;

public interface BizTerminalMapper {
    int deleteByPrimaryKey(String terminalId);

    int insert(BizTerminal record);

    int insertSelective(BizTerminal record);

    BizTerminal selectByPrimaryKey(String terminalId);

    int updateByPrimaryKeySelective(BizTerminal record);

    int updateByPrimaryKey(BizTerminal record);

    List<BizTerminal> getBizTerminalList(BizTerminal condition);

    void delScreensaverTerminalRelation(String bizTerminalId);
}