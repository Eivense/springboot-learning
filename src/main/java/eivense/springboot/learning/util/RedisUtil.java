package eivense.springboot.learning.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@Component
public class RedisUtil {


    @Autowired
    public JedisPool jedisPool;


    public synchronized Jedis getJedis(){
        try{
            if(jedisPool!=null){
                return jedisPool.getResource();
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("获取Jedis资源失败",e);
            throw e;
        }
    }

    public String set(String key,String value){
        try(Jedis jedis=getJedis()){
            return jedis.set(key,value);
        }
    }


    public String get(String key){
        try(Jedis jedis=getJedis()){
            return jedis.get(key);
        }
    }

}
