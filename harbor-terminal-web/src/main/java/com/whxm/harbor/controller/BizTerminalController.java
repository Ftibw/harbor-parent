package com.whxm.harbor.controller;

import com.whxm.harbor.bean.*;
import com.whxm.harbor.terminal.service.TerminalService;
import com.whxm.harbor.utils.IPv4Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "API - BusinessTerminalController", description = "终端 Controller")
@RestController
public class BizTerminalController {

    private static final Logger logger = LoggerFactory.getLogger(BizTerminalController.class);

    @Autowired
    private TerminalService terminalService;

    @ApiOperation(value = "终端注册",
            notes = "json: {'sn':'xx','os':1/2}  sn表示终端编号;os表示终端类型（1=android  2=windows）")
    @PostMapping(value = "/register")
    public Map<String, Boolean> register(String sn, Integer os) {

        Map<String, Boolean> ret = new HashMap<>(1);

        Map<String, Object> params = new HashMap<>();

        params.put("sn", sn);

        params.put("os", os);

        try {
            if (HttpStatus.OK.value() ==
                    terminalService.getRegisteredTerminal(params).getStatus()) {

                ret.put("success", true);
            } else {
                ret.put("success", false);
            }
        } catch (Exception e) {

            logger.error("编号为{}的终端注册检测报错", params.get("sn"), e);

            ret.put("success", false);
        }

        return ret;
    }

    @ApiOperation(value = "获取终端的屏保节目",
            notes = "json: {'sn':'xx','prog':'xx'}    sn表示终端编号；prog表示当前正在播放的屏保编号")
    @PostMapping("/program")
    public Map<String, Object> program(String sn, Integer prog) {

        ResultMap<String, Object> convert = new ResultMap<>(4);

        try {
            convert.build("terminalNumber", sn).build("screensaverId", prog);

            convert = terminalService.getTerminalScreensaverProgram(convert);

        } catch (Exception e) {

            logger.error("编号为{}的终端的屏保数据 获取报错", sn, e);

            convert.clean()
                    .build("code", 0)
                    .build("prog", prog)
                    .build("on_off", null)
                    .build("data", new Object[]{});
        }
        return convert;
    }

    //==========================以下均被拦截============================

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
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端列表 获取报错", pageQO);
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

            logger.error("ID为{}的终端数据 获取报错", terminalId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + terminalId + "的终端数据 获取报错", null);
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
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端数据 修改报错", bizTerminal);
        }

        return ret;
    }

    @ApiOperation("删除终端")
    @DeleteMapping("/bizTerminal/{ID}")
    public Result delBizTerminal(
            @ApiParam(name = "ID", value = "终端的ID", required = true)
            @PathVariable("ID") String terminalId
    ) {
        Result ret = null;
        try {
            ret = terminalService.deleteBizTerminal(terminalId);
        } catch (Exception e) {

            logger.error("ID为{}的终端数据 删除报错", terminalId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + terminalId + "的终端数据 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加终端")
    @PostMapping("/bizTerminal")
    public Result addBizTerminal(@RequestBody BizTerminal bizTerminal, HttpServletRequest request) {
        Result ret = null;
        try {
            bizTerminal.setTerminalIp(IPv4Util.getIpAddress(request));

            ret = terminalService.addBizTerminal(bizTerminal);

        } catch (Exception e) {
            logger.error("终端 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "终端 添加报错", bizTerminal);
        }
        return ret;
    }

}
