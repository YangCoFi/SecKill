package com.yangcofi.Seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;
    //JedisPool的实例注入到spring容器里面
    @Bean
    public JedisPool JedisPoolFactory() {       //@Bean 用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        System.out.println("redisConfig.getPoolMaxldle():"+redisConfig.getPoolMaxldle());
        System.out.println("redisConfig.getPoolMaxTotal():"+redisConfig.getPoolMaxTotal());
        System.out.println("redisConfig.getPoolMaxWait():"+redisConfig.getPoolMaxWait());
        System.out.println("redisConfig.getPassword():"+redisConfig.getPassword());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxldle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);//s-->ms
        //因为我们使用的是s（秒）来配置的，而源码使用的是ms（毫秒），所以转换一下
        JedisPool jp=new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),
                redisConfig.getTimeout()*1000,redisConfig.getPassword(),0);         //redis默认是16个库 你不指定 就默认从零开始
        return jp;
    }
}

