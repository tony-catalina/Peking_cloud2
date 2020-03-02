package awd.mis.desktop.api;

import awd.mis.desktop.api.hystrix.ManagerFallBackFactory;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.*;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@FeignClient(name = "${awd.api.manager:AWD-MIS-MANAGER-SERVER}" ,fallbackFactory= ManagerFallBackFactory.class)

public interface ManagerService {
	
	
	/*******************************app**************************************/
	@RequestMapping(value="/app",method=RequestMethod.GET)
	ResponseMessage<PagerResult<AppModel>> appQuery(QueryParam params);
	
	@GetMapping("/app/getApplistWithJsappByJsbh")
	ResponseMessage<List<AppModel>> getApplistWithJsappByJsbh(QueryParam params);
	
	
	
	/********************************bar*************************************/
	@RequestMapping(value="/bar",method=RequestMethod.GET)
	ResponseMessage<PagerResult<BarModel>> barQuery(QueryParam params);
	
	@RequestMapping(value="/bar",method=RequestMethod.POST)
	ResponseMessage<String> barSave(@RequestBody BarModel bar);
	
	@RequestMapping(value="/bar/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String>  barDelete(@PathVariable("id")String id);
	
	@RequestMapping(value="/bar/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> barUpdate(@PathVariable("id")String id, @RequestBody BarModel bar);
	/********************************classfic*************************************/
	@RequestMapping(value="/classfic",method=RequestMethod.GET)
	ResponseMessage<PagerResult<ClassficModel>> classficQuery(QueryParam params);

	@RequestMapping(value="/classfic",method=RequestMethod.POST)
	ResponseMessage<String> classficAdd(@RequestBody ClassficModel classfic);
	
	@RequestMapping(value="/classfic/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> classficUpdate(@PathVariable("id")String id, @RequestBody ClassficModel classfic);
	
	@RequestMapping(value="/classfic/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> classficDelete(@PathVariable("id")String id);
	
	/********************************dictionary*************************************/
	@RequestMapping(value="/dictionary/getallfields",method=RequestMethod.GET)
	ResponseMessage<List<Map<String, Object>>> getDictionaryAllFields();
	
	@RequestMapping(value="/dictionary",method=RequestMethod.GET)
	ResponseMessage<PagerResult<DictionaryModel>> dictionaryQuery(QueryParam params);
	
	@RequestMapping(value="/dictionary",method=RequestMethod.POST)
	ResponseMessage<String> dictionarySave(@RequestBody DictionaryModel dictionary);
	
	@RequestMapping(value="/dictionary/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> dictionaryDelete(@PathVariable("id") String id);
	
	@RequestMapping(value="/dictionary/{id}",method=RequestMethod.GET)
	ResponseMessage<DictionaryModel> getDictionaryBykey(@PathVariable("id")String id);

	@RequestMapping(value="/dictionary/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> dictionaryUpdate(@PathVariable("id")String id,@RequestBody DictionaryModel dictionary);
	
	
	/********************************fieldinfo*************************************/
	/********************************flowmap*************************************/
	@RequestMapping(value="/flowmap",method=RequestMethod.GET)
	ResponseMessage<PagerResult<FlowmapModel>> flowMapQuery(QueryParam flowparam);
	
	@RequestMapping(value="/flowmap/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> flowmapUpdate(@PathVariable("id")String id, @RequestBody FlowmapModel flowmapModel);
	
	/********************************flownode*************************************/
	@RequestMapping(value="/flownode",method=RequestMethod.GET)
	ResponseMessage<PagerResult<FlownodeModel>> flowNodeQuery(QueryParam nodeparam);

	@RequestMapping(value="/flownode/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> flowNodeUpdate(@PathVariable("id")String id, @RequestBody FlownodeModel flownodeModel);

	/********************************fxpg*************************************/
	
	/********************************groupmenu*************************************/
	@RequestMapping(value="/groupmenus",method=RequestMethod.GET)
	ResponseMessage<PagerResult<GroupmenusModel>> groupMenuQuery(QueryParam groupmenuparam);
	
	@RequestMapping(value="/groupmenus",method=RequestMethod.POST)
	ResponseMessage<String> groupMenuAdd(@RequestBody GroupmenusModel groupmenusModel);
	
	@RequestMapping(value="/groupmenus/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> groupMenuDelete(@PathVariable("id")String id);
	
	/********************************group*************************************/
	@RequestMapping(value="/groups/getGroups",method=RequestMethod.GET)
	ResponseMessage<List<GroupsModel>> getGroupsByJsbh(@RequestParam("jsbh")String jsbh);

	@RequestMapping(value="/groups/{id}",method=RequestMethod.GET)
	ResponseMessage<GroupsModel> getGroupsById(@PathVariable("id")String groupid);
	
	@RequestMapping(value="/groups",method=RequestMethod.POST)
	ResponseMessage<String> saveGroups(@RequestBody GroupsModel groups);
	
	@RequestMapping(value="/groups",method=RequestMethod.GET)
	ResponseMessage<PagerResult<GroupsModel>> groupsQuery(QueryParam params);
	
	@RequestMapping(value="/groups/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> groupsUpdate(@PathVariable("id")String id, @RequestBody GroupsModel groups);

	@RequestMapping(value="/groups/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> groupsDelete(@RequestParam("id")String group_id);
	
	/**根据type获取监所名
	 * @param type
	 * @return
	 */
	@GetMapping(value="/groups/getJsTypeByGroups")
	public ResponseMessage<List<Map<String, Object>>> getJsTypeByGroups(@RequestParam("type") String type);
	/********************************jkfzd*************************************/
	/********************************jsapp*************************************/
	@RequestMapping(value="/jsapp",method=RequestMethod.GET)
	ResponseMessage<PagerResult<JsappModel>> jsAppQuery(QueryParam filter);
	/********************************ls*************************************/
	@RequestMapping(value="/ls",method=RequestMethod.GET)
	ResponseMessage<PagerResult<LsModel>> lsQuery(QueryParam params);
	
	@RequestMapping(value="/ls",method=RequestMethod.POST)
	ResponseMessage<String> lsSave(@RequestBody LsModel ls);
	
	@RequestMapping(value="/ls/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> lsDelete(@PathVariable("id")String id);
	
	@RequestMapping(value="/ls/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> lsUpdate(@PathVariable("id")String id,@RequestBody LsModel ls);
	/********************************menus*************************************/
	@RequestMapping(value="/menus",method=RequestMethod.GET)
	ResponseMessage<PagerResult<MenusModel>> menusQuery(QueryParam menusparam);
	/********************************mount*************************************/
	/********************************publish*************************************/
	/********************************role*************************************/
	@RequestMapping(value="/role",method=RequestMethod.GET)
	ResponseMessage<PagerResult<RoleModel>> roleQuery(QueryParam params);
	
	@RequestMapping(value="/role/{id}",method=RequestMethod.DELETE)
	ResponseMessage<Integer> roleDelete(@PathVariable("id")String id);

	@RequestMapping(value="/role",method=RequestMethod.POST)
	ResponseMessage<String> roleSave(@RequestBody RoleModel role);
	/********************************roleapp*************************************/
	/********************************rolemenus*************************************/
	@RequestMapping(value="/rolemenus",method=RequestMethod.GET)
	ResponseMessage<PagerResult<RolemenusModel>> roleMenuQuery(QueryParam rolemenuparam);
	
	@RequestMapping(value="/rolemenus",method=RequestMethod.POST)
	ResponseMessage<String> roleMenuSave(@RequestBody RolemenusModel rolemeus);
	
	@RequestMapping(value="/rolemenus/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> roleMenuDelete(@PathVariable("id")String id);
	/********************************ryxx*************************************/
	/********************************setting*************************************/
	@RequestMapping(value="/setting",method=RequestMethod.POST)
	ResponseMessage<String> settingSave(@RequestBody SettingModel setting);
	
	@RequestMapping(value="/setting/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> settingUpdate(@PathVariable("id")String id,@RequestBody SettingModel setting);

	@RequestMapping(value="/setting/getByKey",method=RequestMethod.GET)
	ResponseMessage<SettingModel> getSettingByKey(@RequestParam("appid")String platformid, @RequestParam("key")String key);
	
	@RequestMapping(value="/setting/getSetting",method=RequestMethod.POST)
	ResponseMessage<List<SettingModel>> getSettingByGroup(@RequestParam("appcode")String appcode, @RequestParam("progroup")String jsbh);
	
	/********************************sulier*************************************/
	/********************************tableinfo*************************************/
	/********************************userapp*************************************/
	@RequestMapping(value="/userapp",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UserappModel>> userAppQuery(QueryParam userappfilter);
	
	@RequestMapping(value="/userapp/forbid",method=RequestMethod.POST)
	ResponseMessage<String> userAppforbid(@RequestParam("user")String username,@RequestBody Map<String, Object> userappparams);
	/********************************usergroup*************************************/
	@RequestMapping(value="/usergroup",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UsergroupModel>> userGroupQuery(QueryParam params);
	
	@RequestMapping(value="/usergroup/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> userGroupUpdate(@PathVariable("id")String id, @RequestBody UsergroupModel usergroupModel);
	/********************************userinfo*************************************/
	@RequestMapping(value="/userinfo",method=RequestMethod.PATCH)
	ResponseMessage<String> UserInfoSaveOrUpdate(@RequestBody UserinfoModel userinfo);
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UserinfoModel>> userInfoQuery(QueryParam params);
	
	@RequestMapping(value="/userinfo/saveUserInfo",method=RequestMethod.POST)
	ResponseMessage<String> userInfoSave(@RequestBody User params);
	
	@RequestMapping(value="/userinfo/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> userInfoUpdate(@PathVariable("id")String id,@RequestBody  UserinfoModel userinfoModel);
	
	@RequestMapping(value="/userinfo/{id}",method=RequestMethod.DELETE)
	ResponseMessage<Integer> userDel(@PathVariable("id")String id);
	
	/********************************usermenu*************************************/
	
	@RequestMapping(value="/usermenu",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UsermenuModel>> userMenuQuery(QueryParam userappparam);
	
	@RequestMapping(value="/usermenu/forbid",method=RequestMethod.POST)
	ResponseMessage<String> userMenuforbid(@RequestParam("user")String username, @RequestParam("jsbh")String jsbh, @RequestBody Map<String, Object> usermenuparams);
	/********************************userrole*************************************/
	@RequestMapping(value="/userrole",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UserroleModel>> userRoleQuery(QueryParam param);

	@RequestMapping(value="/userrole/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> userRoleDelete(@PathVariable("id")String id);

	@RequestMapping(value="/userrole/batchAdd",method=RequestMethod.POST)
	ResponseMessage<String> userRoleBatchAdd(@RequestParam("user")String username, @RequestBody Map<String, Object> userrole);
	/********************************usersetting*************************************/
	
	@RequestMapping(value="/usersetting",method=RequestMethod.GET)
	ResponseMessage<PagerResult<UsersettingModel>> userSettingQuery(QueryParam usersetparam);
	
	@RequestMapping(value="/usersetting/getbykey",method=RequestMethod.GET)
	ResponseMessage<UsersettingModel> getUserSettingByKey(@RequestParam("userid")String id, @RequestParam("appcode")String platformid,@RequestParam("key") String k);
	
	@RequestMapping(value="/usersetting",method=RequestMethod.PATCH)
	ResponseMessage<String> userSettingSaveOrUpdate(@RequestBody UsersettingModel usersetting);
	
	/**
	 * 批量设置用户壁纸
	 */
	@RequestMapping(value="/usersetting/setWallForList",method=RequestMethod.POST)
	public ResponseMessage<String> setWallForList(@RequestBody List<Map<String,Object>> list);
	
	/********************************wgzd*************************************/
	
	@RequestMapping(value="/wgzd",method=RequestMethod.GET)
	ResponseMessage<PagerResult<WgzdModel>> queryWgzdForPage(QueryParam params);
	
	
	@RequestMapping(value="/wgzd",method=RequestMethod.POST)
	ResponseMessage<String> wgzdSave(@RequestBody WgzdModel wgzd);
	
	@RequestMapping(value="/wgzd/{id}",method=RequestMethod.PUT)
	ResponseMessage<String> wgzdUpdate(@PathVariable("id") String id,@RequestBody WgzdModel wgzd);
	
	@RequestMapping(value="/wgzd/{id}",method=RequestMethod.DELETE)
	ResponseMessage<String> wgzdDelete(@PathVariable("id") String id);
	
	@RequestMapping(value="/wgzd/{id}",method=RequestMethod.GET)
	ResponseMessage<WgzdModel> getWgzdBykey(@PathVariable("id")String id);
	
	/*********************************authorization************************************/
	@RequestMapping(value="/authorization/getUserByName",method=RequestMethod.GET)
	ResponseMessage<User> getUserByName(@RequestParam("jsbh")String jsbh, @RequestParam("username")String username);



	@PostMapping("/dictionary/list")
	public ResponseMessage<List<DictionaryModel>> list(@RequestBody Map<String, String[]> map);

	/**
	 * 根据类型获取字典
	 *
	 * @param filed
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getbyfield/{field}")
	ResponseMessage<List<DictionaryModel>> getByField(
			@PathVariable(value = "field") String filed
	);

	/**
	 * 获取所有字典类型名
	 *
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getallfields")
	ResponseMessage<List<Map<String, Object>>> getAllFields();

	/**
	 * 根据类型分页查询字典
	 *
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/dictionary/getPageByfields")
	ResponseMessage<PagerResult<DictionaryModel>> getPageByfields(@RequestBody QueryParam queryParam);

	/**
	 * 根据id 获取字典信息信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/dictionary/{id}")
	ResponseMessage<DictionaryModel> getByid(@PathVariable("id") String id);

	/**
	 * 新增字典信息
	 *
	 * @return
	 */
	@PostMapping("/dictionary")
	public ResponseMessage<String> save(@RequestBody DictionaryModel dictionaryModel);

	/**
	 * 根据id 如果没有新建有就更新字典信息
	 *
	 * @return
	 */
	@PatchMapping("/dictionary")
	public ResponseMessage<String> saveorupdate(@RequestBody DictionaryModel dictionaryModel);

	/**
	 * 根据id 更新字典信息
	 *
	 * @param id
	 * @return
	 */
	@PutMapping("/dictionary/{id}")
	public ResponseMessage<String> update(@RequestParam(value = "id") String id, @RequestBody DictionaryModel dictionaryModel);

	/**
	 * 字典信息查询（带分页）
	 *
	 * @return
	 */
	@PostMapping("/dictionary/query")
	ResponseMessage<PagerResult<DictionaryModel>> query(@RequestBody Map<String, String[]> params);

	/**
	 * 用户组禁用应用查询
	 * @param userappparam
	 * @return
	 */
	@GetMapping("/groupapp")
	ResponseMessage<PagerResult<GroupappModel>> groupAppQuery(QueryParam userappparam);
	
	/**
	 * 用户组禁用应用添加
	 * @param groupappparams
	 * @return
	 */
	@RequestMapping(value="/groupapp/addGroupApp",method=RequestMethod.POST)
	ResponseMessage<String> saveGroupApp(@RequestBody Map<String, Object> groupappparams);


	@RequestMapping(value = "/userinfo/getUserByJsbhRole", method = RequestMethod.GET)
	ResponseMessage<List<UserinfoModel>> getUserByRole(@RequestParam("jsbh") String jsbh, @RequestParam("role") String role);


























}
