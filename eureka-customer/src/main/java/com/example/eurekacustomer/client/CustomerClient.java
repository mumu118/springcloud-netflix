package com.example.eurekacustomer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="eureka-client")
public interface CustomerClient {

    @GetMapping(value="/client/{id}")
    String feignTest(@PathVariable("id") Integer id);
}
