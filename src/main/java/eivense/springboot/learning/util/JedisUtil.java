package eivense.springboot.learning.util;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

public class JedisUtil {


    @Autowired
    public JedisPool jedisPool;


}
