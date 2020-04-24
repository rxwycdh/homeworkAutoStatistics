package cn.rxwycdh.oraclehomework.service;

import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import cn.rxwycdh.oraclehomework.entity.ContactBasicInfo;
import cn.rxwycdh.oraclehomework.entity.UserHomeworkRelation;

import java.util.List;

/**
 * @FileName UserHomeWorkRelationService
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 14:28
 * @Modified
 * @Version 1.0
 */
public interface UserHomeWorkRelationService {

    int analysisAndInsert(MinioUploadDto dto);

    Long count(String fileName);

    /**
     * @return 未完成的用户id
     */
    List<ContactBasicInfo> listUnSubMit();

    List<UserHomeworkRelation> listFinish();
}
