package awd.cloud.suppers.interfaces.controller.jls;

import awd.cloud.suppers.interfaces.service.jls.BjjlService;
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
@RequestMapping("/jls/bjjl")
@AccessLogger("jls")
@Api(value = "jls")
public class BjjlController {

    @Autowired
    private BjjlService bjjlService;

    @OpenAPI
    @ApiOperation(value="自定义查询")
    @RequestMapping(value = "/getBjjl",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseMessage<PagerResult<Map<String, Object>>> getBjjl() {

        return bjjlService.getBjjl();
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveBjjl",method = {RequestMethod.POST})
    public ResponseMessage<String> saveBjjl() {

        Map<String,Object> map = Maps.newHashMap();

        return bjjlService.saveBjjl(map);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateBjjlById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateBjjlById() {

        String id = "";
        Map<String,Object> map = Maps.newHashMap();

        return bjjlService.updateBjjlById(id,map);
    }
}
