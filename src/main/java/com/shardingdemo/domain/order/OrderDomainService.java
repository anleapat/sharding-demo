package com.shardingdemo.domain.order;

import com.shardingdemo.domain.order.entity.Order;
import com.shardingdemo.domain.order.entity.OrderItem;
import com.shardingdemo.domain.order.mapper.OrderItemMapper;
import com.shardingdemo.domain.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDomainService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    public Order getOrder(Long id) {
        return orderMapper.get(id);
    }

    public int saveOrder(Order order) {
        return orderMapper.insert(order);
    }

    public int updateOrder(Order order) {
        return orderMapper.update(order);
    }

    public int removeOrder(Long id) {
        return orderMapper.delete(id);
    }

    public OrderItem getOrderItem(Long id) {
        return orderItemMapper.get(id);
    }

    public int saveOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    public List<OrderItem> selectOrderItem(OrderItem query) {
        return orderItemMapper.select(query);
    }

    public int updateOrderItem(OrderItem orderItem) {
        return orderItemMapper.update(orderItem);
    }

    public int removeOrderItem(Long id) {
        return orderItemMapper.delete(id);
    }
}
