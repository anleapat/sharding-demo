package com.shardingdemo.application.order;

import com.shardingdemo.domain.order.OrderDomainService;
import com.shardingdemo.domain.order.entity.Order;
import com.shardingdemo.domain.order.entity.OrderItem;
import com.shardingdemo.infrastructure.aop.ServiceMethodLog;
import com.shardingdemo.infrastructure.aop.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDomainService orderDomainService;

    @TargetDataSource
    @ServiceMethodLog
    public Order getOrder(Long id) {
        return orderDomainService.getOrder(id);
    }

    @ServiceMethodLog
    public int saveOrder(Order order) {
        return orderDomainService.saveOrder(order);
    }

    @ServiceMethodLog
    public int updateOrder(Order order) {
        return orderDomainService.updateOrder(order);
    }

    @ServiceMethodLog
    public int removeOrder(Long id) {
        return orderDomainService.removeOrder(id);
    }

    @ServiceMethodLog
    public OrderItem getOrderItem(Long id) {
        return orderDomainService.getOrderItem(id);
    }

    @ServiceMethodLog
    public int saveOrderItem(OrderItem orderItem) {
        return orderDomainService.saveOrderItem(orderItem);
    }

    @ServiceMethodLog
    public List<OrderItem> selectOrderItem(OrderItem query) {
        return orderDomainService.selectOrderItem(query);
    }

    @ServiceMethodLog
    public int updateOrderItem(OrderItem orderItem) {
        return orderDomainService.updateOrderItem(orderItem);
    }

    @ServiceMethodLog
    public int removeOrderItem(Long id) {
        return orderDomainService.removeOrderItem(id);
    }
}
