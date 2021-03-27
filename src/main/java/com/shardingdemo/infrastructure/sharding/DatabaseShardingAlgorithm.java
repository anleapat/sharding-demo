package com.shardingdemo.infrastructure.sharding;

import com.shardingdemo.infrastructure.constants.ShardingDemoConstant;
import com.shardingdemo.infrastructure.util.DataSourceMsContextHolder;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    private final static String MASTER_DB_PREFIX = "master";

    private final static String SLAVE_DB_PREFIX = "slave";

    @Override
    public String doSharding(Collection<String> targetDbs, PreciseShardingValue<Long> preciseShardingValue) {
        // sharding读写分离有问题，如果写表和读表如果在同一个动作中
        // 程序执行的速度很有可能比binlog同步还快,刚写入的数据查从库可能查不到
        // 同步一般在不同服务器，会有网络时延
        // 这里可根据DataSourceMsContextHolder.getDataSourceType()标识来判断切换主还是从
        Long orderId = preciseShardingValue.getValue();
        Long mod = orderId % 2;
        for (String ds : targetDbs) {
            if (isMaster(ds, mod) || isSlave(ds, mod)) {
                return ds;
            }
        }
        return null;
    }

    private boolean isMaster(String ds, Long mod) {
        return !ShardingDemoConstant.SLAVE_DB_PREFIX.equals(DataSourceMsContextHolder.getDataSourceType())
                && ds.startsWith(ShardingDemoConstant.MASTER_DB_PREFIX)
                && ds.endsWith(mod.toString());
    }

    private boolean isSlave(String ds, Long mod) {
        return ShardingDemoConstant.SLAVE_DB_PREFIX.equals(DataSourceMsContextHolder.getDataSourceType())
                && ds.startsWith(ShardingDemoConstant.SLAVE_DB_PREFIX)
                && ds.endsWith(mod.toString());
    }
}
