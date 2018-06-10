package com.whxm.harbor.bean;

import java.util.Date;

public class BizScreensaver {
    private Integer screensaverId;

    private String screensaverProgramName;

    private Date addScreensaverTime;

    //join中间表screensaver_material_relation查询该屏保的素材数量
    private Integer screensaverMaterialAmount;

    //join中间表screensaver_published_terminal_relation查询该屏保的发布终端数量
    private Integer screensaverPublishedAmount;

    public Integer getScreensaverPublishedAmount() {
        return screensaverPublishedAmount;
    }

    public void setScreensaverPublishedAmount(Integer screensaverPublishedAmount) {
        this.screensaverPublishedAmount = screensaverPublishedAmount;
    }

    public Integer getScreensaverId() {
        return screensaverId;
    }

    public void setScreensaverId(Integer screensaverId) {
        this.screensaverId = screensaverId;
    }

    public String getScreensaverProgramName() {
        return screensaverProgramName;
    }

    public void setScreensaverProgramName(String screensaverProgramName) {
        this.screensaverProgramName = screensaverProgramName == null ? null : screensaverProgramName.trim();
    }

    public Integer getScreensaverMaterialAmount() {
        return screensaverMaterialAmount;
    }

    public void setScreensaverMaterialAmount(Integer screensaverMaterialAmount) {
        this.screensaverMaterialAmount = screensaverMaterialAmount;
    }

    public Date getAddScreensaverTime() {
        return addScreensaverTime;
    }

    public void setAddScreensaverTime(Date addScreensaverTime) {
        this.addScreensaverTime = addScreensaverTime;
    }
}