package awd.cloud.suppers.interfaces.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import awd.cloud.suppers.interfaces.api.hystrix.ManagerApiFallBackFactory;
import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

/**
 * @Description
 * @Date 2019-11-15 
 */
@FeignClient(name = "${awd.mis.manager.server:AWD-MIS-MANAGER-SERVER}", fallbackFactory = ManagerApiFallBackFactory.class)
@Component
public interface ManagerApi {
    @RequestMapping(path = {"/manager/test"})
    ResponseMessage<String> test();

    @GetMapping("/userinfo")
    ResponseMessage<PagerResult<UserinfoEntity>> queryUser(@RequestBody QueryParam param);
    
    
}
