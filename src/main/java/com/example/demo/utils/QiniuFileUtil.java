package com.example.demo.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class QiniuFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "usFYZNw0WPHNNrAnx5fePFTQbXWH4rBEzyfTO6BX";
    private static final String SECRET_KEY = "UiBNM6f7lb3JihCefJrGO8GQKwDrZSMnDHhpAkjH";
    // 要上传的空间
    private static final String bucketname = "ljafinalfile";

    // 密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    private static final Configuration cfg = new Configuration(Zone.zone2());


    // ...其他参数参考类注释
//    UploadManager uploadManager = new UploadManager(cfg);

    // 测试域名，只有30天有效期http://r9o05za4e.hn-bkt.clouddn.com/%E5%91%A8%E4%B8%80.jpg
    private static String QINIU_IMAGE_DOMAIN = "http://rb8vxgfgt.hn-bkt.clouddn.com/";

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucketname).toString();
    /**
     * 使用断点上传，防止上传大文件时网络断开，上传一般
     * 原理：就是设置一个临时文件夹，先保存到临时文件夹中，下次加载时在进行上传
     * @param file
     * @return
     * @throws IOException
     */

    public String uploadFile(MultipartFile file) throws IOException {
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        cfg.resumableUploadMaxConcurrentTaskCount = 2;  // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传

        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);


            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
//                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
                return fileName;
            } else {
//                logger.error("七牛异常:" + res.bodyString());
                System.out.println("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
//            logger.error("七牛异常:" + e.getMessage());
            System.out.println("七牛异常:" + e.getMessage());
            return null;
        }
    }

    /**
     * 删除七牛云上ljavideo上的文件
     */
    public int deleteFileFromQiniu(String fileName){

        Configuration cfg = new Configuration(Zone.zone2());
        String key = fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response delete = bucketManager.delete(bucketname, key);
            return delete.statusCode;
        } catch (QiniuException ex) {
            ex.printStackTrace();
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        return -1;
    }
}
