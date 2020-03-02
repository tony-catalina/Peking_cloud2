/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.GzryglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_GzryglModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/gzrygl")
@Api(tags = "kss-gzrygl",description = "Gzrygl") 
public class Kss_GzryglController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/kss/gzrygl/gzryglQuery 其他工作人员查询
	 * @apiVersion 0.4.0
	 * @apiName gzryglQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 *
	 * @apiDescription 其他工作人员查询.
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 *
	 * @apiSuccess {String} zgxm         			      职工姓名
	 * @apiSuccess {String} xmpy         			      职工姓名拼音
	 * @apiSuccess {String} mz         			      民族(字典值)
	 * @apiSuccess {String} mzString         			  民族(转义值)
	 * @apiSuccess {String} csny         			      出生年月
	 * @apiSuccess {String} zy         			      职业(字典值)
	 * @apiSuccess {String} zyString         			  职业(转义值)
	 * @apiSuccess {String} zjlx         			      证件类型(字典值)
	 * @apiSuccess {String} zjlxString         		  证件类型(转义值)
	 * @apiSuccess {String} zjh         			      证件号
	 * @apiSuccess {String} zzmm         			      政治面貌(字典值)
	 * @apiSuccess {String} zzmmString         		  政治面貌(转义值)
	 * @apiSuccess {String} gw         			      岗位
	 * @apiSuccess {String} jtzz         			      家庭住址
	 * @apiSuccess {String} hjszd         			      户籍所在地(字典值)
	 * @apiSuccess {String} hjszdString         		  户籍所在地(转义值)
	 * @apiSuccess {String} lxdh         			      联系电话
	 * @apiSuccess {String} sjh         			      手机号
	 * @apiSuccess {String} xl         			      学历(字典值)
	 * @apiSuccess {String} xlString         			  学历(转义值)
	 * @apiSuccess {String} yszyzbh         			  医生执业证编号
	 * @apiSuccess {String} yszgzbh         			  医生资格证编号
	 * @apiSuccess {String} sfqzys         			  是否全职医生
	 * @apiSuccess {String} flag         			      是否在职(字典值)
	 * @apiSuccess {String} flagString        			  是否在职(转义值)
	 * @apiSuccess {String} bz         			      备注
	 * @apiSuccess {String} jsbh         			      监所编号(字典值)
	 * @apiSuccess {String} jsbhString        			  监所编号(转义值)
	 *
	 * @apiSuccess {String} page         				当前页数
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "bz": "1111121321gh",
	 *         "csny": "2000-06-05",
	 *         "flag": "0",
	 *         "flagString": "否",
	 *         "gw": "123",
	 *         "hjszd": "142200",
	 *         "hjszdString": "山西省忻州地区",
	 *         "jsbh": "110000114",
	 *         "jsbhString": "北京市第一看守所"
	 *         "jtzz": "1233321321"
	 *         "lxdh": "15951728367"
	 *         "mz": "49",
	 *         "mzString": "京",
	 *         "sfqzys": "",
	 *         "sfqzysString": ""
	 *         "sjh": "15951782934"
	 *         "xl": "50"
	 *         "xlString": "工学校",
	 *         "xmpy": "123",
	 *         "yszgzbh": "",
	 *         "yszyzbh": ""
	 *         "zgxm": "元始天尊"
	 *         "zjh": "513436200006129153"
	 *         "zjlx": "17",
	 *         "zjlxString": "护照",
	 *         "zy": "2",
	 *         "zyString": "医务人员(护士)"
	 *         "zzmm": "06"
	 *         "zzmmString": "中国民主建国会会员"
	 *       }
	 *      ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576496854065
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample Example usage:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:
	 *   {
	 *     "zy":"职业(最大长度:2；字典项：1：医务人员(医生)；2：医务人员(护士)；3：保安；4：职工(锅炉)；5：职工(电工)；6：职工(食堂)；7：其他)",
	 *     "zgxm":"职工姓名(最大长度：30)",
	 *     "flag":"是否在职（1：在职；0：离职） ",
	 *   }
	 *
	 */
	@OpenAPI
	@ApiOperation("其他工作人员查询")
	@GetMapping("/gzryglQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> gzrygl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		//接口id
		String interfaceId = "/v4/kss/gzrygl/gzryglQuery";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			JSONObject jsonObject = JSONObject.parseObject(json);
			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("zy"))) {
				param.and("zy", TermType.eq, maps.getResult().get("zy"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("zgxm"))) {
				param.and("zgxm", TermType.like, maps.getResult().get("zgxm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("flag"))) {
				param.and("flag", TermType.eq, maps.getResult().get("flag"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<GzryglModel>> result= kssServerApis.gzryglQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",  result.getResult().getTotal());
			maplist.put("page",  request.getParameter("page"));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			if(list.getStatus()==200) {
				list.setMessage("查询成功");
				if(list.getResult()==null) {
					list.setMessage("未查询数据");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	@ApiOperation("其他工作人员新增(修改)")
	@PostMapping("/gzryglSaveOrUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> gzrygl_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/gzrygl/gzryglSaveOrUpdate";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			System.err.println(map.get("entity").toString());
			List<GzryglModel> modelList = JSONArray.parseArray(map.get("entity").toString(), GzryglModel.class);
			System.err.println("model--"+ JSON.toJSONString(modelList.get(0)));
			GzryglModel gzryglModel = modelList.get(0);
			ResponseMessage<String> result;
			if (!StringUtils.isNullOrEmpty(gzryglModel.getId())){
				gzryglModel.setUpdatetime(new Date());
				gzryglModel.setJsbh(jsbh);
				result = ResponseMessage.ok(kssServerApis.gzryglUpdate(gzryglModel.getId(),gzryglModel));
			}else {
				gzryglModel.setCreatetime(new Date());
				gzryglModel.setState("R2");
				gzryglModel.setJsbh(jsbh);
				result = kssServerApis.gzryglSave(gzryglModel);
			}
			System.err.println("model--"+JSON.toJSONString(gzryglModel));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
}
