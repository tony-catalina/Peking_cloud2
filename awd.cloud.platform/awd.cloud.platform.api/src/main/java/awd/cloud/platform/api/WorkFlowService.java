package awd.cloud.platform.api;

import awd.bj.base.model.R;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.hystrix.WorkFlowServiceFallBackFactory;
import awd.cloud.platform.utils.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${awd.api.activiti:AWD-MIS-ACTIVITI-SERVER}",url="192.168.4.50:4002",fallbackFactory = WorkFlowServiceFallBackFactory.class)
@Component
public interface WorkFlowService {
    /**
     * 开启流程
     */
    @PostMapping("/workflow/start")
    public R start(@RequestParam(name = "processDefinitionId", required = true) String processDefinitionId,
                   @RequestParam(name = "currentUserId", required = true) String currentUserId,
                   @RequestBody Variables variables);

    /**
     * 根据消息启动不同节点
     */
    @PostMapping("/workflow/starts")
    public R starts(@RequestParam(value = "processDefinitionId", required = true) String processDefinitionId,
                    @RequestParam(value = "currentUserId", required = true) String currentUserId,
                    @RequestParam(value = "sqr", required = true) String sqr,
                    @RequestBody Variables variables);

    /**
     * 执行流程任务
     *
     * @param taskId    任务ID
     * @param variables 传递参数集合
     * @return
     */
    @PostMapping("/workflow/execute")
    public R execute(@RequestParam(name = "taskId", required = true) String taskId,
                     @RequestBody(required = false) Variables variables);

    /**
     * 删除除出所管理当前人员的所有流程
     */
    @PostMapping("/workflow/deleteAll")
    public R deleteAllProcessByRybh(@RequestParam(value = "rybh") String rybh);

    /**
     * 删除当前人员的在当前的流程
     */
    @PostMapping("/workflow/delete")
    public R deleteProcessByInstanceId(@RequestParam(value = "processInstanceId") String processInstanceId,@RequestParam(value = "why") String why);


    /**
     * 查询用户代办任务
     */
    @PostMapping("/workflow/findPersonalTaskList")
    public Result findPersonalTaskList(@RequestParam(name = "userId", required = false) String userId,
                                       @RequestParam(name = "roleIds", required = false) String roleIds,
                                       @RequestBody(required = false) Variables variables);
    /**
     * 查询具体流程代办总数
     */
    @PostMapping("/workflow/getTotalOfProcess")
    public R getTotalOfProcess(@RequestParam(name = "roleIds", required = false) String roleIds, @RequestBody Variables variables, @RequestParam(name = "process", required = false) String processName);


    @PostMapping("/workflow/getProName")
    public R getProNameByProId(@RequestParam(name = "processDefinitionId", required = false) String processDefinitionId);
}
