package awd.mis.activiti.controller;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.entity.Variables;
import awd.mis.activiti.sevice.WorkflowService;
import awd.mis.activiti.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by watchdb on 2018/1/1.
 */
@Api(description = "流程控制相关操作", tags = {"workflow"})
@RestController
@RequestMapping(value = "/workflow")
public class WorkflowController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private RedisUtils redisUtils;

    Logger logger = LoggerFactory.getLogger(WorkflowController.class);
    /**
     * 定长线程池
     */
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    /**
     * 新启流程
     *
     * @param processDefinitionId 流程定义ID,带有版本号的，例如：jbxx:1:1234
     * @param currentUserId       当前用户ID
     * @param variables           传递参数集合
     * @return
     */
    @ApiOperation("新启流程")
    @PostMapping("/start")
    @ResponseBody
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public R start(@RequestParam(name = "processDefinitionId", required = true) String processDefinitionId,
                   @RequestParam(name = "currentUserId", required = true) String currentUserId,
                   @RequestBody Variables variables) {
        System.err.println("processDefinitionId==" + processDefinitionId);
        R res = this.validataParams(variables, "");
        if (!"1".equals(res.get("code"))) {
            return res;
        }
        // TODO 判断此人是否在流程中

        Map<String, String> var = ProcessUtil.processParams(variables);
        /**
         * 设置当前操作人
         */
        identityService.setAuthenticatedUserId(currentUserId);
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        if (StringUtils.isNullOrEmpty(processDefinition)) {
            return R.error("流程ID不正确！！");
        }
        logger.info("流程信息 {}", processDefinition.toString());

        /**
         * 提交表单，开始执行流程
         */
        String s = null;
        logger.info("开始执行流程,流程ID {}", processDefinitionId);
        logger.info("开始执行流程,流程参数 {}", JSON.toJSONString(var));

        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), var);
        fixedThreadPool.execute(() -> {
                    workflowService.setProcessNumCache(processDefinitionId, variables.getJsbh(), taskService, repositoryService, redisUtils);
                    ThreadPoolExecutor tpe = ((ThreadPoolExecutor) fixedThreadPool);
                    int queueSize = tpe.getQueue().size();
                    logger.info("当前排队线程数 {}", queueSize);

                    int activeCount = tpe.getActiveCount();
                    logger.info("当前活动线程数 {}", activeCount);

                    long completedTaskCount = tpe.getCompletedTaskCount();
                    logger.info("执行完成线程数 {}", completedTaskCount);

                    long taskCount = tpe.getTaskCount();
                    logger.info("总线程数 {}", taskCount);

                }
        );
        return R.ok().put("processId", processInstance.getId());
    }

    /**
     * description:新启多个流程
     *
     * @return awd.mis.activiti.utils.R
     * @params variablesList
     * @author by: WS
     * @createtime: 2019/11/27
     */
    @ApiOperation("新启多个流程")
    @PostMapping("/starts")
    @ResponseBody
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public R start(@RequestParam(name = "processDefinitionId", required = true) String processDefinitionId,
                   @RequestParam(name = "jsbh", required = true) String jsbh,
                   @RequestBody List<Variables> variablesList) {
        List<String> li = Lists.newArrayList();
        List<Map<String, String>> resultMapList = Lists.newArrayList();
        for (Variables variables : variablesList) {
            if (StringUtils.isNullOrEmpty(variables.getParams().get("rybh")) || StringUtils.isNullOrEmpty(variables.getJsbh())) {
                continue;
            }
            Map<String, String> var = ProcessUtil.processParams(variables);
            /**
             * 设置当前操作人
             */
            identityService.setAuthenticatedUserId("流程创建者");
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();
            if (StringUtils.isNullOrEmpty(processDefinition)) {
                logger.warn("流程办理信息 {}", variables.getParams().get("rybh") + "流程办理失败");
            } else {
                /**
                 * 提交表单，开始执行流程
                 */
                logger.info("开始执行流程,流程ID {},参数 {}", JSON.toJSONString(var));
                ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), var);
                Map<String, String> resultMap = Maps.newHashMap();
                resultMap.put(variables.getParams().get("rybh") + "", processInstance.getId());
                resultMapList.add(resultMap);
            }
        }

        fixedThreadPool.execute(() -> {
                    workflowService.setProcessNumCache(processDefinitionId, jsbh, taskService, repositoryService, redisUtils);
                    ThreadPoolExecutor tpe = ((ThreadPoolExecutor) fixedThreadPool);
                    int queueSize = tpe.getQueue().size();
                    logger.info("当前排队线程数 {}", queueSize);

                    int activeCount = tpe.getActiveCount();
                    logger.info("当前活动线程数 {}", activeCount);

                    long completedTaskCount = tpe.getCompletedTaskCount();
                    logger.info("执行完成线程数 {}", completedTaskCount);

                    long taskCount = tpe.getTaskCount();
                    logger.info("总线程数 {}", taskCount);

                }
        );
        return R.ok().put("result", resultMapList);
    }

    /**
     * 执行流程任务
     *
     * @param taskId    任务ID
     * @param variables 传递参数集合
     * @return
     */
    @ApiOperation("执行流程任务")
    @PostMapping("/execute")
    @ResponseBody
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public R execute(@RequestParam(name = "taskId", required = true) String taskId,
                     @RequestBody(required = false) Variables variables) {
        R res = this.validataParams(variables, "");
        if (!"1".equals(res.get("code"))) {
            return res;
        }
        String processId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessDefinitionId();
        Map<String, String> var = ProcessUtil.processParams(variables);
        logger.info("获取到的参数 {}", JSONUtil.toJson(var));
        formService.submitTaskFormData(taskId, var);
        System.err.println(processId);
        fixedThreadPool.execute(() -> {
                    workflowService.setProcessNumCache(processId, variables.getJsbh(), taskService, repositoryService, redisUtils);
                    ThreadPoolExecutor tpe = ((ThreadPoolExecutor) fixedThreadPool);
                    int queueSize = tpe.getQueue().size();
                    logger.info("当前排队线程数 {}", queueSize);

                    int activeCount = tpe.getActiveCount();
                    logger.info("当前活动线程数 {}", activeCount);

                    long completedTaskCount = tpe.getCompletedTaskCount();
                    logger.info("执行完成线程数 {}", completedTaskCount);

                    long taskCount = tpe.getTaskCount();
                    logger.info("总线程数 {}", taskCount);

                }
        );

        return R.ok();
    }

    /**
     * description:批量执行流程任务
     *
     * @return awd.mis.activiti.utils.R
     * @params taskId
     * variablesList
     * @author by: WS
     * @createtime: 2019/11/21
     */
    @ApiOperation("批量执行流程任务")
    @PostMapping("/executes")
    @ResponseBody
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public R executes(@RequestBody(required = false) List<Variables> variablesList) {
        if (variablesList.size() > 0) {
            for (Variables variables : variablesList) {
                R res = this.validataParams(variables, "taskID");
                if (!"1".equals(res.get("code"))) {
                    return res;
                }
            }
            String processId = taskService.createTaskQuery().taskId(variablesList.get(0).getTaskId()).singleResult().getProcessDefinitionId();
            String jsbh = variablesList.get(0).getJsbh();
            variablesList.stream().parallel().forEach(variables -> {
                Map<String, String> var = ProcessUtil.processParams(variables);
                logger.info("批量执行获取到的参数 {}", JSONUtil.toJson(var));
                formService.submitTaskFormData(variables.getTaskId(), var);
            });
            fixedThreadPool.execute(() -> {
                        workflowService.setProcessNumCache(processId, jsbh, taskService, repositoryService, redisUtils);
                        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) fixedThreadPool);
                        int queueSize = tpe.getQueue().size();
                        logger.info("当前排队线程数 {}", queueSize);

                        int activeCount = tpe.getActiveCount();
                        logger.info("当前活动线程数 {}", activeCount);

                        long completedTaskCount = tpe.getCompletedTaskCount();
                        logger.info("执行完成线程数 {}", completedTaskCount);

                        long taskCount = tpe.getTaskCount();
                        logger.info("总线程数 {}", taskCount);

                    }
            );
        }

        return R.ok();
    }

    /**
     * 查询用户待办任务
     *
     * @param userId  用户的ID
     * @param roleIds 用户的角色ID，可以为多个，用逗号隔开
     */
    @ApiOperation("查询用户待办任务")
    @RequestMapping(value = "/findPersonalTaskList", method = RequestMethod.POST)
    @ResponseBody
    public Result findPersonalTaskList(@RequestParam(name = "userId", required = false) String userId,
                                       @RequestParam(name = "roleIds", required = false) String roleIds,
                                       @RequestBody Variables variables) {


        String page = "0";
        String rows = "20";
        logger.info("接收到的参数：{} ", JSONObject.toJSONString(variables));
        if (variables == null || StringUtils.isNullOrEmpty(variables.getJsbh())) {
            return ResultUtils.error("监所编号必传！！");
        }
        if ("".equals(variables.getTaskDefinitionKey())) {
            variables.setTaskDefinitionKey(null);
        }
        R r = R.ok();
        // 创建查询对象，处理过滤参数
        boolean keyIn = false;//过滤不想查出来的processInstanceKey
        String keyValue = "";
        if (variables.getParams() != null && !StringUtil.isNullOrEmpty(variables.getParams().get("in"))) {
            keyValue = new String(variables.getParams().get("in").toString());
            variables.getParams().remove("in");
            keyIn = true;
        }

        TaskQuery taskQuery = workflowService.processQueryCondition(taskService, variables).includeProcessVariables();
        List<Variables> taskList = new ArrayList<>();
        if (keyIn) {
            taskQuery = taskQuery.processDefinitionKeyIn(new ArrayList<>(Arrays.asList(keyValue.split(","))));
        }

        if (!StringUtils.isNullOrEmpty(variables.getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(variables.getProcessInstanceId());
        }
        if (!StringUtils.isNullOrEmpty(variables.getStart())) {
            page = variables.getStart();
        }
        if (!StringUtils.isNullOrEmpty(variables.getLimit())) {
            rows = variables.getLimit();
        }
        Long count = taskQuery.count();
        List<Task> li = taskQuery.orderByTaskCreateTime().desc().listPage(
                Integer.parseInt(page),
                Integer.parseInt(rows)
        );
        List<Variables> list = workflowService.processResult(li);
        return ResultUtils.ok(count, list);
    }

    /**
     * @param userId
     * @param roleIds
     * @param rows
     * @param page
     * @param variables
     * @Description: 获取流程实例Key最近的一条实例
     * @return: awd.framework.plugins.springboot.activiti.utils.Result
     * @Author: 王帅
     * @Date: 2018/8/13 10:07
     **/
    @ApiOperation("查询用户待办任务_最后一条记录")
    @RequestMapping(value = "/findPersonalLastTaskList", method = RequestMethod.POST)
    @ResponseBody
    public Result findPersonalLastTaskList(@RequestParam(name = "userId", required = false) String userId,
                                           @RequestParam(name = "roleIds", required = false) String roleIds,
                                           @RequestParam(value = "rows", required = false, defaultValue = "10") String rows,
                                           @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                           @RequestBody Variables variables) {
        if (variables == null || StringUtils.isNullOrEmpty(variables.getJsbh())) {
            return ResultUtils.error("监所编号必传！！");
        }
        if (StringUtils.isNullOrEmpty(variables.getProcessDefinitionKey())) {
            return ResultUtils.error("流程实例Key没传！！");
        }
        if (StringUtils.isNullOrEmpty(variables.getRybh())) {
            return ResultUtils.error("请输入人员编号！！");
        }
        if ("".equals(variables.getTaskDefinitionKey())) {
            variables.setTaskDefinitionKey(null);
        }
        R r = R.ok();
        // 创建查询对象，处理过滤参数

        TaskQuery taskQuery = workflowService.processQueryCondition(taskService, variables);

        List<Variables> taskList = new ArrayList<>();
        // 获取查询列表
        List<Task> list = taskQuery.includeProcessVariables().taskDefinitionKey(variables.getTaskDefinitionKey())
                .orderByTaskCreateTime().asc().list();
        // 处理返回结果
        taskList = workflowService.processResult(list);
        //按时间排序
        Map<String, List<Variables>> map = Maps.newHashMap();
        for (Variables variables1 : taskList) {
            if (StringUtils.isNullOrEmpty(map.get(variables1.getProcessInstanceId()))) {
                List<Variables> li = Lists.newArrayList();
                li.add(variables1);
                map.put(variables1.getProcessInstanceId(), li);
            } else {
                List<Variables> li = Lists.newArrayList(map.get(variables1.getProcessInstanceId()));
                li.add(variables1);
                map.put(variables1.getProcessInstanceId(), li);

            }
        }

        Map<String, List<Variables>> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> b.get(0).getCreatetime().compareTo(a.get(0).getCreatetime())))
                .forEach(x -> result.put(x.getKey(), x.getValue()));

        if (StringUtils.isNullOrEmpty(result) || result.size() == 0) {
            return ResultUtils.ok();

        } else {
            return ResultUtils.ok(result.entrySet().iterator().next().getValue());

        }
    }


    /**
     * 挂起流程
     *
     * @param processDefinitionKey
     * @return
     */
    @ApiOperation("挂起流程")
    @PostMapping("/suspendProcess")
    @ResponseBody
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public R suspendProcess(String processDefinitionKey) {
        // 根据流程定义的key暂停一个流程定义
        repositoryService.suspendProcessDefinitionByKey(processDefinitionKey, true, null);
        return R.ok();
    }

    /**
     * 激活流程
     *
     * @param processDefinitionKey
     * @return
     */
    @ApiOperation("激活流程")
    @PostMapping("/v")
    @ResponseBody
    public R activateProcess(String processDefinitionKey) {
        repositoryService.activateProcessDefinitionByKey(processDefinitionKey, true, null);
        return R.ok();
    }

    /**
     * 删除流程
     *
     * @param processDefinitionKey
     * @return
     */
    @ApiOperation("删除流程定义,谨慎操作。")
    @GetMapping("/deleteProcess")
    @ResponseBody
    public R deleteProcess(@RequestParam(value = "processDefinitionKey", required = true) String processDefinitionKey) {
        // 先使用流程定义的key查询流程定义，查询出所有的版本
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)// 使用流程定义的key查询
                .list();
        // 遍历，获取每个流程定义的部署ID
        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                // 获取部署ID
                String deploymentId = pd.getDeploymentId();
                /**
                 * 级联删除 不管流程是否启动，都可以删除
                 */
                repositoryService.deleteDeployment(deploymentId, true);
            }
        }
        return R.ok();
    }

    /**
     * 根据流程名称，查询所有代办数量 如果流程信息未传递则默认查询所有代办
     *
     * @param processDefinitionKey 流程名称key
     * @param jsbh                 用户角色
     * @return
     */
    @ApiOperation("查询具体流程代办总数")
    @PostMapping("/getTotalOfProcess")
    @ResponseBody
    public Result getTotalOfProcess(@RequestParam("jsbh") String jsbh, @RequestParam("processDefinitionKey") String processDefinitionKey) {

        if (StringUtils.isNullOrEmpty(jsbh)) {
            return ResultUtils.error("监所编号必传！！");
        }
        if (StringUtils.isNullOrEmpty(processDefinitionKey)) {
            return ResultUtils.error("流程KE必传！！");
        }
        Variables variables = new Variables();
        variables.setJsbh(jsbh);
        variables.setProcessDefinitionKey(processDefinitionKey);
        TaskQuery taskQuery = taskService.createTaskQuery();

        List<Variables> taskList = new ArrayList<>();
        // 获取查询列表
        List<Task> list = taskQuery.includeProcessVariables().orderByTaskCreateTime().desc().list();

        // 分页查询
        list = taskQuery.includeProcessVariables().processDefinitionKey(processDefinitionKey)
                .orderByTaskCreateTime().desc()
                .list();

        Map<String, Integer> map = Maps.newHashMap();
        for (Task task : list) {
            Map<String, Object> o = task.getProcessVariables();
            if (o.get("rybh") != null) {
                if (map.get(o.get("rybh").toString()) == null) {
                    map.put(o.get("rybh").toString(), 0);
                } else {
                    map.put(o.get("rybh").toString(), map.get(o.get("rybh").toString()) + 1);
                }
            }

        }
        return ResultUtils.ok(map);
    }

    @ApiOperation("使用流程定义ID查询流程名称")
    @PostMapping("/getProName")
    @ResponseBody
    public R getProNameByProId(
            @RequestParam(name = "processDefinitionId", required = false) String processDefinitionId) {
        if (StringUtil.isNullOrEmpty(processDefinitionId)) {
            return R.error("流程实例ID未传递！");
        }
        return workflowService.getProNameByProId(processDefinitionId, repositoryService);
    }

    /**
     * 根据流程instanceID停止该流程,操作不可逆请谨慎
     *
     * @param processInstanceId
     * @return
     */
    @ApiOperation("使用流程实例ID删除当个正在执行的流程,操作不可逆请谨慎")
    @PostMapping("/delete")
    @ResponseBody
    public R deleteProcessByInstanceId(@RequestParam(value = "processInstanceId") String processInstanceId,
                                       @RequestParam(value = "why") String why) {

        redisUtils.removePattern("FLOWCOUNT_*");
        //获取该人的这个流程信息，更新缓存
        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).includeProcessVariables().list();
        for (Task t : list) {

            String jsbh = t.getProcessVariables().get("jsbh").toString();
            String taskDefinitionKey = t.getTaskDefinitionKey();
            String num = redisUtils.get(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + taskDefinitionKey.toUpperCase());
            logger.info("删除的Redis键值：{}", CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + taskDefinitionKey);
            logger.info("删除的Redis键值对应的数字：{}", num);
            if (Integer.parseInt(num) == 1) {
                redisUtils.set(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + taskDefinitionKey.toUpperCase(), "0");
            } else if (Integer.parseInt(num) > 1) {
                redisUtils.set(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + taskDefinitionKey.toUpperCase(), (Integer.parseInt(num) - 1) + "");

            }
            System.err.println(redisUtils.get(CacheUtils.CACHE_FLOWNODE_COUNT + jsbh + "_" + taskDefinitionKey));
        }

        runtimeService.deleteProcessInstance(processInstanceId, why);// 删除流程

        return R.ok();
    }

    /**
     * 停止该人的所有正在执行流程,操作不可逆请谨慎
     *
     * @param rybh
     * @return
     */
    @ApiOperation("根据人员编号停止某一个人的所有正在执行的流程,操作不可逆请谨慎")
    @PostMapping("/deleteAll")
    @ResponseBody
    public R deleteAllProcessByRybh(@RequestParam(value = "rybh") String rybh, @RequestParam("notDeleting") String processDefinitionId, @RequestParam("why") String why) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.includeProcessVariables().processVariableValueEquals("rybh", rybh).orderByTaskCreateTime().desc().list();
        Map<String, String> map = Maps.newHashMap();
        List<Variables> result = Lists.newArrayList();
        list.stream().filter(p -> !p.getProcessDefinitionId().equals(processDefinitionId)).forEach(
                task -> {
                    map.put(task.getProcessInstanceId(), task.getProcessVariables().get("jsbh") + "");
                    Variables var = new Variables();
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
                    result.add(var);
                }
        );

        for (String key : map.keySet()) {
            runtimeService.deleteProcessInstance(key, why);
            try {
                fixedThreadPool.execute(() -> workflowService.setProcessNumCache(key.split("_")[0], map.get(key), taskService, repositoryService, redisUtils));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return R.ok().put("data", result);
    }
    /**
     * 查询多个指定流程节点的值
     *
     * @param userId  用户的ID
     * @param roleIds 用户的角色ID，可以为多个，用逗号隔开
     * @author zw
     */
    @ApiOperation("查询多个指定流程节点的值")
    @RequestMapping(value = "/findPersonalTaskListWithTaskDefinedKey", method = RequestMethod.POST)
    @ResponseBody
    public Result findPersonalTaskListWithTaskDefinedKey(@RequestParam(name = "userId", required = false) String userId,
                                                         @RequestParam(name = "roleIds", required = false) String roleIds,
                                                         @RequestParam(value = "rows", required = false, defaultValue = "10") String rows,
                                                         @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                                         @RequestBody Variables variables) {


        logger.info("接收到的参数：{}" ,JSONObject.toJSONString(variables));
        if (variables == null || StringUtils.isNullOrEmpty(variables.getJsbh())) {
            return ResultUtils.error("监所编号必传！！");
        }
        if ("".equals(variables.getTaskDefinitionKey())) {
            variables.setTaskDefinitionKey(null);
        }
        R r = R.ok();
        // 创建查询对象，处理过滤参数
        boolean keyIn = false;//过滤不想查出来的processInstanceKey
        String keyValue = "";
        if (variables.getParams() != null && !StringUtil.isNullOrEmpty(variables.getParams().get("in"))) {
            keyValue = new String(variables.getParams().get("in").toString());
            variables.getParams().remove("in");
            keyIn = true;
        }
        TaskQuery taskQuery = workflowService.findPersonalTaskListWithTaskDefinedKey(taskService, variables);
        List<Variables> taskList = new ArrayList<>();

        if (keyIn) {
            taskQuery = taskQuery.processDefinitionKeyIn(new ArrayList<>(Arrays.asList(keyValue.split(","))));
        }
        // 获取查询列表
        List<Task> list = taskQuery.includeProcessVariables().orderByTaskCreateTime().desc().list();
        System.err.println(list);
        if (list.size() == 0) {
            return ResultUtils.ok(0, taskList);
        }
//        if (!StringUtils.isNullOrEmpty(variables.getStart())) {
//            page = variables.getStart();
//        }
//        if (!StringUtils.isNullOrEmpty(variables.getLimit())) {
//            rows = variables.getLimit();
//        }
        if (!StringUtils.isNullOrEmpty(variables.getProcessInstanceId())) {
            taskQuery = taskQuery.processInstanceId(variables.getProcessInstanceId());
        }
        list = taskQuery.includeProcessVariables().orderByTaskCreateTime().desc().listPage(
                //传递多个taskdefinedKey会导致查询不出来数据，所以先不分页，将查询结果完整返回后分页
                Integer.parseInt("0"),
                Integer.parseInt("99999")
        );
        // 处理返回结果
        System.err.println("*******开始处理结果******");
        taskList = workflowService.processResultWithTaskDefinitionKey(list, taskList, variables);
        //TODO待完善
       /* //手动分页
        if(Integer.parseInt(rows) == taskList.size()) {
        	rows= String.valueOf(taskList.size()-1);
        }
        if(Integer.parseInt(page) == taskList.size()) {
        	page= String.valueOf(taskList.size()-1);
        }
        List<Variables> pageList = taskList.subList(Integer.parseInt(page), Integer.parseInt(rows));*/

        return ResultUtils.ok(taskList.size(), taskList);
    }

    @ApiOperation("查询待办任务(管理界面)")
    @RequestMapping(value = "/findTaskList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result findTaskList(@RequestParam(name = "jsbh", required = false) String jsbh,
                               @RequestParam(name = "rybh", required = false) String rybh,
                               @RequestParam(name = "key", required = false) String key,
                               @RequestParam(value = "rows") String rows,
                               @RequestParam(value = "page") String page) {

        Variables variables = new Variables();
        R r = R.ok();
        // 创建查询对象，处理过滤参数

        TaskQuery taskQuery = workflowService.processQueryCondition(taskService, variables).includeProcessVariables();

        if (!StringUtil.isNullOrEmpty(rybh)) {
            taskQuery = taskQuery.processVariableValueEquals("rybh", rybh);
        }
        if (!StringUtil.isNullOrEmpty(jsbh)) {
            taskQuery = taskQuery.processVariableValueEquals("jsbh", jsbh);
        }
        if (!StringUtil.isNullOrEmpty(key)) {
            taskQuery = taskQuery.processDefinitionKey(key);
        }

        List<Variables> taskList = new ArrayList<>();
        // 获取查询列表

        // 分页处理
        Long totalSize = taskQuery.count();

        int start = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);

        int end = (Integer.parseInt(page)) * Integer.parseInt(rows);
        // 分页查询
        List<Task> list = taskQuery.orderByTaskCreateTime().desc().listPage(start, end);

        for (Task task : list) {
            Variables var = new Variables();
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
            taskList.add(var);
        }
        return ResultUtils.ok(totalSize, taskList);
    }

    /**
     * 根据processID查询当前流程所有节点
     *
     * @return
     */
    @ApiOperation("根据processID查询当前流程所有节点")
    @RequestMapping(value = "/findElementList", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> findNodeList(@RequestParam(value = "processID", required = true) String processID) {
        BpmnModel model = repositoryService.getBpmnModel(processID);
        /*List<Map<String,String>> list=Lists.newArrayList();*/
        Map<String, String> m = Maps.newHashMap();
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement e : flowElements) {
                if (!StringUtils.isNullOrEmpty(e.getName())) {
                    m.put(e.getId(), e.getName());
                }
                logger.info("flowelement id:{} name:{}", e.getId(), e.getName());
            }
        }


        return m;
    }

    private R validataParams(Variables variables, String taskID) {

        String jsbh = variables.getJsbh();
        if (StringUtil.isNullOrEmpty(jsbh)) {
            return R.error("100", "缺失监所编号");
        }

        if (variables.getRybh() == null) {
            return R.error("100", "缺失人员编号");
        }
        if (variables.getParams() == null) {
            return R.error("100", "params参数缺失（所内编号、监室号、姓名）");
        }
        Map<String, Object> params = variables.getParams();

        if (StringUtil.isNullOrEmpty(params.get("xm"))) {
            return R.error("100", "params参数缺失（姓名）");

        }
        if (StringUtil.isNullOrEmpty(params.get("jsh"))) {
            return R.error("100", "params参数缺失（监室号）");

        }
        if (StringUtil.isNullOrEmpty(params.get("snbh"))) {
            return R.error("100", "params参数缺失（所内编号）");

        }
        if ("taskID".equals(taskID)) {
            if (StringUtil.isNullOrEmpty(variables.getTaskId())) {
                return R.error("100", "taskID未传入！");

            }
        }
        return R.ok();
    }

}
