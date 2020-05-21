package com.user.domeuser.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseTable implements Serializable {
    /**
     * ip
     */
    private Long id;

    /**
     * 表名
     */
    private String baseTable;

    /**
     * 当前表id
     */
    private Long tableMaxId;

    /**
     * 数据数量
     */
    private Long dataCount;

    /**
     * 更新时间
     */
    private Date updataTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseTable() {
        return baseTable;
    }

    public void setBaseTable(String baseTable) {
        this.baseTable = baseTable;
    }

    public Long getTableMaxId() {
        return tableMaxId;
    }

    public void setTableMaxId(Long tableMaxId) {
        this.tableMaxId = tableMaxId;
    }

    public Long getDataCount() {
        return dataCount;
    }

    public void setDataCount(Long dataCount) {
        this.dataCount = dataCount;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }
}