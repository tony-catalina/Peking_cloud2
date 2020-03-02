package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.SjsService;
import feign.hystrix.FallbackFactory;

public class SjsFallBackFactory implements FallbackFactory<SjsService> {

	@Override
	public SjsService create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

}
