package awd.cloud.platform.api.hystrix;



import awd.bj.kss.model.LshjModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.*;
import feign.hystrix.FallbackFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service("kssService")
public class KssFallBackFactory implements FallbackFactory<KssService> {
	public static final Logger logger = LoggerFactory.getLogger(KssService.class);
	
	@Override
	public KssService create(Throwable cause) {
		if(cause.getMessage()!=null) {
			cause.printStackTrace();
			logger.info("熔断错误的具体信息: {} " ,cause.getMessage());
		}
		return new KssService(){

			@Override
			public ResponseMessage<PagerResult<Kss_AqjcModel>> aqjc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> aqjc_save(Kss_AqjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> aqjc_updateByKey(String id, Kss_AqjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> aqjc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_AqjcModel> aqjc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_BjgxfkModel>> bjgxfk_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjgxfk_save(Kss_BjgxfkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjgxfk_updateByKey(String id, Kss_BjgxfkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> bjgxfk_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_BjgxfkModel> bjgxfk_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_BjjlModel>> bjjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjjl_save(Kss_BjjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjjl_updateByKey(String id, Kss_BjjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> bjjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_BjjlModel> bjjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_BjxzModel>> bjxz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjxz_save(Kss_BjxzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bjxz_updateByKey(String id, Kss_BjxzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> bjxz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_BjxzModel> bjxz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_BxdModel>> bxd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bxd_save(Kss_BxdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> bxd_updateByKey(String id, Kss_BxdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> bxd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_BxdModel> bxd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ClcsModel>> clcs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> clcs_save(Kss_ClcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> clcs_updateByKey(String id, Kss_ClcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> clcs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ClcsModel> clcs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_CrjjcModel>> crjjc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> crjjc_save(Kss_CrjjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> crjjc_updateByKey(String id, Kss_CrjjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> crjjc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_CrjjcModel> crjjc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_CxcyModel>> cxcy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cxcy_save(Kss_CxcyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cxcy_updateByKey(String id, Kss_CxcyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> cxcy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_CxcyModel> cxcy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_CypjModel>> cypj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cypj_save(Kss_CypjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cypj_updateByKey(String id, Kss_CypjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> cypj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_CypjModel> cypj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_DdfjgzModel>> ddfjgz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ddfjgz_save(Kss_DdfjgzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ddfjgz_updateByKey(String id, Kss_DdfjgzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ddfjgz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_DdfjgzModel> ddfjgz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_DjglModel>> djgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> djgl_save(Kss_DjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> djgl_updateByKey(String id, Kss_DjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> djgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_DjglModel> djgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_DmModel>> dm_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> dm_save(Kss_DmModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> dm_updateByKey(String id, Kss_DmModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> dm_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_DmModel> dm_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_DxsphjModel>> dxsphj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> dxsphj_save(Kss_DxsphjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> dxsphj_updateByKey(String id, Kss_DxsphjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> dxsphj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_DxsphjModel> dxsphj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_EmdjModel>> emdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> emdj_save(Kss_EmdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> emdj_updateByKey(String id, Kss_EmdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> emdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_EmdjModel> emdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_FjszModel>> fjsz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fjsz_save(Kss_FjszModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fjsz_updateByKey(String id, Kss_FjszModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> fjsz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_FjszModel> fjsz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_FssgModel>> fssg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fssg_save(Kss_FssgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fssg_updateByKey(String id, Kss_FssgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> fssg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_FssgModel> fssg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_FxpgKhModel>> fxpgKh_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpgKh_save(Kss_FxpgKhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpgKh_updateByKey(String id, Kss_FxpgKhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> fxpgKh_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_FxpgKhModel> fxpgKh_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_FxpgModel>> fxpg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpg_save(Kss_FxpgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpg_updateByKey(String id, Kss_FxpgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> fxpg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_FxpgModel> fxpg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_FxpgRygkcsModel>> fxpgRygkcs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpgRygkcs_save(Kss_FxpgRygkcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> fxpgRygkcs_updateByKey(String id, Kss_FxpgRygkcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> fxpgRygkcs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_FxpgRygkcsModel> fxpgRygkcs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_GdModel>> gd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gd_save(Kss_GdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gd_updateByKey(String id, Kss_GdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> gd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_GdModel> gd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_GwjjbModel>> gwjjb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gwjjb_save(Kss_GwjjbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gwjjb_updateByKey(String id, Kss_GwjjbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> gwjjb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_GwjjbModel> gwjjb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_GzqkModel>> gzqk_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gzqk_save(Kss_GzqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gzqk_updateByKey(String id, Kss_GzqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> gzqk_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_GzqkModel> gzqk_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_GzryglModel>> gzrygl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gzrygl_save(Kss_GzryglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> gzrygl_updateByKey(String id, Kss_GzryglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> gzrygl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_GzryglModel> gzrygl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_HjbdModel>> hjbd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hjbd_save(Kss_HjbdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hjbd_updateByKey(String id, Kss_HjbdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> hjbd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_HjbdModel> hjbd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JbjlModel>> jbjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jbjl_save(Kss_JbjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jbjl_updateByKey(String id, Kss_JbjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jbjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JbjlModel> jbjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JbModel>> jb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jb_save(Kss_JbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jb_updateByKey(String id, Kss_JbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JbModel> jb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JbxxModel>> jbxx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jbxx_save(Kss_JbxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jbxx_updateByKey(String id, Kss_JbxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jbxx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JbxxModel> jbxx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JjwpsljlModel>> jjwpsljl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jjwpsljl_save(Kss_JjwpsljlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jjwpsljl_updateByKey(String id, Kss_JjwpsljlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jjwpsljl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JjwpsljlModel> jjwpsljl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JjxModel>> jjx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jjx_save(Kss_JjxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jjx_updateByKey(String id, Kss_JjxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jjx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JjxModel> jjx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JkfjlModel>> jkfjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkfjl_save(Kss_JkfjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkfjl_updateByKey(String id, Kss_JkfjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jkfjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JkfjlModel> jkfjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JkqkModel>> jkqk_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkqk_save(Kss_JkqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkqk_updateByKey(String id, Kss_JkqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jkqk_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JkqkModel> jkqk_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JkrzModel>> jkrz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkrz_save(Kss_JkrzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jkrz_updateByKey(String id, Kss_JkrzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jkrz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JkrzModel> jkrz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JqModel>> jq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jq_save(Kss_JqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jq_updateByKey(String id, Kss_JqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JqModel> jq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JqfbModel>> jqfb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jqfb_save(Kss_JqfbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jqfb_updateByKey(String id, Kss_JqfbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jqfb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JqfbModel> jqfb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JqzxModel>> jqzx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jqzx_save(Kss_JqzxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jqzx_updateByKey(String id, Kss_JqzxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jqzx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JqzxModel> jqzx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JsbjdModel>> jsbjd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsbjd_save(Kss_JsbjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsbjd_updateByKey(String id, Kss_JsbjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jsbjd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JsbjdModel> jsbjd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JsModel>> js_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> js_save(Kss_JsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> js_updateByKey(String id, Kss_JsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> js_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JsModel> js_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JshjModel>> jshj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jshj_save(Kss_JshjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jshj_updateByKey(String id, Kss_JshjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jshj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JshjModel> jshj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JslxModel>> jslx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jslx_save(Kss_JslxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jslx_updateByKey(String id, Kss_JslxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jslx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JslxModel> jslx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JsnwModel>> jsnw_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsnw_save(Kss_JsnwModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsnw_updateByKey(String id, Kss_JsnwModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jsnw_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JsnwModel> jsnw_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JsswModel>> jssw_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jssw_save(Kss_JsswModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jssw_updateByKey(String id, Kss_JsswModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jssw_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JsswModel> jssw_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JstzModel>> jstz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jstz_save(Kss_JstzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jstz_updateByKey(String id, Kss_JstzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jstz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JstzModel> jstz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JsycglModel>> jsycgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsycgl_save(Kss_JsycglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jsycgl_updateByKey(String id, Kss_JsycglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jsycgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JsycglModel> jsycgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JszxjcModel>> jszxjc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jszxjc_save(Kss_JszxjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jszxjc_updateByKey(String id, Kss_JszxjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jszxjc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JszxjcModel> jszxjc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JtjyModel>> jtjy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jtjy_save(Kss_JtjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jtjy_updateByKey(String id, Kss_JtjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jtjy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JtjyModel> jtjy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JwzxjlModel>> jwzxjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jwzxjl_save(Kss_JwzxjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jwzxjl_updateByKey(String id, Kss_JwzxjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jwzxjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JwzxjlModel> jwzxjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JxkhModel>> jxkh_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jxkh_save(Kss_JxkhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jxkh_updateByKey(String id, Kss_JxkhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jxkh_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JxkhModel> jxkh_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JygzzlpgModel>> jygzzlpg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jygzzlpg_save(Kss_JygzzlpgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jygzzlpg_updateByKey(String id, Kss_JygzzlpgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jygzzlpg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JygzzlpgModel> jygzzlpg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_JyModel>> jy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jy_save(Kss_JyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> jy_updateByKey(String id, Kss_JyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> jy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_JyModel> jy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_KssModel>> kss_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> kss_save(Kss_KssModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> kss_updateByKey(String id, Kss_KssModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> kss_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_KssModel> kss_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LdfModel>> ldf_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ldf_save(Kss_LdfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ldf_updateByKey(String id, Kss_LdfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ldf_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LdfModel> ldf_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LdspModel>> ldsp_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ldsp_save(Kss_LdspModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ldsp_updateByKey(String id, Kss_LdspModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ldsp_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LdspModel> ldsp_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LkglModel>> lkgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lkgl_save(Kss_LkglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lkgl_updateByKey(String id, Kss_LkglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lkgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LkglModel> lkgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LsbdModel>> lsbd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsbd_save(Kss_LsbdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsbd_updateByKey(String id, Kss_LsbdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lsbd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LsbdModel> lsbd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LscsModel>> lscs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lscs_save(Kss_LscsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lscs_updateByKey(String id, Kss_LscsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lscs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LscsModel> lscs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LsfxModel>> lsfx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsfx_save(Kss_LsfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsfx_updateByKey(String id, Kss_LsfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lsfx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LsfxModel> lsfx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LsfxykhModel>> lsfxykh_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsfxykh_save(Kss_LsfxykhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lsfxykh_updateByKey(String id, Kss_LsfxykhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lsfxykh_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LsfxykhModel> lsfxykh_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<LshjModel>> lshj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lshj_save(Kss_LshjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lshj_updateByKey(String id, Kss_LshjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lshj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LshjModel> lshj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LstlhmcModel>> lstlhmc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lstlhmc_save(Kss_LstlhmcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lstlhmc_updateByKey(String id, Kss_LstlhmcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lstlhmc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LstlhmcModel> lstlhmc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LswgModel>> lswg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lswg_save(Kss_LswgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lswg_updateByKey(String id, Kss_LswgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lswg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LswgModel> lswg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LxhyModel>> lxhy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lxhy_save(Kss_LxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lxhy_updateByKey(String id, Kss_LxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lxhy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LxhyModel> lxhy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_LybModel>> lyb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lyb_save(Kss_LybModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> lyb_updateByKey(String id, Kss_LybModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> lyb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_LybModel> lyb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjgzjlModel>> mjgzjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjgzjl_save(Kss_MjgzjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjgzjl_updateByKey(String id, Kss_MjgzjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjgzjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjgzjlModel> mjgzjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjjbxxModel>> mjjbxx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjbxx_save(Kss_MjjbxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjbxx_updateByKey(String id, Kss_MjjbxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjjbxx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjjbxxModel> mjjbxx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjjcjlModel>> mjjcjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjcjl_save(Kss_MjjcjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjcjl_updateByKey(String id, Kss_MjjcjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjjcjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjjcjlModel> mjjcjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjjyjlModel>> mjjyjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjyjl_save(Kss_MjjyjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjjyjl_updateByKey(String id, Kss_MjjyjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjjyjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjjyjlModel> mjjyjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjkhModel>> mjkh_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjkh_save(Kss_MjkhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjkh_updateByKey(String id, Kss_MjkhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjkh_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjkhModel> mjkh_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjshgxModel>> mjshgx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjshgx_save(Kss_MjshgxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjshgx_updateByKey(String id, Kss_MjshgxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjshgx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjshgxModel> mjshgx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjzfModel>> mjzf_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjzf_save(Kss_MjzfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjzf_updateByKey(String id, Kss_MjzfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjzf_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjzfModel> mjzf_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_MjzpModel>> mjzp_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjzp_save(Kss_MjzpModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> mjzp_updateByKey(String id, Kss_MjzpModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> mjzp_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_MjzpModel> mjzp_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_NbxcModel>> nbxc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> nbxc_save(Kss_NbxcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> nbxc_updateByKey(String id, Kss_NbxcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> nbxc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_NbxcModel> nbxc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_PhotosModel>> photos_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> photos_save(Kss_PhotosModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> photos_updateByKey(String id, Kss_PhotosModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> photos_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_PhotosModel> photos_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_PjdjModel>> pjdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> pjdj_save(Kss_PjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> pjdj_updateByKey(String id, Kss_PjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> pjdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_PjdjModel> pjdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_PurchaseModel>> purchase_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> purchase_save(Kss_PurchaseModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> purchase_updateByKey(String id, Kss_PurchaseModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> purchase_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_PurchaseModel> purchase_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_QjglModel>> qjgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qjgl_save(Kss_QjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qjgl_updateByKey(String id, Kss_QjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> qjgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_QjglModel> qjgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_QkfyModel>> qkfy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qkfy_save(Kss_QkfyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qkfy_updateByKey(String id, Kss_QkfyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> qkfy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_QkfyModel> qkfy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_QlywgzModel>> qlywgz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qlywgz_save(Kss_QlywgzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qlywgz_updateByKey(String id, Kss_QlywgzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> qlywgz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_QlywgzModel> qlywgz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_QybzModel>> qybz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qybz_save(Kss_QybzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> qybz_updateByKey(String id, Kss_QybzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> qybz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_QybzModel> qybz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_RsjcModel>> rsjc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> rsjc_save(Kss_RsjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> rsjc_updateByKey(String id, Kss_RsjcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> rsjc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_RsjcModel> rsjc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_RykhModel>> rykh_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> rykh_save(Kss_RykhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> rykh_updateByKey(String id, Kss_RykhModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> rykh_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_RykhModel> rykh_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ScqkModel>> scqk_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> scqk_save(Kss_ScqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> scqk_updateByKey(String id, Kss_ScqkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> scqk_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ScqkModel> scqk_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ShffModel>> shff_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> shff_save(Kss_ShffModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> shff_updateByKey(String id, Kss_ShffModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> shff_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ShffModel> shff_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ShgxModel>> shgx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> shgx_save(Kss_ShgxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> shgx_updateByKey(String id, Kss_ShgxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> shgx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ShgxModel> shgx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SjyyModel>> sjyy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sjyy_save(Kss_SjyyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sjyy_updateByKey(String id, Kss_SjyyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sjyy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SjyyModel> sjyy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SpdetailorderModel>> spdetailorder_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> spdetailorder_save(Kss_SpdetailorderModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> spdetailorder_updateByKey(String id, Kss_SpdetailorderModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> spdetailorder_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SpdetailorderModel> spdetailorder_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SphjapModel>> sphjap_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjap_save(Kss_SphjapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjap_updateByKey(String id, Kss_SphjapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sphjap_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SphjapModel> sphjap_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SphjqkdjModel>> sphjqkdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjqkdj_save(Kss_SphjqkdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjqkdj_updateByKey(String id, Kss_SphjqkdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sphjqkdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SphjqkdjModel> sphjqkdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SphjsqModel>> sphjsq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjsq_save(Kss_SphjsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sphjsq_updateByKey(String id, Kss_SphjsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sphjsq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SphjsqModel> sphjsq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SporderModel>> sporder_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sporder_save(Kss_SporderModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sporder_updateByKey(String id, Kss_SporderModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sporder_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SporderModel> sporder_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SpxxModel>> spxx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> spxx_save(Kss_SpxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> spxx_updateByKey(String id, Kss_SpxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> spxx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SpxxModel> spxx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SqdtfxhyModel>> sqdtfxhy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sqdtfxhy_save(Kss_SqdtfxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sqdtfxhy_updateByKey(String id, Kss_SqdtfxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sqdtfxhy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SqdtfxhyModel> sqdtfxhy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SqfxModel>> sqfx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sqfx_save(Kss_SqfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sqfx_updateByKey(String id, Kss_SqfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sqfx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SqfxModel> sqfx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SwdjModel>> swdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swdj_save(Kss_SwdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swdj_updateByKey(String id, Kss_SwdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> swdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SwdjModel> swdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SwfzModel>> swfz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swfz_save(Kss_SwfzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swfz_updateByKey(String id, Kss_SwfzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> swfz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SwfzModel> swfz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SwglModel>> swgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swgl_save(Kss_SwglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swgl_updateByKey(String id, Kss_SwglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> swgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SwglModel> swgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SwhyModel>> swhy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swhy_save(Kss_SwhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swhy_updateByKey(String id, Kss_SwhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> swhy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SwhyModel> swhy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SxsphjapModel>> sxsphjap_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphjap_save(Kss_SxsphjapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphjap_updateByKey(String id, Kss_SxsphjapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sxsphjap_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SxsphjapModel> sxsphjap_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SxsphjModel>> sxsphj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphj_save(Kss_SxsphjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphj_updateByKey(String id, Kss_SxsphjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sxsphj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SxsphjModel> sxsphj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SxsphjsqModel>> sxsphjsq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphjsq_save(Kss_SxsphjsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> sxsphjsq_updateByKey(String id, Kss_SxsphjsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> sxsphjsq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SxsphjsqModel> sxsphjsq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SzjdjlModel>> szjdjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> szjdjl_save(Kss_SzjdjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> szjdjl_updateByKey(String id, Kss_SzjdjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> szjdjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SzjdjlModel> szjdjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TafModel>> taf_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> taf_save(Kss_TafModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> taf_updateByKey(String id, Kss_TafModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> taf_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TafModel> taf_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TfsjdjModel>> tfsjdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tfsjdj_save(Kss_TfsjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tfsjdj_updateByKey(String id, Kss_TfsjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tfsjdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TfsjdjModel> tfsjdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ThjyModel>> thjy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjy_save(Kss_ThjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjy_updateByKey(String id, Kss_ThjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> thjy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ThjyModel> thjy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ThjyLsjyModel>> thjyLsjy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjyLsjy_save(Kss_ThjyLsjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjyLsjy_updateByKey(String id, Kss_ThjyLsjyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> thjyLsjy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ThjyLsjyModel> thjyLsjy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ThjyZssbModel>> thjyZssb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjyZssb_save(Kss_ThjyZssbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thjyZssb_updateByKey(String id, Kss_ThjyZssbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> thjyZssb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ThjyZssbModel> thjyZssb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ThysModel>> thys_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thys_save(Kss_ThysModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> thys_updateByKey(String id, Kss_ThysModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> thys_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ThysModel> thys_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TjdjModel>> tjdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tjdj_save(Kss_TjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tjdj_updateByKey(String id, Kss_TjdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tjdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TjdjModel> tjdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TlhmcjlModel>> tlhmcjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tlhmcjl_save(Kss_TlhmcjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tlhmcjl_updateByKey(String id, Kss_TlhmcjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tlhmcjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TlhmcjlModel> tlhmcjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TmsqModel>> tmsq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tmsq_save(Kss_TmsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tmsq_updateByKey(String id, Kss_TmsqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tmsq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TmsqModel> tmsq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TmtzModel>> tmtz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tmtz_save(Kss_TmtzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tmtz_updateByKey(String id, Kss_TmtzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tmtz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TmtzModel> tmtz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TsclModel>> tscl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tscl_save(Kss_TsclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tscl_updateByKey(String id, Kss_TsclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tscl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TsclModel> tscl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TsdjModel>> tsdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tsdj_save(Kss_TsdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tsdj_updateByKey(String id, Kss_TsdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tsdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TsdjModel> tsdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TsyyModel>> tsyy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tsyy_save(Kss_TsyyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tsyy_updateByKey(String id, Kss_TsyyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tsyy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TsyyModel> tsyy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_SwrypgModel>> swrypg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swrypg_save(Kss_SwrypgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> swrypg_updateByKey(String id, Kss_SwrypgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> swrypg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_SwrypgModel> swrypg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_TszglModel>> tszgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tszgl_save(Kss_TszglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tszgl_updateByKey(String id, Kss_TszglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tszgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_TszglModel> tszgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@GetMapping("/ksscore/wgsjcl")
            @Override
			public ResponseMessage<PagerResult<Kss_WgsjclModel>> wgsjcl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wgsjcl_save(Kss_WgsjclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wgsjcl_updateByKey(String id, Kss_WgsjclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wgsjcl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WgsjclModel> wgsjcl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WlryModel>> wlry_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wlry_save(Kss_WlryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wlry_updateByKey(String id, Kss_WlryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wlry_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WlryModel> wlry_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WmjsModel>> wmjs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wmjs_save(Kss_WmjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wmjs_updateByKey(String id, Kss_WmjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wmjs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WmjsModel> wmjs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WmxcModel>> wmxc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wmxc_save(Kss_WmxcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wmxc_updateByKey(String id, Kss_WmxcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wmxc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WmxcModel> wmxc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WpglModel>> wpgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wpgl_save(Kss_WpglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wpgl_updateByKey(String id, Kss_WpglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wpgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WpglModel> wpgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WpjsModel>> wpjs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wpjs_save(Kss_WpjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wpjs_updateByKey(String id, Kss_WpjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wpjs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WpjsModel> wpjs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WplqModel>> wplq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wplq_save(Kss_WplqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wplq_updateByKey(String id, Kss_WplqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wplq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WplqModel> wplq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WqsyModel>> wqsy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wqsy_save(Kss_WqsyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wqsy_updateByKey(String id, Kss_WqsyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wqsy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WqsyModel> wqsy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_WsfyModel>> wsfy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wsfy_save(Kss_WsfyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> wsfy_updateByKey(String id, Kss_WsfyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> wsfy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_WsfyModel> wsfy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XfmxModel>> xfmx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xfmx_save(Kss_XfmxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xfmx_updateByKey(String id, Kss_XfmxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xfmx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XfmxModel> xfmx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XggzyqModel>> xggzyq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xggzyq_save(Kss_XggzyqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xggzyq_updateByKey(String id, Kss_XggzyqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xggzyq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XggzyqModel> xggzyq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XgModel>> xg_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xg_save(Kss_XgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xg_updateByKey(String id, Kss_XgModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xg_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XgModel> xg_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjglModel>> xjgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjgl_save(Kss_XjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjgl_updateByKey(String id, Kss_XjglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xjgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjglModel> xjgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjhzModel>> xjhz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjhz_save(Kss_XjhzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjhz_updateByKey(String id, Kss_XjhzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xjhz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjhzModel> xjhz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjjlModel>> xjjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjjl_save(Kss_XjjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjjl_updateByKey(String id, Kss_XjjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xjjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjjlModel> xjjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjjsModel>> xjjs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjjs_save(Kss_XjjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjjs_updateByKey(String id, Kss_XjjsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xjjs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjjsModel> xjjs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjModel>> xj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xj_save(Kss_XjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xj_updateByKey(String id, Kss_XjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjModel> xj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XjzcModel>> xjzc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjzc_save(Kss_XjzcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xjzc_updateByKey(String id, Kss_XjzcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xjzc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XjzcModel> xjzc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XlgyModel>> xlgy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xlgy_save(Kss_XlgyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xlgy_updateByKey(String id, Kss_XlgyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xlgy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XlgyModel> xlgy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XljkModel>> xljk_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xljk_save(Kss_XljkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xljk_updateByKey(String id, Kss_XljkModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xljk_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XljkModel> xljk_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XsjlModel>> xsjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public ResponseMessage<String> xsjl_save(Kss_XsjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xsjl_updateByKey(String id, Kss_XsjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xsjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XsjlModel> xsjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XtzxjdModel>> xtzxjd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xtzxjd_save(Kss_XtzxjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xtzxjd_updateByKey(String id, Kss_XtzxjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xtzxjd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XtzxjdModel> xtzxjd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XyrModel>> xyr_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xyr_save(Kss_XyrModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xyr_updateByKey(String id, Kss_XyrModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xyr_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XyrModel> xyr_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_XzpaModel>> xzpa_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xzpa_save(Kss_XzpaModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> xzpa_updateByKey(String id, Kss_XzpaModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> xzpa_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_XzpaModel> xzpa_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YabzModel>> yabz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yabz_save(Kss_YabzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yabz_updateByKey(String id, Kss_YabzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yabz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YabzModel> yabz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YgryModel>> ygry_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ygry_save(Kss_YgryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ygry_updateByKey(String id, Kss_YgryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ygry_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YgryModel> ygry_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YjyaylModel>> yjyayl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yjyayl_save(Kss_YjyaylModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yjyayl_updateByKey(String id, Kss_YjyaylModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yjyayl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YjyaylModel> yjyayl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YpckjlModel>> ypckjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypckjl_save(Kss_YpckjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypckjl_updateByKey(String id, Kss_YpckjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ypckjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YpckjlModel> ypckjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YpclModel>> ypcl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypcl_save(Kss_YpclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypcl_updateByKey(String id, Kss_YpclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ypcl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YpclModel> ypcl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YpjhjlModel>> ypjhjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypjhjl_save(Kss_YpjhjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypjhjl_updateByKey(String id, Kss_YpjhjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ypjhjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YpjhjlModel> ypjhjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YpkcModel>> ypkc_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypkc_save(Kss_YpkcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypkc_updateByKey(String id, Kss_YpkcModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ypkc_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YpkcModel> ypkc_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YpxxModel>> ypxx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypxx_save(Kss_YpxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ypxx_updateByKey(String id, Kss_YpxxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ypxx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YpxxModel> ypxx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YqfxModel>> yqfx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yqfx_save(Kss_YqfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yqfx_updateByKey(String id, Kss_YqfxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yqfx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YqfxModel> yqfx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YqModel>> yq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yq_save(Kss_YqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yq_updateByKey(String id, Kss_YqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YqModel> yq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YsxzModel>> ysxz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ysxz_save(Kss_YsxzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> ysxz_updateByKey(String id, Kss_YsxzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> ysxz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YsxzModel> ysxz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YyModel>> yy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yy_save(Kss_YyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yy_updateByKey(String id, Kss_YyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YyModel> yy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YzModel>> yz_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yz_save(Kss_YzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yz_updateByKey(String id, Kss_YzModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yz_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YzModel> yz_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_YzmxModel>> yzmx_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yzmx_save(Kss_YzmxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> yzmx_updateByKey(String id, Kss_YzmxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> yzmx_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_YzmxModel> yzmx_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZbdjhistoryModel>> zbdjhistory_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbdjhistory_save(Kss_ZbdjhistoryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbdjhistory_updateByKey(String id, Kss_ZbdjhistoryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zbdjhistory_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZbdjhistoryModel> zbdjhistory_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZbdjModel>> zbdj_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbdj_save(Kss_ZbdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbdj_updateByKey(String id, Kss_ZbdjModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zbdj_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZbdjModel> zbdj_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZbhglModel>> zbhgl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbhgl_save(Kss_ZbhglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbhgl_updateByKey(String id, Kss_ZbhglModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zbhgl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZbhglModel> zbhgl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZbkqModel>> zbkq_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbkq_save(Kss_ZbkqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zbkq_updateByKey(String id, Kss_ZbkqModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zbkq_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZbkqModel> zbkq_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZdryModel>> zdry_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zdry_save(Kss_ZdryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zdry_updateByKey(String id, Kss_ZdryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zdry_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZdryModel> zdry_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZdzyModel>> zdzy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zdzy_save(Kss_ZdzyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zdzy_updateByKey(String id, Kss_ZdzyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zdzy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZdzyModel> zdzy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZrapModel>> zrap_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zrap_save(Kss_ZrapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zrap_updateByKey(String id, Kss_ZrapModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zrap_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZrapModel> zrap_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyafModel>> zyaf_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyaf_save(Kss_ZyafModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyaf_updateByKey(String id, Kss_ZyafModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyaf_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyafModel> zyaf_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZydtfxhyModel>> zydtfxhy_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zydtfxhy_save(Kss_ZydtfxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zydtfxhy_updateByKey(String id, Kss_ZydtfxhyModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zydtfxhy_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZydtfxhyModel> zydtfxhy_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyrybgclModel>> zyrybgcl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrybgcl_save(Kss_ZyrybgclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrybgcl_updateByKey(String id, Kss_ZyrybgclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyrybgcl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyrybgclModel> zyrybgcl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyrybxjdModel>> zyrybxjd_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrybxjd_save(Kss_ZyrybxjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrybxjd_updateByKey(String id, Kss_ZyrybxjdModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyrybxjd_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyrybxjdModel> zyrybxjd_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyrycfjlFjcsModel>> zyrycfjlFjcs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrycfjlFjcs_save(Kss_ZyrycfjlFjcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrycfjlFjcs_updateByKey(String id, Kss_ZyrycfjlFjcsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyrycfjlFjcs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyrycfjlFjcsModel> zyrycfjlFjcs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyrycfjlModel>> zyrycfjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrycfjl_save(Kss_ZyrycfjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrycfjl_updateByKey(String id, Kss_ZyrycfjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyrycfjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyrycfjlModel> zyrycfjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyryfyjlModel>> zyryfyjl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyryfyjl_save(Kss_ZyryfyjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyryfyjl_updateByKey(String id, Kss_ZyryfyjlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyryfyjl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyryfyjlModel> zyryfyjl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyryjljlModel>> zyryjljl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyryjljl_save(Kss_ZyryjljlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyryjljl_updateByKey(String id, Kss_ZyryjljlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyryjljl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyryjljlModel> zyryjljl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Kss_ZyrytsclModel>> zyrytscl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrytscl_save(Kss_ZyrytsclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> zyrytscl_updateByKey(String id, Kss_ZyrytsclModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> zyrytscl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Kss_ZyrytsclModel> zyrytscl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}


			
			

		};
	}




}
