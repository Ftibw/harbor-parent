package com.whxm.harbor.business.service.impl;

import com.whxm.harbor.bean.BizFormat;
import com.whxm.harbor.business.service.BusinessFormatService;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizFormatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BusinessFormatServiceImpl implements BusinessFormatService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessFormatServiceImpl.class);

    @Resource
    private BizFormatMapper bizFormatMapper;

    @Override
    public BizFormat getBizFormat(String bizFormatId) {
        Assert.notNull(bizFormatId,"业态ID不能为空");
        return bizFormatMapper.selectByPrimaryKey(bizFormatId);
    }

    public List<BizFormat> getBizFormatList() {
        return bizFormatMapper.getBizFormatList();
    }

    @Override
    public Result deleteBizFormat(String bizFormatId) {
        Assert.notNull(bizFormatId,"业态ID不能为空");

        //StringUtils.isNotBlank(bizFormatId)
        BizFormat bizFormat = new BizFormat();
        bizFormat.setBizFormatId(bizFormatId);
        bizFormat.setIsDeleted(0);
        updateBizFormat(bizFormat);
        Result ret = new Result("void");
        ret.setMsg("ok");
        ret.setStatus(200);
        return ret;
    }

    @Override
    public Result updateBizFormat(BizFormat bizFormat) {

        Assert.notNull(bizFormat,"业态不能为空");
        Assert.notNull(bizFormat.getBizFormatId(),"业态ID不能为空");
        int affectRow = bizFormatMapper.updateByPrimaryKeySelective(bizFormat);

        Result ret = new Result();
        ret.setStatus(200);
        ret.setMsg("ok");
        ret.setData("");
        return ret;
    }

    @Override
    public List<BizFormat> getBizFormatByNumPrefix(String keyword) {
        Assert.notNull(keyword,"关键字不能为空");
        List<BizFormat> bizFormats = bizFormatMapper.getByNumPrefix(keyword);
        return bizFormats;
    }

    @Override
    public Result addBizFormat(BizFormat bizFormat) {
        int affectRow = bizFormatMapper.insert(bizFormat);
        Result ret = new Result();
        ret.setStatus(200);
        ret.setMsg("ok");
        ret.setData("成功添加"+affectRow+"行记录");
        return ret;
    }
}
