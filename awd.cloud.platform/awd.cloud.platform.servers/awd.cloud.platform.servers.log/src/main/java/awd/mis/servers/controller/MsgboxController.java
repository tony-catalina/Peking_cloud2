/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.MsgboxEntity;
import awd.mis.servers.model.MsgboxModel;
import awd.mis.servers.service.MsgboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RefreshScope
@RequestMapping("/msgbox")
@AccessLogger("消息表")
@Api(tags = "awd-message-manager", description = "提供基本消息管理功能")
public class MsgboxController implements GenericEntityController<MsgboxEntity, String, QueryParamEntity, MsgboxModel> {

    private MsgboxService msgboxService;


    @Override
    public MsgboxEntity modelToEntity(MsgboxModel model, MsgboxEntity entity) {
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    @Autowired
    public void setMsgboxService(MsgboxService msgboxService) {
        this.msgboxService = msgboxService;
    }

    @Override
    public CrudService<MsgboxEntity, String> getService() {
        return msgboxService;
    }

    @Override
    @OpenAPI
    @ApiOperation("获取消息")
    public ResponseMessage<PagerResult<MsgboxEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return GenericEntityController.super.list(arg0, arg1);
    }

    @Override
    @ApiOperation("根据ID修改消息")
    public ResponseMessage updateByPrimaryKey(@PathVariable(value = "id") String id, @RequestBody MsgboxModel data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @OpenAPI
    @ApiOperation("根据jh修改所有消息")
    @RequestMapping(value = "/updateAllMsgByJh",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseMessage<String> updateAllMsgByJh(@RequestParam(value = "jh")String jh){
             try{
                 msgboxService.updateAllMsgByJh(jh);
             }catch (Exception e){
                 e.printStackTrace();
                 return ResponseMessage.error("消息更新失败");
             }
        return ResponseMessage.ok("保存成功");
    }
    @Override
    @ApiOperation("消息保存")
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody MsgboxModel data) {
        return GenericEntityController.super.save(data);
    }

	@Override
	public ResponseMessage<String> saveOrUpdate(@RequestBody MsgboxModel data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	public ResponseMessage<MsgboxEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}




}
