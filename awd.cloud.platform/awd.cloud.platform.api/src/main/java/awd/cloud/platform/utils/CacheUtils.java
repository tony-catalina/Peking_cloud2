package awd.cloud.platform.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import awd.bj.logs.model.ExceptiondictionaryModel;
import awd.bj.manager.model.DictionaryModel;
import awd.bj.manager.model.MfingerModel;
import awd.cloud.platform.api.LogsService;
import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.config.GlobalVariable;
import awd.cloud.platform.model.manager.Manager_MfaceModel;
import awd.cloud.platform.model.manager.Manager_MirisModel;
import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;


@Component
public class CacheUtils extends ConstantUtils {

    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    @Autowired
    private ManagerService service;
    @Autowired
    private RedisUtils re;
    @Autowired
    private LogsService logs;

    private static ManagerService managerService;
    private static Map<String, Boolean> map = new HashMap<String, Boolean>() {{
        put("SHFO", true);
        put("BAJD", true);
        put("DWLX", true);
        put("BADW", true);
        put("MZ", true);
        put("GJ", true);
        put("XB", true);
        put("ZJLX", true);
        put("ZZMM", true);
        put("HYZK", true);
        put("XZQH", true);
        put("WHCD", true);
        put("ZC", true);
        put("SF", true);
        put("TSSF", true);
        put("ZY", true);
        put("BHLX", true);
        put("AZB", true);
        put("CYLX", true);
        put("RSXZ", true);
        put("AJLB", true);
        put("BAJD", true);
        put("BADW", true);
        put("DWLX", true);
        put("SYPZ", true);
        put("CSYY", true);
        put("CSQX", true);
        put("SHFO", true);
        put("CLJG", true);
        put("FJX", true);
        put("RYGLLB", true);
        put("ZSZT", true);
        put("LSCS", true);
        put("SHFO", true);
        put("ZYRYXGQK", true);
        put("EMLX", true);
        put("AJLB", true);
        put("RSJCLX", true);
        put("JJ", true);
        put("JB", true);
        put("LDF", true);
        put("YGRY", true);
        put("ZDRY", true);
        put("ZYAF", true);
        put("FXDJ", true);
        put("SHFO", true);
        put("RYSTATE", true);
        put("JJX", true);
        put("SHFO", true);
        put("JCLX", true);
        put("JKZK", true);
        put("SFYJC", true);
        put("SFYJC", true);
        put("JKQKCQCS", true);
        put("TSBJ", true);
        put("RTBW", true);
        put("FW", true);
        put("SL", true);
        put("TSBJ", true);
        put("RTBW", true);
        put("FW", true);
        put("XX", true);
        put("JCLX", true);
        put("JKZK", true);
        put("BHLX", true);
        put("SFYJC", true);
        put("JSLX", true);
        put("JSLB", true);
        put("JSHJSY", true);
        put("TJYY", true);
        put("LSDW", true);
        put("BADW", true);
        put("CLJG", true);
        put("FJX", true);
        put("RSJCJG", true);
        put("XB", true);
        put("GX", true);
        put("YYZT", true);
        put("TSBJ", true);
        put("RTBW", true);
        put("FW", true);
        put("SL", true);
        put("TSBJ", true);
        put("RTBW", true);
        put("FW", true);
        put("SL", true);
        put("TSBJ", true);
        put("RTBW", true);
        put("BADW", true);
        put("TSZJLX", true);
        put("TSBLLX", true);
        put("TSS", true);
        put("MZ", true);
        put("ZJLX", true);
        put("HYZK", true);
        put("WHCD", true);
        put("GJ", true);
        put("ZY", true);
        put("SF", true);
        put("TSSF", true);
        put("ZZMM", true);
        put("XZQH", true);
        put("ZC", true);
        put("JKZK", true);
        put("BHLX", true);
        put("SHFO", true);
        put("RSXZ", true);
        put("AJLB", true);
        put("CYLX", true);
        put("BAJD", true);
        put("DWLX", true);
        put("CZZT", true);
        put("STATE", true);
        put("SYPZ", true);
    }};
    private static final String ATTRIBUTENAME = "globalVariableMap_Base";


    private static RedisUtils redisUtils;
    public static final String CACHE_M_FINGER = "CACHE_M_FINGER";
    public static final String CACHE_M_IRIS = "CACHE_M_IRIS";
    public static final String CACHE_M_FACE = "CACHE_M_FACE";
    private static CacheUtils instance;
    private static LogsService logsService;

    public CacheUtils() {
        super();
        //redisUtils = (RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.managerService = this.service;
        instance.redisUtils = this.re;
        instance.logsService = this.logs;
    }

    public static CacheUtils get() {
        return instance;
    }

    public static void del(String key) {
        redisUtils.remove(key);
    }

    public void set(String key, Object value) {
        try {
            redisUtils.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getKey(String key) {
        return redisUtils.get(key);
    }

    public static Set<String> getKeys(String key) {
        return redisUtils.getkeys(key);
    }

    public static List<JSONObject> getDictionarys(String node) {
        if (StringUtils.isNullOrEmpty(node)) {
            return null;
        } else {
            String object = redisUtils.get(node);
        }
        return null;
    }

    /**
     * 对于在Map的key值先去全局变量中取数据
     * 有->返回结果
     * 无->redis（或者数据库）->存储到全局变量、返回结果
     *
     * @param key
     * @param code
     * @return
     */
    public static String getDictionary(String key, String code) {
    	if (StringUtils.isNullOrEmpty(key) || StringUtils.isNullOrEmpty(code)) {
            return "";
        }
    	String result="";
    	String[] codes=code.split(",");
    	for (String content : codes) {    		
            String redisKey = CACHE_DICTIOANRY + key.toUpperCase() + "_" + content;
            ServletContext servletContext = GlobalVariable.get();
            Map<String, String> attributeMap = (Map<String, String>) servletContext.getAttribute(ATTRIBUTENAME);
            if (attributeMap == null) {
                attributeMap = Maps.newHashMap();
            }
            //attributeMap = Maps.newHashMap();
            JSONObject jsonObject;
            String json = "";
            boolean globalVariableFlage = true;

            if (map.get(key)!=null) {
                json = attributeMap.get(redisKey);
                if (json == null || "null".equals(json)) {
                    globalVariableFlage = false;
                }
            } else {
                json = redisUtils.get(CACHE_DICTIOANRY + key.toUpperCase() + "_" + content);
            }

            if (!globalVariableFlage) {
                json = redisUtils.get(CACHE_DICTIOANRY + key.toUpperCase() + "_" + content);
                if (!StringUtils.isNullOrEmpty(json)) {
                    attributeMap.put(redisKey, json);
                    servletContext.setAttribute(ATTRIBUTENAME, attributeMap);
                }
            }

            if (StringUtils.isNullOrEmpty(json)) {
                ResponseMessage<DictionaryModel> dictionaryModel = managerService.getDictionaryByFieldCode(key, content);
                if (dictionaryModel == null || dictionaryModel.getStatus() == 500) {
                    return "";
                }
                json = JSONObject.toJSONString(dictionaryModel.getResult());
                if ("null".equals(json) || json == null) {
                    attributeMap.put(redisKey, "{\"content\":\" \"}");
                    logger.error("dictionary字典转换失败、转换失败key值为：{}", redisKey);
                    ExceptiondictionaryModel model = new ExceptiondictionaryModel();
                    model.setKey(redisKey);
                    model.setDescribe("全局变量中、Redis缓存、manager服务中均为发现code值");
                    model.setCreatetime(new Date());
                    logsService.exceptionDictionarySave(model);
                    servletContext.setAttribute(ATTRIBUTENAME, attributeMap);
                    return "";
                } else {
                    redisUtils.set(redisKey, json);
                    attributeMap.put(redisKey, json);
                    servletContext.setAttribute(ATTRIBUTENAME, attributeMap);
                }
            }
            jsonObject = JSONObject.parseObject(json);
            if(codes.length==1) {
            	result=jsonObject.getString("content") == null ? "" : jsonObject.getString("content");
            }else {
            	result+=jsonObject.getString("content") == null ? "" : jsonObject.getString("content")+",";
            }
		}      
        
        return result;

    }

    /**
     * 多选code
     *
     * @param key
     * @param codes
     * @return
     */
    public static String findDictionarys(String key, String codes) {
        if (StringUtils.isNullOrEmpty(key) || StringUtils.isNullOrEmpty(codes)) {
            return "";
        }
        String code[];

        if (codes.indexOf("，") > 0) {
            code = codes.split("，");
        } else {
            code = codes.split(",");
        }
        String content = "";
        for (String s : code) {
            String result = getDictionary(key, s);
            if ("".equals(result)) {
                continue;
            }
            content += result + ",";
        }

        if (content.endsWith(",")) {
            content = content.substring(0, content.length() - 1);
        }
        return content;
    }

    public static String getBadw(String key, String code) {
        String json = redisUtils.get(CACHE_DICTIOANRY + key.toUpperCase() + "_" + code);
        if (StringUtils.isNullOrEmpty(json)) {
            return "";
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (StringUtils.isNullOrEmpty(jsonObject)) {
            return "";
        }
        return jsonObject.getString("content") == null ? "" : jsonObject.getString("content");
    }


    public static void set(String key, String jsonString) {
        redisUtils.set(key, jsonString);
    }


    public static void removePattern(String pattern) {
        redisUtils.removePattern(pattern);
    }


    /**
     * 根据code获取违规字典数据
     *
     * @param code
     * @return
     */
    public static String getWgzd(String code) {
        if (code == null) return "";
        String rediskey = CACHE_WGZD + code;
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return "";
        JSONObject json = JSONObject.parseObject(value);
        return json == null ? "" : json.getString("content");
    }

    /**
     * @Description
     * @Date 2019/1/15
     * @Param [jsbh, tm]
     * @Return java.lang.String
     */
    public static String getYpmc(String jsbh, String tm) {
        if (jsbh == null || tm == null) return "";
        String rediskey = CACHE_YPXX + jsbh + "_" + tm;
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return "";
        JSONObject json = JSONObject.parseObject(value);
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
        String rediskey = CACHE_KSS_KSS + jsbh;
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return "";
        JSONObject json = JSONObject.parseObject(value);
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
        String rediskey = CACHE_CLASSFIC + code.toUpperCase();
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return "";
        JSONObject json = JSONObject.parseObject(value);
        return json == null ? "" : json.getString("name");
    }

    /**
     * 根据节点号获取子节点
     *
     * @param code
     * @return
     */
    public List<JSONObject> getClassNode(String lx, String code) {
        List<JSONObject> list = new ArrayList<>();
        if (code == null) return null;
        String rediskey = CACHE_CLASSFIC + lx.toUpperCase() + "_" + code.toUpperCase();
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return null;
        JSONObject json = JSONObject.parseObject(value);
        if (json != null) {
            Set<String> keys = getKeys(json.getString("classid"));
            for (String string : keys) {
                String key = CACHE_CLASSFIC + string.toUpperCase();
                String content = redisUtils.get(key);
                if (StringUtils.isNullOrEmpty(content)) continue;
                JSONObject jsonob = JSONObject.parseObject(content);
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
    public List<JSONObject> getClassNodes(String lx, String code) {
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
    public JSONObject getFlowNode(String code) {
        if (code == null) return null;
        String rediskey = CACHE_FLOWNODE + "_" + code;
        String value = redisUtils.get(rediskey);
        if (StringUtils.isNullOrEmpty(value)) return new JSONObject();
        JSONObject json = JSONObject.parseObject(value);
        return json;
    }

    public Set<String> getFlowNodeList(String jsbh, String processDefinitionKey) {
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
    public String getJqCount(String jqh, String jsbh) {
        if (jqh == null) return null;
        if (jsbh == null) return null;
        String rediskey = CACHE_KSS_JS + jsbh + "_" + jqh;
        Set<String> set = getKeys(rediskey);
        int num = 0;
        for (String s : set) {
            String value = redisUtils.get(rediskey);
            if (StringUtils.isNullOrEmpty(value)) continue;
            JSONObject jsonObject = JSONObject.parseObject(value);
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
    public List<JSONObject> getJsListByJqhJsbh(String jqh, String jsbh) {
        if (jqh == null) return null;
        if (jsbh == null) return null;
        String rediskey = CACHE_KSS_JS + jsbh + "_" + jqh;

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
    public List<JSONObject> getJqListByJsbh(String jsbh) {

        String keys = CACHE_KSS_JQ + jsbh;
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
    public List<JSONObject> getJsListByJsbh(String jsbh, String xb, String jsh) {

        Set<String> stringSet = getKeys("JS");
        List<JSONObject> jsonObjectList = new ArrayList<>();
        stringSet.stream().filter(p -> p.indexOf(jsbh) > 0).filter(p -> {
            if (StringUtils.isNullOrEmpty(xb)) {
                return true;
            } else {
                String value = redisUtils.get(p);
                if (!StringUtils.isNullOrEmpty(value)) {
                    JSONObject js = JSONObject.parseObject(p);
                    if (xb.equals(js.get("type"))) {
                        return true;
                    } else {
                        return false;
                    }
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
    public String getFxpgContent(String code, String jslx) {

        String key = CACHE_FXPG + code + "_" + jslx;

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
    public List<JSONObject> getFxpgList(String classid, String jslx) {

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
     * @param jslx
     * @param classid
     * @param code
     * @Description: 根据 监所类型 和 classID 和code获取fxpg全部数据
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     * @Author: 李明明
     * @Date: 2018/7/4 16:14
     **/
    public List<JSONObject> getFxpgs(String jslx, String classid, String code) {
        if (jslx == null || classid == null || code == null) return null;
        List<JSONObject> result = null;
        Set<String> keys = null;
        if (code != null) {
            keys = getKeys(CACHE_FXPG + jslx + "_" + classid + "_" + code);
        } else {
            keys = getKeys(CACHE_FXPG + jslx + "_" + classid + "_*");
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
            rediskey = CACHE_FXPG + jslx + "_" + classid + "_" + code;
        } else {
            rediskey = CACHE_FXPG + jslx + "_" + classid + "_*";
        }
        JSONObject json = JSONObject.parseObject(redisUtils.get(rediskey));
        return json == null ? "" : json.getString("content");
    }

    /**
     * 根据类型 条件获取所有字典
     *
     * @param type
     * @param filter
     * @return
     */
    public List<JSONObject> getDictionarys(String type, String filter) {
        List<JSONObject> result = null;
        List<JSONObject> list = null;
        Set<String> keys = getKeys(CACHE_DICTIOANRY + type);
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
    public String getJsbhString(String jsbh) {
        String value = redisUtils.get(CACHE_KSS_KSS + jsbh).toString();
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
            return object.getString("kssmc");
        } else {
            return "";
        }
    }


    public static Map<String, List<Map<String, String>>> cacheList = new HashMap<String, List<Map<String, String>>>();

    public static void setCache(String key, List<Map<String, String>> value) {
        cacheList.put(key, value);
    }
    
    public static void setCache(String key, Object obj) {
        redisUtils.set(key, obj);
    }

    public static List<Map<String, String>> getCache(String key) {
        if (StringUtils.isNullOrEmpty(key)) {
            return new ArrayList<Map<String, String>>();
        }
        if (!cacheList.containsKey(key)) {
            return new ArrayList<Map<String, String>>();
        }
        for (Map.Entry<String, List<Map<String, String>>> entry : cacheList.entrySet()) {
            if (entry.getKey().equals(key)) {
                return cacheList.get(key);
            }
        }
        return new ArrayList<Map<String, String>>();
    }

    public static String getCache(String type, String code) {
        if (StringUtils.isNullOrEmpty(type) || StringUtils.isNullOrEmpty(code)) {
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
    
    /**
     * 根据appcode获取url
     *
     * @param
     * @return
     */
    public String geturlString(String appcode) {
        String value = redisUtils.get("cloud_app" + appcode.toUpperCase()).toString();
        System.err.println("==================="+"cloud_app" + appcode.toUpperCase());
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
            return object.getString("url");
        } else {
            return "";
        }
    }
    
    /**
     * 根据所有接口获取interfaceId
     *
     * @param
     * @return
     */
    public JSONObject getinterfaceString(String interfaceId,String sfqy) {
    	JSONObject rerult = null;
        String value = redisUtils.get("CACHE_KEY_INTERFACE" + interfaceId.toUpperCase()).toString();
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
        	if(sfqy.equals(object.getString("sfqy"))) {
        		 return object;
        	}else {
        		 return rerult;
        	}
        } else {
            return rerult;
        }
    }
    
    /**
     * 根据接口权限获取
     *
     * @param interfaceId  sfqy
     * @return
     */
    public JSONObject getinterfacebindingString(String appcode,String interfaceId,String bdzt) {
    	JSONObject rerult = null;
        String value = redisUtils.get("CACHE_KEY_INTERFACEBINDING" +appcode.toUpperCase()+ interfaceId.toUpperCase()).toString();
        JSONObject object = JSONObject.parseObject(value);
        if (object != null) {
        	if(bdzt.equals(object.getString("bdzt"))) {
        		 return object;
        	}else {
        		 return rerult;
        	}
        } else {
            return rerult;
        }
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
    public List<JSONObject> getRoleList() {

        Set<String> stringSet = getKeys(CACHE_ROLE);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        stringSet.forEach(p -> jsonObjectList.add(JSONObject.parseObject(redisUtils.get(p).toString())));
        return jsonObjectList;
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
    
    public MfingerModel getMfingerModel(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_FINGER + sfzh + "*");
        MfingerModel mfingerModel = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mfingerModel = JSONUtil.toBean(json, MfingerModel.class);
        }
        return mfingerModel;
    }
    
    public String getBiometricIP(String ip) {
        if (StringUtils.isNullOrEmpty(ip)) {
            return "";
        }
        Set<String> set = redisUtils.getkeys(CACHE_CONTRAST_IP + ip + "*");
        Map<String, Object> map = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            map = JSONUtil.toMap(json);
        }
        if (map == null) {
            return "";
        }
        String equipmentIp = (String) map.get("equipmentip");

        return equipmentIp;
    }
    
    public List<Manager_MirisModel> getMirisModel(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_IRIS + sfzh + "*");
        List<Manager_MirisModel> mIrisModels = Lists.newArrayList();
        //多条只取一条数据
        for (String s : set) {
        	Manager_MirisModel mirisModel = new Manager_MirisModel();
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mirisModel = JSONUtil.toBean(json, Manager_MirisModel.class);
            mIrisModels.add(mirisModel);
        }
        return mIrisModels;
    }
    
    public Manager_MfaceModel getMface(String sfzh) {
        if (StringUtils.isNullOrEmpty(sfzh)) {
            return null;
        }
        Set<String> set = redisUtils.getkeys(CACHE_M_FACE + sfzh + "*");
        Manager_MfaceModel mfaceModel = null;
        //多条只取一条数据
        for (String s : set) {
            String json = redisUtils.get(s);
            if (StringUtils.isNullOrEmpty(json)) {
                continue;
            }
            mfaceModel = JSONUtil.toBean(json, Manager_MfaceModel.class);
        }
        return mfaceModel;
    }
}
