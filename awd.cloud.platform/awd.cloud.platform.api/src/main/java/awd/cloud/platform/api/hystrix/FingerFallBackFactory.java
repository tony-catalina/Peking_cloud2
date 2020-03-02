package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.FingerService;
import feign.hystrix.FallbackFactory;

public class FingerFallBackFactory implements FallbackFactory<FingerService> {

	@Override
	public FingerService create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

}
