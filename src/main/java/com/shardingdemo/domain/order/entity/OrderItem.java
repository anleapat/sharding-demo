package com.shardingdemo.domain.order.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* Created by Mybatis Generator 2021/03/05
*/
@Data
public class OrderItem implements Serializable {
    private Long orderItemId;

    private Long orderId;

    private String orderItem;

    private Date createDate;

    private Date lastUpdateDate;

    private Integer lastUpdateVersion;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("orderItemId=").append(orderItemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderItem=").append(orderItem);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", lastUpdateVersion=").append(lastUpdateVersion);
        sb.append("]");
        return sb.toString();
    }
}