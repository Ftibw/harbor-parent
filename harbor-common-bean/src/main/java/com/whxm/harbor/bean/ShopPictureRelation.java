package com.whxm.harbor.bean;

public class ShopPictureRelation {
    private String shopId;

    private String shopPicturePath;

    private Long shopPictureSize;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopPicturePath() {
        return shopPicturePath;
    }

    public void setShopPicturePath(String shopPicturePath) {
        this.shopPicturePath = shopPicturePath == null ? null : shopPicturePath.trim();
    }

    public Long getShopPictureSize() {
        return shopPictureSize;
    }

    public void setShopPictureSize(Long shopPictureSize) {
        this.shopPictureSize = shopPictureSize;
    }
}