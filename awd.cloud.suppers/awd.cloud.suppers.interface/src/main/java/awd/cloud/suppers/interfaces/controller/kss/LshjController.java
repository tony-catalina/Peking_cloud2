package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.model.LshjModel;
import awd.cloud.suppers.interfaces.service.kss.JsjgService;
import awd.cloud.suppers.interfaces.service.kss.LshjService;
import awd.cloud.suppers.interfaces.utils.MapUtil;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/kss/lshj")
@AccessLogger("kss")
@Api(value = "kss",description = "律师会见")
public class LshjController {

    @Autowired
    private LshjService lshjService;

    @OpenAPI
    @ApiOperation(value = "律师会见查询")
    @RequestMapping(value = "/getLshj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getLshj(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return lshjService.getLshj(param);
    }

    @OpenAPI
    @ApiOperation(value = "保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveLshj", method = {RequestMethod.POST})
    public ResponseMessage<String> saveLshj(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return lshjService.saveLshj(param);
    }

    @OpenAPI
    @ApiOperation(value = "根据id更新")
    @RequestMapping(value = "/updateLshjById", method = {RequestMethod.POST})
    public ResponseMessage<String> updateLshjById(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数
        String id = (String)map.get("id");

        return lshjService.updateLshjById(id,param);
    }
}
