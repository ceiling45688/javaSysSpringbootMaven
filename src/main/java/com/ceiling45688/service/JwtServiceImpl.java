package com.ceiling45688.service;

import com.ceiling45688.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{
    // Json web token 服务， 这里用于身份验证和授权

    private static final String SECRET_KEY = "tt2kKWhG4t";
    private static final long EXPIRATION_TIME = 86400000; // 1天

    // 为给定用户生成给jwt
    @Override
    public String generateToken(User user) {

        // payload部分：claims放用户邮箱和角色
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("roles", user.getRoles());

        // 构建jwt
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用算法和密钥进行签名
                .compact(); // 构建并返回jwt字符串
    }

    // 验证和解析给定的JWT
    @Override
    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 设置用于验证jwt签名的密钥
                .parseClaimsJws(token) // 解析jwt并验证签名
                .getBody(); // 获取payload部分
    }
}
