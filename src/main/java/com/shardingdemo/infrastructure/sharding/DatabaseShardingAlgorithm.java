package com.shardingdemo.infrastructure.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> targetDbs, PreciseShardingValue<Long> preciseShardingValue) {
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
