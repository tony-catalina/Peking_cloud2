package awd.cloud.platform.webs.charts.api;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.bj.base.model.Variables;
import awd.cloud.platform.webs.charts.api.hystrix.AwdApiFallBackFactory;
import awd.cloud.platform.webs.charts.modal.DictionaryModel;
import awd.cloud.platform.webs.charts.modal.JbxxModelDO;
import awd.cloud.platform.webs.charts.modal.OperatelogsModel;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@FeignClient(name = "${awd.mis.manager.server:AWD-MIS-GATEWAY-SERVER}",fallbackFactory = AwdApiFallBackFactory.class)
@Component
public interface AwdApi {

	/////////////////////////////////////////////////////管理服务接口////////////////////////////////////////////////////////////////
	@RequestMapping(value="/manager/authorization/getUserByName",method=RequestMethod.GET)
    ResponseMessage<User> getUserByName(@RequestParam("jsbh")String jsbh, @RequestParam("username")String username);


	@RequestMapping(value="/manager/dictionary/getbyfield/{field}",method = RequestMethod.GET)
    ResponseMessage<List<DictionaryModel>> getByField(@PathVariable("field") String type);

	/////////////////////////////////////////////////////日志服务接口////////////////////////////////////////////////////////////////
    @RequestMapping(value="/logs/operatelogs",method=RequestMethod.POST)
    ResponseMessage<String> operatelogsSave(@RequestBody OperatelogsModel operatelogs);
    
    
    /////////////////////////////////////////////////////看守所服务接口////////////////////////////////////////////////////////////////
    @RequestMapping(value="/kssbase/jbxx",method=RequestMethod.GET)
    ResponseMessage<PagerResult<JbxxModelDO>> kss_queryJbxxForPage(QueryParam queryParam);  
    
    @RequestMapping(value="/kssbase/jbxx/getListCustom",method=RequestMethod.POST)
	ResponseMessage<PagerResult<JbxxModelDO>> kss_getListCustom(Variables variables);
    
    
    
    
	/////////////////////////////////////////////////////统计服务接口////////////////////////////////////////////////////////////////
    /**
     * 	获取在押人员数量
     * @param operatelogs
     * @return
     */
    @RequestMapping(value="/analyse/kss/jbxx/queryJbxxCount",method=RequestMethod.GET)
    ResponseMessage<Long> kss_queryJbxxCount(@RequestParam(value="jsbh",required = false)String jsbh);

    @RequestMapping(value="/analyse/kss/jbxx/queryJbxxcountByField",method=RequestMethod.GET)
	ResponseMessage<List<Map<String, Object>>> kss_queryJbxxCountByField(@RequestParam(value="jsbh",required = false)String jsbh, @RequestParam(value="field",required = true)String field, @RequestParam(value="value",required = false)String value);

    @RequestMapping(value="/analyse/kss/jbxx/queryTaskCount",method=RequestMethod.GET)
	ResponseMessage<Long> kss_queryTaskCount(@RequestParam(value="jsbh",required = false)String jsbh, @RequestParam(value="flow",required = false)String flow, @RequestParam(value="node",required = false)String node);

    


}
