/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import awd.framework.common.entity.Entity;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.entity.*;
import awd.mis.servers.model.UserInfoOther;
import awd.mis.servers.service.imp.SimpleMfaceService;
import awd.mis.servers.service.imp.SimpleMfingerService;
import awd.mis.servers.service.imp.SimpleMirisService;
import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.model.Userinfo;
import awd.mis.servers.service.UserinfoService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/userinfo")
@AccessLogger("用户信息")
@Api(tags = "awd-user", description = "提供用户信息管理功能")
public class UserinfoController implements GenericEntityController<UserinfoEntity, String, QueryParamEntity, UserinfoEntity> {

    private UserinfoService userinfoService;

    @Autowired
    private SimpleMirisService simpleMirisService;

    @Autowired
    private SimpleMfingerService simpleMfingerService;

    @Autowired
    private SimpleMfaceService simpleMfaceService;

    @Override
    public UserinfoEntity modelToEntity(UserinfoEntity model, UserinfoEntity entity) {
        return model;
    }

    @Autowired
    public void setUserinfoService(UserinfoService userinfoService) {
        this.userinfoService = userinfoService;
    }

    @Override
    public CrudService<UserinfoEntity, String> getService() {
        return userinfoService;
    }

    @Override
    @RequestMapping(method = {RequestMethod.GET})
    @ApiOperation("应用自定义查询")
    @AccessLogger("{dynamic_query}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<PagerResult<UserinfoEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
        return GenericEntityController.super.list(reqest, param);
    }

    @PostMapping("/findByNameAndPass")
    @ApiOperation("根据用户名密码获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("findByNameAndPass")
    @HystrixCommand
    @OpenAPI
    ResponseMessage<UserinfoEntity> findByNameAndPass(@RequestParam(value = "jsbh") String jsbh, @RequestParam(value = "loginname") String name, @RequestParam(value = "password") String pwd) {
        UserinfoEntity userinfo = userinfoService.findByNameAndPass(jsbh, name, pwd);
        return ResponseMessage.ok(userinfo);
    }

    @PostMapping("/saveUserInfo")
    @AccessLogger("saveUserInfo")
    @ApiOperation("新增一条监所用户信息")
    @ApiResponses({@ApiResponse(code = 201, message = "创建成功,返回创建数据的ID"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @HystrixCommand
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody Userinfo user) {
    	String result = userinfoService.save(user);
    	if(result.equals("保存成功！")) {
    		return ResponseMessage.ok(result);
    	}else {
    		return ResponseMessage.error(result);
    	}
    }


    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    @ApiOperation("根据ID删除用户")
    @AccessLogger("{delete_by_primary_key}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }




    @Override
    @PutMapping(path = {"/{id:.+}"})
    @ApiOperation("根据ID更新应用")
    @AccessLogger("{update_by_primary_key}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody UserinfoEntity userinfoEntity) {
        return ResponseMessage.ok(userinfoService.updateByPk(id, userinfoEntity));
    }

    @Override
    @PostMapping("/saveUser")
    @ApiOperation("新增一条普通用户信息")
    @AccessLogger("{action_add}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody UserinfoEntity data) {
        return GenericEntityController.super.save(data);
    }

    @GetMapping("/getUserByJsbhRole")
    @ApiOperation("根据用户角色和监所编号获取用户")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<List<UserinfoEntity>> getUserByJsbhRole(@RequestParam("jsbh") String jsbh, @RequestParam("role") String role) {
        return ResponseMessage.ok(userinfoService.getUserByJsbhRole(jsbh, role));
    }

	@Override
	@OpenAPI
	public ResponseMessage<UserinfoEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UserinfoEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
    
	@GetMapping("/getUserByJsbhsRoles")
    @ApiOperation("根据用户角色和监所编号获取用户")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<List<UserinfoEntity>> getUserByJsbhsRoles(@RequestParam("jsbhs") String jsbhs, @RequestParam("roles") String roles) {
        return ResponseMessage.ok(userinfoService.getUserByJsbhsRoles(jsbhs, roles));
    }
	
	
	@GetMapping("/getUsersToDocument")
    @ApiOperation("根据用户角色和监所编号获取用户")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<PagerResult<UserinfoEntity>> getUsersToDocument(@RequestParam("jsbhs") String jsbhs, @RequestParam("usertype") String usertype) {
		QueryParamEntity queryParam = new QueryParamEntity();
		queryParam.setPaging(false);
        queryParam.and("state", "R2");
        queryParam.and("usertype", TermType.nin, usertype);
        queryParam.and("jsbh", TermType.in, jsbhs);
		return ResponseMessage.ok(userinfoService.selectPager(queryParam));
    }

    @GetMapping("/getUserByJsbh")
    @ApiOperation("根据监所编号获取用户名")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<List<Map<String,String>>> getUserByJsbh(@RequestParam("jsbh") String jsbh) {
        return ResponseMessage.ok(userinfoService.getUserByJsbh(jsbh));
    }

    @GetMapping("/getUserByJsbhAndName")
    @ApiOperation("根据监所编号和登录用户名获取用户信息")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    public ResponseMessage<Map<String,Object>> getUserByJsbhAndName(@RequestParam("jsbh") String jsbh,@RequestParam("loginname") String loginname) {
        try {
            return userinfoService.getUserByJsbhAndName(jsbh,loginname);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/mjjbxxQuery")
    @ApiOperation("根据监所编号和登录用户名获取用户信息")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    public ResponseMessage<List<Map<String,Object>>> mjjbxxQuery(@RequestParam("jsbh") String jsbh,@RequestParam("sfzh") String sfzh) {
        try {
            ResponseMessage<List<Map<String,Object>>> maps = userinfoService.mjjbxxQuery(jsbh,sfzh);
            QueryParamEntity queryParamEntity = new QueryParamEntity();
            queryParamEntity.and("zjh", awd.framework.common.core.param.TermType.in,sfzh);
            queryParamEntity.and("jsbh", awd.framework.common.core.param.TermType.eq,jsbh);
            queryParamEntity.and("state", awd.framework.common.core.param.TermType.eq,"R2");
            List<MirisEntity> mirisEntitys = simpleMirisService.select(queryParamEntity);
            List<MfaceEntity> mfaceEntitys = simpleMfaceService.select(queryParamEntity);
            List<MfingerEntity> mfingerEntitys = simpleMfingerService.select(queryParamEntity);
            for(Map<String,Object> map : maps.getResult()){
                List<MirisEntity> mirisEntitiess = new ArrayList<>();
                for (MirisEntity mirisEntity : mirisEntitys){
                    if (map.get("sfzh").toString().equals(mirisEntity.getZjh())){
                        mirisEntitiess.add(mirisEntity);
                    }
                    mirisEntitiess.stream().forEach(miris -> {
                        for(int i=0;i<7;i++){
                            if (Integer.toString(i).equals(miris.getHmwzbh())){
                                map.put("lris_url"+i,miris.getHmtxurl());
                            }
                        }
                    });
                }
                for (MfaceEntity mfaceEntity : mfaceEntitys){
                    if (map.get("sfzh").toString().equals(mfaceEntity.getZjh())){
                        map.put("face_url",mfaceEntity.getRltxurl());
                    }
                }
                for (MfingerEntity mfingerEntity : mfingerEntitys){
                    if (map.get("sfzh").toString().equals(mfingerEntity.getZjh())){
                        map.put("fingerprint",mfingerEntity.getZwtxurl());
                    }
                }
            }
            return maps;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }
    @OpenAPI
    @GetMapping("/getUserRole")
    public PagerResult<UserInfoOther> getUserRole(QueryParamEntity queryEntity) {
        return userinfoService.getUserRole(queryEntity);
    }
}
