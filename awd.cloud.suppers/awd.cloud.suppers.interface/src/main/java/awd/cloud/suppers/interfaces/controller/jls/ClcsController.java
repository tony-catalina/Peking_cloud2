package awd.cloud.suppers.interfaces.controller.jls;

import awd.cloud.suppers.interfaces.service.jls.ClcsService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/jls/clcs")
@AccessLogger("jls")
@Api(value = "jls")
public class ClcsController {

    @Autowired
    private ClcsService clcsService;

    @OpenAPI
    @ApiOperation(value="自定义查询")
    @RequestMapping(value = "/getClcs",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseMessage<PagerResult<Map<String, Object>>> getClcs() {

        return clcsService.getClcs();
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveClcs",method = {RequestMethod.POST})
    public ResponseMessage<String> saveClcs() {

        Map<String,Object> map = Maps.newHashMap();

        return clcsService.saveClcs(map);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateClcsById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateClcsById() {

        String id = "";
        Map<String,Object> map = Maps.newHashMap();

        return clcsService.updateClcsById(id,map);
    }

}
