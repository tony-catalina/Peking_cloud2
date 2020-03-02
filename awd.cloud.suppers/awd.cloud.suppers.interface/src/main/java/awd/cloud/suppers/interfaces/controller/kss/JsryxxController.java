package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.service.kss.JsryxxService;
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
@RequestMapping("/kss/jsryxx")
@AccessLogger("kss")
@Api(value = "kss",description = "当前监室在押人员信息")
public class JsryxxController {

    @Autowired
    private JsryxxService jsryxxService;

    @OpenAPI
    @ApiOperation(value = "当前监室在押人员信息")
    @RequestMapping(value = "/getJsryxx", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getJsryxx(@RequestParam String json) {

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");//条件参数

        return jsryxxService.getJsryxx(param);
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveJsryxx",method = {RequestMethod.POST})
    public ResponseMessage<String> saveJsryxx(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");

        return jsryxxService.saveJsryxx(param);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateJsryxxById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateJsryxxById(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");
        String id = (String)map.get("id");

        return jsryxxService.updateJsryxxById(id,param);
    }
}
