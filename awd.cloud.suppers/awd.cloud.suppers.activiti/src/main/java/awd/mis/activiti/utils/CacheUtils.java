package awd.mis.activiti.utils;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.ConstantUtils;
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


    public JSONObject getFlowMap(String jsbh, String mapkey) {
        return null;
    }


    public String getJsbhString(String jsbh) {
        String value = redisUtils.get(CACHE_KSS_KSS + jsbh).toString();
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
            return object.getString("kssmc");
        } else {
            return "";
        }
    }


    public String getClassfic(String classid) {
        String value = redisUtils.get(CACHE_CLASSFIC + classid).toString();
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
            return object.getString("name");
        } else {
            return "";
        }
    }


    public String getContentByFxpg(String string, String string2, String code) {
        return null;
    }


    public String getRole(String rolecode) {
        String key = CACHE_ROLE + rolecode.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("name");
        } else {
            return "";
        }
    }


    public String getApp(String appcode) {
        String key = CACHE_APP + appcode.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("name");
        } else {
            return "";
        }

    }


    public String getMenu(String appcode, String menu) {
        String key = CACHE_MENUS + appcode.toUpperCase() + "_" + menu.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("menuname");
        } else {
            return "";
        }
    }

    public String getDictionary(String fieldname, String code) {
        String key = CACHE_DICTIOANRY + fieldname.toUpperCase() + "_" + code.toUpperCase();
        String value = "";
        JSONObject object = null;
        value = redisUtils.get(key);
        object = JSONObject.parseObject(value);
        if (object != null) {
            return object.getString("content");
        } else {
            return "";
        }
    }


}
