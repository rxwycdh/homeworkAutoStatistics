package cn.rxwycdh.oraclehomework.controller;

import cn.rxwycdh.oraclehomework.common.CommonResult;
import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import cn.rxwycdh.oraclehomework.service.MinioOssService;
import cn.rxwycdh.oraclehomework.service.UserHomeWorkRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName HomeWorkController
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 14:11
 * @Modified
 * @Version 1.0
 */
@RestController
public class HomeWorkController {

    @Autowired
    private MinioOssService minioOssService;

    @Autowired
    private UserHomeWorkRelationService relationService;

    @PostMapping("/upload")
    public CommonResult uploadFile(MultipartFile file) {


        if (!file.isEmpty()) {


            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            String a = file.getOriginalFilename().substring(begin, last);
            if(a.endsWith(".doc")) {

                MinioUploadDto minioUploadDto = minioOssService.uploadFile(file);

                if(minioUploadDto == null) {
                    return CommonResult.success("请不要重复提交");
                }

                if(relationService.count(minioUploadDto.getName()) == 0) {
                    return CommonResult.success("上传失败，检查你的文件格式或者命名是否符合");
                }

                int count = relationService.analysisAndInsert(minioUploadDto);

                if(count > 0) {
                    return CommonResult.success("当你看到这个消息，你已经成功上传了");
                }
            }
            return CommonResult.success("上传失败，检查你的文件格式是否是 : .doc");
        }

        return CommonResult.success("上传失败，请重新上传");
    }

}
