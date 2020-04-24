package cn.rxwycdh.oraclehomework.mapper;

import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfo;
import cn.rxwycdh.oraclehomework.entity.HomeworkBasicInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeworkBasicInfoMapper {
    long countByExample(HomeworkBasicInfoExample example);

    int deleteByExample(HomeworkBasicInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeworkBasicInfo record);

    int insertSelective(HomeworkBasicInfo record);

    List<HomeworkBasicInfo> selectByExample(HomeworkBasicInfoExample example);

    HomeworkBasicInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeworkBasicInfo record, @Param("example") HomeworkBasicInfoExample example);

    int updateByExample(@Param("record") HomeworkBasicInfo record, @Param("example") HomeworkBasicInfoExample example);

    int updateByPrimaryKeySelective(HomeworkBasicInfo record);

    int updateByPrimaryKey(HomeworkBasicInfo record);
}