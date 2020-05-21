package com.user.domeuser.service.Impl;

import com.alibaba.fastjson.JSON;
import com.user.domeuser.mybatis.entity.UserInfo;
import com.user.domeuser.mybatis.mapper.UserInfoMapper;
import com.user.domeuser.service.BaseService;
import com.user.domeuser.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author littlenew
 * @date 2020/5/13 4:28 PM
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 表名
     */
    private final String tableName = "user_info";

    @Override
    public Long saveUserInfo(UserInfo userInfo) {
        Long id = baseService.diteratorId(tableName);
        userInfo.setId(id);
        int saveSta = userInfoMapper.insert(userInfo);
        if (saveSta > 0) {
            baseService.upCount(tableName);
            return id;
        }
        return null;
    }

    @Override
    public UserInfo selectUserInfoById(Long id) {
        System.out.println(JSON.toJSONString(id));
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int upDateById(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }
}
