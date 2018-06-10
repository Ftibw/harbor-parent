package com.whxm.harbor.mapper;

import com.whxm.harbor.bean.BizScreensaver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizScreensaverMapper {
    int deleteByPrimaryKey(Integer screensaverId);

    int insert(BizScreensaver record);

    int insertSelective(BizScreensaver record);

    BizScreensaver selectByPrimaryKey(Integer screensaverId);

    int updateByPrimaryKeySelective(BizScreensaver record);

    int updateByPrimaryKey(BizScreensaver record);

    List<BizScreensaver> getBizScreensaverList();

    int insertScreensaverMaterials(
            @Param("screensaverId") Integer screensaverId,
            @Param("screensaverMaterialIds") Integer[] screensaverMaterialIds
    );


    int insertScreensaverPublishedTerminal(
            @Param("screensaverId") Integer screensaverId,
            @Param("terminalIds") String[] terminalIds
    );
}