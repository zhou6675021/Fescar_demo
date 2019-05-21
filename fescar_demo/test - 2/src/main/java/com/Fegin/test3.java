package com.Fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 作为订单接口服务
 * @author zhouxl
 */
@FeignClient(value = "order", url = "http://127.0.0.1:8000",fallback = Test3Fallback.class)
public interface test3 {

        @RequestMapping(path = "/order", method = RequestMethod.POST)
        String order(@RequestParam("userId") String userId,
                     @RequestParam("commodityCode") String commodityCode,
                     @RequestParam("orderCount") int orderCount);

}
