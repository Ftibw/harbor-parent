package com.whxm.harbor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BizFormat {
    private Integer bizFormatId;

    private String bizFormatNumber;

    private String bizFormatType;

    @JsonIgnore
    private Integer isDeleted;

    public Integer getBizFormatId() {
        return bizFormatId;
    }

    public void setBizFormatId(Integer bizFormatId) {
        this.bizFormatId = bizFormatId;
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