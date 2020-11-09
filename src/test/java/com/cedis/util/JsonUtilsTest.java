package com.cedis.util;

import com.cedis.db.entity.RedisInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/24
 * @desc JsonUtilsTest
 */
public class JsonUtilsTest {

    @Test
    public void object2json() {
        RedisInfo redisInfo = new RedisInfo(1, "实例1", "4.0.6", new Date(), null);
        String json = JsonUtils.object2json(redisInfo);
        System.out.println(json);
    }

    @Test
    public void object2jsonPretty() {
        RedisInfo redisInfo = new RedisInfo(1, "实例1", "4.0.6", new Date(), LocalDateTime.now());
        String json = JsonUtils.object2jsonPretty(redisInfo);
        System.out.println(json);
    }

    @Test
    public void json2object() {
        String json = "{\"id\":1,\"name\":\"实例1\",\"createTime\":\"2020-08-25 22:54:48\",\"updateTime\":\"2020-08-25 22:54:48\",\"rversion\":\"4.0.6\"}";
        RedisInfo redisInfo = JsonUtils.json2object(json, RedisInfo.class);
        System.out.println(JsonUtils.object2json(redisInfo));
    }

    @Test
    public void jsonList() {
        RedisInfo redisInfo = new RedisInfo(1, "实例1", "4.0.6", new Date(), LocalDateTime.now());
        RedisInfo redisInfo1 = new RedisInfo(2, "实例2", "4.0.6", new Date(), LocalDateTime.now());
        RedisInfo redisInfo2 = new RedisInfo(3, "实例3", "4.0.6", new Date(), LocalDateTime.now());
        List<RedisInfo> list = Arrays.asList(redisInfo,redisInfo1,redisInfo2);
        String json = JsonUtils.list2json(list);
        System.out.println(json);
        List<RedisInfo> redisInfos = JsonUtils.json2list(json, RedisInfo.class);
        System.out.println(JsonUtils.list2json(redisInfos));
    }
}
