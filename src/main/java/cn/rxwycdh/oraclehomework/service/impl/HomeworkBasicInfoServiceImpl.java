package cn.rxwycdh.oraclehomework.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfo;
import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfoExample;
import cn.rxwycdh.oraclehomework.mapper.HomeworkBasicInfoMapper;
import cn.rxwycdh.oraclehomework.service.HomeworkBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @FileName HomeworkBasicInfoServiceImpl
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 15:10
 * @Modified
 * @Version 1.0
 */
@Service
public class HomeworkBasicInfoServiceImpl implements HomeworkBasicInfoService {

    @Autowired
    private HomeworkBasicInfoMapper homeworkBasicInfoMapper;

    @Override
    public HomeworkBasicInfo getHomework() {
        {

            // 获取这个星期的作业内容
            Date date = new Date();
            Date weekStart = DateUtil.beginOfWeek(date);
            Date weekEnd = DateUtil.endOfWeek(date);

            HomeworkBasicInfoExample example = new HomeworkBasicInfoExample();
//            example.createCriteria().andStartTimeGreaterThanOrEqualTo(weekStart);
            example.createCriteria();

            HomeworkBasicInfo homeworkBasicInfo = homeworkBasicInfoMapper.selectByExample(example).get(0);

            return homeworkBasicInfo;
        }
    }
}
