package com.example.eurekacustomer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="eureka-client")
public interface CustomerClient {

    @RequestMapping(value="/client/{id}",method = RequestMethod.GET)
    public String feignTest(@PathVariable("id") Integer id);
}
