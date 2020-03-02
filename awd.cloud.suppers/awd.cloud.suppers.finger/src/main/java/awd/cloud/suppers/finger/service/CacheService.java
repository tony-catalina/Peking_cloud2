package awd.cloud.suppers.finger.service;

public interface CacheService {
	void add(String key, Object value, int expiration); 
	
	public void loadMjOrRyCache();
	
	void loadAllZwAndTzm();
	
	void loadRyCache();
	
	void loadMjCache();
	
}
