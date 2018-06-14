package com.whxm.harbor.controller;

import com.whxm.harbor.bean.*;
import com.whxm.harbor.conf.FileDir;
import com.whxm.harbor.utils.FileUtils;
import com.whxm.harbor.shop.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private FileDir fileDir;

    @ApiOperation(value = "根据业态和楼层获取店铺列表",
            notes = "param: {'floor':'xx','type':''}   type表示业态ID，floor表示楼层ID")
    @PostMapping("/shops")
    public Map<String, Object> getBizShops(@RequestBody Map<String, Object> params) {

        ResultMap<String, Object> ret = new ResultMap<>(2);

        try {
            List<BizShop> list = shopService.getBizShopList(params);

            ret.build("data", list);

            ret = list.isEmpty() ? ret.build("success", false) : ret.build("success", true);

        } catch (Exception e) {

            logger.error("楼层列表 获取报错", params, e);

            ret.build("data", new Object[]{}).build("success", false);
        }

        return ret;
    }


    @ApiOperation(value = "根据商铺ID获取商铺图片")
    @GetMapping("/shopPictures/{ID}")
    public Result getPicturesById(@PathVariable("ID") String bizShopId) {

        Result ret = null;

        try {
            List<String> shopPicturePaths = shopService.getShopPicturesById(bizShopId);

            ret = new Result(shopPicturePaths);

        } catch (Exception e) {
            logger.error("ID为{}的商铺图片 获取报错", bizShopId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizShopId + "的商铺图片 获取报错", null);
        }

        return ret;
    }

    @ApiOperation(value = "根据商铺的业态类型获取商铺图片")
    @GetMapping("/typePictures")
    public Result getPicturesByType(
            @ApiParam(name = "type", value = "商铺的业态种类", required = true)
                    String type) {

        Result ret = null;

        try {
            ret = shopService.getShopPicturesByBizType(type);

        } catch (Exception e) {
            logger.error("业态类型为{}的商铺图片 获取报错", type, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "业态类型为" + type + "的商铺图片 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("上传商铺logo")
    @PostMapping("/logo")
    public Result uploadLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        return FileUtils.upload(file, request, fileDir.getShopLogoDir());
    }

    @ApiOperation(value = "上传商铺图片", notes = "表单控件中name属性的值必须为file")
    @PostMapping("/pictures")
    public Result uploadPicture(HttpServletRequest request) {

        Result ret = null;

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        ArrayList<Object> retList = new ArrayList<>();

        files.forEach(file -> {
            try {
                Map<String, Object> map = new HashMap<String, Object>(4);

                FileUtils.upload(file, request, fileDir.getShopPictureDir(), map);

                retList.add(map);

            } catch (Exception e) {

                logger.error("文件" + file.getOriginalFilename() + "上传 发生错误", e);
            }
        });

        ret = new Result(retList);

        return ret;
    }

    //==========================以下均被拦截============================

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

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商铺列表 获取错误", pageQO);
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

            logger.error("ID为{}的商铺数据 获取报错", shopId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + shopId + "的商铺数据 获取报错", null);
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

            logger.error("商铺数据 修改报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商铺数据 修改报错", bizShop);
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

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizShopId + "的商铺 状态切换报错", null);
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

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商铺数据 添加报错", param);
        }

        return ret;
    }
}

//商铺+商铺图片数据封装
class ShopParam {

    public BizShop bizShop;

    public List<Map<String, Object>> pictureList;
}