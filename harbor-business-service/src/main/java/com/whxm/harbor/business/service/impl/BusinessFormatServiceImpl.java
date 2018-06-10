package com.whxm.harbor.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizFormat;
import com.whxm.harbor.business.service.BusinessFormatService;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizFormatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        BizFormat bizFormat = null;

        try {
            bizFormat = bizFormatMapper.selectByPrimaryKey(bizFormatId);

        } catch (Exception e) {

            logger.error("ID为{}的业态数据 获取报错", bizFormatId);

            throw new RuntimeException();
        }

        return bizFormat;
    }

    public PageVO<BizFormat> getBizFormatList(PageQO<BizFormat> pageQO) {


        PageVO<BizFormat> pageVO = null;
        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(bizFormatMapper.getBizFormatList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());

        } catch (Exception e) {

            logger.error("业态数据列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public Result deleteBizFormat(Integer bizFormatId) {

        //StringUtils.isNotBlank(bizFormatId)

        Result ret = null;
        try {
            BizFormat bizFormat = new BizFormat();

            bizFormat.setBizFormatId(bizFormatId);

            bizFormat.setIsDeleted(0);

            updateBizFormat(bizFormat);

            ret = new Result("ID为" + bizFormatId + "的业态数据删除成功");

        } catch (Exception e) {

            logger.error("ID为{}的业态 删除报错", bizFormatId);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizFormat(BizFormat bizFormat) {

        Result ret = null;

        if (null == bizFormat) {

            logger.info("业态为空");

            return ret = new Result("业态为空");

        } else if (null == bizFormat.getBizFormatId()) {

            logger.info("业态ID为空");

            return ret = new Result("业态ID为空");
        }

        try {
            int affectRow = bizFormatMapper.updateByPrimaryKeySelective(bizFormat);

            ret = new Result("ID为" + bizFormat.getBizFormatId() + "的业态 修改成功 影响了" + affectRow + "行");

        } catch (Exception e) {

            logger.error("ID为{}的业态 修改报错", bizFormat.getBizFormatId());

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizFormat(BizFormat bizFormat) {

        Result ret = null;

        try {
            int affectRow = bizFormatMapper.insert(bizFormat);

            ret = new Result("成功添加" + affectRow + "行记录");

        } catch (Exception e) {

            logger.error("业态数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
