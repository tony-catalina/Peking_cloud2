package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.AkyyService;
import feign.hystrix.FallbackFactory;

public class AkyyFallBackFactory implements FallbackFactory<AkyyService> {

	@Override
	public AkyyService create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

}
