package com.ceiling45688.service;

import com.ceiling45688.model.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(User user);
    Claims decodeToken(String token);
}
