package com.Fegin;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于核心内部调用接口
 * 采用fegin作为调用支持熔断和负载均衡
 *
 * @author zhouxl
 */
@FeignClient(value = "storage",url = "http://127.0.0.1:8001", fallback = Test4Fallback.class)
public interface test4 {

        @RequestMapping(value = "/storage", method = RequestMethod.POST)
        String storage(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count);
}
