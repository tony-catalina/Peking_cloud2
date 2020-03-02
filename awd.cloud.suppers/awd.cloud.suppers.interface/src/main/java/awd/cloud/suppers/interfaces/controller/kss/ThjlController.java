package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.model.LshjModel;
import awd.cloud.suppers.interfaces.model.ThjlModel;
import awd.cloud.suppers.interfaces.model.TxdjModel;
import awd.cloud.suppers.interfaces.service.kss.ThjlService;
import awd.cloud.suppers.interfaces.utils.MapUtil;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.mockito.cglib.beans.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/kss/thjl")
@AccessLogger("kss")
@Api(value = "kss",description = "谈话记录")
public class ThjlController {

    @Autowired
    private ThjlService thjlService;

    @OpenAPI
    @ApiOperation(value = "谈话记录查询")
    @RequestMapping(value = "/getThjl", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getThjl(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return thjlService.getThjl(param);
    }

    @OpenAPI
    @ApiOperation(value = "保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveThjl", method = {RequestMethod.POST})
    public ResponseMessage<String> saveThjl(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return thjlService.saveThjl(param);
    }

    @OpenAPI
    @ApiOperation(value = "根据id更新")
    @RequestMapping(value = "/updateThjlById", method = {RequestMethod.POST})
    public ResponseMessage<String> updateThjlById(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数
        String id = (String)map.get("id");

        return thjlService.updateThjlById(id,param);
    }
}
