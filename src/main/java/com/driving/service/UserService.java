package com.driving.service;

import com.driving.model.Account;
import com.driving.model.User;

import java.util.List;

/**
 * Created by wk on 2017-11-23
 * account 的 service 的接口
 */
public interface UserService {
    int insertUser(User user);
}