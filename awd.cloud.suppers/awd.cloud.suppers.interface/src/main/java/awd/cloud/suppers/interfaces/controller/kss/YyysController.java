package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.service.kss.YyysService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/kss/yyys")
@AccessLogger("kss")
@Api(value = "kss",description = "预约用水")
public class YyysController {

    @Autowired
    private YyysService yyysService;

    @OpenAPI
    @ApiOperation(value = "预约用水查询")
    @RequestMapping(value = "/getYyys", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getYyys(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return yyysService.getYyys(param);
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveYyys",method = {RequestMethod.POST})
    public ResponseMessage<String> saveYyys(@RequestParam String json){

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return yyysService.saveYyys(param);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateYyysById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateYyysById(@RequestParam String json){

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数
        String id = (String)map.get("id");

        return yyysService.updateYyysById(id,param);
    }

}
