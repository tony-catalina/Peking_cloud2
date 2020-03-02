package awd.cloud.platform.api.hystrix;

import awd.bj.kss.model.MjjbxxModel;
import awd.bj.manager.model.DictionaryModel;
import awd.bj.manager.model.UserinfoModel;
import awd.cloud.platform.model.ApidocModel;
import awd.cloud.platform.model.manager.*;
import awd.framework.common.entity.param.QueryParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

@Service("managerService")
public class ManagerFallBackFactory implements FallbackFactory<ManagerService> {

    public static final Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Override
    public ManagerService create(Throwable throwable) {
        if (throwable.getMessage() != null) {
            throwable.printStackTrace();
            logger.info("熔断错误的具体信息: {} ", throwable.getMessage());
        }
        return new ManagerService() {

            @Override
            public ResponseMessage<List<DictionaryModel>> getByNode(String node) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getDictionaryPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> mfaceCJ(Manager_MfaceModel mfaceModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_MfaceModel>> mface_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> mIrisCJ(Manager_MirisModel mirisModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_MirisModel>> miris_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> mfingerCJ(Manager_MfingerModel mfingerModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_MfingerModel>> mfinger_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> zfaceCJ(Manager_ZfaceModel zfaceModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_ZfaceModel>> zface_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> zIrisCJ(Manager_ZirisModel zirisModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_ZirisModel>> ziris_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> zfingerCJ(Manager_ZfingerModel zfingerModel, String ip) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_ZfingerModel>> zfinger_query(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public awd.framework.common.entity.PagerResult<Manager_UserInfoOther> mjxxQuery(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> roleQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> userinfoQuery(String jsbh, String loginname) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<ApidocModel>> apidocList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> apidocSave(ApidocModel apidocModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> apidocUpdate(String id, ApidocModel apidocModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> apidocDelete(String id) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<DictionaryModel> getDictionaryByFieldCode(String field, String code) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<List<DictionaryModel>> getByField(String filed) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<UserinfoModel>> list(QueryParamEntity entity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<List<UserinfoModel>> getUserByJsbhsRoles(String jsbhs, String roles) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_ApplunboModel>> applunbo_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> applunbo_save(Manager_ApplunboModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> applunbo_updateByKey(String id, Manager_ApplunboModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> applunbo_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_ApplunboModel> applunbo_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> app_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> app_save(Manager_AppModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> app_updateByKey(String id, Manager_AppModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> app_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_AppModel> app_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_BarModel>> bar_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> bar_save(Manager_BarModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> bar_updateByKey(String id, Manager_BarModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> bar_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_BarModel> bar_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_CcicModel>> ccic_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ccic_save(Manager_CcicModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ccic_updateByKey(String id, Manager_CcicModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> ccic_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_CcicModel> ccic_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_ClassficModel>> classfic_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> classfic_save(Manager_ClassficModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> classfic_updateByKey(String id, Manager_ClassficModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> classfic_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_ClassficModel> classfic_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_DataqualityruleModel>> dataqualityrule_query(
                    QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dataqualityrule_save(Manager_DataqualityruleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dataqualityrule_updateByKey(String id, Manager_DataqualityruleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> dataqualityrule_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_DataqualityruleModel> dataqualityrule_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_DependentModel>> dependent_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dependent_save(Manager_DependentModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dependent_updateByKey(String id, Manager_DependentModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> dependent_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_DependentModel> dependent_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_DictionaryModel>> dictionary_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dictionary_save(Manager_DictionaryModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dictionary_updateByKey(String id, Manager_DictionaryModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> dictionary_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_DictionaryModel> dictionary_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_DictionaryWdaModel>> dictionaryWda_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dictionaryWda_save(Manager_DictionaryWdaModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> dictionaryWda_updateByKey(String id, Manager_DictionaryWdaModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> dictionaryWda_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_DictionaryWdaModel> dictionaryWda_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_FieldinfoModel>> fieldinfo_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> fieldinfo_save(Manager_FieldinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> fieldinfo_updateByKey(String id, Manager_FieldinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> fieldinfo_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_FieldinfoModel> fieldinfo_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_FlowmapModel>> flowmap_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> flowmap_save(Manager_FlowmapModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> flowmap_updateByKey(String id, Manager_FlowmapModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> flowmap_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_FlowmapModel> flowmap_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_FlownodeModel>> flownode_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> flownode_save(Manager_FlownodeModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> flownode_updateByKey(String id, Manager_FlownodeModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> flownode_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_FlownodeModel> flownode_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_FxpgModel>> fxpg_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> fxpg_save(Manager_FxpgModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> fxpg_updateByKey(String id, Manager_FxpgModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> fxpg_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_FxpgModel> fxpg_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_GroupappModel>> groupapp_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groupapp_save(Manager_GroupappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groupapp_updateByKey(String id, Manager_GroupappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> groupapp_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_GroupappModel> groupapp_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_GroupmenusModel>> groupmenus_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groupmenus_save(Manager_GroupmenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groupmenus_updateByKey(String id, Manager_GroupmenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> groupmenus_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_GroupmenusModel> groupmenus_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_GroupsModel>> groups_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groups_save(Manager_GroupsModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> groups_updateByKey(String id, Manager_GroupsModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> groups_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_GroupsModel> groups_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_InterfacebindingModel>> interfacebinding_query(
                    QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> interfacebinding_save(Manager_InterfacebindingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> interfacebinding_updateByKey(String id, Manager_InterfacebindingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> interfacebinding_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_InterfacebindingModel> interfacebinding_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_InterfaceModel>> interface_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> interface_save(Manager_InterfaceModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> interface_updateByKey(String id, Manager_InterfaceModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> interface_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_InterfaceModel> interface_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_JkfzdModel>> jkfzd_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> jkfzd_save(Manager_JkfzdModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> jkfzd_updateByKey(String id, Manager_JkfzdModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> jkfzd_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_JkfzdModel> jkfzd_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_JsappModel>> jsapp_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> jsapp_save(Manager_JsappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> jsapp_updateByKey(String id, Manager_JsappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> jsapp_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_JsappModel> jsapp_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_LsModel>> ls_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ls_save(Manager_LsModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ls_updateByKey(String id, Manager_LsModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> ls_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_LsModel> ls_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_MenusModel>> menus_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> menus_save(Manager_MenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> menus_updateByKey(String id, Manager_MenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> menus_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_MenusModel> menus_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_MountModel>> mount_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> mount_save(Manager_MountModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> mount_updateByKey(String id, Manager_MountModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> mount_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_MountModel> mount_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_PublishModel>> publish_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> publish_save(Manager_PublishModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> publish_updateByKey(String id, Manager_PublishModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> publish_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_PublishModel> publish_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_RoleappModel>> roleapp_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> roleapp_save(Manager_RoleappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> roleapp_updateByKey(String id, Manager_RoleappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> roleapp_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_RoleappModel> roleapp_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_RoleModel>> role_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> role_save(Manager_RoleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> role_updateByKey(String id, Manager_RoleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> role_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_RoleModel> role_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_RolemenusModel>> rolemenus_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> rolemenus_save(Manager_RolemenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> rolemenus_updateByKey(String id, Manager_RolemenusModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> rolemenus_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_RolemenusModel> rolemenus_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_RyxxModel>> ryxx_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ryxx_save(Manager_RyxxModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> ryxx_updateByKey(String id, Manager_RyxxModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> ryxx_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_RyxxModel> ryxx_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_SettingModel>> setting_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> setting_save(Manager_SettingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> setting_updateByKey(String id, Manager_SettingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> setting_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_SettingModel> setting_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_SuplierModel>> suplier_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> suplier_save(Manager_SuplierModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> suplier_updateByKey(String id, Manager_SuplierModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> suplier_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_SuplierModel> suplier_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_TableinfoModel>> tableinfo_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> tableinfo_save(Manager_TableinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> tableinfo_updateByKey(String id, Manager_TableinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> tableinfo_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_TableinfoModel> tableinfo_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UserappModel>> userapp_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userapp_save(Manager_UserappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userapp_updateByKey(String id, Manager_UserappModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> userapp_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UserappModel> userapp_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UsergroupModel>> usergroup_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usergroup_save(Manager_UsergroupModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usergroup_updateByKey(String id, Manager_UsergroupModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> usergroup_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UsergroupModel> usergroup_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UserinfoModel>> userinfo_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userinfo_save(Manager_UserinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userinfo_updateByKey(String id, Manager_UserinfoModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> userinfo_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UserinfoModel> userinfo_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UsermenuModel>> usermenu_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usermenu_save(Manager_UsermenuModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usermenu_updateByKey(String id, Manager_UsermenuModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> usermenu_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UsermenuModel> usermenu_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UserroleModel>> userrole_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userrole_save(Manager_UserroleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> userrole_updateByKey(String id, Manager_UserroleModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> userrole_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UserroleModel> userrole_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_UsersettingModel>> usersetting_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usersetting_save(Manager_UsersettingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> usersetting_updateByKey(String id, Manager_UsersettingModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> usersetting_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_UsersettingModel> usersetting_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Manager_WgzdModel>> wgzd_query(QueryParam queryParam) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> wgzd_save(Manager_WgzdModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<String> wgzd_updateByKey(String id, Manager_WgzdModel data) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> wgzd_deleteByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

            @Override
            public ResponseMessage<Manager_WgzdModel> wgzd_getByKey(String id) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
            }

			@Override
			public ResponseMessage<String> mcardCJ(MjjbxxModel mjjbxxModel, String ip) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("manager服务调用失败");
			}


        };
    }

}
