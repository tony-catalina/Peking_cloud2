package awd.mis.desktop.service;

import awd.mis.desktop.api.KssServerService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.Groups;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.*;
import awd.mis.desktop.tools.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.*;

@Service("systemService")
public class SystemService {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private KssServerService kssServerService;

    /**
     * 根据用户获取用户组信息
     *
     * @param currentUser
     * @return
     */
    public Map<String, Object> getGroup(User currentUser) {
        String jsbh = currentUser.getJsbh();
        Map<String, Object> groupmap = new HashMap<String, Object>();
        ResponseMessage<List<GroupsModel>> respone = managerService.getGroupsByJsbh(jsbh);
        System.err.println(respone);
        if (respone != null && respone.getStatus() == 200) {
            groupmap = new LinkedHashMap<>();
            List<GroupsModel> list = respone.getResult();
            for (GroupsModel groupsModel : list) {
                Groups group = new Groups();
                group.setId(groupsModel.getId());
                group.setGroup_id(groupsModel.getId());
                group.setName(groupsModel.getGroupname());
                group.setParent_id(groupsModel.getFgroup());
                Map<String, Object> conf = new HashMap<>();
                conf.put("size_max", 0);
                conf.put("size_min", 0);
                group.setConfig(conf);
                groupmap.put(group.getGroup_id(), group);
            }
        }
        return groupmap;
    }

    /**
     * 根据用户获取用户角色信息
     *
     * @param currentUser
     * @return
     */
    public Map<String, RoleModel> getRole(User currentUser) {
        Map<String, RoleModel> rolemap = null;
        if (currentUser != null) {
            String usertype = currentUser.getUserinfo().getUsertype();
            if (StrUtil.isNotBlank(usertype)) {
                QueryParam params = new QueryParam();
                List<Sort> sorts = new ArrayList<Sort>();
                Sort sort = new Sort();
                sort.setName("code");
                sort.setOrder("desc");
                sort.setType("string");
                sorts.add(sort);
                params.setSorts(sorts);
                params.and("jslx", TermType.eq, usertype);
                params.setPaging(false);

                ResponseMessage<PagerResult<RoleModel>> respone = managerService.roleQuery(params);
                if (respone.getStatus() == 200) {
                    rolemap = new HashMap<>();
                    List<RoleModel> rolelist = respone.getResult().getData();
                    for (RoleModel roleModel : rolelist) {
                        rolemap.put(roleModel.getCode(), roleModel);
                    }
                }
            }
        }
        return rolemap;
    }

    public Map<String, User> getMember(User currentUser, String groupid) {
        Map<String, User> usermap = new HashMap<>();

        QueryParam groupparams = new QueryParam();
        groupparams.setPaging(false);
        groupparams.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("fgroup", TermType.eq, groupid);
        ResponseMessage<PagerResult<GroupsModel>> group = managerService.groupsQuery(groupparams);
        if (group.getStatus() == 200) {
            String groupids = "";
            List<GroupsModel> grouplist = group.getResult().getData();
            for (GroupsModel groupsModel : grouplist) {
                groupids += groupsModel.getId() + ",";
            }
            groupids += groupid;
            groupparams.and("groupid", TermType.in, groupids.length() == 0 ? "无用户组" : groupids);
        }
        System.err.println(JSONUtil.toJsonStr(groupparams));
        ResponseMessage<PagerResult<UsergroupModel>> groupsrepone = managerService.userGroupQuery(groupparams);
        if (groupsrepone.getStatus() == 200 && groupsrepone.getResult() != null) {
            QueryParam params = new QueryParam();
            params.setPaging(false);
            if (groupsrepone.getResult().getData().size() > 0) {
                String userids = "";
                for (int i = 0; i < groupsrepone.getResult().getData().size(); i++) {
                    userids += groupsrepone.getResult().getData().get(i).getUserid() + ",";
                }

                params.and("jsbh", TermType.eq, currentUser.getJsbh())
                        .and("id", TermType.in, userids.length() == 0 ? "无用户" : userids);
            } else {
                params.and("jsbh", TermType.eq, currentUser.getJsbh())
                        .and("id", TermType.eq, "无数据");
            }
            System.out.println(JSONUtil.toJsonStr(params));
            ResponseMessage<PagerResult<UserinfoModel>> respone = managerService.userInfoQuery(params);
            if (respone.getStatus() == 200 && respone.getResult() != null) {
                List<UserinfoModel> userinfolist = respone.getResult().getData();
                for (UserinfoModel userinfoModel : userinfolist) {

                    User user = new User();
                    user.setId(userinfoModel.getId());
                    user.setJsbh(userinfoModel.getJsbh());
                    user.setEmail(userinfoModel.getEmail());
                    user.setUsername(userinfoModel.getLoginname());
                    user.setPassword(userinfoModel.getLoginpass());
                    user.setState(userinfoModel.getState());
                    user.setJh(userinfoModel.getJh());
                    user.setSfzh(userinfoModel.getSfzh());
                    user.setGroupid(getUserGroup(userinfoModel.getId()) != null ? getUserGroup(userinfoModel.getId()).getId() : "");
                    user.setRoles(getRole(userinfoModel.getId()));
                    user.setRolesstr(getRoleStr(userinfoModel.getId()));
                    user.setIsadmin(userinfoModel.getGlybz());
                    usermap.put(user.getId(), user);
                }
            }

        }

        return usermap;
    }

    private List<RoleModel> getRole(String userid) {
        List<RoleModel> result = new ArrayList<RoleModel>();
        Map<String, Object> rolemap = new HashMap<>();
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("userid", TermType.eq, userid);
        ResponseMessage<PagerResult<UserroleModel>> respone = managerService.userRoleQuery(param);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            List<UserroleModel> rolelist = respone.getResult().getData();
            String rolecodes = "";
            for (int i = 0; i < rolelist.size(); i++) {
                rolecodes += rolelist.get(i).getRolecode() + ",";
            }
            QueryParam roelparam = new QueryParam();
            roelparam.setPaging(false);
            roelparam.and("code", TermType.in, rolecodes.length() == 0 ? "无角色" : rolecodes);
            ResponseMessage<PagerResult<RoleModel>> roelerespone = managerService.roleQuery(roelparam);
            if (roelerespone.getStatus() == 200 && roelerespone.getResult() != null) {
                List<RoleModel> roles = roelerespone.getResult().getData();
                for (RoleModel roleModel : roles) {
                    rolemap.put(roleModel.getCode(), roleModel.getName());
                    result.add(roleModel);
                }
            }
        }
        return result;
    }

    private String getRoleStr(String userid) {
        Map<String, Object> rolemap = new HashMap<>();
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("userid", TermType.eq, userid);
        ResponseMessage<PagerResult<UserroleModel>> respone = managerService.userRoleQuery(param);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            List<UserroleModel> rolelist = respone.getResult().getData();
            String rolecodes = "";
            for (int i = 0; i < rolelist.size(); i++) {
                rolecodes += rolelist.get(i).getRolecode() + ",";
            }
            QueryParam roelparam = new QueryParam();
            roelparam.setPaging(false);
            roelparam.and("code", TermType.in, rolecodes.length() == 0 ? "无角色" : rolecodes);
            ResponseMessage<PagerResult<RoleModel>> roelerespone = managerService.roleQuery(roelparam);
            if (roelerespone.getStatus() == 200 && roelerespone.getResult() != null) {
                List<RoleModel> roles = roelerespone.getResult().getData();
                for (RoleModel roleModel : roles) {
                    rolemap.put(roleModel.getCode(), roleModel.getName());
                }
                return JSONUtil.toJsonStr(rolemap);
            }
        }
        return "";
    }

    private GroupsModel getUserGroup(String id) {
        QueryParam params = new QueryParam();
        params.setPaging(false);
        params.and("userid", TermType.eq, id);
        ResponseMessage<PagerResult<UsergroupModel>> respone = managerService.userGroupQuery(params);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            if (respone.getResult().getData().size() > 0) {
                ResponseMessage<GroupsModel> grouprespone = managerService.getGroupsById(respone.getResult().getData().get(0).getGroupid());
                if (grouprespone.getStatus() == 200) {
                    return grouprespone.getResult();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public Map<String, Object> addGroup(User currentUser, String name, String parent_id) {
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        params.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("fgroup", TermType.eq, parent_id)
                .and("groupname", TermType.eq, name);
        ResponseMessage<PagerResult<GroupsModel>> respone = managerService.groupsQuery(params);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            if (respone.getResult().getData().size() == 0) {
                QueryParam groupparam = new QueryParam();
                groupparam.and("jsbh", TermType.eq, currentUser.getJsbh())
                        .and("fgroup", TermType.eq, parent_id);
                ResponseMessage<PagerResult<GroupsModel>> grouprespone = managerService.groupsQuery(groupparam);

                if (grouprespone.getResult().getData().size() == 1) {
                    GroupsModel groups = new GroupsModel();
                    groups.setFgroup(grouprespone.getResult().getData().get(0).getId());
                    groups.setGroupname(name);
                    groups.setJsbh(currentUser.getJsbh());
                    groups.setCreator(currentUser.getUsername());
                    managerService.saveGroups(groups);
                    result.put("code", "true");
                    result.put("data", "添加成功！");
                } else {
                    result.put("code", "false");
                    result.put("data", "无监所组无法添加自定义分组！");
                }

            } else {
                result.put("code", "false");
                result.put("data", "已有该用户组！");
            }

        } else {
            result.put("code", "false");
            result.put("data", "服务器连接失败！");
        }

        return result;
    }

    public Map<String, Object> editGroup(User currentUser, String id, String name) {
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        params.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("id", TermType.eq, id);
        ResponseMessage<PagerResult<GroupsModel>> respone = managerService.groupsQuery(params);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            if (respone.getResult().getData().size() == 1) {
                GroupsModel groups = respone.getResult().getData().get(0);
                groups.setGroupname(name);
                groups.setUpdator(currentUser.getUsername());
                managerService.groupsUpdate(groups.getId(), groups);
                result.put("code", "true");
                result.put("data", "更新成功！");
            } else {
                result.put("code", "false");
                result.put("data", "无数据，更新失败！");
            }

        } else {
            result.put("code", "false");
            result.put("data", "无数据，更新失败！");
        }

        return result;
    }

    public Map<String, Object> delGroup(User currentUser, String group_id) {
        Map<String, Object> result = new HashMap<>();
        ResponseMessage<String> respone = managerService.groupsDelete(group_id);
        if (respone.getStatus() == 200) {
            result.put("code", "true");
            result.put("data", "1".equals(respone.getResult()) ? "删除成功！" : "删除失败！");
        } else {
            result.put("code", "false");
            result.put("data", "服务器连接失败！");
        }
        return result;
    }

    public Map<String, Object> userAction(User currentUser, String action, String[] userids, String param) {
        Map<String, Object> result = new HashMap<>();
        String key = action;
        String ids = "";
        for (String id : userids) {
            ids += id + ",";
        }
        switch (key) {
            case "role_remove_from":
                QueryParam rolemap = new QueryParam();
                rolemap.setPaging(false);
                rolemap.and("userid", TermType.in, ids.length() == 0 ? "无用户" : ids);
                ResponseMessage<PagerResult<UserroleModel>> respone = managerService.userRoleQuery(rolemap);
                if (respone.getStatus() == 200 && respone.getResult() != null) {
                    List<UserroleModel> userrolelist = respone.getResult().getData();
                    for (UserroleModel userroleModel : userrolelist) {
                        managerService.userRoleDelete(userroleModel.getId());
                    }
                }
                result.put("code", "true");
                result.put("data", "清空成功");
                break;
            case "role_add":
                Map<String, Object> userrole = new HashMap<>();
                Set<String> users = new HashSet<String>();
                for (String id : userids) {
                    users.add(id);
                }
                userrole.put("user", userids);
                JSONObject role = JSONUtil.parseObj(param);
                Set<String> roles = role.keySet();
                userrole.put("role", roles);
                System.out.println(JSONUtil.toJsonStr(userrole));
                ResponseMessage<String> respone1 = managerService.userRoleBatchAdd(currentUser.getUsername(), userrole);
                if (respone1.getStatus() == 200) {
                    result.put("code", "true");
                    result.put("data", respone1.getResult());
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,修改失败！");
                }
                break;
            case "group_reset":
                QueryParam resetgroupmap = new QueryParam();
                resetgroupmap.setPaging(false);
                resetgroupmap.and("userid", TermType.in, ids.length() == 0 ? "无用户" : ids);
                ResponseMessage<PagerResult<UsergroupModel>> resetgrouprespone = managerService.userGroupQuery(resetgroupmap);
                if (resetgrouprespone.getStatus() == 200 && resetgrouprespone.getResult() != null) {
                    List<UsergroupModel> usergrouplist = resetgrouprespone.getResult().getData();
                    for (UsergroupModel usergroupModel : usergrouplist) {
                        usergroupModel.setGroupid(param);
                        usergroupModel.setUpdator(currentUser.getUsername());
                        managerService.userGroupUpdate(usergroupModel.getId(), usergroupModel);
                    }
                    result.put("code", "true");
                    result.put("data", "设置成功");
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,设置失败！");
                }
                break;
            case "group_set":
                QueryParam usergroupmap = new QueryParam();
                usergroupmap.setPaging(false);
                usergroupmap.and("userid", TermType.in, ids.length() == 0 ? "无用户" : ids);
                ResponseMessage<PagerResult<UsergroupModel>> usergrouprespone = managerService.userGroupQuery(usergroupmap);
                if (usergrouprespone.getStatus() == 200 && usergrouprespone.getResult() != null) {
                    List<UsergroupModel> usergrouplist = usergrouprespone.getResult().getData();
                    for (UsergroupModel usergroupModel : usergrouplist) {
                        usergroupModel.setGroupid(param);
                        usergroupModel.setUpdator(currentUser.getUsername());
                        managerService.userGroupUpdate(usergroupModel.getId(), usergroupModel);
                    }
                    result.put("code", "true");
                    result.put("data", "设置成功");
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,设置失败！");
                }
                break;
            case "status_set":
                QueryParam usermap = new QueryParam();
                usermap.and("id", TermType.in, ids.length() == 0 ? "无用户" : ids);
                ResponseMessage<PagerResult<UserinfoModel>> userinforespone = managerService.userInfoQuery(usermap);
                if (userinforespone.getStatus() == 200 && userinforespone.getResult() != null) {
                    List<UserinfoModel> userinfolist = userinforespone.getResult().getData();
                    for (UserinfoModel userinfoModel : userinfolist) {
                        if ("1".equals(param)) {
                            userinfoModel.setState("R2");
                        } else {
                            userinfoModel.setState("R3");
                        }
                        managerService.userInfoUpdate(userinfoModel.getId(), userinfoModel);
                    }
                    result.put("code", "true");
                    result.put("data", "设置成功");
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,设置失败！");
                }
                break;
            case "forbidapp":
                Map<String, Object> userappparams = new HashMap<>();
                userappparams.put("userid", userids);
                JSONObject apps = JSONUtil.parseObj(param);
                Set<String> appids = apps.keySet();
                Set<String> appcode = new HashSet<String>();
                for (String id : appids) {
                    appcode.add(id.replaceAll(" ", "+"));
                }
                userappparams.put("appcode", appcode);
                ResponseMessage<String> userapprepone = managerService.userAppforbid(currentUser.getUsername(), userappparams);
                if (userapprepone.getStatus() == 200) {
                    result.put("code", "true");
                    result.put("data", userapprepone.getResult());
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,设置失败！");
                }

                break;
            case "forbidmenu":
                Map<String, Object> usermenuparams = new HashMap<>();
                usermenuparams.put("userid", userids);
                JSONObject menus = JSONUtil.parseObj(param);
                usermenuparams.put("appmenu", menus);
                ResponseMessage<String> usermenurepone = managerService.userMenuforbid(currentUser.getUsername(), currentUser.getJsbh(), usermenuparams);
                if (usermenurepone.getStatus() == 200) {
                    result.put("code", "true");
                    result.put("data", usermenurepone.getResult());
                } else {
                    result.put("code", "false");
                    result.put("data", "服务不可用,设置失败！");
                }
            default:
                break;
        }
        return result;
    }

    public Map<String, Object> getDictionaryType() {
        Map<String, Object> treeList = new HashMap<>();
        ResponseMessage<List<Map<String, Object>>> respone = managerService.getDictionaryAllFields();
        List<Map<String, Object>> list = respone.getResult();
        for (Map<String, Object> map : list) {
            if (map.get("jslx").equals("0")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "公共字典");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }
            if (map.get("jslx").equals("1")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "看守所");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }

            if (map.get("jslx").equals("2")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "拘留所");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }

            if (map.get("jslx").equals("3")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "戒毒所");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }

            if (map.get("jslx").equals("4")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "收教所");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }

            if (map.get("jslx").equals("5")) {
                Map<String, Object> dic = new HashMap<>();
                dic.put("id", map.get("jslx"));
                dic.put("name", "安康医院");
                dic.put("parent_id", "");
                treeList.put((String) dic.get("id"), dic);

                Map<String, Object> dic0 = new HashMap<>();
                dic0.put("id", map.get("fieldname"));
                dic0.put("name", StringUtils.isEmpty(map.get("fieldnameString")) ? map.get("fieldname") : map.get("fieldnameString"));
                dic0.put("parent_id", map.get("jslx"));
                treeList.put((String) dic0.get("id"), dic0);
            }
        }
        return treeList;
    }

    public Map<String, Object> getWgzdType() {
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        params.and("lx", TermType.eq, "WG");
        params.setPageIndex(1);
        params.setPageSize(100000000);
        ResponseMessage<PagerResult<ClassficModel>> respone = managerService.classficQuery(params);
        if (respone.getStatus() == 200) {
            List<ClassficModel> list = respone.getResult().getData();
            for (ClassficModel classficModel : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", classficModel.getClassid());
                map.put("name", classficModel.getName());
                map.put("parent_id", classficModel.getParentid());
                result.put(classficModel.getClassid(), map);
            }
        }

        return result;
    }

    public Map<String, RoleModel> getRole() {
        QueryParam filter = new QueryParam();
        Map<String, RoleModel> rolemap = new HashMap<>();
        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        sort.setName("name");
        sort.setOrder("desc");
        sort.setType("");
        filter.setSorts(sorts);
        sorts.add(sort);
        filter.setPageIndex(1);
        filter.setPageSize(1000);
        ResponseMessage<PagerResult<RoleModel>> response = managerService.roleQuery(filter);
        List<RoleModel> list = null;
        if (response.getStatus() == 200) {
            list = response.getResult().getData();
            for (RoleModel roleModel : list) {
                rolemap.put(roleModel.getCode(), roleModel);
            }
        }
        return rolemap;
    }

    public boolean dictionarySave(DictionaryModel dictionary) {
        if (StrUtil.isEmpty(dictionary.getId())) {
            ResponseMessage<String> responese = managerService.dictionarySave(dictionary);
            if (responese.getStatus() == 200) {
                return true;
            }
        } else {
            ResponseMessage<String> responese = managerService.dictionaryUpdate(dictionary.getId(), dictionary);
            if (responese.getStatus() == 200) {
                return true;
            }
        }

        return false;
    }

    public boolean dictionaryDelete(String[] ids) {
        boolean result = false;
        int respone = 0;
        for (String id : ids) {
            ResponseMessage<String> state = managerService.dictionaryDelete(id);
            if (state.getStatus() == 200) {
                respone = 200;
            } else {
                break;
            }
        }
        if (respone == 200) {
            result = true;
        }
        return true;
    }

    public boolean dictionaryModify(List<Map> ids, String modify) {
        boolean result = false;
        int respone = 0;
        for (Map m : ids) {
            String id = (String) m.get("id");
            ResponseMessage<DictionaryModel> dicresponse = managerService.getDictionaryBykey(id);
            if (dicresponse.getStatus() == 200) {
                DictionaryModel dictionary = dicresponse.getResult();
                if ("true".equals(modify)) {
                    dictionary.setEditable("1");
                } else {
                    dictionary.setEditable("0");
                }
                System.out.println(id);
                System.out.println(JSONUtil.toJsonStr(dictionary));
                ResponseMessage<String> updaterespone = managerService.dictionaryUpdate(id, dictionary);
                if (updaterespone.getStatus() == 200) {
                    respone = 200;
                }
            } else {
                break;
            }
        }
        if (respone == 200) {
            result = true;
        }
        return true;
    }

    public boolean delRole(String id) {
        ResponseMessage<Integer> respone = managerService.roleDelete(id);
        if (respone.getStatus() == 200) {
            if (respone.getResult().intValue() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean addRole(String jslx, String code, String name) {
        RoleModel role = new RoleModel();
        role.setCode(code);
        role.setJslx(jslx);
        role.setName(name);
        role.setCreator("管理员");
        managerService.roleSave(role);
        return true;
    }

    public boolean wgzdSave(WgzdModel wgzd) {
        if (StrUtil.isEmptyIfStr(wgzd.getId())) {
            ResponseMessage<String> respones = managerService.wgzdSave(wgzd);
            if (respones != null && respones.getStatus() == 200) {
                return true;
            } else {
                return false;
            }
        } else {
            ResponseMessage<String> respones = managerService.wgzdUpdate(wgzd.getId(), wgzd);
            if (respones != null && respones.getStatus() == 200) {
                return true;
            } else {
                return false;
            }
        }


    }

    public boolean wgzdModify(List<Map> ids, String modify) {
        boolean result = false;
        int respone = 0;
        for (Map m : ids) {
            String id = (String) m.get("id");
            ResponseMessage<WgzdModel> wgzdresponse = managerService.getWgzdBykey(id);
            if (wgzdresponse.getStatus() == 200) {
                WgzdModel wgzd = wgzdresponse.getResult();
                if ("true".equals(modify)) {
                    wgzd.setEditable("1");
                } else {
                    wgzd.setEditable("0");
                }
                System.out.println(JSONUtil.toJsonStr(wgzd));
                ResponseMessage<String> updaterespone = managerService.wgzdUpdate(id, wgzd);
                if (updaterespone.getStatus() == 200) {
                    respone = 200;
                }
            } else {
                break;
            }
        }
        if (respone == 200) {
            result = true;
        }
        return true;
    }

    public Map<String, Object> getWgzdList(String field, String search) {
        // TODO Auto-generated method stub
        return null;
    }

    public PagerResult<DictionaryModel> getPage(String page, String limit, String dictionaryType, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("content", TermType.like, search + "%");
        if (StrUtil.isNotBlank(dictionaryType)) {
            params.and("fieldname", TermType.eq, dictionaryType);
        } else {
            params.and("fieldname", TermType.eq, "0");
        }
        //不分页
        params.setPaging(false);
        System.out.println(JSONUtil.toJsonStr(params));
        ResponseMessage<PagerResult<DictionaryModel>> result = managerService.dictionaryQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public Map<String, Object> getClassficTree() {
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        params.setPageIndex(1);
        params.setPageSize(100000000);
        ResponseMessage<PagerResult<ClassficModel>> respone = managerService.classficQuery(params);
        if (respone.getStatus() == 200) {
            List<ClassficModel> list = respone.getResult().getData();
            for (ClassficModel classficModel : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", classficModel.getClassid());
                map.put("name", classficModel.getName() + "(" + classficModel.getClassid() + ")");
                map.put("parent_id", classficModel.getParentid());
                result.put(classficModel.getClassid(), map);
            }
        }

        return result;
    }

    public PagerResult<ClassficModel> getClassficPage(String page, String limit, String parentid, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("name", TermType.like, search + "%");
        if (StrUtil.isNotBlank(parentid)) {
            params.and("parentid", TermType.eq, parentid);
        } else {
            params.and("parentid", TermType.eq, "0");
        }
        //不分页
        params.setPaging(false);
        ResponseMessage<PagerResult<ClassficModel>> result = managerService.classficQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public PagerResult<WgzdModel> getwgzdPage(String page, String limit, String classid, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("content", TermType.like, search + "%")
                .and("classid", TermType.like, classid + "%");
        //不分页
        params.setPaging(false);
        ResponseMessage<PagerResult<WgzdModel>> result = managerService.queryWgzdForPage(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public boolean classficSave(ClassficModel classfic) {
        QueryParam params = new QueryParam();
        params.and("classid", TermType.eq, classfic.getClassid());
        params.setPaging(false);
        if (StrUtil.isNotEmpty(classfic.getId())) {
            classfic.setName(URLDecoder.decode(classfic.getName()));
            ResponseMessage<String> updaterespone = managerService.classficUpdate(classfic.getId(), classfic);
            if (updaterespone != null && updaterespone.getStatus() == 200) {
                return true;
            }
        } else {
            ResponseMessage<PagerResult<ClassficModel>> respone = managerService.classficQuery(params);
            if (respone.getStatus() == 200 && respone.getResult() != null) {
                if (respone.getResult().getData().size() == 0) {
                    classfic.setName(URLDecoder.decode(classfic.getName()));
                    ResponseMessage<String> saverespone = managerService.classficAdd(classfic);
                    if (saverespone != null && saverespone.getStatus() == 200) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    public PagerResult<Map<String, Object>> getJqPage(String jsbh, String page, String limit, String search) {
        PagerResult<Map<String, Object>> jqpage = new PagerResult<>();
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue() - 1);
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("jsbh", TermType.eq, jsbh)
                .and("jqmc", TermType.like, "%" + search + "%")
                .and("state", TermType.eq, "R2");
        ResponseMessage<PagerResult<JqModel>> result = kssServerService.jqQuery(params);
        if (result.getStatus() == 200) {
            List<Map<String, Object>> jqlist = new ArrayList<>();
            PagerResult<JqModel> list = result.getResult();
            for (JqModel jqModel : list.getData()) {
                Map<String, Object> map = BeanUtil.beanToMap(jqModel);
                map.put("jsnum", getJss(jqModel.getJsbh(), jqModel.getJqh()));
                jqlist.add(map);

            }
            jqpage.setData(jqlist);
            jqpage.setTotal(list.getTotal());
            return jqpage;
        }
        return null;
    }

    private int getJss(String jsbh, String jqh) {
        QueryParam param = new QueryParam();
        param.and("jsbh", TermType.eq, jsbh)
                .and("jqh", TermType.eq, jqh)
                .and("state", TermType.eq, "R2");
        param.setPaging(false);
        ResponseMessage<PagerResult<JsModel>> list = kssServerService.jsQuery(param);
        if (list.getStatus() == 200) {
            return list.getResult().getData().size();
        }
        return 0;
    }

    public PagerResult<JsModel> getJsPage(String jsbh, String page, String limit, String search) {
        QueryParam params = new QueryParam();
        System.err.println("search=" + search);
        params.setPageIndex(Integer.valueOf(page).intValue() - 1);
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("jsbh", TermType.eq, jsbh)
                .and("state", TermType.eq, "R2");
        if (!"".equals(search)) {
            params.and("jsmc", TermType.like, search + "%");
        }
        ResponseMessage<PagerResult<JsModel>> result = kssServerService.jsQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public PagerResult<FjszModel> getFjszPage(String jsbh, String page, String limit, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("jsbh", TermType.eq, jsbh)
                .and("fjmc", TermType.like, search + "%");
        ResponseMessage<PagerResult<FjszModel>> result = kssServerService.fjszQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public PagerResult<BarModel> getBarPage(String jsbh, String page, String limit, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("xm", TermType.like, search + "%").and("jsbh", jsbh);
        ResponseMessage<PagerResult<BarModel>> result = managerService.barQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public PagerResult<LsModel> geLsPage(String jsbh, String page, String limit, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("xm", TermType.like, search + "%").and("jsbh", jsbh);
        ResponseMessage<PagerResult<LsModel>> result = managerService.lsQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public Map<String, Object> getRoleFlow(User currentUser, String role) {
        List<String> allroles = new ArrayList<String>();
        Map<String, Object> result = new HashMap<>();
        QueryParam flowparam = new QueryParam();
        flowparam.setPaging(false);
        flowparam.and("flag", TermType.eq, "1");
        if (role.equals("11") || role.equals("12") || role.equals("13") || role.equals("14") || role.equals("15") || role.equals("16")
                || role.equals("17") || role.equals("18") || role.equals("19")) {
            flowparam.and("mapkey", TermType.like, "kss_%");
            allroles.add("11");
            allroles.add("12");
            allroles.add("13");
            allroles.add("14");
            allroles.add("15");
            allroles.add("16");
            allroles.add("17");
            allroles.add("18");
            allroles.add("19");
        }
        if (role.equals("21") || role.equals("22") || role.equals("23") || role.equals("24") || role.equals("25") || role.equals("26")
                || role.equals("27") || role.equals("28") || role.equals("29")) {
            flowparam.and("mapkey", TermType.like, "jls_%");
            allroles.add("21");
            allroles.add("22");
            allroles.add("23");
            allroles.add("24");
            allroles.add("25");
            allroles.add("26");
            allroles.add("27");
            allroles.add("28");
            allroles.add("29");
        }
        if (role.equals("31") || role.equals("32") || role.equals("33") || role.equals("34") || role.equals("35") || role.equals("36")
                || role.equals("37") || role.equals("38") || role.equals("39")) {
            flowparam.and("mapkey", TermType.like, "jds_%");
            allroles.add("31");
            allroles.add("32");
            allroles.add("33");
            allroles.add("34");
            allroles.add("35");
            allroles.add("36");
            allroles.add("37");
            allroles.add("38");
            allroles.add("39");
        }
        if (role.equals("41") || role.equals("42") || role.equals("43") || role.equals("44") || role.equals("45") || role.equals("46")
                || role.equals("47") || role.equals("48") || role.equals("49")) {
            flowparam.and("mapkey", TermType.like, "akyy_%");
            allroles.add("41");
            allroles.add("42");
            allroles.add("43");
            allroles.add("44");
            allroles.add("45");
            allroles.add("46");
            allroles.add("47");
            allroles.add("48");
            allroles.add("49");
        }
        ResponseMessage<PagerResult<FlowmapModel>> flowrespone = managerService.flowMapQuery(flowparam);
        if (flowrespone.getStatus() == 200 && flowrespone.getResult() != null) {
            Map<String, Object> allflow = new HashMap<>();
            List<FlowmapModel> flowlist = flowrespone.getResult().getData();
            for (FlowmapModel flowmapModel : flowlist) {
                QueryParam nodeparam = new QueryParam();
                nodeparam.and("flowmapid", TermType.eq, flowmapModel.getId());
                ResponseMessage<PagerResult<FlownodeModel>> noderespone = managerService.flowNodeQuery(nodeparam);
                if (noderespone.getStatus() == 200 && noderespone.getResult() != null) {
                    List<FlownodeModel> resultlist = noderespone.getResult().getData();
                    Collections.sort(resultlist, new Comparator<FlownodeModel>() {
                        @Override
                        public int compare(FlownodeModel o1, FlownodeModel o2) {
                            int i = o1.getId().compareTo(o2.getId());
                            return i;
                        }
                    });
                    for (FlownodeModel flownodeModel : resultlist) {
                        String floderole = "";
                        QueryParam rolemenuparam = new QueryParam();
                        rolemenuparam.setPaging(false);
                        rolemenuparam.and("jsbh", TermType.eq, currentUser.getJsbh())
                                .and("menu", TermType.eq, flownodeModel.getMenu());
                        ResponseMessage<PagerResult<RolemenusModel>> nodemenurespone = managerService.roleMenuQuery(rolemenuparam);
                        List<String> hasrole = new ArrayList<String>();
                        hasrole = CollectionUtil.newArrayList(allroles);
                        if (nodemenurespone != null && nodemenurespone.getStatus() == 200) {
                            List<RolemenusModel> list = nodemenurespone.getResult().getData();
                            for (RolemenusModel rolemenusModel : list) {
                                if (rolemenusModel.getMenu().equals(flownodeModel.getMenu())) {
                                    hasrole.remove(rolemenusModel.getRolecode());
                                }
                            }
                        }
                        for (String rolecode : hasrole) {
                            floderole += rolecode + ",";
                        }
                        flownodeModel.setRole(floderole);
                    }
                    allflow.put(flowmapModel.getId(), resultlist);
                }

            }


            result.put("allflow", flowlist);
            result.put("allnode", allflow);

        }

        return result;
    }

    public Map<String, Object> jqSave(User currentUser, JqModel jq) {
        Map<String, Object> result = new HashMap<>();
        QueryParam param = new QueryParam();
        param.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("jqh", TermType.eq, jq.getJqh())
                .and("state", "R2");
        param.setPaging(false);
        ResponseMessage<PagerResult<JqModel>> list = kssServerService.jqQuery(param);
        if (list.getStatus() == 200 && list.getResult().getData().size() > 0 && StrUtil.isBlank(jq.getId())) {
            result.put("code", false);
            result.put("data", "已经有该监区号");
            return result;
        }
        if (StrUtil.isBlank(jq.getId())) {
            jq.setJsbh(currentUser.getJsbh());
            jq.setState("R2");
            jq.setCreator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.jqSave(jq);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "保存成功");
                return result;
            }
        } else {
            jq.setJsbh(currentUser.getJsbh());
            jq.setState("R2");
            jq.setUpdator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.jqUpdate(jq.getId(), jq);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "更新成功");
                return result;
            }
        }
        result.put("code", false);
        result.put("data", "保存失败");
        return result;
    }

    public Map<String, Object> jqDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<>();
        boolean ishaveJs = false;
        String idstr = "";
        for (String id : ids) {
            idstr += id + ",";
        }
        System.err.println(idstr);
        QueryParam param = new QueryParam();
        param.and("id", TermType.in, idstr.length() == 0 ? "无监区" : idstr);
        param.setPaging(false);
        ResponseMessage<PagerResult<JqModel>> respone = kssServerService.jqQuery(param);
        if (respone.getStatus() == 200) {
            QueryParam jsparam = new QueryParam();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < respone.getResult().getData().size(); i++) {
                if (i == respone.getResult().getData().size()) {
                    sb.append(respone.getResult().getData().get(i).getJqh());
                } else {
                    sb.append(respone.getResult().getData().get(i).getJqh() + ",");
                }

                //Term t = new Term();
                //	t.or("jqh", respone.getResult().getData().get(i).getJqh());
                //	jsparam.addTerm(t);
            }
            jsparam.and("jsbh", TermType.eq, currentUser.getJsbh())
                    .and("state", TermType.eq, "R2")
                    .and("jqh", TermType.in, sb.toString());
            System.err.println(com.alibaba.fastjson.JSONObject.toJSONString(jsparam));
            ResponseMessage<PagerResult<JsModel>> jsrespone = kssServerService.jsQuery(jsparam);
            if (jsrespone.getStatus() == 200 && jsrespone.getResult() != null) {
                if (jsrespone.getResult().getData().size() > 0) {
                    result.put("code", false);
                    result.put("data", "监区中还有监室,请先删除监室！");
                    return result;
                } else {
                    for (String id : ids) {
                        kssServerService.jqDelete(id);
                    }
                    result.put("code", true);
                    result.put("data", "删除成功！");
                    return result;
                }

            }
        }
        result.put("code", false);
        result.put("data", "删除失败！");
        return result;
    }

    public Map<String, Object> jsSave(User currentUser, JsModel js) {
        Map<String, Object> result = new HashMap<>();
        QueryParam param = new QueryParam();
        param.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("jqh", TermType.eq, js.getJqh())
                .and("jsh", TermType.eq, js.getJsh())
                .and("state", TermType.eq, "R2");
        ResponseMessage<PagerResult<JsModel>> list = kssServerService.jsQuery(param);
        if (list.getStatus() == 200 && list.getResult().getData().size() > 0 && StrUtil.isBlank(js.getId())) {
            result.put("code", false);
            result.put("data", "已经有该监室号");
            return result;
        }
        if (StrUtil.isBlank(js.getId())) {
            js.setJsbh(currentUser.getJsbh());
            js.setState("R2");
            js.setInnum(0);
            js.setCreator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.jsSave(js);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "保存成功");
                return result;
            }
        } else {
            js.setJsbh(currentUser.getJsbh());
            js.setState("R2");
            js.setUpdator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.jsUpdate(js.getId(), js);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "更新成功");
                return result;
            }
        }
        result.put("code", false);
        result.put("data", "保存失败");
        return result;
    }

    public Map<String, Object> jsDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<>();
        String idstr = "";
        for (String id : ids) {
            idstr += id + ",";
        }
        boolean ishaveJs = false;
        QueryParam param = new QueryParam();
        param.and("id", TermType.in, idstr.length() == 0 ? "无监室" : idstr);
        param.setPaging(false);
        ResponseMessage<PagerResult<JsModel>> respone = kssServerService.jsQuery(param);
        if (respone.getStatus() == 200) {
            if (respone.getStatus() == 200 && respone.getResult() != null) {
                if (respone.getResult().getData().size() > 0) {
                    for (int i = 0; i < respone.getResult().getData().size(); i++) {
                        if (respone.getResult().getData().get(i).getInnum() > 0) {
                            result.put("code", false);
                            result.put("data", respone.getResult().getData().get(i).getJsmc() + ":该监室还有人员,无法删除！");
                            return result;
                        } else {
                            kssServerService.jsDelete(respone.getResult().getData().get(i).getId());
                        }
                    }
                } else {
                    result.put("code", false);
                    result.put("data", "无数据，无法删除！");
                    return result;
                }

            } else {
                result.put("code", false);
                result.put("data", "服务调用失败，无法删除！");
                return result;
            }
        }
        result.put("code", false);
        result.put("data", "删除成功！");
        return result;
    }

    public Map<String, Object> fjszSave(User currentUser, FjszModel fjsz) {
        Map<String, Object> result = new HashMap<>();
        QueryParam param = new QueryParam();
        param.and("jsbh", TermType.eq, currentUser.getJsbh())
                .and("fjhm", TermType.eq, fjsz.getFjhm())
                .and("fjlx", TermType.eq, fjsz.getFjlx())
                .and("state", TermType.eq, "R2");
        ResponseMessage<PagerResult<FjszModel>> list = kssServerService.fjszQuery(param);
        if (list.getStatus() == 200 && list.getResult().getData().size() > 0 && StrUtil.isBlank(fjsz.getId())) {
            result.put("code", false);
            result.put("data", "已经有该房间！");
            return result;
        }
        if (StrUtil.isBlank(fjsz.getId())) {
            fjsz.setJsbh(currentUser.getJsbh());
            fjsz.setState("R2");
            fjsz.setFjqc(fjsz.getFjmc() + fjsz.getFjhm());
            fjsz.setState("R2");
            fjsz.setSyzt("0");
            fjsz.setYyfj("0");
            fjsz.setCreator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.fjszSave(fjsz);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "保存成功");
                return result;
            }
        } else {
            fjsz.setJsbh(currentUser.getJsbh());
            fjsz.setFjqc(fjsz.getFjmc() + fjsz.getFjhm());
            fjsz.setState("R2");
            fjsz.setUpdator(currentUser.getUsername());
            ResponseMessage<String> respone = kssServerService.fjszUpdate(fjsz.getId(), fjsz);
            if (respone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "更新成功");
                return result;
            }
        }
        result.put("code", false);
        result.put("data", "保存失败");
        return result;
    }

    public Map<String, Object> fjszDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<>();
        String idstr = "";
        for (String id : ids) {
            idstr += id + ",";
        }
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("id", TermType.in, idstr.length() == 0 ? "无房间" : idstr);
        ResponseMessage<PagerResult<FjszModel>> respone = kssServerService.fjszQuery(param);
        if (respone != null && respone.getStatus() == 200) {
            List<FjszModel> list = respone.getResult().getData();
            for (FjszModel fjszModel : list) {
                kssServerService.fjszDelete(fjszModel.getId());
            }
            result.put("code", true);
            result.put("data", "删除成功!");
        }
        return result;
    }

    public Map<String, Object> barSave(User currentUser, BarModel bar) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(bar.getId())) {
            QueryParam param = new QueryParam();
            param.setPaging(false);
            param.and("sfzh", TermType.eq, bar.getSfzh());
            ResponseMessage<PagerResult<BarModel>> respone = managerService.barQuery(param);
            if (respone != null && respone.getStatus() == 200) {
                if (respone.getResult().getData().size() > 0) {
                    result.put("code", false);
                    result.put("data", "办案人已存在!");
                } else {
                    bar.setJsbh(currentUser.getJsbh());
                    bar.setActive("1");
                    bar.setState("R2");
                    bar.setCreator(currentUser.getName());
                    ResponseMessage<String> saverespone = managerService.barSave(bar);
                    if (saverespone != null && saverespone.getStatus() == 200) {
                        result.put("code", true);
                        result.put("data", "保存成功!");
                    } else {
                        result.put("code", false);
                        result.put("data", "保存失败!");
                    }
                }

            } else {
                result.put("code", false);
                result.put("data", "系统错误!");
            }
        } else {
            bar.setActive("1");
            bar.setState("R2");
            bar.setUpdator(currentUser.getName());
            ResponseMessage<String> saverespone = managerService.barUpdate(bar.getId(), bar);
            if (saverespone != null && saverespone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "修改成功!");
            } else {
                result.put("code", false);
                result.put("data", "修改失败!");
            }
        }

        return result;
    }

    public Map<String, Object> barDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<>();
        String idstr = "";
        for (String id : ids) {
            idstr += id + ",";
        }
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("id", TermType.in, idstr.length() == 0 ? "无办案人" : idstr);
        System.err.println(com.alibaba.fastjson.JSONObject.toJSONString(param));
        ResponseMessage<PagerResult<BarModel>> respone = managerService.barQuery(param);
        System.err.println(com.alibaba.fastjson.JSONObject.toJSONString(respone.getResult().getData()));
        if (respone != null && respone.getStatus() == 200) {
            List<BarModel> list = respone.getResult().getData();
            for (BarModel barModel : list) {
                managerService.barDelete(barModel.getId());
            }
            result.put("code", true);
            result.put("data", "删除成功!");
        }
        return result;
    }

    public Map<String, Object> lsSave(User currentUser, LsModel ls) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(ls.getId())) {
            QueryParam param = new QueryParam();
            param.setPaging(false);
            param.and("lszh", TermType.eq, ls.getLszh());
            ResponseMessage<PagerResult<LsModel>> respone = managerService.lsQuery(param);
            if (respone != null && respone.getStatus() == 200) {
                if (respone.getResult().getData().size() > 0) {
                    result.put("code", false);
                    result.put("data", "该律师已存在!");
                } else {
                    ls.setJsbh(currentUser.getJsbh());
                    ls.setYxbz("1");
                    ls.setState("R2");
                    ls.setCreator(currentUser.getName());
                    ResponseMessage<String> saverespone = managerService.lsSave(ls);
                    if (saverespone != null && saverespone.getStatus() == 200) {
                        result.put("code", true);
                        result.put("data", "保存成功!");
                    } else {
                        result.put("code", false);
                        result.put("data", "保存失败!");
                    }
                }

            } else {
                result.put("code", false);
                result.put("data", "系统错误!");
            }
        } else {
            ls.setYxbz("1");
            ls.setState("R2");
            ls.setUpdator(currentUser.getName());
            ResponseMessage<String> saverespone = managerService.lsUpdate(ls.getId(), ls);
            if (saverespone != null && saverespone.getStatus() == 200) {
                result.put("code", true);
                result.put("data", "修改成功!");
            } else {
                result.put("code", false);
                result.put("data", "修改失败!");
            }
        }

        return result;
    }

    public Map<String, Object> lsDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<>();
        String idstr = "";
        for (String id : ids) {
            idstr += id + ",";
        }
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("id", TermType.in, idstr.length() > 0 ? idstr : "无数据");
        ResponseMessage<PagerResult<LsModel>> respone = managerService.lsQuery(param);
        if (respone != null && respone.getStatus() == 200) {
            List<LsModel> list = respone.getResult().getData();
            for (LsModel lsModel : list) {
                managerService.lsDelete(lsModel.getId());
            }
            result.put("code", true);
            result.put("data", "删除成功!");
        }
        return result;
    }

    public Map<String, Object> setRoleNode(User currentUser, String role, String[] nodes) {
        String msg = "";
        String ids = "";
        for (String id : nodes) {
            ids += id + ",";
        }
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        ResponseMessage<PagerResult<FlownodeModel>> respone = managerService.flowNodeQuery(params);
        if (respone.getStatus() == 200 && respone.getResult() != null) {
            List<FlownodeModel> flowlist = respone.getResult().getData();
            for (FlownodeModel flownodeModel : flowlist) {
                if (ids.indexOf(flownodeModel.getId()) > -1) {
                    System.err.println(flownodeModel.getNodename() + "---使用");
                    QueryParam rolemenuparams = new QueryParam();
                    rolemenuparams.setPaging(false);
                    rolemenuparams.and("jsbh", TermType.eq, currentUser.getJsbh())
                            .and("menu", TermType.eq, StrUtil.isNotEmpty(flownodeModel.getMenu()) ? flownodeModel.getMenu() : "没有菜单")
                            .and("rolecode", TermType.eq, StrUtil.isNotEmpty(role) ? role : "没有角色");
                    ResponseMessage<PagerResult<RolemenusModel>> rolemenusrespone = managerService.roleMenuQuery(rolemenuparams);
                    if (rolemenusrespone != null && rolemenusrespone.getStatus() == 200) {
                        List<RolemenusModel> list = rolemenusrespone.getResult().getData();
                        System.err.println("删除数据" + list.size());
                        if (list != null && list.size() > 0) {
                            for (RolemenusModel rolemenusModel : list) {
                                managerService.roleMenuDelete(rolemenusModel.getId());
                            }
                        }
                    }

                } else {
                    System.err.println(flownodeModel.getNodename() + "---禁用");
                    System.err.println(flownodeModel.getMenu() + "******");
                    System.err.println(role + "******");
                    if (StrUtil.isNotEmpty(flownodeModel.getMenu()) && StrUtil.isNotEmpty(role)) {
                        RolemenusModel rolemeus = new RolemenusModel();
                        rolemeus.setJsbh(currentUser.getJsbh());
                        rolemeus.setMenu(flownodeModel.getMenu());
                        rolemeus.setRolecode(role);
                        rolemeus.setCreator(currentUser.getName());
                        managerService.roleMenuSave(rolemeus);
                    } else {
                        msg = "有流程没有设置菜单编号";
                    }

                }

            }
            result.put("code", true);
            result.put("data", "设置成功" + msg);
            return result;
        }
        result.put("code", false);
        result.put("data", "保存失败");
        return result;
    }

    private boolean setFlowNodeRole(String role, FlownodeModel flownodeModel, String[] nodes) {
        String roles = flownodeModel.getRole();
        if (roles.indexOf(role) > -1 && StrUtil.isNotBlank(roles)) {//角色在 节点设置里
            //看看这次有没有设置值
            boolean isset = false;
            for (String node : nodes) {
                if (flownodeModel.getId().equals(node)) {
                    isset = true;
                    break;
                }
            }
            if (isset) {//在设置里  ---不用改变
                return true;
            } else {//不在设置里 ---删除该role
                String newrole = roles.replace("," + role, "");
                flownodeModel.setRole(newrole);
                ResponseMessage<String> updateresult = managerService.flowNodeUpdate(flownodeModel.getId(), flownodeModel);
                if (updateresult.getStatus() == 200) {
                    return true;
                } else {
                    return false;
                }

            }
        } else {// 不在角色配置里

            System.out.println("新增配置--------------------");
            boolean isset = false;
            for (String node : nodes) {
                if (flownodeModel.getId().equals(node)) {
                    isset = true;
                    break;
                }
            }
            if (isset) {//在设置里  ---添加
                String newrole = roles + "," + role;
                flownodeModel.setRole(newrole);
                ResponseMessage<String> updateresult = managerService.flowNodeUpdate(flownodeModel.getId(), flownodeModel);
                if (updateresult.getStatus() == 200) {
                    return true;
                } else {
                    return false;
                }
            } else {//不在设置里 ---不用改变
                return true;
            }
        }
    }

    public Map<String, Object> addUser(User currentUser, List<UserinfoModel> users, String role, String group) {
        Map<String, Object> result = new HashMap<>();
        if (users.size() == 1) {
            UserinfoModel user = users.get(0);
            user.setUsertype(currentUser.getUserinfo().getUsertype());
            user.setRealname(user.getLoginname());
            user.setJsbh(currentUser.getJsbh());
            user.setState("R2");
            user.setCreator(currentUser.getUsername());
            User params = new User();
            params.setUsername(user.getLoginname());
            params.setJh(user.getJh());
            params.setPassword(user.getLoginpass());
            params.setJsbh(user.getJsbh());
            params.setUserinfo(user);

            if (StrUtil.isNotEmpty(group)) {
                GroupsModel groupmodel = new GroupsModel();
                groupmodel.setId(group);
                params.setGroup(groupmodel);
            }

            if (StrUtil.isNotEmpty(role) && JSONUtil.isJson(role)) {
                JSONObject json = JSONUtil.parseObj(role);
                Set<String> keys = json.keySet();
                List<RoleModel> rolelist = new ArrayList<>();
                for (String key : keys) {
                    RoleModel roleModel = new RoleModel();
                    roleModel.setJslx(currentUser.getUserinfo().getUsertype());
                    roleModel.setCode(key);
                    roleModel.setName(json.getStr(key));
                    rolelist.add(roleModel);
                }
                params.setRoles(rolelist);
            }

            System.err.println(JSONUtil.toJsonStr(params));
            if (StringUtils.isEmpty(user.getId())) {
                QueryParam qp = new QueryParam();
                qp.and("loginname", user.getLoginname());
                qp.and("jsbh", currentUser.getJsbh());
                ResponseMessage<PagerResult<UserinfoModel>> rm = managerService.userInfoQuery(qp);
                if (rm.getResult().getTotal() != 0) {
                    result.put("data", "该所已存在同用户名人员！");
                    result.put("code", false);
                } else {
                    ResponseMessage<String> respone = managerService.userInfoSave(params);
                    if (respone.getStatus() == 200) {
                        result.put("data", "保存成功！");
                        result.put("code", true);
                    } else {
                        result.put("data", respone.getMessage());
                        result.put("code", false);
                    }
                }
            } else {
                ResponseMessage<String> respone = managerService.userInfoSave(params);
                if (respone.getStatus() == 200) {
                    result.put("data", "保存成功！");
                    result.put("code", true);
                } else {
                    result.put("data", respone.getMessage());
                    result.put("code", false);
                }
            }
        } else {
            for (UserinfoModel user : users) {
                user.setUsertype(currentUser.getUserinfo().getUsertype());
                user.setRealname(user.getLoginname());
                user.setJsbh(currentUser.getJsbh());
                user.setState("R2");
                user.setCreator(currentUser.getUsername());
                User params = new User();
                params.setUserinfo(user);
                GroupsModel groupmodel = new GroupsModel();
                groupmodel.setId(group);
                params.setGroup(groupmodel);
                JSONObject json = JSONUtil.parseObj(role);
                Set<String> keys = json.keySet();
                List<RoleModel> rolelist = new ArrayList<>();
                for (String key : keys) {
                    RoleModel roleModel = new RoleModel();
                    roleModel.setCode(key);
                    roleModel.setName(json.getStr(key));
                    rolelist.add(roleModel);
                }
                params.setRoles(rolelist);
                QueryParam qp = new QueryParam();
                qp.and("loginname", user.getLoginname());
                qp.and("jsbh", currentUser.getJsbh());
                ResponseMessage<PagerResult<UserinfoModel>> rm = managerService.userInfoQuery(qp);
                if (rm.getResult().getTotal() != 0) {
                    result.put("data", "该所已存在同用户名人员！");
                    result.put("code", false);
                } else {
                    ResponseMessage<String> respone = managerService.userInfoSave(params);
                    if (respone.getStatus() == 200) {
                        result.put("data", "保存成功！");
                        result.put("code", true);
                    } else {
                        result.put("data", respone.getMessage());
                        result.put("code", false);
                    }
                }
            }
        }

        return result;
    }

    public Map<String, Object> getGroupSetting(User currentUser, String group_id, String appid) {
        List<MenusModel> allmenulist = new ArrayList<MenusModel>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String, MenusModel> menumap = new HashMap<>();
        QueryParam menuparam = new QueryParam();
        menuparam.setPaging(false);
        menuparam.and("appcode", TermType.eq, appid)
                .and("menu", TermType.nlike, "%-%");
        ResponseMessage<PagerResult<MenusModel>> menurespone = managerService.menusQuery(menuparam);
        if (menurespone.getStatus() == 200 && menurespone.getResult() != null) {
            HashMap<String, List<MenusModel>> allmenu = new HashMap<>();
            List<MenusModel> menulist = menurespone.getResult().getData();
            allmenulist.addAll(menulist);
            for (MenusModel menusModel : menulist) {
                if (allmenu.get(menusModel.getParent()) == null) {
                    List<MenusModel> menuslist = new ArrayList<>();
                    menuslist.add(menusModel);
                    allmenu.put(menusModel.getParent(), menuslist);
                } else {
                    List<MenusModel> menuslist = allmenu.get(menusModel.getParent());
                    menuslist.add(menusModel);
                    allmenu.put(menusModel.getParent(), menuslist);
                }
                menumap.put(menusModel.getId(), menusModel);
            }

            data.put("allmenu", allmenu);
            QueryParam param = new QueryParam();
            param.and("jsbh", TermType.eq, currentUser.getJsbh())
                    .and("appcode", TermType.eq, appid)
                    .and("groupid", TermType.eq, group_id);
            ResponseMessage<PagerResult<GroupmenusModel>> respone = managerService.groupMenuQuery(param);
            if (respone.getStatus() == 200 && respone.getResult() != null) {
                List<GroupmenusModel> groupmenulist = respone.getResult().getData();
                Map<String, MenusModel> havemenu = new HashMap<String, MenusModel>();
                for (MenusModel menusModel : allmenulist) {
                    havemenu.put(menusModel.getId(), menusModel);
                }
                for (GroupmenusModel groupmenusModel : groupmenulist) {
                    havemenu.remove(groupmenusModel.getMenuid());
                    //havemenu.put(groupmenusModel.getMenuid(),menumap.get(groupmenusModel.getMenuid()));
                }
                data.put("groupmenu", havemenu);
            }
            result.put("data", data);
            result.put("code", "true");
        } else {
            result.put("data", null);
            result.put("code", "false");
        }
        return result;
    }

    public Map<String, Object> classficDelete(User currentUser, String[] ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        int respone = 0;
        for (String id : ids) {
            ResponseMessage<String> state = managerService.classficDelete(id);
            if (state.getStatus() == 200) {
                respone = 200;
            } else {
                break;
            }
        }
        if (respone == 200) {
            result.put("data", "删除成功");
            result.put("code", "true");
        } else {
            result.put("data", "删除失败");
            result.put("code", "false");
        }
        return result;
    }

    public Map<String, Object> wgzdDelete(String[] ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        int respone = 0;
        for (String id : ids) {
            ResponseMessage<String> state = managerService.wgzdDelete(id);
            if (state.getStatus() == 200) {
                respone = 200;
            } else {
                break;
            }
        }
        if (respone == 200) {
            result.put("data", "删除成功");
            result.put("code", "true");
        } else {
            result.put("data", "删除失败");
            result.put("code", "false");
        }
        return result;
    }

    public Map<String, Object> forbidGroupMenu(User currentUser, String app, String group, String menu, String type) {
        Map<String, Object> result = new HashMap<String, Object>();
        if ("1".equals(type)) {
            GroupmenusModel groupmenusModel = new GroupmenusModel();
            groupmenusModel.setJsbh(currentUser.getJsbh());
            groupmenusModel.setAppcode(app);
            groupmenusModel.setGroupid(group);
            groupmenusModel.setMenuid(menu);
            groupmenusModel.setCreator(currentUser.getName());
            ResponseMessage<String> respone = managerService.groupMenuAdd(groupmenusModel);
            if (respone.getStatus() == 200) {
                result.put("data", "菜单禁止成功");
                result.put("code", "true");
            } else {
                result.put("data", "菜单禁止失败");
                result.put("code", "false");
            }
        } else {
            QueryParam param = new QueryParam();
            param.setPaging(false);
            param.and("jsbh", TermType.eq, currentUser.getJsbh())
                    .and("appcode", TermType.eq, app)
                    .and("groupid", TermType.eq, group)
                    .and("menuid", TermType.eq, menu);
            ResponseMessage<PagerResult<GroupmenusModel>> response = managerService.groupMenuQuery(param);
            if (response != null && response.getStatus() == 200) {
                List<GroupmenusModel> list = response.getResult().getData();
                for (GroupmenusModel groupmenusModel : list) {
                    managerService.groupMenuDelete(groupmenusModel.getId());
                }
            }
            result.put("data", "菜单添加成功");
            result.put("code", "true");
        }

        return result;
    }

    public PagerResult<FlowmapModel> getFlowmapPage(String page, String limit, String search) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("memo", TermType.like, search + "%");
        //不分页
        params.setPaging(false);
        ResponseMessage<PagerResult<FlowmapModel>> result = managerService.flowMapQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public Map<String, Object> getFlowMapTree() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> kss = null, jls = null, jds = null, sjs = null, akyy = null;
        QueryParam params = new QueryParam();
        params.setPaging(false);
        ResponseMessage<PagerResult<FlowmapModel>> respone = managerService.flowMapQuery(params);
        if (respone.getStatus() == 200) {
            List<FlowmapModel> list = respone.getResult().getData();
            for (FlowmapModel flowmapModel : list) {
                if (flowmapModel.getMapkey().indexOf("kss") > -1 && kss == null) {
                    kss = new HashMap<String, Object>();
                    kss.put("id", "1");
                    kss.put("name", "看守所");
                    kss.put("parent_id", "0");
                    result.put("1", kss);
                }
                if (flowmapModel.getMapkey().indexOf("jls") > -1 && jls == null) {
                    jls = new HashMap<String, Object>();
                    jls.put("id", "2");
                    jls.put("name", "拘留所");
                    jls.put("parent_id", "0");
                    result.put("2", jls);
                }
                if (flowmapModel.getMapkey().indexOf("jds") > -1 && jds == null) {
                    jds = new HashMap<String, Object>();
                    jds.put("id", "3");
                    jds.put("name", "戒毒所");
                    jds.put("parent_id", "0");
                    result.put("3", jds);
                }
                if (flowmapModel.getMapkey().indexOf("sjs") > -1 && sjs == null) {
                    sjs = new HashMap<String, Object>();
                    sjs.put("id", "4");
                    sjs.put("name", "收教所");
                    sjs.put("parent_id", "0");
                    result.put("4", sjs);
                }
                if (flowmapModel.getMapkey().indexOf("akyy") > -1 && akyy == null) {
                    akyy = new HashMap<String, Object>();
                    akyy.put("id", "5");
                    akyy.put("name", "安康医院");
                    akyy.put("parent_id", "0");
                    result.put("1", akyy);
                }
                if (flowmapModel.getMapkey().indexOf("kss") > -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", flowmapModel.getId());
                    map.put("name", flowmapModel.getMemo());
                    map.put("parent_id", "1");
                    result.put(flowmapModel.getId(), map);
                }
                if (flowmapModel.getMapkey().indexOf("jls") > -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", flowmapModel.getId());
                    map.put("name", flowmapModel.getMemo());
                    map.put("parent_id", "2");
                    result.put(flowmapModel.getId(), map);
                }
                if (flowmapModel.getMapkey().indexOf("jds") > -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", flowmapModel.getId());
                    map.put("name", flowmapModel.getMemo());
                    map.put("parent_id", "3");
                    result.put(flowmapModel.getId(), map);
                }
                if (flowmapModel.getMapkey().indexOf("sjs") > -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", flowmapModel.getId());
                    map.put("name", flowmapModel.getMemo());
                    map.put("parent_id", "4");
                    result.put(flowmapModel.getId(), map);
                }
                if (flowmapModel.getMapkey().indexOf("akyy") > -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", flowmapModel.getId());
                    map.put("name", flowmapModel.getMemo());
                    map.put("parent_id", "5");
                    result.put(flowmapModel.getId(), map);
                }

            }
        }

        return result;
    }

    public PagerResult<FlownodeModel> getFlownodePage(String page, String limit, String search, String flowmapid) {
        QueryParam params = new QueryParam();
        params.setPageIndex(Integer.valueOf(page).intValue());
        params.setPageSize(Integer.valueOf(limit).intValue());
        params.and("nodename", TermType.like, search + "%")
                .and("flowmapid", TermType.eq, flowmapid);
        //不分页
        params.setPaging(false);
        ResponseMessage<PagerResult<FlownodeModel>> result = managerService.flowNodeQuery(params);
        if (result.getStatus() == 200) {
            return result.getResult();
        }
        return null;
    }

    public Map<String, Object> flownodeBindmenu(String id, String menu) {
        Map<String, Object> result = new HashMap<String, Object>();
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("id", TermType.eq, id);
        ResponseMessage<PagerResult<FlownodeModel>> respone = managerService.flowNodeQuery(param);
        if (respone != null && respone.getStatus() == 200) {
            FlownodeModel flownodeModel = respone.getResult().getData().get(0);
            flownodeModel.setMenu(menu);
            ResponseMessage<String> saverespone = managerService.flowNodeUpdate(flownodeModel.getId(), flownodeModel);
            if (saverespone != null && saverespone.getStatus() == 200) {
                result.put("data", "菜单绑定成功");
                result.put("code", "true");
            } else {
                result.put("data", "菜单绑定失败");
                result.put("code", "false");
            }
        } else {
            result.put("data", "菜单绑定失败");
            result.put("code", "false");
        }

        return result;
    }

    public Map<String, Object> flowmapAction(String id, String field, String value) {
        Map<String, Object> result = new HashMap<String, Object>();
        QueryParam param = new QueryParam();
        param.setPaging(false);
        param.and("id", TermType.eq, id);
        ResponseMessage<PagerResult<FlowmapModel>> respone = managerService.flowMapQuery(param);
        if (respone != null && respone.getStatus() == 200) {
            FlowmapModel flowmapModel = respone.getResult().getData().get(0);
            if ("mapmutex".equals(field)) {
                flowmapModel.setMapmutex(value);
            }
            if ("timelimit".equals(field)) {
                flowmapModel.setTimelimit(value);
            }
            if ("monthlylimit".equals(field)) {
                flowmapModel.setMonthlylimit(Integer.valueOf(value));
            }
            ResponseMessage<String> saverespone = managerService.flowmapUpdate(flowmapModel.getId(), flowmapModel);
            if (saverespone != null && saverespone.getStatus() == 200) {
                result.put("data", "操作成功");
                result.put("code", "true");
            } else {
                result.put("data", "操作失败");
                result.put("code", "false");
            }
        } else {
            result.put("data", "操作失败");
            result.put("code", "false");
        }

        return result;
    }

    public Map<String, Object> getGroupApp(String groupId, String jsbh) {
        Map<String, Object> result = new HashMap<>();
        QueryParam params = new QueryParam();
        params.and("groupid", TermType.eq, groupId);
        params.and("jsbh", TermType.eq, jsbh);
        params.setPaging(false);
        ResponseMessage<PagerResult<GroupappModel>> groupapp = managerService.groupAppQuery(params);
        if (groupapp.getStatus() == 200 && groupapp.getResult() != null) {
            List<GroupappModel> groupapplist = groupapp.getResult().getData();
            String appids = "";
            for (int i = 0; i < groupapplist.size(); i++) {
                appids += groupapplist.get(i).getAppcode() + ",";
            }
            QueryParam appparam = new QueryParam();
            appparam.and("appcode", TermType.in, appids.length() == 0 ? "无禁用应用" : appids);
            appparam.setPaging(false);
            ResponseMessage<PagerResult<AppModel>> apprespone = managerService.appQuery(appparam);
            if (apprespone.getStatus() == 200 && apprespone.getResult() != null) {
                List<AppModel> applist = apprespone.getResult().getData();
                for (AppModel appModel : applist) {
                    result.put(appModel.getAppcode(), appModel.getName());
                }
                return result;
            }
        }
        return null;
    }

    public Map<String, Object> saveGroupApp(String jsbh, String creator, String groupid, String param) {
        Map<String, Object> result = new HashMap<>();
        JSONObject apps = JSONUtil.parseObj(param);
        Set<String> appids = apps.keySet();
        Set<String> appcode = new HashSet<String>();
        for (String id : appids) {
            appcode.add(id.replaceAll(" ", "+"));
        }
        Map<String, Object> groupappparams = new HashMap<>();
        groupappparams.put("jsbh", jsbh);
        groupappparams.put("groupid", groupid);
        groupappparams.put("appcode", appcode);
        groupappparams.put("creator", creator);
        ResponseMessage<String> data = managerService.saveGroupApp(groupappparams);
        if (data.getStatus() == 200) {
            result.put("code", true);
            result.put("data", data.getResult());
        } else {
            result.put("code", false);
            result.put("data", "服务不可用,设置失败！");
        }
        return result;
    }

}
