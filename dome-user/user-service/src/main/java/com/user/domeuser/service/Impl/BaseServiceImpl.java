package com.user.domeuser.service.Impl;

import com.user.domeuser.mybatis.entity.BaseTable;
import com.user.domeuser.service.BaseService;
import com.user.domeuser.service.BaseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author littlenew
 * @date 2020/5/13 1:49 PM
 **/
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseTableService baseTableService;
    private Map<String, Iterator<Long>> baseTableServiceMap;
    private int nextNum = 10;

    @Override
    public Long diteratorId(String tableName) {
        if (baseTableServiceMap != null) {
            this.select(tableName);
        } else {
            this.save();
        }
        return baseTableServiceMap.get(tableName).next();

    }

    @Override
    public void upCount(String tableName) {
        BaseTable baseTable = baseTableService.selectByName(tableName);
        baseTableService.updateCount(baseTable.getId());
    }

    protected void save() {
        baseTableServiceMap = new HashMap<>();
        ArrayList<Long> array = new ArrayList<Long>();
        Iterator<Long> iterator = array.iterator();
        List<BaseTable> listTable = baseTableService.selectAll();
        if (baseTableServiceMap == null) {
            baseTableServiceMap = new HashMap<>();
        }
        for (BaseTable baseTable : listTable) {
            long maxid = baseTable.getTableMaxId();
            for (int i = 1; i < nextNum; i++) {
                array.add(maxid++);
            }
            iterator = array.iterator();
            baseTableServiceMap.put(baseTable.getBaseTable(), iterator);
            baseTable.setTableMaxId(maxid);
            baseTableService.update(baseTable);
        }
    }

    protected void select(String tableName) {
        Iterator<Long> iterator = baseTableServiceMap.get(tableName);
        if (iterator == null || !iterator.hasNext()) {
            ArrayList<Long> array = new ArrayList<Long>();
            BaseTable baseTable = baseTableService.selectByName(tableName);

            long maxid = baseTable.getTableMaxId();
            for (int i = 0; i < nextNum; i++) {
                array.add(maxid++);
            }
            iterator = array.iterator();
            baseTableServiceMap.put(baseTable.getBaseTable(), iterator);
            baseTable.setTableMaxId(maxid);
            baseTableService.update(baseTable);
        }
    }
}
