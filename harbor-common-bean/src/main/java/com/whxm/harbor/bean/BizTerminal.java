package com.whxm.harbor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class BizTerminal {
    private String terminalId;

    private String terminalName;

    private String terminalNumber;

    private String terminalIp;

    private String terminalType;

    private String terminalLocation;

    private Integer terminalRotationAngle;

    private Integer isTerminalOnline;

    @JsonIgnore
    private Integer isDeleted;

    private Date addTerminalTime;

    private Date registerTerminalTime;

    private Integer floorId;

    private Date terminalSwitchTime;

    private Integer terminalPlatform;

    //join biz_floor 查询终端所在楼层名
    private String floorName;

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName == null ? null : terminalName.trim();
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp == null ? null : terminalIp.trim();
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }

    public String getTerminalLocation() {
        return terminalLocation;
    }

    public void setTerminalLocation(String terminalLocation) {
        this.terminalLocation = terminalLocation == null ? null : terminalLocation.trim();
    }

    public Integer getTerminalRotationAngle() {
        return terminalRotationAngle;
    }

    public void setTerminalRotationAngle(Integer terminalRotationAngle) {
        this.terminalRotationAngle = terminalRotationAngle;
    }

    public Integer getIsTerminalOnline() {
        return isTerminalOnline;
    }

    public void setIsTerminalOnline(Integer isTerminalOnline) {
        this.isTerminalOnline = isTerminalOnline;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTerminalTime() {
        return addTerminalTime;
    }

    public void setAddTerminalTime(Date addTerminalTime) {
        this.addTerminalTime = addTerminalTime;
    }

    public Date getRegisterTerminalTime() {
        return registerTerminalTime;
    }

    public void setRegisterTerminalTime(Date registerTerminalTime) {
        this.registerTerminalTime = registerTerminalTime;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Date getTerminalSwitchTime() {
        return terminalSwitchTime;
    }

    public void setTerminalSwitchTime(Date terminalSwitchTime) {
        this.terminalSwitchTime = terminalSwitchTime;
    }

    public Integer getTerminalPlatform() {
        return terminalPlatform;
    }

    public void setTerminalPlatform(Integer terminalPlatform) {
        this.terminalPlatform = terminalPlatform;
    }
}