package awd.mis.servers.api.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.mis.servers.api.LogsServiceApi;
import feign.hystrix.FallbackFactory;


@Service("logsService")
public class LogsApiFallBackFactory implements FallbackFactory<LogsServiceApi> {


    public static final Logger logger = LoggerFactory.getLogger(LogsServiceApi.class);

    @Override
    public LogsServiceApi create(Throwable cause) {

        return new LogsServiceApi() {

			@Override
			public awd.framework.common.controller.message.ResponseMessage<String> loginlogsSave(
					awd.mis.servers.vo.LoginlogsModel loginlogs) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public awd.framework.common.controller.message.ResponseMessage<awd.framework.common.entity.PagerResult<awd.mis.servers.vo.LoginlogsModel>> loginlogsList(
					awd.framework.common.datasource.orm.core.param.QueryParam param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public awd.framework.common.controller.message.ResponseMessage<String> loginout(String id) {
				// TODO Auto-generated method stub
				return null;
			}

        };
    }

}
