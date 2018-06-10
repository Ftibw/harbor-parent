package com.whxm.harbor.screensaver.material.controller;

import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.screensaver.material.service.ScreensaverMaterialService;
import com.whxm.harbor.bean.BizScreensaverMaterial;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.common.utils.FileUtils;
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

@Api(value = "API - BusinessScreensaverMaterialController", description = "屏保素材 Controller")
@RestController
public class BizScreensaverMaterialController {

    private static final Logger logger = LoggerFactory.getLogger(BizScreensaverMaterialController.class);

    @Autowired
    private ScreensaverMaterialService screensaverMaterialService;

    @ApiOperation("获取屏保素材列表")
    @GetMapping("/bizScreensaverMaterials")
    public Result getBizActivities(PageQO<BizScreensaverMaterial> pageQO) {

        Result ret = null;

        try {
            PageVO<BizScreensaverMaterial> pageVO = screensaverMaterialService.getBizScreensaverMaterialList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("屏保素材列表 获取报错", e);
            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保素材列表 获取报错", null);
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

            ret = new Result(200, "ok", screensaverMaterial);

        } catch (Exception e) {
            logger.error("屏保素材数据 获取报错", e);
            ret = new Result(500, "error", null);
        }

        return ret;
    }

    @ApiOperation("修改屏保素材")
    @PutMapping("/bizScreensaverMaterial")
    public Result updateBizScreensaverMaterial(@RequestBody BizScreensaverMaterial bizScreensaverMaterial) {
        Result result = screensaverMaterialService.updateBizScreensaverMaterial(bizScreensaverMaterial);

        return result;
    }

    @ApiOperation("删除屏保素材")
    @DeleteMapping("/bizScreensaverMaterial/{ID}")
    public Result delBizScreensaverMaterial(
            @ApiParam(name = "ID", value = "屏保素材的ID", required = true)
            @PathVariable("ID") Integer bizScreensaverMaterialId
    ) {
        Result result = screensaverMaterialService.deleteBizScreensaverMaterial(bizScreensaverMaterialId);

        return result;
    }

    @ApiOperation("添加屏保素材")
    @PostMapping("/bizScreensaverMaterial")
    public Result addBizScreensaverMaterial(@RequestBody BizScreensaverMaterial bizScreensaverMaterial) {
        Result ret = null;
        try {
            ret = screensaverMaterialService.addBizScreensaverMaterial(bizScreensaverMaterial);

        } catch (Exception e) {
            logger.error("屏保素材 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "屏保素材 添加报错", null);
        }
        return ret;
    }

    @ApiOperation("上传屏保素材图片")
    @PostMapping("/picture")
    public Result uploadPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String ACTIVITY_LOGO_DIR = "screensaverMaterialPicture";

        String originName = null;

        if (!file.isEmpty()) {
            try {
                originName = file.getOriginalFilename();

                String href = FileUtils.upload(file, request, ACTIVITY_LOGO_DIR);

                HashMap<String, Object> map = new HashMap<>();
                //返回图片的保存路径,图片原名,图片大小,图片新名
                map.put("screensaverMaterialImgPath", href);
                map.put("screensaverMaterialImgName", originName);
                map.put("screensaverMaterialSize", file.getSize());
                map.put("imgNewName", href.replaceAll("^.*\\\\(.*)\\..*$", "$1"));

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
