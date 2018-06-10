package com.whxm.harbor.business.controller;

import com.whxm.harbor.bean.BizFormat;
import com.whxm.harbor.business.service.BusinessFormatService;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Result getBizFormats(PageQO<BizFormat> pageQO, BizFormat condition) {
        Result ret = null;

        try {
            pageQO.setCondition(condition);

            PageVO<BizFormat> pageVO = businessFormatService.getBizFormatList(pageQO);

            ret = new Result(pageVO);

        } catch (Exception e) {

            logger.error("业态列表 获取报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "业态列表 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("获取业态")
    @GetMapping("/bizFormat/{ID}")
    public Result getBizFormat(@PathVariable("ID") Integer bizFormatId) {
        Result ret = null;

        try {
            BizFormat bizFormat = businessFormatService.getBizFormat(bizFormatId);

            ret = new Result(bizFormat);

        } catch (Exception e) {

            logger.error("ID为{}的业态数据 获取报错", bizFormatId, e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ID为" + bizFormatId + "的业态数据 获取报错", null);
        }

        return ret;
    }

    @ApiOperation("新增业态")
    @PostMapping("/bizFormat/")
    public Result addBizFormat(@RequestBody BizFormat bizFormat) {
        Result ret = null;
        try {
            ret = businessFormatService.addBizFormat(bizFormat);

        } catch (Exception e) {

            logger.error("业态数据 添加报错", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "业态数据 添加报错", null);

        }
        return ret;
    }

    @ApiOperation("修改业态")
    @PutMapping("/bizFormat/")
    public Result updateBizFormat(@RequestBody BizFormat bizFormat) {
        Result ret = null;
        try {
            ret = businessFormatService.updateBizFormat(bizFormat);

        } catch (Exception e) {

            logger.error("", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "业态数据 修改报错", null);
        }
        return ret;
    }

    @ApiOperation("删除业态")
    @DeleteMapping("/bizFormat/{ID}")
    public Result delBizFormat(@ApiParam(name = "ID", value = "业态ID", required = true)
                               @PathVariable("ID") Integer bizFormatId) {
        Result ret = null;
        try {
            ret = businessFormatService.deleteBizFormat(bizFormatId);
        } catch (Exception e) {

            logger.error("", e);

            ret = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "业态数据 删除报错", null);
        }
        return ret;
    }
}
