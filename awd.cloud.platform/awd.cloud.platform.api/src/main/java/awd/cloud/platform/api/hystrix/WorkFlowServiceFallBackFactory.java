package awd.cloud.platform.api.hystrix;

import awd.bj.base.model.R;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.utils.Result;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("workFlowService")
public class WorkFlowServiceFallBackFactory implements FallbackFactory<WorkFlowService> {
    public static Logger logger = Logger.getLogger(WorkFlowService.class);
    @Override
    public WorkFlowService create(Throwable throwable) {

        return new WorkFlowService() {

            @Override
            public R start(String processDefinitionId, String currentUserId, Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public R starts(String processDefinitionId, String currentUserId, String sqr, Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public R execute(String taskId, Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public R deleteAllProcessByRybh(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public R deleteProcessByInstanceId(String processInstanceId,String why) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public Result findPersonalTaskList(String userId, String roleIds, Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public R getTotalOfProcess(String roleIds, Variables variables, String processName) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }

            @Override
            public R getProNameByProId(String processDefinitionId) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return R.error("流程服务调用失败");
            }
        };
    }
}
