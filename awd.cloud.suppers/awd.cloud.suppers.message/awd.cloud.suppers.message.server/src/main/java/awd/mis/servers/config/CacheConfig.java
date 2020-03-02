package awd.mis.servers.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CacheConfig {

	public static String CAHCE_VHOST = "vhost";
	
    public static String CAHCE_EXCHANGE = "exchange";
    
    public static String CAHCE_QUEUE = "queue";
    
    private GuavaCacheManager cacheManager;	//全局的缓存
    
    private GuavaCacheManager rabbitMQCacheManager;	//单独的user的缓存
    
    private static CacheConfig instance;
    
    //获取唯一可用的对象
    public static CacheConfig get(){
        if (instance == null) {
        	instance = new CacheConfig();
        }
       return instance;
    }
    
    @PostConstruct 
    public void init() {
    	instance = this;
    }

	/**
     *	默认的缓存设置 
     * @return
     */
    @Autowired
    public GuavaCacheManager getCacheManager() {
    	if (cacheManager == null) {
    		cacheManager = new GuavaCacheManager();
    		//最多缓存500 条，失效时间30分钟 
            cacheManager.setCacheSpecification("maximumSize=500,expireAfterWrite=30m");
		}
        return cacheManager;
    }
    
    @Autowired
    public GuavaCacheManager getVhostAndExchangesCacheManager() {
    	if (rabbitMQCacheManager == null) {
    		rabbitMQCacheManager = new GuavaCacheManager();
    		//最多缓存500000 条，保存7天
    		rabbitMQCacheManager.setCacheSpecification("maximumSize=5000,expireAfterWrite=10080m");
    		//访问7天之后 失效，防止 别人无登录操作，留下无效缓存数据
    		//jsInfoCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(7,TimeUnit.DAYS));
    		//jsInfoCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterWrite(7,TimeUnit.DAYS));
    	}
    	return rabbitMQCacheManager;
    }
    
    /**
     * 	批量设置缓存
     */
    public void setVhostsCache(List<?> vhosts) {
        String cacheName = CAHCE_VHOST;
        if(rabbitMQCacheManager.getCache(cacheName)==null){
        	List<String> cacheNames = new ArrayList<String>();
        	cacheNames.add(cacheName);
        	rabbitMQCacheManager.setCacheNames(cacheNames);
        }
        Cache cache = rabbitMQCacheManager.getCache(cacheName);
        cache.clear();
        //将数据放入缓存
        cache.put(CAHCE_VHOST,vhosts);
	}
    
    
    /**
     * 	批量设置缓存
     */
    public void setExchangesCache(List<?> exchanges) {
    	String cacheName = CAHCE_EXCHANGE;
    	if(rabbitMQCacheManager.getCache(cacheName)==null){
    		List<String> cacheNames = new ArrayList<String>();
    		cacheNames.add(cacheName);
    		rabbitMQCacheManager.setCacheNames(cacheNames);
    	}
    	Cache cache = rabbitMQCacheManager.getCache(cacheName);
    	cache.clear();
    	//将数据放入缓存
    	cache.put(CAHCE_EXCHANGE,exchanges);
    }
    
    /**
     * 	批量设置缓存
     */
    public void setQueuesCache(List<?> queues) {
    	String cacheName = CAHCE_QUEUE;
    	if(rabbitMQCacheManager.getCache(cacheName)==null){
    		List<String> cacheNames = new ArrayList<String>();
    		cacheNames.add(cacheName);
    		rabbitMQCacheManager.setCacheNames(cacheNames);
    	}
    	Cache cache = rabbitMQCacheManager.getCache(cacheName);
    	cache.clear();
    	//将数据放入缓存
    	cache.put(CAHCE_QUEUE,queues);
    }
    
    /**
     * 获取缓存
     * @return
     */
    public List<?> getVhostsCache() {
    	Cache cache = rabbitMQCacheManager.getCache(CAHCE_VHOST);
    	List<?> list=null;
    	if(cache!=null) {
    		list = cache.get(CAHCE_VHOST, List.class);
			/*
			 * Cache.ValueWrapper valueWrapper = (ValueWrapper) cache.get(CAHCE_EXCHANGE);
			 * System.err.println("valueWrapper---"+valueWrapper); if(valueWrapper!=null) {
			 * Object object = valueWrapper.get(); System.err.println("object---"+object);
			 * list = JSONUtil.toList(JSONUtil.toJson(object), Map.class); }
			 */     	
    	}
    	return list;
    }
    
    public List<?> getExchangesCache() {
    	Cache cache = rabbitMQCacheManager.getCache(CAHCE_EXCHANGE);
    	List<?> list=null;
    	if(cache!=null) {
    		list = cache.get(CAHCE_EXCHANGE, List.class);
    		/*
    		 * Cache.ValueWrapper valueWrapper = (ValueWrapper) cache.get(CAHCE_EXCHANGE);
    		 * System.err.println("valueWrapper---"+valueWrapper); if(valueWrapper!=null) {
    		 * Object object = valueWrapper.get(); System.err.println("object---"+object);
    		 * list = JSONUtil.toList(JSONUtil.toJson(object), Map.class); }
    		 */     	
    	}
    	return list;
    }
    
    public List<?> getQueuesCache() {
    	Cache cache = rabbitMQCacheManager.getCache(CAHCE_QUEUE);
    	List<?> list=null;
    	if(cache!=null) {
    		list = cache.get(CAHCE_QUEUE, List.class);
    	}
    	return list;
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
