package com.user.domeuser.mybatis.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 
     */
    private String userName;

    private static final Long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}