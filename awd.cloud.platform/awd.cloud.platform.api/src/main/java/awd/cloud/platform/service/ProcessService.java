package awd.cloud.platform.service;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-21
 **/
@Component
public class ProcessService {
    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * 流程互斥判断||时隔限制||每月办理次数限制
     *
     * @param jsbh
     * @param rybh
     * @param key       (key=user.jsbh+....)
     * @param tableName 数据库表名
     * @return
     */
	public ResponseMessage<String> FlowMutex(String jsbh, String rybh, String key, String tableName) {
        String mapmutex = null;
        String monthlylimit = null;
        String timelimit = null;
        ResponseMessage<Map<String,Object>> jbxxrespone=kssServerApis.getJbxxByRybh(rybh);
//        System.err.println("jbxxrespone==" + JSONUtil.toJson(jbxxrespone));
        String xm="";
        if(jbxxrespone!=null&&jbxxrespone.getStatus()==200&&jbxxrespone.getResult()!=null) {
        	xm=jbxxrespone.getResult().get("xm").toString();
        }else{
            return ResponseMessage.error("无此人员");
        }
        try {
//            System.err.println("key:"+key);
            JSONObject jsonObject = CacheUtils.get().getValueByKey(key);
//            System.err.println("jsonObject=="+jsonObject);
            if (!StringUtils.isNullOrEmpty(jsonObject)) {
            	if(jsonObject.get("mapmutex")!=null) {
            		mapmutex = jsonObject.get("mapmutex").toString();
            	}
            	if(jsonObject.get("monthlylimit")!=null) {
            		monthlylimit = jsonObject.get("monthlylimit").toString();
            	}
            	if(jsonObject.get("timelimit")!=null) {
            		timelimit = jsonObject.get("timelimit").toString();
            	}
            }
            Variables variables = new Variables();
            variables.setRybh(rybh);
            variables.setJsbh(jsbh);
            if (!StringUtils.isNullOrEmpty(mapmutex)) {
                String[] str = mapmutex.split(",");
                for (int i = 0; i < str.length; i++) {
                    variables.setProcessDefinitionKey(str[i]);
                    List<Map<String, Object>> obj = (List<Map<String, Object>>) workFlowService.findPersonalTaskList("", "", variables).getResult();
//                    System.err.println("obj=="+JSONUtil.toJson(obj));
                    if (obj != null && obj.size()>0) {
                        JSONObject json = CacheUtils.get().getValueByKey(obj.get(0).get("processDefinitionKey").toString().toUpperCase());
                        return ResponseMessage.error("错误！当前("+xm+")正在办理<b style='color:red;'>" + json.get("memo").toString() + "</b></br>中的<b style='color:red;'>" + obj.get(0).get("name") + "</b>");
                    }
                }
            }
            int num = kssServerApis.VerificationHour(new HashMap<String, Object>() {{
                put("tableName", tableName);
                put("jsbh", jsbh);
                put("rybh", rybh);
            }}).getResult();
            int count = kssServerApis.VerificationMonth(new HashMap<String, Object>() {{
                put("tableName", tableName);
                put("jsbh", jsbh);
                put("rybh", rybh);
            }}).getResult();
            if (num > 0) {
                return ResponseMessage.error("当前("+xm+")办理频率过高，请稍后再试！");
            }
            if(monthlylimit!=null) {
            	if (count > Integer.parseInt(monthlylimit)) {
            		return ResponseMessage.error("当前("+xm+")当月办理次数超出上限,请下月再试！");
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("流程判断失败，请联系管理员！");
        }
        return ResponseMessage.ok();
    }
	
	  /**
     * 判断人员是否已经在流程中
     * @param flowkey
     * @param rybh
     * @param jsbh
     * @return
     */
    public ResponseMessage<String> FlowSingle(String flowkey, String rybh, String jsbh){
    	String processDefinitionKey = "";
    	if(!StringUtils.isNullOrEmpty(flowkey)) {
    		processDefinitionKey = flowkey.split(":")[0];
    	}else {
    		return ResponseMessage.error("未传递必要参数");
    	}
    	Variables variables = new Variables();
        variables.setRybh(rybh);
        variables.setJsbh(jsbh);
        variables.setProcessDefinitionKey(processDefinitionKey);
        System.err.println(processDefinitionKey+"-----------------");
		List<Map<String, Object>> obj = (List<Map<String, Object>>) workFlowService.findPersonalTaskList("admin", "1", variables).getResult();
		if (obj.size()>0) {
			return ResponseMessage.error("已存在于该流程中，无法重新办理！！");
		}
        return ResponseMessage.ok();
    }
}
