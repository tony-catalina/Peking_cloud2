package awd.cloud.platform.webs.charts.api.hystrix;


import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JyModel;
import awd.bj.kss.model.WgsjclModel;
import awd.cloud.platform.webs.charts.api.KssServerApis;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
/**
 * @Description
 * @Author WS
 * @Date 2019-06-10 19:40
 */
@Component
public class KssServerFallBackFactory implements FallbackFactory<KssServerApis> {
    public static Logger logger = Logger.getLogger(KssServerApis.class);

    @Override
    public KssServerApis create(Throwable throwable) {

        return new KssServerApis() {

			@Override
			public ResponseMessage<PagerResult<JbxxModel>> queryForPage(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<JyModel>> jyQueryForPage(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<WgsjclModel>> wgsjclQueryForPage(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

        };
    }
}
