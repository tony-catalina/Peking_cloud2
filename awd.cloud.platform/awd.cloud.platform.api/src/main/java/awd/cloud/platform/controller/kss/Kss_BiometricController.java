package awd.cloud.platform.controller.kss;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.MjjbxxModel;
import awd.bj.manager.model.MfingerModel;
import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_MfaceModel;
import awd.cloud.platform.model.manager.Manager_MfingerModel;
import awd.cloud.platform.model.manager.Manager_MirisModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/biometric")
@Api(tags = "kss-biometric", description = "Biometric")
public class Kss_BiometricController extends PublicService {

	@Autowired
	private ManagerService managerApi;

	/**
	 * @return
	 * @api {get} /v4/kss/biometric/mfingerQuery 生物特征指纹查询
	 * @apiVersion 0.4.0
	 * @apiName mfingerQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 生物特征指纹查询
	 * @apiParam {String} appcode 					应用代码(必填)
	 * @apiParam {String} jsbh 						监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 						查询参数集
	 *
	 * @apiSuccess {String}jh 						警号
	 * @apiSuccess {String}zjh 						证件号
	 * @apiSuccess {String}szbh 					手指编号
	 * @apiSuccess {String}zwtx 					指纹图像
	 * @apiSuccess {String}zwtz 					指纹特征
	 * @apiSuccess {String}zwtxurl 					指纹图像地址
	 * @apiSuccess {String}zwtzurl 					指纹特征地址
	 * @apiSuccess {String}createtime 				创建时间
	 * @apiSuccess {String}creator 					创建人
	 * @apiSuccess {String}id 						id
	 * @apiSuccess {String}jsbh 					监所编号
	 * @apiSuccess {String}scbz 					上传标志
	 * @apiSuccess {String}state 					状态
	 * @apiSuccess {String}updatetime 				更新时间
	 * @apiSuccess {String}updator 					更新人
	 * 
	 *
	 *
	 *
	 * @apiSuccess {String}message 					返回信息
	 * @apiSuccess {String}result 					返回结果
	 * @apiSuccess {String}total 					返回总数
	 * @apiSuccess {String}data 					返回数据
	 * @apiSuccess {String}status 					返回状态
	 * @apiSuccess {String}timestamp 				时间戳
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK 
	 * 			{ 
	 * 				"message": "查询成功",
	 *              "result": { 
	 *              	"total": 1, 
	 *              	"data": [ 
	 *              		"createtime":"2020-01-14 16:16:27" "creator": "管理员" 
	 *              		"id":"11000011420200114000005" ,
	 *                    	"jh": "10086" ,
	 *                    	"jsbh":"110000114" ,
	 *                   	"scbz": "" ,
	 *                   	"state": "R2" ,
	 *                   	"szbh": "",
	 *                    	"updatetime": "" ,
	 *                    	"updator": "" ,
	 *                    	"zjh":"321323199706174318" ,
	 *                    	"zwtx": "", 
	 *                    	"zwtxurl":"http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4deNuACd57AAFhB0BF4Rk444.jpg",
	 *                    	"zwtz":"4c655352323300000526240505050709ced000001d276900000064043d248726f7016d64990004007f42b301c20007641900e22612653d00b600a1648e2642002c64f300c7011e42d70136016564470059272c65aa005d01dc64e6264e00676434008200fb427401db006364580003275e65500013011664c126ac018864ac00fe011d4242019a0072644600732680651f000d01081376265a003264e400f9014c42f40152014a648900372680657900f30033646c2606004d6472005f00714272018f00fd64b4008c267b65cd003b015564e226200029645b00940133421101ec00d53eb4005a268265c20045004e641c2636017b6409a0b21ec42c1f7a201f290edc08760a6c162ff4f23763061cdd8c1d567f38973142ec822291f8380b0cfea6ef191c8a1de5f23ccdf04625664234011ba23a22b1dc234b14287113e4b4f70509093cbab63c6ad20fa1d40e2c1c1bf7f91c4dd80ed7fb1b2101ed54110fdff6222a01282dec2d22ec3048891220d813de381dc7021eb7d7da188606647c42917b547e8c1d706c1f4bf62c042c2e6d301eb6f229b569f1a0171157461c9b8631f70631b4dc3b98661c55442f63283e2975548114e4688d04dfed22abff2aa6b318710f2e0efe31003d3100531b5f071da4fedb56810e500c2462f8317dfa4f96815ad6970f51c01adbda232f06375019cac8e13578e317b9b91b3920179be21ddb012ad4202ece9c216383337b06ff23fb7122010997891886931fc0a8289e971a0f921ca45c1f1ae02efeeaced07237cbfb28def32f49020310820ac7891e5ab930445c07387d114406dfe5fc0cc7f60adc2b1737471b28282070781bb3011b99c620270e25b799df5a010f5408323f0a3fc66321fc094787714bc1c75db2ae1c23022a3d09f53b831ca003505a0a55ad086d12026b59111f1bb83304dc6016f86c35093c9bff990e0005002147f21f2d3000cbda2d13d87ba071b289afdd203b01c74f1b5e0d0148180354964fc72a01301e09c068b64600260d29065d090097320664fe5304004a36bf3e14263544f74151ffb4fec4e64b02002c4780070405ec490d681100348f09c61d6c63c23ec009c57059af7ac1790c0078a509c57f595209007061bffe53e77a0b00807683afc06fe415010f74f4380560c5645755c109006f427dc5e4fe5dc20c00774cfa3d7359c104006f8eb26a09267790fd4b565005050567997bc35f0a00b5987fe4c1717d0c00785ffd4111fec05b15000750e9fb6539c0c136ffc0965b0326409f70c1ff96c00076b875751300c2a94c7181577f79990600ca68095fe61001c2af835fbdc1c4e4c1827d06003b706b85e407013eba6271050d05bec07bc1c184c246690d26b4c30958ffff07160420c68ec1c1fec305c0c55ec3c1c2c18485d00106f6916bc1c15b89b0c272e70d017bd9f4ff3afffbe642470a0073daa8c38d56c0070076df6205c28d3401d7e590c2c046c3c7e7c0c37ac26504c5dee336530511050296af160420e49162c0c2fe5378c7e491c0ffc40400caf046530b017bf0e9fdf0fffb660c0186f4f4c038fffad9fdc0ffc03e08c581ff44c5c267c205105e0761e29e071065095043c00d366a0b49ff8970c310ec052640ff0310b3e922c12f11d531abc5c400c9c3ba0511c855fac6030815eb37a6c6c8d0c403ca0836e136b1c2c3a503c5c6e5c3c20310d8398cf710370832a9c26ac00688c4e2c0c4c3c0c0c3b90415af44318304108f80296e3711f44db476c365c3c1e2c4c0c4c2c015d4094d96c3c1c269c2c2078fc1e4c381c2524200ce430426010a45524442c4010526011700000000c300052601010000454200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	 *                    	"zwtzurl": "" 
	 *                    		], 
	 *                    	"page": "1" 
	 *                    },
	 *                    	"status": 200,
	 *                    	"timestamp": 1576826568061 }
	 * @apiUse QueryError
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)", 
	 * 				json:{
	 *             			"rybh":"人员编号(最大字段长度：21)", 
	 *             		}
	 */
	@OpenAPI
	@ApiOperation("生物特征指纹查询")
	@GetMapping("/mfingerQuery")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	public ResponseMessage<String> mfinger_Query(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/biometric/mfingerQuery";
		String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 查询参数
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
				MfingerModel mfingerModel = CacheUtils.get().getMfingerModel(maps.getResult().get("zjh").toString());
				if (mfingerModel == null) {
					QueryParam queryParam = new QueryParam();
					queryParam.and("zjh", maps.getResult().get("zjh"));
					queryParam.and("jsbh", jsbh);
					DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
					ResponseMessage<PagerResult<Manager_MfingerModel>> res = managerApi.mfinger_query(queryParam);
					if (res != null && res.getResult() != null && res.getResult().getData() != null
							&& res.getResult().getData().size() > 0) {
						Manager_MfingerModel finger = res.getResult().getData().get(0);
						CacheUtils.setCache(
								CacheUtils.CACHE_M_FINGER + finger.getZjh() + finger.getJh() + finger.getJsbh(),
								JSONUtil.toJson(finger));
						return ResponseMessage.ok("查询成功");
					} else {
						return ResponseMessage.error("查询失败");
					}
				}
			} else {
				return ResponseMessage.error("证件号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("查询成功");
	}

	/**
	 * @api {post} /v4/kss/biometric/mfingerCJSave 民警指纹采集保存
	 * @apiVersion 0.4.0
	 * @apiName mfingerCJSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警指纹采集保存.
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss))",
	 *             				"creator":"创建人(必填;最大字段长度：30))",
	 *             				"jh":"警号(必填;最大字段长度：20))", 
	 *             				"jsbh":"监所编号(必填;最大字段长度：9))",
	 *             				"szbh":"手指编号(必填;最大字段长度：1))", 
	 *             				"zjh":"证件号(必填;最大字段长度：21))",
	 *             				"zwtx":"指纹图像(必填;))", 
	 *             				"zwtxurl":"指纹图像地址(必填;最大字段长度：255))",
	 *             				"zwtz":"指纹特征(必填;))", 
	 *             				"zwtzurl":"指纹特征地址(必填;最大字段长度：255))", 
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警指纹采集保存")
	@PostMapping("mfingerCJSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mfingerCJ_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/biometric/mfingerCJSave";
		try {
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
			String ip = CacheUtils.get().getBiometricIP(this.getIp(request));
			Manager_MfingerModel mfingerModel = new Manager_MfingerModel();
			mfingerModel.setJsbh(jsbh);
			mfingerModel.setJh(maps.getResult().get("jh").toString());
			mfingerModel.setZjh(maps.getResult().get("zjh").toString());
			mfingerModel.setCreator(maps.getResult().get("creator").toString());
			mfingerModel.setCreatetime(Calendar.getInstance().getTime());
			mfingerModel.setState("R2");
			ResponseMessage<String> res = managerApi.mfingerCJ(mfingerModel, ip);
			if (res.getStatus() == 200) {
				res.setMessage("保存成功!");
			} else {
				res.setMessage("服务异常,保存失败!");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	private String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 处理多IP的情况（只取第一个IP）
		if (ip != null && ip.contains(",")) {
			String[] ipArray = ip.split(",");
			ip = ipArray[0];
		}
		System.err.println(ip);
		return ip;
	}

	
	/**
	 * @return
	 * @api {get} /v4/kss/biometric/mirisQuery 生物特征虹膜查询
	 * @apiVersion 0.4.0
	 * @apiName mirisQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 生物特征虹膜查询
	 * @apiParam {String} appcode 					应用代码(必填)
	 * @apiParam {String} jsbh 						监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 						查询参数集
	 *
	 * @apiSuccess {String}jh 						警号
	 * @apiSuccess {String}zjh 						证件号
	 * @apiSuccess {String}hmwzbh 					虹膜位置编号
	 * @apiSuccess {String}hmtx 					虹膜图像
	 * @apiSuccess {String}hmtz 					虹膜特征
	 * @apiSuccess {String}hmtxurl 					虹膜图像地址
	 * @apiSuccess {String}hmtzurl 					虹膜特征地址
	 * @apiSuccess {String}createtime 				创建时间
	 * @apiSuccess {String}creator 					创建人
	 * @apiSuccess {String}id 						id
	 * @apiSuccess {String}jsbh 					监所编号
	 * @apiSuccess {String}scbz 					上传标志
	 * @apiSuccess {String}state 					状态
	 * @apiSuccess {String}updatetime 				更新时间
	 * @apiSuccess {String}updator 					更新人
	 * 
	 *
	 *
	 *
	 * @apiSuccess {String}message 					返回信息
	 * @apiSuccess {String}result 					返回结果
	 * @apiSuccess {String}total 					返回总数
	 * @apiSuccess {String}data 					返回数据
	 * @apiSuccess {String}status 					返回状态
	 * @apiSuccess {String}timestamp 				时间戳
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK 
	 * 			{ 
	 * 				"message": "查询成功",
	 *              "result": { 
	 *              	"total": 1, 
	 *              	"data": [ 
	 *              		"createtime":"2020-01-14 16:16:27" "creator": "管理员" 
	 *              		"id":"11000011420200114000005" ,
	 *                    	"jh": "10086" ,
	 *                    	"jsbh":"110000114" ,
	 *                    	"scbz":"1",
	 *                   	"hmwzbh": "" ,
	 *                   	"state": "R2" ,
	 *                    	"updatetime": "" ,
	 *                    	"updator": "" ,
	 *                    	"zjh":"321323199706174318" ,
	 *                    	"zmtx": "", 
	 *                    	"zmtxurl":"http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4deNuACd57AAFhB0BF4Rk444.jpg",
	 *                    	"zmtz":"4c655352323300000526240505050709ced000001d276900000064043d248726f7016d64990004007f42b301c20007641900e22612653d00b600a1648e2642002c64f300c7011e42d70136016564470059272c65aa005d01dc64e6264e00676434008200fb427401db006364580003275e65500013011664c126ac018864ac00fe011d4242019a0072644600732680651f000d01081376265a003264e400f9014c42f40152014a648900372680657900f30033646c2606004d6472005f00714272018f00fd64b4008c267b65cd003b015564e226200029645b00940133421101ec00d53eb4005a268265c20045004e641c2636017b6409a0b21ec42c1f7a201f290edc08760a6c162ff4f23763061cdd8c1d567f38973142ec822291f8380b0cfea6ef191c8a1de5f23ccdf04625664234011ba23a22b1dc234b14287113e4b4f70509093cbab63c6ad20fa1d40e2c1c1bf7f91c4dd80ed7fb1b2101ed54110fdff6222a01282dec2d22ec3048891220d813de381dc7021eb7d7da188606647c42917b547e8c1d706c1f4bf62c042c2e6d301eb6f229b569f1a0171157461c9b8631f70631b4dc3b98661c55442f63283e2975548114e4688d04dfed22abff2aa6b318710f2e0efe31003d3100531b5f071da4fedb56810e500c2462f8317dfa4f96815ad6970f51c01adbda232f06375019cac8e13578e317b9b91b3920179be21ddb012ad4202ece9c216383337b06ff23fb7122010997891886931fc0a8289e971a0f921ca45c1f1ae02efeeaced07237cbfb28def32f49020310820ac7891e5ab930445c07387d114406dfe5fc0cc7f60adc2b1737471b28282070781bb3011b99c620270e25b799df5a010f5408323f0a3fc66321fc094787714bc1c75db2ae1c23022a3d09f53b831ca003505a0a55ad086d12026b59111f1bb83304dc6016f86c35093c9bff990e0005002147f21f2d3000cbda2d13d87ba071b289afdd203b01c74f1b5e0d0148180354964fc72a01301e09c068b64600260d29065d090097320664fe5304004a36bf3e14263544f74151ffb4fec4e64b02002c4780070405ec490d681100348f09c61d6c63c23ec009c57059af7ac1790c0078a509c57f595209007061bffe53e77a0b00807683afc06fe415010f74f4380560c5645755c109006f427dc5e4fe5dc20c00774cfa3d7359c104006f8eb26a09267790fd4b565005050567997bc35f0a00b5987fe4c1717d0c00785ffd4111fec05b15000750e9fb6539c0c136ffc0965b0326409f70c1ff96c00076b875751300c2a94c7181577f79990600ca68095fe61001c2af835fbdc1c4e4c1827d06003b706b85e407013eba6271050d05bec07bc1c184c246690d26b4c30958ffff07160420c68ec1c1fec305c0c55ec3c1c2c18485d00106f6916bc1c15b89b0c272e70d017bd9f4ff3afffbe642470a0073daa8c38d56c0070076df6205c28d3401d7e590c2c046c3c7e7c0c37ac26504c5dee336530511050296af160420e49162c0c2fe5378c7e491c0ffc40400caf046530b017bf0e9fdf0fffb660c0186f4f4c038fffad9fdc0ffc03e08c581ff44c5c267c205105e0761e29e071065095043c00d366a0b49ff8970c310ec052640ff0310b3e922c12f11d531abc5c400c9c3ba0511c855fac6030815eb37a6c6c8d0c403ca0836e136b1c2c3a503c5c6e5c3c20310d8398cf710370832a9c26ac00688c4e2c0c4c3c0c0c3b90415af44318304108f80296e3711f44db476c365c3c1e2c4c0c4c2c015d4094d96c3c1c269c2c2078fc1e4c381c2524200ce430426010a45524442c4010526011700000000c300052601010000454200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	 *                    	"zmtzurl": "" 
	 *                    		], 
	 *                    	"page": "1" 
	 *                    },
	 *                    	"status": 200,
	 *                    	"timestamp": 1576826568061 }
	 * @apiUse QueryError
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)", 
	 * 				json:{
	 *             			"rybh":"人员编号(最大字段长度：21)", 
	 *             		}
	 */
	@OpenAPI
	@ApiOperation("生物特征虹膜查询")
	@GetMapping("/mirisQuery")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	public ResponseMessage<String> miris_Query(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/biometric/mirisQuery";
		String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 查询参数
			List<Manager_MirisModel> mirisModelList = CacheUtils.get()
					.getMirisModel(maps.getResult().get("zjh").toString());
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
				if (mirisModelList.size() == 0) {
					QueryParam queryParam = new QueryParam();
					queryParam.and("zjh", maps.getResult().get("zjh"));
					queryParam.and("jsbh", jsbh);
					DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
					ResponseMessage<PagerResult<Manager_MirisModel>> res = managerApi.miris_query(queryParam);
					if (res != null && res.getResult() != null && res.getResult().getData() != null
							&& res.getResult().getData().size() > 0) {
						List<Manager_MirisModel> irisList = res.getResult().getData();
						for (Manager_MirisModel iris : irisList) {
							CacheUtils.setCache(CacheUtils.CACHE_M_IRIS + iris.getZjh() + iris.getJh() + iris.getJsbh()
									+ iris.getId(), JSONUtil.toJson(iris));
						}
						return ResponseMessage.ok("查询成功");
					} else {
						return ResponseMessage.error("查询失败");
					}
				}
			} else {
				return ResponseMessage.error("证件号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("查询成功");
	}

	
	/**
	 * @api {post} /v4/kss/biometric/mIrisCJSave 民警虹膜采集保存
	 * @apiVersion 0.4.0
	 * @apiName mIrisCJSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警虹膜采集保存.
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss)",
	 *             				"creator":"创建人(必填;最大字段长度：30)",
	 *             				"jh":"警号(必填;最大字段长度：20))", 
	 *             				"jsbh":"监所编号(必填;最大字段长度：9)",
	 *             				"hmwzbh":"虹膜位置编号(必填;最大字段长度：1)", 
	 *             				"zjh":"证件号(必填;最大字段长度：21))",
	 *             				"zmtx":"虹膜图像(必填;)", 
	 *             				"zmtxurl":"虹膜图像地址(必填;最大字段长度：255)",
	 *             				"zmtz":"虹膜特征(必填;)", 
	 *             				"zmtzurl":"虹膜特征地址(必填;最大字段长度：255)", 
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警虹膜采集保存")
	@PostMapping("mIrisCJSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mIrisCJ_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/biometric/mIrisCJSave";
		try {
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
			String ip = CacheUtils.get().getBiometricIP(this.getIp(request));
			Manager_MirisModel mirisModel = new Manager_MirisModel();
			mirisModel.setJsbh(jsbh);
			mirisModel.setJh(maps.getResult().get("jh").toString());
			mirisModel.setZjh(maps.getResult().get("zjh").toString());
			mirisModel.setCreator(maps.getResult().get("creator").toString());
			mirisModel.setCreatetime(Calendar.getInstance().getTime());
			mirisModel.setState("R2");
			ResponseMessage<String> res = managerApi.mIrisCJ(mirisModel, ip);
			if (res.getStatus() == 200) {
				res.setMessage("保存成功!");
			} else {
				res.setMessage("服务异常,保存失败!");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @return
	 * @api {get} /v4/kss/biometric/mfaceQuery 生物特征人脸查询
	 * @apiVersion 0.4.0
	 * @apiName mfaceQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 生物特征人脸查询
	 * @apiParam {String} appcode 					应用代码(必填)
	 * @apiParam {String} jsbh 						监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 						查询参数集
	 *
	 * @apiSuccess {String}jh 						警号
	 * @apiSuccess {String}zjh 						证件号
	 * @apiSuccess {String}szbh 					手指编号
	 * @apiSuccess {String}zwtx 					指纹图像
	 * @apiSuccess {String}zwtz 					指纹特征
	 * @apiSuccess {String}zwtxurl 					指纹图像地址
	 * @apiSuccess {String}zwtzurl 					指纹特征地址
	 * @apiSuccess {String}createtime 				创建时间
	 * @apiSuccess {String}creator 					创建人
	 * @apiSuccess {String}id 						id
	 * @apiSuccess {String}jsbh 					监所编号
	 * @apiSuccess {String}scbz 					上传标志
	 * @apiSuccess {String}state 					状态
	 * @apiSuccess {String}updatetime 				更新时间
	 * @apiSuccess {String}updator 					更新人
	 * 
	 *
	 *
	 *
	 * @apiSuccess {String}message 					返回信息
	 * @apiSuccess {String}result 					返回结果
	 * @apiSuccess {String}total 					返回总数
	 * @apiSuccess {String}data 					返回数据
	 * @apiSuccess {String}status 					返回状态
	 * @apiSuccess {String}timestamp 				时间戳
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK 
	 * 			{ 
	 * 				"message": "查询成功",
	 *              "result": { 
	 *              	"total": 1, 
	 *              	"data": [ 
	 *              		"createtime":"2020-01-14 16:16:27" "creator": "管理员" 
	 *              		"id":"11000011420200114000005" ,
	 *                    	"jh": "10086" ,
	 *                    	"jsbh":"110000114" ,
	 *                    	"cjfs":"1",
	 *                    	"scbz":"0",
	 *                   	"hmwzbh": "" ,
	 *                    	"updatetime": "" ,
	 *                    	"updator": "" ,
	 *                    	"zjh":"321323199706174318" ,
	 *                    	"rltx": "", 
	 *                    	"rltxurl":"http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4deNuACd57AAFhB0BF4Rk444.jpg",
	 *                    	"rltz":"4c655352323300000526240505050709ced000001d276900000064043d248726f7016d64990004007f42b301c20007641900e22612653d00b600a1648e2642002c64f300c7011e42d70136016564470059272c65aa005d01dc64e6264e00676434008200fb427401db006364580003275e65500013011664c126ac018864ac00fe011d4242019a0072644600732680651f000d01081376265a003264e400f9014c42f40152014a648900372680657900f30033646c2606004d6472005f00714272018f00fd64b4008c267b65cd003b015564e226200029645b00940133421101ec00d53eb4005a268265c20045004e641c2636017b6409a0b21ec42c1f7a201f290edc08760a6c162ff4f23763061cdd8c1d567f38973142ec822291f8380b0cfea6ef191c8a1de5f23ccdf04625664234011ba23a22b1dc234b14287113e4b4f70509093cbab63c6ad20fa1d40e2c1c1bf7f91c4dd80ed7fb1b2101ed54110fdff6222a01282dec2d22ec3048891220d813de381dc7021eb7d7da188606647c42917b547e8c1d706c1f4bf62c042c2e6d301eb6f229b569f1a0171157461c9b8631f70631b4dc3b98661c55442f63283e2975548114e4688d04dfed22abff2aa6b318710f2e0efe31003d3100531b5f071da4fedb56810e500c2462f8317dfa4f96815ad6970f51c01adbda232f06375019cac8e13578e317b9b91b3920179be21ddb012ad4202ece9c216383337b06ff23fb7122010997891886931fc0a8289e971a0f921ca45c1f1ae02efeeaced07237cbfb28def32f49020310820ac7891e5ab930445c07387d114406dfe5fc0cc7f60adc2b1737471b28282070781bb3011b99c620270e25b799df5a010f5408323f0a3fc66321fc094787714bc1c75db2ae1c23022a3d09f53b831ca003505a0a55ad086d12026b59111f1bb83304dc6016f86c35093c9bff990e0005002147f21f2d3000cbda2d13d87ba071b289afdd203b01c74f1b5e0d0148180354964fc72a01301e09c068b64600260d29065d090097320664fe5304004a36bf3e14263544f74151ffb4fec4e64b02002c4780070405ec490d681100348f09c61d6c63c23ec009c57059af7ac1790c0078a509c57f595209007061bffe53e77a0b00807683afc06fe415010f74f4380560c5645755c109006f427dc5e4fe5dc20c00774cfa3d7359c104006f8eb26a09267790fd4b565005050567997bc35f0a00b5987fe4c1717d0c00785ffd4111fec05b15000750e9fb6539c0c136ffc0965b0326409f70c1ff96c00076b875751300c2a94c7181577f79990600ca68095fe61001c2af835fbdc1c4e4c1827d06003b706b85e407013eba6271050d05bec07bc1c184c246690d26b4c30958ffff07160420c68ec1c1fec305c0c55ec3c1c2c18485d00106f6916bc1c15b89b0c272e70d017bd9f4ff3afffbe642470a0073daa8c38d56c0070076df6205c28d3401d7e590c2c046c3c7e7c0c37ac26504c5dee336530511050296af160420e49162c0c2fe5378c7e491c0ffc40400caf046530b017bf0e9fdf0fffb660c0186f4f4c038fffad9fdc0ffc03e08c581ff44c5c267c205105e0761e29e071065095043c00d366a0b49ff8970c310ec052640ff0310b3e922c12f11d531abc5c400c9c3ba0511c855fac6030815eb37a6c6c8d0c403ca0836e136b1c2c3a503c5c6e5c3c20310d8398cf710370832a9c26ac00688c4e2c0c4c3c0c0c3b90415af44318304108f80296e3711f44db476c365c3c1e2c4c0c4c2c015d4094d96c3c1c269c2c2078fc1e4c381c2524200ce430426010a45524442c4010526011700000000c300052601010000454200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	 *                    	"zmtzurl": "" 
	 *                    		], 
	 *                    	"page": "1" 
	 *                    },
	 *                    	"status": 200,
	 *                    	"timestamp": 1576826568061 }
	 * @apiUse QueryError
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)", 
	 * 				json:{
	 *             			"rybh":"人员编号(最大字段长度：21)", 
	 *             		}
	 */
	@OpenAPI
	@ApiOperation("生物特征人脸查询")
	@GetMapping("/mfaceQuery")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	public ResponseMessage<String> mface_Query(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/biometric/mfaceQuery";
		String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 查询参数
			Manager_MfaceModel mfaceModel = CacheUtils.get().getMface(maps.getResult().get("zjh").toString());
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
				if (mfaceModel == null) {
					QueryParam queryParam = new QueryParam();
					queryParam.and("zjh", maps.getResult().get("zjh"));
					queryParam.and("jsbh", jsbh);
					DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
					ResponseMessage<PagerResult<Manager_MfaceModel>> res = managerApi.mface_query(queryParam);
					if (res != null && res.getResult() != null && res.getResult().getData() != null
							&& res.getResult().getData().size() > 0) {
						Manager_MfaceModel face = res.getResult().getData().get(0);
						CacheUtils.setCache(CacheUtils.CACHE_M_FACE + face.getZjh() + face.getJh() + face.getJsbh(),
								JSONUtil.toJson(face));
					}
					return ResponseMessage.ok("查询成功");
				} else {
					return ResponseMessage.error("查询失败");
				}
			} else {
				return ResponseMessage.error("证件号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("查询成功");
	}

	
	/**
	 * @api {post} /v4/kss/biometric/mfaceCJSave 民警人脸采集保存
	 * @apiVersion 0.4.0
	 * @apiName mfaceCJSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警人脸采集保存.
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss)",
	 *             				"creator":"创建人(必填;最大字段长度：30)",
	 *             				"jh":"警号(必填;最大字段长度：20))", 
	 *             				"jsbh":"监所编号(必填;最大字段长度：9)",
	 *             				"cjfs":"采集方式(必填;最大字段长度：1)", 
	 *             				"zjh":"证件号(必填;最大字段长度：21))",
	 *             				"rltx":"人脸图像(必填;)", 
	 *             				"rltxurl":"人脸图像地址(必填;最大字段长度：255)",
	 *             				"rltz":"人脸特征(必填;)", 
	 *             				"rltzurl":"人脸特征地址(必填;最大字段长度：255)", 
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警人脸采集保存")
	@PostMapping("mfaceCJSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mfaceCJ_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/biometric/mfaceCJSave";
		try {
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
			String ip = CacheUtils.get().getBiometricIP(this.getIp(request));
			Manager_MfaceModel mfaceModel = new Manager_MfaceModel();
			mfaceModel.setJsbh(jsbh);
			mfaceModel.setJh(maps.getResult().get("jh").toString());
			mfaceModel.setZjh(maps.getResult().get("zjh").toString());
			mfaceModel.setCreator(maps.getResult().get("creator").toString());
			mfaceModel.setCreatetime(Calendar.getInstance().getTime());
			mfaceModel.setState("R2");
			ResponseMessage<String> res = managerApi.mfaceCJ(mfaceModel, ip);
			if (res.getStatus() == 200) {
				res.setMessage("保存成功!");
			} else {
				res.setMessage("服务异常,保存失败!");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	
	/**
	 * @api {post} /v4/kss/biometric/mcardCJSave 民警胸卡采集保存
	 * @apiVersion 0.4.0
	 * @apiName mcardCJSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警胸卡采集保存.
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss)",
	 *             				"creator":"创建人(必填;最大字段长度：30)",
	 *             				"jh":"警号(必填;最大字段长度：20)", 
	 *             				"sfzh":"证件号(必填;最大字段长度：21)",
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警胸卡采集保存")
	@PostMapping("mcardCJSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mcardCJ_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/biometric/mcardCJSave";
		try {
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
			String ip = CacheUtils.get().getBiometricIP(this.getIp(request));
			MjjbxxModel mjjbxxModel = new MjjbxxModel();
			mjjbxxModel.setJsbh(jsbh);
			mjjbxxModel.setJh(maps.getResult().get("jh").toString());
			mjjbxxModel.setSfzh(maps.getResult().get("sfzh").toString());
			mjjbxxModel.setUpdator(maps.getResult().get("creator").toString());
			mjjbxxModel.setUpdatetime(Calendar.getInstance().getTime());
			ResponseMessage<String> res = managerApi.mcardCJ(mjjbxxModel, ip);
			if (res.getStatus() == 200) {
				res.setMessage("保存成功!");
			} else {
				res.setMessage("服务异常,保存失败!");
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
}
