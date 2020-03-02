package awd.mis.servers.tools;

import java.util.List;

import com.ace.cache.constants.CacheScope;
import com.ace.cache.parser.IKeyGenerator;
import com.ace.cache.parser.IUserKeyGenerator;

import awd.framework.common.datasource.orm.core.param.Term;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.StringUtils;


public class CachePageKeyGenerator extends IKeyGenerator {
    @Override
    public IUserKeyGenerator getUserKeyGenerator() {
        return null;
    }

    @Override
    public String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] arguments) {
    	//System.err.println(arguments[0] instanceof String);
    	//System.err.println(StringUtils.isNumber(arguments[0]));
    	//System.err.println(arguments[0]);
    	if(arguments[1] instanceof String) {
    		 if(arguments[1].toString().length()==23) {
    			 key= key+":"+arguments[1].toString().substring(0,9)+"_"+arguments[1];
    		 }
    		 key= key+":"+arguments[1];
    	}else {
    		System.err.println(arguments[1] instanceof QueryParamEntity);
    		if(arguments[1] instanceof QueryParamEntity) {
    			QueryParamEntity entity=(QueryParamEntity)arguments[1];
    			key= key+":"+getJsbh(entity.getTerms())+"_"+(arguments[1].hashCode()& Integer.MAX_VALUE);
    		}else {
    			key= key+":"+(arguments[1].hashCode()& Integer.MAX_VALUE);
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
}
