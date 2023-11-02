package com.ceiling45688.configuration;


import com.ceiling45688.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsConfig corsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource()) // 启用cors配置,确保安全过滤器不会阻止预检请求。Spring Security的过滤器链在Spring MVC的过滤器链之前执行。
                .and()
                .csrf().disable() // 禁用csrf保护，安全性后续再加强吧，不然每个修改数据请求都要添加令牌太麻烦，但其实这里是有跨域危险的
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/users/signup", "/users/login", "/home").permitAll() // 允许所有人访问
                    .antMatchers("/api/admin/**").hasRole("ADMIN") // 只有ADMIN角色的用户可以访问
                    .antMatchers("/api/user/**").hasRole("CUSTOMER") // 只有CUSTOMER角色的用户可以访问
                    .antMatchers("/service-requests/**").hasAnyRole("ADMIN", "MAINTENANCE") // 只有ADMIN和MAINTENANCE角色的用户可以访问
                    .antMatchers("/rooms/available").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() // 禁用默认的form登录，
                .httpBasic().disable(); // 禁用HTTP Basic认证

    }

    // 哈希密码编码器
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 自定义cors配置，配置Spring Security的CORS支持，独立于SpringMVC，确保security不会组织跨域请求
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // 允许所有的头部
        configuration.setAllowCredentials(true); // 允许证书，如cookies等

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
