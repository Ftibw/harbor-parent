package com.whxm.harbor.bean;

public class BizActivityMaterial {
    private Integer activityMaterialId;

    private String activityMaterialImgPath;

    private String activityMaterialImgName;

    private String activityMaterialName;

    private Long activityMaterialSize;

    private Integer activityId;

    //join activity 表查询 activity_type
    private String activityType;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityMaterialId() {
        return activityMaterialId;
    }

    public void setActivityMaterialId(Integer activityMaterialId) {
        this.activityMaterialId = activityMaterialId;
    }

    public String getActivityMaterialImgPath() {
        return activityMaterialImgPath;
    }

    public void setActivityMaterialImgPath(String activityMaterialImgPath) {
        this.activityMaterialImgPath = activityMaterialImgPath == null ? null : activityMaterialImgPath.trim();
    }

    public String getActivityMaterialImgName() {
        return activityMaterialImgName;
    }

    public void setActivityMaterialImgName(String activityMaterialImgName) {
        this.activityMaterialImgName = activityMaterialImgName == null ? null : activityMaterialImgName.trim();
    }

    public String getActivityMaterialName() {
        return activityMaterialName;
    }

    public void setActivityMaterialName(String activityMaterialName) {
        this.activityMaterialName = activityMaterialName == null ? null : activityMaterialName.trim();
    }

    public Long getActivityMaterialSize() {
        return activityMaterialSize;
    }

    public void setActivityMaterialSize(Long activityMaterialSize) {
        this.activityMaterialSize = activityMaterialSize;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}