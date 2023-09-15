package com.ceiling45688;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hu
 */

@SpringBootApplication
@RestController
public class Application {
     public static void main(String[] args){
         SpringApplication.run(Application.class, args);
     }

//     @GetMapping("/hello")
//    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name){
////         return String.format("Hello %s!", name);
////     }


}
