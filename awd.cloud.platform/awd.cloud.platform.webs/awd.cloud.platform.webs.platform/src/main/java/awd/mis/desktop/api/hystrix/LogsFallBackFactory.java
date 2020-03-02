package awd.mis.desktop.api.hystrix;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.model.CloudfileModel;
import awd.mis.desktop.model.DocumentAndDetailModel;
import awd.mis.desktop.model.DocumentDetailModel;
import awd.mis.desktop.model.DocumentModel;
import awd.mis.desktop.model.DocumentfileModel;
import awd.mis.desktop.model.LoginlogsModel;
import awd.mis.desktop.model.MsgboxModel;
import awd.mis.desktop.model.OperatelogsModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import feign.hystrix.FallbackFactory;


@Service("logsService")
public class LogsFallBackFactory implements FallbackFactory<LogsService> {


    public static final Logger logger = LoggerFactory.getLogger(LogsService.class);

    @Override
    public LogsService create(Throwable cause) {

        return new LogsService() {

            @Override
            public Map<String, Object> upload(MultipartFile file, String jsbh, String dir, String rybh, String share,
                                              String userid) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"upload");
                return null;
            }

            @Override
            public ResponseMessage<String> senderMsgOne(MsgboxModel msgboxModel) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"senderMsgOne");
                return null;
            }

            @Override
            public ResponseMessage<String> updateAllMsgByJh(String jh) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"updateAllMsgByJh");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<MsgboxModel>> queryMsg(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"queryMsg");
                return null;
            }

            @Override
            public ResponseMessage<String> msgUpdate(String id, MsgboxModel msgboxModel) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"msgUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> loginlogsSave(LoginlogsModel loginlogs) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"loginlogsSave");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<LoginlogsModel>> loginlogsList(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"loginlogsList");
                return null;
            }

            @Override
            public ResponseMessage<String> loginout(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"loginout");
                return null;
            }

            @Override
            public ResponseMessage<String> operatelogsSave(OperatelogsModel operatelogs) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"operatelogsSave");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<CloudfileModel>> cloudFileQuery(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"cloudFileQuery");
                return null;
            }

            @Override
            public ResponseMessage<String> mkdir(String jsbh, String id, String fdir, String dir) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"mkdir");
                return null;
            }

            @Override
            public ResponseMessage<String> cloudFileUpdate(CloudfileModel cloudfile) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"cloudFileUpdate");
                return null;
            }

            @Override
            public ResponseMessage<String> documentSave(DocumentModel data) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentSave");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DocumentModel>> documentList(QueryParam param) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentList");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DocumentDetailModel>> documentDetailList(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentDetailList");
                return null;
            }

            @Override
            public ResponseMessage<String> getDetail(QueryParam param) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"getDetail");
                return null;
            }

            @Override
            public ResponseMessage<String> documentDetailSave(DocumentAndDetailModel data) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentDetailSave");
                return null;
            }

            @Override
            public ResponseMessage documentDetailUpdateByPrimaryKey(String id, DocumentDetailModel data) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentDetailUpdateByPrimaryKey");
                return null;
            }

            @Override
            public Map<String, Object> documentFileUpload(MultipartFile file, String uuid) {

                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentFileUpload");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<DocumentfileModel>> documentfileList(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentfileList");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
            public ResponseMessage<PagerResult<DocumentfileModel>> documentfileListExcludeFile(QueryParam param) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentfileListExcludeFile");
                return null;
            }

			@Override
			public ResponseMessage<String> updateDocumentdetailModel(DocumentDetailModel data) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(),"documentfileListExcludeFile");
				return null;
			}

        };
    }

}
