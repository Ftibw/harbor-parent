package com.whxm.harbor.controller;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.utils.FileUtils;
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

@Api(value = "API - BusinessActivityController", description = "活动 Controller")
@RestController
public class BizActivityController {

    private static final Logger logger = LoggerFactory.getLogger(BizActivityController.class);

    @Autowired
    private ActivityService activityService;

    @ApiOperation("获取活动列表")
    @GetMapping("/bizActivities")
    public Result getBizActivities(PageQO<BizActivity> pageQO, BizActivity condition) {
        Result ret = null;

        PageVO<BizActivity> pageVO = null;

        try {
            pageQO.setCondition(condition);

            pageVO = activityService.getBizActivityList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {

            logger.error("活动列表 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", pageQO);
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

            ret = new Result(activity);

        } catch (Exception e) {

            logger.error("ID为{}的活动数据 获取报错", activityId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + activityId + "的活动数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("修改活动")
    @PutMapping("/bizActivity")
    public Result updateBizActivity(@RequestBody BizActivity bizActivity) {
        Result result = null;
        try {
            result = activityService.updateBizActivity(bizActivity);
        } catch (Exception e) {

            logger.error("活动数据 修改报错", e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "活动数据 修改报错", bizActivity);
        }
        return result;
    }

    @ApiOperation("删除活动")
    @DeleteMapping("/bizActivity/{ID}")
    public Result delBizActivity(
            @ApiParam(name = "ID", value = "活动的ID", required = true)
            @PathVariable("ID") Integer bizActivityId
    ) {
        Result result = null;

        try {
            result = activityService.deleteBizActivity(bizActivityId);

        } catch (Exception e) {

            logger.error("ID为{}的活动数据 删除报错", bizActivityId, e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizActivityId + "的活动数据 删除报错", null);
        }
        return result;
    }

    @ApiOperation("添加活动")
    @PostMapping("/bizActivity")
    public Result addBizActivity(@RequestBody BizActivity bizActivity) {
        Result result = null;
        try {
            result = activityService.addBizActivity(bizActivity);

        } catch (Exception e) {

            logger.error("活动数据 添加报错", e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "活动数据 添加报错", bizActivity);
        }
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
                return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, file);
            }
        } else {

            logger.error("上传的文件是空的");
            return new Result(HttpStatus.OK.value(), "上传的文件是空的", file);
        }
    }

}
