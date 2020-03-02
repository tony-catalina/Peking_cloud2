package awd.mis.desktop.api.hystrix;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.BarModel;
import awd.mis.desktop.model.ClassficModel;
import awd.mis.desktop.model.DictionaryModel;
import awd.mis.desktop.model.FlowmapModel;
import awd.mis.desktop.model.FlownodeModel;
import awd.mis.desktop.model.GroupappModel;
import awd.mis.desktop.model.GroupmenusModel;
import awd.mis.desktop.model.GroupsModel;
import awd.mis.desktop.model.JsappModel;
import awd.mis.desktop.model.LsModel;
import awd.mis.desktop.model.MenusModel;
import awd.mis.desktop.model.RoleModel;
import awd.mis.desktop.model.RolemenusModel;
import awd.mis.desktop.model.SettingModel;
import awd.mis.desktop.model.UserappModel;
import awd.mis.desktop.model.UsergroupModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.model.UsermenuModel;
import awd.mis.desktop.model.UserroleModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.model.WgzdModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("managerService")
public class ManagerFallBackFactory implements FallbackFactory<ManagerService> {

    public static final Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Override
    public ManagerService create(Throwable cause) {

        return new ManagerService() {

            @Override
            public ResponseMessage<PagerResult<AppModel>> appQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "appQuery");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<BarModel>> barQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "barQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> barSave(BarModel bar) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "barSave");
                return null;
            }

            @Override
            public ResponseMessage<String> barDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "barDelete");
                return null;
            }

            @Override
            public ResponseMessage<String> barUpdate(String id, BarModel bar) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "barUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<ClassficModel>> classficQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "classficQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> classficAdd(ClassficModel classfic) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "classficAdd");
                return null;
            }

            @Override
            public ResponseMessage<String> classficUpdate(String id, ClassficModel classfic) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "classficUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> classficDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "classficDelete");
                return null;
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getDictionaryAllFields() {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getDictionaryAllFields");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DictionaryModel>> dictionaryQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "dictionaryQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> dictionarySave(DictionaryModel dictionary) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "dictionarySave");
                return null;
            }

            @Override
            public ResponseMessage<String> dictionaryDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "dictionaryDelete");
                return null;
            }

            @Override
            public ResponseMessage<DictionaryModel> getDictionaryBykey(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getDictionaryBykey");
                return null;
            }

            @Override
            public ResponseMessage<String> dictionaryUpdate(String id, DictionaryModel dictionary) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "dictionaryUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<FlowmapModel>> flowMapQuery(QueryParam flowparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "flowMapQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> flowmapUpdate(String id, FlowmapModel flowmapModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "flowmapUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<FlownodeModel>> flowNodeQuery(QueryParam nodeparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "flowNodeQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> flowNodeUpdate(String id, FlownodeModel flownodeModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "flowNodeUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<GroupmenusModel>> groupMenuQuery(QueryParam groupmenuparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupMenuQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> groupMenuAdd(GroupmenusModel groupmenusModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupMenuAdd");
                return null;
            }

            @Override
            public ResponseMessage<String> groupMenuDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupMenuDelete");
                return null;
            }

            @Override
            public ResponseMessage<List<GroupsModel>> getGroupsByJsbh(String jsbh) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getGroupsByJsbh");
                return null;
            }

            @Override
            public ResponseMessage<GroupsModel> getGroupsById(String groupid) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getGroupsById");
                return null;
            }

            @Override
            public ResponseMessage<String> saveGroups(GroupsModel groups) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "saveGroups");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<GroupsModel>> groupsQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupsQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> groupsUpdate(String id, GroupsModel groups) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupsUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> groupsDelete(String group_id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupsDelete");
                return null;
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getJsTypeByGroups(String type) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getJsTypeByGroups");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JsappModel>> jsAppQuery(QueryParam filter) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "jsAppQuery");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<LsModel>> lsQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "lsQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> lsSave(LsModel ls) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "lsSave");
                return null;
            }

            @Override
            public ResponseMessage<String> lsDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "lsDelete");
                return null;
            }

            @Override
            public ResponseMessage<String> lsUpdate(String id, LsModel ls) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "lsUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<MenusModel>> menusQuery(QueryParam menusparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "menusQuery");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<RoleModel>> roleQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleQuery");
                return null;
            }

            @Override
            public ResponseMessage<Integer> roleDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleDelete");
                return null;
            }

            @Override
            public ResponseMessage<String> roleSave(RoleModel role) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleSave");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<RolemenusModel>> roleMenuQuery(QueryParam rolemenuparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleMenuQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> roleMenuSave(RolemenusModel rolemeus) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleMenuSave");
                return null;
            }

            @Override
            public ResponseMessage<String> roleMenuDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "roleMenuDelete");
                return null;
            }

            @Override
            public ResponseMessage<String> settingSave(SettingModel setting) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "settingSave");
                return null;
            }

            @Override
            public ResponseMessage<String> settingUpdate(String id, SettingModel setting) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "settingUpdate");
                return null;
            }

            @Override
            public ResponseMessage<SettingModel> getSettingByKey(String platformid, String key) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getSettingByKey");
                return null;
            }

            @Override
            public ResponseMessage<List<SettingModel>> getSettingByGroup(String appcode, String jsbh) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getSettingByGroup");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UserappModel>> userAppQuery(QueryParam userappfilter) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userAppQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> userAppforbid(String username, Map<String, Object> userappparams) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userAppforbid");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UsergroupModel>> userGroupQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userGroupQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> userGroupUpdate(String id, UsergroupModel usergroupModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userGroupUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> UserInfoSaveOrUpdate(UserinfoModel userinfo) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "UserInfoSaveOrUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UserinfoModel>> userInfoQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userInfoQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> userInfoSave(User params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userInfoSave");
                return null;
            }

            @Override
            public ResponseMessage<String> userInfoUpdate(String id, UserinfoModel userinfoModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userInfoUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UsermenuModel>> userMenuQuery(QueryParam userappparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userMenuQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> userMenuforbid(String username, String jsbh,
                                                          Map<String, Object> usermenuparams) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userMenuforbid");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UserroleModel>> userRoleQuery(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userRoleQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> userRoleDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userRoleDelete");
                return null;
            }

            @Override
            public ResponseMessage<String> userRoleBatchAdd(String username, Map<String, Object> userrole) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userRoleBatchAdd");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UsersettingModel>> userSettingQuery(QueryParam usersetparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userSettingQuery");
                return null;
            }

            @Override
            public ResponseMessage<UsersettingModel> getUserSettingByKey(String id, String platformid, String k) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getUserSettingByKey");
                return null;
            }

            @Override
            public ResponseMessage<String> userSettingSaveOrUpdate(UsersettingModel usersetting) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userSettingSaveOrUpdate");
                return null;
            }


            @Override
            public ResponseMessage<Integer> userDel(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "userSettingSaveOrUpdate");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<WgzdModel>> queryWgzdForPage(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "queryWgzdForPage");
                return null;
            }

            @Override
            public ResponseMessage<String> wgzdSave(WgzdModel wgzd) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "wgzdSave");
                return null;
            }

            @Override
            public ResponseMessage<String> wgzdUpdate(String id, WgzdModel wgzd) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "wgzdUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> wgzdDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "wgzdDelete");
                return null;
            }

            @Override
            public ResponseMessage<WgzdModel> getWgzdBykey(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getWgzdBykey");
                return null;
            }

            @Override
            public ResponseMessage<User> getUserByName(String jsbh, String username) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getUserByName");
                return null;
            }

            @Override
            public ResponseMessage<List<DictionaryModel>> list(Map<String, String[]> map) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "list");
                return null;
            }

            @Override
            public ResponseMessage<List<DictionaryModel>> getByField(String filed) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getByField");
                return null;
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getAllFields() {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getAllFields");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DictionaryModel>> getPageByfields(QueryParam queryParam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getPageByfields");
                return null;
            }

            @Override
            public ResponseMessage<DictionaryModel> getByid(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getByid");
                return null;
            }

            @Override
            public ResponseMessage<String> save(DictionaryModel dictionaryModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "save");
                return null;
            }

            @Override
            public ResponseMessage<String> saveorupdate(DictionaryModel dictionaryModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "saveorupdate");
                return null;
            }

            @Override
            public ResponseMessage<String> update(String id, DictionaryModel dictionaryModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "update");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DictionaryModel>> query(Map<String, String[]> params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "query");
                return null;
            }

            @Override
            public ResponseMessage<List<AppModel>> getApplistWithJsappByJsbh(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "getApplistWithJsappByJsbh");
                return null;
            }

            @Override
            public ResponseMessage<String> setWallForList(List<Map<String, Object>> list) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "setWallForList");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<GroupappModel>> groupAppQuery(QueryParam userappparam) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "groupAppQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> saveGroupApp(Map<String, Object> groupappparams) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "saveGroupApp");
                return null;
            }

            @Override
            public ResponseMessage<List<UserinfoModel>> getUserByRole(String jsbh, String role) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "saveGroupApp");
                return null;
            }

        };
    }

}
