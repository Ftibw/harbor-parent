package com.whxm.harbor.floor.controller;

import com.whxm.harbor.bean.BizFloor;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.floor.service.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "API - FloorController", description = "楼层 Controller")
@RestController
public class FloorController {
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private FloorService floorService;

    @ApiOperation("获取楼层列表")
    @GetMapping("/bizFloors")
    public Result getBizFloors(PageQO<BizFloor> pageQO,BizFloor condition) {
        PageVO<BizFloor> pageVO = null;

        Result ret = null;
        try {
            pageQO.setCondition(condition);

            pageVO = floorService.getBizFloorList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {
            logger.error("楼层列表 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "楼层列表 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("添加楼层")
    @PostMapping("/bizFloor/")
    public Result addBizFloor(@RequestBody BizFloor bizFloor) {
        Result ret = null;

        try {
            ret = floorService.addBizFloor(bizFloor);
        } catch (Exception e) {

            logger.error("楼层数据 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "楼层数据 添加报错", null);
        }

        return ret;
    }

    @ApiOperation("修改楼层")
    @PutMapping("/bizFloor/")
    public Result updateBizFloor(@RequestBody BizFloor bizFloor) {

        Result ret = null;

        try {
            ret = floorService.updateBizFloor(bizFloor);
        } catch (Exception e) {

            logger.error("楼层数据 修改报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "楼层数据 修改报错", null);
        }

        return ret;
    }


    @ApiOperation("删除楼层")
    @DeleteMapping("/bizFloor/{ID}")
    public Result delBizFloor(@ApiParam(name = "ID", value = "楼层ID", required = true)
                              @PathVariable("ID") Integer bizFloorId) {
        Result ret = null;

        try {
            ret = floorService.deleteBizFloor(bizFloorId);
        } catch (Exception e) {

            logger.error("楼层数据 删除报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "楼层数据 删除报错", null);
        }

        return ret;
    }
}
