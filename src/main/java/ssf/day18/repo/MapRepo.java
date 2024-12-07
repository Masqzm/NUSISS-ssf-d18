package ssf.day18.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ssf.day18.config.Constants;

@Repository
public class MapRepo {
    @Autowired @Qualifier(Constants.REDIS_TEMPLATE_01)
    private RedisTemplate<String, String> redisTemplate;

    public void createHash(String key, String hashKey, String hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    public String getHashValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey).toString();
    }

    // Returns hashmap stored at this key
    public List<Object> getValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    public void deleteHashKey(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public Boolean hashKeyExists(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public Long size(String key) {
        return redisTemplate.opsForHash().size(key);
    }
}