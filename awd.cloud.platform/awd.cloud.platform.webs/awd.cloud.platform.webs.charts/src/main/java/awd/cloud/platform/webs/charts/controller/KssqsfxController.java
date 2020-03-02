package awd.cloud.platform.webs.charts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/kssqsfx")
public class KssqsfxController {

	@Autowired
	private AnalyseApis analyseApis;

	@GetMapping("/ylwsCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("医疗情况分析查询")
	public ResponseMessage<Map<String, Object>> ylwsCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.ylwsCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/ryflfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("人员分类分析查询")
	public ResponseMessage<Map<String, Object>> ryflfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.ryflfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/shseCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("涉黑涉恶人员统计")
	public ResponseMessage<Map<String, Object>> shseCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.shseCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/zdryCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("重点人员分析")
	public ResponseMessage<Map<String, Object>> zdryCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.zdryCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/gjfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("国籍分析")
	public ResponseMessage<Map<String, Object>> gjfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.gjfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/cqjyCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("超期羁押")
	public ResponseMessage<Map<String, Object>> cqjyCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.cqjyCount();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/gyqxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("关押期限分析")
	public ResponseMessage<List<Map<String, Object>>> gyqxCount() {
		try {
			ResponseMessage<List<Map<String, Object>>> map = analyseApis.gyqxCount();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/ajqkfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("案件情况分析")
	public ResponseMessage<Map<String, Object>> ajqkfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.ajqkfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/lsfxfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("留所服刑分析")
	public ResponseMessage<Map<String, Object>> lsfxfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.lsfxfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/yzjbfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("严重疾病分析")
	public ResponseMessage<Map<String, Object>> yzjbfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.yzjbfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/wgqkfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("违规事件分析")
	public ResponseMessage<Map<String, Object>> wgqkfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.wgqkfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/tswsfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("投送未收分析")
	public ResponseMessage<Map<String, Object>> tswsfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.tswsfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	
	@GetMapping("/lslsfxCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("临时离所分析")
	public ResponseMessage<Map<String, Object>> lslsfxCount() {
		try {
			ResponseMessage<Map<String, Object>> map = analyseApis.lslsfxCount();
			System.err.println("map--" + JSON.toJSONString(map));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/wgCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("违规分析")
	public Map<String,Object> wgCount(HttpServletRequest request) {
		try {
			String jsbh = request.getParameter("strify");
			String jsh=request.getParameter("jsh");
			return analyseApis.wgCount(jsbh,jsh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
