package com.verio.utils;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * OssUtil 对象存储工具
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Component
public class OssUtil {

    private static String FILE_URL;
    private static String BUCKET_NAME = "";
    private static String END_POINT = "oss-cn-beijing.aliyuncs.com";
    private static String ACCESS_KEY_ID = "";
    private static String ACCESS_KEY_SECRET = "";

    /**
     * 上传文件
     */
    public static String upload(MultipartFile file, String path) throws IOException {
        if (file == null || path == null) {
            return null;
        }
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        if (!ossClient.doesBucketExist(BUCKET_NAME)) {
            ossClient.createBucket(BUCKET_NAME);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        String extension = OssUtil.getFileExtension(file);
        //设置文件路径
        String fileUrl = path + "/" + IdUtil.simpleUUID() + extension;
        FILE_URL = "https://" + BUCKET_NAME + "." + END_POINT + "/" + fileUrl;
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, file.getInputStream()));
        //上传文件
        ossClient.setBucketAcl(BUCKET_NAME, CannedAccessControlList.PublicRead);
        return FILE_URL;
    }

    /**
     * 获取文件的扩展名
     */
    public static String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename.substring(filename.lastIndexOf("."));
    }
}
