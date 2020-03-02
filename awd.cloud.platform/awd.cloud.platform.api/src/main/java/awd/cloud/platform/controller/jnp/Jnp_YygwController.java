package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/yygw")
@Api(tags = "jnp-yygw",description = "yygw")
public class Jnp_YygwController extends PublicService{
	
	@Autowired
	private KssServerApis kssServerApis;

    /**
     * @api {post} /v4/jnp/yygw/yygwSave 预约购物保存
     * @apiVersion 0.4.0
     * @apiName yygwSave
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 预约购物保存
     *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 *
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *               "rybh":"人员编号(必填; 最大字段长度：21)",
	 *               "sptm":" 物品名称  (必填; 最大字段长度：50)",
	 *               "spsl":"商品数量(必填; 最大字段长度：50)",
	 *               "sj":"售价(必填; 最大字段长度：10)",
	 *               "creator":"管理员(必填; 最大字段长度：50)"
	 *               }]
	 *          }
	 *
     */
    @OpenAPI
    @ApiOperation("预约购物保存")
    @PostMapping("/yygwSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> yygw_save(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
    	Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
    	//接口id
    	String interfaceId = "/v4/jnp/yygw/yygwSave";
        //校验权限
    	try {
	        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
	        if(maps.getStatus()!=200) {
	        	return ResponseMessage.error(maps.getMessage());
	        }
	       //数据类型校验
	    	map.put("interfaceId", interfaceId);
	    	ResponseMessage<String> msg = this.modelYz(map);
	    	if(msg.getStatus()!=200) {
	    		return ResponseMessage.error(msg.getMessage());
	    	}


	    	String rq = DateTimeUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
	    	String dhbh = "kss"+ jsbh + rq;
	    	System.err.println(map.get("entity").toString());
	    	List<SpdetailorderModelDO> splist = JSONArray.parseArray(map.get("entity").toString(), SpdetailorderModelDO.class);
	    	System.err.println("splist--"+JSON.toJSONString(splist));
	    	if(splist.size()==0) {
				return ResponseMessage.error(300,"请先添加购物车");
			}
	    	splist.forEach(s ->{
				s.setState("R2");
				s.setDdbh(dhbh);
				s.setJsbh(jsbh);
				s.setSfje(s.getSj());
				s.setXfje(s.getSj());
				s.setStatus("01");
			});
	    	//流程id
	    	String processDefinitionId = CacheUtils.get().getFlowKey("KSS_GWGL");
	    	ResponseMessage<String> re = kssServerApis.insertByLists(processDefinitionId,splist,jsbh);
	    	System.err.println("re--"+JSON.toJSONString(re));
	    	return re;
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
    }

}
