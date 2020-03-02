package awd.mis.activiti.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.mis.activiti.sevice.UserTaskService;
import awd.mis.activiti.utils.StringUtil;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by watchdb on 2017/12/31.
 */
@Component
public class SpringActivitiListener implements ActivitiEventListener,ExecutionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Expression xsql;

    @Autowired
    private UserTaskService userTaskService;

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        Object entity ;

        ActivitiEntityEventImpl activitiEntityEvent;
        switch (activitiEvent.getType()) {
        	
            case ENTITY_CREATED:
            	
                // 监听器监听的流程引擎已经创建完毕，并准备好接受API调用。
                activitiEntityEvent = (ActivitiEntityEventImpl)activitiEvent;
                entity = activitiEntityEvent.getEntity();
                if (entity instanceof ProcessDefinitionEntity){
                    System.out.println("~~~~~~~~~~~~~ProcessDefinitionEntity~`````````````````````");
                } else if (entity instanceof TaskEntity) {
                } else if (entity instanceof IdentityLinkEntity) {
                    IdentityLinkEntity identityLinkEntity = (IdentityLinkEntity)entity;
                    TaskEntity taskEntity = identityLinkEntity.getTask();
                    if (taskEntity != null) {
                        //TODO：：发送待办至MQ队列，或调用接口
                        //select * from act_ru_task t where id_=？，可获取流程实例ID,流程定义ID等数据
                    	System.out.println("【待办消息推送】##### 任务ID:"+identityLinkEntity.getTaskId() +"("+taskEntity.getName()+"),待办人："+identityLinkEntity.getUserId()+",待办角色："+identityLinkEntity.getGroupId()+" ####");
                        System.out.println("【事务执行-任务创建】#### 流程定义主键：" + taskEntity.getProcessDefinitionId() + ",流程实例ID:" + taskEntity.getProcessInstanceId() +",当前任务名称："+taskEntity.getName()+"("+taskEntity.getId()+")，传递参数集合：" + taskEntity.getVariables() +" ####");

                        TaskFormData taskFormData = activitiEvent.getEngineServices().getFormService().getTaskFormData(taskEntity.getId());
                        if (taskFormData != null) {
                            List<FormProperty> formProperties = taskFormData.getFormProperties();
                            for (FormProperty property : formProperties) {
                                System.err.println(property.getId() + ":" + property.getValue());
                                try {
                                    userTaskService.executeFlowTransaction(property.getValue(),taskEntity.getVariables());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println(" taskFormData is null ");
                        }
                    }
                } else if (entity instanceof ExecutionEntity) {
                }else {

                }
                break;
            case ENTITY_DELETED:
                // 删除了已存在的实体。实体包含在事件中
                activitiEntityEvent = (ActivitiEntityEventImpl)activitiEvent;
                entity = activitiEntityEvent.getEntity();
                if (entity instanceof ProcessDefinitionEntity){
                } else if (entity instanceof TaskEntity) {
                } else if (entity instanceof IdentityLinkEntity) {
                    IdentityLinkEntity identityLinkEntity = (IdentityLinkEntity)entity;
                    TaskEntity taskEntity = identityLinkEntity.getTask();
                    if (taskEntity != null) {
                        //TODO：：发送待办至MQ队列，或调用接口
                        //select * from act_ru_task t where id_=？，可获取流程实例ID,流程定义ID等数据
                    	System.out.println("【待办消息推送】##### 任务ID:"+identityLinkEntity.getTaskId() +"("+taskEntity.getName()+"),待办人："+identityLinkEntity.getUserId()+",待办角色："+identityLinkEntity.getGroupId()+" #### ");
                        System.out.println("【事务执行】#### 流程定义主键：" + taskEntity.getProcessDefinitionId() + ",流程实例ID:" + taskEntity.getProcessInstanceId() +",当前任务名称："+taskEntity.getName()+"("+taskEntity.getId()+")，传递参数集合：" + taskEntity.getVariables()+" ####");
                        TaskFormData taskFormData = activitiEvent.getEngineServices().getFormService().getTaskFormData(taskEntity.getId());
                        if (taskFormData != null) {
                            List<FormProperty> formProperties = taskFormData.getFormProperties();
                           
                            for (FormProperty property : formProperties) {
                                System.err.println(property.getId() + ":" + property.getValue());
                                try {
                                    userTaskService.executeFlowTransaction(property.getValue(),taskEntity.getVariables());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println(" taskFormData is null ");
                        }
                    } else {
                    }
                } else if (entity instanceof ExecutionEntity) {
                }else {
                }
                break;
            default:
                break;
        }

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        String eventName = delegateExecution.getEventName();
        if ("take".equals(eventName)){
            System.out.println("连线活动名称：" + delegateExecution.getCurrentActivityName());
            if (xsql != null){ //执行事务SQL，参数
                System.out.println("execute xsql2:" + xsql.getExpressionText() +"  ");
                userTaskService.executeFlowTransaction(xsql.getExpressionText(),delegateExecution.getVariables());
            }
        }
        System.out.println("@@-----------------------------------------------------@@");
        System.out.println("params map:" + JSON.toJSONString(delegateExecution.getVariables())+"<<");
        System.out.println("@@-----------------------------------------------------@@");
        System.out.println("");
    }
}
