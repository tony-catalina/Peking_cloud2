package awd.cloud.platform.api;


import org.springframework.cloud.netflix.feign.FeignClient;

import awd.cloud.platform.api.hystrix.JdsFallBackFactory;


@FeignClient(name = "${awd.api:AWD-MIS-GATEWAY-SERVER}", fallbackFactory = JdsFallBackFactory.class)

public interface JdsService {


}
