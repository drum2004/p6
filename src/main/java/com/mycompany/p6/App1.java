package com.mycompany.p6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication

public class App1 {

    @RequestMapping("/hello")
    public String helloFunction() {
        return "Hello";
    }

    @RequestMapping("/hello2")
    public String helloFunction2(String firstname) {
        return "Hello, " + firstname + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App1.class, args);

    }

    String readURL(String address){
        try{
          BufferedReader br=new BufferedReader(new InputStreamReader(
            new URL(address).openStream()
          ));
          return br.readLine();
        } catch(IOException ex){ex.printStackTrace();}
        return "-1";
    }    
    
    @RequestMapping("/numberList")
    public String listNumber(String count) {
        JSONArray list = new JSONArray();
        for (int i = 0; i < Integer.parseInt(count); i++) {
            JSONObject obj = new JSONObject();
            obj.put("first", readURL("http://johiv6.herokuapp.com/random?max=100"));
            obj.put("second", i * 2);
            list.add(obj);
        }
        return list.toJSONString();
    }

    @RequestMapping("/random")
    public String randomFunction(String max) {
        SecureRandom random = new SecureRandom();
        int number = 1 + random.nextInt(Integer.parseInt(max));
        String token = String.valueOf(number);
        return (token);
    }

}
