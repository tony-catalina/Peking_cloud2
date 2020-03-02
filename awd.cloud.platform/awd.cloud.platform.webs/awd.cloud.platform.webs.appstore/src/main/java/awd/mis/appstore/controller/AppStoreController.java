package awd.mis.appstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javaws.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.appstore.api.LogsService;
import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.api.MessageServersApi;
import awd.mis.appstore.model.AppModel;
import awd.mis.appstore.model.InterfaceModel;
import awd.mis.appstore.model.InterfacebindingModel;
import awd.mis.appstore.model.JsappModel;
import awd.mis.appstore.model.PublishModel;
import awd.mis.appstore.model.RabbitUserAndQueueModel;
import awd.mis.appstore.model.UserinfoModel;
import awd.mis.appstore.service.AppService;
import awd.mis.appstore.service.UserService;
import awd.mis.appstore.tools.Base64Utils;
import awd.mis.appstore.tools.ExcelPoiTools;
import awd.mis.appstore.tools.FileTools;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import awd.mis.appstore.tools.Term;
import awd.mis.appstore.tools.TermType;

@RestController
public class AppStoreController {
	@Autowired
	private AppService appService;
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private LogsService logsService;
	@Autowired
	private MessageServersApi messageServersApi;
	
	
	private  List<Map> picList =null;
	
	
	@RequestMapping(value = "/appInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, AppModel> appInfo() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();  
		String id = request.getParameter("id");
		AppModel appEntity=null;
		Map<String,AppModel> map=new HashMap<String,AppModel>();
		int i=1;
		appEntity=new AppModel("1000000000000000000000"+i);
		appEntity.setAppcode("0000"+i);
		appEntity.setCreatetime(new Date());
		appEntity.setCreator("zhoujian");
		appEntity.setFlag("activity");
		appEntity.setName("app"+i+i+i);
		appEntity.setPic1("IMG_1836_"+(23+i)+".png");
		appEntity.setPic2("IMG_1836_"+(23+i)+".png");
		appEntity.setPic3("IMG_1836_"+(23+i)+".png");
		appEntity.setState("R2");
		appEntity.setType("0101q");
		appEntity.setVersion("1.01");
		appEntity.setZone("china");
		map.put("app", appEntity);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public @ResponseBody synchronized ResponseMessage<?> addApp(@RequestParam(value = "exefile",required=false) MultipartFile exefile,HttpServletRequest request) throws IOException {
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		AppModel app =  new AppModel();
		PublishModel pModel = new PublishModel();
		String appName = request.getParameter("name");
		String version = request.getParameter("version");
		String memo = request.getParameter("desc");
		String url  = request.getParameter("url");
		String type = request.getParameter("type");
		String icon=request.getParameter("icon");
		String zone  = request.getParameter("zone");
		String exeurl = request.getParameter("exeurlyz");
		String role = request.getParameter("role");
		String sysversion=request.getParameter("version");
		String browversion = request.getParameter("browversion");
	    String jsbh = userinfo.getJsbh();
		app.setName(appName);
		Base64Utils bs = new Base64Utils();
		app.setAppcode(bs.encodeStr("应用名称:"+appName+",应用URL:"+url+",应用类型:"+type));
		app.setCreatetime(new Date());
		app.setState("R2");
		app.setVersion(version);
		app.setCreator(userinfo.getLoginname());
		app.setMemo(memo);
		app.setIcon(icon);
		app.setUrl(url);
		app.setExeurl(exeurl);
		app.setType(type);
		app.setZone(zone);
		app.setRole(role);
		app.setBrowversion(browversion);
		app.setSysversion(sysversion);
		byte[] exeByte = null;
		if(exefile != null) {
		try {
			InputStream in = exefile.getInputStream();
			 if(in!=null){
		            int len1 = in.available();
		            exeByte = new byte[len1];
		            in.read(exeByte);
		     	}
				app.setExefile(exeByte);
		//Map<String, Object> add = logsService.uploadExe(exefile, jsbh,"1","1", "1");
			//System.err.println(add.get("address")+"*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		//	app.setExeurl((String)add.get("address"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}
		try {
			app.setP1((byte[])picList.get(0).get("byte"));
			app.setPic1((String)picList.get(0).get("address"));
			app.setP2((byte[])picList.get(1).get("byte"));
			app.setPic2((String)picList.get(1).get("address"));
			app.setP3((byte[])picList.get(2).get("byte"));
			app.setPic3((String)picList.get(2).get("address"));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			picList.clear();
		}
		if(!("9".equals(userinfo.getUsertype()))) {
			app.setFlag("0");
			ResponseMessage msg = managerService.saveApp(app);
			System.err.println("+++++"+msg);
			if(msg.getStatus()==200) {
				//新增消息队列
				RabbitUserAndQueueModel rabbitUserAndQueueModel = new RabbitUserAndQueueModel();
				rabbitUserAndQueueModel.setAppid(app.getAppcode());
				rabbitUserAndQueueModel.setAppname(app.getName());
				rabbitUserAndQueueModel.setAutodelete("0");
				rabbitUserAndQueueModel.setIstemp("0");
				rabbitUserAndQueueModel.setPassword(userinfo.getLoginpass());
				rabbitUserAndQueueModel.setQueuename(app.getName());
				rabbitUserAndQueueModel.setUsername(app.getName());
				System.err.println("rabbitUserAndQueueModel--"+JSON.toJSONString(rabbitUserAndQueueModel));
				messageServersApi.addUserAndQueue(rabbitUserAndQueueModel);
				return msg;
			}else {
				return ResponseMessage.error("保存成功！");
			}
		}else {
			app.setFlag("3");
			pModel.setAppcode(app.getAppcode());
			pModel.setCreator(userinfo.getLoginname());
			pModel.setPublisher(userinfo.getLoginname());
			pModel.setPubtime(new Date());
			pModel.setCreatetime(new Date());
			pModel.setVersion(version);
			pModel.setState("R2");
			ResponseMessage res =managerService.saveApp(app);
			if(res.getStatus() == 200) {
				return ResponseMessage.ok(managerService.save(pModel));
			}else {
				return ResponseMessage.error("应用发布失败，请联系管理员");
			}
		}
	}
	
	/**
	 * app下架
	 * @param appModel
	 * @return
	 */
	@RequestMapping(value = "/appXj", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage<String> appXj(HttpServletRequest request) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		AppModel appModel = new AppModel();
		String id = request.getParameter("id");
		String jsbh = "";
		appModel.setFlag("4");
		appModel.setUpdatetime(new Date());
		appModel.setUpdator(userinfo.getLoginname());
		ResponseMessage<String> result = appService.appXj(id,appModel);
		
		QueryParam params=new QueryParam();
		String appcode = request.getParameter("appcode");
		if(!StringUtils.isNullOrEmpty(appcode)) {
			params.and("appcode", TermType.eq, appcode);
		}
		if(!StringUtils.isNullOrEmpty(jsbh)) {
			params.and("jsbh", TermType.eq, jsbh);
		}
		//修改publish记录
		if (managerService.listPublish(params).getResult().getData().size()>0) {
			List<PublishModel> pList = managerService.listPublish(params).getResult().getData();
			PublishModel publish  =  new PublishModel();
			publish.setState("R4");
			ResponseMessage<String> r = managerService.updatePublish(pList.get(0).getId(), publish);
		}
		
		//删除jsapp记录
		if (managerService.listJsApp(params).getResult().getData().size()>0) {
			managerService.delJsApp(appcode,jsbh);
		}
		
		return result;
	}
	
	/**
	 * 设置轮播图
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/appLunbo", method=RequestMethod.POST)
	public @ResponseBody ResponseMessage<String> appLunbo(HttpServletRequest request){
		UserinfoModel userinfo = (UserinfoModel) request.getSession().getAttribute("currentUser");//获得当前用户
		String data[] = request.getParameterValues("data[]");//获得前段传过来的数据
		StringBuffer sb=new StringBuffer();//创建stringBuffer，并将前段数据遍历用‘,’拼接放入stringBuffer中
		for(int i=0;i<data.length;i++){
			if(i==data.length){
				sb.append(data[i]);
			}else{
				sb.append(data[i]+",");
			}
			
		}
		ResponseMessage<String> rs=managerService.setLunBoApp(sb.toString(),userinfo.getLoginname());
		return rs;
		
	}
	
//	审批应用
	@RequestMapping(value = "/appSp", method = RequestMethod.POST)
	public ResponseMessage<?> appSp(HttpServletRequest request) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		UserinfoModel userinfo = (UserinfoModel) request.getSession().getAttribute("currentUser");
		AppModel appModel = new AppModel();
		String appCode = request.getParameter("appcode");
		String id = request.getParameter("id");
		String data = request.getParameter("data");
		appModel.setId(id);
		QueryParam params = new QueryParam();
		if (!StringUtils.isNullOrEmpty(appCode)) {
			params.and("appcode", TermType.eq, appCode);
		}
		List<PublishModel> pList = managerService.listPublish(params).getResult().getData();
		if ("yes".equals(data)) {
			appModel.setFlag("3");
			if (pList.size() > 0) {
				PublishModel publish = new PublishModel();
				publish.setState("R2");
				publish.setUpdatetime(new Date());
				publish.setUpdator(userinfo.getLoginname());
				ResponseMessage<String> r = managerService.updatePublish(pList.get(0).getId(), publish);
			}
		} else {
			appModel.setFlag("2");
			if (pList.size() > 0) {
				PublishModel publish = new PublishModel();
				publish.setState("R3");
				publish.setUpdatetime(new Date());
				publish.setUpdator(userinfo.getLoginname());
				ResponseMessage<String> r = managerService.updatePublish(pList.get(0).getId(), publish);
			}
		}
		appModel.setUpdatetime(new Date());
		appModel.setUpdator(userinfo.getLoginname());
		ResponseMessage res = appService.appSp(id, appModel);

		return res;
	}
	/**
	 * 厂家app查询
	 * 
	 * 
	 */
	@RequestMapping(value = "/getApp", method = RequestMethod.GET)
	public ResponseMessage<PagerResult<AppModel>> getapp(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String options = request.getParameter("options");
		String creator = userinfo.getLoginname();
		String flag="";
		String name=request.getParameter("name");
		String page=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if("option1".equals(options)) {
		}else if("option2".equals(options)) {
			flag="1"; //审批中
		}else if("option3".equals(options)) {
			flag="2"; //审批未通过
		}else if("option4".equals(options)) {
			flag="3"; //审批通过
		}else if("option5".equals(options)) {
			flag="0"; //未发布
		}else if("option6".equals(options)) {
			flag="4"; //下架
		}else if("option7".equals(options)) {
			flag="-1"; //api申请-默认查询除审核未通过以外的应用
		}
		QueryParam params=new QueryParam();
		params.setPageSize(Integer.parseInt(pageSize));
		params.setPaging(true);
		if(!StringUtils.isNullOrEmpty(creator)) {
			if(!("9".equals(userinfo.getUsertype()))){
				params.setPageIndex(Integer.parseInt(page));
				params.and("creator", TermType.eq, creator);
			}else {
				params.setPageIndex(Integer.parseInt(page)-1);
				if("option4".equals(options)) {
					flag="3";
				}
			}
		}
		if(!StringUtils.isNullOrEmpty(flag)) {
			if(flag != "-1"){
				params.and("flag", TermType.eq, flag);
			}else {
				params.and("flag", TermType.nin, "2,4");
			}
		}else {
			params.and("flag", TermType.nin, "4");
		}
		if(!StringUtils.isNullOrEmpty(name)) {
			params.and("name", TermType.like, "%"+name+"%");
		}
		return managerService.listApp(params);
	}
	/**
	 * 
	 * app发布
	 * 
	 */
	@RequestMapping(value = "/publishApp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<?> publishApp(HttpServletRequest request){
		AppModel app = new AppModel();
		PublishModel publish  =  new PublishModel();
		String value = request.getParameter("value");
		String data[] = value.split(",");
		app.setId(data[3]);
		app.setFlag("1");
		publish.setAppcode(data[0]);
		publish.setCreator(data[1]);
		publish.setPublisher(data[1]);
		publish.setPubtime(new Date());
		publish.setCreatetime(new Date());
		publish.setVersion(data[2]);
		publish.setState("R1");
		ResponseMessage<String> res =managerService.saveOrUpdateApp(app);
		if(res.getStatus() == 200) {
			return ResponseMessage.ok(managerService.save(publish));
		}else {
			return ResponseMessage.error("应用发布失败，请联系管理员");
		}
		
	}
	
	/**
	 * 查找用户
	 */
	@RequestMapping(value = "/queryUsers", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage<PagerResult<UserinfoModel>> deleteUser(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String creator = userinfo.getLoginname();
		String loginname = request.getParameter("loginname");
		String page=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		QueryParam params=new QueryParam();
		params.setPageSize(Integer.parseInt(pageSize));
		params.setPageIndex(Integer.parseInt(page)-1);
		params.setPaging(true);
		params.and("state", TermType.eq, "R2");
		if(!StringUtils.isNullOrEmpty(loginname)) {
			params.and("loginname", TermType.like, "%"+loginname+"%");
		}
		params.and("jsbh", TermType.eq, "999999999");
		return managerService.listUsers(params);
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<String> queryUsers(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		UserinfoModel user=new UserinfoModel();
		String loginname = userinfo.getLoginname();
		String id  = request.getParameter("id");
		user.setId(id);
		user.setState("R3");
		user.setUpdatetime(new Date());
		user.setUpdator(loginname);
		return managerService.deleteUser(id, user);
		
	}
	
	/**
	 * 新增用户
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addUser(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		UserinfoModel user=new UserinfoModel();
		String loginname = request.getParameter("loginname");
		String loginpass = request.getParameter("loginpass");
		String sfzh = request.getParameter("sfzh");
		String email = request.getParameter("email");
		String realname = request.getParameter("realname");
		
		user.setUsertype("8");
		user.setJsbh("999999999");
		user.setLoginname(loginname);
		user.setLoginpass(loginpass);
		user.setSfzh(sfzh);
		user.setEmail(email);
		user.setRealname(realname);
		user.setGlybz("1");
		user.setState("R2");
		user.setCreatetime(new Date());
		user.setCreator(userinfo.getLoginname());
		
		return ResponseMessage.ok(managerService.addUser(user));
	}
	
	/**
	 * 更新用户
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage updateUser(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		UserinfoModel user=new UserinfoModel();
		String id = request.getParameter("id");
		String loginname = request.getParameter("loginname");
		String loginpass = request.getParameter("loginpass");
		String sfzh = request.getParameter("sfzh");
		String email = request.getParameter("email");
		String realname = request.getParameter("realname");
		
		user.setLoginname(loginname);
		user.setLoginpass(loginpass);
		user.setSfzh(sfzh);
		user.setEmail(email);
		user.setRealname(realname);
		user.setState("R2");
		user.setUpdatetime(new Date());
		user.setUpdator(userinfo.getLoginname());
		
		return ResponseMessage.ok(managerService.updateUser(id,user));
	}
	/**
	 * 图片单独保存
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/getPicAddress", method = RequestMethod.POST)
	public synchronized  List getPicAddress(@RequestParam(value = "file") MultipartFile[] file,HttpServletRequest request) {
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		picList= new ArrayList();
		String jsbh = userinfo.getJsbh();
		System.err.println("长度："+file.length);
		for(int i=0;i<file.length;i++) {
			Map<String,Object> picMap = new HashMap<>();
			byte[] file_buff = null;
            int len1;
			try {
				InputStream inputStream = file[i].getInputStream(); 
				System.err.println("数据长度"+inputStream.available());
				  if(inputStream!=null){
					  len1 = inputStream.available();
					  file_buff = new byte[len1];
					  inputStream.read(file_buff);
					  Map<String, Object> add1 = logsService.upload(file[i], jsbh,"","1","1","1");
					  picMap.put("byte", file_buff);
					  picMap.put("address", add1.get("data").toString());
					  picList.add(picMap);
				  }
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return picList;
	}
	
	/*
	 * 查找jsapp
	 */
	@RequestMapping(value = "/listJsapp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage listJsapp(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String jsbh="";
		if (userinfo != null) {
			jsbh = userinfo.getJsbh();
		}
		String appcode = request.getParameter("appcode");
		
		QueryParam params=new QueryParam();
		params.and("appcode", appcode);
		if(!StringUtils.isNullOrEmpty(jsbh)) {
			params.and("jsbh", jsbh);
		}
		return ResponseMessage.ok(managerService.listJsApp(params));
	}
	
	/**
	 * 安装应用
	 */
	@RequestMapping(value = "/addJsapp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addJsapp(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String jsbh = userinfo.getJsbh();
		String appcode = request.getParameter("appcode");
		JsappModel jsapp = new JsappModel();
		
		jsapp.setJsbh(jsbh);
		jsapp.setAppcode(appcode);
		jsapp.setCreator(userinfo.getLoginname());
		jsapp.setCreatetime(new Date());
		return ResponseMessage.ok(managerService.addJsapp(jsapp));
	}
	
	/**
	 * 卸载应用
	 */
	@RequestMapping(value = "/delJsapp", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage delJsapp(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String jsbh = userinfo.getJsbh();
		String appcode = request.getParameter("appcode");
		
		return ResponseMessage.ok(managerService.delJsApp(appcode, jsbh));
	}

	@GetMapping("/getTree")
	public List<Map<String,Object>> getTree(HttpServletRequest request){
		return managerService.getTree();
	}
	@GetMapping("/getApiTree")
	public List<Map> getApiTree(HttpServletRequest request){
		QueryParam qparam = new QueryParam();
		String node = request.getParameter("node");
		String pid = request.getParameter("pid");
		String appcode = request.getParameter("appcode");
		//node为空查询子节点
		if(awd.framework.common.utils.StringUtils.isNullOrEmpty(node)) {
			List<Map> list = new ArrayList<>();
			qparam.and("interfaceType", pid);
//			try {
//				ResponseMessage<PagerResult<InterfaceModel>> res1 = managerService.getinterfaceByAppcode(appcode, "2", "0", "1000");
//				ResponseMessage<PagerResult<InterfaceModel>> res2 = managerService.getinterfaceByAppcode(appcode, "1", "0", "1000");
//				for(InterfaceModel m :res2.getResult().getData()) {
//					qparam.and("id",TermType.not,m.getId());
//				}
//				for(InterfaceModel m :res1.getResult().getData()) {
//					qparam.and("id",TermType.not,m.getId());
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
			qparam.setPageSize(1000);
			ResponseMessage<PagerResult<InterfaceModel>> res = managerService.getInterface(qparam);
			for(InterfaceModel model:res.getResult().getData()) {
					Map<String,Object> map = new HashMap<>();
					map.put("pid", pid);
					map.put("isParent", "false");
					map.put("id", model.getId());
					map.put("name", model.getInterfaceName());
					list.add(map);
			}
			System.err.println("listSize--------------"+list.size());
			return list;
		}else {
		//不为空查询父节点
			List<Map> list = new ArrayList<>();
			List l = managerService.getApiTreeNode();
			for(int i=0;i<l.size();i++) {
				Map<String,Object> map = new HashMap<>();
				if("0".equals(l.get(i))) {
					map.put("pid", "0");
					map.put("isParent", "true");
					map.put("name", "监管业务指导部门");
					list.add(map);
				}else if("1".equals(l.get(i))){
					map.put("pid", "1");
					map.put("isParent", "true");
					map.put("name", "看守所");
					list.add(map);
					
				}else if("2".equals(l.get(i))){
					map.put("pid", "2");
					map.put("isParent", "true");
					map.put("name", "拘留所");
					list.add(map);
					
				}else if("3".equals(l.get(i))){
					map.put("pid", "3");
					map.put("isParent", "true");
					map.put("name", "强制戒毒所");
					list.add(map);
					
				}else if("4".equals(l.get(i))){
					map.put("pid", "4");
					map.put("isParent", "true");
					map.put("name", "收容教育所");
					list.add(map);
					
				}else if("5".equals(l.get(i))){
					map.put("pid", "5");
					map.put("isParent", "true");
					map.put("name", "安康医院");
					list.add(map);
					
				}else if("6".equals(l.get(i))){
					map.put("pid", "6");
					map.put("isParent", "true");
					map.put("name", "拘役所");
					list.add(map);
					
				}else if("9".equals(l.get(i))){
					map.put("pid", "9");
					map.put("isParent", "true");
					map.put("name", "其他");
					list.add(map);
					
				}
			}

			System.err.println("listSize22222----------"+list.size());
					return list;
		}
		}
	  
	/**
	 * api审批获取数据
	 */
	@RequestMapping(value = "/getinterfaceByAppcode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage getinterfaceByAppcode(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String appcode=request.getParameter("appcode");
		String num=request.getParameter("page");
		String end=request.getParameter("rows");
		String start=Integer.valueOf(num)*Integer.valueOf(end)-Integer.valueOf(end)+"";	
		return ResponseMessage.ok(managerService.getinterfaceByAppcode(appcode, "1", start, end));
	}
	
	/**
	 * api审批通过
	 */
	@RequestMapping(value="/updateInterfaceBdByCode",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMessage updateInterfaceBdByCode(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String appcode=request.getParameter("appcode");
		String bdzt=request.getParameter("bdzt");
		String[] interface_id=request.getParameterValues(("interface_id[]"));
		return ResponseMessage.ok(managerService.updateInterfaceBdByCode(bdzt, appcode, interface_id));
	}


	/**
	 * 通过appcode查询当前应用api待审核数
	 * @param request
	 * @return
	 */
	@GetMapping("/apiDshsl")
	public List<Integer> apiDshslQuery(HttpServletRequest request){
		String[] appcode = request.getParameterValues("appcode[]");
		return managerService.apiDshslQuery(appcode);
	}
	/**
	 * 添加系统参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addSetting", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<?> addSetting(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String jsbh = userinfo.getJsbh();
		String appcode = request.getParameter("appcode");
		String setting = request.getParameter("setting");
		
		Map<String, Object> map = Maps.newHashMap();
		//List<? extends Map> settingList = new ArrayList<>();
		List<Map<String,Object>> settingList = new ArrayList<>();
		try {
			settingList = (List<Map<String, Object>>) JSONArray.parseArray(setting, map.getClass());
			//settingList = JSONUtil.toList(setting, map.getClass());
		} catch (Exception e) {
			return ResponseMessage.error("JSON解析失败！");
		}
		System.err.println("settingList==="+JSON.toJSONString(settingList));
		
		ResponseMessage<String> result = null;
		
		/*if(settingList.size()>0) {
			for(int i=0;i<settingList.size();i++) {
				String progroup = (String) settingList.get(i).get("progroup");
				String protype = (String) settingList.get(i).get("protype");
				String proname = (String) settingList.get(i).get("proname");
				String prolist = (String) settingList.get(i).get("prolist");
				String provalue = (String) settingList.get(i).get("provalue");
				result = managerService.settingInit(appcode, progroup, protype, proname, prolist, provalue, userinfo.getLoginname());
			}
		}*/
		
		try {
			result = managerService.settingListInit(appcode, settingList, userinfo.getLoginname());
			if (result.getStatus() == 200) {
				return result;
			}else {
				return ResponseMessage.error(result.getMessage()==""?"系统设置添加失败！":result.getMessage());
			}
		} catch (Exception e) {
			return ResponseMessage.error("系统设置添加失败！");
		}
	}
	
	/**
	 * 保存单个应用菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addMenus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<?> addMenus(HttpServletRequest request){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String appcode = request.getParameter("appcode");
		String menus = request.getParameter("menus");
		Map<String, Object> map = Maps.newHashMap();
		//List<? extends Map> menuList = new ArrayList<>();
		List<Map<String,Object>> menuList = new ArrayList<>();
		try {
			menuList = (List<Map<String, Object>>) JSONArray.parseArray(menus, map.getClass());
			//menuList = JSONUtil.toList(menus, map.getClass());
		} catch (Exception e) {
			return ResponseMessage.error("JSON解析失败！");
		}
		System.err.println("menuList==="+JSON.toJSONString(menuList));
		ResponseMessage<String> result = null;
		/*if(menuList.size()>0) {
			for(int i=0;i<menuList.size();i++) {
				String menu = (String) menuList.get(i).get("menu");
				String parent = (String) menuList.get(i).get("parent");
				String menuname = (String) menuList.get(i).get("menuname");
				String url = (String) menuList.get(i).get("url");
				result = managerService.menusSave(appcode, menu, parent, menuname, url, userinfo.getLoginname(), Calendar.getInstance().getTime());
			}
		}*/
		
		try {
			result = managerService.addMenuList(appcode, menuList, userinfo.getLoginname());
			if (result.getStatus() == 200) {
				return result;
			}else {
				return ResponseMessage.error(result.getMessage()==""?"菜单添加失败！":result.getMessage());
			}
		} catch (Exception e) {
			return ResponseMessage.error("菜单添加失败！");
		}
	}
	
	/**
	 * 已存在接口查询
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/getInterface")
	public ResponseMessage<?> getInterface(HttpServletRequest request){
		String pid = request.getParameter("pid");
		String description = request.getParameter("description");
		String id = request.getParameter("id");
		String tablename = request.getParameter("tablename");
		String appcode = request.getParameter("appcode");
		String row = request.getParameter("rows");
		String page = request.getParameter("page");
		Map<String,Object> map = new HashMap<>();
		map.put("description",description);
		map.put("interfaceType",pid);
		map.put("id",id);
		map.put("appcode",appcode);
		map.put("tablename",tablename);
		map.put("start",(Integer.parseInt(page)-1)*(Integer.parseInt(row)));
		map.put("end",Integer.parseInt(row));
//		QueryParam params = new QueryParam();
//		params.and("interfaceType", pid);
//		params.setPageIndex(Integer.parseInt(page)-1);
//		params.setPageSize(Integer.parseInt(row));
//		try {
//			ResponseMessage<PagerResult<InterfaceModel>> res1 = managerService.getinterfaceByAppcode(appcode, "2", "0", "1000");
//			ResponseMessage<PagerResult<InterfaceModel>> res2 = managerService.getinterfaceByAppcode(appcode, "1", "0", "1000");
//			for(InterfaceModel m :res1.getResult().getData()) {
//				params.and("id",TermType.not,m.getId());
//			}
//			for(InterfaceModel m :res2.getResult().getData()) {
//				params.and("id",TermType.not,m.getId());
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if(!StringUtils.isNullOrEmpty(id) && !"undefined".equals(id)) {
//			params.and("id", id);
//		}
//
//		params.and("interfaceType", pid);
//		ResponseMessage<PagerResult<InterfaceModel>> interfaces = managerService.getInterface(params);
//		return managerService.getInterface(params);
		return managerService.getInterfaceAndBdzt(map);
	}
	
	/**
	 * 保存用户申请的接口
	 * @param request
	 * @return
	 */
	@PostMapping("/saveInterfaceBinding")
	public ResponseMessage<String> saveInterfacebinding(HttpServletRequest request){
		String num = request.getParameter("num");
		List<Map<String,Object>> list = new ArrayList<>();
		if(!"null".equals(num)) {
			for(int i =0 ;i<Integer.parseInt(num);i++) {
				Map<String,Object> map = new HashMap<>();
				String [] str = request.getParameterValues("value["+i+"][]");
				map.put("id", str[0]);
				map.put("appcode", str[1]);
				list.add(map);
			}
		}else {
			Map<String,Object> map = new HashMap<>();
			String [] str = request.getParameterValues("value[]");
			map.put("id", str[0]);
			map.put("appcode", str[1]);
			list.add(map);
		}
		
		return managerService.saveInterfacebinding(list);

	}

	/**
	 * 取消aip申请后删除interfacebinding表中数据
	 * @param request
	 * @return
	 */
	@GetMapping("/deleteInterfaceBinding")
	public ResponseMessage<String> deleteInterfaceBinding(HttpServletRequest request){
		String ibdId = request.getParameter("ibdId");
		return managerService.deleteInterfaceBinding(ibdId);
	}
	
}
