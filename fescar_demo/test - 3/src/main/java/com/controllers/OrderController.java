package com.controllers;

import com.alibaba.fescar.core.context.RootContext;


import com.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.ctrip.framework.apollo.tracer.spi.Transaction.SUCCESS;

/**
 * 订单接口
 * @author zhouxl
 */
@RestController
public class OrderController {
    @Resource
    private  JdbcTemplate jdbcTemplate;

    private Random random;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private  static final Logger logger=LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json")
    public String order(String userId, String commodityCode, int orderCount) {
        logger.info("订单开始了，其全局ID是: " + RootContext.getXID());

        int orderMoney = calculate(commodityCode, orderCount);

        Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement pst = con.prepareStatement(
                        "insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setObject(1, order.userId);
                pst.setObject(2, order.commodityCode);
                pst.setObject(3, order.count);
                pst.setObject(4, order.money);
                return pst;
            }
        }, keyHolder);

        order.id = keyHolder.getKey().longValue();

//        if (random.nextBoolean()) {
//            throw new RuntimeException("this is a mock Exception");
//        }

        logger.info("订单服务结束 " + order);

        if (result == 1) {
            return SUCCESS;
        }
        return FAIL;
    }

    //每次金额2块
    private int calculate(String commodityId, int orderCount) {
        return 2 * orderCount;
    }

}
