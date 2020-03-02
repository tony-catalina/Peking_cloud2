package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.EmdjModel;
import awd.bj.kss.model.ShgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.ShgxsModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
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
@RequestMapping("/v4/kss/shgx")
@Api(tags = "kss-shgx",description = "Shgx")
public class Kss_ShgxController  extends PublicService {



    @Autowired
    private KssServerApis kssServerApis;



    /**
     * @api {post} /v4/kss/shgx/shgxList  社会关系查询（若有参数，就执行拼接参数查询）
     * @apiVersion 0.4.0
     * @apiName shgxList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription    社会关系查询（若有参数，就执行拼接参数查询）
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id          				                        id
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsbhString                                       监所编号(已转换)
     * @apiSuccess {String}jsxm                                             家属姓名
     * @apiSuccess {String}xb                                               性别(字典:XB)
     * @apiSuccess {String}xbString                                         性别(已转换)
     * @apiSuccess {String}nl                                               年龄
     * @apiSuccess {String}jszjh                                            家属证件号
     * @apiSuccess {String}gx                                               与在押人员关系(字典:GX)
     * @apiSuccess {String}gxString                                         与在押人员关系(已转换)
     * @apiSuccess {String}dwdh                                             单位电话
     * @apiSuccess {String}gzdw                                             工作单位
     * @apiSuccess {String}sj                                               手机号码
     * @apiSuccess {String}dh                                               联系电话
     * @apiSuccess {String}csrq                                             出生日期
     * @apiSuccess {String}mz                                               民族（字典:MZ）
     * @apiSuccess {String}mzString                                         民族(已转换)
     * @apiSuccess {String}zzdz                                             暂住地址
     * @apiSuccess {String}dz                                               联系地址
     * @apiSuccess {String}yb                                               邮编
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}state                                            删除状态(字典:YWSTATE)
     * @apiSuccess {String}stateString                                      删除状态(已转换)
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}zjlx                                             证件类型
     * @apiSuccess {String}zjlxString                                       证件类型(已转换)
     * @apiSuccess {String}zy                                               职业
     * @apiSuccess {String}zyString                                         职业(已转换)
     * @apiSuccess {String}sfswgx                                           是否涉维关系(字典:SHFO)
     *
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 245,
     *     "data": [
     *       {
     *         "csrq": null,
     *         "jszjh": "512501197203035172",
     *         "dh": "214124124124",
     *         "zjlx": null,
     *         "yb": null,
     *         "stateString": "有效",
     *         "mzString": null,
     *         "jsxm": "空调费",
     *         "dz": "啊实打实",
     *         "sj": null,
     *         "zjlxString": null,
     *         "bz": null,
     *         "id": "11000011420190812000112",
     *         "state": "R2",
     *         "jsbhString": "北京市第一看守所",
     *         "gxString": "祖（外祖）父母",
     *         "creator": "管理员",
     *         "createtime": "2019-08-12 18:10:24",
     *         "xb": "1",
     *         "zyString": null,
     *         "mz": null,
     *         "zzdz": null,
     *         "xbString": "男性",
     *         "gx": "160",
     *         "gzdw": null,
     *         "rybh": "110000111201907120012",
     *         "dwdh": null,
     *         "sfswgx": null,
     *         "updator": "管理员",
     *         "updatetime": "2019-08-13 17:22:51",
     *         "jsbh": "110000114",
     *         "nl": null,
     *         "zy": null
     *       },
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578640551496
     * }


     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * 	  json:{
     *             "rybh":"人员编号"
     *         }
     *
     *
     */

    @ApiOperation("社会关系查询")
    @PostMapping("/shgxList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> shgxList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/shgx/shgxList";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh",TermType.eq,maps.getResult().get("rybh").toString());
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.shgxQueryForPage(param);
            System.err.println("result" + JSON.toJSONString(result));
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
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }


    /**
     * @api {post} /v4/kss/shgx/shgxAdd  社会关系的保存操作
     * @apiVersion 0.4.0
     * @apiName shgxAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  社会关系的保存操作.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:json:{
     * 	"entity": [{
     * 	    "creator":"创建者"
     * 		"rybh": "人员所内编号(必填;最大字段长度:21)",
     * 			"jsxm": "家属姓名(必填:最大长度30)",
     * 			"xb": "性别(必填:字典:xb;最大长度1)",
     * 			"gx": "与在押人员关系(必填:最大长度3,字典:gx)",
     * 			"gzdw": "工作单位(必填:最大长度60)",
     * 			"zy": "职业(必填:最大长度50)",
     * 			"dz": "地址(必填:最大长度255)",
     * 			"dh": "联系电话(必填:最大长度40)",
     * 			"jszjh": "家属证件号(必填:最大长度18)"
     * 	},{
     *
     * 	}]
     * }
     *
     */
    @ApiOperation("社会关系的保存操作")
    @PostMapping("/shgxAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> shgxAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/shgx/shgxAdd";
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



            //封装数据
            List<ShgxModel> shgxModelsList = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class);
            List<ShgxsModel> shgxsModels = JSONArray.parseArray(map.get("entity").toString(), ShgxsModel.class);
            ShgxsModel shgxsModel = shgxsModels.get(0);
            for (ShgxModel shgxModel : shgxModelsList) {
                shgxModel.setJsbh(jsbh);
                shgxModel.setState("R2");
                shgxModel.setCreatetime(new Date());
            }
            shgxsModel.setShgxModels(shgxModelsList);
            shgxsModel.setJsbh(jsbh);

            System.err.println("shgxs="+JSON.toJSONString(shgxsModel));

            ResponseMessage<String> result = kssServerApis.shgxsSave(shgxsModel);
            System.err.println("--"+ JSON.toJSONString(result));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }




    /**
     * @api {post} /v4/kss/shgx/shgxYwtz  业务台账查询
     * @apiVersion 0.4.0
     * @apiName shgxYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription    业务台账查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}creatimeString                                   创建时间(已转换)
     * @apiSuccess {String}jszjh                                            家属证件号
     * @apiSuccess {String}jsxm                                             监所编号(已转换)
     * @apiSuccess {String}gx                                               与在押人员关系(字典:GX)
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}creatime                                         创建时间
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}state                                            删除状态(字典:YWSTATE)
     * @apiSuccess {String}gxString                                         与在押人员关系(已转换)
     * @apiSuccess  {String}jsh                                             监室号
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 232,
     *     "data": [
     *       {
     *         "creator": "创建人",
     *         "createtime": 1577425648000,
     *         "jszjh": "123123123123",
     *         "creatimeString": "2019-12-27 13:47:28",
     *         "jsxm": "家属姓名",
     *         "gx": "12",
     *         "xm": "结界",
     *         "rybh": "110000111201907120001",
     *         "creatime": 1577425648000,
     *         "id": "11000011420191227000422",
     *         "state": "R2",
     *         "gxString": " ",
     *         "jsh": "0101"
     *       },
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578640551496
     * }


     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * 	  json:{
     *             "rybh":"人员编号"
     *         }
     *
     *
     */

    @ApiOperation("业务台账查询")
    @PostMapping("/shgxYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> shgxYwtz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/shgx/shgxYwtz";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                param.and("createtime", TermType.gte, maps.getResult().get("blrqstart").toString()+ " 00:00:00");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                param.and("createtime", TermType.lte, maps.getResult().get("blrqend").toString()+ " 23:59:59");
            }

            String xm=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))){
                xm= maps.getResult().get("xm").toString();
            }
            String xm_type=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm_type"))){
                xm_type= maps.getResult().get("xm_type").toString();
            }
            if (!StringUtils.isNullOrEmpty(xm)) {
                if ("0".equals(xm_type)) {
                    xm = word2py(xm);
                    param.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
                } else {
                    param.and("jbxx_xm", TermType.like, "%" + xm + "%");
                }
            }

            String gx=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("gx"))){
                gx= maps.getResult().get("gx").toString();
            }
            String gx_type=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("gx"))){
                gx_type=maps.getResult().get("gx_type").toString();
            }

            if (!StringUtils.isNullOrEmpty(gx)) {
                if ("0".equals(gx_type)) {
                    param.and("gx", TermType.eq, gx);
                } else {
                    param.and("gx", TermType.not, gx);
                }
            }

            String jszjh=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jszjh"))){
                jszjh= maps.getResult().get("jszjh").toString();
            }
            String jszjh_type=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jszjh_type"))){
                jszjh_type= maps.getResult().get("jszjh_type").toString();
            }
            if (!StringUtils.isNullOrEmpty(jszjh)) {
                if ("0".equals(jszjh_type)) {
                    param.and("jszjh", TermType.like, "%"+jszjh+"%");
                } else {
                    param.and("jszjh", TermType.not, jszjh);
                }
            }

            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {

                param.and("jbxx_jsh", TermType.eq,maps.getResult().get("jsh").toString() );
            }
            param.and("state", TermType.eq, "R2");
            param.and("jbxx_state", TermType.eq, "R8");
            param.and("jsbh", TermType.eq, jsbh);
            param.and("jbxx_jsbh", TermType.eq,jsbh);


            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.shgxYwtz(param);
            System.err.println("result" + JSON.toJSONString(result));
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
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    public String word2py(String word) {
        if (StringUtils.isNullOrEmpty(word)) {
            return "";
        }
        return Pinyin4j.cn2Pinyin(word);

    }


}
