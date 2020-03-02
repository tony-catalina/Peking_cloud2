package awd.mis.servers.api;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.api.hystrix.ManagerServiceBackFactory;
import awd.mis.servers.model.UserinfoModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "${awd.api.manager:AWD-MIS-MANAGER-SERVER}" ,url="http://192.168.4.115:12102" ,fallbackFactory = ManagerServiceBackFactory.class)
public interface ManagerService {


    @RequestMapping(value = "/userinfo/getUserByJsbhRole", method = RequestMethod.GET)
    ResponseMessage<List<UserinfoModel>> getUserByRole(@RequestParam("jsbh") String jsbh, @RequestParam("role") String role);

    @RequestMapping(value = "/userinfo/getByGroup", method = RequestMethod.GET)
    ResponseMessage<List<UserinfoModel>> getUserByGroup(@RequestParam("jsbh") String jsbh, @RequestParam("group") String group);

    @GetMapping(value = "/userinfo")
    ResponseMessage<PagerResult<UserinfoModel>> userInfoQuery(QueryParam params);

    @RequestMapping(value = "/userinfo/getUserByJsbhsRoles", method = RequestMethod.GET)
    ResponseMessage<List<UserinfoModel>> getUserByRoles(@RequestParam("jsbhs") String jsbhs, @RequestParam("roles") String roles);

    @GetMapping(value = "/userinfo/getUsersToDocument")
    ResponseMessage<PagerResult<UserinfoModel>> getUsersToDocument(@RequestParam("jsbhs") String jsbhs, @RequestParam("usertype") String usertype);
}
