package com.ceiling45688.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键 自动

    @Column(nullable = false, unique = true)
    private String email; // email 作为登录名，注册后且不能修改

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username; // 名字可以重复

    @Column(precision = 10, scale = 2)
    private BigDecimal balance;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    //避免将所有的用户类型放在一个列中
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();


    // 一个客户可以有多个预定
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();




    public enum Role{
        ADMIN, CUSTOMER, MAINTENANCE
    }
    // constructor

    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; } // 这里是不是应该删掉，因为只在注册时使用

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

}
