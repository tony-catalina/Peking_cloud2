package awd.mis.desktop.tools;

import awd.framework.common.utils.ConstantUtils;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.rabbitmq.model.KssMessage;
import awd.framework.expands.redisclient.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CacheUtils extends ConstantUtils {

    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    @Autowired
    private RedisUtils re;


    private static Map<String, Boolean> map = new HashMap<String, Boolean>() {{
        put("XB", true);
        put("SF", true);
        put("SRLX", true);
        put("XJXFLX", true);
        put("CXCYLX", true);
        put("EMBLLX", true);
        put("EMSZLY", true);
        put("EMLX", true);
        put("PG", true);
        put("PSBZ", true);
        put("QKLX", true);
        put("FSSGLX", true);
        put("FSSGXZ", true);
        put("ZDSJLX", true);
        put("FSSGLX", true);
        put("LX", true);
        put("SHFO", true);
        put("GKLX", true);
        put("JSGZRW", true);
        put("GZBZWCQK", true);
        put("MZ", true);
        put("ZY", true);
        put("ZJLX", true);
        put("ZZMM", true);
        put("XZQH", true);
        put("WHCD", true);
        put("LCBLLX", true);
        put("JBYY", true);
        put("JBYCYY", true);
        put("JBCXYY", true);
        put("PSBZ", true);
        put("YWYC", true);
        put("JJB", true);
        put("LXZT", true);
        put("GX", true);
        put("SQYY", true);
        put("SQRLX", true);
        put("LY", true);
        put("BHLX", true);
        put("CSJYLX", true);
        put("YWYC", true);
        put("PG", true);
        put("XZQH", true);
        put("DJ", true);
        put("GM", true);
        put("LSDW", true);
        put("BADW", true);
        put("WCZT", true);
        put("LSCS", true);
        put("LSYY", true);
        put("LYRLX", true);
        put("XB", true);
        put("MZ", true);
        put("JX", true);
        put("WHCD", true);
        put("ZZMM", true);
        put("HYZK", true);
        put("XZQH", true);
        put("ZWJB", true);
        put("ORDER", true);
        put("XSLX", true);
        put("AJLB", true);
        put("RSXZ", true);
        put("CZQK", true);
        put("CLZT", true);
        put("YW", true);
        put("RSXZ", true);
        put("XSTS", true);
        put("GX", true);
        put("TSLX", true);
        put("BADW", true);
        put("ZJLX", true);
        put("WGCLQK", true);
        put("YCQK", true);
        put("YWYC", true);
        put("WGLX", true);
        put("WMJSBLLX", true);
        put("WPLQZT", true);
        put("JJSYYY", true);
        put("JJYCYY", true);
        put("JJCXYY", true);
        put("XJMC", true);
        put("JJBLLX", true);
        put("BZ", true);
        put("YWYC", true);
        put("STATE", true);
        put("CURRENCY", true);
        put("XJXFLX", true);
        put("WGLX", true);
        put("WGCLQK", true);
        put("XSLB", true);
        put("YGYY", true);
        put("YGZZYY", true);
        put("YGCXYY", true);
        put("YGJJYY", true);
        put("YGBLLX", true);
        put("YPCLBJ", true);
        put("SYFF", true);
        put("FYDW", true);
        put("YPDW", true);
        put("JBMC", true);
        put("ZJLX", true);
        put("JSHJSY", true);
        put("LSCS", true);
        put("DW", true);
        put("JJBLLX", true);
        put("ZW", true);
        put("GX", true);
        put("XSTS", true);
        put("TSLB", true);
        put("QTGZRY", true);
        put("YWSTATE", true);
    }};
    private static final String ATTRIBUTENAME = "globalVariableMap";

    private static RedisUtils redisUtils;

    private static CacheUtils instance;

    public CacheUtils() {
        super();
        //redisUtils = (RedisUtils) ApplicationContextHolder.get().getBean("redisUtils");
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.redisUtils = this.re;
    }

    public static CacheUtils get() {
        if (instance == null) {
            return new CacheUtils();
        }
        return instance;

    }

    public static void del(String key) {
        redisUtils.remove(key);
    }

    public static Object getKey(String key) {
        return redisUtils.get(key);
    }

    public static Set<String> getKeys(String key) {
        return redisUtils.getkeys(key);
    }

    public static void set(String key, String jsonString) {
        redisUtils.set(key, jsonString);
    }


    public static void removePattern(String pattern) {
        redisUtils.removePattern(pattern);
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
    /**
     * 根据监所编号 获取中文名
     *
     * @param jsbh
     * @return
     */
    public String getJsbhString(String jsbh) {
        if (jsbh == null) return "";
        String rediskey = CACHE_KSS_KSS + jsbh;
        String jsbhString = redisUtils.get(rediskey);
        return jsbhString;
    }

    public List<JSONObject> getJsbhList() {
        String key = CACHE_KSS_KSS + "*";
        Set<String> set = getKeys(key);
        List<JSONObject> li = new ArrayList<>();
        for (String s : set) {
            JSONObject value = JSONObject.parseObject((String) getKey(s));
            li.add(value);
        }

        return li;
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

    public boolean setBadMsg(KssMessage messageModel) {
        try {

            String key = CACHE_BADMSG + messageModel.getMsgId();
            String numKey = CACHE_BADMSGNUM + messageModel.getMsgId();
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
}
