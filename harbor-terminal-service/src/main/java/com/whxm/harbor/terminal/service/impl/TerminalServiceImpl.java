package com.whxm.harbor.terminal.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.*;
import com.whxm.harbor.mapper.BizScreensaverMaterialMapper;
import com.whxm.harbor.mapper.BizTerminalMapper;
import com.whxm.harbor.terminal.service.TerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.whxm.harbor.bean.Result;
import javax.annotation.Resource;
import java.util.*;

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
                logger.error("ID为{}的终端不存在", bizTerminalId);
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

            bizTerminal.setIsDeleted(1);

            bizTerminal.setAddTerminalTime(new Date());

            bizTerminal.setTerminalId(UUID.randomUUID().toString().replace("-", ""));

            int affectRow = bizTerminalMapper.insert(bizTerminal);

            ret = new Result("终端数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("终端数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result getRegisteredTerminal(Map<String, Object> params) {

        Result ret = null;

        try {
            String terminalId = bizTerminalMapper.selectRegisteredTerminal(params);

            if (null != terminalId)
                ret = new Result(terminalId);
            else
                ret = new Result(HttpStatus.NOT_FOUND.value(), "终端未注册", null);

        } catch (Exception e) {

            logger.error("终端是否注册 查询报错 ", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Resource
    private BizScreensaverMaterialMapper bizScreensaverMaterialMapper;

    @Override
    public Map<String, Object> getTerminalScreensaverProgram(Map<String, Object> params) {

        ResultMap<String, Object> ret = new ResultMap<>(4);

        final List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> terminalInfo = null;

        String terminalNumber = (String) params.get("terminalNumber");

        Object screensaverId = null;

        Object terminalSwitchTime = null;

        try {
            terminalInfo = bizTerminalMapper.selectTerminalWithScreensaver(terminalNumber);

            if (null != terminalInfo) {
                //屏保信息
                screensaverId = terminalInfo.get("screensaverId");
                //终端开关机时间
                terminalSwitchTime = terminalInfo.get("terminalSwitchTime");
            }
            //先存了list引用再说
            ret.build("prog", screensaverId)
                    .build("on_off", terminalSwitchTime)
                    .build("data", list);

            if (null == screensaverId || "".equals(screensaverId)) {
                ret.build("code", 0);

            } else {
                bizScreensaverMaterialMapper
                        .selectMaterialsByScreensaverId(screensaverId)
                        .forEach(item -> {
                            HashMap<String, Object> map = new HashMap<>(2);
                            map.put("name", item.getScreensaverMaterialName());
                            map.put("url", item.getScreensaverMaterialImgPath());
                            list.add(map);
                        });

                ret = list.isEmpty() ? ret.build("code", 0) : ret.build("code", 1);
            }

        } catch (Exception e) {

            logger.error("编号为{}的终端屏保信息 查询报错", terminalNumber, e);

            throw new RuntimeException();
        }

        return ret;
    }
}
