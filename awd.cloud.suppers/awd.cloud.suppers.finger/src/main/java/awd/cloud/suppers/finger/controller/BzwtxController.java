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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.suppers.finger.entity.BzwtxEntity;
import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.cloud.suppers.finger.model.FingerModel;
import awd.cloud.suppers.finger.service.BzwtxService;
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
import sun.misc.BASE64Encoder;


@RestController
@RefreshScope
@RequestMapping("/bzwtx")
@AccessLogger("Bzwtx")
@Api(value = "Bzwtx") 
public class BzwtxController implements GenericEntityController<BzwtxEntity, String, QueryParamEntity, BzwtxEntity> {

	@Autowired
	private BzwtxService bzwtxService;

	@Autowired
	private BzwtzmService bzwtzmService;
	@Autowired
	private CacheService cacheService;
	
	@Override
    public BzwtxEntity modelToEntity(BzwtxEntity model, BzwtxEntity entity) {
        return model;
    }

    @Autowired
    public void setBzwtxService(BzwtxService bzwtxService) {
        this.bzwtxService = bzwtxService;
    }
	 
	@Override
	public CrudService<BzwtxEntity, String> getService() {
		return bzwtxService;
	}
	
	@PostMapping("/fingerRegist")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像注册——被监管人员指纹图像数据表结构，6个图像数据")
	@ResponseBody
	public ResponseMessage<?> fingerRegist(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		byte[] fingercode = fingerModel.getFingercode();
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员已注册指纹图像，请删除后重新注册");
		}
		
		byte[] bmpbyte = null;// 指纹图像
		Map<String, byte[]> bmpbytemap = new HashMap<String, byte[]>();
		for (int i = 0; i < 6; i++) {
			try {
				bmpbyte = FingerUtil.getBmpByte(fingerdata[i].replace("\"", ""));
				bmpbytemap.put("bmpbyte" + i, bmpbyte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String ZWTXXH11 = fingercode[0] + "1";
		byte[] ZWTX11 = bmpbytemap.get("bmpbyte0");
		String ZWTXXH12 = fingercode[0] + "2";
		byte[] ZWTX12 = bmpbytemap.get("bmpbyte1");
		String ZWTXXH13 = fingercode[0] + "3";
		byte[] ZWTX13 = bmpbytemap.get("bmpbyte2");
		String ZWTXXH21 = fingercode[1] + "1";
		byte[] ZWTX21 = bmpbytemap.get("bmpbyte3");
		String ZWTXXH22 = fingercode[1] + "2";
		byte[] ZWTX22 = bmpbytemap.get("bmpbyte4");
		String ZWTXXH23 = fingercode[1] + "3";
		byte[] ZWTX23 = bmpbytemap.get("bmpbyte5");
		
		BzwtxEntity entity = new BzwtxEntity();
		entity.setRybh(rybh);
		entity.setJsbh(jsbh);
		entity.setState("R2");
		entity.setScbz("1");
		entity.setCreatetime(new Date());
		
		entity.setZwtx11(ZWTX11);
		entity.setZwtxxh11(ZWTXXH11);
		entity.setZwtx12(ZWTX12);
		entity.setZwtxxh12(ZWTXXH12);
		entity.setZwtx13(ZWTX13);
		entity.setZwtxxh13(ZWTXXH13);
		entity.setZwtx21(ZWTX21);
		entity.setZwtxxh21(ZWTXXH21);
		entity.setZwtx22(ZWTX22);
		entity.setZwtxxh22(ZWTXXH22);
		entity.setZwtx23(ZWTX23);
		entity.setZwtxxh23(ZWTXXH23);
		
		int num = bzwtxService.fingerRegist(entity);
		
		if(num == 1) {
			return ResponseMessage.ok("指纹注册成功！");
		}else {
			return ResponseMessage.error("指纹注册失败！");
		}
	}
	
	
	@PostMapping("/fingerRegistWithYs")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像与特征码注册——被监管人员，使用一所指纹仪，传入6个图像数据和2个特征码数据")
	@ResponseBody
	public ResponseMessage<?> fingerRegistWithYs(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		String[] fingerTzmdata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();	
			
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员已注册指纹图像，请删除后重新注册");
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
		BzwtxEntity zwtx_entity = new BzwtxEntity();
		zwtx_entity.setRybh(rybh);
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
		
		
		BzwtzmEntity zwtzm_entity = new BzwtzmEntity();
		zwtzm_entity.setJsbh(jsbh);
		zwtzm_entity.setRybh(rybh);
		zwtzm_entity.setState("R2");
		zwtzm_entity.setScbz("1");
		zwtzm_entity.setCreatetime(new Date());
		zwtzm_entity.setZwtzxh1(ZWTZXH1);
		zwtzm_entity.setZwtz1(ZWTZ1);
		zwtzm_entity.setZwtzxh2(ZWTZXH2);
		zwtzm_entity.setZwtz2(ZWTZ2);
		
		int num = bzwtxService.fingerRegistWithYs(zwtx_entity,zwtzm_entity);
		
		if(num == 1) {
			CacheUtils.delete(rybh);
			CacheUtils.addZwtzmCache(rybh,fingerTzmdata,fingercode);
			FingerUtil.moveList(jsbh,rybh,0);
			return ResponseMessage.ok("人员指纹注册成功！");
		}else {
			return ResponseMessage.error("人员指纹注册失败！");
		}
	}
	
	
	
	@PostMapping("/upateFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像更新——被监管人员指纹图像数据表结构")
	@ResponseBody
	public ResponseMessage<?> upateFinger(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		byte[] fingercode = fingerModel.getFingercode();	
			
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹图像，请先注册");
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
		
		String ZWTXXH11 = fingercode[0] + "1";
		byte[] ZWTX11 = bmpbytemap.get("bmpbyte0");
		String ZWTXXH12 = fingercode[0] + "2";
		byte[] ZWTX12 = bmpbytemap.get("bmpbyte1");
		String ZWTXXH13 = fingercode[0] + "3";
		byte[] ZWTX13 = bmpbytemap.get("bmpbyte2");
		String ZWTXXH21 = fingercode[1] + "1";
		byte[] ZWTX21 = bmpbytemap.get("bmpbyte3");
		String ZWTXXH22 = fingercode[1] + "2";
		byte[] ZWTX22 = bmpbytemap.get("bmpbyte4");
		String ZWTXXH23 = fingercode[1] + "3";
		byte[] ZWTX23 = bmpbytemap.get("bmpbyte5");
		
		BzwtxEntity entity = bzwtxService.getEntyByRybh(rybh,"R2");
		entity.setJsbh(jsbh);
		entity.setZwtx11(ZWTX11);
		entity.setZwtxxh11(ZWTXXH11);
		entity.setZwtx12(ZWTX12);
		entity.setZwtxxh12(ZWTXXH12);
		entity.setZwtx13(ZWTX13);
		entity.setZwtxxh13(ZWTXXH13);
		entity.setZwtx21(ZWTX21);
		entity.setZwtxxh21(ZWTXXH21);
		entity.setZwtx22(ZWTX22);
		entity.setZwtxxh22(ZWTXXH22);
		entity.setZwtx23(ZWTX23);
		entity.setZwtxxh23(ZWTXXH23);
		
		entity.setUpdatetime(new Date());
		String num = bzwtxService.saveOrUpdate(entity);
		
		if(num == entity.getId()) {
			return ResponseMessage.ok("指纹图像更新成功");
		}else {
			return ResponseMessage.error("指纹更新失败");
		}
		
	}
	
	@PostMapping("/upatefingerWithYs")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("指纹图像与特征码更新——被监管人员，使用一所指纹仪，传入6个图像数据和2个特征码数据")
	@ResponseBody
	public ResponseMessage<?> upatefingerWithYs(FingerModel fingerModel,HttpServletRequest request) {
		String rybh = fingerModel.getRybhOrZjh(); 
		String jsbh = fingerModel.getJsbh();
		String[] fingerdata = fingerModel.getFingerdata();
		String[] fingerTzmdata = fingerModel.getFeaturedata();
		byte[] fingercode = fingerModel.getFingercode();	
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹图像，请先注册");
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
		BzwtxEntity zwtx_entity = bzwtxService.getEntyByRybh(rybh,"R2");
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
		
		BzwtzmEntity zwtzm_entity = bzwtzmService.getEntyByRybh(rybh,"R2");
		zwtzm_entity.setJsbh(jsbh);
		zwtzm_entity.setCreatetime(new Date());
		zwtzm_entity.setZwtzxh1(ZWTZXH1);
		zwtzm_entity.setZwtz1(ZWTZ1);
		zwtzm_entity.setZwtzxh2(ZWTZXH2);
		zwtzm_entity.setZwtz2(ZWTZ2);
		zwtzm_entity.setUpdatetime(new Date());
		
		int num = bzwtxService.fingerRegistWithYs(zwtx_entity,zwtzm_entity);
		
		if(num == 1) {
			CacheUtils.addZwtzmCache(rybh,fingerTzmdata,fingercode);
			FingerUtil.moveList(jsbh,rybh,0);
			return ResponseMessage.ok("指纹更新成功！");
		}else {
			return ResponseMessage.error("指纹更新失败！");
		}
	}
	
	
	@PostMapping("/removeFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据rybh删除指纹图像和特征码——需要指纹图像和特征码都存在")
	public ResponseMessage<?> removeFinger(String rybh) {
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹图像！");
		}
		
		String num = bzwtxService.deleteZwtxAndTzmByRybh(rybh);
		String result = null;
		if (num == "1") {
			result = "删除成功";
			CacheUtils.delete(rybh);
			cacheService.loadMjOrRyCache();
		}else {
			result = "删除失败";
		}
		return ResponseMessage.ok(result);
	}
	
	@PostMapping("/rycsFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("人员出所")
	public ResponseMessage<?> rycsFinger(String rybh) {
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹图像！");
		}
		
		String num = bzwtxService.updateZwtxAndTzmByRybh(rybh,"R2","R3");
		String result = null;
		if (num == "1") {
			result = "人员出所成功，state修改为R3";
			CacheUtils.delete(rybh);
			cacheService.loadMjOrRyCache();
		}else {
			result = "人员出所失败";
		}
		return ResponseMessage.ok(result);
	}
	
	@PostMapping("/ysthFinger")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("人员移送退回")
	public ResponseMessage<?> ysthFinger(String rybh) {
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.ryIsYcs(rybh)) {
			return ResponseMessage.error("此人员不是已出所人员！");
		}
		
		String num = bzwtxService.updateZwtxAndTzmByRybh(rybh,"R3","R2");
		String result = null;
		if (num == "1") {
			result = "人员移送退回成功，state修改为R2";
		}else {
			result = "人员移送退回失败";
		}
		//重新加载特征码进入缓存
		//cacheService.loadAllZwAndTzm();
		List<BzwtzmEntity> entityList = bzwtzmService.getEntyListByRybh(rybh,"R2");
		BASE64Encoder base64Encoder = new BASE64Encoder();
		if(entityList != null && entityList.size() > 0){
			List<String> tzmlist=new ArrayList<String>();
			if(entityList.get(0).getZwtz1() == null){
				System.err.println("----error--------" + rybh + "--特征码1为空");//139正式库未覆盖
			}else{
				tzmlist.add(base64Encoder.encode((byte[]) entityList.get(0).getZwtz1()));
			}
			if(entityList.get(0).getZwtz2() == null){
				System.err.println("----error--------" + rybh + "--特征码2为空");
			}else{
				tzmlist.add(base64Encoder.encode((byte[]) entityList.get(0).getZwtz2()));
			}
			tzmlist.add(entityList.get(0).getZwtzxh1());
			tzmlist.add(entityList.get(0).getZwtzxh2());
			
			Map<String,Object> tzmMap = new HashMap<String,Object>();
				
			tzmMap.put(rybh,tzmlist);
			CacheUtils.add(rybh, tzmMap);
			FingerUtil.moveList(entityList.get(0).getJsbh(), rybh, 0);
			System.err.println("------" + rybh + "移送退回缓存更新结束------");
		}
		return ResponseMessage.ok(result);
	}
	
	@PostMapping("/getfingercode")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("返回人员手指指纹")
	public ResponseMessage<?> getfingercode(String rybh) {
		
		if (!FingerUtil.checkRYBH(rybh)) {
			return ResponseMessage.error("人员编号格式错误！");
		}
		
		if (!bzwtxService.entyIsExist(rybh)) {
			return ResponseMessage.error("此人员未注册指纹图像！");
		}
		
		String result = null;
		try {
			Map<String, Object> listcode = bzwtxService.selectZwCode(rybh, "R2", "R2");
			result = listcode.get("zw1").toString() + "," + listcode.get("zw2").toString();
		} catch (Exception e) {
			System.err.println("出错了");
			return ResponseMessage.error("指纹图像或特征码未注册");
		}
		System.err.println("指位代码：" + result);
		return ResponseMessage.ok(result);
	}
	
}
