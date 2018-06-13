package com.whxm.harbor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BizActivity {
    private Integer activityId;

    private String activityLogo;

    private Long activityLogoSize;

    private String activityType;

    @JsonIgnore
    private Integer isDeleted;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityLogo() {
        return activityLogo;
    }

    public void setActivityLogo(String activityLogo) {
        this.activityLogo = activityLogo == null ? null : activityLogo.trim();
    }

    public Long getActivityLogoSize() {
        return activityLogoSize;
    }

    public void setActivityLogoSize(Long activityLogoSize) {
        this.activityLogoSize = activityLogoSize;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}