package awd.cloud.platform.api;


import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.api.hystrix.AkyyFallBackFactory;
import awd.cloud.platform.model.kss.*;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient(name = "${awd.api:AWD-MIS-GATEWAY-SERVER}", fallbackFactory = AkyyFallBackFactory.class)

public interface AkyyService {


}
