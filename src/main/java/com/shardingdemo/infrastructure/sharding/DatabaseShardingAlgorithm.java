package com.shardingdemo.infrastructure.sharding;

import com.shardingdemo.infrastructure.constants.ShardingDemoConstant;
import com.shardingdemo.infrastructure.util.DataSourceMsContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Slf4j
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
            boolean isMaster = selectMaster(ds, mod);
            boolean isSlave = selectSlave(ds, mod);
            if (isMaster || isSlave) {
                log.info("current thread using datasource: {}", ds);
                return ds;
            }
        }
        return null;
    }

    private boolean selectMaster(String ds, Long mod) {
        return !ShardingDemoConstant.SLAVE_DB_PREFIX.equals(DataSourceMsContextHolder.getDataSourceType())
                && ds.startsWith(ShardingDemoConstant.MASTER_DB_PREFIX)
                && ds.endsWith(mod.toString());
    }

    private boolean selectSlave(String ds, Long mod) {
        return ShardingDemoConstant.SLAVE_DB_PREFIX.equals(DataSourceMsContextHolder.getDataSourceType())
                && ds.startsWith(ShardingDemoConstant.SLAVE_DB_PREFIX)
                && ds.endsWith(mod.toString());
    }
}
