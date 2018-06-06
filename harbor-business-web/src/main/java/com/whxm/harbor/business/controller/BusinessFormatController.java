package com.whxm.harbor.business.controller;

import com.whxm.harbor.bean.BizFormat;
import com.whxm.harbor.business.service.BusinessFormatService;
import com.whxm.harbor.common.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("获取业态列表")
    @GetMapping("/bizFormats")
    public Result getBizFormats() {
        List<BizFormat> bizFormatList = businessFormatService.getBizFormatList();
        Result ret = new Result();
        ret.setData(bizFormatList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    @ApiOperation(value = "根据首字母获取业态列表", notes = "首字母不区分大小写")
    @GetMapping("/bizFormats/{keyword}")
    public Result getBizFormats(@PathVariable("keyword") String keyword) {
        List<BizFormat> bizFormatList = businessFormatService.getBizFormatByNumPrefix(keyword);
        Result ret = new Result();
        ret.setData(bizFormatList);
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    @ApiOperation("新增业态")
    @PostMapping("/bizFormat/")
    public Result addBizFormat(@RequestBody BizFormat bizFormat) {
        Result result = businessFormatService.addBizFormat(bizFormat);
        return result;
    }

    @ApiOperation("修改业态")
    @PutMapping("/bizFormat/")
    public Result updateBizFormat(@RequestBody BizFormat bizFormat) {
        Result result = businessFormatService.updateBizFormat(bizFormat);
        return result;
    }

    @ApiOperation("删除业态")
    @DeleteMapping("/bizFormat/{ID}")
    public Result delBizFormat(@ApiParam(name = "ID", value = "业态ID", required = true)
                               @PathVariable("ID") String bizFormatId) {
        Result result = businessFormatService.deleteBizFormat(bizFormatId);
        return result;
    }
}
