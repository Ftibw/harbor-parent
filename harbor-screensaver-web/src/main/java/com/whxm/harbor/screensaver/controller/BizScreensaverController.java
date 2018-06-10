package com.whxm.harbor.screensaver.controller;

import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.screensaver.service.ScreensaverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "API - BusinessScreensaverController", description = "屏保 Controller")
@RestController
public class BizScreensaverController {

    private static final Logger logger = LoggerFactory.getLogger(BizScreensaverController.class);

    @Autowired
    private ScreensaverService screensaverService;

    @ApiOperation("获取屏保列表")
    @GetMapping("/bizScreensavers")
    public Result getBizActivities(PageQO<BizScreensaver> pageQO, BizScreensaver condition) {

        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<BizScreensaver> pageVO = screensaverService.getBizScreensaverList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("屏保列表 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保列表 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("获取屏保")
    @GetMapping("/bizScreensaver/{ID}")
    public Result getBizScreensaver(
            @ApiParam(name = "ID", value = "屏保的ID", required = true)
            @PathVariable("ID") Integer screensaverId
    ) {
        Result ret = null;
        BizScreensaver screensaver = null;
        try {
            screensaver = screensaverService.getBizScreensaver(screensaverId);

            ret = new Result(screensaver);

        } catch (Exception e) {
            logger.error("屏保数据 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }

        return ret;
    }

    @ApiOperation("修改屏保")
    @PutMapping("/bizScreensaver")
    public Result updateBizScreensaver(@RequestBody BizScreensaver bizScreensaver) {


        Result ret = null;
        try {
            ret = screensaverService.updateBizScreensaver(bizScreensaver);
        } catch (Exception e) {
            logger.error("屏保数据 修改报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保数据 修改报错", null);
        }

        return ret;
    }

    @ApiOperation("删除屏保")
    @DeleteMapping("/bizScreensaver/{ID}")
    public Result delBizScreensaver(
            @ApiParam(name = "ID", value = "屏保的ID", required = true)
            @PathVariable("ID") Integer bizScreensaverId
    ) {
        Result ret = null;

        try {
            ret = screensaverService.deleteBizScreensaver(bizScreensaverId);
        } catch (Exception e) {
            logger.error("屏保 删除报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加屏保")
    @PostMapping("/bizScreensaver")
    public Result addBizScreensaver(@RequestBody ScreensaverParam param) {
        Result ret = null;
        try {
            ret = screensaverService.addBizScreensaver(param.bizScreensaver, param.screensaverMaterialIds);

        } catch (Exception e) {
            logger.error("屏保 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保 添加报错", null);
        }
        return ret;
    }

    @ApiOperation("发布屏保")
    @PostMapping("/publishedScreensaver")
    public Result publishScreensaver(@RequestBody PublishedScreensaverParam param) {
        Result ret = null;
        try {
            ret = screensaverService.publishScreensaver(param.screensaverId, param.terminalIds);

        } catch (Exception e) {
            logger.error("ID为{}的屏保 发布报错", param.screensaverId);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保 发布报错", null);
        }
        return ret;
    }

}

class ScreensaverParam {

    public BizScreensaver bizScreensaver;

    public Integer[] screensaverMaterialIds;
}

class PublishedScreensaverParam {

    public Integer screensaverId;

    public String[] terminalIds;
}