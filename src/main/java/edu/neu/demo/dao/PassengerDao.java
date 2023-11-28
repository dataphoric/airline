package edu.neu.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void savePassengerInfo(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getPassengerInfo(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deletePassengerInfo(String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
