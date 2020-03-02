package awd.mis.servers.api.hystrix;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.api.ManagerService;
import awd.mis.servers.model.UserinfoModel;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("managerService")
public class ManagerServiceBackFactory implements FallbackFactory<ManagerService> {

    Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Override
    public ManagerService create(Throwable throwable) {
        return new ManagerService() {
            @Override
            public ResponseMessage<List<UserinfoModel>> getUserByRole(String jsbh, String role) {
                throwable.printStackTrace();
                logger.info("getUserByRole进入熔断器: {}", throwable.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<List<UserinfoModel>> getUserByGroup(String jsbh, String group) {
                throwable.printStackTrace();
                logger.info("getUserByGroup进入熔断器: {}", throwable.getMessage());

                return null;
            }

            @Override
            public ResponseMessage<PagerResult<UserinfoModel>> userInfoQuery(QueryParam params) {
                throwable.printStackTrace();
                logger.info("userInfoQuery进入熔断器: {}", throwable.getMessage());

                return null;
            }

			@Override
			public ResponseMessage<List<UserinfoModel>> getUserByRoles(String jsbhs, String roles) {
				throwable.printStackTrace();
                logger.info("getUserByRoles进入熔断器: {}", throwable.getMessage());
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<UserinfoModel>> getUsersToDocument(String jsbhs, String usertype) {
				throwable.printStackTrace();
                logger.info("getUsersToDocument进入熔断器: {}", throwable.getMessage());
				return null;
			}

        };
    }
}
