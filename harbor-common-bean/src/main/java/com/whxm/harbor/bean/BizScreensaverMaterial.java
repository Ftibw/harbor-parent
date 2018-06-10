package com.whxm.harbor.bean;

public class BizScreensaverMaterial {
    private Integer screensaverMaterialId;

    private String screensaverMaterialImgPath;

    private Long screensaverMaterialSize;

    private String screensaverMaterialName;

    private String screensaverMaterialType;

    public Integer getScreensaverMaterialId() {
        return screensaverMaterialId;
    }

    public void setScreensaverMaterialId(Integer screensaverMaterialId) {
        this.screensaverMaterialId = screensaverMaterialId;
    }

    public String getScreensaverMaterialImgPath() {
        return screensaverMaterialImgPath;
    }

    public void setScreensaverMaterialImgPath(String screensaverMaterialImgPath) {
        this.screensaverMaterialImgPath = screensaverMaterialImgPath == null ? null : screensaverMaterialImgPath.trim();
    }

    public Long getScreensaverMaterialSize() {
        return screensaverMaterialSize;
    }

    public void setScreensaverMaterialSize(Long screensaverMaterialSize) {
        this.screensaverMaterialSize = screensaverMaterialSize;
    }

    public String getScreensaverMaterialName() {
        return screensaverMaterialName;
    }

    public void setScreensaverMaterialName(String screensaverMaterialName) {
        this.screensaverMaterialName = screensaverMaterialName == null ? null : screensaverMaterialName.trim();
    }

    public String getScreensaverMaterialType() {
        return screensaverMaterialType;
    }

    public void setScreensaverMaterialType(String screensaverMaterialType) {
        this.screensaverMaterialType = screensaverMaterialType == null ? null : screensaverMaterialType.trim();
    }
}