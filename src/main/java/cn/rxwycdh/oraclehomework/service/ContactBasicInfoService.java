package cn.rxwycdh.oraclehomework.service;

import cn.rxwycdh.oraclehomework.entity.ContactBasicInfo;

import java.util.List;

/**
 * @FileName ContactBasicInfoService
 * @Description
 * @Author jiuhao
 * @Date 2020/3/19 20:37
 * @Modified
 * @Version 1.0
 */
public interface ContactBasicInfoService {

    List<ContactBasicInfo> listAll();

    ContactBasicInfo get(Integer id);
}
