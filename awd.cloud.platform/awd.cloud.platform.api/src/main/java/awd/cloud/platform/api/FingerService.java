package awd.cloud.platform.api;


import org.springframework.cloud.netflix.feign.FeignClient;

import awd.cloud.platform.api.hystrix.FingerFallBackFactory;


@FeignClient(name = "${awd.api:AWD-MIS-GATEWAY-SERVER}", fallbackFactory = FingerFallBackFactory.class)
public interface FingerService {


}
