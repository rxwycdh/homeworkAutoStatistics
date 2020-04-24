package cn.rxwycdh.oraclehomework.mapper;

import cn.rxwycdh.oraclehomework.entity.UserHomeworkRelation;
import cn.rxwycdh.oraclehomework.entity.UserHomeworkRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserHomeworkRelationMapper {
    long countByExample(UserHomeworkRelationExample example);

    int deleteByExample(UserHomeworkRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserHomeworkRelation record);

    int insertSelective(UserHomeworkRelation record);

    List<UserHomeworkRelation> selectByExample(UserHomeworkRelationExample example);

    UserHomeworkRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserHomeworkRelation record, @Param("example") UserHomeworkRelationExample example);

    int updateByExample(@Param("record") UserHomeworkRelation record, @Param("example") UserHomeworkRelationExample example);

    int updateByPrimaryKeySelective(UserHomeworkRelation record);

    int updateByPrimaryKey(UserHomeworkRelation record);
}