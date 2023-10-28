package com.ceiling45688.dto;

import com.ceiling45688.model.User;

public class AuthenticationResult {
    // 为了同时返回用户和JWT 只能新弄一个dto了

    private User user;
    private String token;

    public AuthenticationResult(User user, String token){
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
