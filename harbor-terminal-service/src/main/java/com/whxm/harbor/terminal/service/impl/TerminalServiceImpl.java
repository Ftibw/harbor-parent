package com.whxm.harbor.terminal.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizTerminal;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.mapper.BizTerminalMapper;
import com.whxm.harbor.terminal.service.TerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TerminalServiceImpl implements TerminalService {

    private static final Logger logger = LoggerFactory.getLogger(TerminalServiceImpl.class);

    @Resource
    private BizTerminalMapper bizTerminalMapper;

    @Override
    public BizTerminal getBizTerminal(String bizTerminalId) {

        BizTerminal terminal = null;

        try {
            terminal = bizTerminalMapper.selectByPrimaryKey(bizTerminalId);

            if (null == terminal) {
                logger.error("错误终端ID", terminal);
            }
        } catch (Exception e) {

            logger.error("终端ID为{}的数据 获取报错", bizTerminalId);

            throw new RuntimeException();
        }

        return terminal;
    }

    @Override
    public PageVO<BizTerminal> getBizTerminalList(PageQO<BizTerminal> pageQO) {

        PageVO<BizTerminal> pageVO = null;
        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(bizTerminalMapper.getBizTerminalList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());

        } catch (Exception e) {

            logger.error("终端列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public Result deleteBizTerminal(String bizTerminalId) {
        Result ret = null;

        try {

            bizTerminalMapper.delScreensaverTerminalRelation(bizTerminalId);

            BizTerminal bizTerminal = new BizTerminal();

            bizTerminal.setIsDeleted(0);

            updateBizTerminal(bizTerminal);

            logger.info("ID为{}的终端 删除成功", bizTerminalId);

            ret = new Result("删除成功");

        } catch (Exception e) {

            logger.error("终端ID为{}的数据 删除错误", bizTerminalId);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizTerminal(BizTerminal bizTerminal) {
        Result ret = null;

        try {

            int affectRow = bizTerminalMapper.updateByPrimaryKeySelective(bizTerminal);

            ret = new Result("终端数据修改了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("终端数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizTerminal(BizTerminal bizTerminal) {
        Result ret = null;

        try {
            int affectRow = bizTerminalMapper.insert(bizTerminal);

            ret = new Result("终端数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("终端数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
