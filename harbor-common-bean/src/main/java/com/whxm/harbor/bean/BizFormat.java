package com.whxm.harbor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BizFormat {
    private String bizFormatId;

    private String bizFormatNumber;

    private String bizFormatType;

    @JsonIgnore
    private Integer isDeleted;

    public String getBizFormatId() {
        return bizFormatId;
    }

    public void setBizFormatId(String bizFormatId) {
        this.bizFormatId = bizFormatId == null ? null : bizFormatId.trim();
    }

    public String getBizFormatNumber() {
        return bizFormatNumber;
    }

    public void setBizFormatNumber(String bizFormatNumber) {
        this.bizFormatNumber = bizFormatNumber == null ? null : bizFormatNumber.trim();
    }

    public String getBizFormatType() {
        return bizFormatType;
    }

    public void setBizFormatType(String bizFormatType) {
        this.bizFormatType = bizFormatType == null ? null : bizFormatType.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}