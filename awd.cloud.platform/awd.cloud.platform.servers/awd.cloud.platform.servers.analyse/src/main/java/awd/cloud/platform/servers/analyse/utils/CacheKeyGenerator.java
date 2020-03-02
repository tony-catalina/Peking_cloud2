package awd.cloud.platform.servers.analyse.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.ace.cache.constants.CacheScope;
import com.ace.cache.parser.ICacheResultParser;
import com.ace.cache.parser.IKeyGenerator;
import com.ace.cache.parser.IUserKeyGenerator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.Term;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;


public class CacheKeyGenerator extends IKeyGenerator {
    @Override
    public IUserKeyGenerator getUserKeyGenerator() {
        return null;
    }

    @Override
    public String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] arguments) {
    	//System.err.println(arguments[0] instanceof String);
    	//System.err.println(StringUtils.isNumber(arguments[0]));
    	//System.err.println(arguments[0]);
    	if(arguments[0] instanceof String) {
    		 if(arguments[0].toString().length()==23) {
    			 key= key+":"+arguments[0].toString().substring(0,9)+"_"+arguments[0];
    		 }else {
    			 key= key+":"+arguments[0];
    		 }    		 
    	}else {
    		System.err.println(arguments[0] instanceof QueryParamEntity);
    		if(arguments[0] instanceof QueryParamEntity) {
    			QueryParamEntity entity=(QueryParamEntity)arguments[0];
    			key= key+":"+getJsbh(entity.getTerms())+"_"+(arguments[0].hashCode()& Integer.MAX_VALUE);
    		}else {
    			key= key+":"+(arguments[0].hashCode()& Integer.MAX_VALUE);
    		}
    		
    	}  
    	System.err.println(key);
    	return key;
    }

	private String getJsbh(List<Term> terms) {
		for (Term term : terms) {
			if("jsbh".equals(term.getColumn())) {
				return term.getValue().toString();
			}
		}
		return "public";
	}  
	
	public static class ResultCacheResultParser implements ICacheResultParser {
        @Override
        public Object parse(String value, Type returnType, Class<?>... origins) {
            return JSONUtil.toBean(value, ResponseMessage.class);
//        	return JSON.parseObject(value, new TypeReference<ResponseMessage>() {
//            });
        }
    }
}


