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

import awd.cloud.suppers.finger.entity.MzwtxEntity;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.cloud.suppers.finger.model.FingerModel;
import awd.cloud.suppers.finger.service.CacheService;
import awd.cloud.suppers.finger.service.MzwtxService;
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
@RequestMapping("/mzwtx")
@AccessLogger("Mzwtx")
@Api(value = "Mzwtx") 
public class MzwtxController implements GenericEntityController<MzwtxEntity, String, QueryParamEntity, MzwtxEntity> {

	@Autowired
	private MzwtxService mzwtxService;

	@Autowired
	private CacheService cacheService;

	@Override
    public MzwtxEntity modelToEntity(MzwtxEntity model, MzwtxEntity entity) {
        return model;
    }

    @Autowired
    public void setMzwtxService(MzwtxService mzwtxService) {
        this.mzwtxService = mzwtxService;
    }
	 
	@Override
	public CrudService<MzwtxEntity, String> getService() {
		return mzwtxService;
	}
	
	@PostMapping("/mfingerRegister")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("新增民警指纹图像信息")
	@ResponseBody
	public ResponseMessage<?> mfingerRegister(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		byte[] fingercode = fingerModel.getFingercode();
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警已注册指纹图像，请删除后重新注册");
		}
		
		byte[] bmpbyte = null;// 指纹特征码
		Map<String, byte[]> bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 6; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				bmpbytemap.put("bmpbyte" + i, bmpbyte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		MzwtxEntity mzwtxEntity = new MzwtxEntity();
		mzwtxEntity.setZwtxxh11(fingercode[0] + "1");
		mzwtxEntity.setZwtx11(bmpbytemap.get("bmpbyte0"));
		mzwtxEntity.setZwtxxh12(fingercode[0] + "2");
		mzwtxEntity.setZwtx12(bmpbytemap.get("bmpbyte1"));
		mzwtxEntity.setZwtxxh13(fingercode[0] + "3");
		mzwtxEntity.setZwtx13(bmpbytemap.get("bmpbyte2"));
		mzwtxEntity.setZwtxxh21(fingercode[1] + "1");
		mzwtxEntity.setZwtx21(bmpbytemap.get("bmpbyte3"));
		mzwtxEntity.setZwtxxh22(fingercode[1] + "2");
		mzwtxEntity.setZwtx22(bmpbytemap.get("bmpbyte4"));
		mzwtxEntity.setZwtxxh23(fingercode[1] + "3");
		mzwtxEntity.setZwtx23(bmpbytemap.get("bmpbyte5"));
		mzwtxEntity.setZjh(zjh);
		mzwtxEntity.setJsbh(jsbh);
		mzwtxEntity.setState("R2");
		mzwtxEntity.setScbz("1");
		mzwtxEntity.setCreatetime(new Date());
		
		String num = mzwtxService.saveMzwtx(mzwtxEntity);
		if(num == "1") {
			return ResponseMessage.ok("民警 " + zjh + "指纹图像注册成功！");
		}else {
			return ResponseMessage.error("民警 " + zjh + "指纹图像注册失败！");
		}
	}
	
	
	@PostMapping("/mfingerRegistWithYs")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像与特征码注册——民警，使用一所指纹仪，传入6个图像数据和2个特征码数据")
	@ResponseBody
	public ResponseMessage<?> mfingerRegistWithYs(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		String[] fingerTzmdata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();	
			
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警已注册指纹图像，请删除后重新注册");
		}
		
		byte[] bmpbyte = null;// 临时字节对象
		
		//塞入指纹图像信息
		Map<String, byte[]> zwtx_bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 6; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				zwtx_bmpbytemap.put("zwtx_bmpbyte" + i, bmpbyte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTXXH11 = fingercode[0] + "1";
		byte[] ZWTX11 = zwtx_bmpbytemap.get("zwtx_bmpbyte0");
		String ZWTXXH12 = fingercode[0] + "2";
		byte[] ZWTX12 = zwtx_bmpbytemap.get("zwtx_bmpbyte1");
		String ZWTXXH13 = fingercode[0] + "3";
		byte[] ZWTX13 = zwtx_bmpbytemap.get("zwtx_bmpbyte2");
		String ZWTXXH21 = fingercode[1] + "1";
		byte[] ZWTX21 = zwtx_bmpbytemap.get("zwtx_bmpbyte3");
		String ZWTXXH22 = fingercode[1] + "2";
		byte[] ZWTX22 = zwtx_bmpbytemap.get("zwtx_bmpbyte4");
		String ZWTXXH23 = fingercode[1] + "3";
		byte[] ZWTX23 = zwtx_bmpbytemap.get("zwtx_bmpbyte5");
		
		//塞入指纹特征码信息
		Map<String, byte[]> zwtzm_bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 2; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				zwtzm_bmpbytemap.put("zwtzm_bmpbyte" + i, bmpbyte); //bmptytemap中存放解码后的特征码
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTZXH1 = fingercode[0] + "";
		byte[] ZWTZ1 = zwtzm_bmpbytemap.get("zwtzm_bmpbyte0");
		String ZWTZXH2 = fingercode[1] + "";
		byte[] ZWTZ2 = zwtzm_bmpbytemap.get("zwtzm_bmpbyte1");
		
		//把数据塞到实体类中
		MzwtxEntity zwtx_entity = new MzwtxEntity();
		zwtx_entity.setZjh(zjh);
		zwtx_entity.setJsbh(jsbh);
		zwtx_entity.setState("R2");
		zwtx_entity.setScbz("1");
		zwtx_entity.setCreatetime(new Date());
		zwtx_entity.setZwtx11(ZWTX11);
		zwtx_entity.setZwtxxh11(ZWTXXH11);
		zwtx_entity.setZwtx12(ZWTX12);
		zwtx_entity.setZwtxxh12(ZWTXXH12);
		zwtx_entity.setZwtx13(ZWTX13);
		zwtx_entity.setZwtxxh13(ZWTXXH13);
		zwtx_entity.setZwtx21(ZWTX21);
		zwtx_entity.setZwtxxh21(ZWTXXH21);
		zwtx_entity.setZwtx22(ZWTX22);
		zwtx_entity.setZwtxxh22(ZWTXXH22);
		zwtx_entity.setZwtx23(ZWTX23);
		zwtx_entity.setZwtxxh23(ZWTXXH23);
		
		MzwtzmEntity zwtzm_entity = new MzwtzmEntity();
		zwtzm_entity.setJsbh(jsbh);
		zwtzm_entity.setRybh(zjh);
		zwtzm_entity.setState("R2");
		zwtzm_entity.setScbz("1");
		zwtzm_entity.setCreatetime(new Date());
		zwtzm_entity.setZwtzxh1(ZWTZXH1);
		zwtzm_entity.setZwtz1(ZWTZ1);
		zwtzm_entity.setZwtzxh2(ZWTZXH2);
		zwtzm_entity.setZwtz2(ZWTZ2);
		
		String num = mzwtxService.mfingerRegistWithYs(zwtx_entity,zwtzm_entity);
		
		if(num == "1") {
			CacheUtils.addZwtzmCache(zjh,fingerTzmdata,fingercode);
			FingerUtil.moveList(jsbh,zjh,1);
			return ResponseMessage.ok("民警指纹注册成功！");
		}else {
			return ResponseMessage.error("民警指纹注册失败！");
		}
	}
	
	
	@PostMapping("/mupateFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("更新民警指纹图像信息")
	@ResponseBody
	public ResponseMessage<?> mupateFinger(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		byte[] fingercode = fingerModel.getFingercode();

		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹图像");
		}
		
		byte[] bmpbyte = null;// 指纹图像数据raw 格式
		Map<String, byte[]> bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 6; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				bmpbytemap.put("bmpbyte" + i, bmpbyte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		MzwtxEntity mzwtxEntity = mzwtxService.getEntyByZjh(zjh);
		mzwtxEntity.setJsbh(jsbh);
		mzwtxEntity.setZwtxxh11(fingercode[0] + "1");
		mzwtxEntity.setZwtx11(bmpbytemap.get("bmpbyte0"));
		mzwtxEntity.setZwtxxh12(fingercode[0] + "2");
		mzwtxEntity.setZwtx12(bmpbytemap.get("bmpbyte1"));
		mzwtxEntity.setZwtxxh13(fingercode[0] + "3");
		mzwtxEntity.setZwtx13(bmpbytemap.get("bmpbyte2"));
		mzwtxEntity.setZwtxxh21(fingercode[1] + "1");
		mzwtxEntity.setZwtx21(bmpbytemap.get("bmpbyte3"));
		mzwtxEntity.setZwtxxh22(fingercode[1] + "2");
		mzwtxEntity.setZwtx22(bmpbytemap.get("bmpbyte4"));
		mzwtxEntity.setZwtxxh23(fingercode[1] + "3");
		mzwtxEntity.setZwtx23(bmpbytemap.get("bmpbyte5"));
		mzwtxEntity.setUpdatetime(new Date());
		
		String num = mzwtxService.saveOrUpdate(mzwtxEntity);
		if(num == mzwtxEntity.getId()) {
			return ResponseMessage.ok("民警 " + zjh + "指纹图像更新成功！");
		}else  {
			return ResponseMessage.error("民警 " + zjh + "指纹图像更新失败！");
		}
	}

	@PostMapping("/mupateFingerWithYs")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像与特征码注册——民警，使用一所指纹仪，传入6个图像数据和2个特征码数据")
	public ResponseMessage<?> mupateFingerWithYs(FingerModel fingerModel,HttpServletRequest request) {
		String zjh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		String[] fingerTzmdata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();	
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹图像");
		}
		
		byte[] bmpbyte = null;// 临时字节对象
		
		//塞入指纹图像信息
		Map<String, byte[]> zwtx_bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 6; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				zwtx_bmpbytemap.put("zwtx_bmpbyte" + i, bmpbyte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTXXH11 = fingercode[0] + "1";
		byte[] ZWTX11 = zwtx_bmpbytemap.get("zwtx_bmpbyte0");
		String ZWTXXH12 = fingercode[0] + "2";
		byte[] ZWTX12 = zwtx_bmpbytemap.get("zwtx_bmpbyte1");
		String ZWTXXH13 = fingercode[0] + "3";
		byte[] ZWTX13 = zwtx_bmpbytemap.get("zwtx_bmpbyte2");
		String ZWTXXH21 = fingercode[1] + "1";
		byte[] ZWTX21 = zwtx_bmpbytemap.get("zwtx_bmpbyte3");
		String ZWTXXH22 = fingercode[1] + "2";
		byte[] ZWTX22 = zwtx_bmpbytemap.get("zwtx_bmpbyte4");
		String ZWTXXH23 = fingercode[1] + "3";
		byte[] ZWTX23 = zwtx_bmpbytemap.get("zwtx_bmpbyte5");
		
		//塞入指纹特征码信息
		Map<String, byte[]> zwtzm_bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 2; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				zwtzm_bmpbytemap.put("zwtzm_bmpbyte" + i, bmpbyte); //bmptytemap中存放解码后的特征码
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTZXH1 = fingercode[0] + "";
		byte[] ZWTZ1 = zwtzm_bmpbytemap.get("zwtzm_bmpbyte0");
		String ZWTZXH2 = fingercode[1] + "";
		byte[] ZWTZ2 = zwtzm_bmpbytemap.get("zwtzm_bmpbyte1");
		
		//把数据塞到实体类中
		MzwtxEntity zwtx_entity = new MzwtxEntity();
		zwtx_entity.setJsbh(jsbh);
		zwtx_entity.setZwtx11(ZWTX11);
		zwtx_entity.setZwtxxh11(ZWTXXH11);
		zwtx_entity.setZwtx12(ZWTX12);
		zwtx_entity.setZwtxxh12(ZWTXXH12);
		zwtx_entity.setZwtx13(ZWTX13);
		zwtx_entity.setZwtxxh13(ZWTXXH13);
		zwtx_entity.setZwtx21(ZWTX21);
		zwtx_entity.setZwtxxh21(ZWTXXH21);
		zwtx_entity.setZwtx22(ZWTX22);
		zwtx_entity.setZwtxxh22(ZWTXXH22);
		zwtx_entity.setZwtx23(ZWTX23);
		zwtx_entity.setZwtxxh23(ZWTXXH23);
		zwtx_entity.setUpdatetime(new Date());
		
		MzwtzmEntity zwtzm_entity = new MzwtzmEntity();
		zwtzm_entity.setJsbh(jsbh);
		zwtzm_entity.setCreatetime(new Date());
		zwtzm_entity.setZwtzxh1(ZWTZXH1);
		zwtzm_entity.setZwtz1(ZWTZ1);
		zwtzm_entity.setZwtzxh2(ZWTZXH2);
		zwtzm_entity.setZwtz2(ZWTZ2);
		zwtzm_entity.setUpdatetime(new Date());
		
		String num = mzwtxService.mfingerRegistWithYs(zwtx_entity,zwtzm_entity);
		
		if(num == "1") {
			CacheUtils.delete(zjh);
			CacheUtils.addZwtzmCache(zjh,fingerTzmdata,fingercode);
			FingerUtil.moveList(jsbh,zjh,1);
			return ResponseMessage.ok("民警指纹更新成功！");
		}else {
			return ResponseMessage.error("民警指纹更新失败！");
		}
	}
	
	@PostMapping("/mremoveFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据zjh删除指纹图像和特征码——需要指纹图像和特征码都存在")
	public ResponseMessage<?> mremoveFinger(String zjh) {
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹图像");
		}
		
		String num = mzwtxService.deleteZwtxAndTzmByZjh(zjh);
		if (num == "1") {
			CacheUtils.delete(zjh);
			cacheService.loadMjOrRyCache();
			return ResponseMessage.ok("民警 " + zjh + "指纹图像删除成功");
		}else {
			return ResponseMessage.error("民警 " + zjh + "指纹图像删除失败");
		}
	}
	
	
	@PostMapping("/getmfingercode")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("返回民警手指指纹")
	public ResponseMessage<?> getmfingercode(String zjh) {
		
		if (!FingerUtil.checkRYBH(zjh)) {
			return ResponseMessage.error("证件号格式错误！");
		}
		
		if (!mzwtxService.entyIsExist(zjh)) {
			return ResponseMessage.error("此民警未注册指纹图像");
		}
		
		String result = null;
		try {
			Map<String, Object> listcode = mzwtxService.selectMjZwCode(zjh, "R2", "R2");
			result = listcode.get("zw1").toString() + "," + listcode.get("zw2").toString();
		} catch (Exception e) {
			System.err.println("出错了");
			return ResponseMessage.error("指纹图像或特征码未注册");
		}
		System.err.println("指位代码：" + result);
		return ResponseMessage.ok(result);
	}
	
}
