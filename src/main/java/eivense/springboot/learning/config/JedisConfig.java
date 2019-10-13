package eivense.springboot.learning.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
@Data
@PropertySource("classpath:redis-config.yml")
public class JedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public JedisPool redisPoolFactory(){
        try{
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);
            log.info("初始化Redis连接池JedisPool成功!地址: " + host + ":" + port);
            return jedisPool;
        }catch (Exception e){
            log.error("初始化Redis连接池失败",e);
        }
        return null;
    }
}
