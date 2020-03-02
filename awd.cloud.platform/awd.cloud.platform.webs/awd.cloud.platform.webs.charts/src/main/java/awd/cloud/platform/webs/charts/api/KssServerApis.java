package awd.cloud.platform.webs.charts.api;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JyModel;
import awd.bj.kss.model.WgsjclModel;
import awd.cloud.platform.webs.charts.api.hystrix.KssServerFallBackFactory;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-17
 **/

@FeignClient(name = "${awd.api.kssBase:AWD-KSS-SERVER}",fallbackFactory = KssServerFallBackFactory.class)
@Component
public interface KssServerApis {
	
	 @GetMapping("/jbxx")
	 ResponseMessage<PagerResult<JbxxModel>> queryForPage(QueryParam queryParam);
	 
	 @GetMapping("/jy")
	 public ResponseMessage<PagerResult<JyModel>> jyQueryForPage(QueryParam queryParam);
	 
	 @GetMapping("/wggl")
	 public ResponseMessage<PagerResult<WgsjclModel>> wgsjclQueryForPage(QueryParam queryParam);
}



