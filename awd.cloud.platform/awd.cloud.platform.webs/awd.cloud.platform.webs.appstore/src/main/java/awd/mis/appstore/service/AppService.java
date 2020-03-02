package awd.mis.appstore.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import awd.mis.appstore.api.LogsService;
import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.model.AppModel;
import awd.mis.appstore.model.ApplunboModel;
import awd.mis.appstore.model.PlModel;
import awd.mis.appstore.tools.JSONUtil;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import awd.mis.appstore.tools.Term;
import awd.mis.appstore.tools.TermType;

@Service
public class AppService {
	@Autowired
	private ManagerService managerSevice;

	
	@Autowired
	private LogsService logsService;

	/**
	 * 获取 推荐 app信息列表
	 * @return
	 */
	public List<AppModel> getTuiJian(){
		List<AppModel>  list=new ArrayList<AppModel>();		
		for(int i=1;i<5;i++) {
			AppModel appModel=new AppModel("1000000000000000000000"+i);
			appModel.setAppcode("0000"+i);
			appModel.setCreatetime(new Date());
			appModel.setCreator("zhoujian");
			//appModel.setDesc("app"+i);
			appModel.setFlag("activity");
			appModel.setName("app"+i+i+i);
			appModel.setPic1("IMG_1836_"+(23+i)+".png");
			appModel.setPic2("IMG_1836_"+(23+i)+".png");
			appModel.setPic3("IMG_1836_"+(23+i)+".png");
			appModel.setState("R2");
			appModel.setType("0101q");
			appModel.setVersion("1.01");
			appModel.setZone("china");
			list.add(appModel);
		}
		return list;
	}
	
	/**
	 * 根据id 获取应用详细情况
	 * @return 
	 */
	public AppModel getApp(String id) {
		ResponseMessage<AppModel> responseMessage = managerSevice.getAppByid(id);
		return responseMessage.getResult();
	}

	/**
	 * 根据code 获取应用详细情况
	 */
	public AppModel getAppByCode(String appCode) {
		QueryParam param = new QueryParam();
		param.setPageSize(1);
		param.and("code","1");
		param.and("appcode",appCode);
		ResponseMessage<PagerResult<AppModel>> responseMessage = managerSevice.listApp(param);
		if(responseMessage.getResult().getData().size()>0) {
			return responseMessage.getResult().getData().get(0);
		}else {
			return null;
		}
	}


	public ResponseMessage<PagerResult<AppModel>> findAll(String jsbh, String appname, String classfic, String role, String feature) {
		
		QueryParam params=new QueryParam();
		params.setPageSize(8);
		params.setPaging(true);
		if(!StringUtils.isEmpty(jsbh)) {
			params.and("jsbh", TermType.eq, jsbh);
		}
		if(!StringUtils.isEmpty(appname)) {
			params.and("name", TermType.like, appname);
		}
		if(classfic!=null) {
			params.and("zone", TermType.like, "%"+classfic+"%");
		}
		if(!StringUtils.isEmpty(role)) {
			params.and("role", TermType.in, role);
		}
		if(!StringUtils.isEmpty(feature)) {
			params.and("fruit", TermType.gte, feature);
		}
		params.and("flag", TermType.eq, "3");
		System.out.println("---------------------观测---------------------------");
		System.out.println(JSONUtil.toJson(params));
		System.out.println("------------------------------------------------");
		ResponseMessage<PagerResult<AppModel>> appList=managerSevice.listApp(params);	
		if(appList.getStatus()==200) {
			return appList;
		}else {
			return ResponseMessage.error("错误");
		}
		
	}
	
	
	public ResponseMessage<PagerResult<AppModel>> findAllWithPage(String jsbh, String appname, String classfic, String role, String feature,String pageIndex, String pageSize) {
		
		QueryParam params=new QueryParam();
		int index = 0;
		int size = 12;
		try {
			index = Integer.valueOf(pageIndex).intValue();
			size = Integer.valueOf(pageSize).intValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		params.setPageIndex(index);
		params.setPageSize(size);
		params.setPaging(true);
		if(!StringUtils.isEmpty(jsbh)) {
			params.and("jsbh", TermType.eq, jsbh);
		}
		if(!StringUtils.isEmpty(appname)) {
			params.and("name", TermType.like, appname);
		}
		if(classfic!=null) {
			 String [] c = classfic.split(",");
			 Term term = new Term();
			 for(String str:c) {
				term.or("zone", TermType.like,"%"+str+"%");
				}
			 params.addTerm(term);
		}
		if(!StringUtils.isEmpty(role)) {
			params.and("role", TermType.in, role);
		}
		if(!StringUtils.isEmpty(feature)) {
			params.and("fruit", TermType.gte, feature);
		}
		params.and("flag", TermType.eq, "3");
		System.out.println("---------------------观测---------------------------");
		System.out.println(JSONUtil.toJson(params));
		System.out.println("------------------------------------------------");
		ResponseMessage<PagerResult<AppModel>> appList=managerSevice.listApp(params);	
		if(appList!=null&&appList.getStatus()==200) {
			return appList;
		}else {
			return ResponseMessage.error("错误");
		}
		
	}
	
	public ResponseMessage<PagerResult<PlModel>> getAppPl(QueryParam queryParam) { // 根据appcode获取app的评论
		
		ResponseMessage<PagerResult<PlModel>> plList = logsService.getPlEntyListByAppcode(queryParam);
		return plList;
	}
	
	public ResponseMessage<String> savePl(PlModel plModel) { // 添加评论
		return logsService.savePl(plModel);
	}
	
	public ResponseMessage<String> updatePl(PlModel plModel) {
		String id = plModel.getId();
		return logsService.updatePlById(id, plModel);
	}
	
	public ResponseMessage<String> removePl(String id) {//移除评论
		
		return logsService.removePlById(id);
	}
	
	public ResponseMessage<String> deletePl(String id) {//删除
		
		return logsService.deletePlById(id);
	}

	public Object comment(String id) {
		return "这个方法删除了";
	}
	
	/*
	 * app发布
	 */
	public ResponseMessage<String> addApp(AppModel appModel){
		ResponseMessage<String> result = managerSevice.saveApp(appModel);
		return null;
		
	}
	
	/*
	 * app下架
	 */
	
	
	public ResponseMessage<String> appXj(String id,AppModel appModel){
		
		ResponseMessage<String> result = managerSevice.updateApp(id,appModel);
		return result;
		
	}
//	app审批
	public ResponseMessage<String> appSp(String id,AppModel appModel){
		
		ResponseMessage<String> result = managerSevice.updateApp(id,appModel);
		/*去除审批通过后设置轮播功能，改为手动
		 if (result.getStatus() == 200) {
			AppModel app = managerSevice.getAppByid(id).getResult();
			ApplunboModel applunboModel = new ApplunboModel();
			applunboModel.setSort("1");
			applunboModel.setAppcode(app.getAppcode());
			applunboModel.setAppurl(app.getUrl());
			applunboModel.setPhotoURL(app.getPic1());
			applunboModel.setCreator(app.getUpdator());
			applunboModel.setCreatetime(new Date());
			applunboModel.setState("R2");
			managerSevice.insertLunbo(applunboModel);
		}*/
		return result;
		
	}

	//获取轮播图片url
	public List<ApplunboModel> getLunboUrl(){
		List<ApplunboModel> list=new ArrayList<>();
		QueryParam param=new QueryParam();
		param.setPageSize(4);
		try {
			list =managerSevice.getFourURL(param).getResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ResponseMessage<PagerResult<AppModel>> getAppEntyListByJsbh(String pageIndex,String pageSize,String jsbh) {
		System.err.println("---jsbh---"+jsbh);
		System.err.println("---pageIndex---"+pageIndex);
		System.err.println("---pageSize---"+pageSize);
		return managerSevice.getAppEntyListByJsbh(pageIndex,pageSize,jsbh);
	}
	
	
}
