package com.ceiling45688.service;

import com.ceiling45688.dto.AuthenticationResult;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface UserService {

    // 用户注册
    User registerUser(User user);
    // 登录验证
    AuthenticationResult authenticateUser(String email, String password);
    // 用户信息更新
    User updateUserDetails(User user);
    // 付款
    void makePayment(Long userId, BigDecimal amount);
    // 余额查询 有点麻烦后面弄
    BigDecimal viewBalance(Long UserId);

}
