//package com.bmw.common.autoconfigure;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Repository;
//
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Redis配置
// * @author lyl
// * 2018年5月14日
// */
//@Configuration
//@Repository
//
//public class RedisConfiguration{
//
//	@Value("${spring.redis.config.base.maxTotal}")
//	private int maxTotal;
//
//	@Value("${spring.redis.config.base.maxIdle}")
//	private int maxIdle;
//
//	@Value("${spring.redis.config.base.minIdle}")
//	private int minIdle;
//
//    @Value("${spring.redis.config.base.numTestsPerEvictionRun}")
//    private int numTestsPerEvictionRun;
//
//    @Value("${spring.redis.config.base.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//
//    @Value("${spring.redis.config.base.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//
//    @Value("${spring.redis.config.base.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${spring.redis.config.base.testOnReturn}")
//    private boolean testOnReturn;
//
//    @Value("${spring.redis.config.base.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${spring.redis.config.base.maxWaitMillis}")
//    private int maxWaitMillis;
//
//    @Value("${spring.redis.config.business.host}")
//    private String host;
//
//    @Value("${spring.redis.config.business.port}")
//    private int port;
//
//    @Value("${spring.redis.config.business.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.config.business.password}")
//    private String password;
//
//
//	@Bean("jedisPool")
//	@ConditionalOnMissingBean
//    public JedisPool jedisPool() {
//
//		JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(maxTotal);
//        config.setMaxIdle(maxIdle);
//        config.setMinIdle(minIdle);
//        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
//        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        config.setTestOnBorrow(testOnBorrow);
//        config.setTestOnReturn(testOnReturn);
//        config.setTestWhileIdle(testWhileIdle);
//        config.setMaxWaitMillis(maxWaitMillis);
//
//        JedisPool jedisPool = new JedisPool(config, host, port, timeout, password);
//        return jedisPool;
//    }
//
//}