package com.whxm.harbor.activity.controller;

import com.whxm.harbor.activity.service.ActivityService;
import com.whxm.harbor.bean.BizActivity;
import com.whxm.harbor.common.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BizActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 获取楼层列表
     */
    @GetMapping("/bizActivities")
    public Result getBizActivities(){
        List<BizActivity> bizActivityList = activityService.getBizActivityList();
        Result ret = new Result();
        ret.setData(bizActivityList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    /**
     * 修改楼层
     */
    @PutMapping("/bizActivity/")
    public Result updateBizActivity(@RequestBody BizActivity bizActivity){
        Result result = activityService.updateBizActivity(bizActivity);
        return result;
    }

    /**
     * 删除楼层
     */
    @DeleteMapping("/bizActivity/{ID}")
    public Result delBizActivity(@PathVariable("ID") Integer bizActivityId){
        Result result = activityService.deleteBizActivity(bizActivityId);
        return result;
    }

    /**
     * 添加楼层
     */
    @PostMapping("/bizActivity/")
    public Result addBizActivity(@RequestBody BizActivity bizActivity){
        Result result = activityService.addBizActivity(bizActivity);
        return result;
    }

    /**
     * 上传logo
     */
    public Result uploadLogo(){
        return null;
    }
}
