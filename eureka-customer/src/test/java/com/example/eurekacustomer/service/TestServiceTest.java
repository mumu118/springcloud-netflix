package com.example.eurekacustomer.service;

import com.example.eurekacustomer.equator.FieldEquator;
import com.example.eurekacustomer.equator.User;
import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class TestServiceTest {

    @Test
    public void test1(){
        Equator equator = new FieldEquator();
        User user = new User("duncan ",23,"eeeee ");
        User user1 = new User("Duncan",23,"eeeee5");
        List<FieldInfo> lesseeFieldInfos = equator.getDiffFields(user,user1);
        List<String> lesseedifFieldNames = lesseeFieldInfos.stream().map(FieldInfo::getFieldName).collect(Collectors.toList());
        System.out.println(lesseeFieldInfos);
    }

    @Test
    public void test2(){

        int[] array = new int[]{1,5,8,2};
        int target = 10;

        int index = 0;
        int sum = 0;
        for (int i = 0;i< array.length;i++){
            for (int j = i;j< array.length;j++){
                sum = sum + array[j];
                if (sum == target){
                    index = j;
                    return;
                }
            }
            sum = 0;
        }
        System.out.println(index);
    }
}