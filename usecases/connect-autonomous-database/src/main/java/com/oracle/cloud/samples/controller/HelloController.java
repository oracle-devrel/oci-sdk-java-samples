package com.oracle.cloud.samples.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController  
public class HelloController   
{  
    @RequestMapping("/api")  
    public String hello()   
    {  
        return "Hello World !";  
    }
}