package awd.mis.desktop.controller;

import awd.framework.common.utils.StringUtils;
import awd.mis.desktop.api.KssServerService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.*;
import awd.mis.desktop.service.SystemService;
import awd.mis.desktop.tools.CacheKeyGenerator;
import awd.mis.desktop.tools.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import cn.hutool.core.util.PinyinUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ace.cache.annotation.Cache;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.*;


@RestController
public class SystemController {	

	@Autowired
	private SystemService systemService;

	@Autowired
	private ManagerService managerService;
	@Autowired
	private KssServerService kssServerService;

	@RequestMapping(value ="/system_group/get",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getGroup(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			result.setCode("true");
			result.setData(systemService.getGroup(currentUser));
			return result;			
	}
	
	@RequestMapping(value ="/system_role/get",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getRole(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			result.setCode("true");
			result.setData(systemService.getRole(currentUser));
			return result;			
	}
	
	@RequestMapping(value ="/system_role/getAll",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getAllRole(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			result.setCode("true");
			result.setData(systemService.getRole());
			result.setInfo("查询成功！");
			return result;			
	}
	
	@PostMapping(value ="/system_role/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  addRole(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			String jslx=URLDecoder.decode(request.getParameter("jslx"));
			String code=URLDecoder.decode(request.getParameter("code"));
			String name=URLDecoder.decode(request.getParameter("name"));
			result.setCode(systemService.addRole(jslx,code,name)?"true":"false");
			result.setInfo("保存成功！");
			return result;			
	}
	
	@GetMapping(value ="/system_role/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  delRole(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String id=request.getParameter("id");
			ResultMap result=new ResultMap();
			result.setCode(systemService.delRole(id)?"true":"false");
			result.setInfo("删除成功");
			return result;			
	}
	
	@GetMapping(value ="/system_role/roleflow",produces = "application/json;charset=UTF-8")
	@ResponseBody
	@Cache(key="soa_web_platform{2}",expire=5,generator=CacheKeyGenerator.class,parser=ResultCacheResultParser.class)
	public ResultMap  getRoleFlow(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			//System.out.println(JSONUtil.toJsonStr(request.getParameterMap()));
			String role =request.getParameter("role");
			result.setCode("true");
			result.setData(systemService.getRoleFlow(currentUser,role));
			result.setInfo("查询成功");
			return result;			
	}
	@PostMapping(value ="/system_role/roleNodeSet",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  roleNodeSet(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			String role =request.getParameter("role");
			String[] nodes=request.getParameterValues("ids[]");
			Map<String, Object> data=systemService.setRoleNode(currentUser,role,nodes);
			result.setCode((boolean) data.get("code")?"true":"false");
			result.setData(data.get("data"));
			return result;			
	}
	
	
	
	
	@RequestMapping(value ="/system_member/get",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getMember(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			String group_id=request.getParameter("group_id");
			System.err.println(group_id);
			result.setCode("true");
			result.setData(systemService.getMember(currentUser,group_id));
			return result;			
	}
	
	@PostMapping(value ="/system_group/add",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  addGroup(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name=request.getParameter("name");
			String parent_id=request.getParameter("parent_id");
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.addGroup(currentUser,name,parent_id);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	@PostMapping(value ="/system_group/forbidmenu",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  forbidMenu(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String app=request.getParameter("app");
			String group=request.getParameter("group");
			String menu=request.getParameter("menu");
			String type=request.getParameter("type");
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.forbidGroupMenu(currentUser,app,group,menu,type);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	@PostMapping(value ="/system_group/edit",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  editGroup(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.editGroup(currentUser,id,name);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	
	@PostMapping(value ="/system_group/del",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  delGroup(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String group_id=request.getParameter("group_id");	
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.delGroup(currentUser,group_id);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	@PostMapping(value ="/system_group/getGroupSetting",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getGroupSetting(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String group_id=request.getParameter("group_id");
			String appid=request.getParameter("appid");	
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.getGroupSetting(currentUser,group_id,appid);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	
	@PostMapping(value ="/system_member/add",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  addUser(ModelMap model,UserinfoModel user,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			String name=URLDecoder.decode(request.getParameter("name"));
			String password=URLDecoder.decode(request.getParameter("password")!=null?request.getParameter("password"):"");
			String group=URLDecoder.decode(request.getParameter("group")!=null?request.getParameter("group"):"");
			String isadmin=URLDecoder.decode(request.getParameter("glybz"));
			String role=URLDecoder.decode(request.getParameter("role"));
			String email = request.getParameter("email")!=null?request.getParameter("email"):"";
			String sfzh = request.getParameter("sfzh")!=null?request.getParameter("sfzh"):"";
			String jh = request.getParameter("jh")!=null?request.getParameter("jh"):"";
			String imports=URLDecoder.decode(request.getParameter("isImport")==null?"":request.getParameter("isImport"));
			List<UserinfoModel> userlist=new ArrayList<UserinfoModel>();
			if("1".equals(imports)) {
//				String[] names=null;
//				if(name.contains(",")) {
//					names=name.split(",");
//				}else if(name.contains("，")){
//					 names=name.split("，");
//				}
				String[] names = name.split("[, ，]");
				System.err.println(names.length);
				for (String name1 : names) {
					UserinfoModel user1 = new UserinfoModel();
					System.err.println(name1);
					user1.setLoginname(name1);
					if (password!=""){
						user1.setLoginpass(password);
					}
					user1.setGlybz(isadmin);
					user1.setEmail(email);
					userlist.add(user1);
					System.err.println("list="+JSONObject.toJSONString(userlist));
				} 				
			}else {
				user.setLoginname(name);
				if (password!=""){
					user.setLoginpass(password);
				}
				user.setGlybz(isadmin);
				user.setEmail(email);
				userlist.add(user);
			}
			Map<String, Object> data=systemService.addUser(currentUser,userlist,role,group);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	@PostMapping(value ="/system_member/do_action",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  userAction(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String action=request.getParameter("action");
			String userids=request.getParameter("user_id");			
			Object[] obs=JSONUtil.parseArray(userids).toArray();
			String[] ids=new String[obs.length];
			for (int i=0;i<obs.length;i++) {
				ids[i]=obs[i].toString();
			}
			String param=request.getParameter("param");	
			ResultMap result=new ResultMap();
			Map<String, Object> data=systemService.userAction(currentUser,action,ids,param);
			result.setCode(data.get("code").toString());
			result.setData(data.get("data"));
			return result;			
	}
	
	@GetMapping(value="/system_dictionary/getType")
	@ResponseBody
	public ResultMap  getDicType(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		result.setCode("true");
		result.setData(systemService.getDictionaryType());
		return result;	
	}
	
	@GetMapping(value="/system_dictionary/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		String dictionaryType=request.getParameter("field");
		String search=request.getParameter("search");
		PagerResult<DictionaryModel> pageResult=systemService.getPage(page,limit,dictionaryType,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	@PostMapping(value="/system_dictionary/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  dictionarySave(ModelMap model,DictionaryModel dictionary,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String dictionaryType=request.getParameter("field");
		String search=request.getParameter("search");
		result.setCode("true");
		if("0".equals(dictionary.getJslx())) {
			dictionary.setEditable("0");
			dictionary.setIsgb("0");
		}else {
			dictionary.setEditable("1");
			dictionary.setIsgb("1");
		}	
		dictionary.setContent(URLDecoder.decode(dictionary.getContent()));
		dictionary.setPy(PinyinUtil.getAllFirstLetter(dictionary.getContent()));
		dictionary.setCreator(currentUser.getName());
		result.setData(systemService.dictionarySave(dictionary));		
		return result;
	}
	@PostMapping(value="/system_dictionary/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  dictionaryDelete(ModelMap model,DictionaryModel dictionary,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("ids[]");
		result.setCode("true");
		result.setData(systemService.dictionaryDelete(ids));	
		result.setInfo("删除成功！");
		return result;
	}
	
	@PostMapping(value="/system_dictionary/modify",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  dictionaryModify(ModelMap model,DictionaryModel dictionary,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		String ids=request.getParameter("ids");
		String modify=request.getParameter("modify");
		List<Map> list = new ArrayList<>();
		list = JSONArray.parseArray(ids, Map.class);
		result.setCode("true");
		result.setData(systemService.dictionaryModify(list,modify));	
		result.setInfo("设置成功！");
		return result;
	}
	
	
	@GetMapping(value="/system_wgzd/getType",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  getWgzdType(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		result.setCode("true");
		result.setData(systemService.getWgzdType());
		return result;	
	}
	
	
	@PostMapping(value="/system_wgzd/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  wgzdSave(ModelMap model,WgzdModel wgzd,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String dj=URLDecoder.decode(wgzd.getDj());
		if("轻微违规".equals(dj))dj="1";
		if("一般违规".equals(dj))dj="2";
		if("严重违规".equals(dj))dj="3";
		if("特别严重违规".equals(dj))dj="4";
		result.setCode("true");
		wgzd.setCreator(currentUser.getName());
		wgzd.setPy(PinyinUtil.getAllFirstLetter(URLDecoder.decode(wgzd.getContent())).toUpperCase());
		wgzd.setContent(URLDecoder.decode(wgzd.getContent()));
		wgzd.setState("R2");
		wgzd.setDj(dj);
		boolean success=systemService.wgzdSave(wgzd);
		result.setData(success?"保存成功":"保存失败");	
		result.setInfo(success);
		return result;
	}
	
	@PostMapping(value="/system_wgzd/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  wgzdDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("ids[]");
		result.setCode("true");
		Map<String, Object> data=systemService.wgzdDelete(ids);
		result.setCode(data.get("code")=="true"?"true":"false");
		result.setData(data.get("data"));
		result.setInfo(data.get("data"));
		return result;
	}
	
	@GetMapping(value="/system_wgzd/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getwgzdPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		String dictionaryType=request.getParameter("field");
		String search=request.getParameter("search");
		PagerResult<WgzdModel> pageResult=systemService.getwgzdPage(page,limit,dictionaryType,search);
		System.err.println("---------------------"+pageResult);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_wgzd/modify",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  wgzdModify(ModelMap model,WgzdModel wgzdModel,HttpServletRequest request,HttpServletResponse response) {
		ResultMap result=new ResultMap();
		String ids=request.getParameter("ids");
		String modify=request.getParameter("modify");
		List<Map> wgzd = new ArrayList<>();
		wgzd = JSONArray.parseArray(ids, Map.class);
		result.setCode("true");
		result.setData(systemService.wgzdModify(wgzd,modify));	
		result.setInfo("设置成功！");
		return result;
	}
	
	@GetMapping(value="/system_flowmap/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getflowmapPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		String search=request.getParameter("search");
		PagerResult<FlowmapModel> pageResult=systemService.getFlowmapPage(page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@GetMapping(value="/system_flownode/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getflownodePage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		String flowmapid=request.getParameter("flowmapid");
		String search=request.getParameter("search");
		PagerResult<FlownodeModel> pageResult=systemService.getFlownodePage(page,limit,search,flowmapid);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_flownode/bindmenu",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap flownodeBindmenu(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=request.getParameter("id");
		String menu=request.getParameter("menu");
		ResultMap result=new ResultMap();		
		result.setCode("true");
		result.setData(systemService.flownodeBindmenu(id,menu));	
		result.setInfo("保存成功！");
		return result;	
	}
	
	@PostMapping(value="/system_flowmap/action",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap flowmapAction(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=request.getParameter("id");
		String field=request.getParameter("field");
		String value=request.getParameter("value");
		ResultMap result=new ResultMap();		
		result.setCode("true");
		result.setData(systemService.flowmapAction(id,field,value));	
		result.setInfo("保存成功！");
		return result;	
	}
	
	@GetMapping(value="/system_classfic/getAll",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getClassficAll() {
		ResultMap result=new ResultMap();		
		result.setCode("true");
		result.setData(systemService.getClassficTree());	
		result.setInfo("查询成功！");
		return result;	
	}
	
	@GetMapping(value="/system_flowmap/getAllTree",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getFlowmapTree() {
		ResultMap result=new ResultMap();		
		result.setCode("true");
		result.setData(systemService.getFlowMapTree());	
		result.setInfo("查询成功！");
		return result;	
	}
	
	
	@PostMapping(value="/system_classfic/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap classficSave(ClassficModel classfic) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		classfic.setCreator(currentUser.getName());
		ResultMap result=new ResultMap();		
		result.setCode("true");
		result.setData(systemService.classficSave(classfic));	
		result.setInfo("保存成功！");
		return result;	
	}
	@GetMapping(value="/system_classfic/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getClassficPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		String parentid=request.getParameter("field");
		String search=URLDecoder.decode(request.getParameter("search")==null?"":request.getParameter("search"));
		PagerResult<ClassficModel> pageResult=systemService.getClassficPage(page,limit,parentid,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_classfic/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  classficDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("ids[]");
		Map<String, Object> data=systemService.classficDelete(currentUser,ids);
		result.setCode(data.get("code")=="true"?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@GetMapping(value="/system_jq/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getJqPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		
		String search=StrUtil.isBlank(request.getParameter("search"))?"":request.getParameter("search");
		PagerResult<Map<String, Object>> pageResult=systemService.getJqPage(currentUser.getJsbh(),page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_jq/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  jqSave(ModelMap model,JqModel jq,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		Map<String, Object> data=systemService.jqSave(currentUser,jq);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value="/system_jq/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  jqDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("id[]");
		System.err.println(JSONObject.toJSONString(ids));
		Map<String, Object> data=systemService.jqDelete(currentUser,ids);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	
	@GetMapping(value="/system_js/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getJsPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		
		String search=StrUtil.isBlank(request.getParameter("search"))?"":request.getParameter("search");
		PagerResult<JsModel> pageResult=systemService.getJsPage(currentUser.getJsbh(),page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_js/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  jsSave(ModelMap model,JsModel js,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String jqh = request.getParameter("jqh");
		QueryParam params = new QueryParam();
		params.and("jqh", jqh);
		params.and("jsbh",currentUser.getJsbh());
		ResponseMessage<PagerResult<JqModel>> jq = kssServerService.jqQuery(params);
		ResultMap result=new ResultMap();
		if(jq.getResult().getTotal() != 0) {
			Map<String, Object> data=systemService.jsSave(currentUser,js);
			result.setCode((boolean) data.get("code")?"true":"false");
			result.setData(data.get("data"));		
			return result;
		}else {
			Map<String, Object> re=new HashMap<>();
			re.put("code", false);
			re.put("data", "没有该监区,请先新建监区");
			result.setCode((boolean) re.get("code")?"true":"false");
			result.setData(re.get("data"));		
			return result;
			
		}
		
	}
	
	@PostMapping(value="/system_js/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  jsDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("id[]");
		Map<String, Object> data=systemService.jsDelete(currentUser,ids);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@GetMapping(value="/system_fjsz/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getFjszPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		
		String search=StrUtil.isBlank(request.getParameter("search"))?"":request.getParameter("search");
		PagerResult<FjszModel> pageResult=systemService.getFjszPage(currentUser.getJsbh(),page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_fjsz/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  fjszSave(ModelMap model,FjszModel fjsz,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		Map<String, Object> data=systemService.fjszSave(currentUser,fjsz);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value="/system_fjsz/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  fjszDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("id[]");
		Map<String, Object> data=systemService.fjszDelete(currentUser,ids);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@GetMapping(value="/system_bar/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  getBarPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		
		String search=StrUtil.isBlank(request.getParameter("search"))?"":request.getParameter("search");
		PagerResult<BarModel> pageResult=systemService.getBarPage(currentUser.getJsbh(),page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_bar/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  barSave(ModelMap model,BarModel bar,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		Map<String, Object> data=systemService.barSave(currentUser,bar);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value="/system_bar/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  barDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("id[]");
		Map<String, Object> data=systemService.barDelete(currentUser,ids);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));
		result.setInfo("删除成功！");
		return result;
	}
	
	@GetMapping(value="/system_lsgl/getPage",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagerResult  geLsPage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PagerResult result=new PagerResult();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		
		String search=StrUtil.isBlank(request.getParameter("search"))?"":request.getParameter("search");
		PagerResult<LsModel> pageResult=systemService.geLsPage(currentUser.getJsbh(),page,limit,search);
		result.setCode(0);
		result.setData(pageResult.getData());	
		result.setTotal(pageResult.getTotal());
		result.setMsg("查询成功");
		return result;	
	}
	
	@PostMapping(value="/system_ls/save",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  lsSave(ModelMap model,LsModel ls,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		Map<String, Object> data=systemService.lsSave(currentUser,ls);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value="/system_ls/delete",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  lsDelete(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String[] ids=request.getParameterValues("id[]");
		Map<String, Object> data=systemService.lsDelete(currentUser,ids);
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value = "/system_groupApp/getGroupApp", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getGroupApp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String groupId = request.getParameter("groupid");
		System.err.println("getGroupApp=="+groupId);
		String jsbh = currentUser.getJsbh();
		Map<String, Object> pageResult = systemService.getGroupApp(groupId, jsbh);
		ResultMap result = new ResultMap();		
		result.setCode("true");
		result.setInfo("查询成功！");
		result.setData(pageResult);		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());
		return result;
	}
	
	@PostMapping(value="/system_groupApp/saveGroupApp",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap groupAppSave(ModelMap model,BarModel bar,HttpServletRequest request,HttpServletResponse response) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String groupid=request.getParameter("groupid");
		String param=request.getParameter("param");
		String jsbh=currentUser.getJsbh();
		String creator = currentUser.getUsername();
		Map<String, Object> data=systemService.saveGroupApp(jsbh,creator,groupid,param);
		ResultMap result=new ResultMap();
		result.setCode((boolean) data.get("code")?"true":"false");
		result.setData(data.get("data"));		
		return result;
	}
	
	@PostMapping(value="/system_groupApp/changeUserGroup",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseMessage<String> changeUserGroup(HttpServletRequest request){
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userid = request.getParameter("userid");
		QueryParam qParam  = new QueryParam();
		qParam.and("jsbh",currentUser.getJsbh());
		qParam.and("fgroup","0");
		ResponseMessage<PagerResult<GroupsModel>> rm = managerService.groupsQuery(qParam);
		if(rm.getResult().getTotal() != 0){
			QueryParam 	param  = new QueryParam();
			param.and("jsbh",currentUser.getJsbh());
			param.and("userid",userid);	
			ResponseMessage<PagerResult<UsergroupModel>> um =managerService.userGroupQuery(param);
			if(um.getResult().getTotal() != 0 ){
				UsergroupModel ugm = um.getResult().getData().get(0);
				ugm.setGroupid(rm.getResult().getData().get(0).getId());
				managerService.userGroupUpdate(ugm.getId(),ugm);
				return ResponseMessage.ok("更新成功");
			}else{
				UsergroupModel ugm = new UsergroupModel();
				ugm.setUserid(userid);
				ugm.setJsbh(currentUser.getJsbh());
				ugm.setGroupid(rm.getResult().getData().get(0).getId());
				//用户不可能没有组，不写了
			//	managerService.userg
				return ResponseMessage.ok("用户组数据异常");
			}
			
		}else{
			return ResponseMessage.ok("更新失败");
		}
	}
	
	@RequestMapping(value = "system_jsbh/getJsTypeByGroups", method = RequestMethod.POST)
	public List<Map<String, Object>> getJsTypeByGroups(HttpServletRequest request) {	
		String type = request.getParameter("id");
		int t = 0;
		if(type !=null && type !="") {
			t = Integer.parseInt(type)-1;
		}
		ResponseMessage<List<Map<String, Object>>> list=managerService.getJsTypeByGroups(t+"");
		List<Map<String, Object>> list1 = list.getResult();
		List<Map<String, Object>> result = new ArrayList<>();
		if(list1.size()>0) {
			for(int i=0;i<list1.size();i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", list1.get(i).get("jsbh"));
				map.put("name", list1.get(i).get("name"));
				result.add(map);
			}
		}
		return result;
	}
	
	/**
	 * 新建用户时判断用户名，警号，身份证号是否已存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/system_member/judjeUserExit", method = RequestMethod.GET)
	public int judjeUserExit(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = request.getParameter("name");
		String jh = request.getParameter("jh");
		String sfzh = request.getParameter("sfzh");
		QueryParam qParam = new QueryParam();
		if(!StringUtils.isNullOrEmpty(name)) {
			qParam.and("loginname", name);
		}
		if(!StringUtils.isNullOrEmpty(jh)) {
			qParam.and("jh", jh);
		}
		if(!StringUtils.isNullOrEmpty(sfzh)) {
			qParam.and("sfzh", sfzh);
		}
		qParam.and("jsbh", currentUser.getJsbh());
		ResponseMessage<PagerResult<UserinfoModel>> rm  = managerService.userInfoQuery(qParam);
		
		return rm.getResult().getTotal();
		
	}
	
}
