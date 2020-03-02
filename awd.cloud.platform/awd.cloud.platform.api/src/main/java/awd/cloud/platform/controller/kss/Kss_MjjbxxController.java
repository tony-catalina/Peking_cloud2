/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.MjjbxxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.UploadFileModel;
import awd.cloud.platform.service.AppMjjbxxService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.Result;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/mjjbxx")
@Api(tags = "kss-mjjbxx",description = "Mjjbxx") 
public class Kss_MjjbxxController extends PublicService{
	
	@Autowired
    private KssService kssService;
	
	@Autowired
    private KssServerApis kssServerApis;
	
	@Autowired
    private AppMjjbxxService appMjjbxxService;
	
	/**
	 * @return
	 * @api {get} /v4/kss/mjJbxx/mjjbxxQuery  民警基本信息查询
	 * @apiVersion 0.4.0
	 * @apiName mjjbxxQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警基本信息查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}ksscjry										            看守所参加人数
	 * @apiSuccess {String}qtry										                        其它人员
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态(已转换)
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}updator										            更新人
	 * @apiSuccess {String}ylqk												演练情况
	 * @apiSuccess {String}ylsj	 											演练时间
	 * @apiSuccess {String}zzzhz											组织指挥者
	 * @apiSuccess {String}hyjlr											会议记录人
	 * @apiSuccess {String}gjcs	 											改进措施
	 * @apiSuccess {String}czwt												存在的问题
	 * @apiSuccess {String}creator  								 		创建人
	 * @apiSuccess {String}createtime  										创建时间
	 * @apiSuccess {String}bz												备注						                  
	 * 
	 *
	 *
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
	 * "data":  [
	 *          "bm": "",
	 *			"bmzw": "",
	 *			"bmzwString": null,
	 *			"bz": "",
	 *			"cjgmgzsj": "2019-08-14 00:00:00",
	 *			"cjgzsj": "",
	 *			"createtime": "2019-05-17 11:08:09",
	 *			"creator": "管理员",
	 *			"csny": "2019-05-14",
	 *			"flag": "2",
	 *			"flagString": " ",
	 *			"gbzwjb": "",
	 *			"gbzwjbString": "",
	 *			"hjszd": "",
	 *			"hjszdString": "",
	 *			"hyzk": "",
	 *			"hyzkString": "",
	 *			"id": "31000011120190517000129",
	 *			"jh": "31231",
	 *			"jsbh": "110000114",
	 *			"jsbhString": "北京市第一看守所",
	 *			"jtzz": "",
	 *			"jx": "6",
	 *			"jxString": "一级警督",
	 *			"lkrq": "",
	 *			"lkyy": "",
	 *			"lxdh": "",
	 *			"mz": "01",
	 *			"mzString": "汉",
	 *			"sfzh": "232134532234342",
	 *			"sjh": "",
s	 *			"updatetime": "2019-09-06 16:32:05",
	 *			"updator": "管理员",
	 *			"xb": "2",
	 *			"xbString": "女性",
	 *			"xkid": "",
	 *			"xl": "",
	 *			"xlString": "",
	 *			"xlzxs": "1",
	 *			"xlzxsString": "是",
	 *			"xm": "十大大大",
	 *			"xmpy": "",
	 *			"zpurl": "",
	 *			"zzmm": "",
	 *			"zzmmString": ""
	 * ],
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
	 *    "rybh":"人员编号(最大字段长度：21)",
	 * }
	 */
	@OpenAPI
    @ApiOperation("民警基本信息查询")
    @GetMapping("/mjjbxxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> mjjbxx_Query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/mjJbxx/mjjbxxQuery";
        String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam qParam = new QueryParam();
            DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
            if (maps.getResult().get("jh") != null) {
    			qParam.and("jh", TermType.like, "%"+maps.getResult().get("jh")+"%");
    		}
            if (maps.getResult().get("xm") != null) {        	
            	qParam.and("xm", TermType.like, "%"+maps.getResult().get("xm")+"%");
    		}
    		if (maps.getResult().get("sfzh") != null) {
    			qParam.and("sfzh", TermType.eq, maps.getResult().get("sfzh"));
    		}
            if (maps.getResult().get("flag") != null) {
            	qParam.and("flag", TermType.eq, maps.getResult().get("flag"));
            }
            qParam.and("jsbh",TermType.eq ,jsbh);
            ResponseMessage<PagerResult<MjjbxxModel>> result = kssServerApis.mjjbxxQueryForPage(qParam);
          //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessage.error("查询失败！");
		}
	}

	
	/**
	 * @api {post} /v4/kss/mjJbxx/mjjbxxSave 民警基本信息保存
	 * @apiVersion 0.4.0
	 * @apiName mjjbxxSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警基本信息保存
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳

	 *
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *                      "id":"Id(最大字段长度：23)",
	 *                      "jh":"警号(必填; 最大字段长度：20)",
	 *                      "xm":"姓名 (必填; 最大字段长度：30)",
	 *                      "xb":"性别(必填，字典(xb),最大字段长度：1)",
	 *                      "sfzh":"身份证号(必填;最大字段长度：18)",
	 *                      "mz":"民族(必填;字典(mz),最大字段长度：2)",
	 *                      "jx":"警衔(必填;字典(jx),最大字段长度：2)",
	 *                      "csny":"出生年月(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "gbzwjb":"职位级别(必填;字典(zwjb),最大字段长度：2))",
	 *                      "lxdh":"联系电话(必填;最大字段长度：40))",
	 *                      "zzmm":"政治面貌(必填;字典(zzmm),最大字段长度：3))",
	 *                      "hyzk":"婚姻状况(必填;字典(hyzk),最大字段长度：3))",
	 *                      "xl":"学历(必填;字典(whcd),最大字段长度：2))",
	 *                      "hjszd":"户籍所在地(必填;字典(xqzh),最大字段长度：6))",
	 *                      "jtzz":"家庭住址(必填;最大字段长度：100))",
	 *                      "xlzxs":"是否有心理咨询资格(必填;字典(shfo),最大字段长度：1))",
	 *                      "sjh":"手机号(必填;最大字段长度：30))",
	 *                      "bm":"别名(必填;最大字段长度：30))",
	 *                      "bmzw":"职位(必填;最大字段长度：20))",
	 *                      "cjgzsj":"参加工作时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "cjgmgzsj":"参加革命时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "bz":"备注(必填;最大字段长度：500))",
	 *                      "mjzp":"照片名称(必填))",
	 *                      "creator":"创建热(必填;最大字段长度：30))",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("民警基本信息保存")
	@PostMapping("mjjbxxSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mjjbxx_Save(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/mjJbxx/mjjbxxSave";
        String state = "R2";
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
            
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
        	MultipartFile policePhoto = multiRequest.getFile("mjzp");
            
        	List<MjjbxxModel> mjjbxxModel = JSONArray.parseArray(map.get("entity").toString(), MjjbxxModel.class);
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date cjgmgzsj;
            Date cjgzsj;
            Date csny;
            try {
            	mjjbxxModel.get(0).setId(maps.getResult().get("id").toString());
            	mjjbxxModel.get(0).setJsbh(jsbh);
            	mjjbxxModel.get(0).setLxdh(maps.getResult().get("lxdh").toString());
            	mjjbxxModel.get(0).setBmzw(maps.getResult().get("bmzw").toString());
            	if(maps.getResult().get("cjgzsj").equals("")) {
            		mjjbxxModel.get(0).setCjgzsj(null);
            	}else {
            		cjgzsj = formatter.parse(maps.getResult().get("cjgzsj").toString());
            		mjjbxxModel.get(0).setCjgzsj(cjgzsj);
    			}
            	mjjbxxModel.get(0).setXb(maps.getResult().get("xb").toString());
            	mjjbxxModel.get(0).setBz(maps.getResult().get("bz").toString());
            	mjjbxxModel.get(0).setBm(maps.getResult().get("bm").toString());
            	if (maps.getResult().get("cjgmgzsj").equals("")) {
            		mjjbxxModel.get(0).setCjgmgzsj(null);
    			}else {
    				cjgmgzsj = formatter.parse(maps.getResult().get("cjgmgzsj").toString());
    				mjjbxxModel.get(0).setCjgmgzsj(cjgmgzsj);
    			}
            	mjjbxxModel.get(0).setXlzxs(maps.getResult().get("xlzxs").toString());
            	mjjbxxModel.get(0).setMz(maps.getResult().get("mz").toString());
            	mjjbxxModel.get(0).setJx(maps.getResult().get("jx").toString());
            	mjjbxxModel.get(0).setHjszd(maps.getResult().get("hjszd").toString());
            	mjjbxxModel.get(0).setSjh(maps.getResult().get("sjh").toString());
            	mjjbxxModel.get(0).setGbzwjb(maps.getResult().get("gbzwjb").toString());
            	mjjbxxModel.get(0).setHyzk(maps.getResult().get("hyzk").toString());
            	mjjbxxModel.get(0).setSfzh(maps.getResult().get("sfzh").toString() );
            	mjjbxxModel.get(0).setXl(maps.getResult().get("xl").toString());
            	mjjbxxModel.get(0).setXm(maps.getResult().get("xm").toString());
            	if(maps.getResult().get("csny").equals("")) {
            		mjjbxxModel.get(0).setCsny(null);
            	}else {
            		csny = formatter.parse(maps.getResult().get("csny").toString());
            		mjjbxxModel.get(0).setCsny(csny);
    			}
            	mjjbxxModel.get(0).setBz(maps.getResult().get("bz").toString());
            	mjjbxxModel.get(0).setZzmm(maps.getResult().get("zzmm").toString());
            	mjjbxxModel.get(0).setJtzz(maps.getResult().get("jtzz").toString());
            	mjjbxxModel.get(0).setJh(maps.getResult().get("jh").toString());
            	
                mjjbxxModel.get(0).setUpdatetime(new Date());
                mjjbxxModel.get(0).setUpdator(maps.getResult().get("creator").toString());
                
//                Map<String, Object> map = Maps.newHashMap();
//                map.put("MjjbxxEntity", mjjbxxModel);
//                map.put("pictureByteStr", policePhoto);
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (policePhoto != null) {
        		UploadFileModel upfile = new UploadFileModel();
        		String fileName = policePhoto.getOriginalFilename();
        		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        		upfile.setFileName(fileName);
        		upfile.setFile(policePhoto.getBytes());
        		upfile.setExt(ext);
        		String url = kssServerApis.uploadZpDfs(upfile);
        		
        		if (url == null) {
        			url = "";
        		}
        		mjjbxxModel.get(0).setZpurl(url);
        		Result<?> result=appMjjbxxService.saveMjxxAndPicture(mjjbxxModel.get(0), policePhoto);
        		if(result.getSuccess().equals("success")) {
        			return ResponseMessage.ok("保存成功");
        		}else {
        			return ResponseMessage.error("保存失败");
        		}
    		}else {
    			ResponseMessage<String> result=kssServerApis.mjjbxxSave(mjjbxxModel.get(0));
    			if(result.getStatus()==200) {
    				return ResponseMessage.ok("保存成功");
    			}else {
    			    return ResponseMessage.error("保存失败");
    			}
    		}
		} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败");
		}
	}
	
	
	/**
	 * @api {post} /v4/kss/mjJbxx/mjjbxxUpdate 民警基本信息修改
	 * @apiVersion 0.4.0
	 * @apiName mjjbxxUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警基本信息保存
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳

	 *
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *                      "id":"Id(必填;最大字段长度：23)",
	 *                      "jh":"警号(必填; 最大字段长度：20)",
	 *                      "xm":"姓名 (必填; 最大字段长度：30)",
	 *                      "xb":"性别(必填，字典(xb),最大字段长度：1)",
	 *                      "sfzh":"身份证号(必填;最大字段长度：18)",
	 *                      "mz":"民族(必填;字典(mz),最大字段长度：2)",
	 *                      "jx":"警衔(必填;字典(jx),最大字段长度：2)",
	 *                      "csny":"出生年月(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "gbzwjb":"职位级别(必填;字典(zwjb),最大字段长度：2))",
	 *                      "lxdh":"联系电话(必填;最大字段长度：40))",
	 *                      "zzmm":"政治面貌(必填;字典(zzmm),最大字段长度：3))",
	 *                      "hyzk":"婚姻状况(必填;字典(hyzk),最大字段长度：3))",
	 *                      "xl":"学历(必填;字典(whcd),最大字段长度：2))",
	 *                      "hjszd":"户籍所在地(必填;字典(xqzh),最大字段长度：6))",
	 *                      "jtzz":"家庭住址(必填;最大字段长度：100))",
	 *                      "xlzxs":"是否有心理咨询资格(必填;字典(shfo),最大字段长度：1))",
	 *                      "sjh":"手机号(必填;最大字段长度：30))",
	 *                      "bm":"别名(必填;最大字段长度：30))",
	 *                      "bmzw":"职位(必填;最大字段长度：20))",
	 *                      "cjgzsj":"参加工作时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "cjgmgzsj":"参加革命时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "bz":"备注(必填;最大字段长度：500))",
	 *                      "mjzp":"照片名称(必填))",
	 *                      "creator":"创建热(必填;最大字段长度：30))",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("民警基本信息修改")
	@PostMapping("mjjbxxUpdate")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mjjbxx_Update(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/mjJbxx/mjjbxxUpdate";
        String state = "R2";
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
            
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
        	MultipartFile policePhoto = multiRequest.getFile("mjzp");
            
        	List<MjjbxxModel> mjjbxxModel = JSONArray.parseArray(map.get("entity").toString(), MjjbxxModel.class);
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date cjgmgzsj;
            Date cjgzsj;
            Date csny;
            try {
            	mjjbxxModel.get(0).setId(maps.getResult().get("id").toString());
            	mjjbxxModel.get(0).setJsbh(jsbh);
            	mjjbxxModel.get(0).setLxdh(maps.getResult().get("lxdh").toString());
            	mjjbxxModel.get(0).setBmzw(maps.getResult().get("bmzw").toString());
            	if(maps.getResult().get("cjgzsj").equals("")) {
            		mjjbxxModel.get(0).setCjgzsj(null);
            	}else {
            		cjgzsj = formatter.parse(maps.getResult().get("cjgzsj").toString());
            		mjjbxxModel.get(0).setCjgzsj(cjgzsj);
    			}
            	mjjbxxModel.get(0).setXb(maps.getResult().get("xb").toString());
            	mjjbxxModel.get(0).setBz(maps.getResult().get("bz").toString());
            	mjjbxxModel.get(0).setBm(maps.getResult().get("bm").toString());
            	if (maps.getResult().get("cjgmgzsj").equals("")) {
            		mjjbxxModel.get(0).setCjgmgzsj(null);
    			}else {
    				cjgmgzsj = formatter.parse(maps.getResult().get("cjgmgzsj").toString());
    				mjjbxxModel.get(0).setCjgmgzsj(cjgmgzsj);
    			}
            	mjjbxxModel.get(0).setXlzxs(maps.getResult().get("xlzxs").toString());
            	mjjbxxModel.get(0).setMz(maps.getResult().get("mz").toString());
            	mjjbxxModel.get(0).setJx(maps.getResult().get("jx").toString());
            	mjjbxxModel.get(0).setHjszd(maps.getResult().get("hjszd").toString());
            	mjjbxxModel.get(0).setSjh(maps.getResult().get("sjh").toString());
            	mjjbxxModel.get(0).setGbzwjb(maps.getResult().get("gbzwjb").toString());
            	mjjbxxModel.get(0).setHyzk(maps.getResult().get("hyzk").toString());
            	mjjbxxModel.get(0).setSfzh(maps.getResult().get("sfzh").toString() );
            	mjjbxxModel.get(0).setXl(maps.getResult().get("xl").toString());
            	mjjbxxModel.get(0).setXm(maps.getResult().get("xm").toString());
            	if(maps.getResult().get("csny").equals("")) {
            		mjjbxxModel.get(0).setCsny(null);
            	}else {
            		csny = formatter.parse(maps.getResult().get("csny").toString());
            		mjjbxxModel.get(0).setCsny(csny);
    			}
            	mjjbxxModel.get(0).setBz(maps.getResult().get("bz").toString());
            	mjjbxxModel.get(0).setZzmm(maps.getResult().get("zzmm").toString());
            	mjjbxxModel.get(0).setJtzz(maps.getResult().get("jtzz").toString());
            	mjjbxxModel.get(0).setJh(maps.getResult().get("jh").toString());
            	
                mjjbxxModel.get(0).setUpdatetime(new Date());
                mjjbxxModel.get(0).setUpdator(maps.getResult().get("creator").toString());
                
//                Map<String, Object> map = Maps.newHashMap();
//                map.put("MjjbxxEntity", mjjbxxModel);
//                map.put("pictureByteStr", policePhoto);
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (policePhoto != null) {
        		UploadFileModel upfile = new UploadFileModel();
        		String fileName = policePhoto.getOriginalFilename();
        		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        		upfile.setFileName(fileName);
        		upfile.setFile(policePhoto.getBytes());
        		upfile.setExt(ext);
        		String url = kssServerApis.uploadZpDfs(upfile);
        		
        		if (url == null) {
        			url = "";
        		}
        		mjjbxxModel.get(0).setZpurl(url);
        		Result<?> result=appMjjbxxService.updateMjxxAndPicture(mjjbxxModel.get(0), policePhoto);
        		if(result.getSuccess().equals("success")) {
        			return ResponseMessage.ok("修改成功");
        		}else {
        			return ResponseMessage.error("修改失败");
        		}
    		}else {
    			ResponseMessage<String> result=kssServerApis.mjjbxxUpdate(mjjbxxModel.get(0).getId(),mjjbxxModel.get(0));
    			if(result.getStatus()==200) {
    				return ResponseMessage.ok("修改成功");
    			}else {
    			    return ResponseMessage.error("修改失败");
    			}
    		}
		} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("修改失败");
		}
	}
	
	/**
	 * @api {post} /v4/kss/mjJbxx/mjjbxxGzztUpdate 办理民警调离
	 * @apiVersion 0.4.0
	 * @apiName mjjbxxGzztUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 办理民警调离.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳

	 *
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *                      "id":"id(必填;最大字段长度：23)",
	 *                      "updator":"更新人(必填;最大字段长度：30)",
	 *                      "updatetime":"更新时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("办理民警调离")
	@PostMapping("mjjbxxGzztUpdate")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mjjbxxGzzt_Update(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/mjJbxx/mjjbxxGzztUpdate";
        String state = "R2";
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
            List<MjjbxxModel> mjjbxxModel = JSONArray.parseArray(map.get("entity").toString(), MjjbxxModel.class);
            mjjbxxModel.get(0).setUpdatetime(new Date());
            mjjbxxModel.get(0).setUpdator(maps.getResult().get("updator").toString());
            mjjbxxModel.get(0).setFlag("2");
            mjjbxxModel.get(0).setJsbh(jsbh);
            ResponseMessage<String> result=kssServerApis.mjjbxxUpdate(mjjbxxModel.get(0).getId(), mjjbxxModel.get(0));
            if(result.getStatus() == 200){
            	result.setMessage("保存成功!");
            }else{
            	result.setMessage("服务异常,保存失败!");
            }
            return result;
        }catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败!");
		}
	}
}
