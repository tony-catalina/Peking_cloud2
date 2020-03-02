package awd.cloud.platform.api.hystrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import awd.bj.kss.model.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.model.jwp.WgsjclModel;
import awd.cloud.platform.model.kss.AudioModel;
import awd.cloud.platform.model.kss.ClcsModelDO;
import awd.cloud.platform.model.kss.CrjjcModelDO;
import awd.cloud.platform.model.kss.EmdjModelDO;
import awd.cloud.platform.model.kss.GxswModel;
import awd.cloud.platform.model.kss.JjwpModal;
import awd.cloud.platform.model.kss.JkTmModal;
import awd.cloud.platform.model.kss.JqfbModelDO;
import awd.cloud.platform.model.kss.JshjModelDO;
import awd.cloud.platform.model.kss.JstzLdspModel;
import awd.cloud.platform.model.kss.JstzModelDO;
import awd.cloud.platform.model.kss.Kss_ClcsInfoModel;
import awd.cloud.platform.model.kss.Kss_CrjjcModelDO;
import awd.cloud.platform.model.kss.Kss_CscsModel;
import awd.cloud.platform.model.kss.Kss_FwdjModel;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_JsbjdModelDO;
import awd.cloud.platform.model.kss.Kss_JslxModelDO;
import awd.cloud.platform.model.kss.Kss_JstzLdspModel;
import awd.cloud.platform.model.kss.Kss_JstzModal;
import awd.cloud.platform.model.kss.Kss_LdspModelDO;
import awd.cloud.platform.model.kss.Kss_RsdjModel;
import awd.cloud.platform.model.kss.Kss_ShffModel;
import awd.cloud.platform.model.kss.Kss_SykzModel;
import awd.cloud.platform.model.kss.Kss_TjAndCrjjcModel;
import awd.cloud.platform.model.kss.Kss_TsdjModelDO;
import awd.cloud.platform.model.kss.Kss_XyrModel;
import awd.cloud.platform.model.kss.Kss_YqModelDO;
import awd.cloud.platform.model.kss.LdspModelDO;
import awd.cloud.platform.model.kss.PjdjModelDO;
import awd.cloud.platform.model.kss.ShgxsModel;
import awd.cloud.platform.model.kss.SjyyModelDO;
import awd.cloud.platform.model.kss.TafModelIDO;
import awd.cloud.platform.model.kss.UploadFileModel;
import awd.cloud.platform.model.kss.WgsjclModelDO;
import awd.cloud.platform.model.kss.XjhzModelDO;
import awd.cloud.platform.model.kss.XjjsModelDO;
import awd.cloud.platform.utils.ModelList;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("KssServerApis")
public class KssServerFallBackFactory implements FallbackFactory<KssServerApis> {

    public static Logger logger = Logger.getLogger(KssServerApis.class);


    @Override
    public KssServerApis create(Throwable throwable) {
        return new KssServerApis() {

            @Override
            public ResponseMessage<Map<String, String>> ygryAddflow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> ygryQueryForPage(QueryParam map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> cypjAddcypj(CypjModel cypjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> cypjQueryForPage(QueryParam map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xlgyUpdate(String id, XlgyModel xlgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xlgySave(XlgyModel xlgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xlgyQueryForPage(QueryParam map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> qjglSave(QjglModel qjglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> xljkList(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xljkSave(XljkModel data) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xljkUpdateByPrimaryKey(String id, XljkModel data) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zdzyQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zyafQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public  ResponseMessage<Map<String,Object>> addZyaf(String id, ZyafModel zyafModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> zyafUpdate(String id, ZyafModel zyafModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> fxpgQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveTaf(TafModelIDO tafModelIDO) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jkfjlSave(JkfjlModel jkfjlModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jkfjlQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> qjglQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> emdjSave(EmdjModel emdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> emdjQueryForPage(QueryParam arg1) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xjYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> jbAddflow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> ddgyaddflow(DdgyModel ddgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> ddgyExecuteflow(DdgyModel ddgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> ddgy_ldspAdd(DdgyModel ddgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> drgyjsAdd(DdgyModel ddgyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> addZrap(ZrapModel zrapModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wmjsQueryForPage(QueryParam arg1) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<?> wmjsSave(String lcid, WmjsModel wmjsModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbQueryForPage(QueryParam map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xzpaYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveForclzd(XzpaModel xzpaModel, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xzpaSpFlow(XzpaModel xzpaModel, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveForXzpa(String processDefinitionId, XzpaModel xzpaModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> selectNewResult(String jsbh, String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> zdrySpflow(ZdryModel zdryModel, String taskid, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> zdryaddFlow(ZdryModel zdryModel, String processDefinitionId) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addflow(String processDefinitionId, ZyryjljlModel zyryjljlModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jlglSpFlow(ZyryjljlModel zyryjljlModel, String taskid, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jlglDjFlow(ZyryjljlModel zyryjljlModel, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zyrycfjlYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> JJXaddFlow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> cfglAddflow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> zdzySave(ZdzyModel zdzyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> findCountBySbfh(String yfh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zyryjljlYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> pwglSave(PwglModel data) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> findPwglByCwh(String cwh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveSwrypg(SwrypgModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jstzQueryJbxxForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> ldspSavejstz(List<JstzLdspModel> jstzLdspModels) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> shgxJsxmList(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xjglJbxxlist(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xjglSave(XjglModel xjglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xjglUpdate(String id, XjglModel xjglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> qkfySave(QkfyModel qkfyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> qkfyQueryForPage(QueryParam map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addJstzAndJbxx(List<Kss_JstzLdspModel> JstzLdspModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveForRsdjZp(Kss_RsdjModel rsdjModal) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> emdjAddFlow(EmdjModelDO emdjEntityDo) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> emdjSpFlow(EmdjModel emdjModel, String taskid, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> emdjAddCXflow(EmdjModelDO emdjEntityDo) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> emYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");

            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> shgxQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");

            }

            @Override
            public ResponseMessage<String> shgxsSave(ShgxsModel data) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> shgxYwtz(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> xjAddFlow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xjsySpFlow(XjModel xjModel, String taskid, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<String> cfglYcflow(ZyrycfjlModel zyrycfjlModel, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> cfglSpflow(ZyrycfjlModel zyrycfjlModel, String taskid, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> cfglJcflow(ZyrycfjlModel zyrycfjlModel, String ywlcid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jjwpglqueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jjwpglSave(JjwpglModel jjwpglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jsbjdAddflow(String lcid, Kss_JsbjdModelDO jsbjdModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jsbjdQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> findTaf(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> addJstz(JstzModelDO jmd) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getJsXqList(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public String gwjjbjbqr(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return "看守所服务调用失败";
            }

            @Override
            public String SaveGwjjb(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return "看守所服务调用失败";
            }

            @Override
            public List<Map<String, Object>> findMjJbxx(String jsbh, String code) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return new ArrayList<>();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> gwjjbList(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> tszglUpdate(String id, TszglModel tszglEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> tszglSave(TszglModel tszglEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jjdlUpdate(String id, SzjdjlModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jjdlAdd(SzjdjlModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> ldjfQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> sjyySaveSjyys(SjyyModelDO sjyyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> startSjyy(String rybhs, String userName) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> startJs(String rybhs, String userName) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> kssQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> lswgQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> lswgSave(LswgModel lswgModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> update(String id, ShffModel shffModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage shUpdateById(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addSqsw(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> lstlhmcQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> wlrylk(String id, WlryModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> sjyyQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> wlrydj(WlryModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForPjdj(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }



            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wlryQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveJslx(String taskid, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> jslxAddFlow(String csid, Kss_JslxModelDO jslxModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, String>> saveJslxLdsp(String taskid, Kss_LdspModelDO ldspModel, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addYqdjPlFlow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addYqdjFlow(String taskid, Kss_YqModelDO model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> yqAddflow(String lcid, Kss_YqModelDO model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addTsdjTx(String taskid, Kss_TsdjModelDO model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addTsdjHsaqjc(String taskid, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addJqtrFlow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForJwzx(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> tjdjAddflow(TjdjModel tjdjModel, String LcId) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addTjdjFlow(String taskid, TjdjModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addTjCrjFlow(String flag, String taskid, Kss_TjAndCrjjcModel tjAndCrjjcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForHjbd(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveHjbdPl(List<HjbdModel> list) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> pjdjSave(PjdjModelDO pjdjEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForJshj(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> jshjHjs(String hjs, String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jshjAddflow(String lcid, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addJshjLdsp(String taskid, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addJshjCrjjc(String flag, String taskid, CrjjcModelDO model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addTsdj(String taskid, String ywlcid, Kss_TsdjModelDO tsdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> TssUseState(String tss) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> removeTaf(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveTafs(TafModel tafModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<?>> findTaf(TafModel tafEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addJwzxJd(String taskid, JwzxjlModel model, String type) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForTj(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForYq(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }




            @Override
            public ResponseMessage<String> updateJshj(String taskid, JshjModelDO jshjModelDO) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> sqswList(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<String> tsdjAddflow(Kss_TsdjModelDO tsdjModel, String LcId) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLscsHsqr(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> tszglQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLscsCs(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> VerificationHour(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> VerificationMonth(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String,Object>> getJbxxByRybh(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLscsLdsp(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override

            public ResponseMessage<String> csAndJbxx(String taskid, List<Kss_CrjjcModelDO> crjjcList) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jwzxjlAddflow(String lcid, JwzxjlModel jwzxModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> swhyjAdd(Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<String> csxjth(XjhzModel xjhzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> getSykz(String taskid, Kss_SykzModel sykzModal) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> update(String id, Kss_JbxxModelDO jbxxEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> syFwdj(String taskid, Kss_FwdjModel fwdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xjhzlist(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getFwdjTaskList(Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveZbsy(String taskid, XyrModel xyrModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> rsAqjc(String taskid, Map<String, Object> rsajMap) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveAsLegal(String processDefinitionId, XyrModel xyrEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> getTaskList(Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> dabh() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> updateXyrById(String processDefinitionId, Kss_XyrModel xyrModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveAsShdj(String processDefinitionId, XyrModel xyrEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> wcshfSave(JbxxModel jbxxModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String,Object>> audioUpload(AudioModel audioModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<String> ldspForRsdj(JkqkModel jkqkModel, String taskid, String xm) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> firstJkjc(String taskid, JkTmModal jkTmModal) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public String uploadZpDfs(String fileName, byte[] file, String ext) {
              return  null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jkqkQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> getListCustom(Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> shffQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jjwpSave(String taskid, JjwpModal jjwpModal) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }
            @Override
            public ResponseMessage<String> shffSave(Kss_ShffModel shffModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage saveGz(String taskid, QlywgzModel qlywgzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<?> ldspSave(LdspModelDO ldspEntity, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> deleteFlowSfz(String taskid, Variables variables) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> cwcxList(String rybh, String rows, String page, String sort, String order) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<JqModel>> jqQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String,Object>>> xyrQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<String> wgdjSave(WgsjclModel wgdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<XsjlModel>> xsjlQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public String getYeWithRybhAndBz(String rybh, String currency) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> queryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> getRyxxAndPhoto(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> findLsjyJtqk(String zjh, String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<LshjModel>> lshjQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> hjsIsNull(String hjs, String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLshjHdpz(String lcid, LshjModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> pwglByjbxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLshjDjcb(String taskid, LshjModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> addLshjCrjFlow(String flag, String taskid, CrjjcModel model) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> insertByLists(String processDefinitionId, List<SpdetailorderModelDO> list,
                                                         String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> hqjjwpxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> tjdjQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jsQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jszxjcQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jszxjcSave(JszxjcModel jszxjcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jszxjcUpdate(String id, JszxjcModel jszxjcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zrapQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> photoQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jyByjbxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> thjyQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> thjySaveAndOverFlow(ThjyModel thjyEntity, String taskid) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> spdetailorderByjbxxQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jjwpglQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> hqwpQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xjjsQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> spxxQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> jsswSave(GxswModel gxswModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public List<Map<String, Object>> jrwgQueryForPage(QueryParam qParam) {
                return null;
            }

            @Override
            public ResponseMessage<Map<String, String>> jjwpsladdFlow(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wpglQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wpglJbxxListQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jsswQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<JbxxModelDO>> jbxxQuery(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<PhotosModel>> photosList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> photosUpdate(String id, PhotosModel photosModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> savePhotos(PhotosModel photo) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zdryQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xdrzQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> findZrap(ZrapModel zrapModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public PagerResult mjxxQuery(Map<String,String> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }


            @Override
            public ResponseMessage<Map<String, String>> jstzSave(Kss_JstzModal kss_jstzModal) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Integer> ysxzSave(Map<String, Object> params) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jshjQuery(QueryParam param) {
                throwable.printStackTrace();
               logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
               return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> lshjQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> txQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> xdrzSave(XdrzModel xdrzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> lscsQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> lscsAddflow(String processDefinitionId, ModelList<LscsModel> lscslist) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForLscs(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> lscsSave(LscsModel lscsModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> thjySave(ThjyModel thjyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jstzList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> clcsSave(List<Kss_CscsModel> list) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveStartflow(Kss_ClcsInfoModel clcsInfo) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wlryQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xzpaByjbxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zbdjQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wgglQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> wpjsQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> yecxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> xjjsQueryJbxxForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> JsswListByRybh(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> updateJsswGjqrById(String id, JsswModel jsswModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<Integer> saveXjjsAndShgx(Map<String, Object> map, String jsbh) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> shgxsave(ShgxModel shgxModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> shgxupdate(String id, ShgxModel shgxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> xjjsUpdate(String id, XjjsModelDO xjjsModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<XjhzModelDO>> xjhzQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<Integer> saveWpAndShgx(Map<String, Object> map, String jsbh) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> xjzcSave(XjzcModel xjzcModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> saveOrUpdateXjhz(XjhzModelDO xjhzModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<XjzcModel>> xjzcQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
            public ResponseMessage<String> spxxSave(SpxxModel spxxModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> spxxUpdate(String id, SpxxModel spxxModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

			@Override
			public ResponseMessage<PagerResult<XjzcModel>> xfxxList(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> xjhzUpdateID(String id, XjhzModelDO xjhzModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<QybzModel>> qybzQueryForPage(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> getProcessForGwgl(Variables variables) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> spdetailorderUpdateByList(List<SpdetailorderModelDO> spList, String jsbh) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> UpdateSpdetailForList(String flag, List<SpdetailorderModelDO> spList) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> spLq(Map<String, Object> map) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<DdfjgzModel>> ddfjgzQueryForPage(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> ddfjgzSave(DdfjgzModel ddfjgzEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> dmQueryForPage(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> dmadd(DmModel data) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
            public ResponseMessage<PagerResult<XgModel>> xgQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }
			@Override
			public ResponseMessage<String> qybzSave(QybzModel qybzModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<ZyrybgclModel>> zyrybgclQueryForPage(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> zyrybgclSave(ZyrybgclModel zyrybgclEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<QybzModel>> ptxzList(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> ptxzAdd(QybzModel qybzEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> xsjlSave(XsjlModel xsjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> xsjlUpdate(String id, XsjlModel xsjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
			public ResponseMessage<PagerResult<JkrzModel>> jkrzQueryForPage(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<QybzModel>> wxgsList(QueryParam queryParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> wxgsAdd(QybzModel qybzEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> jkrzSave(JkrzModel jkrzEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}
			@Override
			public ResponseMessage<PagerResult<AqjcModel>> aqjcQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> aqjcSave(AqjcModel aqjcEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> aqjcUpdate(String id, AqjcModel aqjcEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<ScqkModel>> scqkQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> scqkSave(ScqkModel scqkEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> scqkUpdate(String id, ScqkModel scqkEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<JtjyModel>> jtjyQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> jtjySave(JtjyModel jtjyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> jtjyUpdate(String id, JtjyModel jtjyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<YqfxModel>> yqfxQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> yqfxSave(YqfxModel yqfxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> yqfxUpdate(String id, YqfxModel yqfxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}


            @Override
            public ResponseMessage<PagerResult<TfsjdjModel>> tfsjdjQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

			@Override
			public ResponseMessage<Integer> qybzSaves(QybzModel qybzEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}
			
			@Override
			public ResponseMessage<Integer> wgglSaves(WgsjclModelDO wgsjclEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<SqfxModel>> sqfxList(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public String uploadZpDfs(UploadFileModel upfile) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
			}

			@Override
			public ResponseMessage<String> sqfxSave(SqfxModel sqfxModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> sqfxUpdate(String id, SqfxModel sqfxModel) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}
			
            @Override
            public ResponseMessage<String> tfsjdjSave(TfsjdjModel tfsjdjEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<String> tfsjdjUpdate(String id, TfsjdjModel tfsjdjEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

			@Override
			public ResponseMessage<PagerResult<SzjdjlModel>> szjdjlQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> szjdjlSave(SzjdjlModel szjdjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> szjdjlUpdate(String id, SzjdjlModel szjdjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<SwhyModel>> swhyQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> swhySave(SwhyModel swhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> swhyUpdate(String id, SwhyModel swhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<LxhyModel>> lxhyQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> lxhySave(LxhyModel lxhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> lxhyUpdate(String id, LxhyModel lxhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<ZydtfxhyModel>> zydtfxhyQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> zydtfxhySave(ZydtfxhyModel zydtfxhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> zydtfxhyUpdate(String id, ZydtfxhyModel zydtfxhyEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<FssgModel>> fssgQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> fssgSave(FssgModel fssgEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> fssgUpdate(String id, FssgModel fssgEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
			}
            @Override
            public ResponseMessage<PagerResult<JqzxModel>> queryForPageJqzx(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<String> saveJqzx(JqzxModel jqzxEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<WmxcModel>> wmxcList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> wmxcSave(WmxcModel wmxcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> wmxcUpdate(String id, WmxcModel wmxcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }
            @Override
            public ResponseMessage<PagerResult<JsnwModel>> queryForPageJsnw(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<String> saveJsnw(JsnwModel jsnwModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<JqfbModelDO>> jqfbList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jqfbSave(JqfbModelDO jqfbModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jqfbUpdate(String id, JqfbModelDO jqfbModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<GzryglModel>> gzryglQueryForPage(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> gzryglSave(GzryglModel gzryglEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public String gzryglUpdate(String id, GzryglModel gzryglEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DjglModel>> djglList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> djglSave(DjglModel djglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<String> djglUpdate(String id, DjglModel djglModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }
            @Override
            public ResponseMessage<PagerResult<MjzfModel>> queryForPageMjzf(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<ZbkqModel>> zbkqList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> zbkqSave(ZbkqModel zbkqModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> zbkqUpdate(String id, ZbkqModel zbkqModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<String> saveMjzf(MjzfModel mjzfModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<PagerResult<XggzyqModel>> queryForPageXggzyq(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
            @Override
            public ResponseMessage<String> saveXggzyq(XggzyqModel xggzyqModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }


			@Override
			public ResponseMessage<PagerResult<YjyaylModel>> yjyaylQueryForPage(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> yjyaylSave(YjyaylModel yjyaylEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public String yjyaylUpdate(String id, YjyaylModel yjyaylEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return "看守所服务调用失败";
			}

			@Override
			public ResponseMessage<PagerResult<MjjbxxModel>> mjjbxxQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
            public ResponseMessage<PagerResult<YabzModel>> yabzList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> yabzSave(YabzModel yabzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> yabzUpdate(String id, YabzModel yabzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<TsdjModel>> tsdjquery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<JxkhModel>> jxkhList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jxkhSave(JxkhModel jxkhModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jxkhUpdate(String id, JxkhModel jxkhModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }



            @Override
            public ResponseMessage<PagerResult<JshjModelDO>> jshjquery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<LshjModel>> lshjquery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

			@Override
			public ResponseMessage<PagerResult<WqsyModel>> getWqsy(QueryParam param) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> wqsyAddFlow(String lcid, WqsyModel model) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> addLdsp(String taskid, LdspModel model) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> addWqgh(String taskid, WqsyModel entity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> wqsyAndMjxx(String syly, String syts,
					String wqzl) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> saveMjxxAndPicture(Map<String, Object> params) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> updateMjxxAndPicture(Map<String, Object> params) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public String getPicByMjid(String mjid) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
			}

			@Override
			public ResponseMessage<String> mjjbxxSave(MjjbxxModel mjjbxxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjjbxxUpdate(String id, MjjbxxModel mjjbxxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
            public ResponseMessage<PagerResult<ZdzyModel>> zdzyquery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }



           

            @Override
            public ResponseMessage<PagerResult<XjjlModel>> xjjlList(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> xjjlSave(XjjlModel xjjlEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> xjjlUpdate(String id, XjjlModel xjjlEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<JygzzlpgModel>> jypgList(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jypgSave(JygzzlpgModel jygzzlpgModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> jypgUpdate(String id, JygzzlpgModel jygzzlpgModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<ClcsModelDO>> clcsQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> lsfxQuery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jslxQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xsjlList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<List<ZbdjModel>> zbdjSelectOne(String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

			@Override
			public ResponseMessage<PagerResult<MjjyjlModel>> mjjyjlQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjjyjlSave(MjjyjlModel mjjyjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjjyjlUpdate(String id, MjjyjlModel mjjyjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<MjjcjlModel>> mjjcjlQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjjcjlSave(MjjcjlModel mjjcjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjjcjlUpdate(String id, MjjcjlModel mjjcjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<MjgzjlModel>> mjgzjlQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjgzjlSave(MjgzjlModel mjgzjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjgzjlUpdate(String id, MjgzjlModel mjgzjlEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<MjshgxModel>> mjshgxQueryForPage(QueryParam qParam) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjshgxSave(MjshgxModel mjshgxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

			@Override
			public ResponseMessage<String> mjshgxUpdate(String id, MjshgxModel mjshgxEntity) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
			}

            @Override
            public ResponseMessage<String> zbdjSave(List<ZbdjModel> list) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<ZbdjhistoryModel>> zbdjHistoryList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> cyflcxquery(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> ryxxUpdate(String id, JbxxModel jbxxEntity) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jjxQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<XdrzModel>> xdrzList(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> xdrzUpdate(String id, XdrzModel xdrzModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> addFlow(Map<String, Object> map1) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> tsclQueryForPage(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> addTscl(Map<String, Object> map1) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage<String> addTscldj(String taskid, String rybh, TsclModel tsclModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.ok();
            }
        };
    }
}
