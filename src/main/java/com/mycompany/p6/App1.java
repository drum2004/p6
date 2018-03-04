package com.mycompany.p6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication

public class App1 {
    @RequestMapping("/hello")
    public String helloFunction(){
        return "Hello";
    }
    
    @RequestMapping("/hello2")
    public String helloFunction2(String firstname){
        return "Hello, " +firstname+"!";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(App1.class, args);

    }

}
