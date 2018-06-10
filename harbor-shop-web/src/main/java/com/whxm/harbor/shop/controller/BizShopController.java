package com.whxm.harbor.shop.controller;

import com.github.pagehelper.Page;
import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.bean.BizShop;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.common.utils.FileUtils;
import com.whxm.harbor.shop.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "API - BusinessShopController", description = "商铺 Controller")
@RestController
public class BizShopController {

    private static final Logger logger = LoggerFactory.getLogger(BizShopController.class);

    @Autowired
    private ShopService shopService;

    @ApiOperation("获取商铺列表")
    @GetMapping("/bizShops")
    public Result getBizShops(PageQO<BizShop> pageQO, BizShop condition) {
        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<BizShop> pageVO = shopService.getBizShopList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {

            logger.error("商铺列表 获取错误", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }

        return ret;
    }

    @ApiOperation("获取商铺")
    @GetMapping("/bizShop/{ID}")
    public Result getBizShop(
            @ApiParam(name = "ID", value = "商铺的ID", required = true)
            @PathVariable("ID") String shopId
    ) {
        Result ret = null;
        BizShop shop = null;
        try {
            shop = shopService.getBizShop(shopId);

            ret = new Result(shop);

        } catch (Exception e) {

            logger.error("商铺数据 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }

        return ret;
    }

    @ApiOperation("修改商铺")
    @PutMapping("/bizShop")
    public Result updateBizShop(@RequestBody BizShop bizShop) {

        Result ret = null;
        try {
            ret = shopService.updateBizShop(bizShop);

        } catch (Exception e) {

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }
        return ret;
    }

    @ApiOperation("启用/停用商铺")
    @PatchMapping("/bizShop/{ID}")
    public Result triggerBizShop(
            @ApiParam(name = "ID", value = "商铺的ID", required = true)
            @PathVariable("ID") String bizShopId
    ) {
        Result ret = null;
        try {
            ret = shopService.triggerBizShop(bizShopId);

        } catch (Exception e) {

            logger.error("ID为{}的商铺 状态(启用/停用)变更报错", bizShopId);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }

        return ret;
    }

    @ApiOperation(value = "添加商铺",
            notes = "pictureList中元素为map,map有两个key," +
                    "分别必须写成shopPicturePath(商铺图片路径)和shopPictureSize(商铺图片大小)")
    @PostMapping("/bizShop")
    public Result addBizShop(@RequestBody ShopParam param) {

        Result ret = null;
        try {
            ret = shopService.addBizShop(param.bizShop, param.pictureList);

        } catch (Exception e) {

            logger.error("商铺数据 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", null);
        }

        return ret;
    }


    @ApiOperation("上传商铺logo")
    @PostMapping("/logo")
    public Result uploadLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Result ret = null;

        String SHOP_LOGO_DIR = "shopLogo";

        if (!file.isEmpty()) {
            try {
                String href = FileUtils.upload(file, request, SHOP_LOGO_DIR);
                ret = new Result(href);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("商铺logo 上传错误");
                ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商铺logo 上传错误", null);
            }
        } else {
            logger.debug("商铺logo文件为空");
            ret = new Result(null);
        }
        return ret;
    }

    @ApiOperation(value = "上传商铺图片", notes = "表单控件中name属性的值必须为file")
    @PostMapping("/pictures")
    public Result uploadPicture(HttpServletRequest request) {
        Result ret = null;

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        String SHOP_PICTURE_DIR = "shopPicture";

        ArrayList<Object> retList = new ArrayList<>();

        files.forEach(file -> {
            try {

                Long size = file.getSize();

                String href = FileUtils.upload(file, request, SHOP_PICTURE_DIR);

                Map<String, Object> map = new HashMap<String, Object>(2);

                map.put("shopPicturePath", href);
                map.put("shopPictureSize", size);

                retList.add(map);

            } catch (IOException e) {
                logger.error("文件" + file.getOriginalFilename() + "上传 发生错误", e);

            }
        });

        ret = new Result(retList);

        return ret;
    }
}

//商铺+商铺图片数据封装
class ShopParam {

    public BizShop bizShop;

    public List<Map<String, Object>> pictureList;
}