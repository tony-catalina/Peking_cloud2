package awd.cloud.platform.servers.analyse.utils;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	public static Set<String> getKeys(String key) {
		return redisUtils.getkeys(key);
	}


	public void set(String key, String jsonString) {
		redisUtils.set(key, jsonString);
	}

	public void removePattern(String pattern) {
		redisUtils.removePattern(pattern);
	}



	/**
	 * 根据类型 条件获取所有字典名称
	 *
	 * @param type
	 * @param filter
	 * @return
	 */
	public String getDictionarys(String type, String filter) {
		List<JSONObject> result = null;
		List<JSONObject> list = null;
		String content= "";
		Set<String> keys = getKeys(CACHE_DICTIOANRY + type);
		if (keys != null && keys.size() > 0) {
			list = new ArrayList<JSONObject>();
			for (String key : keys) {
				list.add(JSONObject.parseObject(redisUtils.get(key)));
			}
		}
		if (!StringUtils.isNullOrEmpty(filter)) {
			result = new ArrayList<JSONObject>();

			for (JSONObject object : list) {

				if (filter.contains(object.getString("code"))) {

					result.add(object);
					content = object.get("content").toString()+content+",";
				}
			}
		}
		return content;
	}


    /**
	 * 根据监所编号 获取中文名(kss)
	 *
	 * @param jsbh
	 * @return
	 */
	public String getJsbhString(String jsbh) {
		String value = redisUtils.get(CACHE_KSS_KSS + jsbh).toString();
		JSONObject object = JSONObject.parseObject(value);
		if (object != null) {

			return object.getString("kssmc");
		} else {
			return "";
		}
	}

	/**
	 * 根据监所编号 获取中文名(jls)
	 *
	 * @param jsbh
	 * @return
	 */
	public String getJlsJsbhString(String jsbh) {
		String value = redisUtils.get("jsbh_jls_jsbh_" + jsbh).toString();
		JSONObject object = JSONObject.parseObject(value);
		if (object != null) {

			return object.getString("jlsmc");
		} else {
			return "";
		}
	}

    /**
     * 根据jsbh获取KSS里面的名称
     *
     * @param jsbh
     * @return
     */
    public static String getKssmcByKss(String jsbh) {
        if (jsbh == null) return "";
        String rediskey = CACHE_KSS_KSS + jsbh;

        String value = redisUtils.get(rediskey);

        if (StringUtils.isNullOrEmpty(value)) return "";
        JSONObject json = JSONObject.parseObject(value);

        return json == null ? "" : json.getString("kssmc");
    }



    /**
	 * @param code
	 * @param jslx 监所类型
	 * @Description: 获取风险评估CONTENT值
	 * @return: java.lang.String
	 * @Author: 王帅
	 * @Date: 2018/4/13 19:33
	 **/
	public static String getFxpgContent(String code, String jslx) {

		String key = CacheUtils.CACHE_FXPG + code + "_" + jslx;
		JSONObject value = JSONObject.parseObject(redisUtils.get(key));
		String content = "";
		if (value.get("content") != null) {
			content = value.get("content").toString();
		}

		return content;
	}



	/**
	 * 获取各个节点的待办数量
	 *
	 * @param processDefinitionKey 列如:kss_lscs
	 * @param jsbh
	 * @return
	 */
	public  Map<String, String> getFlowNodeCount(String processDefinitionKey, String jsbh) {
		Set<String> allkey = getKeys(CACHE_FLOWNODE_COUNT);
		if (allkey == null || allkey.size() == 0) {
			return null;
		}
		Map<String, String> map = Maps.newHashMap();
		allkey.stream().filter(p -> p.indexOf(jsbh + "_" + processDefinitionKey) > 0).forEach(b -> {
			String s = redisUtils.get(b);
			map.put(b.substring(24), s);

		});
		return map;
	}

}
