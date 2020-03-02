package awd.cloud.suppers.interfaces.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.cache.Cache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.StringUtils;

@Configuration
public class CacheConfig {

    public static String CAHCE_USER = "userinfo_cahce";
    
    private static GuavaCacheManager cacheManager;	//全局的缓存
    
    private static GuavaCacheManager userInfoCacheManager;	//单独的user的缓存
    
    private static CacheConfig instance;
    
    //获取唯一可用的对象
    public static CacheConfig getInstance(){
        if (instance == null) {
            return new CacheConfig();
        }
       return instance;
    }
  
    public CacheConfig() {
        super();
    }

    /**
     *	默认的缓存设置 
     * @return
     */
    public static GuavaCacheManager getCacheManager() {
    	if (cacheManager == null) {
    		cacheManager = new GuavaCacheManager();
    		//最多缓存500 条，失效时间30分钟 
            cacheManager.setCacheSpecification("maximumSize=500,expireAfterWrite=30m");
		}
        return cacheManager;
    }
    
    /**
     *	制定的useinfo缓存
     * @return
     */
    public static GuavaCacheManager getUserCacheManager() {
    	if (userInfoCacheManager == null) {
    		userInfoCacheManager = new GuavaCacheManager();
    		//最多缓存500000 条，保存1天
    		userInfoCacheManager.setCacheSpecification("maximumSize=50000,expireAfterWrite=1440m");
    		//访问7天之后 失效，防止 别人无登录操作，留下无效缓存数据
    		//userInfoCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(7,TimeUnit.DAYS));
    		//userInfoCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(7,TimeUnit.DAYS));
    	}
    	return userInfoCacheManager;
    }
    
    
    public void setUserCache(List<UserinfoEntity> userList) {
    	//所有缓存的名字
        List<String> cacheNames = new ArrayList<String>();
        String cacheName = CAHCE_USER;
        if(userInfoCacheManager.getCache(cacheName)==null){
        	//为每一个 userinfo 如果不存在，创建一个新的缓存对象
        	cacheNames.add(cacheName);
        	userInfoCacheManager.setCacheNames(cacheNames);
        }
        Cache cache = userInfoCacheManager.getCache(cacheName);
        cache.clear();
        //将数据放入缓存
        userList.stream().forEach(user -> {
            cache.put(user.getId(),user);
        });
    }
    
    
    public Object getCacheByKey(String cacheName,String key) {
    	System.err.println("cacheName:"+cacheName+" ,key:"+key);
    	Cache cache = getCacheManager().getCache(cacheName.toString());
    	Object object=null;
    	if(cache!=null) {
    		Cache.ValueWrapper valueWrapper = cache.get(key);
    		if(valueWrapper!=null) {
    			object = valueWrapper.get();
    		}        	
    	}    	
    	return object;
	}
    
}
