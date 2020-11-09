package com.cedis.core;

import com.cedis.CedisApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/10
 * @desc todo
 */
public class RedisTemplateTest extends CedisApplicationTests {

    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    public void bgSave () {
        redisTemplate.getRequiredConnectionFactory().getConnection().bgSave();

    }
}
