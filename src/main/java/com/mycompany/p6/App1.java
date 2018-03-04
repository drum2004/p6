package com.mycompany.p6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
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
            /*JSONObject obj=new JSONObject();
          obj.put("first", 
                   readURL("http://kursusp6.herokuapp.com/hello4?max=100"));
          obj.put("second", i*2);*/
          JSONArray list2=new JSONArray();
          list2.add(readURL("http://johiv6.herokuapp.com/random?max=100"));
          list2.add(readURL("http://johiv6.herokuapp.com/random?max=100"));
          list.add(list2);
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
    
    @RequestMapping("/exercises")
    String exercisesHTML(String count){
            String s="<html><body><h1>Exercises</h1>";
        try {
            String data1=readURL("http://johiv6.herokuapp.com/numberList?count="+
                    Integer.parseInt(count));
            
            JSONArray a=(JSONArray)new JSONParser().parse(data1);
            for (Object obj: a){
               JSONArray a2=(JSONArray)obj;
               s+=a2.get(0)+"*"+a2.get(1)+"<br />";
            }
            s+="</body></html>";
            return s;
        } catch (Exception ex) {
            Logger.getLogger(App1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

}
