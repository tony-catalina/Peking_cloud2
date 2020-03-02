package awd.cloud.platform.servers.analyse.api.hystrix;

import awd.cloud.platform.servers.analyse.api.AwdapiService;
import awd.cloud.platform.servers.analyse.model.kss.JqModel;
import awd.cloud.platform.servers.analyse.model.kss.TsdjModel;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.cloud.platform.servers.analyse.model.kss.*;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("managerService")
public class AwdapiServiceBackFactory implements FallbackFactory<AwdapiService> {

    Logger logger = LoggerFactory.getLogger(AwdapiService.class);

    @Override
    public AwdapiService create(Throwable throwable) {
        return new AwdapiService() {

            @Override
            public ResponseMessage<PagerResult<JqModel>> kss_jq_query(QueryParamEntity param) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JbxxModel>> kss_jbxx_query(QueryParamEntity param) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<TsdjModel>> kss_tsdj_query(QueryParamEntity tsParam) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<TsdjModel>> kss_lshj_query(QueryParamEntity lsParam) {
                throwable.printStackTrace();
                return null;
            }


            @Override
            public ResponseMessage<PagerResult<JshjModel>> getThjyQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getyqQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getjbqkQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getfssgQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getemdjQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getjstzQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getjbxxQuery(QueryParamEntity queryParamEntity) {
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JshjModel>> getJshjQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<LshjModel>> getLshjQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<PjdjModel>> getPjdjQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<ClcsModel>> getClcsQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<TafModel>> getTafQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<ZdryModel>> getZdryQuery(QueryParamEntity queryParamEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JjxModel>> getJjxList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<ShgxModel>> getShgxList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<TsdjModel>> getTsdjList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<HjbdModel>> getHjbdList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<XjModel>> getXjList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<KssModel>> getClgyfxList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JbxxModel>> getGjfxList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JbxxModel>> getGyqxfxList(QueryParamEntity paramEntity) {
                throwable.printStackTrace();
                return null;
            }


        };
    }
}
