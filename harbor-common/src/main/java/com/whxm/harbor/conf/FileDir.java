package com.whxm.harbor.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileDir {

    @Value("${activityLogoDir}")
    private String activityLogoDir;
    @Value("${activityMaterialImgDir}")
    private String activityMaterialImgDir;
    @Value("${shopLogoDir}")
    private String shopLogoDir;
    @Value("${shopPictureDir}")
    private String shopPictureDir;
    @Value("${screensaverMaterialImgDir}")
    private String screensaverMaterialImgDir;

    public String getActivityLogoDir() {
        return activityLogoDir;
    }

    public String getActivityMaterialImgDir() {
        return activityMaterialImgDir;
    }

    public String getShopLogoDir() {
        return shopLogoDir;
    }

    public String getShopPictureDir() {
        return shopPictureDir;
    }

    public String getScreensaverMaterialImgDir() {
        return screensaverMaterialImgDir;
    }
}
