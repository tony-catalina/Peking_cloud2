package awd.mis.desktop.views;

import java.util.HashMap;
import java.util.Map;

public class GlobalVar {

	public static Map<String, Object> config;
	public static Map<String, Object> user_config;

	public static Map<String, Object> getConfig() {
		if (config == null) {
			config = new HashMap<>();
		}
		return config;
	}

	public static Map<String, Object> getUserConfig() {
		if (user_config == null) {
			user_config = new HashMap<>();
		}
		return user_config;
	}

}
