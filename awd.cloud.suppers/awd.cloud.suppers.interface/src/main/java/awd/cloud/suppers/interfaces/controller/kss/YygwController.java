package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.service.kss.YygwService;
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
@RequestMapping("/kss/yygw")
@AccessLogger("kss")
@Api(value = "kss",description = "预约购物")
public class YygwController {

    @Autowired
    private YygwService yygwService;

    @OpenAPI
    @ApiOperation(value = "预约购物查询")
    @RequestMapping(value = "/getYygw", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getYygw(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return yygwService.getYygw(param);
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveYygw",method = {RequestMethod.POST})
    public ResponseMessage<String> saveYygw(@RequestParam String json){

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return yygwService.saveYygw(param);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateYygwById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateYygwById(@RequestParam String json){

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数
        String id = (String)map.get("id");

        return yygwService.updateYygwById(id,param);
    }
}
