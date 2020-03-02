package awd.mis.servers.utils;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.rabbitmq.model.KssMessage;
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

    public boolean setBadMsg(KssMessage messageModel, String messageId) {
        try {

            String key = CACHE_BADMSG + messageId;
            String numKey = CACHE_BADMSGNUM + messageId;
            redisUtils.set(key, JSONObject.toJSONString(messageModel));
            redisUtils.set(numKey, 1 + "");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setBadMsgNum(String id) {
        try {
            String numKey = CACHE_BADMSGNUM + id;
            String value = redisUtils.get(numKey);
            int num = 0;
            if (!StringUtils.isNullOrEmpty(value)) {
                num = Integer.parseInt(value);
            }
            redisUtils.set(numKey, 1 + num + "");
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getBadMsgNum(String id) {
        try {
            String numKey = CACHE_BADMSGNUM + id;
            String num = redisUtils.get(numKey);
            if (StringUtils.isNullOrEmpty(num)) {
                return 0;
            }
            return Integer.parseInt(num);
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }
    }

    public String getBadMsg(String id) {
        String key = CACHE_BADMSG + id;
        String content = redisUtils.get(key);
        if (StringUtils.isNullOrEmpty(content)) {
            return "";
        }
        return content;
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
        if (rolecode == null) {
            return "";
        }
        String key = CacheUtils.CACHE_ROLE + rolecode.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("name");
        } else {
            return "";
        }
    }


    public String getApp(String appcode) {
        String key = CacheUtils.CACHE_APP + appcode.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("name");
        } else {
            return "";
        }

    }


    public String getMenu(String appcode, String menu) {
        String key = CacheUtils.CACHE_MENUS + appcode.toUpperCase() + "_" + menu.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("menuname");
        } else {
            return "";
        }
    }


    public String getDictionary(String fieldname, String code) {
        if (StringUtils.isNullOrEmpty(code)) {
            return "";
        }
        if (StringUtils.isNullOrEmpty(fieldname)) {
            return "";
        }
        String key = CacheUtils.CACHE_DICTIOANRY + fieldname.toUpperCase() + "_" + code.toUpperCase();
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


    public void saveDictionaryFild(String fieldstr) {
        redisUtils.set(CacheUtils.CACHE_DICTIOANRY_FIELD, fieldstr);
    }


}
