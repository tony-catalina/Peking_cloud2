package awd.mis.activiti.sevice;

import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.entity.FlowMap;
import awd.mis.activiti.entity.FlowNode;
import awd.mis.activiti.entity.RequestParameters;
import awd.mis.activiti.entity.Variables;
import awd.mis.activiti.mapper.FlowMapMapper;
import awd.mis.activiti.mapper.FlowNodeMapper;
import awd.mis.activiti.utils.CacheUtils;
import awd.mis.activiti.utils.R;
import awd.mis.activiti.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkflowService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowMapMapper flowMapMapper;

    @Autowired
    private FlowNodeMapper flowNodeMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private FormService formService;

    private Logger logger = LoggerFactory.getLogger(WorkflowService.class);

    public List<RequestParameters> listRequestParameters(String jsbh) {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        RequestParameters mm = new RequestParameters();
        //获取流程信息集合
        List<RequestParameters> listMap = Lists.newArrayList();
        for (ProcessDefinition processDefinition : processDefinitionList) {

            mm = this.flowSynchronization(jsbh, processDefinition);
            listMap.add(mm);
        }

        return listMap;
    }

    public R getProNameByProId(String processDefinitionId, RepositoryService repositoryService) {
        List<ProcessDefinition> list = repositoryService
                .createProcessDefinitionQuery()
                //.deploymentId(deploymentId)//使用部署对象ID查询
                .processDefinitionId(processDefinitionId)//使用流程定义ID查询
                .orderByProcessDefinitionVersion().desc()//按照版本的升序排列      
                .list();//返回一个集合列表，封装流程定义
        if (list != null && list.size() > 0) {
            //只查询最新版本
            ProcessDefinition processDefinition = list.get(0);
            Map<String, String> result = new HashMap<String, String>();
            result.put("name", processDefinition.getName());
            result.put("key", processDefinition.getKey());
            result.put("version", String.valueOf(processDefinition.getVersion()));
            result.put("id", processDefinition.getId());
//            System.err.println(processDefinition.getDeploymentId()+","+processDefinition.getId()+","+processDefinition.getKey()+","+
//                    processDefinition.getName()+","+processDefinition.getCategory()+","+processDefinition.getDescription()+","+
//                    processDefinition.getDiagramResourceName()+","+processDefinition.getResourceName()+","+processDefinition.getTenantId());
//
            return R.ok().put("data", result);
        }
        return R.error("查询过程出错！");
    }

    public TaskQuery processQueryCondition(TaskService taskService, Variables variables) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        String taskKey = variables.getTaskDefinitionKey();
        //设置查询条件
        if (StringUtil.isNotNullOrEmpty(variables.getJsbh())) {
            if (!"000000000".equals(variables.getJsbh())) {
                taskQuery.processVariableValueLike("jsbh", "%" + variables.getJsbh() + "%");
            }
        }
        if (StringUtil.isNotNullOrEmpty(variables.getRybh())) {
            taskQuery.processVariableValueLike("rybh", "%" + variables.getRybh() + "%");
        }
        if (StringUtil.isNotNullOrEmpty(variables.getTaskId())) {
            taskQuery.taskId(variables.getTaskId());
        }
        if (StringUtil.isNotNullOrEmpty(variables.getYwlcid())) {
            taskQuery.processInstanceIdIn(Arrays.asList(variables.getYwlcid().split(",")));
        }
        if (StringUtil.isNotNullOrEmpty(variables.getProcessDefinitionId())) {
            taskQuery.processDefinitionId(variables.getProcessDefinitionId());
        }
        if (StringUtil.isNotNullOrEmpty(variables.getProcessInstanceId())) {
            taskQuery.processInstanceIdIn(Arrays.asList(variables.getProcessInstanceId().split(",")));
        }
        //指定流程定义key，只查询某个流程的任务
        if (StringUtil.isNotNullOrEmpty(variables.getProcessDefinitionKey())) {
            taskQuery.processDefinitionKey(variables.getProcessDefinitionKey());
        }
        if (StringUtil.isNotNullOrEmpty(taskKey)) {
            if (taskKey.indexOf(",") == -1||taskKey.indexOf("，") == -1) {
                taskQuery.taskDefinitionKey(variables.getTaskDefinitionKey());
            }
        }
        if (variables.getParams() != null && variables.getParams().size() > 0) {
            variables.getParams().forEach((k, v) -> {
//                taskQuery.processVariableValueEquals(k, v);
                taskQuery.processVariableValueLike(k, "%" + v + "%");
            });
        }
        if (variables.getNotEqualsMap() != null && variables.getNotEqualsMap().size() > 0) {
            variables.getNotEqualsMap().forEach((k, v) -> {
                taskQuery.processVariableValueNotEqualsIgnoreCase(k, (String) v);
            });
        }
        if (variables.getGreaterThanOrEqualMap() != null && variables.getGreaterThanOrEqualMap().size() > 0) {
            variables.getGreaterThanOrEqualMap().forEach((k, v) -> {
                taskQuery.processVariableValueGreaterThanOrEqual(k, (String) v);
            });
        }
        if (variables.getLessThanOrEqualMap() != null && variables.getLessThanOrEqualMap().size() > 0) {
            variables.getLessThanOrEqualMap().forEach((k, v) -> {
                taskQuery.processVariableValueLessThanOrEqual(k, (String) v);
            });
        }


        return taskQuery;
    }

    /**
     * 监外屏调用
     * @param taskService
     * @param variables
     * @return
     */
    public TaskQuery findPersonalTaskListWithTaskDefinedKey(TaskService taskService, Variables variables) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        //设置查询条件
//        if (StringUtil.isNotNullOrEmpty(userId)){
//            taskQuery.taskAssignee(userId);
//        }
//        if (StringUtil.isNotNullOrEmpty(roleIds)){
//            taskQuery.or().taskCandidateGroupIn(Arrays.asList(roleIds.split(",")));
//        }
        //指定流程定义key，只查询某个流程的任务
        if (StringUtil.isNotNullOrEmpty(variables.getProcessDefinitionKey())) {
            taskQuery.processDefinitionKey(variables.getProcessDefinitionKey());
        }
        if (variables.getParams() != null && variables.getParams().size() > 0) {
            variables.getParams().forEach((k, v) -> {
//                taskQuery.processVariableValueEquals(k, v);
                taskQuery.processVariableValueLike(k, "%" + v + "%");
            });
        }
        if (variables.getNotEqualsMap() != null && variables.getNotEqualsMap().size() > 0) {
            variables.getNotEqualsMap().forEach((k, v) -> {
                taskQuery.processVariableValueNotEqualsIgnoreCase(k, (String) v);
            });
        }
        if (variables.getGreaterThanOrEqualMap() != null && variables.getGreaterThanOrEqualMap().size() > 0) {
            variables.getGreaterThanOrEqualMap().forEach((k, v) -> {
                taskQuery.processVariableValueGreaterThanOrEqual(k, (String) v);
            });
        }
        if (variables.getLessThanOrEqualMap() != null && variables.getLessThanOrEqualMap().size() > 0) {
            variables.getLessThanOrEqualMap().forEach((k, v) -> {
                taskQuery.processVariableValueLessThanOrEqual(k, (String) v);
            });
        }


        return taskQuery;
    }
    /**
     * 处理返回的结果
     *
     * @param source 要处理的集合
     * @return
     */
    public List<Variables> processResult(List<Task> source) {
        List<Variables> target = Lists.newArrayList();
        if (source == null) {
            return target;
        }

        for (Task task : source) {
            Variables var = new Variables();
            Map<String, Object> map = task.getProcessVariables();
            var.setRybh(StringUtil.isNullOrEmpty(task.getProcessVariables().get("rybh")) ? "" : task.getProcessVariables().get("rybh").toString());
            var.setProcessInstanceId(task.getProcessInstanceId());
            var.setProcessDefinitionId(task.getProcessDefinitionId());
            var.setProcessDefinitionKey(task.getProcessDefinitionId().split(":")[0]);
            var.setTaskId(task.getId());
            var.setTaskDefinitionKey(task.getTaskDefinitionKey());
            var.setAssignee(task.getAssignee());
            var.setName(task.getName());
            var.setCreatetime(task.getCreateTime());
            var.setJsbh(StringUtil.isNullOrEmpty(task.getProcessVariables().get("jsbh")) ? "" : task.getProcessVariables().get("jsbh").toString());
            var.setYwlcid(task.getProcessInstanceId());
            //处理领导审批参数
            Map<String, Object> o = task.getProcessVariables();
            if (o != null && o.size() > 2) {
                var.setParams(o);
            }
            target.add(var);
        }
        return target;
    }


    /**
     * 处理返回的结果（查询指定多个节点的值）
     *
     * @param source    要处理的集合
     * @param target    处理后的结果集
     * @param variables 处理过程中需要的参数
     * @return
     * @author zw
     */
    public List<Variables> processResultWithTaskDefinitionKey(List<Task> source, List<Variables> target, Variables variables) {
        if (source == null) {
            return target;
        }
        //System.err.println("进来了 ===");
        List<Task> list = source;
        List<Variables> taskList = target;

        if (target == null) {
            taskList = new ArrayList<Variables>();
        }
        for (Task task : list) {
            Variables var = new Variables();
            if (StringUtil.isNotNullOrEmpty(variables.getJsbh())) {
                if (!"000000000".equals(variables.getJsbh())) {
                    if (!variables.getJsbh().equals(task.getProcessVariables().get("jsbh"))) {
                        continue;
                    }
                }

            }
            var.setRybh(StringUtil.isNullOrEmpty(task.getProcessVariables().get("rybh")) ? "" : task.getProcessVariables().get("rybh").toString());
            var.setProcessInstanceId(task.getProcessInstanceId());
            var.setProcessDefinitionId(task.getProcessDefinitionId());
            var.setProcessDefinitionKey(task.getProcessDefinitionId().split(":")[0]);
            var.setTaskId(task.getId());
            var.setTaskDefinitionKey(task.getTaskDefinitionKey());
            var.setAssignee(task.getAssignee());
            var.setName(task.getName());
            var.setCreatetime(task.getCreateTime());
            var.setJsbh(StringUtil.isNullOrEmpty(task.getProcessVariables().get("jsbh")) ? "" : task.getProcessVariables().get("jsbh").toString());
            //处理领导审批参数
            Map<String, Object> o = task.getProcessVariables();
            if (o != null && o.size() > 2) {
                var.setParams(o);
            }
            if (!StringUtils.isNullOrEmpty(variables.getTaskDefinitionKey())) {
                if (variables.getTaskDefinitionKey().indexOf(task.getTaskDefinitionKey()) != -1) {
                    taskList.add(var);
                }
            }

        }
        return taskList;
    }

    /**
     * 获取执行记录的总条数 分页使用
     *
     * @param list
     * @return
     */
    public int getTotalSize(List<Task> list, Variables variables) {
        int totalSize = 0;
        if (list == null) {
            return totalSize;
        }
        for (Task task : list) {
            if (StringUtil.isNotNullOrEmpty(variables.getProcessInstanceId())) {
                if (!variables.getProcessInstanceId().equals(task.getProcessInstanceId())) {
                    continue;
                }
            }

            if (StringUtil.isNotNullOrEmpty(variables.getJsbh())) {
                if (!"000000000".equals(variables.getJsbh())) {
                    if (!variables.getJsbh().equals(task.getProcessVariables().get("jsbh"))) {
                        continue;
                    }
                }

            }
            //任务标识
            if (StringUtil.isNotNullOrEmpty(variables.getTaskDefinitionKey())) {
                if (!variables.getTaskDefinitionKey().equals(task.getTaskDefinitionKey())) {
                    continue;
                }
            }
            if (StringUtil.isNotNullOrEmpty(variables.getRybh())) {
                if (!variables.getRybh().equals(task.getProcessVariables().get("rybh"))) {
                    continue;
                }
            }
            totalSize++;
        }
        return totalSize;
    }

    public void setProcessNumCache(String k, String jsbh, TaskService taskService, RepositoryService repositoryService, RedisUtils redisUtils) {
        if (StringUtils.isNullOrEmpty(jsbh)) {
            return;
        }
        //获取所有已部署流程实例id
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (!StringUtils.isNullOrEmpty(k)) {
            processDefinitionQuery = processDefinitionQuery.processDefinitionId(k);
        }
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
        logger.info("所有已部署流程实例id数量：{} ", processDefinitionList.size());

        // 获取所有待办列表
        for (ProcessDefinition xx : processDefinitionList) {
            Map<String, Integer> countMap = Maps.newHashMap();

            List<Task> list = taskService.createTaskQuery().processVariableValueLike("jsbh", "%" + jsbh + "%").processDefinitionId(xx.getId())
                    .includeProcessVariables().orderByTaskCreateTime().desc().list();
            if (list.size() > 0) {
                logger.info("代办数据ID：{} 代办节点数量：{}", xx.getId(), list.size());
            }
            BpmnModel models = repositoryService.getBpmnModel(xx.getId());
            if (models != null) {
                Collection<FlowElement> flowElements = models.getMainProcess().getFlowElements();
                for (FlowElement e : flowElements) {
                    //过滤名称为空和流程自动生成的ID
                    if (!StringUtils.isNullOrEmpty(e.getName()) && e.getId().length() < 30) {
                        countMap.put(e.getId(), 0);
                    }
                }
            }

            for (Task task : list) {
                if (countMap.get(task.getTaskDefinitionKey()) != null) {
                    int value = countMap.get(task.getTaskDefinitionKey());
                    value = value + 1;
                    countMap.put(task.getTaskDefinitionKey(), value);
                } else {
                    countMap.put(task.getTaskDefinitionKey(), 1);
                }
            }

            if (!StringUtils.isNullOrEmpty(jsbh)) {
                if (countMap.size() > 0) {
                    for (String key : countMap.keySet()) {
                        //redisUtils.set(CacheUtils.CACHE_KEY_FLOWCOUNT + jsbh + "_" + key, countMap.get(key) + "");
                        redisUtils.set(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + key.toUpperCase(), JSON.toJSONString(countMap.get(key)));
                    }

                }
            }


        }
    }

    /**
     * @param jsbh
     * @Description: 初始化流程图节点信息
     * @return: awd.mis.util.Result<?>
     * @Author: 王帅
     * @Date: 2018/4/23 20:58
     **/
    public void flowSynchronization(String jsbh, List<ProcessDefinition> processDefinitionList) {

        for (ProcessDefinition processDefinition : processDefinitionList) {

            flowSynchronization(jsbh, processDefinition);
        }

    }

    /**
     * @param jsbh
     * @param processDefinition
     * @Description: 更新单个流程图和节点信息
     * @return: awd.mis.util.Result<?>
     * @Author: 王帅
     * @Date: 2018/4/23 20:57
     **/
    @Transactional
    public RequestParameters flowSynchronization(String jsbh, ProcessDefinition processDefinition) {
        if (jsbh == null) {
            jsbh = "";
        }
        //获取流程图信息
        FlowMap flowMap = new FlowMap();
        flowMap.setJsbh(jsbh);
        flowMap.setFlag("1");
        flowMap.setMapname(processDefinition.getId());
        flowMap.setMapkey(processDefinition.getKey());
        flowMap.setVersion(processDefinition.getVersion());
        flowMap.setMemo(processDefinition.getName());

        logger.info("流程图介绍Memo:{}", flowMap.getMemo());

        flowMap.setCreator("ws");
        flowMap.setCreatetime(Calendar.getInstance().getTime());
        List<FlowMap> li = flowMapMapper.selectByMapkey(flowMap.getMapkey());
        //如果表中有flowMap则赋值
        if (li != null & li.size() > 0) {
            FlowMap sjk = li.get(0);
            flowMap.setId(sjk.getId());
            flowMap.setMonthlylimit(sjk.getMonthlylimit());
            flowMap.setTimelimit(sjk.getTimelimit());
            flowMap.setMenu(sjk.getMenu());
            flowMap.setMemo(sjk.getMemo());
            flowMap.setMapmutex(sjk.getMapmutex());
            flowMapMapper.update(flowMap);
        } else {
            flowMap.setId(getGuid());
            flowMapMapper.insert(flowMap);
        }

        redisUtils.set(CacheUtils.CACHE_FLOWMAP + flowMap.getMapkey().toUpperCase(), JSON.toJSONString(flowMap));
        //获取节点信息
        BpmnModel models = repositoryService.getBpmnModel(flowMap.getMapname());
        logger.info("节点信息: {}", models.toString());

        FlowNode flowNode = null;
        List<FlowNode> flownodeModelList = new ArrayList<>();
        //准备遍历节点
        if (models != null) {
            Collection<FlowElement> flowElements = models.getMainProcess().getFlowElements();

            for (FlowElement e : flowElements) {
                //去除名称为空 和 流程图自动生成ID 的节点
                if (!StringUtils.isNullOrEmpty(e.getName()) && e.getId().length() < 30) {
                    flowNode = new FlowNode();
                    flowNode.setCreator("管理员");
                    flowNode.setCreatetime(Calendar.getInstance().getTime());
                    flowNode.setJdlx(e.getClass().toString());
                    flowNode.setNodename(e.getName());
                    flowNode.setNodecode(e.getId());
                    flowNode.setRole("");
                    flowNode.setJsbh(jsbh);
                    flownodeModelList.add(flowNode);
                }
            }
            List<FlowNode> nodesOld = flowNodeMapper.selectByFlowMapId(flowMap.getId());
            redisUtils.removePattern(CacheUtils.CACHE_FLOWNODE + flowMap.getMapkey() + "_*");
            if (nodesOld.size() == 0) {
                for (FlowNode _new : flownodeModelList) {
                    _new.setFlowmapid(flowMap.getId());
                    _new.setId(getGuid());
                    flowNodeMapper.insert(_new);
                    redisUtils.set(CacheUtils.CACHE_FLOWNODE + _new.getNodecode().toUpperCase(), JSON.toJSONString(_new));
                }
            } else {
                //新流程节点数量大于数据节点数量
                if (nodesOld.size() < flownodeModelList.size()) {
                    for (FlowNode _new : flownodeModelList) {
                        _new.setFlowmapid(flowMap.getId());

                        boolean flag = true;
                        for (FlowNode _old : nodesOld) {
                            if (_new.getNodecode() != null && _new.getNodecode().equals(_old.getNodecode())) {
                                _new.setId(_old.getId());
                                _new.setRole(_old.getRole());
                                _new.setMenu(_old.getMenu());
                                _new.setMemo(_old.getMemo());
                                flowNodeMapper.update(_new);
                                flag = false;
                                redisUtils.set(CacheUtils.CACHE_FLOWNODE + _new.getNodecode().toUpperCase(), JSON.toJSONString(_new));
                            }
                        }
                        if (flag) {
                            _new.setId(getGuid());
                            redisUtils.set(CacheUtils.CACHE_FLOWNODE + _new.getNodecode().toUpperCase(), JSON.toJSONString(_new));
                            flowNodeMapper.insert(_new);
                        }
                    }
                }
                //新流程节点数量小于等于数据节点数量
                if (nodesOld.size() >= flownodeModelList.size()) {
                    for (FlowNode _old : nodesOld) {
                        boolean flag = true;
                        for (FlowNode _new : flownodeModelList) {
                            if (_new.getNodecode() != null && _new.getNodecode().equals(_old.getNodecode())) {
                                _new.setFlowmapid(flowMap.getId());
                                _new.setId(_old.getId());
                                _new.setRole(_old.getRole());
                                _new.setMenu(_old.getMenu());
                                _new.setMemo(_old.getMemo());
                                flowNodeMapper.update(_new);
                                flag = false;
                                redisUtils.set(CacheUtils.CACHE_FLOWNODE + _new.getNodecode().toUpperCase(), JSON.toJSONString(_new));
                                continue;
                            }
                        }
                        if (flag) {
                            flowNodeMapper.delete(_old.getId());
                            redisUtils.remove(CacheUtils.CACHE_FLOWNODE + _old.getNodecode().toUpperCase());

                        }
                    }
                    flownodeModelList.stream().filter(p -> {
                        p.getNodecode();
                        long i = nodesOld.stream().filter(g -> {
                            return p.getNodecode().equals(g.getNodecode());
                        }).count();
                        if (i > 0) return false;
                        return true;
                    }).forEach(p -> {
                        p.setId(getGuid());
                        p.setFlowmapid(flowMap.getId());
                        flowNodeMapper.insert(p);
                        redisUtils.set(CacheUtils.CACHE_FLOWNODE + p.getNodecode().toUpperCase(), JSON.toJSONString(p));

                    });
                }
            }
        }
        RequestParameters pps = new RequestParameters();
        pps.setJsbh(jsbh);
        pps.setFlowmapEntity(flowMap);
        pps.setFlownodeEntityList(flownodeModelList);
        return pps;
    }

    public FlowNode flowNodeByKey(String key) {
        FlowNode fd = null;
        if (!StringUtils.isNullOrEmpty(key)) {
            fd = flowNodeMapper.selectNodeByCode(key);
        }


        return fd;

    }

    public void updateflowNodeByKey(FlowNode flowNode) {
        flowNodeMapper.updateByCODE(flowNode);
    }

    public static int Guid = 10000;

    public static String getGuid() {

        WorkflowService.Guid += 1;

        long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(now);
        String info = now + "";
        int ran = 0;
        if (WorkflowService.Guid > 9999) {
            WorkflowService.Guid = 1000;
        }
        ran = WorkflowService.Guid;

        return time + info.substring(2, info.length()) + ran;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void execute(String taskId, Map<String, String> var) {
        formService.submitTaskFormData(taskId, var);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ProcessInstance start(String id, Map<String, String> var) {
        return formService.submitStartFormData(id, var);
    }


}
