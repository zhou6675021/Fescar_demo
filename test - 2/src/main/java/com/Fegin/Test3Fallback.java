package com.Fegin;

import org.springframework.stereotype.Component;

@Component
public class Test3Fallback implements test3 {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    @Override
    public String order(String userId, String commodityCode, int orderCount) {
        return FAIL;
    }
}
