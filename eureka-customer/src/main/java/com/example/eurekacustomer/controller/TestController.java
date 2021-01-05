package com.example.eurekacustomer.controller;


import com.example.eurekacustomer.client.CustomerClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private CustomerClient customerClient;

    @GetMapping("/customer/test")
    public String test(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        System.out.println(serviceInstance);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/test" ;
        return restTemplate.getForObject(url,String.class);
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    @GetMapping("/customer/client/{id}")
    public String feignTest(@PathVariable("id") Integer id){
        return customerClient.feignTest(id);
    }

    public String fallBack(@PathVariable("id") Integer id){
        return "error" + id;
    }
}
