package com.whxm.harbor.activity.controller;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.common.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Api(value = "API - BusinessActivityController", description = "活动 Controller")
@RestController
public class BizActivityController {

    private static final Logger logger = LoggerFactory.getLogger(BizActivityController.class);

    @Autowired
    private ActivityService activityService;

    @ApiOperation("获取活动列表")
    @GetMapping("/bizActivities")
    public Result getBizActivities() {
        List<BizActivity> bizActivityList = activityService.getBizActivityList();
        Result ret = new Result();
        ret.setData(bizActivityList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    @ApiOperation("获取活动")
    @GetMapping("/bizActivity/{ID}")
    public Result getBizActivity(
            @ApiParam(name = "ID", value = "活动的ID", required = true)
            @PathVariable("ID") Integer activityId
    ) {
        Result ret = null;
        BizActivity activity = null;
        try {
            activity = activityService.getBizActivity(activityId);

            ret = new Result(200,"ok",activity);

        } catch (Exception e) {
            logger.error("活动数据 获取报错", e);
            ret = new Result(500,"error",null);
        }
        
        return ret;
    }

    @ApiOperation("修改活动")
    @PutMapping("/bizActivity/")
    public Result updateBizActivity(@RequestBody BizActivity bizActivity) {
        Result result = activityService.updateBizActivity(bizActivity);
        return result;
    }

    @ApiOperation("删除活动")
    @DeleteMapping("/bizActivity/{ID}")
    public Result delBizActivity(
            @ApiParam(name = "ID", value = "活动的ID", required = true)
            @PathVariable("ID") Integer bizActivityId
    ) {
        Result result = activityService.deleteBizActivity(bizActivityId);
        return result;
    }

    @ApiOperation("添加活动")
    @PostMapping("/bizActivity/")
    public Result addBizActivity(@RequestBody BizActivity bizActivity) {
        Result result = activityService.addBizActivity(bizActivity);
        return result;
    }

    @ApiOperation("上传logo")
    @PostMapping("logo")
    public Result uploadLogo(String orderCode, String fileName, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File rootDirectory = new File("rootFile\\" + orderCode);
                if (!rootDirectory.exists()) {
                    rootDirectory.mkdirs();
                }
                File outFile = new File(rootDirectory, fileName + ".pjg");
                byte[] bytes = file.getBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile));
                outputStream.write(bytes);
                outputStream.close();
                //"upload successful the path is " + outFile.getPath()
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //"failed because " + fileName + "file is empty"
        return null;
    }
}
