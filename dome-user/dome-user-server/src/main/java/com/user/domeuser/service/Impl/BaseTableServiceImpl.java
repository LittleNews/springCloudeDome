package com.user.domeuser.service.Impl;

import com.user.domeuser.mybatis.entity.BaseTable;
import com.user.domeuser.mybatis.entity.BaseTableExample;
import com.user.domeuser.mybatis.mapper.BaseTableMapper;
import com.user.domeuser.service.BaseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author littlenew
 * @date 2020/5/13 1:34 PM
 **/
@Service
public class BaseTableServiceImpl implements BaseTableService {
    @Autowired
    private BaseTableMapper baseTableMapper;

    @Override
    public int save(BaseTable baseTable) {
        return baseTableMapper.insert(baseTable);
    }

    @Override
    public BaseTable selectById(Long id) {
        return baseTableMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(BaseTable baseTable) {

        return baseTableMapper.updateByPrimaryKey(baseTable);
    }

    /**
     * 保证数据一致性
     * @param id
     */
    @Override
    public void updateCount(Long id) {
        boolean status = true;
        while (status) {
            BaseTable baseTable = baseTableMapper.selectByPrimaryKey(id);
            BaseTableExample example = new BaseTableExample();
            example.createCriteria().andUpdataTimeEqualTo(baseTable.getUpdataTime())
                    .andIdEqualTo(id);
            long count = baseTable.getDataCount();
            baseTable.setDataCount(count+1);
            int i = baseTableMapper.updateByExampleSelective(baseTable, example);
            if (i > 0) {
                status = false;
            }
        }
    }

    @Override
    public List<BaseTable> selectAll() {
        BaseTableExample example = new BaseTableExample();
        example.createCriteria().andIdIsNotNull();
        return baseTableMapper.selectByExample(example);
    }

    @Override
    public BaseTable selectByName(String tableName) {
        BaseTableExample example = new BaseTableExample();
        example.createCriteria().andIdIsNotNull();
        List<BaseTable> list = baseTableMapper.selectByExample(example);
        return list.get(0);
    }

}
