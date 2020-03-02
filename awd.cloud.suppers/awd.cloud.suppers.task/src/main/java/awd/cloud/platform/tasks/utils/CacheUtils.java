package awd.cloud.platform.tasks.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import awd.cloud.platform.tasks.api.AwdApi;
import awd.cloud.platform.tasks.model.DictionaryModel;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;


public class CacheUtils extends ConstantUtils{
	
	 @Autowired
     private AwdApi awdApi;
    
    public static Map<String, List<Map<String, String>>> cacheList = new HashMap<String, List<Map<String, String>>>();
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

    public static Object getKey(String key) {
        return redisUtils.get(key);
    }

    public static Set<String> getKeys(String key) {
        return redisUtils.getkeys(key);
    }

    public static void setCache(String key, List<Map<String, String>> value) {
        cacheList.put(key, value);
    }

    public static List<Map<String, String>> getCache(String key) {
        if (key == null || key == "") {
            return new ArrayList<Map<String, String>>();
        }
        if (!cacheList.containsKey(key)) {
            return new ArrayList<Map<String, String>>();
        }
        for (Entry<String, List<Map<String, String>>> entry : cacheList.entrySet()) {
            if (entry.getKey().equals(key)) {
                return cacheList.get(key);
            }
        }
        return new ArrayList<Map<String, String>>();
    }

    public static String getCache(String type, String code) {
        if (type == null || type == "" || code == null || code == "") {
            return "";
        }
        if (!cacheList.containsKey(type)) {
            return "";
        }
        String content = "";
        List<Map<String, String>> list = cacheList.get(type);
        String[] codes = code.split(",");
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < codes.length; i++) {
                if (list.get(j).get(codes[i]) != null) {
                    content += list.get(j).get(codes[i]) + ",";
                    break;
                }
            }
        }
        return content;
    }

    private static Map<String, JsonObject> dictionary = new HashMap<String, JsonObject>();


    /**
     * 设置所有字典
     *
     * @param dicmaps
     */
    public static void setDicationary(Map<String, JsonObject> dicmaps) {
        dictionary = dicmaps;
    }


    /**
     * 根据类型 代码获取字典内容
     *
     * @param type
     * @param code
     * @return
     */
    public static String getDictionary(String type, String code) {
        if (type == null || code == null) return "";
        String rediskey = CacheUtils.CACHE_DICTIOANRY + type.toUpperCase() + "_" + code.toUpperCase();
        JSONObject json = JSONObject.parseObject((String) getKey(rediskey));
        return json == null ? "" : json.getString("content");
    }
    
    /**
     * 根据code获取字典内容
     * @param type
     * @return
     */
    public List<?> getDictionaryByType(String type) {
        String key = CACHE_DICTIOANRY + type + "_*";
        Set<String> keySet = redisUtils.getkeys(key);
        if(keySet.isEmpty()){
            //Todo 1、查询数据库 2、获取的内容放到缓存中
        	List<DictionaryModel> list=awdApi.getByField(type).getResult();
        	for(DictionaryModel d: list) {
        		redisUtils.set(CACHE_DICTIOANRY+type+"_"+d.getCode(), d);
        	}
            return list;
        }
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (String s : keySet) {
            String content = redisUtils.get(s);
            jsonObjects.add(JSONObject.parseObject(content));
        }
        return jsonObjects;
    }
    
    /**
     * 根据code获取单个字典内容
     * @param type
     * @return
     */
    public List<?> getDictionaryByTypeSingle(String type,String num) {
        String key = CACHE_DICTIOANRY + type + "_"+num;
        Set<String> keySet = redisUtils.getkeys(key);
        if(keySet.isEmpty()){
            //Todo 1、查询数据库 2、获取的内容放到缓存中
        	List<DictionaryModel> list=awdApi.getByField(type).getResult();
        	for(DictionaryModel d: list) {
        		redisUtils.set(CACHE_DICTIOANRY+type+"_"+d.getCode(), d);
        	}
            return list;
        }
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (String s : keySet) {
            String content = redisUtils.get(s);
            jsonObjects.add(JSONObject.parseObject(content));
        }
        return jsonObjects;
    }

    /**
     * 根据code获取违规字典数据
     *
     * @param code
     * @return
     */
    public static String getWgzd(String code) {
        if (code == null) return "";
        String rediskey = CacheUtils.CACHE_WGZD + code;
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("content");
    }

    /**
     * 根据jsbh、tm查询药品名称
     *
     * @param jsbh tm
     * @return
     */
    public static String getYpmc(String jsbh, String tm) {
        if (jsbh == null || tm == null) return "";
        String rediskey = CacheUtils.CACHE_YPXX + jsbh + "_" + tm;
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("ypmc");
    }

    /**
     * 根据jsbh获取KSS里面的名称
     *
     * @param jsbh
     * @return
     */
    public static String getKssmcByKss(String jsbh) {
        if (jsbh == null) return "";
        String rediskey = CacheUtils.CACHE_KSS_KSS + jsbh;
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("kssmc");
    }

    /**
     * 根据类型 CLASSFIC内容
     *
     * @param code
     * @return
     */
    public static String getClassfic(String code) {
        if (code == null) return "";
        String rediskey = CacheUtils.CACHE_CLASSFIC + code.toUpperCase();
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("name");
    }

    /**
     * 根据节点号获取子节点
     *
     * @param code
     * @return
     */
    public static List<JSONObject> getClassNode(String lx, String code) {
        List<JSONObject> list = new ArrayList<>();
        if (code == null) return null;
        String rediskey = CacheUtils.CACHE_CLASSFIC + lx.toUpperCase() + "_" + code.toUpperCase();
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        if (json != null) {
            Set<String> keys = getKeys(json.getString("classid"));
            for (String string : keys) {
                String key = CacheUtils.CACHE_CLASSFIC + code.toUpperCase();
                JSONObject jsonob = JSONObject.parseObject(redisUtils.get(key));
                list.add(jsonob);
            }
        }
        return list;
    }

    /**
     * 根据节点号获取所有子节点
     *
     * @param code
     * @return
     */
    public static List<JSONObject> getClassNodes(String lx, String code) {
        List<JSONObject> list = getClassNode(lx, code);
        if (list != null && list.size() > 0) {
            for (JSONObject jsonObject : list) {
                list.addAll(getClassNode(lx, jsonObject.getString("classid")));
            }
        }
        return list;
    }

    /**
     * 根据类型 流程实例内容
     *
     * @param code
     * @return
     */
    public JSONObject getFlowMap(String code) {
        if (code == null) return null;
        String rediskey = CACHE_FLOWMAP+ code.toUpperCase();
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return new JSONObject();
        JSONObject json = JSONObject.parseObject(value);
        return json;
    }


    /**
     * 根据类型 流程实例内容
     *
     * @param code
     * @return
     */
    public static JSONObject getFlowNode(String code) {
        if (code == null) return null;
        String rediskey = CacheUtils.CACHE_FLOWNODE  + code;
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json;
    }

    public static Set<String> getFlowNodeList(String jsbh, String processDefinitionKey) {
        Set<String> allkey = getKeys(CACHE_FLOWNODE_COUNT);


        allkey = allkey.stream().filter(p -> p.indexOf(jsbh + "_" + processDefinitionKey) > 0).collect(Collectors.toSet());

        return allkey;
    }

    /**
     * @param jqh
     * @param jsbh
     * @Description: 获取监区人数总和
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: 王帅
     * @Date: 2018/4/11 15:57
     **/
    public static String getJqCount(String jqh, String jsbh) {
        if (jqh == null) return null;
        if (jsbh == null) return null;
        String rediskey = CacheUtils.CACHE_KSS_JS + jsbh + "_" + jqh;
        Set<String> set = getKeys(rediskey);
        int num = 0;
        for (String s : set) {
            JSONObject jsonObject = JSONObject.parseObject(redisUtils.get(s));
            String innum = jsonObject.getString("innum");
            if (!StringUtils.isNullOrEmpty(innum)) {
                num += Integer.parseInt(innum);
            }
        }
        return num + "";
    }

    /**
     * @param jqh
     * @param jsbh
     * @Description: 根据监区号，监所编号获取监室列表
     * @return: java.util.Set<java.lang.String>
     * @Author: 王帅
     * @Date: 2018/4/11 17:03
     **/
    public static List<JSONObject> getJsListByJqhJsbh(String jqh, String jsbh) {
        if (jqh == null) return null;
        if (jsbh == null) return null;
        String rediskey = CacheUtils.CACHE_KSS_JS + jsbh + "_" + jqh;

        Set<String> keys = getKeys(rediskey);
        List<JSONObject> list = new ArrayList<>();
        keys.forEach(p -> list.add(JSONObject.parseObject(redisUtils.get(p))));
        return list;
    }

    /**
     * @param jsbh
     * @Description: 根据监所编号 获取所有的监区列表
     * @return:
     * @Author: 王帅
     * @Date: 2018/4/13 14:45
     **/
    public static List<JSONObject> getJqListByJsbh(String jsbh) {

        String keys = CacheUtils.CACHE_KSS_JQ + jsbh;
        Set<String> stringSet = getKeys(keys);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        stringSet.forEach(p -> jsonObjectList.add(JSONObject.parseObject(redisUtils.get(p).toString())));
        return jsonObjectList;
    }

    /**
     * @param jsbh
     * @Description: 根据监所编号性别 获取所有监室列表
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     * @Author: 王帅
     * @Date: 2018/4/13 20:06
     **/
    public static List<JSONObject> getJsListByJsbh(String jsbh, String xb, String jsh) {

        Set<String> stringSet = getKeys(CACHE_KSS_JQ);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        stringSet.stream().filter(p -> p.indexOf(jsbh) > 0).filter(p -> {
            if (StringUtils.isNullOrEmpty(xb)) {
                return true;
            } else {
                JSONObject js = JSONObject.parseObject(redisUtils.get(p).toString());
                if (xb.equals(js.get("type"))) {
                    return true;
                } else {
                    return false;
                }
            }
        }).filter(p -> {
            if (StringUtils.isNullOrEmpty(jsh)) {
                return true;
            } else {
                JSONObject js = JSONObject.parseObject(redisUtils.get(p).toString());
                if (js.get("jsh").toString().indexOf(jsh) > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }).forEach(p -> jsonObjectList.add(JSONObject.parseObject(redisUtils.get(p).toString())));
        return jsonObjectList;
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
     * @param classid
     * @param jslx
     * @Description: 根据 监所类型 和 classID 获取列表
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     * @Author: 王帅
     * @Date: 2018/4/16 11:57
     **/
    public static List<JSONObject> getFxpgList(String classid, String jslx) {

        Set<String> fxpgSet = getKeys(CACHE_FXPG);
        System.err.println(fxpgSet);
        List<JSONObject> jsonoBjects = new ArrayList<>();
        for (String s : fxpgSet) {
            JSONObject j = JSONObject.parseObject(redisUtils.get(s));
            if (classid.equals(j.get("classid")) && jslx.equals(j.get("jslx"))) {
                jsonoBjects.add(j);
            }

        }
        System.err.println(jsonoBjects.size());
        return jsonoBjects;
    }

    /**
     * 根据类型获取所有字典
     *
     * @param type
     * @return
     */
    public static List<JSONObject> getDictionarys(String type) {
        if (type == null) return null;
        List<JSONObject> result = null;
        Set<String> keys = getKeys(CacheUtils.CACHE_DICTIOANRY + type);
        if (keys != null && keys.size() > 0) {
            result = new ArrayList<JSONObject>();
            for (String key : keys) {
                result.add(JSONObject.parseObject(redisUtils.get(key)));
            }
        }
        return result;
    }

    /**
     * @param jslx
     * @param classid
     * @param code
     * @Description: 根据 监所类型 和 classID 和code获取fxpg全部数据
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     * @Author: 李明明
     * @Date: 2018/7/4 16:14
     **/
    public static List<JSONObject> getFxpgs(String jslx, String classid, String code) {
        if (jslx == null || classid == null || code == null) return null;
        List<JSONObject> result = null;
        Set<String> keys = null;
        if (code != null) {
            keys = getKeys(CacheUtils.CACHE_FXPG + jslx + "_" + classid + "_" + code);
        } else {
            keys = getKeys(CacheUtils.CACHE_FXPG + jslx + "_" + classid + "_*");
        }
        if (keys != null && keys.size() > 0) {
            result = new ArrayList<JSONObject>();
            for (String key : keys) {
                result.add(JSONObject.parseObject(redisUtils.get(key)));
            }
        }
        return result;
    }

    /**
     * @param jslx
     * @param classid
     * @param code
     * @Description: 根据 监所类型 和 classID 和code获取fxpg的content
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     * @Author: 李明明
     * @Date: 2018/7/4 16:21
     **/
    public static String getContentByFxpg(String jslx, String classid, String code) {
        if (jslx == null || classid == null || code == null) return null;
        String rediskey = null;
        if (code != null) {
            rediskey = CacheUtils.CACHE_FXPG + jslx + "_" + classid + "_" + code;
        } else {
            rediskey = CacheUtils.CACHE_FXPG + jslx + "_" + classid + "_*";
        }
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("content");
    	/*if(jslx==null||classid==null||code==null) return null;
    	Set<String> keys=null;
    	String content =null;
    	if(code !=null) {
    		 keys=getKeys(CacheUtils.CACHE_FXPG+jslx+"_"+classid+"_"+code);
    	}else {
    		 keys=getKeys(CacheUtils.CACHE_FXPG+jslx+"_"+classid+"_*");
    	}
    	if(keys!=null&&keys.size()>0) {
    		for (String key : keys) {
    			 content = JSONObject.parseObject(redisUtils.get(key)).getString("content");
    		}
    	}
    	return content;*/
    }

    /**
     * 根据类型 条件获取所有字典
     *
     * @param type
     * @param filter
     * @return
     */
    public static List<JSONObject> getDictionarys(String type, String filter) {
        List<JSONObject> result = null;
        List<JSONObject> list = null;
        Set<String> keys = getKeys(CacheUtils.CACHE_DICTIOANRY + type);
        if (keys != null && keys.size() > 0) {
            list = new ArrayList<JSONObject>();
            for (String key : keys) {
                list.add(JSONObject.parseObject(redisUtils.get(key)));
            }
        }
        if (StringUtils.isNumber(filter)) {
            result = new ArrayList<JSONObject>();
            for (JSONObject object : list) {
                if (object.getString("code").startsWith(filter)) {
                    result.add(object);
                }
            }
        }
        if (StringUtils.isEnglishChars(filter)) {
            result = new ArrayList<JSONObject>();
            for (JSONObject object : list) {
                if (object.getString("py").indexOf(filter.toUpperCase()) >= 0) {
                    result.add(object);
                }
            }
        }
        if (StringUtils.containsChineseChar(filter)) {
            result = new ArrayList<JSONObject>();
            for (JSONObject object : list) {
                if (object.getString("content").indexOf(filter) >= 0) {
                    result.add(object);
                }
            }
        }
        return result;
    }

    /**
     * 根据监所编号 获取中文名
     *
     * @param jsbh
     * @return
     */
    public static String getJsbhString(String jsbh) {
        if (jsbh == null) return "";
        String rediskey = CacheUtils.CACHE_KSS_JBXX + jsbh;
        String jsbhString = redisUtils.get(rediskey);
        return jsbhString;
    }

    /**
     * 根据人员编号获取人员信息
     */
    public static JSONObject getJbxx(String rybh) {
        if (StringUtils.isNullOrEmpty(rybh)) return null;
        String rediskey = CacheUtils.CACHE_KSS_JBXX + rybh;
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json;
    }


    /**
     * 获取APP名称
     *
     * @param appcode
     * @return
     */
    public static String getApp(String appcode) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取菜单名
     *
     * @param menu
     * @return
     */
    public static String getMenu(String menu) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取角色名称
     *
     * @param rolecode
     * @return
     */
    public static String getRole(String rolecode) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * 获取 流程节点名称 和 角色信息，
     *
     * @param flowMapKey
     * @return
     */

    public static Map<String, String> getFlownodeKey(String flowMapKey) {
        Set<String> allkey = getKeys(CACHE_FLOWNODE);
        //过滤不是我们需要的节点信息
        Map<String, String> map = Maps.newHashMap();
        //数据过滤，转换成Map
        allkey.stream().filter(a -> a.indexOf(flowMapKey) > 0 && "1"
                .equals(a.substring(a.lastIndexOf("_") + 1)))
                .forEach(b -> {
                    Map<String, Object> json = JSONUtil.toMap(redisUtils.get(b));
                    map.put(json.get("nodecode").toString(), json.get("role").toString());
                });

        return map;
    }

    /**
     * 获取各个节点的待办数量
     *
     * @param processDefinitionKey 列如:kss_lscs
     * @param jsbh
     * @return
     */
    public static Map<String, String> getFlowCount(String processDefinitionKey, String jsbh) {
        Set<String> allkey = getKeys(CACHE_FLOWNODE_COUNT);
        if (allkey.size() < 0) {
            return null;
        }
        Map<String, String> map = Maps.newHashMap();
        allkey.stream().filter(p -> p.indexOf(jsbh + "_" + processDefinitionKey) > 0).forEach(b -> {
            String s = redisUtils.get(b);
            map.put(b.substring(20), s);

        });
        return map;
    }

    public static String  getFlowSingle(String processDefinitionKey, String jsbh) {
    	String str = redisUtils.get(CACHE_FLOWNODE_COUNT+jsbh+"_"+processDefinitionKey);
        return str;
    }
    
    public static String getFlowKey(String key) {
        Set<String> allKey = getKeys(CACHE_FLOWMAP);

        for (String s : allKey) {
            if (s.indexOf(key) > 0) {
                System.err.println(s);
                JSONObject jsonObject = JSONObject.parseObject(redisUtils.get(s));
                return jsonObject.get("mapname").toString();
            }
        }
        return "";
    }
    public static JSONObject getValueByKey(String key){
        Set<String > allKey = redisUtils.getkeys(CACHE_FLOWMAP);
        for(String s : allKey) {
            if(s.indexOf(key)>0) {
                return JSONObject.parseObject(redisUtils.get(s));
            }
        }
        return new JSONObject();
    }
    public static JSONObject getJbxxByRybh(String rybh){
        Set<String > allKey = redisUtils.getkeys(CACHE_KSS_JBXX);
        for(String s : allKey) {
            if(s.indexOf(rybh)>0) {
                return JSONObject.parseObject(redisUtils.get(s));
            }
        }
        return new JSONObject();
    }
}
