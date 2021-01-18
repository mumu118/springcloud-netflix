package com.example.eurekacustomer.equator;

import com.github.dadiyang.equator.FieldInfo;
import com.github.dadiyang.equator.GetterBaseEquator;

import java.util.Objects;

public class FieldEquator extends GetterBaseEquator {

    @Override
    protected boolean isFieldEquals(FieldInfo fieldInfo) {
        if (fieldInfo.getFirstVal() instanceof String) {
            String first = ((String) fieldInfo.getFirstVal()).replaceAll(" ","").toLowerCase();
            String second = ((String) fieldInfo.getSecondVal()).replaceAll(" ","").toLowerCase();
            if (Objects.equals(first, second)) {
                return true;
            }
        }
        return super.isFieldEquals(fieldInfo);
    }
}
