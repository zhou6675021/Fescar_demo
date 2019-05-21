package com.Controller;

import com.Fegin.test3;
import com.Fegin.test4;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoFegin {
    //状态
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    //用户ID
    private static final String USER_ID = "U100001";
    //商品的代码
    private static final String COMMODITY_CODE = "C00321";
    //订单数量
    private static final int ORDER_COUNT = 2;
    @Autowired
     test3 t1;
    @Autowired
    test4 t2;
    @GlobalTransactional(timeoutMills = 300000, name = "test")
    @RequestMapping(value = "/demo" ,method = RequestMethod.GET,produces = "application/json")
    public String  getdemo(){
        //先计算商品
       String result = t2.storage(COMMODITY_CODE,ORDER_COUNT);

       if(!SUCCESS.equals(result)){
           throw new RuntimeException();
       }
       result = t1.order(USER_ID,COMMODITY_CODE,ORDER_COUNT);

        if(!SUCCESS.equals(result)){
            throw new RuntimeException();
        }
       return SUCCESS;
    }
}
