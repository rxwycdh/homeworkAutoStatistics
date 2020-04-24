package cn.rxwycdh.oraclehomework.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfo;
import cn.rxwycdh.oraclehomework.service.HomeworkBasicInfoService;
import cn.rxwycdh.oraclehomework.service.MinioOssService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName MinioOssServiceImpl
 * @Description
 * @Author jiuhao
 * @Date 2020/3/14 10:30
 * @Modified
 * @Version 1.0
 */
@Service
public class MinioOssServiceImpl implements MinioOssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioOssServiceImpl.class);

    @Value("${minio.appKey}")
    private String appKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.url}")
    private String url;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private HomeworkBasicInfoService homeworkBasicInfoService;



    @Override
    public MinioUploadDto uploadFile(MultipartFile file) {
        try {
            MinioClient minioClient = new MinioClient(url, appKey, secretKey);
            if(!minioClient.bucketExists(bucketName)) {

                minioClient.makeBucket(bucketName);

            }


            String fileName = file.getOriginalFilename();

            String objectName = homeworkBasicInfoService.getHomework().getTitle() + "/" + fileName;

            if(isExist(minioClient, objectName)) {
                return null;
            }

            minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getSize(),
                    null, null, file.getContentType());
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(fileName);
            minioUploadDto.setUrl(url + "/" + bucketName + "/" + objectName);
            LOGGER.info("upload file successful!");
            return minioUploadDto;

        }catch (Exception e) {
            LOGGER.info("Error upload file:" + e);
        }
        return null;
    }

    public Boolean isExist(MinioClient minioClient, String objectName) throws Exception {
        List<String> objectNameList = new ArrayList<>();

        Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
        for (Result<Item> result : myObjects) {
            Item item = result.get();
            objectNameList.add(item.objectName());
        }

        return objectNameList.contains(objectName);

    }

}
