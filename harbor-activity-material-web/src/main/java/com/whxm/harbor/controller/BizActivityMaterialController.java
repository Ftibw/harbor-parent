package com.whxm.harbor.controller;

import com.whxm.harbor.activity.material.service.ActivityMaterialService;
import com.whxm.harbor.bean.BizActivityMaterial;
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

@Api(value = "API - BusinessActivityMaterialController", description = "活动素材 Controller")
@RestController
public class BizActivityMaterialController {

    private static final Logger logger = LoggerFactory.getLogger(BizActivityMaterialController.class);

    @Autowired
    private ActivityMaterialService activityMaterialService;

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
        String ACTIVITY_LOGO_DIR = "activityMaterialPicture";

        String originName = null;

        if (!file.isEmpty()) {
            try {
                originName = file.getOriginalFilename();

                String href = FileUtils.upload(file, request, ACTIVITY_LOGO_DIR);

                HashMap<String, Object> map = new HashMap<>();
                map.put("activityMaterialImgPath", href);
                map.put("activityMaterialImgName", originName);
                map.put("activityMaterialSize", file.getSize());
                map.put("imgNewName", href.replaceAll("^.*\\\\(.*)\\..*$", "$1"));

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
    /**
     * 文件上传返回参数模板方法
     */
    /*public Result uploadTemplate(MultipartFile file, HttpServletRequest request, String uploadDir, ParamCallback callback) {
        String originName = null;
        if (!file.isEmpty()) {
            try {
                originName = file.getOriginalFilename();
                String href = FileUtils.upload(file, request, uploadDir);
                HashMap<String, Object> map = new HashMap<>();
                callback.execute(map, href, originName, file.getSize());
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

    interface ParamCallback {
        void execute(Map<String, Object> map, String var1, String var2, Long var3);
    }

    @ApiOperation("上传活动素材图片")
    @PostMapping("/img")
    public Result uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String ACTIVITY_LOGO_DIR = "activityMaterialPicture";

        return uploadTemplate(file, request, ACTIVITY_LOGO_DIR, (map, var1, var2, var3) -> {
            map.put("activityMaterialImgPath", var1);
            map.put("activityMaterialImgName", var2);
            map.put("activityMaterialSize", file.getSize());
        });
    }*/
}
