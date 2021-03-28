package com.shardingdemo;

import com.shardingdemo.application.order.OrderService;
import com.shardingdemo.domain.order.entity.Order;
import com.shardingdemo.domain.order.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {
    @Autowired
    OrderService orderService;

    @Test
    public void addOrderTest() {
        Long orderId = null;
        for (long i = 1; i < 7; i++) {
            Order order = new Order();
            order.setUserId(i + 1000);
            order.setOrderNo(UUID.randomUUID().toString());
            orderService.saveOrder(order);
            log.info("order info={}", order);

            Order order1 = orderService.getOrder(order.getOrderId());
            log.info("get order1 info test={}", order1);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setOrderItem(UUID.randomUUID().toString());
            orderService.saveOrderItem(orderItem);
            log.info("orderItem info={}", orderItem);
            if (orderId == null) {
                orderId = order.getOrderId();
                getOrderTest(orderId);
            }
        }
    }

    private void getOrderTest(Long orderId) {
        for (int i = 0; i < 7; i++) {
            Order order = orderService.getOrder(orderId);
            log.info("get order info test={}", order);
            order.setLastUpdateDate(new Date());
            order.setOrderNo(UUID.randomUUID().toString());
            int result = orderService.updateOrder(order);
            log.info("update Order info test,result={}, order={}", result, order);
        }

        OrderItem query = new OrderItem();
        query.setOrderId(orderId);
        List<OrderItem> orderItems = orderService.selectOrderItem(query);
        orderItems.forEach(item -> log.info("select orderItem info test={}", item));
    }
}
