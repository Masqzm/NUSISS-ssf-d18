package ssf.day18.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ssf.day18.config.Constants;

@Repository
public class ListRepo {
    @Autowired
    @Qualifier(Constants.REDIS_TEMPLATE_01)
    RedisTemplate<String, String> redisTemplate;

    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void rightPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public String leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public String getByIndex(String key, Integer index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public List<String> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}