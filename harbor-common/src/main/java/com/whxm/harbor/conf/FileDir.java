package com.whxm.harbor.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * spring boot配置文件扫描顺序,
 * 如果在不同的目录中存在多个配置文件，它的读取顺序是：
 * 1、config/application.properties（项目根目录中config目录下）
 * 2、config/application.properties
 * 3、application.properties（项目根目录下）
 * 4、application.properties
 * 5、resources/config/application.properties（项目resources目录中config目录下）
 * 6、resources/config/application.properties
 * 7、resources/application.properties（项目的resources目录下）
 * 8、resources/application.properties
 * 注：
 * 1、如果同一个目录下，有application.yml也有application.properties，默认先读取application.properties。
 * 2、如果同一个配置属性，在多个配置文件都配置了，默认使用第1个读取到的，后面读取的不覆盖前面读取到的。
 * 3、创建SpringBoot项目时，一般的配置文件放置在“项目的resources目录下”
 */
@Configuration
public class FileDir {
    //属性不需要getter和setter,这里只是为了构成不变类
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
