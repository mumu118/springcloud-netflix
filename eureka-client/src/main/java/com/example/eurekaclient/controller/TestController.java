package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String hello(){
        return "test";
    }

    @GetMapping("/client/{id}")
    public String feignTest(@PathVariable("id") Integer id){
        return "test:" + port + ":" + id;
    }
}
