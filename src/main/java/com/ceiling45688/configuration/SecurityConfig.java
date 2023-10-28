package com.ceiling45688.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // 启用cors配置
                .and()
                .csrf().disable() // 禁用csrf保护，安全性后续再加强吧，不然每个修改数据请求都要添加令牌太麻烦，但其实这里是有跨域危险的
                .authorizeRequests()
                .anyRequest().permitAll() // 允许所有请求，后续根据需要进行更细粒度的配置
                .and()
                .formLogin().disable() // 禁用默认的form登录，
                .httpBasic().disable(); // 禁用HTTP Basic认证
    }

    // 哈希密码编码器
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
