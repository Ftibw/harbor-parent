package com.whxm.harbor.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 2018/6/7 by Ftibw
 */
public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);

    public static String upload(MultipartFile file, HttpServletRequest request, String uploadRootDir) throws IOException {
        //文件名称
        String originName = file.getOriginalFilename();
        //文件大小
        //Long size = file.getSize();
        //uuid生成新名称
        String newName = StringUtils.createStrUseUUID(originName);
        //文件保存的目录
        String filePath = request.getServletContext().getRealPath(File.separator + uploadRootDir);
        //分文件夹管理时的文件夹名
        String dirName = StringUtils.createDirName();
        //文件夹
        File dirFile = new File(filePath, dirName);

        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String href = uploadRootDir + File.separator + dirName + File.separator + newName;
        //拷贝文件
        try {
            file.transferTo(new File(filePath + File.separator + dirName, newName));
        } catch (IOException | IllegalStateException e) {
            logger.error("文件拷贝报错", e);

            throw new RuntimeException();
        }
        return href;

    }
}
