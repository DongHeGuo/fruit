package com.boot.common.utils;

import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author ziv
 * @date 2019-04-16
 */
@Component
public class RedisUtils {

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 30;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, String> valueOperations;

    /**
     * 设置超时时间
     * @param key key值
     * @param expire 超时时间
     */
    public void setExpire(String key, long expire){
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置超时时间（默认时长）
     * @param key key值
     */
    public void setExpire(String key){
        redisTemplate.expire(key, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    /**
     * 设置键值对
     * @param key key值
     * @param value val值
     * @param expire 超时时间
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置键值对（默认时长）
     * @param key key值
     * @param value val值
     */
    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 从redis获取值
     * @param key key值
     * @param clazz 对象类型
     * @param expire 超时时间
     * @param <T> 对象类型
     * @return 对象实体
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 从redis获取对象（永久有效的值）
     * @param key key值
     * @param clazz 对象类型
     * @param <T> 对象类型
     * @return 对象实体
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 从redis获取值
     * @param key key值
     * @param expire 超时时间
     * @return String
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 从redis获取值(永久有效的值)
     * @param key key值
     * @return String
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 从redis删除
     * @param key key值
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 对象转json字符串
     * @param object 对象实体
     * @return String
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }

        return JSONUtil.toJsonStr(object);
    }

    /**
     * json字符串转对象
     * @param json json字符串
     * @param clazz 对象类
     * @param <T> 对象类型
     * @return 对象实体
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return JSONUtil.toBean(json, clazz);
    }
}
