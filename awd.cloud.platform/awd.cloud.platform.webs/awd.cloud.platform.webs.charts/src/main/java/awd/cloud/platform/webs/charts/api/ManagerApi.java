package awd.cloud.platform.webs.charts.api;

import awd.cloud.platform.webs.charts.api.hystrix.ManagerFallBackFactory;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@FeignClient(name = "${awd.mis.manager.server:AWD-MIS-MANAGER-SERVER}", fallbackFactory = ManagerFallBackFactory.class)
@Component
public interface ManagerApi {

    @GetMapping("/userinfo/getUserByJsbh")
    ResponseMessage<List<Map<String,String>>> getUsers(@RequestParam("jsbh")String jsbh);
}
