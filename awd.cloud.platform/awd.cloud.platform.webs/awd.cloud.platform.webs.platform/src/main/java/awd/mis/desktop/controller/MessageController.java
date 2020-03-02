package awd.mis.desktop.controller;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.GroupappModel;
import awd.mis.desktop.model.GroupsModel;
import awd.mis.desktop.model.MsgboxModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.service.UserService;
import awd.mis.desktop.tools.*;
import awd.mis.desktop.tools.TermType;
import awd.mis.desktop.views.GlobalVar;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sun.star.bridge.oleautomation.Currency;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MessageController {
    @Autowired
    LogsService logsService;
    @Autowired
    ManagerService managerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/message", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String printWelcome(ModelMap model) {
        GlobalVar.getConfig().put("my_desktop", "\\/message\\/");
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userType",users.getUserinfo().getUsertype());


        return "message/message";
    }


    /**
     * 获取消息
     * R2 为未读消息 R3 为已读消息
     *
     * @param pageIndex
     * @param pageSize
     * @param state
     * @return
     */
    @RequestMapping(value = "/getUnreadMessage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PagerResult<MsgboxModel> getUnreadMessage(@RequestParam("page") int pageIndex,
                                                     @RequestParam("limit") int pageSize,
                                                     @RequestParam("state") String state,
                                                     @RequestParam("kssj") String kssj,
                                                     @RequestParam("jssj") String jssj) {

        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userJh = users.getUserinfo().getJh();
        QueryParam queryParam = new QueryParam();

        queryParam.setPaging(true);

        if (pageIndex != 0){
            queryParam.setPageIndex(pageIndex - 1);
        }
        if (pageSize != 0){
            queryParam.setPageSize(pageSize);
        }
        queryParam.setSorts(getSorts());
        queryParam.and("jsrjh", userJh);
        if (!StringUtils.isNullOrEmpty(state)) {
            queryParam.and("state", "R2");
        }else{
            queryParam.and("state",TermType.in, "R2,R3");
        }
        if (!StringUtils.isNullOrEmpty(kssj)) {
            kssj = kssj+" 00:00:00";
            queryParam.and("fssj",TermType.gt, kssj);
        }
        if (!StringUtils.isNullOrEmpty(jssj)) {
            jssj = jssj+" 23:59:59";
            queryParam.and("fssj",TermType.lt, jssj);
        }
        ResponseMessage<PagerResult<MsgboxModel>> msg = logsService.queryMsg(queryParam);
//        if (msg != null && msg.getResult() != null && msg.getResult().getData().size() > 0) {
//            msg.getResult().getData().sort(Comparator.comparing(MsgboxModel::getState)
//                    .thenComparing(MsgboxModel::getFssj));
//        }
        if (msg != null) {
            return msg.getResult();
        }
        return new PagerResult<MsgboxModel>();
    }

    /**
     * 修改消息的状态
     *
     * @param id
     * @return
     */
    @GetMapping("/updateMessageState")
    @ResponseBody
    public ResponseMessage updateMessageState(@RequestParam("id") String id,@RequestParam("dele") int dele) {
        if (StringUtils.isNullOrEmpty(id)) {
            return ResponseMessage.error("ID不能为空！！");
        }
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.println(dele);
        MsgboxModel msgboxModel = new MsgboxModel();
        if (dele == 0){
            msgboxModel.setState("R3");
            System.err.println(dele);
        }else if (dele == 1){
            msgboxModel.setState("R1");
            System.err.println(dele);
        }
        msgboxModel.setUpdator(users.getUsername() + "_" + users.getJh());
        ResponseMessage r = logsService.msgUpdate(id, msgboxModel);
        return r;
    }

    /**
     * 获取未读的紧急消息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getInstancyUnreadMessage")
    @ResponseBody
    public PagerResult<MsgboxModel> getInstancyUnreadMessage(HttpServletRequest request) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String pageIndex = request.getParameter("page");

        String pageSize = request.getParameter("limit");
        String state = request.getParameter("state");

        String userJh = users.getUserinfo().getJh();

        QueryParam queryParam = new QueryParam();

        queryParam.setPaging(true);
        if (StringUtils.isNullOrEmpty(pageIndex)) {
            pageIndex = 1 + "";
        }
        if (StringUtils.isNullOrEmpty(pageSize)) {
            pageSize = 25 + "";
        }
        queryParam.setPageIndex(Integer.parseInt(pageIndex)-1);
        queryParam.setPageSize(Integer.parseInt(pageSize));
        queryParam.setSorts(getSorts());
        queryParam.and("jsrjh", userJh);
        queryParam.and("xxjb", "1");
        if (!StringUtils.isNullOrEmpty(state)) {
            queryParam.and("state", state);
        }
        ResponseMessage<PagerResult<MsgboxModel>> msg = logsService.queryMsg(queryParam);
        if (msg != null) {
            return msg.getResult();
        }
        return new PagerResult<>();
    }

    /**
     * 消息全部已读
     * @return
     */
    @RequestMapping("/updateAllMsgByUser")
    @ResponseBody
    public ResponseMessage<String> updateAllMsgByUser() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.println(users.getUserinfo().getJh());
        ResponseMessage res = logsService.updateAllMsgByJh(users.getUserinfo().getJh());
        return res;
    }
    
    /**
     * 新建消息收件人数据获取
     * @return
     */
    @RequestMapping(value="/getReceiptPerson",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,String>> getReceiptPerson(HttpServletRequest request) {
    	 String type = null;
    	 User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 String pid = request.getParameter("pid");
    	 String user = request.getParameter("user");
    	 String jsbh = request.getParameter("jsbh");
    	 String gwfs = request.getParameter("gwfs");
    	 List<Map<String,String>>  list = new ArrayList<Map<String,String>>();
    	 String jsType =String.valueOf(users.getJsbh().toCharArray()[7]);
    	 //如果是监管用户，查询父节点
    	 if("0".equals(jsType) && !awd.framework.common.utils.StringUtils.isNullOrEmpty(pid)){
    		 //监所级用户节点查询
    		 if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(jsbh) && !jsbh.equals("undefined")){
    			 QueryParam qParam = new QueryParam();
        		 qParam.and("jsbh", jsbh);
        		 qParam.setPaging(false);
        		 ResponseMessage<PagerResult<UserinfoModel>> rs = managerService.userInfoQuery(qParam);
        		 for(UserinfoModel model:rs.getResult().getData()){
    				 Map<String,String> map = new HashMap<String, String>();
    				 map.put("pid", pid);
        			 map.put("id", model.getId());
        			 map.put("jsbh", model.getJsbh());
        			 map.put("jh", model.getJh());
        			 map.put("isParent", "false");
        			 map.put("name", model.getLoginname());
        			 list.add(map);
        	 }
    		 }else {
    			//监管用户下所级用户父节点
    		 QueryParam qParam = new QueryParam();
    //		 qParam.and("jsbh", TermType.like,"110%");
    		 qParam.and("fgroup", TermType.eq,"0");
    		 if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(pid) && "0".equals(pid)){
    			// qParam.and("jsbh", TermType.eq,"%看守所%");
    			  type="1";
    		 }else if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(pid) && "1".equals(pid)){
    			// qParam.and("groupname", TermType.like,"%拘留所%");
    			  type="2";
    		 }else if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(pid) && "2".equals(pid)){
    			// qParam.and("groupname", TermType.like,"%戒毒所%");
    			  type="3";
    		 }
    		 qParam.setPaging(false);
    		 ResponseMessage<PagerResult<GroupsModel>> rs = managerService.groupsQuery(qParam);
    		 if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(gwfs) && "true".equals(gwfs)){
    			 for(GroupsModel model:rs.getResult().getData()){
        			 if(String.valueOf(model.getJsbh().toCharArray()[7]).equals(type)) {
        				 Map<String,String> map = new HashMap<String, String>();
        				 map.put("pid", pid);
            			 map.put("id", model.getId());
            			 map.put("jsbh", model.getJsbh());
            			 map.put("isParent", "false");
            			 map.put("name", model.getGroupname());
            			 list.add(map);
        			 }
		 }
			 }else{
    		 for(GroupsModel model:rs.getResult().getData()){
    			 if(String.valueOf(model.getJsbh().toCharArray()[7]).equals(type)) {
    				 Map<String,String> map = new HashMap<String, String>();
    				 map.put("pid", pid);
        			 map.put("id", model.getId());
        			 map.put("jsbh", model.getJsbh());
        			 map.put("isParent", "true");
        			 map.put("name", model.getGroupname());
        			 list.add(map);
    			 }
    		 }
    		 }
    	 }
    	 }else if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(user)){
    		 //监所用户查询该所用户
    		 QueryParam qParam = new QueryParam();
    		 qParam.and("jsbh", TermType.eq,users.getJsbh());
    		 qParam.setPaging(false);
    		 ResponseMessage<PagerResult<UserinfoModel>> rs = managerService.userInfoQuery(qParam);
    		 for(UserinfoModel model:rs.getResult().getData()){
				 Map<String,String> map = new HashMap<String, String>();
				 map.put("pid", "0");
    			 map.put("id", model.getId());
    			 map.put("jsbh", model.getJsbh());
    			 map.put("jh", model.getJh());
    			 map.put("isParent", "false");
    			 map.put("name", model.getLoginname());
    			 list.add(map);
    		 	}
    	 }
		return list;
    }
    
    
    @RequestMapping(value="/getUserType",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,String>> getUserType(HttpServletRequest request) {
    	 User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	 if("0".equals(String.valueOf(users.getJsbh().toCharArray()[8]))){
    		 Map<String,String> map =  new HashMap<String, String>();
    		 map.put("flag", "true");
    		 map.put("name", users.getGroup().getGroupname());
    		 list.add(map);
    		 return list;
    	 }else{
    		 Map<String,String> map =  new HashMap<String, String>();
    		 map.put("flag", "false");
    		 map.put("name", users.getGroup().getGroupname());
    		 list.add(map);
    		 return list;
    	 }
    	
    }
    
    
    
    private List<Sort> getSorts() {
        Sort sort = new Sort();
        sort.setName("state");
        sort.setOrder("asc");
        List<Sort> sortList = Lists.newArrayList();
        sortList.add(sort);
        sort = new Sort();
        sort.setName("fssj");
        sort.setOrder("desc");
        sortList.add(sort);
        return sortList;
    }
    /**
     * 将前台传输的数据转化为ztree可读取的格式
     * @param request
     * @return
     */
    @RequestMapping(value="/getRightVal",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>> getRightVal(HttpServletRequest request){
    	String  csdwStr = request.getParameter("csdwStr");
    	String  csdwJsbh = request.getParameter("csdwJsbh");
    	String [] jsbhstr= csdwJsbh.split(",");
    	String [] str = csdwStr.split(",");
    	List<Map<String,Object>> list = new ArrayList();
    	for(int i=0;i<str.length;i++){
    		Map<String,Object> map = new HashMap<String, Object>();
    		map.put("name", str[i]);
    		map.put("pid", i);
    		map.put("jsbh", jsbhstr[i]);
    		map.put("isParent", "true");
    		map.put("id", i);
    		list.add(map);
    	}
    	
    	return list;
    }
    
    
}
