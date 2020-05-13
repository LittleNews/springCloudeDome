package com.cloudetest.generator.mybatis.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TCommunityEntry implements Serializable {
    /**
     * 社区进场id
     */
    private Long id;

    /**
     * 社区ID
     */
    private Long communitId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 排队编号/0为未开始排队
     */
    private Long rowNum;

    /**
     * 参与时间
     */
    private Date attendTime;

    /**
     * 0正常/1移出队列
     */
    private Byte remove;

    /**
     * 创建时间
     */
    private Date createrTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0正常/1删除
     */
    private Byte isDelete;

    /**
     * 参与排队金额
     */
    private BigDecimal attendAmount;

    /**
     * 币种类型,BTC:1,LTC:2,ETH:3,USDT4;
     */
    private Byte currencyType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommunitId() {
        return communitId;
    }

    public void setCommunitId(Long communitId) {
        this.communitId = communitId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public Byte getRemove() {
        return remove;
    }

    public void setRemove(Byte remove) {
        this.remove = remove;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public BigDecimal getAttendAmount() {
        return attendAmount;
    }

    public void setAttendAmount(BigDecimal attendAmount) {
        this.attendAmount = attendAmount;
    }

    public Byte getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Byte currencyType) {
        this.currencyType = currencyType;
    }
}