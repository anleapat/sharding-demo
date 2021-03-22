package com.shardingdemo.infrastructure.sharding;

import com.shardingdemo.infrastructure.util.DataSourceMsContextHolder;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> targetDbs, PreciseShardingValue<Long> preciseShardingValue) {
        // sharding读写分离有问题，如果写表和读表如果在同一个方法中
        // 程序执行的速度很有可能比binlog同步还快
        // 同步一般在不同服务器，会有网络时延
        // 这里可根据此标识来判断切换主还是从
        System.out.println(DataSourceMsContextHolder.getDataSourceType());
        Long orderId = preciseShardingValue.getValue();
        Long mod = orderId % 2;
        for (String ds : targetDbs) {
            if (ds.endsWith(mod.toString())) {
                return ds;
            }
        }
        return null;
    }
}
