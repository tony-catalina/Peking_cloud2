package awd.cloud.platform.controller.kss;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import awd.bj.kss.model.ShgxModel;
import awd.bj.kss.model.WpglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.WpjsModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wpjs")
@Api(tags = "kss-wpjs", description = "wpjs")
public class Kss_WpjsController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;
	
	
/**
    
     * @api {post} /v4/kss/wpjs/wpjsSave 物品接收保存
     * @apiVersion 0.4.0
     * @apiName wpjsSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 物品接收保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json                                              保存参数集(必填)

     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000005",
     *   "status": 200,
     *   "timestamp": 1576311187399
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *   appcode:"应用代码(必填)",
     *   jsbh:"监所编号(必填; 字段长度：9)",
     *   json:{"entity":[{
     *                 "jszjh":"家属证件号(必填; 最大长度:18)"
     *                 "wpmc":"物品名称(必填; 最大长度:50)",
     *                 "jsxm":"家属姓名(必填; 最大长度:20)",
     *                 "gx":"与关押人员关系(必填; 最大长度:3)",
     *                 "dh":"联系电话(必填; 最大长度:40)",
     *                 "dz":"地址(必填; 最大长度:255)",
     *                 "blrq":"办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *                 "creator":"创建人(必填；最大长度:255)",     
     *               }]
     *           }
     *
     */
	@ApiOperation("物品接收保存")
	@PostMapping("/wpjsSave")
	@ResponseBody
	public ResponseMessage<String> wpjs_Add(HttpServletRequest request, @RequestParam(name = "appcode") String appcode,
			@RequestParam(name = "jsbh") String jsbh, String json) {
		// 接口id
		String interfaceId = "/v4/kss/wpjs/wpjsSave";
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
		String jsxm = maps.getResult().get("jsxm").toString();
		String gx = maps.getResult().get("gx").toString();
		String dh = maps.getResult().get("dh").toString();
		String dz = maps.getResult().get("dz").toString();
		String jszjh = maps.getResult().get("jszjh").toString();
		String djsj = maps.getResult().get("blrq").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String xb;
		if (Integer.parseInt(jszjh.substring(16).substring(0, 1)) % 2 == 0) {
			xb = "2";
		} else {
			xb = "1";
		}
		// 算年龄
		Integer selectYear = Integer.valueOf(jszjh.substring(6, 10)); // 出生的年份
		Integer selectMonth = Integer.valueOf(jszjh.substring(10, 12)); // 出生的月份
		Integer selectDay = Integer.valueOf(jszjh.substring(12, 14)); // 出生的日期
		Calendar cal = Calendar.getInstance();
		Integer yearMinus = cal.get(Calendar.YEAR) - selectYear;
		Integer monthMinus = cal.get(Calendar.MONTH) + 1 - selectMonth;
		Integer dayMinus = cal.get(Calendar.DATE) - selectDay;
		Integer age = yearMinus;
		if (yearMinus < 0) {
			age = 0;
		} else if (yearMinus == 0) {
			age = 0;
		} else if (yearMinus > 0) {
			if (monthMinus == 0) {
				if (dayMinus < 0) {
					age = age - 1;
				}
			} else if (monthMinus > 0) {
				age = age + 1;
			}
		}
		List<WpjsModelDO> modelList = JSONArray.parseArray(map.get("entity").toString(), WpjsModelDO.class);
		try {
			Map<String, Object> mapss = new HashMap<String, Object>();
			modelList.get(0).setJswpmc(maps.getResult().get("wpmc").toString());
			modelList.get(0).setSrlx("2");
			modelList.get(0).setState("R2");
			modelList.get(0).setJsbh(jsbh);
			modelList.get(0).setCreator(maps.getResult().get("creator").toString());
			modelList.get(0).setCreatetime(new Date());
			modelList.get(0).setDjr(maps.getResult().get("creator").toString());
			modelList.get(0).setDjsj(sdf.parse(djsj));
			mapss.put("WpjsEntity", modelList);
			mapss.put("sl", maps.getResult().get("sl").toString()); // 数量不填默认为1
			List<WpglModel> wpglEntity = JSONArray.parseArray(map.get("entity").toString(), WpglModel.class);
			wpglEntity.get(0).setLqzt("0");
			wpglEntity.get(0).setCreator(maps.getResult().get("creator").toString());
			wpglEntity.get(0).setJsbh(jsbh);
			wpglEntity.get(0).setCreatetime(new Date());
			mapss.put("WpglEntity", wpglEntity);
			List<ShgxModel> shgxEntity = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class);
			shgxEntity.get(0).setJsbh(jsbh);
			shgxEntity.get(0).setNl(String.valueOf(age));
			shgxEntity.get(0).setCreator(maps.getResult().get("creator").toString());
			shgxEntity.get(0).setCreatetime(new Date());
			shgxEntity.get(0).setState("R2");
			shgxEntity.get(0).setXb(xb);
			mapss.put("ShgxEntity", shgxEntity);
			mapss.put("jsbh", jsbh);
			ResponseMessage<Integer> num = kssServerApis.saveWpAndShgx(mapss, jsbh);
			if (1 == num.getResult()) {
				return ResponseMessage.ok("保存成功");
			} else {
				return ResponseMessage.ok("保存失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
}
