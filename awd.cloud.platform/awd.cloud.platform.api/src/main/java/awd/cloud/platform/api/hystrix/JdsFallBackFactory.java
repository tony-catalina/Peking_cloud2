package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.JdsService;
import feign.hystrix.FallbackFactory;

public class JdsFallBackFactory implements FallbackFactory<JdsService> {

	@Override
	public JdsService create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

}
