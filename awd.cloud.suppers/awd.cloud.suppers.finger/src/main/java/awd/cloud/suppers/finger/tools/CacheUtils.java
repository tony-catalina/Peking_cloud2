package awd.cloud.suppers.finger.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;

public class CacheUtils {
	
	public static final String CACHE_KEY_FINGER = "cloud_finger";

	private static RedisUtils redisUtils;
	private static CacheUtils instance;

	
	public CacheUtils() {
		super();
		redisUtils = (RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
	}

	public static CacheUtils get() {
		if (instance == null) {
			return new CacheUtils();
		}
		return instance;
	}

	public  void del(String key) {
		redisUtils.remove(key);
	}

	public  Object getKey(String key) {
		return redisUtils.get(key);
	}

	public  Set<String> getKeys(String key) {
		return redisUtils.getkeys(key);
	}

	public  void set(String key, String jsonString) {
		System.err.println("key---"+key);
		System.err.println("jsonString---"+jsonString);
		redisUtils.set(key, jsonString);
	}


	public  void removePattern(String pattern) {
		redisUtils.removePattern(pattern);
	}

	
	//以下是 用到的 缓存方法
	
	public static void add(String key, Object value) {
		String cache_key = CACHE_KEY_FINGER + "_" + key;
		CacheUtils.get().set(cache_key , JSONObject.toJSONString(value));
		//redisUtils.set(cache_key, JSONObject.toJSONString(value));
	}
	
	public static void addZwtzmCache(String rybhOrZjh,String[] fingerdata,byte[] fingercode) {
		String cache_key = CACHE_KEY_FINGER + "_" + rybhOrZjh;
		
		List<String> tzmlist=new ArrayList<String>();
		tzmlist.add(fingerdata[0].replace("\"", ""));
		tzmlist.add(fingerdata[1].replace("\"", ""));
		tzmlist.add(fingercode[0]+"");
		tzmlist.add(fingercode[1]+"");
		Map<String,Object> tzmMap = new HashMap<String,Object>();
		tzmMap.put(rybhOrZjh,tzmlist);
		//redisUtils.set(cache_key, JSONObject.toJSONString(tzmMap));
		CacheUtils.get().set(cache_key, JSONObject.toJSONString(tzmMap));
	}
	
	
	public static void delete(String key) {
		String cache_key = CACHE_KEY_FINGER + "_" + key;
		CacheUtils.get().del(cache_key);
	}
	
	public static void deleteAll() {
		String cache_key = CACHE_KEY_FINGER + "*";
		CacheUtils.get().removePattern(cache_key);
	}
	
	public static Map<String,Object> getMap(String key) {
		String cache_key = CACHE_KEY_FINGER + "_" + key;
		Map<String, Object> re =Maps.newHashMap();
		JSONObject obj = JSONObject.parseObject(CacheUtils.get().getKey(cache_key).toString());
		re.put(key, obj.get(key));
		return re;
	}
	
	
}
