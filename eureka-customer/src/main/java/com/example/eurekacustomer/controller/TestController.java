package com.example.eurekacustomer.controller;


import com.example.eurekacustomer.client.CustomerClient;
import com.example.eurekacustomer.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private TestService testService;

    @GetMapping("/customer/test")
    public String test(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        System.out.println(serviceInstance);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/test" ;
        return restTemplate.getForObject(url,String.class);
    }

    /**
     * Hystrix:
     * 1.服务降级
     * 2.隔离，线程池 信号量
     * 3.服务熔断
     * 4.缓存
     * @param id
     * @return
     */
    //@HystrixCommand(fallbackMethod = "fallBack")
    @GetMapping("/customer/client/{id}")
    public String feignTest(@PathVariable("id") Integer id){
        //if (id == 1){
        //    int i = 1/0;
        //    return "error";
        //}
        //return customerClient.feignTest(id);
        testService.remove(id);
        System.out.println(testService.feignTest(id));
        System.out.println(testService.feignTest(id));
        testService.remove(id);
        System.out.println(testService.feignTest(id));
        System.out.println(testService.feignTest(id));
        return customerClient.feignTest(id);
    }

    /**
     * 降级方法，方法描述和原方法（feignTest）一致
     * @param id
     * @return
     */
    public String fallBack(@PathVariable("id") Integer id){
        return "error" + id;
    }
}
