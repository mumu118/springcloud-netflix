package com.example.eurekacustomer.service;

import com.example.eurekacustomer.client.CustomerClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private CustomerClient customerClient;

    // 缓存key与removeKey保持一致
    // 缓存key以方法全部参数作为key,可用@CacheKey指定部分参数作为key
    @CacheResult
    @HystrixCommand(commandKey = "feignTest")
    public String feignTest(@CacheKey Integer id){
        System.out.println(id);
        //if (id == 1){
        //    int i = 1/0;
        //    return "error";
        //}
        return customerClient.feignTest(id);
    }

    @CacheRemove(commandKey = "feignTest")
    @HystrixCommand
    public void remove(@CacheKey Integer id){
        System.out.println("缓存清除成功");
    }

}
