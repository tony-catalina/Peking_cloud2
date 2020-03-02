package awd.mis.servers.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.hystrix.ManagerApiFallBackFactory;
import awd.mis.servers.vo.User;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@FeignClient(name = "AWD-MIS-MANAGER-SERVER",fallbackFactory = ManagerApiFallBackFactory.class)
@Component
public interface ManagerServersApi {

    @RequestMapping(value="/authorization/getUserByName",method=RequestMethod.GET)
    public ResponseMessage<Map<String, Object>> getUserByName(@RequestParam("jsbh")String jsbh, @RequestParam("username")String username);

}
