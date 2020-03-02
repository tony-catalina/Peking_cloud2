package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JshjModel;
import awd.bj.kss.model.ShgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.CrjjcModelDO;
import awd.cloud.platform.model.kss.WpjsModelDO;
import awd.cloud.platform.model.kss.XjjsModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/sqsw")
@Api(tags = "kss-sqsw", description = "Sqsw")
public class Kss_SqswController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/sqsw/sqswList   送钱送物基本信息查询
     * @apiVersion 0.4.0
     * @apiName sqswList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription   送钱送物基本信息查询
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}rsrqString          				                入所日期(已转换)
     * @apiSuccess {String}jszjh                                            家属证件号
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}rsrq                                             入所日期
     * @apiSuccess {String}jsje                                             接收的金额
     * @apiSuccess {String}dz                                               联系地址
     * @apiSuccess {String}blrqString                                       办理日期(已转换)
     * @apiSuccess {String}blrq                                             办理日期
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}dah                                              档案编号
     * @apiSuccess {String}jsh                                              监视号
     * @apiSuccess {String}jsxm                                             家属姓名


     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 10,
     *     "data": [
     *       {
     *         "rsrqString": "2019-10-10 10:10:10",
     *         "jszjh": "123213213213123123",
     *         "xm": "汪枫桦",
     *         "rsrq": 1570673410000,
     *         "jsje": 123,
     *         "dz": "12321",
     *         "blrqString": "2019-11-26 00:00:00",
     *         "blrq": 1574697600000,
     *         "rybh": "310000111201906200004",
     *         "dah": "123123",
     *         "jsh": "0814",
     *         "jsxm": "123"
     *       },
     *
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * json:{
     *      "jbxx_jsh":"姓名"
     *      "jbxx_xm":"监室号"
     *      "Ywtz_blrqStart":"业务台账办理日期开始时间"
     *      "Ywtz_blrqEnd":"业务台账办理日期结束时间"
     *      "pageIndex": "当前页 不传默认为1"
     *      "pageSize": "展示数据条数,不传为10"
     * }
     */

    @ApiOperation("送钱送物基本信息查询")
    @GetMapping("/sqswList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> sqswList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/sqsw/sqswList";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();

            param.and("jsbh", TermType.eq, jsbh);


            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            Map<String,Object> map=new HashMap<String,Object>();
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                map.put("jbxx_jsh",maps.getResult().get("jsh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                map.put("jbxx_jsh",maps.getResult().get("xm"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("Ywtz_blrqStart"))) {
                map.put("sqswrq_start",maps.getResult().get("Ywtz_blrqStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("Ywtz_blrqEnd"))) {
                map.put("sqswrq_end",maps.getResult().get("Ywtz_blrqEnd"));
            }

//            当前页和展示的页数
              map.put("pageIndex",1);
              map.put("pageSize",10);
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("pageIndex"))) {
                map.put("pageIndex",maps.getResult().get("pageIndex"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("pageSize"))) {
                map.put("pageSize",maps.getResult().get("pageSize"));
            }
            map.put("jbxx_state","R8");
            map.put("shgx_state","R2");
            map.put("xjjs_state","R2");
            map.put("jbxx_jsbh",jsbh);
            map.put("shgx_jsbh",jsbh);
            map.put("xjjs_jsbh",jsbh);


            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.sqswList(map);
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
     * @api {post} /v4/kss/sqsw/sqswAdd  送钱送物保存
     * @apiVersion 0.4.0
     * @apiName sqswAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription   送钱送物保存
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
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
     * 		"srlx": "接收类型 (必填: 最大字段长度1; 1: 随身携带， 2: 接济: 字典SRLX)",
     * 		"jsxm": "家属姓名 (必填;最大字段长度：30)",
     * 		"xb": "性别(必填;最大字段长度：1;字典:XB)",
     * 		"mz": "民族 (必填；最大字段长度：2;字典:MZ)",
     * 		"jscsrq": "家属出生日期 (必填；日期)",
     * 		"zjlx": "证件类型 (必填；字典：ZJLX；最大字段长度：2)",
     * 		"gx": "与在押人员关系(必填；字典：GX；最大字段长度：3)",
     * 		"jszjh": "家属证件号 (必填；最大字段长度：18)",
     * 		"dz": "联系地址 (最大字段长度：100)",
     * 		"dh": "联系电话 (必填；最大字段长度：100)",
     * 		"zzdz": "暂住地址 (必填；最大字段长度：255)",
     * 		"jsje": "接收的金额(必填；最大字段长度：10)",
     * 		"gzdw": "工作单位(必填；最大字段长度：60)",
     * 		"sfswgx": "是否涉维关系(必填；最大字段长度：1;字典:SHFO)",
     * 		"wpmc": "物品名称(必填；最大字段长度：200) 多个物品用逗号隔开",
     * 		"bz": "备注(必填；最大字段长度：200)",
     * 		"rybh": "人员编号(最大字段长度21)"，
     * 		"creator": "创建人(最大字段长度：50)"
     * 	} ]
     * }
     *
     *
     */
    //{"processid":".{1,30}","ywlcid":"\\d{1,21}","ly":".{1,100}","creator":".{1,50}","rybh":"\\d{1,21}","zjlx":"\\d{1,1}","xb":"\\d{1,1}","jsxm":".{1,30}","gx":"\\d{1,3}","dh":".{1,40}","sj":"\\d{1,11}","dwdh":"\\d{1,11}","gzdw":".{1,60}","zy":".{1,50}","dz":".{1,100}","zzdz":".{1,255}","sfswgx":"\\d{1,1}","jszjh":".{1,100}"}
    @ApiOperation("送钱送物保存")
    @PostMapping("/sqswAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jshj_save( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/sqsw/sqswAdd";

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
//            封装数据
            ShgxModel shgxModel = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class).get(0);
            XjjsModelDO xjjsModelDO = JSONArray.parseArray(map.get("entity").toString(), XjjsModelDO.class).get(0);
            WpjsModelDO wpjsModelDO = JSONArray.parseArray(map.get("entity").toString(), WpjsModelDO.class).get(0);
            Map map1 = JSONArray.parseArray(map.get("entity").toString(), Map.class).get(0);
            if (!StringUtils.isNullOrEmpty(map1.get("jscsrq"))){
                try {
                    shgxModel.setCsrq(new SimpleDateFormat("yyyy-MM-dd").parse( map1.get("jscsrq").toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if("11".equals(map1.get("zjlx").toString())){
                Integer nl = this.idCardToAge(map1.get("jszjh").toString());
                shgxModel.setNl(String.valueOf(nl));
            }
            shgxModel.setJsbh(jsbh);
            shgxModel.setCreator(map1.get("creator").toString());
            shgxModel.setCreatetime(new Date());

            xjjsModelDO.setBlrq(new Date());
            xjjsModelDO.setJsje(new BigDecimal(map1.get("jsje").toString()));
            xjjsModelDO.setCreator(map1.get("creator").toString());
            xjjsModelDO.setCreatetime(new Date());
            xjjsModelDO.setJsbh(jsbh);
            xjjsModelDO.setBlr(map1.get("creator").toString());
            xjjsModelDO.setState("R2");

            String wpmc = map1.get("wpmc").toString();
            String jswpmc ="";
            if(wpmc!=null) {
              jswpmc=wpmc;
            }
            wpjsModelDO.setJswpmc(jswpmc);
            wpjsModelDO.setJswpmc(jswpmc);
            wpjsModelDO.setDjr(map1.get("creator").toString());
            wpjsModelDO.setDjsj(new Date());
            wpjsModelDO.setJsbh(jsbh);
            wpjsModelDO.setCreator(map1.get("creator").toString());
            wpjsModelDO.setCreatetime(new Date());
            Map<String , Object> map2 = new HashMap<>();
            map2.put("shgx", shgxModel);
            map2.put("xjjs", xjjsModelDO);
            map2.put("wpjs", wpjsModelDO);
            System.err.println(JSONUtil.toJson(map2));

            ResponseMessage<String>  result = kssServerApis.addSqsw(map2);

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


    public static Integer idCardToAge(String idcard) {
        Integer selectYear = Integer.valueOf(idcard.substring(6, 10));         //出生的年份
        Integer selectMonth = Integer.valueOf(idcard.substring(10, 12));       //出生的月份
        Integer selectDay = Integer.valueOf(idcard.substring(12, 14));         //出生的日期
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
        return age;
    }







}
