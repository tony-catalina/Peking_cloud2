/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_FjszModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/fjsz")
@Api(tags = "kss-fjsz",description = "Fjsz") 
public class Kss_FjszController {
	
	@Autowired
    private KssService kssService;


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_FjszModel>> fjsz_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_FjszModel>> result= kssService.fjsz_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fjsz_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_FjszModel data) {
		return kssService.fjsz_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fjsz_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_FjszModel data) {
		return kssService.fjsz_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_FjszModel> fjsz_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.fjsz_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> fjsz_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.fjsz_deleteByKey(id);
	}
}
