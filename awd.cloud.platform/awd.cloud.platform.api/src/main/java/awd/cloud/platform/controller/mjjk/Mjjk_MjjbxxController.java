package awd.cloud.platform.controller.mjjk;

import awd.bj.kss.model.MjjbxxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_UserInfoOther;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：张延
 * Date：2019-12-20 13:10
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/mjjk/mjjbxx")
@Api(tags = "mjjk-mjjbxx",description = "mjjbxx")
public class Mjjk_MjjbxxController extends PublicService {

    Logger logger = LoggerFactory.getLogger(Mjjk_MjjbxxController.class);

    @Autowired
    private ManagerService managerService;



    /**
     * @api {get} /v4/mjjk/mjjbxx/mjxxList 民警信息查询
     * @apiVersion 0.4.0
     * @apiName mjxxList
     * @apiGroup g_mjjk
     * @apiPermission any
     * @apiDescription 民警信息查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json												json
     *
     *
     * @apiSuccess {String}xm         				                         民警姓名
     * @apiSuccess {String}jh         				                         民警警号
     * @apiSuccess {String}cardID         				                     门卡ID
     * @apiSuccess {String}loginname                                         登录名
     * @apiSuccess {String}email                                             邮箱
     * @apiSuccess {String}iris_url1                                         左眼大瞳孔虹膜url
     * @apiSuccess {String}iris_url2                                         左眼小瞳孔虹膜url
     * @apiSuccess {String}iris_url4                                         右眼大瞳孔虹膜url
     * @apiSuccess {String}iris_url5                                         右眼小瞳孔虹膜url
     * @apiSuccess {String}face_url                                          人脸url
     * @apiSuccess {String}fingerprint                                       指纹url
     * @apiSuccess {String}role_id                                           角色id
     * @apiSuccess {String}id                                                用户信息id
     * @apiSuccess {String}role_name                                         角色名称
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功!",
     *   "result": {
     *     "total": 4,
     *     "data": [
     *       {
     *         "loginname": "哈哈哈哈",
     *         "iris_url4": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4duNaAG6XQAAS0NiMiZ6U939.jpg",
     *         "iris_url5": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4duNmAPcrWAAS0Ni-Qgic058.jpg",
     *         "face_url": null,
     *         "role_name": "医生",
     *         "xm": "北京二看管理员",
     *         "role_id": "14",
     *         "cardID": "9",
     *         "fingerprint": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4atBSAc1mQAAEqx_iW-04955.jpg,http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4atBSAc1mQAAEqx_iW-04955.jpg,http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4atBSAc1mQAAEqx_iW-04955.jpg",
     *         "iris_url1": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4duM-ADCLrAAS0NkoWlng570.jpg",
     *         "id": "00000000201710250000091",
     *         "iris_url2": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4duNOAHOzBAAS0Nr8GALE670.jpg",
     *         "jh": "12314",
     *         "email": null
     *       },
     *       {
     *         "loginname": "管理员",
     *         "iris_url4": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4OtDGARf3OAAS0NnMZA2U807.jpg",
     *         "iris_url5": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4O7VyARYwxAAS0NirX_xI686.jpg",
     *         "face_url": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4O6h6APU7QAAHuAt5lH-o481.jpg",
     *         "role_name": "监管",
     *         "xm": "管理员真名",
     *         "role_id": "01",
     *         "cardID": "8",
     *         "fingerprint": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4atBSAc1mQAAEqx_iW-04955.jpg,http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4OtKiANDakAAFJXXVgDOE634.jpg",
     *         "iris_url1": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4OtC6AflgcAAS0Nr-gvs8400.jpg",
     *         "id": "00000000201710250000081",
     *         "iris_url2": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4OtDCAZ1-BAAS0NhtrNIw528.jpg",
     *         "jh": "888",
     *         "email": "123@qq.com"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578362754018
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *     "json"  :{
     *          "id":"用户信息id（是否必填：否）",
     *          "jh":"民警警号(是否必填:否)",
     *          "cardID":"门卡id(是否必填:否)",
     *          "role_id":"角色id(是否必填:否)",
     *          "role_name":"角色名(是否必填:否)",
     *          "page":"当前页（是否必填：否）",
     *          "pageSize":"一页数据量（是否必填：否）"
     *                    }
     */
    @OpenAPI
    @ApiOperation("民警信息查询")
    @GetMapping("/mjxxList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> mjxxList(HttpServletRequest request,
                                           @RequestParam(name = "appcode")String appcode,
                                            @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "json")String json) {

        String interfaceId = "/v4/mjjk/mjjbxx/mjxxList";
        QueryParam queryParam = new QueryParam();
        ResponseMessage<Map<String, Object>> list = new ResponseMessage<>();
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            queryParam.and("jsbh",TermType.eq,jsbh);
            queryParam.and("state",TermType.eq,"R2");
            String page = "";
            String pageSize = "";
            Map<String, Object> jsonMap = maps.getResult();
            for (String s : jsonMap.keySet()) {
                if ("role_name".equals(s)){
                    boolean flag = true;
                    for (JSONObject jsonObject : CacheUtils.get().getRoleList()){
                        if (jsonMap.get("role_name").equals(jsonObject.getString("name"))){
                            queryParam.and("role_id", TermType.like,"%"+jsonObject.get("code")+"%");
                            flag = false;
                        }
                    }
                    System.err.println("------------------------"+CacheUtils.get().getRoleList().size());
                    if (flag){
                        logger.warn("role_name值不正确,查询已退回:{}",jsonMap.get("role_name"));
                        return ResponseMessage.error("role_name值不正确，请重新输入!");
                    }
                }else if("page".equals(s)){
                    page = jsonMap.get(s).toString();
                    queryParam.setPageIndex(Integer.parseInt(jsonMap.get(s).toString())-1);
                }else if("pageSize".equals(s)){
                    queryParam.setPageSize(Integer.parseInt(jsonMap.get(s).toString()));
                }else{
                    queryParam.and(s, TermType.like,"%"+jsonMap.get(s)+"%");
                }
            }

            PagerResult<Manager_UserInfoOther> mjxx = managerService.mjxxQuery(queryParam);
            logger.info("mjxx.result:{}",JSONObject.toJSONString(mjxx));
            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            if (mjxx.getTotal()>0){
                maplist.put("entity", mjxx.getData());
                maplist.put("total", mjxx.getTotal());
                System.err.println("total-------------"+mjxx.getTotal());
            }else{
                maplist.put("entity", null);
                maplist.put("total", 0);
            }

            maplist.put("interfaceId", interfaceId);
            maplist.put("page", page == ""?"0":Integer.parseInt(page)-1);
            list = this.kfzdShow(maplist);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }
}
