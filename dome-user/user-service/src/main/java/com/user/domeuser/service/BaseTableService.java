package com.user.domeuser.service;

import com.user.domeuser.mybatis.entity.BaseTable;

import java.util.List;

/**
 * @author littlenew
 * @date 2020/5/13 1:34 PM
 **/

public interface BaseTableService {

    int save(BaseTable baseTable);

    BaseTable selectById(Long id);

    int update(BaseTable baseTable);

    List<BaseTable> selectAll();

    BaseTable selectByName(String tableName);

    void updateCount(Long id);

}
