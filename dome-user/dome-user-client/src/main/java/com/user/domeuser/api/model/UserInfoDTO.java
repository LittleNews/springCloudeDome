package com.user.domeuser.api.model;

import java.io.Serializable;

/**
 * @author littlenew
 * @date 2020/5/12 4:29 PM
 **/
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 2430120169651200326L;
    public String userName;
    public Long id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                '}';
    }
}
