package com.user.domeuser.mybatis.mapper;

import com.user.domeuser.mybatis.entity.BaseTable;
import com.user.domeuser.mybatis.entity.BaseTableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BaseTableMapper {
    long countByExample(BaseTableExample example);

    int deleteByExample(BaseTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseTable record);

    int insertSelective(BaseTable record);

    List<BaseTable> selectByExample(BaseTableExample example);

    BaseTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseTable record, @Param("example") BaseTableExample example);

    int updateByExample(@Param("record") BaseTable record, @Param("example") BaseTableExample example);

    int updateByPrimaryKeySelective(BaseTable record);

    int updateByPrimaryKey(BaseTable record);
}