package awd.mis.servers.utils;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public class CacheUtils extends ConstantUtils {


    private static RedisUtils redisUtils;

    private static CacheUtils instance;

    public CacheUtils() {
        super();
        redisUtils = (RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
    }


    public static CacheUtils get() {
        return new CacheUtils();
    }

    public void del(String key) {
        redisUtils.remove(key);
    }

    public Object getKey(String key) {
        return redisUtils.get(key);
    }

    public Set<String> getKeys(String key) {
        return redisUtils.getkeys(key);
    }

    public void set(String key, String jsonString) {
        redisUtils.set(key, jsonString);
    }


    public void removePattern(String pattern) {
        redisUtils.removePattern(pattern);
    }

}
