package com.whxm.harbor.business.service.impl;

import com.github.pagehelper.PageHelper;
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
    public BizFormat getBizFormat(Integer bizFormatId) {

        return bizFormatMapper.selectByPrimaryKey(bizFormatId);
    }

    public List<BizFormat> getBizFormatList() {

        PageHelper.startPage(0, 1);

        return bizFormatMapper.getBizFormatList();
    }

    @Override
    public Result deleteBizFormat(Integer bizFormatId) {

        //StringUtils.isNotBlank(bizFormatId)

        BizFormat bizFormat = new BizFormat();

        bizFormat.setBizFormatId(bizFormatId);

        bizFormat.setIsDeleted(0);

        updateBizFormat(bizFormat);

        Result ret = new Result(200,"ok","void");

        return ret;
    }

    @Override
    public Result updateBizFormat(BizFormat bizFormat) {

        int affectRow = bizFormatMapper.updateByPrimaryKeySelective(bizFormat);

        Result ret = new Result(200,"ok","");

        return ret;
    }

/*    @Override
    public List<BizFormat> getBizFormatByNumPrefix(String keyword) {

        List<BizFormat> bizFormats = bizFormatMapper.getByNumPrefix(keyword);

        return bizFormats;
    }*/

    @Override
    public Result addBizFormat(BizFormat bizFormat) {

        int affectRow = bizFormatMapper.insert(bizFormat);

        Result ret = new Result(200,"ok","成功添加"+affectRow+"行记录");

        return ret;
    }
}
