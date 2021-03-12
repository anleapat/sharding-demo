package com.shardingdemo.domain.order.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* Created by Mybatis Generator 2021/03/05
*/
@Data
public class Order implements Serializable {
    private Long orderId;

    private String orderNo;

    private Long userId;

    private Date createDate;

    private Date lastUpdateDate;

    private Integer lastUpdateVersion;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", lastUpdateVersion=").append(lastUpdateVersion);
        sb.append("]");
        return sb.toString();
    }
}