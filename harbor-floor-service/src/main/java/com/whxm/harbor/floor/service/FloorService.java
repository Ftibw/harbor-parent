package com.whxm.harbor.floor.service;

import com.whxm.harbor.bean.BizFloor;
import com.whxm.harbor.bean.PageQO;
import com.whxm.harbor.bean.PageVO;
import com.whxm.harbor.bean.Result;

/**
 * 楼层服务
 */
public interface FloorService {
    /**
     * 根据楼层ID获取楼层数据
     *
     * @param bizFloorId 楼层ID
     * @return 楼层数据
     */
    BizFloor getBizFloor(Integer bizFloorId);

    /**
     * 获取楼层列表
     *
     * @return list
     * @param pageQO
     */
    PageVO<BizFloor> getBizFloorList(PageQO<BizFloor> pageQO);

    /**
     * 根据ID删除楼层
     *
     * @param bizFloorId 楼层ID
     * @return ret
     */
    Result deleteBizFloor(Integer bizFloorId);

    /**
     * 修改楼层数据
     *
     * @param bizFloor 楼层数据新值
     * @return ret
     */
    Result updateBizFloor(BizFloor bizFloor);

    /**
     * 新增楼层数据
     * @param bizFloor  新楼层数据
     * @return  添加操作结果
     */
    Result addBizFloor(BizFloor bizFloor);
}
