package com.whxm.harbor.screensaver.controller;

import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.common.utils.FileUtils;
import com.whxm.harbor.screensaver.service.ScreensaverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Api(value = "API - BusinessScreensaverController", description = "屏保 Controller")
@RestController
public class BizScreensaverController {

    private static final Logger logger = LoggerFactory.getLogger(BizScreensaverController.class);

    @Autowired
    private ScreensaverService screensaverService;

    @ApiOperation("获取屏保列表")
    @GetMapping("/bizScreensavers")
    public Result getBizActivities() {

        Result ret = null;
        try {
            List<BizScreensaver> bizScreensaverList = screensaverService.getBizScreensaverList();

            ret = new Result(HttpStatus.OK.value(), "OK", bizScreensaverList);

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

            ret = new Result(200, "ok", screensaver);

        } catch (Exception e) {
            logger.error("屏保数据 获取报错", e);
            ret = new Result(500, "error", null);
        }

        return ret;
    }

    @ApiOperation("修改屏保")
    @PutMapping("/bizScreensaver")
    public Result updateBizScreensaver(@RequestBody BizScreensaver bizScreensaver) {
        Result result = screensaverService.updateBizScreensaver(bizScreensaver);

        return result;
    }

    @ApiOperation("删除屏保")
    @DeleteMapping("/bizScreensaver/{ID}")
    public Result delBizScreensaver(
            @ApiParam(name = "ID", value = "屏保的ID", required = true)
            @PathVariable("ID") Integer bizScreensaverId
    ) {
        Result result = screensaverService.deleteBizScreensaver(bizScreensaverId);

        return result;
    }

    @ApiOperation("添加屏保")
    @PostMapping("/bizScreensaver")
    public Result addBizScreensaver(@RequestBody BizScreensaver bizScreensaver) {
        Result ret = null;
        try {
            ret = screensaverService.addBizScreensaver(bizScreensaver);

        } catch (Exception e) {
            logger.error("屏保 添加报错",e);

            ret=new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"屏保 添加报错",null);
        }
        return ret;
    }

}
