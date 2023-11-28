package edu.neu.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CrewDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //save crew into redis
    public void saveCrewInfo(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    //get crew from redis
    public Object getCrewInfo(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    //delete crew from redis
    public void deleteCrewInfo(String key) {
        redisTemplate.delete(key);
    }

    //verify whether there is a key in redis
    public boolean hasKey(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
