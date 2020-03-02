/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.cloud.suppers.finger.model.FingerModel;
import awd.cloud.suppers.finger.service.BzwtzmService;
import awd.cloud.suppers.finger.service.CacheService;
import awd.cloud.suppers.finger.tools.CacheUtils;
import awd.cloud.suppers.finger.tools.FingerUtil;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RefreshScope
@RequestMapping("/bzwtzm")
@AccessLogger("Bzwtzm")
@Api(value = "Bzwtzm") 
public class BzwtzmController implements GenericEntityController<BzwtzmEntity, String, QueryParamEntity, BzwtzmEntity> {

	@Autowired
	private BzwtzmService bzwtzmService;
	@Autowired
	private CacheService cacheService;

	@Override
    public BzwtzmEntity modelToEntity(BzwtzmEntity model, BzwtzmEntity entity) {
        return model;
    }

    @Autowired
    public void setBzwtzmService(BzwtzmService bzwtzmService) {
        this.bzwtzmService = bzwtzmService;
    }
	 
	@Override
	public CrudService<BzwtzmEntity, String> getService() {
		return bzwtzmService;
	}
	
	@PostMapping("/fingerfeatureRegist")
	//@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹特征码注册——被监管人指纹特征数据表结构")
	@ResponseBody
	public ResponseMessage<?> fingerfeatureRegist(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] featuredata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();

		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (bzwtzmService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员已注册指纹特征码，请删除后重新注册");
		}
		
		byte[] bmpbyte = null;// 指纹特征码
		Map<String, byte[]> bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 2; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(featuredata[i].replace("\"", ""));
				bmpbytemap.put("bmpbyte" + i, bmpbyte); //bmptytemap中存放解码后的特征码
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTZXH1 = fingercode[0] + "";
		byte[] ZWTZ1 = bmpbytemap.get("bmpbyte0");
		String ZWTZXH2 = fingercode[1] + "";
		byte[] ZWTZ2 = bmpbytemap.get("bmpbyte1");
		BzwtzmEntity entity = new BzwtzmEntity();
		entity.setJsbh(jsbh);
		entity.setRybh(rybh);
		entity.setState("R2");
		entity.setScbz("1");
		entity.setCreatetime(new Date());
		
		entity.setZwtzxh1(ZWTZXH1);
		entity.setZwtz1(ZWTZ1);
		entity.setZwtzxh2(ZWTZXH2);
		entity.setZwtz2(ZWTZ2);
	
		int num = bzwtzmService.fingerfeatureRegist(entity);
		if(num == 1) {
			//更新缓存
			CacheUtils.addZwtzmCache(rybh,featuredata,fingercode);
			FingerUtil.moveList(jsbh,rybh,0);
			return ResponseMessage.ok("人员" + rybh + "指纹注册成功！");
		}else {
			return ResponseMessage.error("人员" + rybh + "指纹注册失败！");
		}
	}
	
	@PostMapping("/upateFingerfeature")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹特征码注册——被监管人指纹特征数据表结构——2个指纹特征码？")
	public ResponseMessage<?> upateFingerfeature(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] featuredata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtzmService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹特征码");
		}
		
			byte[] bmpbyte = null;// 指纹图像数据raw 格式
			Map<String, byte[]> bmpbytemap = new HashMap<String, byte[]>();
			for (int i = 0; i < 2; i++) {
				try {
					bmpbyte = FingerUtil.getBmpByte(featuredata[i].replace("\"", ""));
					bmpbytemap.put("bmpbyte" + i, bmpbyte);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String ZWTZXH1 = fingercode[0] + "";
			byte[] ZWTZ1 = bmpbytemap.get("bmpbyte0");
			String ZWTZXH2 = fingercode[1] + "";
			byte[] ZWTZ2 = bmpbytemap.get("bmpbyte1");
			BzwtzmEntity entity = bzwtzmService.getEntyByRybh(rybh,"R2");
			entity.setJsbh(jsbh);
			entity.setZwtzxh1(ZWTZXH1);
			entity.setZwtz1(ZWTZ1);
			entity.setZwtzxh2(ZWTZXH2);
			entity.setZwtz2(ZWTZ2);
			entity.setUpdatetime(new Date());
			
			String num = bzwtzmService.saveOrUpdate(entity);
			if(num == entity.getId()){
				CacheUtils.delete(rybh);
				FingerUtil.updateCache(rybh,featuredata,fingercode);//更新缓存
				FingerUtil.moveList(jsbh,rybh,0);
				return ResponseMessage.ok("人员"+ rybh +" 指纹特征码更新成功！");
			}else{
				return ResponseMessage.error("人员"+ rybh +" 指纹特征码更新失败！");
			}
	}
	
	/**
	 * 根据rybh 删除人员指纹特征码
	 * 
	 */
	@PostMapping("/removeFingerfeature")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据rybh 删除人员指纹特征码")
	public ResponseMessage<?> removeFingerfeature(String rybh) {
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtzmService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹特征码");
		}
		
		int num = bzwtzmService.deleteByRybh(rybh);
		
		if (num == 1) {
			CacheUtils.delete(rybh);
			cacheService.loadMjOrRyCache();
			return ResponseMessage.ok("人员"+ rybh +" 指纹特征码删除成功");
		}else {
			return ResponseMessage.error("人员"+ rybh +" 指纹特征码删除失败");
		}
	}
	

}
