package com.whxm.harbor.activity.controller;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.common.utils.FileUtils;
import com.whxm.harbor.common.utils.StringUtils;
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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Api(value = "API - BusinessActivityController", description = "活动 Controller")
@RestController
public class BizActivityController {

    private static final Logger logger = LoggerFactory.getLogger(BizActivityController.class);

    @Autowired
    private ActivityService activityService;

    @ApiOperation("获取活动列表")
    @GetMapping("/bizActivities")
    public Result getBizActivities(PageQO<BizActivity> pageQO) {
        Result ret = null;

        PageVO<BizActivity> pageVO = null;

        try {
            pageVO = activityService.getBizActivityList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {

            logger.error("活动列表 获取报错", e);
        }

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

            ret = new Result(200, "ok", activity);

        } catch (Exception e) {
            logger.error("活动数据 获取报错", e);
            ret = new Result(500, "error", null);
        }

        return ret;
    }

    @ApiOperation("修改活动")
    @PutMapping("/bizActivity")
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
    @PostMapping("/bizActivity")
    public Result addBizActivity(@RequestBody BizActivity bizActivity) {
        Result result = activityService.addBizActivity(bizActivity);
        return result;
    }

    @ApiOperation("上传logo")
    @PostMapping("/logo")
    public Result uploadLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String ACTIVITY_LOGO_DIR = "activityLogo";

        String originName = null;

        if (!file.isEmpty()) {
            try {
                originName = file.getOriginalFilename();

                String href = FileUtils.upload(file, request, ACTIVITY_LOGO_DIR);

                HashMap<String, Object> map = new HashMap<>();
                map.put("activityLogo", href);
                map.put("activityLogoSize", file.getSize());

                return new Result(HttpStatus.OK.value(), "文件" + originName + "上传成功", map);

            } catch (IOException e) {
                String msg = "文件" + originName + "上传 发生错误";
                logger.error(msg, e);
                return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
            }
        } else {

            logger.error("上传的文件是空的");
            return new Result(HttpStatus.OK.value(), "上传的文件是空的", null);
        }
    }

}
