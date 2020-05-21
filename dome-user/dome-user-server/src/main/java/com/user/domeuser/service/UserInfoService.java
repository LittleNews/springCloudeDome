package com.user.domeuser.service;

import com.user.domeuser.mybatis.entity.UserInfo;

/**
 * @author littlenew
 * @date 2020/5/13 4:28 PM
 **/

public interface UserInfoService {
    Long saveUserInfo(UserInfo userInfo);

    UserInfo selectUserInfoById(Long id);

    int upDateById(UserInfo userinfo);

}
