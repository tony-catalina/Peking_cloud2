package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JbxxService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("kss/dm")
@Api(tags = "kss_dm",description = "点名")
public class DmController {

    @Autowired
    private Kss_JbxxService jbxxService;

    /*img:      config.serverUrl+'jbxx/getryphotos?rybh='+res.list[i].snbh +'&jsbh='+res.list[i].jsbh,
      name:     res.list[i].xm,
      anyou:    res.list[i].ay,
      gongsu:   res.list[i].bahj*/
    @GetMapping("/queryDmList")
    @ApiOperation("获取人员信息列表")
    @OpenAPI
    public List<Map<String, Object>> queryDmList(){

        return jbxxService.getJbxxForDm();
    }
}
