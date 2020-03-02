package awd.mis.activiti.controller;

import java.util.List;

import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.utils.CacheUtils;
import awd.mis.activiti.utils.R;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import awd.framework.common.utils.OpenAPI;
import awd.mis.activiti.entity.RequestParameters;
import awd.mis.activiti.sevice.FlowmapService;
import awd.mis.activiti.sevice.WorkflowService;
import awd.mis.activiti.utils.Result;
import awd.mis.activiti.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/activiti")
@Api(tags = "awd-activiti", description = "流程初始化功能")
public class InitActivitiCacheController {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private FlowmapService flowmapService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 1、缓存流程节点的接口
     * <p>
     * 2、缓存流程节点代办数量的接口
     */
    @SuppressWarnings("rawtypes")
    @OpenAPI()
    @ApiOperation("将现有流程信息全部保存到我们自己的表中和Redis缓存中,无流程图修改只需执行一边")
    @RequestMapping(value = "/saveFlowMapNodeCache", method = RequestMethod.POST)
    public Result updateFlow(@RequestParam(value = "jsbh", required = false) String jsbh) {

        boolean flag = flowmapService.initActiviti(jsbh);
		if(flag){
		    return ResultUtils.ok("流程数据更新成功");
        }
        return ResultUtils.error("流程更新失败");
    }

    /**
     * 缓存待办数量
     *
     * @param userId  暂未启用
     * @param roleIds 暂未启用
     * @param jsbh    参数监所编号 必传
     * @return
     */
    @ApiOperation("根据监所编号缓存流程待办数量")
    @GetMapping("/setProcessNumCache")
    @ResponseBody
    public R setProcessNumCache(@RequestParam(name = "userId", required = false) String userId,
                                @RequestParam(name = "roleIds", required = false) String roleIds,
                                @RequestParam(name = "jsbh") String jsbh) {
        if (StringUtils.isNullOrEmpty(jsbh)) {
            return R.error("监所编号必传！！");
        }
        // 创建查询对象，处理过滤参数
        workflowService.setProcessNumCache("",jsbh, taskService, repositoryService, redisUtils);

        return R.ok();
    }

    /**
     * 缓存待办数量和数据缓存到数据库
     *
     * @param jsbh    参数监所编号 必传
     * @return
     */
    @ApiOperation("根据监所编号缓存流程待办数量")
    @GetMapping("/updateFlowAndsetProcessNumCache")
    @ResponseBody
    public String updateFlowAndsetProcessNumCache(@RequestParam(name = "jsbh",required = false) String jsbh) {
        if (StringUtils.isNullOrEmpty(jsbh)) {
            boolean flag = flowmapService.initActiviti(jsbh);
        }else{
            boolean flag = flowmapService.initActiviti(jsbh);
            workflowService.setProcessNumCache("",jsbh, taskService, repositoryService, redisUtils);
        }
        // 创建查询对象，处理过滤参数

        return "success";
    }
}
