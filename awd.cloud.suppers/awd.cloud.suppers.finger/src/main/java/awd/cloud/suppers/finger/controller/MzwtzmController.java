/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.cloud.suppers.finger.model.FingerModel;
import awd.cloud.suppers.finger.service.CacheService;
import awd.cloud.suppers.finger.service.MzwtzmService;
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
import sun.misc.BASE64Encoder;

@RestController
@RefreshScope
@RequestMapping("/mzwtzm")
@AccessLogger("Mzwtzm")
@Api(value = "Mzwtzm") 
public class MzwtzmController implements GenericEntityController<MzwtzmEntity, String, QueryParamEntity, MzwtzmEntity> {

	@Autowired
	private MzwtzmService mzwtzmService;

	@Autowired
	private CacheService cacheService;

	@Override
    public MzwtzmEntity modelToEntity(MzwtzmEntity model, MzwtzmEntity entity) {
        return model;
    }

    @Autowired
    public void setMzwtzmService(MzwtzmService mzwtzmService) {
        this.mzwtzmService = mzwtzmService;
    }
	 
	@Override
	public CrudService<MzwtzmEntity, String> getService() {
		return mzwtzmService;
	}
	
	@PostMapping("/test")
	@ApiOperation("一个测试用接口")
	public void test(String jsbh,String rybhOrZjh,int type) {
		
		
	}
	
	@ApiOperation("新增民警指纹特征码")
	@HystrixCommand
	@OpenAPI
	@PostMapping("/mfingerfeatureRegist")
	@ResponseBody
	public ResponseMessage<?> mfingerfeatureRegist(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] featuredata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (mzwtzmService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警已注册指纹特征码，请删除后重新注册！");
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
		
		MzwtzmEntity mzwtzmEntity = new MzwtzmEntity();
		mzwtzmEntity.setZwtzxh1(fingercode[0]+"");
		mzwtzmEntity.setZwtz1(bmpbytemap.get("bmpbyte0"));
		mzwtzmEntity.setZwtzxh2(fingercode[1]+"");
		mzwtzmEntity.setZwtz2(bmpbytemap.get("bmpbyte1"));
		mzwtzmEntity.setRybh(zjh);
		mzwtzmEntity.setJsbh(jsbh);
		mzwtzmEntity.setScbz("1");
		mzwtzmEntity.setState("R2");
		mzwtzmEntity.setCreatetime(new Date());
		
		String num = mzwtzmService.saveMzwtzm(mzwtzmEntity);
		if(num == "1") {
			//更新缓存
			CacheUtils.addZwtzmCache(zjh,featuredata,fingercode);
			FingerUtil.moveList(jsbh,zjh,1);
			return ResponseMessage.ok("指纹注册成功！");
		}else {
			return ResponseMessage.error("指纹注册失败！");
		}
	}

	
	@PostMapping("/mupateFingerfeature")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("更新民警指纹特征码")
	@ResponseBody
	public ResponseMessage<?> mupateFingerfeature(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] featuredata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtzmService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹特征码！");
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
			
		MzwtzmEntity mzwtzmEntity = mzwtzmService.getEntyByZjh(zjh);
		mzwtzmEntity.setJsbh(jsbh);
		mzwtzmEntity.setZwtzxh1(fingercode[0]+"");
		mzwtzmEntity.setZwtz1(bmpbytemap.get("bmpbyte0"));
		mzwtzmEntity.setZwtzxh2(fingercode[1]+"");
		mzwtzmEntity.setZwtz2(bmpbytemap.get("bmpbyte1"));
		mzwtzmEntity.setUpdatetime(new Date());
		
		String num = mzwtzmService.saveOrUpdate(mzwtzmEntity);
		if(num == mzwtzmEntity.getId()){
			CacheUtils.delete(zjh);
			FingerUtil.updateCache(zjh,featuredata,fingercode);//更新缓存
			FingerUtil.moveList(jsbh,zjh,1);
			return ResponseMessage.ok("民警 " + zjh + "指纹特征码更新成功！");
		}else {
			return ResponseMessage.error("民警 " + zjh + "指纹特征码更新失败！");
		}
	}
	
	
	/**
	 * 根据zjh 删除民警指纹特征码
	 * 
	 */
	@PostMapping("/removeMjFingerfeature")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据zjh 删除民警指纹特征码")
	public ResponseMessage<?> removeMjFingerfeature(String zjh) {
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtzmService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹特征码！");
		}
		
		int num = mzwtzmService.deleteByZjh(zjh);
		
		if (num == 1) {
			CacheUtils.delete(zjh);
			cacheService.loadMjOrRyCache();
			return ResponseMessage.ok("民警"+ zjh +" 指纹特征码删除成功");
		}else {
			return ResponseMessage.error("民警"+ zjh +" 指纹特征码删除失败");
		}
	}
	
}
