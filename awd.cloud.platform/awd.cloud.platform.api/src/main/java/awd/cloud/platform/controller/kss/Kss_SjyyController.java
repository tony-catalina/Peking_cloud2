/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_SjyyModel;
import awd.cloud.platform.model.kss.SjyyModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
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
@RequestMapping("/v4/kss/sjyy")
@Api(tags = "kss-sjyy",description = "Sjyy") 
public class Kss_SjyyController  extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;
	
	@Autowired
    private KssService kssService;


	/**
	 * @api {get} /v4/kss/sjyy/lsryList  离所人员查询
	 * @apiVersion 0.4.0
	 * @apiName lsryList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 离所人员查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}jbxxXm                                           姓名
	 * @apiSuccess {String}jbxxSnbh                                         所内编号
	 * @apiSuccess {String}jbxxRsrq                                         入所日期
     * @apiSuccess {String}jbxxjsh                                          监室号
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}id                                               ID
	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}total                                             返回总数
	 * @apiSuccess {String}data                                              返回数据
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "查询成功",
	 * "result": {
	 * "total": 1,
	 * "data":
	 * {
	 *        "jbxxXm": "别操作这个人",
     *         "jbxxJsh": "0101",
     *         "id": "11000011420190926000061",
     *         "jbxxSnbh": "20190167",
     *         "jbxxRsrq": "2019-09-24 16:17:17",
	 * }
	 *
	 * "page": "1"
	 * },
	 * "status": 200,
	 * "timestamp": 1576826568061
	 * }
	 * @apiUse QueryError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *       "bsbh":"B所编号(接收所编号)(最大字段长度：9)"
	 *      }
	 * @return
	 */
	@ApiOperation("离所人员查询")
	@GetMapping("/lsryList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> lsryList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

	     	String interfaceId ="/v4/kss/sjyy/lsryList";
			String state = "R2";

		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("bsbh"))) {
				param.and("bsbh", TermType.eq, maps.getResult().get("bsbh"));
			}
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq,jsbh);
			}

			DefaultQueryParam.addDefaultQueryParam(request,param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.sjyyQueryForPage(param);

            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {get} /v4/kss/sjyy/sjyyList 所间移押查询
     * @apiVersion 0.4.0
     * @apiName sjyyList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 所间移押查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}jbxxXm                                           姓名
     * @apiSuccess {String}jbxxSnbh                                         所内编号
     * @apiSuccess {String}jbxxRsrq                                         入所日期
     * @apiSuccess {String}jbxxjsh                                          监室号
     * @apiSuccess {String}asbh                                             A监所编号
     * @apiSuccess {String}asbhString                                       A监所编号(已转换)
     * @apiSuccess {String}id                                               ID
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data":
     * {
     *         "jbxxXm": "王靖",
     *         "jbxxJsh": "0814",
     *         "asbh": "110000112",
     *         "id": "11000011220191105000087",
     *         "jbxxSnbh": "20190001",
     *         "jbxxRsrq": "2019-10-26 11:05:56",
     *         "asbhString": "北京市第二看守所"
     * }
     *
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *       "bsbh":"B所编号(接收所编号)(最大字段长度：9)"
     *      }
     * @return
     */
    @ApiOperation("所间移押查询")
    @GetMapping("/sjyyList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> sjyyList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId ="/v4/kss/sjyy/sjyyList";
        String state = "R2";

        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("bsbh"))) {
                param.and("bsbh", TermType.eq, maps.getResult().get("bsbh"));
            }
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq,jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("asbh"))) {
                param.and("asbh", TermType.eq, maps.getResult().get("asbh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request,param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.sjyyQueryForPage(param);

            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }


    /**
     * @api {post} /v4/kss/sjyy/saveSjyys  所间移押保存
     * @apiVersion 0.4.0
     * @apiName saveSjyys
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 所间移押保存
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * 	"entity": [{
     *        "creator": "创建人(必填;最大字段长度：50)",
     * 	      "rybh": "人员编号(必填;最大字段长度：21)",
     * 	      "asbh":"A所编号(移押所编号)(必填;最大字段长度：9)",
     * 	      "bsbh":"B所编号(移押所编号)(必填;最大字段长度：9)",
     * 	      "ysyy":"移押原因"
     * 	      "asblr":"a所办理人(必填;最大字段长度：50)"
     * 	      "asblsj":"a所办理时间(必填;格式：yyyy-MM-dd HH:mm:ss)"
     * 	      "bz":"备注()"
     *        }]
     * }
     */

    //{"creator":".{1,50}","rybh":".{1,21}","asbh":"\\d{1,9}","bsbh":"\\d{1,9}","asblr":".{1,50}","asblsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("所间移押保存")
    @PostMapping("/saveSjyys")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveSjyys( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/sjyy/saveSjyys";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<SjyyModelDO> list = JSONArray.parseArray(map.get("entity").toString(), SjyyModelDO.class);
            SjyyModelDO sjyyEntity =list.get(0);
            // List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            // Map<String,Object> entityMap = mapsList.get(0);

                sjyyEntity.setState("R2");
                sjyyEntity.setYyzt("0");
                sjyyEntity.setCreatetime(new Date());
                sjyyEntity.setJsbh(jsbh);

                System.err.println("所间移押"+JSONUtil.toJson(sjyyEntity));

                ResponseMessage<String> result =   kssServerApis.sjyySaveSjyys(sjyyEntity);

                if (result.getStatus() == 200) {
                    result.setMessage("保存成功!");
                } else {
                    result.setMessage("服务异常,保存失败!");
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseMessage.error("保存失败！");
            }
        }




    /**
     * @api {post} /v4/kss/sjyy/startSjyy  开始所间移押
     * @apiVersion 0.4.0
     * @apiName startSjyy
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 开始所间移押
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * 	"entity": [{
     * 	      "rybhs": "人员编号s(必填;最大字段长度：21)",
     * 	      "username":"用户名(必填;最大字段长度：50)"
     *        }]
     * }
     */

    //{"usename":".{1,50}"}
    @ApiOperation("开始所间移押")
    @PostMapping("/startSjyy")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> startSjyy( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/sjyy/startSjyy";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);

            if(StringUtils.isNullOrEmpty(entityMap.get("rybhs").toString())) {
                return ResponseMessage.error("请输入人员编号");
            }
            String rybhs = entityMap.get("rybhs").toString();

            ResponseMessage<String> result = kssServerApis.startSjyy(rybhs, entityMap.get("username").toString());

            if (result.getStatus() == 200) {
                result.setMessage("移押成功!");
            } else {
                result.setMessage("移押失败,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("移押失败！");
        }
    }


    /**
     * @api {post} /v4/kss/sjyy/startJs 开始批量拒收
     * @apiVersion 0.4.0
     * @apiName startJs
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 开始批量拒收
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * 	"entity": [{
     * 	      "rybhs": "人员编号s(必填;最大字段长度：21)",
     * 	      "username":"用户名(必填;最大字段长度：50)"
     *        }]
     * }
     */

    //{"usename":".{1,50}"}
    @ApiOperation("开始批量拒收")
    @PostMapping("/startJs")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> startJs( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/sjyy/startJs";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);


            if(StringUtils.isNullOrEmpty(entityMap.get("rybhs").toString())) {
                return ResponseMessage.error("请输入人员编号");
            }
            String rybhs = entityMap.get("rybhs").toString();

            ResponseMessage<String> result = kssServerApis.startJs(rybhs, entityMap.get("username").toString());
            if (result.getStatus() == 200) {
                result.setMessage("拒收成功!");
            } else {
                result.setMessage("拒收失败,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("拒收失败！");
        }
    }


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> sjyy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_SjyyModel data) {
		return kssService.sjyy_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_SjyyModel> sjyy_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.sjyy_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> sjyy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.sjyy_deleteByKey(id);
	}
}
