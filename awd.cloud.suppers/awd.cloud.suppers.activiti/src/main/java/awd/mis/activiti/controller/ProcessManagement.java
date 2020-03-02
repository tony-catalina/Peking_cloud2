package awd.mis.activiti.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.activiti.entity.FlowNode;
import awd.mis.activiti.entity.RoleEntity;
import awd.mis.activiti.sevice.RoleService;
import awd.mis.activiti.sevice.WorkflowService;
import awd.mis.activiti.utils.Result;
import awd.mis.activiti.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/8/7 11:52
 *
 * @description: 流程管理
 **/
@Api(description = "流程节点管理", tags = {"process"})
@RequestMapping("/process")
@RestController
public class ProcessManagement {
	protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RoleService roleService;
    @Autowired
    private WorkflowService workflowService;
	 /**
     * 根据processID查询当前流程所有节点
     * @return
     */
    @ApiOperation("根据processID查询当前流程所有节点")
    @RequestMapping(value = "/findElementList", method = { RequestMethod.GET})
    @ResponseBody
    public Result findElementList(
    		@RequestParam(value = "rows",required = false,defaultValue = "10") String rows , 
    		@RequestParam(value = "page",required = false,defaultValue = "1") String page, 
    		HttpServletRequest request
    		){ 	
    	String processId=request.getParameter("processId");
    	BpmnModel model = repositoryService.getBpmnModel(processId);
    	List<Map<String,String>> list=Lists.newArrayList();
    	if(model != null) {
    	    Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
    	    for(FlowElement e : flowElements) {
    	    	if(!StringUtils.isNullOrEmpty(e.getName())) {
    	    		Map<String,String> m=Maps.newHashMap();
    	    		m.put("flowelementKey", e.getId());
    	    		m.put("name", e.getName());
    	    		list.add(m);
    	    	}
    	    }
    	}
    	
        return ResultUtils.ok(list);
    }
    /**
            * 根据processID查询当前流程所有节点
     * @Param jslx 监所类型
     * @return
     */
    @ApiOperation("查询当前所有角色")
    @RequestMapping(value = "/findRoleList", method = { RequestMethod.POST})
    @ResponseBody
    public Result findElementList(@RequestParam(value = "jslx",required = false) String jslx ){ 	
    	List<RoleEntity> list=Lists.newArrayList();
    	if(StringUtils.isNullOrEmpty(jslx)) {
    	 list=	roleService.listJSLX();
    	}else{
    		list=	roleService.listRoleByJSLX(jslx);
    	}
    	System.out.println(JSONUtil.toJson(list));
        return ResultUtils.ok(list);
    }

	/**
	 * flowNode添加角色
	 * @param nodeKey 节点key
	 * @param code[] 角色编码
	 * @return
	 */
@ApiOperation("flowNode添加角色")
@RequestMapping(value = "/addNodeForRole", method = { RequestMethod.POST})
@ResponseBody
public Result addNodeForRole(
		@RequestParam(value = "roleCode",required = true) String[] roleCode ,
		@RequestParam(value = "nodeCode",required = true) String nodeCode
		){ 	
	//获取floeNode
	FlowNode fd=workflowService.flowNodeByKey(nodeCode);
	//所有角色逗号隔开
	String roles="";
	if(!StringUtils.isNullOrEmpty(roleCode)) {
		 roles=String.join(",",roleCode);// (Java8) 使用join方法链接字符串
		System.out.println(roles);
		fd.setRole(roles);
		workflowService.updateflowNodeByKey(fd);
	}
 return ResultUtils.ok();
}
}
