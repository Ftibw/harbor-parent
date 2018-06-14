package com.whxm.harbor.controller;

import com.whxm.harbor.activity.material.service.ActivityMaterialService;
import com.whxm.harbor.bean.*;
import com.whxm.harbor.conf.FileDir;
import com.whxm.harbor.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "API - BusinessActivityMaterialController", description = "活动素材 Controller")
@RestController
public class BizActivityMaterialController {

    private static final Logger logger = LoggerFactory.getLogger(BizActivityMaterialController.class);

    @Autowired
    private ActivityMaterialService activityMaterialService;


    @ApiOperation(value = "根据活动编号获取活动素材列表",
            notes = "param: {'activity':'xx'}   activity表示活动编号")
    @PostMapping("/activityMaterials")
    public Map<String, Object> getBizActivities(@RequestBody Map<String, Object> params) {

        ResultMap<String, Object> ret = new ResultMap<String, Object>(2);

        try {
            Integer activityId = (Integer) params.get("activity");

            List<BizActivityMaterial> list = activityMaterialService.getMaterialListByActivityId(activityId);

            ret.build("data", list);

            ret = list.isEmpty() ? ret.build("success", false) : ret.build("success", true);

        } catch (Exception e) {

            logger.error("活动素材列表 获取报错", params, e);

            ret.build("data", new Object[]{}).build("success", false);
        }

        return ret;
    }

    //==========================以下均被拦截============================

    @ApiOperation("获取活动素材列表")
    @GetMapping("/bizActivityMaterials")
    public Result getBizActivities(PageQO<BizActivityMaterial> pageQO, BizActivityMaterial condition) {

        Result ret = null;
        PageVO<BizActivityMaterial> pageVO = null;
        try {
            pageQO.setCondition(condition);

            pageVO = activityMaterialService.getBizActivityMaterialList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("活动素材列表 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "活动素材列表 获取报错", pageQO);
        }

        return ret;
    }

    @ApiOperation("获取活动素材")
    @GetMapping("/bizActivityMaterial/{ID}")
    public Result getBizActivityMaterial(
            @ApiParam(name = "ID", value = "活动素材的ID", required = true)
            @PathVariable("ID") Integer activityMaterialId
    ) {
        Result ret = null;
        BizActivityMaterial activityMaterial = null;

        logger.info("活动素材的ID为{}", activityMaterialId);

        try {
            activityMaterial = activityMaterialService.getBizActivityMaterial(activityMaterialId);

            ret = new Result(activityMaterial);

        } catch (Exception e) {

            logger.error("ID为{}活动素材数据 获取报错", activityMaterialId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + activityMaterialId + "活动素材数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("修改活动素材")
    @PutMapping("/bizActivityMaterial")
    public Result updateBizActivityMaterial(@RequestBody BizActivityMaterial bizActivityMaterial) {
        Result result = null;
        try {
            result = activityMaterialService.updateBizActivityMaterial(bizActivityMaterial);
        } catch (Exception e) {

            logger.error("活动素材数据 修改报错", e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "活动素材数据 修改报错", bizActivityMaterial);
        }
        return result;
    }

    @ApiOperation("删除活动素材")
    @DeleteMapping("/bizActivityMaterial/{ID}")
    public Result delBizActivityMaterial(
            @ApiParam(name = "ID", value = "活动素材的ID", required = true)
            @PathVariable("ID") Integer bizActivityMaterialId
    ) {
        Result result = null;
        try {
            result = activityMaterialService.deleteBizActivityMaterial(bizActivityMaterialId);
        } catch (Exception e) {

            logger.error("ID为{}的活动素材 删除报错", bizActivityMaterialId, e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizActivityMaterialId + "的活动素材 删除报错", null);
        }
        return result;
    }

    @ApiOperation("添加活动素材")
    @PostMapping("/bizActivityMaterial")
    public Result addBizActivityMaterial(@RequestBody BizActivityMaterial bizActivityMaterial) {
        Result result = null;
        try {
            result = activityMaterialService.addBizActivityMaterial(bizActivityMaterial);

        } catch (Exception e) {

            logger.error("活动素材数据 添加报错", e);

            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "活动素材数据 添加报错", bizActivityMaterial);
        }
        return result;
    }

    @ApiOperation("上传活动素材图片")
    @PostMapping("/picture")
    public Result uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        return FileUtils.upload(file, request, fileDir.getActivityMaterialImgDir());
    }

    @Autowired
    private FileDir fileDir;
}
