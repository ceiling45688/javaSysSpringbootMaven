package com.ceiling45688.exception;


import com.ceiling45688.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理IllegalArgumentException异常
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // 处理EmailAlreadyExistsException异常
    @ExceptionHandler(UserServiceImpl.EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(UserServiceImpl.EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409 Conflict
    }

    // 注意这里处理全是controller异常，虽然是在service层抛出的，但是冒泡到调用它的controller层进行处理。
    // 如果控制器层也没有捕获和处理这个异常，它会继续“冒泡”到更高的层级，直到被处理或者到达应用的最顶层。
    // 在Spring MVC中，当一个异常从控制器层“冒泡”出来并且没有被处理时，@ControllerAdvice注解的类就会介入捕获并处理这个异常
//    @ControllerAdvice可以捕获以下情况的异常：
//    1.直接在控制器方法中抛出的异常。
//    2.在控制器方法调用的服务层或其他组件中抛出，但没有被捕获处理的异常。


    // 处理资源未找到的异常
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 NOT FOUND
    }
}
