package com.whxm.harbor.screensaver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.common.bean.PageQO;
import com.whxm.harbor.common.bean.PageVO;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizScreensaverMapper;
import com.whxm.harbor.screensaver.service.ScreensaverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScreensaverServiceImpl implements ScreensaverService {

    private static final Logger logger = LoggerFactory.getLogger(ScreensaverServiceImpl.class);

    @Resource
    private BizScreensaverMapper bizScreensaverMapper;

    @Override
    public BizScreensaver getBizScreensaver(Integer bizScreensaverId) {

        BizScreensaver screensaver = null;

        try {
            screensaver = bizScreensaverMapper.selectWithScreensaverMaterialAndPublishedTerminalAmount(bizScreensaverId);

            if (null == screensaver) {
                logger.error("错误屏保ID", screensaver);
            }
        } catch (Exception e) {

            logger.error("屏保ID为{}的数据 获取报错", bizScreensaverId);

            throw new RuntimeException();
        }

        return screensaver;
    }

    @Override
    public PageVO<BizScreensaver> getBizScreensaverList(PageQO<BizScreensaver> pageQO) {

        PageVO<BizScreensaver> pageVO = null;

        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(bizScreensaverMapper.getBizScreensaverList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());
        } catch (Exception e) {
            logger.error("屏保列表 获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public Result deleteBizScreensaver(Integer bizScreensaverId) {
        Result ret = null;

        try {
            //删除屏保,先删屏保-屏保素材关系表,再删主表
            int affectRow1 = bizScreensaverMapper.delScreensaverMaterialRelation(bizScreensaverId);

            int affectRow2 = bizScreensaverMapper.deleteByPrimaryKey(bizScreensaverId);

            logger.info("ID为{}的屏保 删除成功{}行,屏保-屏保素材关系表删除成功{}行",
                    bizScreensaverId, affectRow2, affectRow1);

            ret = new Result("ID为" + bizScreensaverId + "的屏保 删除成功" + affectRow2 + "行,屏保-屏保素材关系表删除成功" + affectRow1 + "行");

        } catch (Exception e) {

            logger.error("屏保ID为{}的数据 删除错误", bizScreensaverId);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizScreensaver(BizScreensaver bizScreensaver) {
        Result ret = null;

        try {

            int affectRow = bizScreensaverMapper.updateByPrimaryKeySelective(bizScreensaver);

            ret = new Result("屏保数据修改了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("屏保数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizScreensaver(BizScreensaver bizScreensaver, Integer[] screensaverMaterialIds) {
        Result ret = null;

        try {
            bizScreensaver.setAddScreensaverTime(new Date());

            int affectRow1 = bizScreensaverMapper.insert(bizScreensaver);

            System.out.println("影响了" + affectRow1 + "行,新增数据的主键为" + bizScreensaver.getScreensaverId());

            int affectRow2 = bizScreensaverMapper.insertScreensaverMaterials(bizScreensaver.getScreensaverId(), screensaverMaterialIds);

            ret = new Result("屏保数据添加了" + affectRow1 + "行,该屏保添加了" + affectRow2 + "个素材");

        } catch (Exception e) {

            logger.error("屏保数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result publishScreensaver(Integer screensaverId, String[] terminalIds) {
        Result ret = null;

        try {
            //发布屏保,就是向屏保-发布的终端关系表中插入数据
            int affectRow = bizScreensaverMapper.insertScreensaverPublishedTerminal(screensaverId, terminalIds, new Date());

            ret = new Result("成功发布终端" + affectRow + "个");

        } catch (Exception e) {

            logger.error("ID为{}的屏保 发布报错", screensaverId, e);

            throw new RuntimeException();
        }

        return ret;
    }
}
