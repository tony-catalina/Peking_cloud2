package awd.mis.appstore.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.mis.appstore.api.hystrix.ManagerServiceHystrix;
import awd.mis.appstore.model.AppModel;
import awd.mis.appstore.model.ApplunboModel;
import awd.mis.appstore.model.InterfaceModel;
import awd.mis.appstore.model.InterfacebindingModel;
import awd.mis.appstore.model.JsappModel;
import awd.mis.appstore.model.MenusModel;
import awd.mis.appstore.model.PublishModel;
import awd.mis.appstore.model.SettingModel;
import awd.mis.appstore.model.UserinfoModel;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;



@FeignClient(name = "${awd.api.manager:AWD-MIS-MANAGER-SERVER}",url = "192.168.4.61:12102",fallbackFactory=ManagerServiceHystrix.class)


public interface ManagerService {
	
	/**
	 * 登录查询
	 */
	@PostMapping("/userinfo/findByNameAndPass")	
	ResponseMessage<UserinfoModel> findByNameAndPass(@RequestParam(value="jsbh")String jsbh, @RequestParam(value="loginname")String name, @RequestParam(value="password")String pwd);
	
	/**
	 * 根据id 获取用户信息信息
	 */
	@RequestMapping("/userinfo/{id}")
	ResponseMessage<UserinfoModel> getUserByid(@PathVariable("id")String id);
	
	/**
	 * 根据id 获取用户信息信息
	 */
	@GetMapping("/userinfo")
	ResponseMessage<PagerResult<UserinfoModel>> listUsers(QueryParam params);
	
	/**
	 * 保存用户信息信息
	 */
	@PostMapping("/userinfo/saveUser")
	ResponseMessage addUser(@RequestBody UserinfoModel userinfoModel);
	
	/**
	 * 根据id 更新用户信息信息
	 */
	@PutMapping("/userinfo/{id}")
	ResponseMessage updateUser(@RequestParam(value="id") String id ,@RequestBody UserinfoModel userinfoModel);
	
	/**
	 * 根据id 删除user(将state改为R3)
	 */
	@DeleteMapping("/userinfo/{id}")
	ResponseMessage<String> deleteUser(@RequestParam(value="id") String id ,@RequestBody UserinfoModel userinfoModel);
	
	
	/**
	 * 根据id 获取app信息
	 */
	@GetMapping("/app/{id}")
	ResponseMessage<AppModel> getAppByid(@PathVariable("id")String id);

	/**
	 * 根据code 获取app信息
	 */
	@GetMapping("/app/selectSingle")
	ResponseMessage<AppModel> querySingle(QueryParam params);
	
	/**
	 * 新增app
	 */
	@PostMapping("/app") 
	public ResponseMessage<String> saveApp(@RequestBody AppModel appModel);
	
	/**
	 * 根据id 如果没有新建有就更新
	 */
	@PatchMapping("/app") 
	public ResponseMessage<String> saveOrUpdateApp(@RequestBody AppModel appModel);
	
	/**
	 * 根据id 更新app
	 * @param id
	 * @param appModel
	 * @return
	 */
	@PutMapping("/app/{id}")
	public ResponseMessage<String> updateApp(@RequestParam(value="id") String id ,@RequestBody AppModel appModel);

	/**
	 * 根据参数查询所有app
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/app",method=RequestMethod.GET)
	ResponseMessage<PagerResult<AppModel>> listApp(QueryParam params);
	
	/**
	 * 发布应用
	 */
	@PostMapping(value="/publish")
	public ResponseMessage<String> save(@RequestBody PublishModel publish);
	
	/**
	 * 根据参数查询publish
	 */
	@GetMapping(value="/publish")
	public ResponseMessage<PagerResult<PublishModel>> listPublish(QueryParam params);
	
	/**
	 * 根据id 更新publish
	 * @param id
	 * @param publish
	 * @return
	 */
	@PutMapping("/publish/{id}")
	public ResponseMessage<String> updatePublish(@RequestParam(value="id") String id ,@RequestBody PublishModel publish);
	
	/*
	 * 根据appcode删除jsapp
	 */
	@PostMapping("/jsapp/delJsApp")
	public ResponseMessage delJsApp(@RequestParam(value="appcode") String appcode ,@RequestParam(value="jsbh") String jsbh);
	
	/**
	 * 安装应用
	 * @param jsapp
	 * @return
	 */
	@PostMapping(value="/jsapp")
	public ResponseMessage<String> addJsapp(@RequestBody JsappModel jsapp);
	
	/**
	 * 根据参数查询jsapp
	 */
	@GetMapping(value="/jsapp")
	public ResponseMessage<PagerResult<JsappModel>> listJsApp(QueryParam params);
	
	@GetMapping("/applunbo/getURL")
	public ResponseMessage<List<ApplunboModel>> getFourURL(QueryParam params);
	
	@PostMapping("/applunbo/setLunBoApp")
	public ResponseMessage<String> setLunBoApp(@RequestParam("appcode") String appcode,@RequestParam("username")String username);
	
	@PostMapping("/applunbo/insertLunbo")
	public ResponseMessage<String> insertLunbo(@RequestBody ApplunboModel applunboModel);
	
	@PostMapping("/jsapp/getAppEntyListByJsbh")
	public ResponseMessage<PagerResult<AppModel>> getAppEntyListByJsbh(
			@RequestParam(value="pageIndex") String pageIndex,
			@RequestParam(value="pageSize") String pageSize,
			@RequestParam(value="jsbh") String jsbh);
	
	/**根据type获取监所名
	 * @param type
	 * @return
	 */
	@GetMapping(value="/groups/getJsTypeByGroups")
	public ResponseMessage<List<Map<String, Object>>> getJsTypeByGroups(@RequestParam("type") String type);
	
	
	/**
	 * 提供已存在接口查询功能
	 * @param params
	 * @return
	 */
	@GetMapping("/interface")
	public ResponseMessage<PagerResult<InterfaceModel>> getInterface(QueryParam params);

	/**
	 *获取接口信息与appcode对应的接口绑定状态
	 * @param map
	 * @return
	 */
	@PostMapping("/interface/selectInterfaceAndBdzt")
	public ResponseMessage<PagerResult<InterfaceModel>> getInterfaceAndBdzt(@RequestBody Map<String,Object> map);
	/**
	 * 提供app已绑定的查询接口
	 * @param params
	 * @return
	 */
	@GetMapping("/interfacebinding")
	public ResponseMessage<InterfacebindingModel> getInterfacebinding(QueryParam params);
	/**
	 * 用户申请api接口
	 * @param nodel
	 * @return
	 */
	@PostMapping("/interfacebinding/saveinterfacebinding")
	public ResponseMessage<String> saveInterfacebinding(@RequestBody List<Map<String,Object>> list);

	@DeleteMapping("/interfacebinding/{id}")
	public ResponseMessage<String> deleteInterfaceBinding(@RequestParam(value = "id") String id);
	/**
	 * 提供菜单添加功能
	 * @param appcode
	 * @param menu
	 * @param fname
	 * @param name
	 * @param url
	 * @param creator
	 * @param createtime
	 * @return
	 */
	@RequestMapping(value="/menus/add",method=RequestMethod.POST)
	 public ResponseMessage<String> menusSave(@RequestParam("appcode")String appcode, @RequestParam("menu")String menu, @RequestParam("fname")String fname, @RequestParam("name")String name, @RequestParam("url")String url,
	    		@RequestParam("creator")String creator, @RequestParam("createtime")Date createtime);
	
	@RequestMapping(value="/menus/addMenuList",method=RequestMethod.POST)
	public ResponseMessage<String> addMenuList(@RequestParam("appcode")String appcode, 
			@RequestBody List<Map<String, Object>> menuList, 
			@RequestParam("creator")String creator);

	
	
	/**
	 * 提供用户应用设置添加功能
	 * @param aPPCODE
	 * @param progroup
	 * @param type
	 * @param key
	 * @param pronamezh
	 * @param value
	 * @param creator
	 * @return
	 */
	@RequestMapping(value="/setting/add",method=RequestMethod.POST)
	 public ResponseMessage<String> settingInit(@RequestParam("appcode")String aPPCODE, @RequestParam("progroup")String progroup, @RequestParam("type")String type, @RequestParam("proname")String key,@RequestParam("pronamezh")String pronamezh,
	                                               @RequestParam("provalue")String value, @RequestParam("user")String creator);
	
	@RequestMapping(value="/setting/settingListInit",method=RequestMethod.POST)
	public ResponseMessage<String> settingListInit(@RequestParam("appcode")String appcode, 
			@RequestBody List<Map<String, Object>> settingListInit, 
			@RequestParam("user")String creator);
	
	
	/**
	 * 查询菜单接口
	 * @param param
	 * @return
	 */
	@GetMapping("/menus")
	public ResponseMessage<PagerResult<MenusModel>> getMenus(QueryParam param);
	
	/**
	 * 查询系统参数接口
	 * @param param
	 * @return
	 */
	@GetMapping("/setting")
	public ResponseMessage<PagerResult<SettingModel>> getSetting(QueryParam param);
	
	/**
	 * 管理员审批用户申请的api
	 * @param bdzt
	 * @param appcode
	 * @param interface_id
	 * @return
	 */
	@PostMapping("/interfacebinding/updateInterfaceBdByCode")
	public ResponseMessage<String> updateInterfaceBdByCode(@RequestParam("bdzt")String bdzt, @RequestParam("appcode")String appcode,String[] interface_id);

	/**
	 * 应用中api接口待审核数量查询
	 * @param appcode
	 * @return
	 */
	@GetMapping("/interface/apiDshslQuery")
	public List<Integer> apiDshslQuery(String[] appcode);
	
	/**
	 * 根据id 更新interface_binding
	 * @param id
	 * @param appModel
	 * @return
	 */
	@PutMapping("/interfacebinding/{id}")
	public ResponseMessage<String> updateInterfacebinding(@RequestParam(value="id") String id ,@RequestBody InterfacebindingModel model);
	
	/**
	 * 根据appcode查询绑定的api的详细参数
	 * @param appcode
	 * @param bdzt
	 * @return
	 */
	@PostMapping("/interface/getinterfaceByAppcode")
	public ResponseMessage<PagerResult<InterfaceModel>> getinterfaceByAppcode(@RequestParam(value="appcode") String appcode,@RequestParam(value="bdzt") String bdzt,@RequestParam("start")String start,@RequestParam("end")String end);
	
	/**
	 * 
	 * 获取api树型结构父节点种类
	 * @return
	 */
	@GetMapping("/interface/getApiTreeNode")
	public List getApiTreeNode();

	@GetMapping("/interface/getTree")
	public List<Map<String,Object>> getTree();
}
