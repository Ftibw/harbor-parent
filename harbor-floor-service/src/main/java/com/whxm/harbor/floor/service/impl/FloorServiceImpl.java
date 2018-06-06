package com.whxm.harbor.floor.service.impl;

import com.whxm.harbor.bean.BizFloor;
import com.whxm.harbor.common.bean.Result;
import com.whxm.harbor.floor.service.FloorService;
import com.whxm.harbor.mapper.BizFloorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {
    @Resource
    private BizFloorMapper bizFloorMapper;

    @Override
    public BizFloor getBizFloor(Integer bizFloorId) {

        BizFloor bizFloor = bizFloorMapper.selectByPrimaryKey(bizFloorId);

        return bizFloor;
    }

    @Override
    public List<BizFloor> getBizFloorList() {

        List<BizFloor> floorList = bizFloorMapper.getBizFloorList();

        return floorList;
    }

    @Override
    public Result deleteBizFloor(Integer bizFloorId) {

        int affectRow = bizFloorMapper.deleteByPrimaryKey(bizFloorId);

        return null;
    }

    @Override
    public Result updateBizFloor(BizFloor bizFloor) {

        int affectRow = bizFloorMapper.updateByPrimaryKeySelective(bizFloor);

        return null;
    }

    @Override
    public Result addBizFloor(BizFloor bizFloor) {

        int affectRow = bizFloorMapper.insert(bizFloor);

        return null;
    }
}
