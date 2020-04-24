package cn.rxwycdh.oraclehomework.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.rxwycdh.oraclehomework.dto.MinioUploadDto;
import cn.rxwycdh.oraclehomework.entity.*;
import cn.rxwycdh.oraclehomework.mapper.ContactBasicInfoMapper;
import cn.rxwycdh.oraclehomework.mapper.HomeworkBasicInfoMapper;
import cn.rxwycdh.oraclehomework.mapper.UserHomeworkRelationMapper;
import cn.rxwycdh.oraclehomework.service.ContactBasicInfoService;
import cn.rxwycdh.oraclehomework.service.HomeworkBasicInfoService;
import cn.rxwycdh.oraclehomework.service.UserHomeWorkRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName UserHomeWorkRelationServiceImpl
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 14:28
 * @Modified
 * @Version 1.0
 */
@Service
public class UserHomeWorkRelationServiceImpl implements UserHomeWorkRelationService {

    @Autowired
    private UserHomeworkRelationMapper userHomeworkRelationMapper;

    @Autowired
    private ContactBasicInfoMapper contactBasicInfoMapper;

    @Autowired
    HomeworkBasicInfoService homeworkBasicInfoService;

    @Autowired
    private ContactBasicInfoService contactBasicInfoService;

    @Override
    public int analysisAndInsert(MinioUploadDto dto) {

        // 分析有没有
        UserHomeworkRelationExample example = new UserHomeworkRelationExample();
        example.createCriteria().andFileNameEqualTo(dto.getName());

        if(userHomeworkRelationMapper.selectByExample(example).size() > 0) {
            return 1;
        }

        UserHomeworkRelation relation = new UserHomeworkRelation();
        relation.setFileName(dto.getName());
        relation.setFileUrl(dto.getUrl());
        relation.setContactId(getContactId(dto.getName()));
        relation.setHomeworkId(homeworkBasicInfoService.getHomework().getId());
        relation.setCreateTime(new Date());

        return userHomeworkRelationMapper.insert(relation);


    }

    public Integer getContactId(String fileName) {

        String name = fileName2Name(fileName);

        ContactBasicInfoExample example = new ContactBasicInfoExample();
        example.createCriteria().andNameEqualTo(name);

        List<ContactBasicInfo> list = contactBasicInfoMapper.selectByExample(example);

        if(list.size() > 0){
            return list.get(0).getId();
        }
        return 0;
    }

    @Override
    public Long count(String fileName) {

        String name = fileName2Name(fileName);

        ContactBasicInfoExample example = new ContactBasicInfoExample();
        example.createCriteria().andNameEqualTo(name);

        return contactBasicInfoMapper.countByExample(example);
    }


    public String fileName2Name(String fileName) {
        String delclareFileName = StrUtil.removeSuffix(fileName, ".doc");
        HomeworkBasicInfo homework = homeworkBasicInfoService.getHomework();

        String title = homeworkBasicInfoService.getHomework().getTitle();
        String numAndName = StrUtil.removeSuffix(delclareFileName, homeworkBasicInfoService.getHomework().getTitle());
        return StrUtil.subSuf(numAndName, 10);
    }


    /**
     * @return 未完成的用户id
     */
    @Override
    public List<ContactBasicInfo> listUnSubMit() {

        List<ContactBasicInfo> all = contactBasicInfoService.listAll();

        List<UserHomeworkRelation> relations = listFinish();

        List<Integer> finishIds = relations.stream()
                .map(UserHomeworkRelation::getContactId)
                .collect(Collectors.toList());

        List<ContactBasicInfo> unfinish = all.stream()
                .filter(contact -> !finishIds.contains(contact.getId()))
                .collect(Collectors.toList());

        return unfinish;
    }

    @Override
    public List<UserHomeworkRelation> listFinish() {

        HomeworkBasicInfo homework = homeworkBasicInfoService.getHomework();

        UserHomeworkRelationExample example = new UserHomeworkRelationExample();

        example.createCriteria().andHomeworkIdEqualTo(homework.getId());

        return userHomeworkRelationMapper.selectByExample(example);
    }
}
