package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.service.kss.GjthService;
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
@RequestMapping("/kss/gjth")
@AccessLogger("kss")
@Api(value = "kss",description = "管教谈话")
public class GjthController {

    @Autowired
    private GjthService gjthService;

    @OpenAPI
    @ApiOperation(value = "管教谈话查询")
    @RequestMapping(value = "/getGjth", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getGjth(@RequestParam String json) {

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");//条件参数

        return gjthService.getGjth(param);
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveGjth",method = {RequestMethod.POST})
    public ResponseMessage<String> saveGjth(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");

        return gjthService.saveGjth(param);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateGjthById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateGjthById(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");
        String id = (String)map.get("id");

        return gjthService.updateGjthById(id,param);
    }
}
