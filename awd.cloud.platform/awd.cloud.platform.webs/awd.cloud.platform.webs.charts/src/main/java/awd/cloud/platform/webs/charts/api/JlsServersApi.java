package awd.cloud.platform.webs.charts.api;

import awd.cloud.platform.webs.charts.api.hystrix.JlsServersApiFallBackFactory;
import awd.cloud.platform.webs.charts.modal.jls.FlwsbgModel;
import awd.cloud.platform.webs.charts.modal.jls.JbxxModel;
import awd.cloud.platform.webs.charts.modal.jls.PhotosModel;
import awd.cloud.platform.webs.charts.modal.jls.SwjyModel;
import awd.cloud.platform.webs.charts.modal.jls.XsjlModel;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Description
 * @Author WS
 * @Date 2019-06-21 16:31
 */


@FeignClient(name = "${awd.api.jls:AWD-JLS-SERVER}",fallbackFactory = JlsServersApiFallBackFactory.class)

@Component
public interface JlsServersApi {
	
		@GetMapping("/jls_jbxx")
	    ResponseMessage<PagerResult<JbxxModel>> jbxxQueryForPage(@RequestBody QueryParam param);
		
		
		@GetMapping("/jls_xsjl")
	    ResponseMessage<PagerResult<XsjlModel>> xsjlQueryForPage(@RequestBody QueryParam param);
		 //照片
	    @GetMapping("/jls_photos")
	    ResponseMessage<PagerResult<PhotosModel>> photosQueryForPage(@RequestBody QueryParam param);
	    //法律手续变更
	    @GetMapping("/jls_flwsbg")
	    ResponseMessage<PagerResult<FlwsbgModel>> flsxbgQueryForPage(@RequestBody QueryParam param);
	    
	    @GetMapping("/jls_swjy")
	    ResponseMessage<PagerResult<SwjyModel>> swjyQueryForPage(@RequestBody QueryParam param);
}
