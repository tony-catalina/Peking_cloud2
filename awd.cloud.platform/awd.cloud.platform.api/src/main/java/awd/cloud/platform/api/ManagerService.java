package awd.cloud.platform.api;

import awd.bj.kss.model.MjjbxxModel;
import awd.bj.manager.model.DictionaryModel;
import awd.bj.manager.model.MfingerModel;
import awd.bj.manager.model.UserinfoModel;
import awd.cloud.platform.api.hystrix.ManagerFallBackFactory;
import awd.cloud.platform.model.ApidocModel;
import awd.cloud.platform.model.manager.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.entity.param.QueryParamEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient(name = "${awd.api.manager:AWD-MIS-MANAGER-SERVER}",url="http://192.168.4.50:12102", fallbackFactory = ManagerFallBackFactory.class)
public interface ManagerService {
    /***
     *
     */
    @GetMapping("/manager/apidoc")
    public ResponseMessage<PagerResult<ApidocModel>> apidocList(QueryParam param);

    @PostMapping("/manager/apidoc")
    public ResponseMessage<String> apidocSave(@RequestBody ApidocModel apidocModel);

    @PutMapping("/manager/apidoc/{id}")
    public ResponseMessage<String> apidocUpdate(@RequestParam(value = "id") String id, @RequestBody ApidocModel apidocModel);

    @DeleteMapping("/manager/apidoc/remove/{id}")
    public ResponseMessage<Integer> apidocDelete(@RequestParam(value = "id") String id);

    @GetMapping("/dictionary/getDictionaryByFieldCode/{field}/{code}")
    ResponseMessage<DictionaryModel> getDictionaryByFieldCode(@PathVariable("field") String field, @PathVariable("code") String code);

    /**
     * 根据类型获取字典
     *
     * @param filed
     * @return
     * @Author ztx
     */
    @RequestMapping(value = "/manager/dictionary/getbyfield/{field}")
    ResponseMessage<List<DictionaryModel>> getByField(
            @PathVariable(value = "field") String filed
    );

    @GetMapping("/userinfo")
    ResponseMessage<PagerResult<UserinfoModel>> list(QueryParamEntity entity);

    @GetMapping("/userinfo/getUserByJsbhsRoles")
    public ResponseMessage<List<UserinfoModel>> getUserByJsbhsRoles(@RequestParam("jsbhs") String jsbhs, @RequestParam("roles") String roles);
    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/applunbo")
    ResponseMessage<PagerResult<Manager_ApplunboModel>> applunbo_query(QueryParam queryParam);

    @PostMapping("/manager/applunbo")
    ResponseMessage<String> applunbo_save(@RequestBody Manager_ApplunboModel data);

    @PatchMapping("/manager/applunbo/{id}")
    ResponseMessage<String> applunbo_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_ApplunboModel data);

    @DeleteMapping("/manager/applunbo/{id}")
    ResponseMessage<Integer> applunbo_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/applunbo/{id}")
    ResponseMessage<Manager_ApplunboModel> applunbo_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/app")
    ResponseMessage<PagerResult<Map<String, Object>>> app_query(QueryParam queryParam);

    @PostMapping("/manager/app")
    ResponseMessage<String> app_save(@RequestBody Manager_AppModel data);

    @PatchMapping("/manager/app/{id}")
    ResponseMessage<String> app_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_AppModel data);

    @DeleteMapping("/manager/app/{id}")
    ResponseMessage<Integer> app_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/app/{id}")
    ResponseMessage<Manager_AppModel> app_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/bar")
    ResponseMessage<PagerResult<Manager_BarModel>> bar_query(QueryParam queryParam);

    @PostMapping("/manager/bar")
    ResponseMessage<String> bar_save(@RequestBody Manager_BarModel data);

    @PatchMapping("/manager/bar/{id}")
    ResponseMessage<String> bar_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_BarModel data);

    @DeleteMapping("/manager/bar/{id}")
    ResponseMessage<Integer> bar_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/bar/{id}")
    ResponseMessage<Manager_BarModel> bar_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/ccic")
    ResponseMessage<PagerResult<Manager_CcicModel>> ccic_query(QueryParam queryParam);

    @PostMapping("/manager/ccic")
    ResponseMessage<String> ccic_save(@RequestBody Manager_CcicModel data);

    @PatchMapping("/manager/ccic/{id}")
    ResponseMessage<String> ccic_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_CcicModel data);

    @DeleteMapping("/manager/ccic/{id}")
    ResponseMessage<Integer> ccic_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/ccic/{id}")
    ResponseMessage<Manager_CcicModel> ccic_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/classfic")
    ResponseMessage<PagerResult<Manager_ClassficModel>> classfic_query(QueryParam queryParam);

    @PostMapping("/manager/classfic")
    ResponseMessage<String> classfic_save(@RequestBody Manager_ClassficModel data);

    @PatchMapping("/manager/classfic/{id}")
    ResponseMessage<String> classfic_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_ClassficModel data);

    @DeleteMapping("/manager/classfic/{id}")
    ResponseMessage<Integer> classfic_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/classfic/{id}")
    ResponseMessage<Manager_ClassficModel> classfic_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/dataqualityrule")
    ResponseMessage<PagerResult<Manager_DataqualityruleModel>> dataqualityrule_query(QueryParam queryParam);

    @PostMapping("/manager/dataqualityrule")
    ResponseMessage<String> dataqualityrule_save(@RequestBody Manager_DataqualityruleModel data);

    @PatchMapping("/manager/dataqualityrule/{id}")
    ResponseMessage<String> dataqualityrule_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_DataqualityruleModel data);

    @DeleteMapping("/manager/dataqualityrule/{id}")
    ResponseMessage<Integer> dataqualityrule_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/dataqualityrule/{id}")
    ResponseMessage<Manager_DataqualityruleModel> dataqualityrule_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/dependent")
    ResponseMessage<PagerResult<Manager_DependentModel>> dependent_query(QueryParam queryParam);

    @PostMapping("/manager/dependent")
    ResponseMessage<String> dependent_save(@RequestBody Manager_DependentModel data);

    @PatchMapping("/manager/dependent/{id}")
    ResponseMessage<String> dependent_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_DependentModel data);

    @DeleteMapping("/manager/dependent/{id}")
    ResponseMessage<Integer> dependent_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/dependent/{id}")
    ResponseMessage<Manager_DependentModel> dependent_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/dictionary")
    ResponseMessage<PagerResult<Manager_DictionaryModel>> dictionary_query(QueryParam queryParam);

    @PostMapping("/manager/dictionary")
    ResponseMessage<String> dictionary_save(@RequestBody Manager_DictionaryModel data);

    @PatchMapping("/manager/dictionary/{id}")
    ResponseMessage<String> dictionary_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_DictionaryModel data);

    @DeleteMapping("/manager/dictionary/{id}")
    ResponseMessage<Integer> dictionary_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/dictionary/{id}")
    ResponseMessage<Manager_DictionaryModel> dictionary_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/dictionaryWda")
    ResponseMessage<PagerResult<Manager_DictionaryWdaModel>> dictionaryWda_query(QueryParam queryParam);

    @PostMapping("/manager/dictionaryWda")
    ResponseMessage<String> dictionaryWda_save(@RequestBody Manager_DictionaryWdaModel data);

    @PatchMapping("/manager/dictionaryWda/{id}")
    ResponseMessage<String> dictionaryWda_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_DictionaryWdaModel data);

    @DeleteMapping("/manager/dictionaryWda/{id}")
    ResponseMessage<Integer> dictionaryWda_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/dictionaryWda/{id}")
    ResponseMessage<Manager_DictionaryWdaModel> dictionaryWda_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/fieldinfo")
    ResponseMessage<PagerResult<Manager_FieldinfoModel>> fieldinfo_query(QueryParam queryParam);

    @PostMapping("/manager/fieldinfo")
    ResponseMessage<String> fieldinfo_save(@RequestBody Manager_FieldinfoModel data);

    @PatchMapping("/manager/fieldinfo/{id}")
    ResponseMessage<String> fieldinfo_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_FieldinfoModel data);

    @DeleteMapping("/manager/fieldinfo/{id}")
    ResponseMessage<Integer> fieldinfo_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/fieldinfo/{id}")
    ResponseMessage<Manager_FieldinfoModel> fieldinfo_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/flowmap")
    ResponseMessage<PagerResult<Manager_FlowmapModel>> flowmap_query(QueryParam queryParam);

    @PostMapping("/manager/flowmap")
    ResponseMessage<String> flowmap_save(@RequestBody Manager_FlowmapModel data);

    @PatchMapping("/manager/flowmap/{id}")
    ResponseMessage<String> flowmap_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_FlowmapModel data);

    @DeleteMapping("/manager/flowmap/{id}")
    ResponseMessage<Integer> flowmap_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/flowmap/{id}")
    ResponseMessage<Manager_FlowmapModel> flowmap_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/flownode")
    ResponseMessage<PagerResult<Manager_FlownodeModel>> flownode_query(QueryParam queryParam);

    @PostMapping("/manager/flownode")
    ResponseMessage<String> flownode_save(@RequestBody Manager_FlownodeModel data);

    @PatchMapping("/manager/flownode/{id}")
    ResponseMessage<String> flownode_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_FlownodeModel data);

    @DeleteMapping("/manager/flownode/{id}")
    ResponseMessage<Integer> flownode_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/flownode/{id}")
    ResponseMessage<Manager_FlownodeModel> flownode_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/fxpg")
    ResponseMessage<PagerResult<Manager_FxpgModel>> fxpg_query(QueryParam queryParam);

    @PostMapping("/manager/fxpg")
    ResponseMessage<String> fxpg_save(@RequestBody Manager_FxpgModel data);

    @PatchMapping("/manager/fxpg/{id}")
    ResponseMessage<String> fxpg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_FxpgModel data);

    @DeleteMapping("/manager/fxpg/{id}")
    ResponseMessage<Integer> fxpg_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/fxpg/{id}")
    ResponseMessage<Manager_FxpgModel> fxpg_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/groupapp")
    ResponseMessage<PagerResult<Manager_GroupappModel>> groupapp_query(QueryParam queryParam);

    @PostMapping("/manager/groupapp")
    ResponseMessage<String> groupapp_save(@RequestBody Manager_GroupappModel data);

    @PatchMapping("/manager/groupapp/{id}")
    ResponseMessage<String> groupapp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_GroupappModel data);

    @DeleteMapping("/manager/groupapp/{id}")
    ResponseMessage<Integer> groupapp_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/groupapp/{id}")
    ResponseMessage<Manager_GroupappModel> groupapp_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/groupmenus")
    ResponseMessage<PagerResult<Manager_GroupmenusModel>> groupmenus_query(QueryParam queryParam);

    @PostMapping("/manager/groupmenus")
    ResponseMessage<String> groupmenus_save(@RequestBody Manager_GroupmenusModel data);

    @PatchMapping("/manager/groupmenus/{id}")
    ResponseMessage<String> groupmenus_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_GroupmenusModel data);

    @DeleteMapping("/manager/groupmenus/{id}")
    ResponseMessage<Integer> groupmenus_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/groupmenus/{id}")
    ResponseMessage<Manager_GroupmenusModel> groupmenus_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/groups")
    ResponseMessage<PagerResult<Manager_GroupsModel>> groups_query(QueryParam queryParam);

    @PostMapping("/manager/groups")
    ResponseMessage<String> groups_save(@RequestBody Manager_GroupsModel data);

    @PatchMapping("/manager/groups/{id}")
    ResponseMessage<String> groups_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_GroupsModel data);

    @DeleteMapping("/manager/groups/{id}")
    ResponseMessage<Integer> groups_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/groups/{id}")
    ResponseMessage<Manager_GroupsModel> groups_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/interfacebinding")
    ResponseMessage<PagerResult<Manager_InterfacebindingModel>> interfacebinding_query(QueryParam queryParam);

    @PostMapping("/manager/interfacebinding")
    ResponseMessage<String> interfacebinding_save(@RequestBody Manager_InterfacebindingModel data);

    @PatchMapping("/manager/interfacebinding/{id}")
    ResponseMessage<String> interfacebinding_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_InterfacebindingModel data);

    @DeleteMapping("/manager/interfacebinding/{id}")
    ResponseMessage<Integer> interfacebinding_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/interfacebinding/{id}")
    ResponseMessage<Manager_InterfacebindingModel> interfacebinding_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/interface")
    ResponseMessage<PagerResult<Manager_InterfaceModel>> interface_query(QueryParam queryParam);

    @PostMapping("/manager/interface")
    ResponseMessage<String> interface_save(@RequestBody Manager_InterfaceModel data);

    @PatchMapping("/manager/interface/{id}")
    ResponseMessage<String> interface_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_InterfaceModel data);

    @DeleteMapping("/manager/interface/{id}")
    ResponseMessage<Integer> interface_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/interface/{id}")
    ResponseMessage<Manager_InterfaceModel> interface_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/jkfzd")
    ResponseMessage<PagerResult<Manager_JkfzdModel>> jkfzd_query(QueryParam queryParam);

    @PostMapping("/manager/jkfzd")
    ResponseMessage<String> jkfzd_save(@RequestBody Manager_JkfzdModel data);

    @PatchMapping("/manager/jkfzd/{id}")
    ResponseMessage<String> jkfzd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_JkfzdModel data);

    @DeleteMapping("/manager/jkfzd/{id}")
    ResponseMessage<Integer> jkfzd_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/jkfzd/{id}")
    ResponseMessage<Manager_JkfzdModel> jkfzd_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/jsapp")
    ResponseMessage<PagerResult<Manager_JsappModel>> jsapp_query(QueryParam queryParam);

    @PostMapping("/manager/jsapp")
    ResponseMessage<String> jsapp_save(@RequestBody Manager_JsappModel data);

    @PatchMapping("/manager/jsapp/{id}")
    ResponseMessage<String> jsapp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_JsappModel data);

    @DeleteMapping("/manager/jsapp/{id}")
    ResponseMessage<Integer> jsapp_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/jsapp/{id}")
    ResponseMessage<Manager_JsappModel> jsapp_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/ls")
    ResponseMessage<PagerResult<Manager_LsModel>> ls_query(QueryParam queryParam);

    @PostMapping("/manager/ls")
    ResponseMessage<String> ls_save(@RequestBody Manager_LsModel data);

    @PatchMapping("/manager/ls/{id}")
    ResponseMessage<String> ls_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_LsModel data);

    @DeleteMapping("/manager/ls/{id}")
    ResponseMessage<Integer> ls_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/ls/{id}")
    ResponseMessage<Manager_LsModel> ls_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/menus")
    ResponseMessage<PagerResult<Manager_MenusModel>> menus_query(QueryParam queryParam);

    @PostMapping("/manager/menus")
    ResponseMessage<String> menus_save(@RequestBody Manager_MenusModel data);

    @PatchMapping("/manager/menus/{id}")
    ResponseMessage<String> menus_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_MenusModel data);

    @DeleteMapping("/manager/menus/{id}")
    ResponseMessage<Integer> menus_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/menus/{id}")
    ResponseMessage<Manager_MenusModel> menus_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/mount")
    ResponseMessage<PagerResult<Manager_MountModel>> mount_query(QueryParam queryParam);

    @PostMapping("/manager/mount")
    ResponseMessage<String> mount_save(@RequestBody Manager_MountModel data);

    @PatchMapping("/manager/mount/{id}")
    ResponseMessage<String> mount_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_MountModel data);

    @DeleteMapping("/manager/mount/{id}")
    ResponseMessage<Integer> mount_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/mount/{id}")
    ResponseMessage<Manager_MountModel> mount_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/publish")
    ResponseMessage<PagerResult<Manager_PublishModel>> publish_query(QueryParam queryParam);

    @PostMapping("/manager/publish")
    ResponseMessage<String> publish_save(@RequestBody Manager_PublishModel data);

    @PatchMapping("/manager/publish/{id}")
    ResponseMessage<String> publish_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_PublishModel data);

    @DeleteMapping("/manager/publish/{id}")
    ResponseMessage<Integer> publish_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/publish/{id}")
    ResponseMessage<Manager_PublishModel> publish_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/roleapp")
    ResponseMessage<PagerResult<Manager_RoleappModel>> roleapp_query(QueryParam queryParam);

    @PostMapping("/manager/roleapp")
    ResponseMessage<String> roleapp_save(@RequestBody Manager_RoleappModel data);

    @PatchMapping("/manager/roleapp/{id}")
    ResponseMessage<String> roleapp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_RoleappModel data);

    @DeleteMapping("/manager/roleapp/{id}")
    ResponseMessage<Integer> roleapp_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/roleapp/{id}")
    ResponseMessage<Manager_RoleappModel> roleapp_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/role")
    ResponseMessage<PagerResult<Manager_RoleModel>> role_query(QueryParam queryParam);

    @PostMapping("/manager/role")
    ResponseMessage<String> role_save(@RequestBody Manager_RoleModel data);

    @PatchMapping("/manager/role/{id}")
    ResponseMessage<String> role_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_RoleModel data);

    @DeleteMapping("/manager/role/{id}")
    ResponseMessage<Integer> role_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/role/{id}")
    ResponseMessage<Manager_RoleModel> role_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/rolemenus")
    ResponseMessage<PagerResult<Manager_RolemenusModel>> rolemenus_query(QueryParam queryParam);

    @PostMapping("/manager/rolemenus")
    ResponseMessage<String> rolemenus_save(@RequestBody Manager_RolemenusModel data);

    @PatchMapping("/manager/rolemenus/{id}")
    ResponseMessage<String> rolemenus_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_RolemenusModel data);

    @DeleteMapping("/manager/rolemenus/{id}")
    ResponseMessage<Integer> rolemenus_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/rolemenus/{id}")
    ResponseMessage<Manager_RolemenusModel> rolemenus_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/ryxx")
    ResponseMessage<PagerResult<Manager_RyxxModel>> ryxx_query(QueryParam queryParam);

    @PostMapping("/manager/ryxx")
    ResponseMessage<String> ryxx_save(@RequestBody Manager_RyxxModel data);

    @PatchMapping("/manager/ryxx/{id}")
    ResponseMessage<String> ryxx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_RyxxModel data);

    @DeleteMapping("/manager/ryxx/{id}")
    ResponseMessage<Integer> ryxx_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/ryxx/{id}")
    ResponseMessage<Manager_RyxxModel> ryxx_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/setting")
    ResponseMessage<PagerResult<Manager_SettingModel>> setting_query(QueryParam queryParam);

    @PostMapping("/manager/setting")
    ResponseMessage<String> setting_save(@RequestBody Manager_SettingModel data);

    @PatchMapping("/manager/setting/{id}")
    ResponseMessage<String> setting_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_SettingModel data);

    @DeleteMapping("/manager/setting/{id}")
    ResponseMessage<Integer> setting_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/setting/{id}")
    ResponseMessage<Manager_SettingModel> setting_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/suplier")
    ResponseMessage<PagerResult<Manager_SuplierModel>> suplier_query(QueryParam queryParam);

    @PostMapping("/manager/suplier")
    ResponseMessage<String> suplier_save(@RequestBody Manager_SuplierModel data);

    @PatchMapping("/manager/suplier/{id}")
    ResponseMessage<String> suplier_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_SuplierModel data);

    @DeleteMapping("/manager/suplier/{id}")
    ResponseMessage<Integer> suplier_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/suplier/{id}")
    ResponseMessage<Manager_SuplierModel> suplier_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/tableinfo")
    ResponseMessage<PagerResult<Manager_TableinfoModel>> tableinfo_query(QueryParam queryParam);

    @PostMapping("/manager/tableinfo")
    ResponseMessage<String> tableinfo_save(@RequestBody Manager_TableinfoModel data);

    @PatchMapping("/manager/tableinfo/{id}")
    ResponseMessage<String> tableinfo_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_TableinfoModel data);

    @DeleteMapping("/manager/tableinfo/{id}")
    ResponseMessage<Integer> tableinfo_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/tableinfo/{id}")
    ResponseMessage<Manager_TableinfoModel> tableinfo_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/userapp")
    ResponseMessage<PagerResult<Manager_UserappModel>> userapp_query(QueryParam queryParam);

    @PostMapping("/manager/userapp")
    ResponseMessage<String> userapp_save(@RequestBody Manager_UserappModel data);

    @PatchMapping("/manager/userapp/{id}")
    ResponseMessage<String> userapp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UserappModel data);

    @DeleteMapping("/manager/userapp/{id}")
    ResponseMessage<Integer> userapp_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/userapp/{id}")
    ResponseMessage<Manager_UserappModel> userapp_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/usergroup")
    ResponseMessage<PagerResult<Manager_UsergroupModel>> usergroup_query(QueryParam queryParam);

    @PostMapping("/manager/usergroup")
    ResponseMessage<String> usergroup_save(@RequestBody Manager_UsergroupModel data);

    @PatchMapping("/manager/usergroup/{id}")
    ResponseMessage<String> usergroup_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UsergroupModel data);

    @DeleteMapping("/manager/usergroup/{id}")
    ResponseMessage<Integer> usergroup_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/usergroup/{id}")
    ResponseMessage<Manager_UsergroupModel> usergroup_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/userinfo")
    ResponseMessage<PagerResult<Manager_UserinfoModel>> userinfo_query(QueryParam queryParam);

    @PostMapping("/manager/userinfo")
    ResponseMessage<String> userinfo_save(@RequestBody Manager_UserinfoModel data);

    @PatchMapping("/manager/userinfo/{id}")
    ResponseMessage<String> userinfo_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UserinfoModel data);

    @DeleteMapping("/manager/userinfo/{id}")
    ResponseMessage<Integer> userinfo_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/userinfo/{id}")
    ResponseMessage<Manager_UserinfoModel> userinfo_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/usermenu")
    ResponseMessage<PagerResult<Manager_UsermenuModel>> usermenu_query(QueryParam queryParam);

    @PostMapping("/manager/usermenu")
    ResponseMessage<String> usermenu_save(@RequestBody Manager_UsermenuModel data);

    @PatchMapping("/manager/usermenu/{id}")
    ResponseMessage<String> usermenu_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UsermenuModel data);

    @DeleteMapping("/manager/usermenu/{id}")
    ResponseMessage<Integer> usermenu_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/usermenu/{id}")
    ResponseMessage<Manager_UsermenuModel> usermenu_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/userrole")
    ResponseMessage<PagerResult<Manager_UserroleModel>> userrole_query(QueryParam queryParam);

    @PostMapping("/manager/userrole")
    ResponseMessage<String> userrole_save(@RequestBody Manager_UserroleModel data);

    @PatchMapping("/manager/userrole/{id}")
    ResponseMessage<String> userrole_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UserroleModel data);

    @DeleteMapping("/manager/userrole/{id}")
    ResponseMessage<Integer> userrole_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/userrole/{id}")
    ResponseMessage<Manager_UserroleModel> userrole_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/usersetting")
    ResponseMessage<PagerResult<Manager_UsersettingModel>> usersetting_query(QueryParam queryParam);

    @PostMapping("/manager/usersetting")
    ResponseMessage<String> usersetting_save(@RequestBody Manager_UsersettingModel data);

    @PatchMapping("/manager/usersetting/{id}")
    ResponseMessage<String> usersetting_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_UsersettingModel data);

    @DeleteMapping("/manager/usersetting/{id}")
    ResponseMessage<Integer> usersetting_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/usersetting/{id}")
    ResponseMessage<Manager_UsersettingModel> usersetting_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/manager/wgzd")
    ResponseMessage<PagerResult<Manager_WgzdModel>> wgzd_query(QueryParam queryParam);

    @PostMapping("/manager/wgzd")
    ResponseMessage<String> wgzd_save(@RequestBody Manager_WgzdModel data);

    @PatchMapping("/manager/wgzd/{id}")
    ResponseMessage<String> wgzd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Manager_WgzdModel data);

    @DeleteMapping("/manager/wgzd/{id}")
    ResponseMessage<Integer> wgzd_deleteByKey(@PathVariable(value = "id") String id);

    @GetMapping("/manager/wgzd/{id}")
    ResponseMessage<Manager_WgzdModel> wgzd_getByKey(@PathVariable(value = "id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 用户权限查询
     *
     * @param param
     * @return
     */
    @GetMapping("/role")
    public ResponseMessage<PagerResult<Map<String, Object>>> roleQuery(QueryParam param);


    @GetMapping("/userinfo/getUserByJsbhAndName")
    public ResponseMessage<Map<String, Object>> userinfoQuery(@RequestParam("jsbh") String jsbh, @RequestParam("loginname") String loginname);

    /**
     * 根据类型获取字典
     *
     * @param field
     * @return
     * @Author ztx
     */
    @RequestMapping(value = "/dictionary/getbyfield/{field}")
    ResponseMessage<List<DictionaryModel>> getByNode(
            @PathVariable(value = "field") String field
    );

    /**
     * 字典分页查询
     *
     * @param param
     * @return
     */
    @GetMapping("/dictionary")
    public ResponseMessage<PagerResult<Map<String, Object>>> getDictionaryPage(QueryParam param);

    //民警人脸采集
    @PostMapping("/biometric/mfaceCJ")
    ResponseMessage<String> mfaceCJ(@RequestBody Manager_MfaceModel mfaceModel, @RequestParam("ip") String ip);

    @GetMapping("mface")
    ResponseMessage<PagerResult<Manager_MfaceModel>> mface_query(QueryParam queryParam);

    //民警虹膜采集
    @PostMapping("/biometric/mIrisCJ")
    ResponseMessage<String> mIrisCJ(@RequestBody Manager_MirisModel mirisModel, @RequestParam("ip") String ip);

    @GetMapping("miris")
    ResponseMessage<PagerResult<Manager_MirisModel>> miris_query(QueryParam queryParam);

    //民警指纹采集
    @PostMapping("/biometric/mfingerCJ")
    ResponseMessage<String> mfingerCJ(@RequestBody Manager_MfingerModel mfingerModel, @RequestParam("ip") String ip);

    @GetMapping("mfinger")
    ResponseMessage<PagerResult<Manager_MfingerModel>> mfinger_query(QueryParam queryParam);

    //在押人员人脸采集
    @PostMapping("/biometric/zfaceCJ")
    ResponseMessage<String> zfaceCJ(@RequestBody Manager_ZfaceModel zfaceModel, @RequestParam("ip") String ip);

    @GetMapping("/zface")
    ResponseMessage<PagerResult<Manager_ZfaceModel>> zface_query(QueryParam queryParam);

    //在押人员虹膜采集
    @PostMapping("/biometric/zIrisCJ")
    ResponseMessage<String> zIrisCJ(@RequestBody Manager_ZirisModel zirisModel, @RequestParam("ip") String ip);

    @GetMapping("/ziris")
    ResponseMessage<PagerResult<Manager_ZirisModel>> ziris_query(QueryParam queryParam);

    //在押人员指纹采集
    @PostMapping("/biometric/zfingerCJ")
    ResponseMessage<String> zfingerCJ(@RequestBody Manager_ZfingerModel zfingerModel, @RequestParam("ip") String ip);

    @GetMapping("/zfinger")
    ResponseMessage<PagerResult<Manager_ZfingerModel>> zfinger_query(QueryParam queryParam);

    @GetMapping("/userinfo/getUserRole")
    awd.framework.common.entity.PagerResult<Manager_UserInfoOther> mjxxQuery(QueryParam queryParam);
    
    //民警卡采集
    @PostMapping("/biometric/mcardCJ")
    ResponseMessage<String> mcardCJ(@RequestBody MjjbxxModel mjjbxxModel, @RequestParam("ip") String ip);
}


