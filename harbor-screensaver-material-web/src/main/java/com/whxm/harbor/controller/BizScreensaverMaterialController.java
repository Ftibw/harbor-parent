package com.whxm.harbor.controller;

import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.conf.FileDir;
import com.whxm.harbor.screensaver.material.service.ScreensaverMaterialService;
import com.whxm.harbor.bean.BizScreensaverMaterial;
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

@Api(value = "API - BusinessScreensaverMaterialController", description = "屏保素材 Controller")
@RestController
public class BizScreensaverMaterialController {

    private static final Logger logger = LoggerFactory.getLogger(BizScreensaverMaterialController.class);

    @Autowired
    private ScreensaverMaterialService screensaverMaterialService;

    @Autowired
    private FileDir fileDir;

    @ApiOperation("上传屏保素材图片")
    @PostMapping("/picture")
    public Result uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        return FileUtils.upload(file, request, fileDir.getScreensaverMaterialImgDir());
    }

    //===============================以下均被拦截===============================

    @ApiOperation("获取屏保素材列表")
    @GetMapping("/bizScreensaverMaterials")
    public Result getBizActivities(PageQO<BizScreensaverMaterial> pageQO, BizScreensaverMaterial condition) {

        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<BizScreensaverMaterial> pageVO = screensaverMaterialService.getBizScreensaverMaterialList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("屏保素材列表 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保素材列表 获取报错", pageQO);
        }

        return ret;
    }

    @ApiOperation("获取屏保素材")
    @GetMapping("/bizScreensaverMaterial/{ID}")
    public Result getBizScreensaverMaterial(
            @ApiParam(name = "ID", value = "屏保素材的ID", required = true)
            @PathVariable("ID") Integer screensaverMaterialId
    ) {
        Result ret = null;
        BizScreensaverMaterial screensaverMaterial = null;
        try {
            screensaverMaterial = screensaverMaterialService.getBizScreensaverMaterial(screensaverMaterialId);

            ret = new Result(screensaverMaterial);

        } catch (Exception e) {

            logger.error("ID为{}的屏保素材数据 获取报错", screensaverMaterialId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + screensaverMaterialId + "的屏保素材数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("修改屏保素材")
    @PutMapping("/bizScreensaverMaterial")
    public Result updateBizScreensaverMaterial(@RequestBody BizScreensaverMaterial bizScreensaverMaterial) {
        Result ret = null;
        try {
            ret = screensaverMaterialService.updateBizScreensaverMaterial(bizScreensaverMaterial);

        } catch (Exception e) {

            logger.error("屏保素材数据 修改报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保素材数据 修改报错", bizScreensaverMaterial);
        }

        return ret;
    }

    @ApiOperation("删除屏保素材")
    @DeleteMapping("/bizScreensaverMaterial/{ID}")
    public Result delBizScreensaverMaterial(
            @ApiParam(name = "ID", value = "屏保素材的ID", required = true)
            @PathVariable("ID") Integer bizScreensaverMaterialId
    ) {
        Result ret = null;
        try {
            ret = screensaverMaterialService.deleteBizScreensaverMaterial(bizScreensaverMaterialId);
        } catch (Exception e) {

            logger.error("ID为{}的屏保素材数据 删除报错", bizScreensaverMaterialId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizScreensaverMaterialId + "的屏保素材数据 删除报错", null);
        }

        return ret;
    }

    @ApiOperation("添加屏保素材")
    @PostMapping("/bizScreensaverMaterial")
    public Result addBizScreensaverMaterial(@RequestBody BizScreensaverMaterial bizScreensaverMaterial) {
        Result ret = null;
        try {
            ret = screensaverMaterialService.addBizScreensaverMaterial(bizScreensaverMaterial);

        } catch (Exception e) {
            logger.error("屏保素材 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保素材 添加报错", bizScreensaverMaterial);
        }
        return ret;
    }
}
