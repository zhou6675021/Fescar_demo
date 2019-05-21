package com.Fegin;

import org.springframework.stereotype.Component;

@Component
public class Test4Fallback  implements test4{
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    @Override
    public String storage(String commodityCode, int count) {
        return FAIL;
    }
}
