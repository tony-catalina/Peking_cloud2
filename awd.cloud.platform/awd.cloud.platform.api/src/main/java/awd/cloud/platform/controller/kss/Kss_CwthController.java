package awd.cloud.platform.controller.kss;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import awd.bj.kss.model.XjzcModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.XjhzModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/cwth")
@Api(tags = "kss-cwth", description = "cwth")
public class Kss_CwthController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;

	@ApiOperation("财务退回保存")
	@PostMapping("/cwthAdd")
	@ResponseBody
	public ResponseMessage<String> cwth_Add(HttpServletRequest request, @RequestParam(name = "appcode") String appcode,
			@RequestParam(name = "jsbh") String jsbh, String json) {
		// 接口id
		String interfaceId = "/v4/kss/cwth/cwthSave";
		// 校验权限
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}
		// 数据类型校验
		Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
		map.put("interfaceId", interfaceId);
		ResponseMessage<String> msg = this.modelYz(map);
		if (msg.getStatus() != 200) {
			return ResponseMessage.error(msg.getMessage());
		}
		String blsj = maps.getResult().get("blsj").toString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<XjzcModel> xjzcModel = JSONArray.parseArray(map.get("entity").toString(), XjzcModel.class);
		try {
			xjzcModel.get(0).setState("R2");
			xjzcModel.get(0).setXflx("01");
			xjzcModel.get(0).setJsbh(jsbh);
			xjzcModel.get(0).setCurrency("156");
			xjzcModel.get(0).setBlsj(format.parse(blsj));
			xjzcModel.get(0).setCreatetime(new Date());
			xjzcModel.get(0).setCreator(maps.getResult().get("creator").toString());
			// xjzcModel.get(0).setJsbh(jsbh);

			ResponseMessage<String> responseMessage = kssServerApis.xjzcSave(xjzcModel.get(0));
			if (responseMessage.getStatus() == 200) {
				QueryParam queryXjhz = new QueryParam();
				// DefaultQueryParam.addDefaultQuerySingle(queryXjhz, "createtime", "desc");
				queryXjhz.and("rybh", xjzcModel.get(0).getRybh()).and("currency", TermType.eq, "156");
				ResponseMessage<PagerResult<XjhzModelDO>> XjhzModelDOList = kssServerApis.xjhzQueryForPage(queryXjhz);
				XjhzModelDO xjhzModel = null;
				if (XjhzModelDOList.getResult().getData().size() != 0) {
					xjhzModel = XjhzModelDOList.getResult().getData().get(0);
				}
				if (xjhzModel != null) {
					xjhzModel.setJe(xjzcModel.get(0).getXfhye());
					xjhzModel.setJsbh(jsbh);
					kssServerApis.saveOrUpdateXjhz(xjhzModel);
					return ResponseMessage.ok("保存成功");
				} else {
					kssServerApis.saveOrUpdateXjhz(xjhzModel);
					return ResponseMessage.ok("保存成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}

}
