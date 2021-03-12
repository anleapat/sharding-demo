package com.shardingdemo.domain.order.mapper;

import com.shardingdemo.domain.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int delete(Long orderItemId);

    int insert(OrderItem record);

    OrderItem get(Long orderItemId);

    List<OrderItem> select(OrderItem record);

    int update(OrderItem record);
}