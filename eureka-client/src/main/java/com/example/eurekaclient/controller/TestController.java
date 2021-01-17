package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
        // 模拟异常
        //System.out.println(1/0);
        // 模拟超时
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //return "test:" + port + ":" + id;
        return UUID.randomUUID().toString();
    }
}
