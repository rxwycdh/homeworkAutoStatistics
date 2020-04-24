package cn.rxwycdh.oraclehomework.mapper;

import cn.rxwycdh.oraclehomework.entity.ContactBasicInfo;
import cn.rxwycdh.oraclehomework.entity.ContactBasicInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactBasicInfoMapper {
    long countByExample(ContactBasicInfoExample example);

    int deleteByExample(ContactBasicInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContactBasicInfo record);

    int insertSelective(ContactBasicInfo record);

    List<ContactBasicInfo> selectByExample(ContactBasicInfoExample example);

    ContactBasicInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContactBasicInfo record, @Param("example") ContactBasicInfoExample example);

    int updateByExample(@Param("record") ContactBasicInfo record, @Param("example") ContactBasicInfoExample example);

    int updateByPrimaryKeySelective(ContactBasicInfo record);

    int updateByPrimaryKey(ContactBasicInfo record);
}