package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/kss/xlpc")
@Api(tags = "kss-xlpc",description = "xlpc")
public class Kss_XlpcController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    @OpenAPI
    @ApiOperation("心理评测查询")
    @GetMapping("/xlpcQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xlpc_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/kss/xlpc/xlpcQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
         ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }


        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsbh"))) {
            param.and("jsbh", TermType.eq, maps.getResult().get("jsbh"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
            param.and("user", TermType.eq, maps.getResult().get("user"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.lshjQuery(param);
        System.err.println("result"+ JSON.toJSONString(result));




        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", result.getResult().getData());
        maplist.put("interfaceId", interfaceId);
        maplist.put("total",  result.getResult().getTotal());
        maplist.put("page",  request.getParameter("page"));
        ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
        if(list.getStatus()==200) {
            list.setMessage("查询成功");
            if(list.getResult()==null) {
                list.setMessage("未查询数据");
            }
        }
        return list;
    }
}
