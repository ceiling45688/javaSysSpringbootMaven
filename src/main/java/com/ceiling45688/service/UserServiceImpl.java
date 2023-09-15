package com.ceiling45688.service;

import com.ceiling45688.model.User;
import com.ceiling45688.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    // 用户注册
    @Override
    public User registerUser(User user){
        // 这里是不是应该直接用email和password？为什么可以直接写User呢？不是应该先add才会有user实例吗
        // 另外这里注册的email需要满足email格式，且必须查询为数据库中没有存在的才可以注册。
        // 设置用户role等

        // 首先验证email是否满足格式，并且在数据库中不存在
        if (!isValidEmail(user.getEmail()) || userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email or email already exists");
        }
        // set default role as customer
        user.getRoles().add(User.Role.CUSTOMER);

        return userRepository.save(user);
    }


    // 登录验证
    @Override
    public User authenticateUser(String email, String password){
        // 首先验证email是否在数据库中存在，存在再去验证密码
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("Incorrect password");
        }
        return user;
    }

    // 用户信息更新
    @Override
    public User updateUserDetails(User user){
        // 前面应该有个验证


        User existingUser = userRepository.findById(user.getId()).
                orElseThrow(() -> new IllegalArgumentException("User not found"));
        // 可以更新username，
        existingUser.setUsername(user.getUsername());
        // 更新balance
        existingUser.setBalance(user.getBalance());
        // 更新password
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    // 付款（没写完
    @Override
    public void makePayment(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
    }

    // 余额查询 有点麻烦后面弄（没写完
    @Override
    public BigDecimal viewBalance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getBalance();
    }

    // 邮箱验证（简易版）
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
