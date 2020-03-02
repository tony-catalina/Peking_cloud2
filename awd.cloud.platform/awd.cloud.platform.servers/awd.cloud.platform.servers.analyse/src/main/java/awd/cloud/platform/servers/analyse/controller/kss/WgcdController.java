package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.BJSDY_kss_wgcdService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_WgsjclService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Descriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("kss/wgcd")
@Api(description = "违规分析==" )
public  class WgcdController {
	@Autowired
	private BJSDY_kss_wgcdService kss_wgcdService;


	@GetMapping("/wgCount")
	@ApiOperation("违规分析")
	@OpenAPI
	public Map<String, Object> wgCount(@RequestParam(value = "jsbh", required = false) String jsbh) {
		return kss_wgcdService.wgCount(jsbh);

	}
}


