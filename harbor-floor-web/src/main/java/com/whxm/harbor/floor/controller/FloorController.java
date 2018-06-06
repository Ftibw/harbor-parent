package com.whxm.harbor.floor.controller;

import com.whxm.harbor.bean.BizFloor;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.floor.service.FloorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FloorController {
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private FloorService floorService;

    /**
     * 获取楼层列表
     */
    @GetMapping("/bizFloors")
    public Result getBizFloors(){
        List<BizFloor> bizFloorList = floorService.getBizFloorList();
        Result ret = new Result();
        ret.setData(bizFloorList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    /**
     * 添加楼层
     */
    @PostMapping("/bizFloor/")
    public Result addBizFloor(@RequestBody BizFloor bizFloor){
        Result result = floorService.addBizFloor(bizFloor);
        return result;
    }

    /**
     * 修改楼层
     */
    @PutMapping("/bizFloor/")
    public Result updateBizFloor(@RequestBody BizFloor bizFloor){
        Result result = floorService.updateBizFloor(bizFloor);
        return result;
    }

    /**
     * 删除楼层
     */
    @DeleteMapping("/bizFloor/{ID}")
    public Result delBizFloor(@PathVariable("ID") Integer bizFloorId){
        Result result = floorService.deleteBizFloor(bizFloorId);
        return result;
    }
}
