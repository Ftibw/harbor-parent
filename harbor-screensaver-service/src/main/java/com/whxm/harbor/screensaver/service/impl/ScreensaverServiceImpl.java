package com.whxm.harbor.screensaver.service.impl;

import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizScreensaver;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.mapper.BizScreensaverMapper;
import com.whxm.harbor.screensaver.service.ScreensaverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
            screensaver = bizScreensaverMapper.selectByPrimaryKey(bizScreensaverId);

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
    public List<BizScreensaver> getBizScreensaverList() {

        List<BizScreensaver> screensaverList = null;
        try {
            PageHelper.startPage(0, 1);

            screensaverList = bizScreensaverMapper.getBizScreensaverList();
        } catch (Exception e) {
            logger.error("屏保列表 获取报错", e);

            throw new RuntimeException();
        }

        return screensaverList;
    }

    @Override
    public Result deleteBizScreensaver(Integer bizScreensaverId) {
        Result ret = null;

        try {

            bizScreensaverMapper.deleteByPrimaryKey(bizScreensaverId);

            logger.info("ID为{}的屏保 删除成功", bizScreensaverId);

            ret = new Result("删除成功");

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
            int affectRow1 = bizScreensaverMapper.insert(bizScreensaver);

            System.out.println("影响了" + affectRow1 + "行,新增数据的主键为" + bizScreensaver.getScreensaverId());

            int affectRow2 = bizScreensaverMapper.insertScreensaverMaterials(bizScreensaver.getScreensaverId(), screensaverMaterialIds);

            ret = new Result("屏保数据添加了" + affectRow1 + "行,该屏保添加了"+affectRow2+"个素材");

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
            int affectRow = bizScreensaverMapper.insertScreensaverPublishedTerminal(screensaverId, terminalIds);

            ret=new Result("成功发布终端"+affectRow+"个");

        } catch (Exception e) {

            logger.error("ID为{}的屏保 发布报错", screensaverId);

            throw new RuntimeException();
        }

        return ret;
    }
}
