package ssf.day18.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ssf.day18.config.Constants;

@Repository
public class ValueRepo {
    @Autowired
    @Qualifier(Constants.REDIS_TEMPLATE_01)
    RedisTemplate<String, String> redisTemplate;

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void incrementValue(String key) {
        redisTemplate.opsForValue().increment(key);
    }
    public void incrementByValue(String key, Integer incrementVal) {
        redisTemplate.opsForValue().increment(key, incrementVal);
    }

    public void derementValue(String key) {
        redisTemplate.opsForValue().decrement(key);
    }
    public void derementByValue(String key, Integer decrementVal) {
        redisTemplate.opsForValue().decrement(key, decrementVal);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public Boolean checkKeyExists(String key) {
        return redisTemplate.hasKey(key);
    }
}
