package com.ceiling45688.dto;

import com.ceiling45688.model.User;

import java.math.BigDecimal;
import java.util.Set;

public class UserDTO {
    private String email;
    private String password;
    private String username;
    private BigDecimal balance;
    private Set<User.Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<User.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<User.Role> roles) {
        this.roles = roles;
    }
}
