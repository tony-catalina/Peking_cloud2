package awd.mis.servers.tools;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.entity.*;

import awd.mis.servers.model.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CacheUtils extends ConstantUtils {

    private static RedisUtils redisUtils;
    private static CacheUtils instance;

    public CacheUtils() {
        super();
        redisUtils = (RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
    }

    public static final String CACHE_M_FINGER = "CACHE_M_FINGER";
    public static final String CACHE_M_IRIS = "CACHE_M_IRIS";
    public static final String CACHE_M_FACE = "CACHE_M_FACE";
    public static final String CACHE_Z_FINGER = "CACHE_Z_FINGER";
    public static final String CACHE_Z_IRIS = "CACHE_Z_IRIS";
    public static final String CACHE_Z_FACE = "CACHE_Z_FACE";

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


    public String getClassfic(String lx, String classid) {
        String key = CACHE_CLASSFIC + lx.toUpperCase() + "_" + classid.toUpperCase();
        String value = redisUtils.get(key).toString();
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


    public String getMenu(String menu) {
        String key = CACHE_MENUS + "_" + menu.toUpperCase();
        JSONObject object = JSONObject.parseObject(redisUtils.get(key).toString());
        if (object != null) {
            return object.getString("menuname");
        } else {
            return "";
        }
    }


    public void saveDictionary(DictionaryEntity dictionaryEntity) {
        String key = CACHE_DICTIOANRY + dictionaryEntity.getFieldname().toUpperCase() + "_" + dictionaryEntity.getCode().toUpperCase();
        String value = JSONObject.toJSONString(dictionaryEntity);
        redisUtils.set(key, value);
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


    public void saveDictionaryFild(String fieldstr) {
        redisUtils.set(CACHE_DICTIOANRY_FIELD, fieldstr);
    }


    public void saveApp(AppEntity appEntity) {
        String key = CACHE_APP + appEntity.getAppcode().toUpperCase();
        String value = JSONObject.toJSONString(appEntity);
        redisUtils.set(key, value);
    }

    public void setAppCacheWithoutByte(AppEntity appEntity, String appcode) {//不保存byte,只保存部分属性——是为我我下面的getAppEntity方法从缓存获取数据
        String key = "CACHE_KEY_APP_WITHOUTBYTE" + appEntity.getAppcode().toUpperCase();
        String value = JSONObject.toJSONString(appEntity);
        redisUtils.set(key, value);
    }

    public AppEntity getAppEntity(String appcode) {
        String key = "CACHE_KEY_APP_WITHOUTBYTE" + appcode.toUpperCase();
        JSON json = JSONObject.parseObject(redisUtils.get(key));
        if (!StringUtils.isNullOrEmpty(json)) {
            AppEntity appEntity = JSONObject.toJavaObject(json, AppEntity.class);
            return appEntity;
        } else {
            return null;
        }
    }

    //接口缓存
    public void setInterfaceCache(InterfaceEntity entity) {//不保存byte,只保存部分属性——是为我我下面的getAppEntity方法从缓存获取数据
        String key = "CACHE_KEY_INTERFACE" + entity.getInterfaceId().toUpperCase();
        String value = JSONObject.toJSONString(entity);
        redisUtils.set(key, value);
    }

    //接口权限方法
    public void setInterfacebindingCache(InterfacebindingEntity entity) {//不保存byte,只保存部分属性——是为我我下面的getAppEntity方法从缓存获取数据
        String key = "CACHE_KEY_INTERFACEBINDING" + entity.getAppcode().toUpperCase() + entity.getInterfaceId().toUpperCase();
        String value = JSONObject.toJSONString(entity);
        redisUtils.set(key, value);
    }

    //缓存所有表信息
    public void setTableInfoCache(TableinfoEntity entity) {
        String key = "CACHE_KEY_TABLEINFO" + entity.getUsername().toUpperCase() + entity.getTablename().toUpperCase();
        String value = JSONObject.toJSONString(entity);
        redisUtils.set(key, value);
    }

    public MfaceModel getMface(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_FACE + sfzh + "*");
        MfaceModel mfaceModel = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mfaceModel = JSONUtil.toBean(json, MfaceModel.class);
        }
        return mfaceModel;
    }

    public ZfaceModel getZface(String rybh) {
        if (StringUtils.isNullOrEmpty(rybh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_Z_FACE + rybh + "*");
        ZfaceModel zfaceModel = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            zfaceModel = JSONUtil.toBean(json, ZfaceModel.class);
        }
        return zfaceModel;
    }

    public List<MfingerModel> getMfingerModel(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_FINGER + sfzh + "*");
        List<MfingerModel> mfingerModels = Lists.newArrayList();
        //多条只取一条数据
        for (String s : set) {
            MfingerModel mfingerModel = new MfingerModel();

            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mfingerModel = JSONUtil.toBean(json, MfingerModel.class);
            mfingerModels.add(mfingerModel);
        }
        return mfingerModels;
    }

    public ZfingerModel getZfingerModel(String rybh) {
        if (StringUtils.isNullOrEmpty(rybh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_Z_FINGER + rybh + "*");
        ZfingerModel zfingerModel = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            zfingerModel = JSONUtil.toBean(json, ZfingerModel.class);
        }
        return zfingerModel;
    }

    public List<MirisModel> getMirisModel(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_IRIS + sfzh + "*");
        List<MirisModel> mIrisModels = Lists.newArrayList();
        //多条只取一条数据
        for (String s : set) {
            MirisModel mirisModel = new MirisModel();
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mirisModel = JSONUtil.toBean(json, MirisModel.class);
            mIrisModels.add(mirisModel);
        }
        return mIrisModels;
    }
    public String getMirisUrl(String sfzh,String hmbh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_IRIS + sfzh + "*");
        for (String s : set) {
            MirisModel mirisModel = new MirisModel();
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mirisModel = JSONUtil.toBean(json, MirisModel.class);
            if(hmbh.equals(mirisModel.getHmwzbh())){
                return mirisModel.getHmtxurl();
            }
        }
        return "";
    }

    public List<ZirisModel> getZirisModel(String rybh) {
        if (StringUtils.isNullOrEmpty(rybh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_FACE + rybh + "*");
        List<ZirisModel> zirisModels = Lists.newArrayList();
        //多条只取一条数据
        for (String s : set) {
            ZirisModel zirisModel = new ZirisModel();
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            zirisModel = JSONUtil.toBean(json, ZirisModel.class);
            zirisModels.add(zirisModel);
        }
        return zirisModels;
    }

    public List<JSONObject> getCacheList(String name) {

        Set<String> stringSet = getKeys(name);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        stringSet.forEach(p -> jsonObjectList.add(JSONObject.parseObject(redisUtils.get(p).toString())));
        return jsonObjectList;
    }
}
