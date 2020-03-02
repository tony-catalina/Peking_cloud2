package awd.cloud.platform.tasks.api;

import awd.cloud.platform.tasks.api.hystrix.AwdApiFallBackFactory;
import awd.cloud.platform.tasks.model.DictionaryModel;
import awd.cloud.platform.tasks.model.OperatelogsModel;
import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.utils.ResponseMessage;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@FeignClient(name = "${awd.mis.manager.server:AWD-MIS-GATEWAY-SERVER}", fallbackFactory = AwdApiFallBackFactory.class)
@Component
public interface AwdApi {

	@RequestMapping(value="/manager/authorization/getUserByName",method=RequestMethod.GET)
    ResponseMessage<User> getUserByName(@RequestParam("jsbh")String jsbh, @RequestParam("username")String username);


	@RequestMapping(value="/manager/dictionary/getbyfield/{field}",method = RequestMethod.GET)
    ResponseMessage<List<DictionaryModel>> getByField(@PathVariable("field") String type);

    @RequestMapping(value="/logs/operatelogs",method=RequestMethod.POST)
    ResponseMessage<String> operatelogsSave(@RequestBody OperatelogsModel operatelogs);
}
