package com.whxm.harbor.business.controller;

import com.whxm.harbor.bean.BizFormat;
import com.whxm.harbor.business.service.BusinessFormatService;
import com.whxm.harbor.common.bean.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "API - BusinessFormatController", description = "业态 Controller")
@RestController
public class BusinessFormatController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessFormatController.class);

    @Autowired
    private BusinessFormatService businessFormatService;

    @GetMapping("/bizFormats")
    public Result getBizFormats(){
        List<BizFormat> bizFormatList = businessFormatService.getBizFormatList();
        Result ret = new Result();
        ret.setData(bizFormatList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    @GetMapping("/bizFormats/{keyword}")
    public Result getBizFormats(@PathVariable("keyword") String keyword){
        List<BizFormat> bizFormatList = businessFormatService.getBizFormatByNumPrefix(keyword);
        Result ret = new Result();
        ret.setData(bizFormatList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }


    @PostMapping("/bizFormat/")
    public Result addBizFormat(@RequestBody BizFormat bizFormat){
        Result result = businessFormatService.addBizFormat(bizFormat);
        return result;
    }

    @PutMapping("/bizFormat/")
    public Result updateBizFormat(@RequestBody BizFormat bizFormat){
        Result result = businessFormatService.updateBizFormat(bizFormat);
        return result;
    }

    @DeleteMapping("/bizFormat/{ID}")
    public Result delBizFormat(@PathVariable("ID") String bizFormatId){
        Result result = businessFormatService.deleteBizFormat(bizFormatId);
        return result;
    }
}
