package com.controllers;

import com.alibaba.fescar.core.context.RootContext;


import com.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * 订单接口
 * @author zhouxl
 */
@RestController
@Slf4j
public class StorageController {
    @Resource
    private  JdbcTemplate jdbcTemplate;

    private Random random;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private  static final Logger logger=LoggerFactory.getLogger(StorageController.class);

    @RequestMapping(value = "/storage", method = RequestMethod.POST)
    public String echo(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count) {
        logger.info("商品任务开始了，全局ID是: " + RootContext.getXID());
        System.out.println("商品日志被调用");
        log.info("商品被log调用！");
//        return FAIL;
        int result = jdbcTemplate.update(
                "update storage_tbl set count = count - ? where commodity_code = ?",
                new Object[] { count, commodityCode });
        logger.info("商品任务结束了 ... ");
        if (result == 1) {
            return SUCCESS;
        }
        return FAIL;
    }

}
