package com.whxm.harbor.floor.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whxm.harbor.bean.BizFloor;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;
import com.whxm.harbor.floor.service.FloorService;
import com.whxm.harbor.mapper.BizFloorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    private static final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    @Resource
    private BizFloorMapper bizFloorMapper;

    @Override
    public BizFloor getBizFloor(Integer bizFloorId) {

        BizFloor bizFloor = null;

        try {
            bizFloor = bizFloorMapper.selectByPrimaryKey(bizFloorId);

        } catch (Exception e) {

            logger.error("ID为{}的楼层 获取报错", bizFloorId, e);

            throw new RuntimeException();
        }

        return bizFloor;
    }

    @Override
    public PageVO<BizFloor> getBizFloorList(PageQO<BizFloor> pageQO) {

        PageVO<BizFloor> pageVO = null;

        try {
            Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());

            pageVO = new PageVO<>(pageQO);

            pageVO.setList(bizFloorMapper.getBizFloorList(pageQO.getCondition()));

            pageVO.setTotal(page.getTotal());
        } catch (Exception e) {

            logger.error("楼层列表获取报错", e);

            throw new RuntimeException();
        }

        return pageVO;
    }

    @Override
    public List<BizFloor> getBizFloorList() {

        List<BizFloor> list = null;

        try {
            list = bizFloorMapper.getBizFloorList(null);

        } catch (Exception e) {

            logger.error("楼层数据列表 获取报错", e);

            throw new RuntimeException();
        }

        return list;
    }

    @Override
    public Result deleteBizFloor(Integer bizFloorId) {
        Result ret = null;

        try {

            bizFloorMapper.deleteByPrimaryKey(bizFloorId);

            ret = new Result("ID为" + bizFloorId + "的楼层 删除成功");

        } catch (Exception e) {

            logger.error("ID为{}的楼层 删除报错", bizFloorId, e);

            throw new RuntimeException();
        }

        return ret;
    }

    @Override
    public Result updateBizFloor(BizFloor bizFloor) {
        Result ret = null;

        if (null == bizFloor) {
            return new Result("楼层数据为空");
        } else if (null == bizFloor.getFloorId()) {
            return new Result("该楼层不存在");
        }
        try {
            bizFloorMapper.updateByPrimaryKeySelective(bizFloor);

            ret = new Result("ID为" + bizFloor.getFloorId() + "的楼层 修改成功");

        } catch (Exception e) {

            logger.error("ID为{}的楼层修改 报错", bizFloor.getFloorId(), e);
        }

        return null;
    }

    @Override
    public Result addBizFloor(BizFloor bizFloor) {
        Result ret = null;

        try {
            int affectRow = bizFloorMapper.insert(bizFloor);

            ret = new Result("楼层数据 成功添加" + affectRow + "条数据");

        } catch (Exception e) {

            logger.error("楼层数据 添加报错", e);

            throw new RuntimeException();
        }

        return ret;
    }
}
