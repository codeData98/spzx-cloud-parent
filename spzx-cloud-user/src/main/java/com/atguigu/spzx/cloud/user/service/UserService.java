package com.atguigu.spzx.cloud.user.service;

import com.atguigu.spzx.cloud.user.entity.User;

public interface UserService {
    User findUserByUserId(Long id);
}
