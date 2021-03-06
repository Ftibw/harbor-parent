package com.whxm.harbor.screensaver.material.service.impl;

import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizScreensaverMaterial;
import com.whxm.harbor.mapper.BizScreensaverMaterialMapper;
import com.whxm.harbor.screensaver.material.service.ScreensaverMaterialService;
import com.whxm.harbor.common.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ScreensaverMaterialServiceImpl implements ScreensaverMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(ScreensaverMaterialServiceImpl.class);

    @Resource
    private BizScreensaverMaterialMapper bizScreensaverMaterialMapper;

    @Override
    public BizScreensaverMaterial getBizScreensaverMaterial(Integer bizScreensaverMaterialId) {

        BizScreensaverMaterial screensaverMaterial = null;

        try {
            screensaverMaterial = bizScreensaverMaterialMapper.selectByPrimaryKey(bizScreensaverMaterialId);

            if (null == screensaverMaterial) {
                logger.error("错误屏保素材ID", screensaverMaterial);
            }
        } catch (Exception e) {

            logger.error("屏保素材ID为{}的数据 获取报错", bizScreensaverMaterialId);

            throw new RuntimeException();
        }

        return screensaverMaterial;
    }

    @Override
    public List<BizScreensaverMaterial> getBizScreensaverMaterialList() {

        List<BizScreensaverMaterial> screensaverMaterialList = null;
        try {
            PageHelper.startPage(0, 1);

            screensaverMaterialList = bizScreensaverMaterialMapper.getBizScreensaverMaterialList();
        } catch (Exception e) {
            logger.error("屏保素材列表 获取报错", e);

            throw new RuntimeException();
        }

        return screensaverMaterialList;
    }

    @Override
    public Result deleteBizScreensaverMaterial(Integer bizScreensaverMaterialId) {
        Result ret = null;

        try {

            bizScreensaverMaterialMapper.deleteByPrimaryKey(bizScreensaverMaterialId);

            logger.info("ID为{}的屏保素材 删除成功", bizScreensaverMaterialId);

            ret = new Result("删除成功");

        } catch (Exception e) {

            logger.error("屏保素材ID为{}的数据 删除错误", bizScreensaverMaterialId);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizScreensaverMaterial(BizScreensaverMaterial bizScreensaverMaterial) {
        Result ret = null;

        try {

            int affectRow = bizScreensaverMaterialMapper.updateByPrimaryKeySelective(bizScreensaverMaterial);

            ret = new Result("屏保素材数据修改了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("屏保素材数据 修改报错", e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result addBizScreensaverMaterial(BizScreensaverMaterial bizScreensaverMaterial) {
        Result ret = null;

        try {
            int affectRow = bizScreensaverMaterialMapper.insert(bizScreensaverMaterial);

            ret = new Result("屏保素材数据添加了" + affectRow + "行");
        } catch (Exception e) {

            logger.error("屏保素材数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
