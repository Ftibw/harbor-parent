package com.whxm.harbor.bean;


import java.util.Date;

public class BizShop {

    private String shopId;

    private String shopNumber;

    private String shopName;

    private String shopEnglishName;

    private Integer floorId;

    private String bizFormatId;

    private String shopHouseNumber;

    private Integer isShopEnabled;

    private String shopLogoPath;

    private String shopTel;

    private Date addShopTime;

    private Date shopCheckinTime;

    private Integer shopWeight;

    private String shopDescript;

    //join biz_floor
    private String floorName;

    //join biz_format
    private String bizFormatType;

    public String getBizFormatType() {
        return bizFormatType;
    }

    public void setBizFormatType(String bizFormatType) {
        this.bizFormatType = bizFormatType;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber == null ? null : shopNumber.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopEnglishName() {
        return shopEnglishName;
    }

    public void setShopEnglishName(String shopEnglishName) {
        this.shopEnglishName = shopEnglishName == null ? null : shopEnglishName.trim();
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getBizFormatId() {
        return bizFormatId;
    }

    public void setBizFormatId(String bizFormatId) {
        this.bizFormatId = bizFormatId == null ? null : bizFormatId.trim();
    }

    public String getShopHouseNumber() {
        return shopHouseNumber;
    }

    public void setShopHouseNumber(String shopHouseNumber) {
        this.shopHouseNumber = shopHouseNumber == null ? null : shopHouseNumber.trim();
    }

    public Integer getIsShopEnabled() {
        return isShopEnabled;
    }

    public void setIsShopEnabled(Integer isShopEnabled) {
        this.isShopEnabled = isShopEnabled;
    }

    public String getShopLogoPath() {
        return shopLogoPath;
    }

    public void setShopLogoPath(String shopLogoPath) {
        this.shopLogoPath = shopLogoPath == null ? null : shopLogoPath.trim();
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel == null ? null : shopTel.trim();
    }

    public Date getAddShopTime() {
        return addShopTime;
    }

    public void setAddShopTime(Date addShopTime) {
        this.addShopTime = addShopTime;
    }

    public Date getShopCheckinTime() {
        return shopCheckinTime;
    }

    public void setShopCheckinTime(Date shopCheckinTime) {
        this.shopCheckinTime = shopCheckinTime;
    }

    public Integer getShopWeight() {
        return shopWeight;
    }

    public void setShopWeight(Integer shopWeight) {
        this.shopWeight = shopWeight;
    }

    public String getShopDescript() {
        return shopDescript;
    }

    public void setShopDescript(String shopDescript) {
        this.shopDescript = shopDescript == null ? null : shopDescript.trim();
    }
}