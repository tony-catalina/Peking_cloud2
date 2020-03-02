package awd.cloud.platform.api.hystrix;

import java.util.List;
import java.util.Map;

import awd.bj.jls.model.*;
import awd.cloud.platform.model.jls.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("JlsServerApis")
public class JlsServerFallBackFactory implements FallbackFactory<JlsServerApis> {

	public static Logger logger = Logger.getLogger(JlsServerApis.class);

	@Override
	public JlsServerApis create(Throwable cause) {
		// TODO Auto-generated method stub
		return new JlsServerApis() {

			@Override
			public ResponseMessage<String> shgxSave(ShgxModel jls_shgxModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("拘留所服务调用失败");
			}

			@Override
			public ResponseMessage<PagerResult<JbxxModel>> jbxxQueryForPage(QueryParam param) {
					cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");

			}
			@Override
			public ResponseMessage<PagerResult<ShgxModel>> shgxQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");

			}
			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> shgxJbxxQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");

			}
			@Override
			public ResponseMessage<PagerResult<XyrModel>> xyrQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");

			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> tsclQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> qjcsStartflow(QjcsModel qjcsModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> clcsStartflow(String processDefinitionId, ClcsModel jls_clcsModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> clcsSpflow(ClcsModel jls_ClcsModel, String taskid, String yjlx) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}


			@Override
			public ResponseMessage<String> jkqkSave(String taskid, String ywlcid, JkqkModel jkqkModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> wpglSaveByZcrs(String taskid, String ywlcid, List<WpglModel> wpglEntity) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> addFlowWfwth(String taskid, Variables variables) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> savePLAsCustom(WpglInfoModel wpglInfoModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> qmcsZxflow(ClcsModel jls_ClcsModel, String taskid, String type) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> qmcsKzdyZxflow(ClcsModel jls_ClcsModel, String taskid, String type) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> qjhsDrjs(CrjdjModel crjdjModel, String taskid) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> qjcsJbxxForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> wlrydjQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> wlryxgSave(WlrydjInfoModel wlrydjInfoModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> wlrydjUpdate(String id, WlrydjModel wlrydjModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> ywtzQuery(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> swdjSave(String processDefinitionId, SqswInfoModel model) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> wpjsZxflow(String taskid, WpjsModel wpjsEntity) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> swPfwpSave(SqswInfoModel model) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> wlrydjCount(String jsbh, String starttime, String endtime) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> szjdjlQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

            @Override
            public ResponseMessage<String> szjdjlSave(SzjdjlModel szjdjlModel) {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
            public ResponseMessage<String> szjdjlUpdate(String id, SzjdjlModel szjdjlModel) {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> szjdjlCount(String jsbh, String starttime, String endtime) {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> scqkQueryForPage(QueryParam param) {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
            public ResponseMessage<String> scqkAndsctt(ScqkModel scqkModel) {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
            }

            @Override
			public ResponseMessage<String> tqjcZxflow(ClcsModel clcsModel, String taskid, String type) {
            	cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> saveAsKsrsOrJypz(XyrAndPhotosModel xyrAndPhotosModel) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> hdpzSave(TsdjModel tsdjModel) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> djcpSave(TsdjModel model) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> szyjSave(TsdjModel model) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> aptxSave(TsdjModel model) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> xwdjSave(String stjcqk, TsdjModel model) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> dwkfBySqdj(DwkfAndRyxxModel dwkfAndRyxxModel) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<DwkfModel>> dwkfQueryForPage(QueryParam param) {
				cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> dwkfBySzyj(DwkfModel dwkfModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> saveByGjcp(TxthModel txthEntity) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> saveByLdsp(TxthModel txthEntity) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> thdjsave(TxthModel txthEntity) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> txYwdtQuery(QueryParam arg1) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> txYwtzQuery(QueryParam arg1) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> saveAsXyrByJypz(XyrModel xyrModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<JbxxModel>> getListCustom(Variables variables) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> saveAsXyr(XyrModel xyrModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> jkqkSaveByZcrs(String jkjcjg, String taskid, String ywlcid,
					JkqkModel jkqkModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> swYwtzQuery(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<DwkfModel>> dwkfYwtzQuery(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> qqdhYwtzQuery(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> jkqksave(JkqkModel jkqkModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> jkqkUpdate(String id, JkqkModel jkqkModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<XxfbModel>> xxfbQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> xxfbSave(XxfbModel xxfbModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<PagerResult<Map<String, Object>>> lkrywhQueryForPage(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}

			@Override
			public ResponseMessage<String> lkrywhSave(LkrywhModel lkrywhModel) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("进入熔断器");
			}
		};
	}

}
