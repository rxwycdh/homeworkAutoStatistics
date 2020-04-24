package cn.rxwycdh.oraclehomework.service.impl;

import cn.rxwycdh.oraclehomework.entity.ContactBasicInfo;
import cn.rxwycdh.oraclehomework.entity.ContactBasicInfoExample;
import cn.rxwycdh.oraclehomework.mapper.ContactBasicInfoMapper;
import cn.rxwycdh.oraclehomework.service.ContactBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName ContactBasicInfoServiceImpl
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 20:37
 * @Modified
 * @Version 1.0
 */
@Service
public class ContactBasicInfoServiceImpl implements ContactBasicInfoService {

    @Autowired
    private ContactBasicInfoMapper contactBasicInfoMapper;

    @Override
    public List<ContactBasicInfo> listAll() {
        ContactBasicInfoExample example = new ContactBasicInfoExample();
        example.createCriteria();

        return contactBasicInfoMapper.selectByExample(example);
    }

    @Override
    public ContactBasicInfo get(Integer id) {
        return contactBasicInfoMapper.selectByPrimaryKey(id);
    }
}
