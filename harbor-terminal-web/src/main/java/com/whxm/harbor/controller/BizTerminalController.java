package com.whxm.harbor.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whxm.harbor.bean.BizTerminal;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.terminal.service.TerminalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "API - BusinessTerminalController", description = "终端 Controller")
@RestController
public class BizTerminalController {

    private static final Logger logger = LoggerFactory.getLogger(BizTerminalController.class);

    @Autowired
    private TerminalService terminalService;

    @ApiOperation("获取终端列表")
    @GetMapping("/bizTerminals")
    public Result getBizTerminals(PageQO<BizTerminal> pageQO, BizTerminal condition) {

        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<BizTerminal> pageVO = terminalService.getBizTerminalList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("终端列表 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端列表 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("获取终端")
    @GetMapping("/bizTerminal/{ID}")
    public Result getBizTerminal(
            @ApiParam(name = "ID", value = "终端的ID", required = true)
            @PathVariable("ID") String terminalId
    ) {
        Result ret = null;
        BizTerminal terminal = null;
        try {
            terminal = terminalService.getBizTerminal(terminalId);

            ret = new Result(terminal);

        } catch (Exception e) {
            logger.error("终端数据 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("修改终端")
    @PutMapping("/bizTerminal")
    public Result updateBizTerminal(@RequestBody BizTerminal bizTerminal) {
        Result ret = null;
        try {
            ret = terminalService.updateBizTerminal(bizTerminal);
        } catch (Exception e) {

            logger.error("终端数据 修改报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端数据 修改报错", null);
        }

        return ret;
    }

    @ApiOperation("删除终端")
    @DeleteMapping("/bizTerminal/{ID}")
    public Result delBizTerminal(
            @ApiParam(name = "ID", value = "终端的ID", required = true)
            @PathVariable("ID") String bizTerminalId
    ) {
        Result ret = null;
        try {
            ret = terminalService.deleteBizTerminal(bizTerminalId);
        } catch (Exception e) {

            logger.error("终端数据 删除报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端数据 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加终端")
    @PostMapping("/bizTerminal")
    public Result addBizTerminal(@RequestBody BizTerminal bizTerminal) {
        Result ret = null;
        try {
            ret = terminalService.addBizTerminal(bizTerminal);

        } catch (Exception e) {
            logger.error("终端 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端 添加报错", null);
        }
        return ret;
    }

    @ApiOperation(value = "终端注册",
            notes = "param: {'sn':'xx','os':1/2}  sn表示终端编号;os表示终端类型（1=android  2=windows）")
    @PostMapping("/register")
    public Map<String, Boolean> register(@RequestBody Map<String, Object> params) {

        Map<String, Boolean> ret = new HashMap<>();

        try {
            if (HttpStatus.OK.value() ==
                    terminalService.getRegisteredTerminal(params).getStatus()) {

                ret.put("success", true);
            } else {
                ret.put("success", false);
            }
        } catch (Exception e) {

            logger.error("终端注册检测报错", e);

            ret.put("success", false);
        }

        return ret;
    }
}
