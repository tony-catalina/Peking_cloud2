package awd.cloud.platform.webs.charts.api.hystrix;

import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class AnalyseFallBackFactory implements FallbackFactory<AnalyseApis> {
	public static Logger logger = Logger.getLogger(AnalyseApis.class);

	@Override
	public AnalyseApis create(Throwable throwable) {
		return new AnalyseApis() {

			@Override
			public Map<String, Object> gylQuery() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> findKss_dsjPT() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_Dagy(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_QSCSJY(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_AYFX() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_JSJL(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_JGRYSY() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> findZfzlk() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_GQSXX(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> aqglQuery() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_ylgl(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_Jqwg(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_jqwgryxx(String jsbh, String jqh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_txhj(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_gyqx(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_jqbb(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_zbld(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public HashMap<String,Object> select_hdsj(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> find_wgcd(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public LinkedHashMap<String, Object> select_wgcd_rybh(String jsbh, String wgqk) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public HashMap<String, Object> select_zdgz(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_zdgzry(String jsbh,String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}


			@Override
			public Map<String, Object> selectJbxx(String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> selectRyqk(String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> selectRygx(String jsbh, String rybh, String jsh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}



			@Override
			public Map<String, Object> select_fzx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_fwx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_aqx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_jspf() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_zhx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_ljx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_Jsjy(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_zsry(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_zsryxx(String bahj,String rybh,String jsbh,String rsrq,String crjbj,String jsh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> select_ssqq(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> ylwsCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> ryflfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> shseCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}


			@Override
			public ResponseMessage<Map<String, Object>> zdryCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> gjfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> cqjyCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> gyqxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> ajqkfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> lsfxfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> yzjbfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> wgqkfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> tswsfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> lslsfxCount() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String,Object> wgCount(String jsbh,String jsh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> dpzsry(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> kssdpaqgl(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			//在押人员=风险等级
			@Override
			public Map<String, Object> selectFxdj( String rybh){
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

            @Override
            public Map<String, Object> jlsdpaqgl(String jsbh) {
                return null;
            }

            @Override
			public Map<String, Object> jlsdpylgl(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdptxhj(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpcqjy(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpjqwgqst(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdprsxz(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpajlb(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpjqwgry(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> yzjbfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> jlsdpjqhdkp(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpjqbb(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> select_ssqk(String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public Map<String, Object> jlsdpzbld(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String,Object> jkqkCount(String jsbh,String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
            @Override
            public Map<String,Object> findRygx(String jsbh,String rybh,String jsh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }
			@Override
			public Map<String,Object> mjwhcd() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> jlsFxdj(String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> kssdpzyryFxys(String jsbh, String rybh) {
				throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
			}
			@Override
			public Map<String, Object> dpzyryFxys(String jsbh, String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> select_jy(String jsbh, String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> select_ajfx(String jsbh, String rybh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> zyrygyl() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> hjdfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> nlfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> whcd() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> sffx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> jkqkfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> jsryfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public List rsxz(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public List ajlb(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> zccs() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> lscsfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public List csyyfx(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> yzblfz() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public List shsefx(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> mjsl() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> mjxbfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> swfz() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public List mjjxfx(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
			@Override
			public Map<String, Object> zzscfx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

            @Override
            public Map<String, Object> select_kss_hjrs() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public Map<String, Object> select_kss_zszt() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public Map<String, Object> select_kss_rygl() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

			@Override
			public List<Object> select_kss_qsbajd(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}


            @Override
            public Map<String, Object> select_kss_ndrs() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public Map<String, Object> select_kss_fxdj() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

			@Override
			public Map<String, Object> select_kss_xdry() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_sjwsry() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_ndrs() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_cqjy() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_zdry() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_jy() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_rygl() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_hjrs() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_xdry() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_fxdj() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> select_jls_gyqx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return null;
			}
		};

	}
}
