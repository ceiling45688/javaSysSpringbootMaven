package com.ceiling45688.service;

import com.ceiling45688.dto.AuthenticationResult;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService{

    // 使用SLF4J进行日志记录
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;


    // 用户注册
    @Override
    public User registerUser(User user){
        // 这里是不是应该直接用email和password？为什么可以直接写User呢？不是应该先add才会有user实例吗
        // 另外这里注册的email需要满足email格式，且必须查询为数据库中没有存在的才可以注册。
        // 设置用户role等

        // 日志记录
        logger.info("Attempting to register user with email: {}", user.getEmail());

        // 首先验证email是否满足格式，并且在数据库中不存在
        if (!isValidEmail(user.getEmail())) {
            logger.warn("Invalid email format for email: {}", user.getEmail());
            throw new IllegalArgumentException("Invalid email format");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Email already exists: {}", user.getEmail());
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // set default role as customer
        user.getRoles().add(User.Role.CUSTOMER);

        // 对密码进行哈希处理
        user.setPassword(encoder.encode(user.getPassword()));

        logger.info("User registered successfully with email: {}", user.getEmail());

        return userRepository.save(user);
    }


    // 登录验证
    @Override
    public AuthenticationResult authenticateUser(String email, String password){

        logger.info("Attempting to authenticate user with email: {}", email);


        // 首先验证email是否在数据库中存在，存在再去验证密码
        User user = userRepository.findByEmail(email);

        if(user == null){
            logger.warn("Authentication failed for email: {}. User not found.", email);
            throw new EntityNotFoundException("User not found"); // 抛出实体未找到异常比nosuchelementexception更好
        }
        if(!encoder.matches(password, user.getPassword())){
            logger.warn("Authentication failed for email: {}. Incorrect password.", email);
            throw new IllegalArgumentException("Incorrect password");
        }

        logger.info("User authenticated successfully with email: {}", email);

        // 用户验证成功后，生成JWT并返回
        String token = jwtService.generateToken(user);
        return new AuthenticationResult(user, token); // 用户和JWT都放入AuthenticationResult对象
    }

    // 用户信息更新
    @Override
    public User updateUserDetails(User user){
        logger.info("Attempting to update user details for user ID: {}", user.getId());

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> {
                    logger.warn("User not found for ID: {}", user.getId());
                    return new EntityNotFoundException("User not found");
                });
        // 可以更新username，
        existingUser.setUsername(user.getUsername());
        // 更新balance
        existingUser.setBalance(user.getBalance());
        // 更新password
        existingUser.setPassword(encoder.encode(user.getPassword()));

        logger.info("User details updated successfully for user ID: {}", user.getId());

        return userRepository.save(existingUser);
    }

    // 付款（没写完
    @Override
    public void makePayment(Long userId, BigDecimal amount) {

        // 注意：余额是指用户待付款的余额
        logger.info("Attempting payment of amount {} for user ID: {}", amount, userId);

        // 获取用户
        User user = userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User not found for ID: {}", userId);
            return new EntityNotFoundException("User not found");
        });

        // 余额检查，确保不为负数，否则抛出异常
        if (user.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            logger.warn("Payment of amount {} exceeds outstanding balance for user ID: {}", amount, userId);
            throw new IllegalArgumentException("Payment exceeds outstanding balance");
        }

        // 更新余额并保存用户
        user.setBalance(user.getBalance().subtract(amount)); // 从待付款余额中减去支付的金额
        userRepository.save(user);

        logger.info("Payment of amount {} processed successfully for user ID: {}", amount, userId);
    }

    // 余额查询 有点麻烦后面弄（没写完
    @Override
    public BigDecimal viewBalance(Long userId) {
        logger.info("Fetching balance for user ID: {}", userId);

        User user = userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User not found for ID: {}", userId);
            return new EntityNotFoundException("User not found");
        });

        logger.info("Balance fetched successfully for user ID: {}. Balance: {}", userId, user.getBalance());

        return user.getBalance();
    }

    // 邮箱验证（简易版）
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // 处理异常
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }


}
