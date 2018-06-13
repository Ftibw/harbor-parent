package com.whxm.harbor.utils;

import com.whxm.harbor.bean.Result;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ftibw
 * @Email ftibw@live.com
 * @CreateTime 2018/6/13 21:58
 */
public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);

    public static void upload(

            MultipartFile file, HttpServletRequest request,

            String uploadRootDir, Map<String, Object> result
    ) {
        String originName = null;
        Long size = null;
        String newName = null;
        String href = null;
        try {
            //文件名称
            originName = file.getOriginalFilename();
            //文件大小
            size = file.getSize();
            //uuid生成新名称
            newName = StringUtils.createStrUseUUID(originName);
            //文件保存的目录
            String filePath = request.getServletContext().getRealPath(File.separator + uploadRootDir);
            //分文件夹管理时的文件夹名
            String dirName = StringUtils.createDirName();
            //文件夹
            File dirFile = new File(filePath, dirName);

            if (!dirFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                dirFile.mkdirs();
            }

            href = uploadRootDir + File.separator + dirName + File.separator + newName;
            //拷贝文件
            file.transferTo(new File(filePath + File.separator + dirName, newName));

        } catch (IOException e) {

            logger.error("文件拷贝报错", e);

            throw new RuntimeException();
        }

        if (null != result) {
            result.put("fileOriginName", originName);
            result.put("fileSize", size);
            result.put("fileNewName", newName);
            result.put("filePath", href);
        }
    }

    public static Result upload(MultipartFile file, HttpServletRequest request, String uploadRootDir) {

        if (!file.isEmpty()) {
            try {
                HashMap<String, Object> map = new HashMap<>(4);

                FileUtils.upload(file, request, uploadRootDir, map);

                return new Result(map);

            } catch (Exception e) {

                logger.error("文件上传 发生错误", e);

                return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文件上传 发生错误", file);
            }
        } else {
            logger.error("上传的文件是空的");

            return new Result(HttpStatus.NOT_ACCEPTABLE.value(), "上传的文件是空的", file);
        }
    }
}
