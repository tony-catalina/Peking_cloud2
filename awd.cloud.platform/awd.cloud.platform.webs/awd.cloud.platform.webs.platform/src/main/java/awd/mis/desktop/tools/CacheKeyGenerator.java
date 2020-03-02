package awd.mis.desktop.tools;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ace.cache.constants.CacheScope;
import com.ace.cache.parser.ICacheResultParser;
import com.ace.cache.parser.IKeyGenerator;
import com.ace.cache.parser.IUserKeyGenerator;
import com.ace.cache.utils.ReflectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.User;


public class CacheKeyGenerator extends IKeyGenerator {
    @Override
    public IUserKeyGenerator getUserKeyGenerator() {
        return null;
    }

    @Override
    public String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] arguments) {
    	User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 boolean isFirst = true;
         if (key.indexOf("{") > 0) {
             key = key.replace("{", ":{");
             Pattern pattern = Pattern.compile("\\d+\\.?[\\w]*");
             Matcher matcher = pattern.matcher(key);
             while (matcher.find()) {
                 String tmp = matcher.group();
                 String express[] = matcher.group().split("\\.");
                 String i = express[0];
                 int index = Integer.parseInt(i) - 1;
                 Object value = arguments[index];
                 if (parameterTypes[index].isArray()) {
                     List result = (List) arguments[index];
                     value = result.get(0);
                 }
                 if (value == null || value.equals("null"))
                     value = "";
                 if (express.length > 1) {
                     String field = express[1];
                     value = ReflectionUtils.getFieldValue(value, field);
                 }
                 HttpServletRequest request=(HttpServletRequest) value;
                 value=request.getParameterMap();
                 String url=request.getRequestURI();
                 
                 //System.out.println(url);
                 if (isFirst) {
                     key = key.replace("{" + tmp + "}", ""+currentUser.getJsbh()+"_"+url.hashCode()+"_"+cn.hutool.json.JSONUtil.toJsonStr(value).hashCode());
                 } else {
                     key = key.replace("{" + tmp + "}", LINK +currentUser.getJsbh()+"_"+url.hashCode()+"_"+ cn.hutool.json.JSONUtil.toJsonStr(value).hashCode());
                 }
             }
         }
         //System.err.println("缺省生成："+key);
         return key;
    } 
	
	public static class ResultCacheResultParser implements ICacheResultParser {
        @Override
        public Object parse(String value, Type returnType, Class<?>... origins) {
            return JSONUtil.toBean(value, ResultMap.class);
        }
    }
}


