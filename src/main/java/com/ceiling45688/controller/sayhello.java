package com.ceiling45688.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sayhello {
    //Port: http://localhost:8081/hello
    // 使用RequestMapping处理get请求
//    @RequestMapping("hello")
//    public String sayHello(){
//        return "hello world";
//    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "world")String name){
        return String.format("Hello %s!", name);
    }

}
