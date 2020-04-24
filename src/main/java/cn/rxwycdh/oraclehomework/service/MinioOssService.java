package cn.rxwycdh.oraclehomework.service;

import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName MinioOssService
 * @Description
 * @Author jiuhao
 * @Date 2020/3/14 10:30
 * @Modified
 * @Version 1.0
 */
public interface MinioOssService {

    /**
     * 文件上传 失败返回空对象
     * @param file 文件
     * @return {@link MinioUploadDto}
     */
    MinioUploadDto uploadFile(MultipartFile file);
}
