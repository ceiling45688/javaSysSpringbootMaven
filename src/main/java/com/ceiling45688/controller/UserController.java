package com.ceiling45688.controller;

import com.ceiling45688.dto.*;
import com.ceiling45688.model.User;
import com.ceiling45688.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // sign up
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody SignupDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        User registeredUser = userService.registerUser(user);
        //不直接返回Entity，而是返回一个DTO
        return new ResponseEntity<>(convertToDTO(registeredUser), HttpStatus.CREATED);  // 返回201状态码
    }

    // login
    @PostMapping("login")
    public ResponseEntity<AuthenticationResult> authenticateUser(@RequestBody LoginDTO dto){
        AuthenticationResult result = userService.authenticateUser(dto.getEmail(), dto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + result.getToken());
        return new ResponseEntity<>(result, headers, HttpStatus.OK); // // 当用户登录成功时，他们会收到一个HTTP状态码为200的响应，响应体中包含用户信息，响应头中包含JWT。

    }

    // update user details
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUserDetails(@PathVariable Long userId, @RequestBody UserDTO dto){
        User user = new User();
        user.setId(userId);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setBalance(dto.getBalance());
        user.setRoles(dto.getRoles());
        User updatedUser = userService.updateUserDetails(user);
        return ResponseEntity.ok(convertToDTO(updatedUser));  // 返回200状态码
    }

    // 用户付款
    @PostMapping("/{userId}/payment")
    public ResponseEntity<Void> makePayment(@PathVariable Long userId, @RequestBody BigDecimal amount) {
        userService.makePayment(userId, amount);
        return ResponseEntity.noContent().build();  // 返回204状态码，表示成功但没有内容返回
    }

    // 查询余额
    @GetMapping("/{userId}/balance")
    public ResponseEntity<BigDecimal> viewBalance(@PathVariable Long userId) {
        BigDecimal balance = userService.viewBalance(userId);
        return ResponseEntity.ok(balance);  // 返回200状态码
    }


    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        // ... 设置其他属性
        return dto;
    }
}
