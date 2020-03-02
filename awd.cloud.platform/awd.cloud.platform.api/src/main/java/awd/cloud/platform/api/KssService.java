package awd.cloud.platform.api;



import awd.bj.kss.model.LshjModel;
import awd.cloud.platform.config.FeignSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import awd.cloud.platform.api.hystrix.KssFallBackFactory;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;



@FeignClient(name = "${awd.api:AWD-MIS-GATEWAY-SERVER}",configuration = FeignSupportConfig.class,fallbackFactory=KssFallBackFactory.class)
public interface KssService {
	////////////////////////////////////////////////////////////////////////////////////////////
		
	@GetMapping("/ksscore/aqjc")
	ResponseMessage<PagerResult<Kss_AqjcModel>> aqjc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/aqjc")
	ResponseMessage<String> aqjc_save(@RequestBody Kss_AqjcModel data);
	
	@PatchMapping("/ksscore/aqjc/{id}")
	ResponseMessage<String> aqjc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_AqjcModel data);
	
	@DeleteMapping("/ksscore/aqjc/{id}")
	ResponseMessage<Integer> aqjc_deleteByKey(@PathVariable(value = "id") String id);
	
	@GetMapping("/ksscore/aqjc/{id}")
	ResponseMessage<Kss_AqjcModel> aqjc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/bjgxfk")
	ResponseMessage<PagerResult<Kss_BjgxfkModel>> bjgxfk_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/bjgxfk")
	ResponseMessage<String> bjgxfk_save(@RequestBody Kss_BjgxfkModel data);

	@PatchMapping("/ksscore/bjgxfk/{id}")
	ResponseMessage<String> bjgxfk_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_BjgxfkModel data);

	@DeleteMapping("/ksscore/bjgxfk/{id}")
	ResponseMessage<Integer> bjgxfk_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/bjgxfk/{id}")
	ResponseMessage<Kss_BjgxfkModel> bjgxfk_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/bjjl")
	ResponseMessage<PagerResult<Kss_BjjlModel>> bjjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/bjjl")
	ResponseMessage<String> bjjl_save(@RequestBody Kss_BjjlModel data);

	@PatchMapping("/ksscore/bjjl/{id}")
	ResponseMessage<String> bjjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_BjjlModel data);

	@DeleteMapping("/ksscore/bjjl/{id}")
	ResponseMessage<Integer> bjjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/bjjl/{id}")
	ResponseMessage<Kss_BjjlModel> bjjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/bjxz")
	ResponseMessage<PagerResult<Kss_BjxzModel>> bjxz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/bjxz")
	ResponseMessage<String> bjxz_save(@RequestBody Kss_BjxzModel data);

	@PatchMapping("/ksscore/bjxz/{id}")
	ResponseMessage<String> bjxz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_BjxzModel data);

	@DeleteMapping("/ksscore/bjxz/{id}")
	ResponseMessage<Integer> bjxz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/bjxz/{id}")
	ResponseMessage<Kss_BjxzModel> bjxz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/bxd")
	ResponseMessage<PagerResult<Kss_BxdModel>> bxd_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/bxd")
	ResponseMessage<String> bxd_save(@RequestBody Kss_BxdModel data);

	@PatchMapping("/ksscore/bxd/{id}")
	ResponseMessage<String> bxd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_BxdModel data);

	@DeleteMapping("/ksscore/bxd/{id}")
	ResponseMessage<Integer> bxd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/bxd/{id}")
	ResponseMessage<Kss_BxdModel> bxd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/clcs")
	ResponseMessage<PagerResult<Kss_ClcsModel>> clcs_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/clcs")
	ResponseMessage<String> clcs_save(@RequestBody Kss_ClcsModel data);

	@PatchMapping("/kssbase/clcs/{id}")
	ResponseMessage<String> clcs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ClcsModel data);

	@DeleteMapping("/kssbase/clcs/{id}")
	ResponseMessage<Integer> clcs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/clcs/{id}")
	ResponseMessage<Kss_ClcsModel> clcs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/crjjc")
	ResponseMessage<PagerResult<Kss_CrjjcModel>> crjjc_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/crjjc")
	ResponseMessage<String> crjjc_save(@RequestBody Kss_CrjjcModel data);

	@PatchMapping("/kssbase/crjjc/{id}")
	ResponseMessage<String> crjjc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_CrjjcModel data);

	@DeleteMapping("/kssbase/crjjc/{id}")
	ResponseMessage<Integer> crjjc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/crjjc/{id}")
	ResponseMessage<Kss_CrjjcModel> crjjc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/cxcy")
	ResponseMessage<PagerResult<Kss_CxcyModel>> cxcy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/cxcy")
	ResponseMessage<String> cxcy_save(@RequestBody Kss_CxcyModel data);

	@PatchMapping("/ksscore/cxcy/{id}")
	ResponseMessage<String> cxcy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_CxcyModel data);

	@DeleteMapping("/ksscore/cxcy/{id}")
	ResponseMessage<Integer> cxcy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/cxcy/{id}")
	ResponseMessage<Kss_CxcyModel> cxcy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/cypj")
	ResponseMessage<PagerResult<Kss_CypjModel>> cypj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/cypj")
	ResponseMessage<String> cypj_save(@RequestBody Kss_CypjModel data);

	@PatchMapping("/ksscore/cypj/{id}")
	ResponseMessage<String> cypj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_CypjModel data);

	@DeleteMapping("/ksscore/cypj/{id}")
	ResponseMessage<Integer> cypj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/cypj/{id}")
	ResponseMessage<Kss_CypjModel> cypj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ddfjgz")
	ResponseMessage<PagerResult<Kss_DdfjgzModel>> ddfjgz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ddfjgz")
	ResponseMessage<String> ddfjgz_save(@RequestBody Kss_DdfjgzModel data);

	@PatchMapping("/ksscore/ddfjgz/{id}")
	ResponseMessage<String> ddfjgz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_DdfjgzModel data);

	@DeleteMapping("/ksscore/ddfjgz/{id}")
	ResponseMessage<Integer> ddfjgz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ddfjgz/{id}")
	ResponseMessage<Kss_DdfjgzModel> ddfjgz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/djgl")
	ResponseMessage<PagerResult<Kss_DjglModel>> djgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/djgl")
	ResponseMessage<String> djgl_save(@RequestBody Kss_DjglModel data);

	@PatchMapping("/ksscore/djgl/{id}")
	ResponseMessage<String> djgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_DjglModel data);

	@DeleteMapping("/ksscore/djgl/{id}")
	ResponseMessage<Integer> djgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/djgl/{id}")
	ResponseMessage<Kss_DjglModel> djgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/dm")
	ResponseMessage<PagerResult<Kss_DmModel>> dm_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/dm")
	ResponseMessage<String> dm_save(@RequestBody Kss_DmModel data);

	@PatchMapping("/ksscore/dm/{id}")
	ResponseMessage<String> dm_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_DmModel data);

	@DeleteMapping("/ksscore/dm/{id}")
	ResponseMessage<Integer> dm_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/dm/{id}")
	ResponseMessage<Kss_DmModel> dm_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/dxsphj")
	ResponseMessage<PagerResult<Kss_DxsphjModel>> dxsphj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/dxsphj")
	ResponseMessage<String> dxsphj_save(@RequestBody Kss_DxsphjModel data);

	@PatchMapping("/ksscore/dxsphj/{id}")
	ResponseMessage<String> dxsphj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_DxsphjModel data);

	@DeleteMapping("/ksscore/dxsphj/{id}")
	ResponseMessage<Integer> dxsphj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/dxsphj/{id}")
	ResponseMessage<Kss_DxsphjModel> dxsphj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/emdj")
	ResponseMessage<PagerResult<Kss_EmdjModel>> emdj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/emdj")
	ResponseMessage<String> emdj_save(@RequestBody Kss_EmdjModel data);

	@PatchMapping("/ksscore/emdj/{id}")
	ResponseMessage<String> emdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_EmdjModel data);

	@DeleteMapping("/ksscore/emdj/{id}")
	ResponseMessage<Integer> emdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/emdj/{id}")
	ResponseMessage<Kss_EmdjModel> emdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/fjsz")
	ResponseMessage<PagerResult<Kss_FjszModel>> fjsz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/fjsz")
	ResponseMessage<String> fjsz_save(@RequestBody Kss_FjszModel data);

	@PatchMapping("/ksscore/fjsz/{id}")
	ResponseMessage<String> fjsz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_FjszModel data);

	@DeleteMapping("/ksscore/fjsz/{id}")
	ResponseMessage<Integer> fjsz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/fjsz/{id}")
	ResponseMessage<Kss_FjszModel> fjsz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/fssg")
	ResponseMessage<PagerResult<Kss_FssgModel>> fssg_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/fssg")
	ResponseMessage<String> fssg_save(@RequestBody Kss_FssgModel data);

	@PatchMapping("/ksscore/fssg/{id}")
	ResponseMessage<String> fssg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_FssgModel data);

	@DeleteMapping("/ksscore/fssg/{id}")
	ResponseMessage<Integer> fssg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/fssg/{id}")
	ResponseMessage<Kss_FssgModel> fssg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/fxpgKh")
	ResponseMessage<PagerResult<Kss_FxpgKhModel>> fxpgKh_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/fxpgKh")
	ResponseMessage<String> fxpgKh_save(@RequestBody Kss_FxpgKhModel data);

	@PatchMapping("/ksscore/fxpgKh/{id}")
	ResponseMessage<String> fxpgKh_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_FxpgKhModel data);

	@DeleteMapping("/ksscore/fxpgKh/{id}")
	ResponseMessage<Integer> fxpgKh_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/fxpgKh/{id}")
	ResponseMessage<Kss_FxpgKhModel> fxpgKh_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/fxpg")
	ResponseMessage<PagerResult<Kss_FxpgModel>> fxpg_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/fxpg")
	ResponseMessage<String> fxpg_save(@RequestBody Kss_FxpgModel data);

	@PatchMapping("/ksscore/fxpg/{id}")
	ResponseMessage<String> fxpg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_FxpgModel data);

	@DeleteMapping("/ksscore/fxpg/{id}")
	ResponseMessage<Integer> fxpg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/fxpg/{id}")
	ResponseMessage<Kss_FxpgModel> fxpg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/fxpgRygkcs")
	ResponseMessage<PagerResult<Kss_FxpgRygkcsModel>> fxpgRygkcs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/fxpgRygkcs")
	ResponseMessage<String> fxpgRygkcs_save(@RequestBody Kss_FxpgRygkcsModel data);

	@PatchMapping("/ksscore/fxpgRygkcs/{id}")
	ResponseMessage<String> fxpgRygkcs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_FxpgRygkcsModel data);

	@DeleteMapping("/ksscore/fxpgRygkcs/{id}")
	ResponseMessage<Integer> fxpgRygkcs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/fxpgRygkcs/{id}")
	ResponseMessage<Kss_FxpgRygkcsModel> fxpgRygkcs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/gd")
	ResponseMessage<PagerResult<Kss_GdModel>> gd_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/gd")
	ResponseMessage<String> gd_save(@RequestBody Kss_GdModel data);

	@PatchMapping("/ksscore/gd/{id}")
	ResponseMessage<String> gd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_GdModel data);

	@DeleteMapping("/ksscore/gd/{id}")
	ResponseMessage<Integer> gd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/gd/{id}")
	ResponseMessage<Kss_GdModel> gd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/gwjjb")
	ResponseMessage<PagerResult<Kss_GwjjbModel>> gwjjb_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/gwjjb")
	ResponseMessage<String> gwjjb_save(@RequestBody Kss_GwjjbModel data);

	@PatchMapping("/ksscore/gwjjb/{id}")
	ResponseMessage<String> gwjjb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_GwjjbModel data);

	@DeleteMapping("/ksscore/gwjjb/{id}")
	ResponseMessage<Integer> gwjjb_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/gwjjb/{id}")
	ResponseMessage<Kss_GwjjbModel> gwjjb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/gzqk")
	ResponseMessage<PagerResult<Kss_GzqkModel>> gzqk_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/gzqk")
	ResponseMessage<String> gzqk_save(@RequestBody Kss_GzqkModel data);

	@PatchMapping("/ksscore/gzqk/{id}")
	ResponseMessage<String> gzqk_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_GzqkModel data);

	@DeleteMapping("/ksscore/gzqk/{id}")
	ResponseMessage<Integer> gzqk_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/gzqk/{id}")
	ResponseMessage<Kss_GzqkModel> gzqk_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/gzrygl")
	ResponseMessage<PagerResult<Kss_GzryglModel>> gzrygl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/gzrygl")
	ResponseMessage<String> gzrygl_save(@RequestBody Kss_GzryglModel data);

	@PatchMapping("/ksscore/gzrygl/{id}")
	ResponseMessage<String> gzrygl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_GzryglModel data);

	@DeleteMapping("/ksscore/gzrygl/{id}")
	ResponseMessage<Integer> gzrygl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/gzrygl/{id}")
	ResponseMessage<Kss_GzryglModel> gzrygl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/hjbd")
	ResponseMessage<PagerResult<Kss_HjbdModel>> hjbd_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/hjbd")
	ResponseMessage<String> hjbd_save(@RequestBody Kss_HjbdModel data);

	@PatchMapping("/kssbase/hjbd/{id}")
	ResponseMessage<String> hjbd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_HjbdModel data);

	@DeleteMapping("/kssbase/hjbd/{id}")
	ResponseMessage<Integer> hjbd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/hjbd/{id}")
	ResponseMessage<Kss_HjbdModel> hjbd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jbjl")
	ResponseMessage<PagerResult<Kss_JbjlModel>> jbjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jbjl")
	ResponseMessage<String> jbjl_save(@RequestBody Kss_JbjlModel data);

	@PatchMapping("/ksscore/jbjl/{id}")
	ResponseMessage<String> jbjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JbjlModel data);

	@DeleteMapping("/ksscore/jbjl/{id}")
	ResponseMessage<Integer> jbjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jbjl/{id}")
	ResponseMessage<Kss_JbjlModel> jbjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jb")
	ResponseMessage<PagerResult<Kss_JbModel>> jb_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jb")
	ResponseMessage<String> jb_save(@RequestBody Kss_JbModel data);

	@PatchMapping("/ksscore/jb/{id}")
	ResponseMessage<String> jb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JbModel data);

	@DeleteMapping("/ksscore/jb/{id}")
	ResponseMessage<Integer> jb_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jb/{id}")
	ResponseMessage<Kss_JbModel> jb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jbxx")
	ResponseMessage<PagerResult<Kss_JbxxModel>> jbxx_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jbxx")
	ResponseMessage<String> jbxx_save(@RequestBody Kss_JbxxModel data);

	@PatchMapping("/kssbase/jbxx/{id}")
	ResponseMessage<String> jbxx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JbxxModel data);

	@DeleteMapping("/kssbase/jbxx/{id}")
	ResponseMessage<Integer> jbxx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jbxx/{id}")
	ResponseMessage<Kss_JbxxModel> jbxx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jjwpsljl")
	ResponseMessage<PagerResult<Kss_JjwpsljlModel>> jjwpsljl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jjwpsljl")
	ResponseMessage<String> jjwpsljl_save(@RequestBody Kss_JjwpsljlModel data);

	@PatchMapping("/ksscore/jjwpsljl/{id}")
	ResponseMessage<String> jjwpsljl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JjwpsljlModel data);

	@DeleteMapping("/ksscore/jjwpsljl/{id}")
	ResponseMessage<Integer> jjwpsljl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jjwpsljl/{id}")
	ResponseMessage<Kss_JjwpsljlModel> jjwpsljl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jjx")
	ResponseMessage<PagerResult<Kss_JjxModel>> jjx_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jjx")
	ResponseMessage<String> jjx_save(@RequestBody Kss_JjxModel data);

	@PatchMapping("/kssbase/jjx/{id}")
	ResponseMessage<String> jjx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JjxModel data);

	@DeleteMapping("/kssbase/jjx/{id}")
	ResponseMessage<Integer> jjx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jjx/{id}")
	ResponseMessage<Kss_JjxModel> jjx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jkfjl")
	ResponseMessage<PagerResult<Kss_JkfjlModel>> jkfjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jkfjl")
	ResponseMessage<String> jkfjl_save(@RequestBody Kss_JkfjlModel data);

	@PatchMapping("/ksscore/jkfjl/{id}")
	ResponseMessage<String> jkfjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JkfjlModel data);

	@DeleteMapping("/ksscore/jkfjl/{id}")
	ResponseMessage<Integer> jkfjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jkfjl/{id}")
	ResponseMessage<Kss_JkfjlModel> jkfjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jkqk")
	ResponseMessage<PagerResult<Kss_JkqkModel>> jkqk_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jkqk")
	ResponseMessage<String> jkqk_save(@RequestBody Kss_JkqkModel data);

	@PatchMapping("/kssbase/jkqk/{id}")
	ResponseMessage<String> jkqk_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JkqkModel data);

	@DeleteMapping("/kssbase/jkqk/{id}")
	ResponseMessage<Integer> jkqk_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jkqk/{id}")
	ResponseMessage<Kss_JkqkModel> jkqk_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jkrz")
	ResponseMessage<PagerResult<Kss_JkrzModel>> jkrz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jkrz")
	ResponseMessage<String> jkrz_save(@RequestBody Kss_JkrzModel data);

	@PatchMapping("/ksscore/jkrz/{id}")
	ResponseMessage<String> jkrz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JkrzModel data);

	@DeleteMapping("/ksscore/jkrz/{id}")
	ResponseMessage<Integer> jkrz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jkrz/{id}")
	ResponseMessage<Kss_JkrzModel> jkrz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jq")
	ResponseMessage<PagerResult<Kss_JqModel>> jq_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jq")
	ResponseMessage<String> jq_save(@RequestBody Kss_JqModel data);

	@PatchMapping("/kssbase/jq/{id}")
	ResponseMessage<String> jq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JqModel data);

	@DeleteMapping("/kssbase/jq/{id}")
	ResponseMessage<Integer> jq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jq/{id}")
	ResponseMessage<Kss_JqModel> jq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jqfb")
	ResponseMessage<PagerResult<Kss_JqfbModel>> jqfb_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jqfb")
	ResponseMessage<String> jqfb_save(@RequestBody Kss_JqfbModel data);

	@PatchMapping("/ksscore/jqfb/{id}")
	ResponseMessage<String> jqfb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JqfbModel data);

	@DeleteMapping("/ksscore/jqfb/{id}")
	ResponseMessage<Integer> jqfb_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/manager/jqfb/{id}")
	ResponseMessage<Kss_JqfbModel> jqfb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jqzx")
	ResponseMessage<PagerResult<Kss_JqzxModel>> jqzx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jqzx")
	ResponseMessage<String> jqzx_save(@RequestBody Kss_JqzxModel data);

	@PatchMapping("/ksscore/jqzx/{id}")
	ResponseMessage<String> jqzx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JqzxModel data);

	@DeleteMapping("/ksscore/jqzx/{id}")
	ResponseMessage<Integer> jqzx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jqzx/{id}")
	ResponseMessage<Kss_JqzxModel> jqzx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jsbjd")
	ResponseMessage<PagerResult<Kss_JsbjdModel>> jsbjd_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jsbjd")
	ResponseMessage<String> jsbjd_save(@RequestBody Kss_JsbjdModel data);

	@PatchMapping("/ksscore/jsbjd/{id}")
	ResponseMessage<String> jsbjd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JsbjdModel data);

	@DeleteMapping("/ksscore/jsbjd/{id}")
	ResponseMessage<Integer> jsbjd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jsbjd/{id}")
	ResponseMessage<Kss_JsbjdModel> jsbjd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/js")
	ResponseMessage<PagerResult<Kss_JsModel>> js_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/js")
	ResponseMessage<String> js_save(@RequestBody Kss_JsModel data);

	@PatchMapping("/kssbase/js/{id}")
	ResponseMessage<String> js_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JsModel data);

	@DeleteMapping("/kssbase/js/{id}")
	ResponseMessage<Integer> js_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/js/{id}")
	ResponseMessage<Kss_JsModel> js_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jshj")
	ResponseMessage<PagerResult<Kss_JshjModel>> jshj_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jshj")
	ResponseMessage<String> jshj_save(@RequestBody Kss_JshjModel data);

	@PatchMapping("/kssbase/jshj/{id}")
	ResponseMessage<String> jshj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JshjModel data);

	@DeleteMapping("/kssbase/jshj/{id}")
	ResponseMessage<Integer> jshj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jshj/{id}")
	ResponseMessage<Kss_JshjModel> jshj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jslx")
	ResponseMessage<PagerResult<Kss_JslxModel>> jslx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jslx")
	ResponseMessage<String> jslx_save(@RequestBody Kss_JslxModel data);

	@PatchMapping("/ksscore/jslx/{id}")
	ResponseMessage<String> jslx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JslxModel data);

	@DeleteMapping("/ksscore/jslx/{id}")
	ResponseMessage<Integer> jslx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jslx/{id}")
	ResponseMessage<Kss_JslxModel> jslx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jsnw")
	ResponseMessage<PagerResult<Kss_JsnwModel>> jsnw_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jsnw")
	ResponseMessage<String> jsnw_save(@RequestBody Kss_JsnwModel data);

	@PatchMapping("/ksscore/jsnw/{id}")
	ResponseMessage<String> jsnw_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JsnwModel data);

	@DeleteMapping("/ksscore/jsnw/{id}")
	ResponseMessage<Integer> jsnw_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jsnw/{id}")
	ResponseMessage<Kss_JsnwModel> jsnw_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jssw")
	ResponseMessage<PagerResult<Kss_JsswModel>> jssw_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jssw")
	ResponseMessage<String> jssw_save(@RequestBody Kss_JsswModel data);

	@PatchMapping("/ksscore/jssw/{id}")
	ResponseMessage<String> jssw_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JsswModel data);

	@DeleteMapping("/ksscore/jssw/{id}")
	ResponseMessage<Integer> jssw_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jssw/{id}")
	ResponseMessage<Kss_JsswModel> jssw_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/jstz")
	ResponseMessage<PagerResult<Kss_JstzModel>> jstz_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/jstz")
	ResponseMessage<String> jstz_save(@RequestBody Kss_JstzModel data);

	@PatchMapping("/kssbase/jstz/{id}")
	ResponseMessage<String> jstz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JstzModel data);

	@DeleteMapping("/kssbase/jstz/{id}")
	ResponseMessage<Integer> jstz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/jstz/{id}")
	ResponseMessage<Kss_JstzModel> jstz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jsycgl")
	ResponseMessage<PagerResult<Kss_JsycglModel>> jsycgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jsycgl")
	ResponseMessage<String> jsycgl_save(@RequestBody Kss_JsycglModel data);

	@PatchMapping("/ksscore/jsycgl/{id}")
	ResponseMessage<String> jsycgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JsycglModel data);

	@DeleteMapping("/ksscore/jsycgl/{id}")
	ResponseMessage<Integer> jsycgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jsycgl/{id}")
	ResponseMessage<Kss_JsycglModel> jsycgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jszxjc")
	ResponseMessage<PagerResult<Kss_JszxjcModel>> jszxjc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jszxjc")
	ResponseMessage<String> jszxjc_save(@RequestBody Kss_JszxjcModel data);

	@PatchMapping("/ksscore/jszxjc/{id}")
	ResponseMessage<String> jszxjc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JszxjcModel data);

	@DeleteMapping("/ksscore/jszxjc/{id}")
	ResponseMessage<Integer> jszxjc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jszxjc/{id}")
	ResponseMessage<Kss_JszxjcModel> jszxjc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jtjy")
	ResponseMessage<PagerResult<Kss_JtjyModel>> jtjy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jtjy")
	ResponseMessage<String> jtjy_save(@RequestBody Kss_JtjyModel data);

	@PatchMapping("/ksscore/jtjy/{id}")
	ResponseMessage<String> jtjy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JtjyModel data);

	@DeleteMapping("/ksscore/jtjy/{id}")
	ResponseMessage<Integer> jtjy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jtjy/{id}")
	ResponseMessage<Kss_JtjyModel> jtjy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jwzxjl")
	ResponseMessage<PagerResult<Kss_JwzxjlModel>> jwzxjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jwzxjl")
	ResponseMessage<String> jwzxjl_save(@RequestBody Kss_JwzxjlModel data);

	@PatchMapping("/ksscore/jwzxjl/{id}")
	ResponseMessage<String> jwzxjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JwzxjlModel data);

	@DeleteMapping("/ksscore/jwzxjl/{id}")
	ResponseMessage<Integer> jwzxjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jwzxjl/{id}")
	ResponseMessage<Kss_JwzxjlModel> jwzxjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jxkh")
	ResponseMessage<PagerResult<Kss_JxkhModel>> jxkh_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jxkh")
	ResponseMessage<String> jxkh_save(@RequestBody Kss_JxkhModel data);

	@PatchMapping("/ksscore/jxkh/{id}")
	ResponseMessage<String> jxkh_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JxkhModel data);

	@DeleteMapping("/ksscore/jxkh/{id}")
	ResponseMessage<Integer> jxkh_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jxkh/{id}")
	ResponseMessage<Kss_JxkhModel> jxkh_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jygzzlpg")
	ResponseMessage<PagerResult<Kss_JygzzlpgModel>> jygzzlpg_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jygzzlpg")
	ResponseMessage<String> jygzzlpg_save(@RequestBody Kss_JygzzlpgModel data);

	@PatchMapping("/ksscore/jygzzlpg/{id}")
	ResponseMessage<String> jygzzlpg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JygzzlpgModel data);

	@DeleteMapping("/ksscore/jygzzlpg/{id}")
	ResponseMessage<Integer> jygzzlpg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jygzzlpg/{id}")
	ResponseMessage<Kss_JygzzlpgModel> jygzzlpg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/jy")
	ResponseMessage<PagerResult<Kss_JyModel>> jy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/jy")
	ResponseMessage<String> jy_save(@RequestBody Kss_JyModel data);

	@PatchMapping("/ksscore/jy/{id}")
	ResponseMessage<String> jy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_JyModel data);

	@DeleteMapping("/ksscore/jy/{id}")
	ResponseMessage<Integer> jy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/jy/{id}")
	ResponseMessage<Kss_JyModel> jy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/kss")
	ResponseMessage<PagerResult<Kss_KssModel>> kss_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/kss")
	ResponseMessage<String> kss_save(@RequestBody Kss_KssModel data);

	@PatchMapping("/kssbase/kss/{id}")
	ResponseMessage<String> kss_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_KssModel data);

	@DeleteMapping("/kssbase/kss/{id}")
	ResponseMessage<Integer> kss_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/kss/{id}")
	ResponseMessage<Kss_KssModel> kss_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ldf")
	ResponseMessage<PagerResult<Kss_LdfModel>> ldf_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ldf")
	ResponseMessage<String> ldf_save(@RequestBody Kss_LdfModel data);

	@PatchMapping("/ksscore/ldf/{id}")
	ResponseMessage<String> ldf_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LdfModel data);

	@DeleteMapping("/ksscore/ldf/{id}")
	ResponseMessage<Integer> ldf_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ldf/{id}")
	ResponseMessage<Kss_LdfModel> ldf_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ldsp")
	ResponseMessage<PagerResult<Kss_LdspModel>> ldsp_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ldsp")
	ResponseMessage<String> ldsp_save(@RequestBody Kss_LdspModel data);

	@PatchMapping("/ksscore/ldsp/{id}")
	ResponseMessage<String> ldsp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LdspModel data);

	@DeleteMapping("/ksscore/ldsp/{id}")
	ResponseMessage<Integer> ldsp_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ldsp/{id}")
	ResponseMessage<Kss_LdspModel> ldsp_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lkgl")
	ResponseMessage<PagerResult<Kss_LkglModel>> lkgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lkgl")
	ResponseMessage<String> lkgl_save(@RequestBody Kss_LkglModel data);

	@PatchMapping("/ksscore/lkgl/{id}")
	ResponseMessage<String> lkgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LkglModel data);

	@DeleteMapping("/ksscore/lkgl/{id}")
	ResponseMessage<Integer> lkgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lkgl/{id}")
	ResponseMessage<Kss_LkglModel> lkgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lsbd")
	ResponseMessage<PagerResult<Kss_LsbdModel>> lsbd_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lsbd")
	ResponseMessage<String> lsbd_save(@RequestBody Kss_LsbdModel data);

	@PatchMapping("/ksscore/lsbd/{id}")
	ResponseMessage<String> lsbd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LsbdModel data);

	@DeleteMapping("/ksscore/lsbd/{id}")
	ResponseMessage<Integer> lsbd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lsbd/{id}")
	ResponseMessage<Kss_LsbdModel> lsbd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lscs")
	ResponseMessage<PagerResult<Kss_LscsModel>> lscs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lscs")
	ResponseMessage<String> lscs_save(@RequestBody Kss_LscsModel data);

	@PatchMapping("/ksscore/lscs/{id}")
	ResponseMessage<String> lscs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LscsModel data);

	@DeleteMapping("/ksscore/lscs/{id}")
	ResponseMessage<Integer> lscs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lscs/{id}")
	ResponseMessage<Kss_LscsModel> lscs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lsfx")
	ResponseMessage<PagerResult<Kss_LsfxModel>> lsfx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lsfx")
	ResponseMessage<String> lsfx_save(@RequestBody Kss_LsfxModel data);

	@PatchMapping("/ksscore/lsfx/{id}")
	ResponseMessage<String> lsfx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LsfxModel data);

	@DeleteMapping("/ksscore/lsfx/{id}")
	ResponseMessage<Integer> lsfx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lsfx/{id}")
	ResponseMessage<Kss_LsfxModel> lsfx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lsfxykh")
	ResponseMessage<PagerResult<Kss_LsfxykhModel>> lsfxykh_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lsfxykh")
	ResponseMessage<String> lsfxykh_save(@RequestBody Kss_LsfxykhModel data);

	@PatchMapping("/ksscore/lsfxykh/{id}")
	ResponseMessage<String> lsfxykh_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LsfxykhModel data);

	@DeleteMapping("/ksscore/lsfxykh/{id}")
	ResponseMessage<Integer> lsfxykh_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lsfxykh/{id}")
	ResponseMessage<Kss_LsfxykhModel> lsfxykh_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 查询律师会见
	 * */
	@GetMapping("/kssbase/lshj")
	ResponseMessage<PagerResult<LshjModel>> lshj_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/lshj")
	ResponseMessage<String> lshj_save(@RequestBody Kss_LshjModel data);

	@PatchMapping("/kssbase/lshj/{id}")
	ResponseMessage<String> lshj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LshjModel data);

	@DeleteMapping("/kssbase/lshj/{id}")
	ResponseMessage<Integer> lshj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/lshj/{id}")
	ResponseMessage<Kss_LshjModel> lshj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lstlhmc")
	ResponseMessage<PagerResult<Kss_LstlhmcModel>> lstlhmc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lstlhmc")
	ResponseMessage<String> lstlhmc_save(@RequestBody Kss_LstlhmcModel data);

	@PatchMapping("/ksscore/lstlhmc/{id}")
	ResponseMessage<String> lstlhmc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LstlhmcModel data);

	@DeleteMapping("/ksscore/lstlhmc/{id}")
	ResponseMessage<Integer> lstlhmc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lstlhmc/{id}")
	ResponseMessage<Kss_LstlhmcModel> lstlhmc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lswg")
	ResponseMessage<PagerResult<Kss_LswgModel>> lswg_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lswg")
	ResponseMessage<String> lswg_save(@RequestBody Kss_LswgModel data);

	@PatchMapping("/ksscore/lswg/{id}")
	ResponseMessage<String> lswg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LswgModel data);

	@DeleteMapping("/ksscore/lswg/{id}")
	ResponseMessage<Integer> lswg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lswg/{id}")
	ResponseMessage<Kss_LswgModel> lswg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lxhy")
	ResponseMessage<PagerResult<Kss_LxhyModel>> lxhy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lxhy")
	ResponseMessage<String> lxhy_save(@RequestBody Kss_LxhyModel data);

	@PatchMapping("/ksscore/lxhy/{id}")
	ResponseMessage<String> lxhy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LxhyModel data);

	@DeleteMapping("/ksscore/lxhy/{id}")
	ResponseMessage<Integer> lxhy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lxhy/{id}")
	ResponseMessage<Kss_LxhyModel> lxhy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/lyb")
	ResponseMessage<PagerResult<Kss_LybModel>> lyb_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/lyb")
	ResponseMessage<String> lyb_save(@RequestBody Kss_LybModel data);

	@PatchMapping("/ksscore/lyb/{id}")
	ResponseMessage<String> lyb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_LybModel data);

	@DeleteMapping("/ksscore/lyb/{id}")
	ResponseMessage<Integer> lyb_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/lyb/{id}")
	ResponseMessage<Kss_LybModel> lyb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjgzjl")
	ResponseMessage<PagerResult<Kss_MjgzjlModel>> mjgzjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjgzjl")
	ResponseMessage<String> mjgzjl_save(@RequestBody Kss_MjgzjlModel data);

	@PatchMapping("/ksscore/mjgzjl/{id}")
	ResponseMessage<String> mjgzjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjgzjlModel data);

	@DeleteMapping("/ksscore/mjgzjl/{id}")
	ResponseMessage<Integer> mjgzjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjgzjl/{id}")
	ResponseMessage<Kss_MjgzjlModel> mjgzjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjjbxx")
	ResponseMessage<PagerResult<Kss_MjjbxxModel>> mjjbxx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjjbxx")
	ResponseMessage<String> mjjbxx_save(@RequestBody Kss_MjjbxxModel data);

	@PatchMapping("/ksscore/mjjbxx/{id}")
	ResponseMessage<String> mjjbxx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjjbxxModel data);

	@DeleteMapping("/ksscore/mjjbxx/{id}")
	ResponseMessage<Integer> mjjbxx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjjbxx/{id}")
	ResponseMessage<Kss_MjjbxxModel> mjjbxx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjjcjl")
	ResponseMessage<PagerResult<Kss_MjjcjlModel>> mjjcjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjjcjl")
	ResponseMessage<String> mjjcjl_save(@RequestBody Kss_MjjcjlModel data);

	@PatchMapping("/ksscore/mjjcjl/{id}")
	ResponseMessage<String> mjjcjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjjcjlModel data);

	@DeleteMapping("/ksscore/mjjcjl/{id}")
	ResponseMessage<Integer> mjjcjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjjcjl/{id}")
	ResponseMessage<Kss_MjjcjlModel> mjjcjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjjyjl")
	ResponseMessage<PagerResult<Kss_MjjyjlModel>> mjjyjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjjyjl")
	ResponseMessage<String> mjjyjl_save(@RequestBody Kss_MjjyjlModel data);

	@PatchMapping("/ksscore/mjjyjl/{id}")
	ResponseMessage<String> mjjyjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjjyjlModel data);

	@DeleteMapping("/ksscore/mjjyjl/{id}")
	ResponseMessage<Integer> mjjyjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjjyjl/{id}")
	ResponseMessage<Kss_MjjyjlModel> mjjyjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjkh")
	ResponseMessage<PagerResult<Kss_MjkhModel>> mjkh_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjkh")
	ResponseMessage<String> mjkh_save(@RequestBody Kss_MjkhModel data);

	@PatchMapping("/ksscore/mjkh/{id}")
	ResponseMessage<String> mjkh_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjkhModel data);

	@DeleteMapping("/ksscore/mjkh/{id}")
	ResponseMessage<Integer> mjkh_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjkh/{id}")
	ResponseMessage<Kss_MjkhModel> mjkh_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjshgx")
	ResponseMessage<PagerResult<Kss_MjshgxModel>> mjshgx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjshgx")
	ResponseMessage<String> mjshgx_save(@RequestBody Kss_MjshgxModel data);

	@PatchMapping("/ksscore/mjshgx/{id}")
	ResponseMessage<String> mjshgx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjshgxModel data);

	@DeleteMapping("/ksscore/mjshgx/{id}")
	ResponseMessage<Integer> mjshgx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjshgx/{id}")
	ResponseMessage<Kss_MjshgxModel> mjshgx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjzf")
	ResponseMessage<PagerResult<Kss_MjzfModel>> mjzf_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjzf")
	ResponseMessage<String> mjzf_save(@RequestBody Kss_MjzfModel data);

	@PatchMapping("/ksscore/mjzf/{id}")
	ResponseMessage<String> mjzf_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjzfModel data);

	@DeleteMapping("/ksscore/mjzf/{id}")
	ResponseMessage<Integer> mjzf_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjzf/{id}")
	ResponseMessage<Kss_MjzfModel> mjzf_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/mjzp")
	ResponseMessage<PagerResult<Kss_MjzpModel>> mjzp_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/mjzp")
	ResponseMessage<String> mjzp_save(@RequestBody Kss_MjzpModel data);

	@PatchMapping("/ksscore/mjzp/{id}")
	ResponseMessage<String> mjzp_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_MjzpModel data);

	@DeleteMapping("/ksscore/mjzp/{id}")
	ResponseMessage<Integer> mjzp_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/mjzp/{id}")
	ResponseMessage<Kss_MjzpModel> mjzp_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/nbxc")
	ResponseMessage<PagerResult<Kss_NbxcModel>> nbxc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/nbxc")
	ResponseMessage<String> nbxc_save(@RequestBody Kss_NbxcModel data);

	@PatchMapping("/ksscore/nbxc/{id}")
	ResponseMessage<String> nbxc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_NbxcModel data);

	@DeleteMapping("/ksscore/nbxc/{id}")
	ResponseMessage<Integer> nbxc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/nbxc/{id}")
	ResponseMessage<Kss_NbxcModel> nbxc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
		
	@GetMapping("/kssbase/photos")
	ResponseMessage<PagerResult<Kss_PhotosModel>> photos_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/photos")
	ResponseMessage<String> photos_save(@RequestBody Kss_PhotosModel data);
	
	@PatchMapping("/kssbase/photos/{id}")
	ResponseMessage<String> photos_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_PhotosModel data);
	
	@DeleteMapping("/kssbase/photos/{id}")
	ResponseMessage<Integer> photos_deleteByKey(@PathVariable(value = "id") String id);
	
	@GetMapping("/kssbase/photos/{id}")
	ResponseMessage<Kss_PhotosModel> photos_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/pjdj")
	ResponseMessage<PagerResult<Kss_PjdjModel>> pjdj_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/pjdj")
	ResponseMessage<String> pjdj_save(@RequestBody Kss_PjdjModel data);

	@PatchMapping("/kssbase/pjdj/{id}")
	ResponseMessage<String> pjdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_PjdjModel data);

	@DeleteMapping("/kssbase/pjdj/{id}")
	ResponseMessage<Integer> pjdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/pjdj/{id}")
	ResponseMessage<Kss_PjdjModel> pjdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/purchase")
	ResponseMessage<PagerResult<Kss_PurchaseModel>> purchase_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/purchase")
	ResponseMessage<String> purchase_save(@RequestBody Kss_PurchaseModel data);

	@PatchMapping("/ksscore/purchase/{id}")
	ResponseMessage<String> purchase_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_PurchaseModel data);

	@DeleteMapping("/ksscore/purchase/{id}")
	ResponseMessage<Integer> purchase_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/purchase/{id}")
	ResponseMessage<Kss_PurchaseModel> purchase_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/qjgl")
	ResponseMessage<PagerResult<Kss_QjglModel>> qjgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/qjgl")
	ResponseMessage<String> qjgl_save(@RequestBody Kss_QjglModel data);

	@PatchMapping("/ksscore/qjgl/{id}")
	ResponseMessage<String> qjgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_QjglModel data);

	@DeleteMapping("/ksscore/qjgl/{id}")
	ResponseMessage<Integer> qjgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/qjgl/{id}")
	ResponseMessage<Kss_QjglModel> qjgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/qkfy")
	ResponseMessage<PagerResult<Kss_QkfyModel>> qkfy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/qkfy")
	ResponseMessage<String> qkfy_save(@RequestBody Kss_QkfyModel data);

	@PatchMapping("/ksscore/qkfy/{id}")
	ResponseMessage<String> qkfy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_QkfyModel data);

	@DeleteMapping("/ksscore/qkfy/{id}")
	ResponseMessage<Integer> qkfy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/qkfy/{id}")
	ResponseMessage<Kss_QkfyModel> qkfy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/qlywgz")
	ResponseMessage<PagerResult<Kss_QlywgzModel>> qlywgz_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/qlywgz")
	ResponseMessage<String> qlywgz_save(@RequestBody Kss_QlywgzModel data);

	@PatchMapping("/kssbase/qlywgz/{id}")
	ResponseMessage<String> qlywgz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_QlywgzModel data);

	@DeleteMapping("/kssbase/qlywgz/{id}")
	ResponseMessage<Integer> qlywgz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/qlywgz/{id}")
	ResponseMessage<Kss_QlywgzModel> qlywgz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/qybz")
	ResponseMessage<PagerResult<Kss_QybzModel>> qybz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/qybz")
	ResponseMessage<String> qybz_save(@RequestBody Kss_QybzModel data);

	@PatchMapping("/ksscore/qybz/{id}")
	ResponseMessage<String> qybz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_QybzModel data);

	@DeleteMapping("/ksscore/qybz/{id}")
	ResponseMessage<Integer> qybz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/qybz/{id}")
	ResponseMessage<Kss_QybzModel> qybz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/rsjc")
	ResponseMessage<PagerResult<Kss_RsjcModel>> rsjc_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/rsjc")
	ResponseMessage<String> rsjc_save(@RequestBody Kss_RsjcModel data);

	@PatchMapping("/kssbase/rsjc/{id}")
	ResponseMessage<String> rsjc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_RsjcModel data);

	@DeleteMapping("/kssbase/rsjc/{id}")
	ResponseMessage<Integer> rsjc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/rsjc/{id}")
	ResponseMessage<Kss_RsjcModel> rsjc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/rykh")
	ResponseMessage<PagerResult<Kss_RykhModel>> rykh_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/rykh")
	ResponseMessage<String> rykh_save(@RequestBody Kss_RykhModel data);

	@PatchMapping("/ksscore/rykh/{id}")
	ResponseMessage<String> rykh_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_RykhModel data);

	@DeleteMapping("/ksscore/rykh/{id}")
	ResponseMessage<Integer> rykh_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/rykh/{id}")
	ResponseMessage<Kss_RykhModel> rykh_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/scqk")
	ResponseMessage<PagerResult<Kss_ScqkModel>> scqk_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/scqk")
	ResponseMessage<String> scqk_save(@RequestBody Kss_ScqkModel data);

	@PatchMapping("/ksscore/scqk/{id}")
	ResponseMessage<String> scqk_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ScqkModel data);

	@DeleteMapping("/ksscore/scqk/{id}")
	ResponseMessage<Integer> scqk_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/scqk/{id}")
	ResponseMessage<Kss_ScqkModel> scqk_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/shff")
	ResponseMessage<PagerResult<Kss_ShffModel>> shff_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/shff")
	ResponseMessage<String> shff_save(@RequestBody Kss_ShffModel data);

	@PatchMapping("/ksscore/shff/{id}")
	ResponseMessage<String> shff_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ShffModel data);

	@DeleteMapping("/ksscore/shff/{id}")
	ResponseMessage<Integer> shff_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/shff/{id}")
	ResponseMessage<Kss_ShffModel> shff_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/shgx")
	ResponseMessage<PagerResult<Kss_ShgxModel>> shgx_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/shgx")
	ResponseMessage<String> shgx_save(@RequestBody Kss_ShgxModel data);

	@PatchMapping("/kssbase/shgx/{id}")
	ResponseMessage<String> shgx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ShgxModel data);

	@DeleteMapping("/kssbase/shgx/{id}")
	ResponseMessage<Integer> shgx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/shgx/{id}")
	ResponseMessage<Kss_ShgxModel> shgx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/sjyy")
	ResponseMessage<PagerResult<Kss_SjyyModel>> sjyy_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/sjyy")
	ResponseMessage<String> sjyy_save(@RequestBody Kss_SjyyModel data);

	@PatchMapping("/kssbase/sjyy/{id}")
	ResponseMessage<String> sjyy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SjyyModel data);

	@DeleteMapping("/kssbase/sjyy/{id}")
	ResponseMessage<Integer> sjyy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/sjyy/{id}")
	ResponseMessage<Kss_SjyyModel> sjyy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/spdetailorder")
	ResponseMessage<PagerResult<Kss_SpdetailorderModel>> spdetailorder_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/spdetailorder")
	ResponseMessage<String> spdetailorder_save(@RequestBody Kss_SpdetailorderModel data);

	@PatchMapping("/ksscore/spdetailorder/{id}")
	ResponseMessage<String> spdetailorder_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SpdetailorderModel data);

	@DeleteMapping("/ksscore/spdetailorder/{id}")
	ResponseMessage<Integer> spdetailorder_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/spdetailorder/{id}")
	ResponseMessage<Kss_SpdetailorderModel> spdetailorder_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sphjap")
	ResponseMessage<PagerResult<Kss_SphjapModel>> sphjap_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sphjap")
	ResponseMessage<String> sphjap_save(@RequestBody Kss_SphjapModel data);

	@PatchMapping("/ksscore/sphjap/{id}")
	ResponseMessage<String> sphjap_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SphjapModel data);

	@DeleteMapping("/ksscore/sphjap/{id}")
	ResponseMessage<Integer> sphjap_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sphjap/{id}")
	ResponseMessage<Kss_SphjapModel> sphjap_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sphjqkdj")
	ResponseMessage<PagerResult<Kss_SphjqkdjModel>> sphjqkdj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sphjqkdj")
	ResponseMessage<String> sphjqkdj_save(@RequestBody Kss_SphjqkdjModel data);

	@PatchMapping("/ksscore/sphjqkdj/{id}")
	ResponseMessage<String> sphjqkdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SphjqkdjModel data);

	@DeleteMapping("/ksscore/sphjqkdj/{id}")
	ResponseMessage<Integer> sphjqkdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sphjqkdj/{id}")
	ResponseMessage<Kss_SphjqkdjModel> sphjqkdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sphjsq")
	ResponseMessage<PagerResult<Kss_SphjsqModel>> sphjsq_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sphjsq")
	ResponseMessage<String> sphjsq_save(@RequestBody Kss_SphjsqModel data);

	@PatchMapping("/ksscore/sphjsq/{id}")
	ResponseMessage<String> sphjsq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SphjsqModel data);

	@DeleteMapping("/ksscore/sphjsq/{id}")
	ResponseMessage<Integer> sphjsq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sphjsq/{id}")
	ResponseMessage<Kss_SphjsqModel> sphjsq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sporder")
	ResponseMessage<PagerResult<Kss_SporderModel>> sporder_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sporder")
	ResponseMessage<String> sporder_save(@RequestBody Kss_SporderModel data);

	@PatchMapping("/ksscore/sporder/{id}")
	ResponseMessage<String> sporder_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SporderModel data);

	@DeleteMapping("/ksscore/sporder/{id}")
	ResponseMessage<Integer> sporder_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sporder/{id}")
	ResponseMessage<Kss_SporderModel> sporder_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/spxx")
	ResponseMessage<PagerResult<Kss_SpxxModel>> spxx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/spxx")
	ResponseMessage<String> spxx_save(@RequestBody Kss_SpxxModel data);

	@PatchMapping("/ksscore/spxx/{id}")
	ResponseMessage<String> spxx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SpxxModel data);

	@DeleteMapping("/ksscore/spxx/{id}")
	ResponseMessage<Integer> spxx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/spxx/{id}")
	ResponseMessage<Kss_SpxxModel> spxx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sqdtfxhy")
	ResponseMessage<PagerResult<Kss_SqdtfxhyModel>> sqdtfxhy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sqdtfxhy")
	ResponseMessage<String> sqdtfxhy_save(@RequestBody Kss_SqdtfxhyModel data);

	@PatchMapping("/ksscore/sqdtfxhy/{id}")
	ResponseMessage<String> sqdtfxhy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SqdtfxhyModel data);

	@DeleteMapping("/ksscore/sqdtfxhy/{id}")
	ResponseMessage<Integer> sqdtfxhy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sqdtfxhy/{id}")
	ResponseMessage<Kss_SqdtfxhyModel> sqdtfxhy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sqfx")
	ResponseMessage<PagerResult<Kss_SqfxModel>> sqfx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sqfx")
	ResponseMessage<String> sqfx_save(@RequestBody Kss_SqfxModel data);

	@PatchMapping("/ksscore/sqfx/{id}")
	ResponseMessage<String> sqfx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SqfxModel data);

	@DeleteMapping("/ksscore/sqfx/{id}")
	ResponseMessage<Integer> sqfx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sqfx/{id}")
	ResponseMessage<Kss_SqfxModel> sqfx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/swdj")
	ResponseMessage<PagerResult<Kss_SwdjModel>> swdj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/swdj")
	ResponseMessage<String> swdj_save(@RequestBody Kss_SwdjModel data);

	@PatchMapping("/ksscore/swdj/{id}")
	ResponseMessage<String> swdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SwdjModel data);

	@DeleteMapping("/ksscore/swdj/{id}")
	ResponseMessage<Integer> swdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/swdj/{id}")
	ResponseMessage<Kss_SwdjModel> swdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/swfz")
	ResponseMessage<PagerResult<Kss_SwfzModel>> swfz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/swfz")
	ResponseMessage<String> swfz_save(@RequestBody Kss_SwfzModel data);

	@PatchMapping("/ksscore/swfz/{id}")
	ResponseMessage<String> swfz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SwfzModel data);

	@DeleteMapping("/ksscore/swfz/{id}")
	ResponseMessage<Integer> swfz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/swfz/{id}")
	ResponseMessage<Kss_SwfzModel> swfz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/swgl")
	ResponseMessage<PagerResult<Kss_SwglModel>> swgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/swgl")
	ResponseMessage<String> swgl_save(@RequestBody Kss_SwglModel data);

	@PatchMapping("/ksscore/swgl/{id}")
	ResponseMessage<String> swgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SwglModel data);

	@DeleteMapping("/ksscore/swgl/{id}")
	ResponseMessage<Integer> swgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/swgl/{id}")
	ResponseMessage<Kss_SwglModel> swgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/swhy")
	ResponseMessage<PagerResult<Kss_SwhyModel>> swhy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/swhy")
	ResponseMessage<String> swhy_save(@RequestBody Kss_SwhyModel data);

	@PatchMapping("/ksscore/swhy/{id}")
	ResponseMessage<String> swhy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SwhyModel data);

	@DeleteMapping("/ksscore/swhy/{id}")
	ResponseMessage<Integer> swhy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/swhy/{id}")
	ResponseMessage<Kss_SwhyModel> swhy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sxsphjap")
	ResponseMessage<PagerResult<Kss_SxsphjapModel>> sxsphjap_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sxsphjap")
	ResponseMessage<String> sxsphjap_save(@RequestBody Kss_SxsphjapModel data);

	@PatchMapping("/ksscore/sxsphjap/{id}")
	ResponseMessage<String> sxsphjap_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SxsphjapModel data);

	@DeleteMapping("/ksscore/sxsphjap/{id}")
	ResponseMessage<Integer> sxsphjap_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sxsphjap/{id}")
	ResponseMessage<Kss_SxsphjapModel> sxsphjap_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sxsphj")
	ResponseMessage<PagerResult<Kss_SxsphjModel>> sxsphj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sxsphj")
	ResponseMessage<String> sxsphj_save(@RequestBody Kss_SxsphjModel data);

	@PatchMapping("/ksscore/sxsphj/{id}")
	ResponseMessage<String> sxsphj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SxsphjModel data);

	@DeleteMapping("/ksscore/sxsphj/{id}")
	ResponseMessage<Integer> sxsphj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sxsphj/{id}")
	ResponseMessage<Kss_SxsphjModel> sxsphj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/sxsphjsq")
	ResponseMessage<PagerResult<Kss_SxsphjsqModel>> sxsphjsq_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/sxsphjsq")
	ResponseMessage<String> sxsphjsq_save(@RequestBody Kss_SxsphjsqModel data);

	@PatchMapping("/ksscore/sxsphjsq/{id}")
	ResponseMessage<String> sxsphjsq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SxsphjsqModel data);

	@DeleteMapping("/ksscore/sxsphjsq/{id}")
	ResponseMessage<Integer> sxsphjsq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/sxsphjsq/{id}")
	ResponseMessage<Kss_SxsphjsqModel> sxsphjsq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/szjdjl")
	ResponseMessage<PagerResult<Kss_SzjdjlModel>> szjdjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/szjdjl")
	ResponseMessage<String> szjdjl_save(@RequestBody Kss_SzjdjlModel data);

	@PatchMapping("/ksscore/szjdjl/{id}")
	ResponseMessage<String> szjdjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SzjdjlModel data);

	@DeleteMapping("/ksscore/szjdjl/{id}")
	ResponseMessage<Integer> szjdjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/szjdjl/{id}")
	ResponseMessage<Kss_SzjdjlModel> szjdjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/taf")
	ResponseMessage<PagerResult<Kss_TafModel>> taf_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/taf")
	ResponseMessage<String> taf_save(@RequestBody Kss_TafModel data);

	@PatchMapping("/kssbase/taf/{id}")
	ResponseMessage<String> taf_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TafModel data);

	@DeleteMapping("/kssbase/taf/{id}")
	ResponseMessage<Integer> taf_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/taf/{id}")
	ResponseMessage<Kss_TafModel> taf_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/tfsjdj")
	ResponseMessage<PagerResult<Kss_TfsjdjModel>> tfsjdj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/tfsjdj")
	ResponseMessage<String> tfsjdj_save(@RequestBody Kss_TfsjdjModel data);

	@PatchMapping("/ksscore/tfsjdj/{id}")
	ResponseMessage<String> tfsjdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TfsjdjModel data);

	@DeleteMapping("/ksscore/tfsjdj/{id}")
	ResponseMessage<Integer> tfsjdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/tfsjdj/{id}")
	ResponseMessage<Kss_TfsjdjModel> tfsjdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/thjy")
	ResponseMessage<PagerResult<Kss_ThjyModel>> thjy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/thjy")
	ResponseMessage<String> thjy_save(@RequestBody Kss_ThjyModel data);

	@PatchMapping("/ksscore/thjy/{id}")
	ResponseMessage<String> thjy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ThjyModel data);

	@DeleteMapping("/ksscore/thjy/{id}")
	ResponseMessage<Integer> thjy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/thjy/{id}")
	ResponseMessage<Kss_ThjyModel> thjy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/thjyLsjy")
	ResponseMessage<PagerResult<Kss_ThjyLsjyModel>> thjyLsjy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/thjyLsjy")
	ResponseMessage<String> thjyLsjy_save(@RequestBody Kss_ThjyLsjyModel data);

	@PatchMapping("/ksscore/thjyLsjy/{id}")
	ResponseMessage<String> thjyLsjy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ThjyLsjyModel data);

	@DeleteMapping("/ksscore/thjyLsjy/{id}")
	ResponseMessage<Integer> thjyLsjy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/thjyLsjy/{id}")
	ResponseMessage<Kss_ThjyLsjyModel> thjyLsjy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/thjyZssb")
	ResponseMessage<PagerResult<Kss_ThjyZssbModel>> thjyZssb_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/thjyZssb")
	ResponseMessage<String> thjyZssb_save(@RequestBody Kss_ThjyZssbModel data);

	@PatchMapping("/ksscore/thjyZssb/{id}")
	ResponseMessage<String> thjyZssb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ThjyZssbModel data);

	@DeleteMapping("/ksscore/thjyZssb/{id}")
	ResponseMessage<Integer> thjyZssb_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/thjyZssb/{id}")
	ResponseMessage<Kss_ThjyZssbModel> thjyZssb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/thys")
	ResponseMessage<PagerResult<Kss_ThysModel>> thys_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/thys")
	ResponseMessage<String> thys_save(@RequestBody Kss_ThysModel data);

	@PatchMapping("/ksscore/thys/{id}")
	ResponseMessage<String> thys_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ThysModel data);

	@DeleteMapping("/ksscore/thys/{id}")
	ResponseMessage<Integer> thys_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/thys/{id}")
	ResponseMessage<Kss_ThysModel> thys_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/tjdj")
	ResponseMessage<PagerResult<Kss_TjdjModel>> tjdj_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/tjdj")
	ResponseMessage<String> tjdj_save(@RequestBody Kss_TjdjModel data);

	@PatchMapping("/kssbase/tjdj/{id}")
	ResponseMessage<String> tjdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TjdjModel data);

	@DeleteMapping("/kssbase/tjdj/{id}")
	ResponseMessage<Integer> tjdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/tjdj/{id}")
	ResponseMessage<Kss_TjdjModel> tjdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/tlhmcjl")
	ResponseMessage<PagerResult<Kss_TlhmcjlModel>> tlhmcjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/tlhmcjl")
	ResponseMessage<String> tlhmcjl_save(@RequestBody Kss_TlhmcjlModel data);

	@PatchMapping("/ksscore/tlhmcjl/{id}")
	ResponseMessage<String> tlhmcjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TlhmcjlModel data);

	@DeleteMapping("/ksscore/tlhmcjl/{id}")
	ResponseMessage<Integer> tlhmcjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/tlhmcjl/{id}")
	ResponseMessage<Kss_TlhmcjlModel> tlhmcjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/tmsq")
	ResponseMessage<PagerResult<Kss_TmsqModel>> tmsq_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/tmsq")
	ResponseMessage<String> tmsq_save(@RequestBody Kss_TmsqModel data);

	@PatchMapping("/kssbase/tmsq/{id}")
	ResponseMessage<String> tmsq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TmsqModel data);

	@DeleteMapping("/kssbase/tmsq/{id}")
	ResponseMessage<Integer> tmsq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/tmsq/{id}")
	ResponseMessage<Kss_TmsqModel> tmsq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/tmtz")
	ResponseMessage<PagerResult<Kss_TmtzModel>> tmtz_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/tmtz")
	ResponseMessage<String> tmtz_save(@RequestBody Kss_TmtzModel data);

	@PatchMapping("/kssbase/tmtz/{id}")
	ResponseMessage<String> tmtz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TmtzModel data);

	@DeleteMapping("/kssbase/tmtz/{id}")
	ResponseMessage<Integer> tmtz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/tmtz/{id}")
	ResponseMessage<Kss_TmtzModel> tmtz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/tscl")
	ResponseMessage<PagerResult<Kss_TsclModel>> tscl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/tscl")
	ResponseMessage<String> tscl_save(@RequestBody Kss_TsclModel data);

	@PatchMapping("/ksscore/tscl/{id}")
	ResponseMessage<String> tscl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TsclModel data);

	@DeleteMapping("/ksscore/tscl/{id}")
	ResponseMessage<Integer> tscl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/tscl/{id}")
	ResponseMessage<Kss_TsclModel> tscl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/tsdj")
	ResponseMessage<PagerResult<Kss_TsdjModel>> tsdj_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/tsdj")
	ResponseMessage<String> tsdj_save(@RequestBody Kss_TsdjModel data);

	@PatchMapping("/kssbase/tsdj/{id}")
	ResponseMessage<String> tsdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TsdjModel data);

	@DeleteMapping("/kssbase/tsdj/{id}")
	ResponseMessage<Integer> tsdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/tsdj/{id}")
	ResponseMessage<Kss_TsdjModel> tsdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/tsyy")
	ResponseMessage<PagerResult<Kss_TsyyModel>> tsyy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/tsyy")
	ResponseMessage<String> tsyy_save(@RequestBody Kss_TsyyModel data);

	@PatchMapping("/ksscore/tsyy/{id}")
	ResponseMessage<String> tsyy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TsyyModel data);

	@DeleteMapping("/ksscore/tsyy/{id}")
	ResponseMessage<Integer> tsyy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/tsyy/{id}")
	ResponseMessage<Kss_TsyyModel> tsyy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/swrypg")
	ResponseMessage<PagerResult<Kss_SwrypgModel>> swrypg_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/swrypg")
	ResponseMessage<String> swrypg_save(@RequestBody Kss_SwrypgModel data);

	@PatchMapping("/kssbase/swrypg/{id}")
	ResponseMessage<String> swrypg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_SwrypgModel data);

	@DeleteMapping("/kssbase/swrypg/{id}")
	ResponseMessage<Integer> swrypg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/swrypg/{id}")
	ResponseMessage<Kss_SwrypgModel> swrypg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/tszgl")
	ResponseMessage<PagerResult<Kss_TszglModel>> tszgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/tszgl")
	ResponseMessage<String> tszgl_save(@RequestBody Kss_TszglModel data);

	@PatchMapping("/ksscore/tszgl/{id}")
	ResponseMessage<String> tszgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_TszglModel data);

	@DeleteMapping("/ksscore/tszgl/{id}")
	ResponseMessage<Integer> tszgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/tszgl/{id}")
	ResponseMessage<Kss_TszglModel> tszgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wgsjcl")
	ResponseMessage<PagerResult<Kss_WgsjclModel>> wgsjcl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wgsjcl")
	ResponseMessage<String> wgsjcl_save(@RequestBody Kss_WgsjclModel data);

	@PatchMapping("/ksscore/wgsjcl/{id}")
	ResponseMessage<String> wgsjcl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WgsjclModel data);

	@DeleteMapping("/ksscore/wgsjcl/{id}")
	ResponseMessage<Integer> wgsjcl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wgsjcl/{id}")
	ResponseMessage<Kss_WgsjclModel> wgsjcl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wlry")
	ResponseMessage<PagerResult<Kss_WlryModel>> wlry_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wlry")
	ResponseMessage<String> wlry_save(@RequestBody Kss_WlryModel data);

	@PatchMapping("/ksscore/wlry/{id}")
	ResponseMessage<String> wlry_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WlryModel data);

	@DeleteMapping("/ksscore/wlry/{id}")
	ResponseMessage<Integer> wlry_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wlry/{id}")
	ResponseMessage<Kss_WlryModel> wlry_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wmjs")
	ResponseMessage<PagerResult<Kss_WmjsModel>> wmjs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wmjs")
	ResponseMessage<String> wmjs_save(@RequestBody Kss_WmjsModel data);

	@PatchMapping("/ksscore/wmjs/{id}")
	ResponseMessage<String> wmjs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WmjsModel data);

	@DeleteMapping("/ksscore/wmjs/{id}")
	ResponseMessage<Integer> wmjs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wmjs/{id}")
	ResponseMessage<Kss_WmjsModel> wmjs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wmxc")
	ResponseMessage<PagerResult<Kss_WmxcModel>> wmxc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wmxc")
	ResponseMessage<String> wmxc_save(@RequestBody Kss_WmxcModel data);

	@PatchMapping("/ksscore/wmxc/{id}")
	ResponseMessage<String> wmxc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WmxcModel data);

	@DeleteMapping("/ksscore/wmxc/{id}")
	ResponseMessage<Integer> wmxc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wmxc/{id}")
	ResponseMessage<Kss_WmxcModel> wmxc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wpgl")
	ResponseMessage<PagerResult<Kss_WpglModel>> wpgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wpgl")
	ResponseMessage<String> wpgl_save(@RequestBody Kss_WpglModel data);

	@PatchMapping("/ksscore/wpgl/{id}")
	ResponseMessage<String> wpgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WpglModel data);

	@DeleteMapping("/ksscore/wpgl/{id}")
	ResponseMessage<Integer> wpgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wpgl/{id}")
	ResponseMessage<Kss_WpglModel> wpgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wpjs")
	ResponseMessage<PagerResult<Kss_WpjsModel>> wpjs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wpjs")
	ResponseMessage<String> wpjs_save(@RequestBody Kss_WpjsModel data);

	@PatchMapping("/ksscore/wpjs/{id}")
	ResponseMessage<String> wpjs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WpjsModel data);

	@DeleteMapping("/ksscore/wpjs/{id}")
	ResponseMessage<Integer> wpjs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/manager/wpjs/{id}")
	ResponseMessage<Kss_WpjsModel> wpjs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wplq")
	ResponseMessage<PagerResult<Kss_WplqModel>> wplq_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wplq")
	ResponseMessage<String> wplq_save(@RequestBody Kss_WplqModel data);

	@PatchMapping("/ksscore/wplq/{id}")
	ResponseMessage<String> wplq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WplqModel data);

	@DeleteMapping("/ksscore/wplq/{id}")
	ResponseMessage<Integer> wplq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wplq/{id}")
	ResponseMessage<Kss_WplqModel> wplq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wqsy")
	ResponseMessage<PagerResult<Kss_WqsyModel>> wqsy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wqsy")
	ResponseMessage<String> wqsy_save(@RequestBody Kss_WqsyModel data);

	@PatchMapping("/ksscore/wqsy/{id}")
	ResponseMessage<String> wqsy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WqsyModel data);

	@DeleteMapping("/ksscore/wqsy/{id}")
	ResponseMessage<Integer> wqsy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wqsy/{id}")
	ResponseMessage<Kss_WqsyModel> wqsy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/wsfy")
	ResponseMessage<PagerResult<Kss_WsfyModel>> wsfy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/wsfy")
	ResponseMessage<String> wsfy_save(@RequestBody Kss_WsfyModel data);

	@PatchMapping("/ksscore/wsfy/{id}")
	ResponseMessage<String> wsfy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_WsfyModel data);

	@DeleteMapping("/ksscore/wsfy/{id}")
	ResponseMessage<Integer> wsfy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/wsfy/{id}")
	ResponseMessage<Kss_WsfyModel> wsfy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xfmx")
	ResponseMessage<PagerResult<Kss_XfmxModel>> xfmx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xfmx")
	ResponseMessage<String> xfmx_save(@RequestBody Kss_XfmxModel data);

	@PatchMapping("/ksscore/xfmx/{id}")
	ResponseMessage<String> xfmx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XfmxModel data);

	@DeleteMapping("/ksscore/xfmx/{id}")
	ResponseMessage<Integer> xfmx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xfmx/{id}")
	ResponseMessage<Kss_XfmxModel> xfmx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xggzyq")
	ResponseMessage<PagerResult<Kss_XggzyqModel>> xggzyq_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xggzyq")
	ResponseMessage<String> xggzyq_save(@RequestBody Kss_XggzyqModel data);

	@PatchMapping("/ksscore/xggzyq/{id}")
	ResponseMessage<String> xggzyq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XggzyqModel data);

	@DeleteMapping("/ksscore/xggzyq/{id}")
	ResponseMessage<Integer> xggzyq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xggzyq/{id}")
	ResponseMessage<Kss_XggzyqModel> xggzyq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xg")
	ResponseMessage<PagerResult<Kss_XgModel>> xg_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xg")
	ResponseMessage<String> xg_save(@RequestBody Kss_XgModel data);

	@PatchMapping("/ksscore/xg/{id}")
	ResponseMessage<String> xg_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XgModel data);

	@DeleteMapping("/ksscore/xg/{id}")
	ResponseMessage<Integer> xg_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xg/{id}")
	ResponseMessage<Kss_XgModel> xg_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xjgl")
	ResponseMessage<PagerResult<Kss_XjglModel>> xjgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xjgl")
	ResponseMessage<String> xjgl_save(@RequestBody Kss_XjglModel data);

	@PatchMapping("/ksscore/xjgl/{id}")
	ResponseMessage<String> xjgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjglModel data);

	@DeleteMapping("/ksscore/xjgl/{id}")
	ResponseMessage<Integer> xjgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xjgl/{id}")
	ResponseMessage<Kss_XjglModel> xjgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xjhz")
	ResponseMessage<PagerResult<Kss_XjhzModel>> xjhz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xjhz")
	ResponseMessage<String> xjhz_save(@RequestBody Kss_XjhzModel data);

	@PatchMapping("/ksscore/xjhz/{id}")
	ResponseMessage<String> xjhz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjhzModel data);

	@DeleteMapping("/ksscore/xjhz/{id}")
	ResponseMessage<Integer> xjhz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xjhz/{id}")
	ResponseMessage<Kss_XjhzModel> xjhz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xjjl")
	ResponseMessage<PagerResult<Kss_XjjlModel>> xjjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xjjl")
	ResponseMessage<String> xjjl_save(@RequestBody Kss_XjjlModel data);

	@PatchMapping("/ksscore/xjjl/{id}")
	ResponseMessage<String> xjjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjjlModel data);

	@DeleteMapping("/ksscore/xjjl/{id}")
	ResponseMessage<Integer> xjjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xjjl/{id}")
	ResponseMessage<Kss_XjjlModel> xjjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xjjs")
	ResponseMessage<PagerResult<Kss_XjjsModel>> xjjs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xjjs")
	ResponseMessage<String> xjjs_save(@RequestBody Kss_XjjsModel data);

	@PatchMapping("/ksscore/xjjs/{id}")
	ResponseMessage<String> xjjs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjjsModel data);

	@DeleteMapping("/ksscore/xjjs/{id}")
	ResponseMessage<Integer> xjjs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xjjs/{id}")
	ResponseMessage<Kss_XjjsModel> xjjs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xj")
	ResponseMessage<PagerResult<Kss_XjModel>> xj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xj")
	ResponseMessage<String> xj_save(@RequestBody Kss_XjModel data);

	@PatchMapping("/ksscore/xj/{id}")
	ResponseMessage<String> xj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjModel data);

	@DeleteMapping("/ksscore/xj/{id}")
	ResponseMessage<Integer> xj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xj/{id}")
	ResponseMessage<Kss_XjModel> xj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xjzc")
	ResponseMessage<PagerResult<Kss_XjzcModel>> xjzc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xjzc")
	ResponseMessage<String> xjzc_save(@RequestBody Kss_XjzcModel data);

	@PatchMapping("/ksscore/xjzc/{id}")
	ResponseMessage<String> xjzc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XjzcModel data);

	@DeleteMapping("/ksscore/xjzc/{id}")
	ResponseMessage<Integer> xjzc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xjzc/{id}")
	ResponseMessage<Kss_XjzcModel> xjzc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xlgy")
	ResponseMessage<PagerResult<Kss_XlgyModel>> xlgy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xlgy")
	ResponseMessage<String> xlgy_save(@RequestBody Kss_XlgyModel data);

	@PatchMapping("/ksscore/xlgy/{id}")
	ResponseMessage<String> xlgy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XlgyModel data);

	@DeleteMapping("/ksscore/xlgy/{id}")
	ResponseMessage<Integer> xlgy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/manager/xlgy/{id}")
	ResponseMessage<Kss_XlgyModel> xlgy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xljk")
	ResponseMessage<PagerResult<Kss_XljkModel>> xljk_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xljk")
	ResponseMessage<String> xljk_save(@RequestBody Kss_XljkModel data);

	@PatchMapping("/ksscore/xljk/{id}")
	ResponseMessage<String> xljk_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XljkModel data);

	@DeleteMapping("/ksscore/xljk/{id}")
	ResponseMessage<Integer> xljk_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xljk/{id}")
	ResponseMessage<Kss_XljkModel> xljk_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xsjl")
	ResponseMessage<PagerResult<Kss_XsjlModel>> xsjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xsjl")
	ResponseMessage<String> xsjl_save(@RequestBody Kss_XsjlModel data);

	@PatchMapping("/ksscore/xsjl/{id}")
	ResponseMessage<String> xsjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XsjlModel data);

	@DeleteMapping("/ksscore/xsjl/{id}")
	ResponseMessage<Integer> xsjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xsjl/{id}")
	ResponseMessage<Kss_XsjlModel> xsjl_getByKey(@PathVariable(value = "id") String id);	
	
	///////////////////////////////////////////////////////////////////////
	// ///////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/xtzxjd")
	ResponseMessage<PagerResult<Kss_XtzxjdModel>> xtzxjd_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/xtzxjd")
	ResponseMessage<String> xtzxjd_save(@RequestBody Kss_XtzxjdModel data);

	@PatchMapping("/kssbase/xtzxjd/{id}")
	ResponseMessage<String> xtzxjd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XtzxjdModel data);

	@DeleteMapping("/kssbase/xtzxjd/{id}")
	ResponseMessage<Integer> xtzxjd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/xtzxjd/{id}")
	ResponseMessage<Kss_XtzxjdModel> xtzxjd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/xyr")
	ResponseMessage<PagerResult<Kss_XyrModel>> xyr_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/xyr")
	ResponseMessage<String> xyr_save(@RequestBody Kss_XyrModel data);

	@PatchMapping("/kssbase/xyr/{id}")
	ResponseMessage<String> xyr_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XyrModel data);

	@DeleteMapping("/kssbase/xyr/{id}")
	ResponseMessage<Integer> xyr_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/xyr/{id}")
	ResponseMessage<Kss_XyrModel> xyr_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/xzpa")
	ResponseMessage<PagerResult<Kss_XzpaModel>> xzpa_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/xzpa")
	ResponseMessage<String> xzpa_save(@RequestBody Kss_XzpaModel data);

	@PatchMapping("/ksscore/xzpa/{id}")
	ResponseMessage<String> xzpa_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_XzpaModel data);

	@DeleteMapping("/ksscore/xzpa/{id}")
	ResponseMessage<Integer> xzpa_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/xzpa/{id}")
	ResponseMessage<Kss_XzpaModel> xzpa_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yabz")
	ResponseMessage<PagerResult<Kss_YabzModel>> yabz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yabz")
	ResponseMessage<String> yabz_save(@RequestBody Kss_YabzModel data);

	@PatchMapping("/ksscore/yabz/{id}")
	ResponseMessage<String> yabz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YabzModel data);

	@DeleteMapping("/ksscore/yabz/{id}")
	ResponseMessage<Integer> yabz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yabz/{id}")
	ResponseMessage<Kss_YabzModel> yabz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ygry")
	ResponseMessage<PagerResult<Kss_YgryModel>> ygry_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ygry")
	ResponseMessage<String> ygry_save(@RequestBody Kss_YgryModel data);

	@PatchMapping("/ksscore/ygry/{id}")
	ResponseMessage<String> ygry_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YgryModel data);

	@DeleteMapping("/ksscore/ygry/{id}")
	ResponseMessage<Integer> ygry_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ygry/{id}")
	ResponseMessage<Kss_YgryModel> ygry_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yjyayl")
	ResponseMessage<PagerResult<Kss_YjyaylModel>> yjyayl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yjyayl")
	ResponseMessage<String> yjyayl_save(@RequestBody Kss_YjyaylModel data);

	@PatchMapping("/ksscore/yjyayl/{id}")
	ResponseMessage<String> yjyayl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YjyaylModel data);

	@DeleteMapping("/ksscore/yjyayl/{id}")
	ResponseMessage<Integer> yjyayl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yjyayl/{id}")
	ResponseMessage<Kss_YjyaylModel> yjyayl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ypckjl")
	ResponseMessage<PagerResult<Kss_YpckjlModel>> ypckjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ypckjl")
	ResponseMessage<String> ypckjl_save(@RequestBody Kss_YpckjlModel data);

	@PatchMapping("/ksscore/ypckjl/{id}")
	ResponseMessage<String> ypckjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YpckjlModel data);

	@DeleteMapping("/ksscore/ypckjl/{id}")
	ResponseMessage<Integer> ypckjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ypckjl/{id}")
	ResponseMessage<Kss_YpckjlModel> ypckjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ypcl")
	ResponseMessage<PagerResult<Kss_YpclModel>> ypcl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ypcl")
	ResponseMessage<String> ypcl_save(@RequestBody Kss_YpclModel data);

	@PatchMapping("/ksscore/ypcl/{id}")
	ResponseMessage<String> ypcl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YpclModel data);

	@DeleteMapping("/ksscore/ypcl/{id}")
	ResponseMessage<Integer> ypcl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ypcl/{id}")
	ResponseMessage<Kss_YpclModel> ypcl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ypjhjl")
	ResponseMessage<PagerResult<Kss_YpjhjlModel>> ypjhjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ypjhjl")
	ResponseMessage<String> ypjhjl_save(@RequestBody Kss_YpjhjlModel data);

	@PatchMapping("/ksscore/ypjhjl/{id}")
	ResponseMessage<String> ypjhjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YpjhjlModel data);

	@DeleteMapping("/ksscore/ypjhjl/{id}")
	ResponseMessage<Integer> ypjhjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ypjhjl/{id}")
	ResponseMessage<Kss_YpjhjlModel> ypjhjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ypkc")
	ResponseMessage<PagerResult<Kss_YpkcModel>> ypkc_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ypkc")
	ResponseMessage<String> ypkc_save(@RequestBody Kss_YpkcModel data);

	@PatchMapping("/ksscore/ypkc/{id}")
	ResponseMessage<String> ypkc_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YpkcModel data);

	@DeleteMapping("/ksscore/ypkc/{id}")
	ResponseMessage<Integer> ypkc_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ypkc/{id}")
	ResponseMessage<Kss_YpkcModel> ypkc_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ypxx")
	ResponseMessage<PagerResult<Kss_YpxxModel>> ypxx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ypxx")
	ResponseMessage<String> ypxx_save(@RequestBody Kss_YpxxModel data);

	@PatchMapping("/ksscore/ypxx/{id}")
	ResponseMessage<String> ypxx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YpxxModel data);

	@DeleteMapping("/ksscore/ypxx/{id}")
	ResponseMessage<Integer> ypxx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ypxx/{id}")
	ResponseMessage<Kss_YpxxModel> ypxx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yqfx")
	ResponseMessage<PagerResult<Kss_YqfxModel>> yqfx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yqfx")
	ResponseMessage<String> yqfx_save(@RequestBody Kss_YqfxModel data);

	@PatchMapping("/ksscore/yqfx/{id}")
	ResponseMessage<String> yqfx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YqfxModel data);

	@DeleteMapping("/ksscore/yqfx/{id}")
	ResponseMessage<Integer> yqfx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yqfx/{id}")
	ResponseMessage<Kss_YqfxModel> yqfx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/kssbase/yq")
	ResponseMessage<PagerResult<Kss_YqModel>> yq_query(QueryParam queryParam);
	
	@PostMapping("/kssbase/yq")
	ResponseMessage<String> yq_save(@RequestBody Kss_YqModel data);

	@PatchMapping("/kssbase/yq/{id}")
	ResponseMessage<String> yq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YqModel data);

	@DeleteMapping("/kssbase/yq/{id}")
	ResponseMessage<Integer> yq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/kssbase/yq/{id}")
	ResponseMessage<Kss_YqModel> yq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/ysxz")
	ResponseMessage<PagerResult<Kss_YsxzModel>> ysxz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/ysxz")
	ResponseMessage<String> ysxz_save(@RequestBody Kss_YsxzModel data);

	@PatchMapping("/ksscore/ysxz/{id}")
	ResponseMessage<String> ysxz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YsxzModel data);

	@DeleteMapping("/ksscore/ysxz/{id}")
	ResponseMessage<Integer> ysxz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/ysxz/{id}")
	ResponseMessage<Kss_YsxzModel> ysxz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yy")
	ResponseMessage<PagerResult<Kss_YyModel>> yy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yy")
	ResponseMessage<String> yy_save(@RequestBody Kss_YyModel data);

	@PatchMapping("/ksscore/yy/{id}")
	ResponseMessage<String> yy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YyModel data);

	@DeleteMapping("/ksscore/yy/{id}")
	ResponseMessage<Integer> yy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yy/{id}")
	ResponseMessage<Kss_YyModel> yy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yz")
	ResponseMessage<PagerResult<Kss_YzModel>> yz_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yz")
	ResponseMessage<String> yz_save(@RequestBody Kss_YzModel data);

	@PatchMapping("/ksscore/yz/{id}")
	ResponseMessage<String> yz_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YzModel data);

	@DeleteMapping("/ksscore/yz/{id}")
	ResponseMessage<Integer> yz_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yz/{id}")
	ResponseMessage<Kss_YzModel> yz_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/yzmx")
	ResponseMessage<PagerResult<Kss_YzmxModel>> yzmx_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/yzmx")
	ResponseMessage<String> yzmx_save(@RequestBody Kss_YzmxModel data);

	@PatchMapping("/ksscore/yzmx/{id}")
	ResponseMessage<String> yzmx_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_YzmxModel data);

	@DeleteMapping("/ksscore/yzmx/{id}")
	ResponseMessage<Integer> yzmx_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/yzmx/{id}")
	ResponseMessage<Kss_YzmxModel> yzmx_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zbdjhistory")
	ResponseMessage<PagerResult<Kss_ZbdjhistoryModel>> zbdjhistory_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zbdjhistory")
	ResponseMessage<String> zbdjhistory_save(@RequestBody Kss_ZbdjhistoryModel data);

	@PatchMapping("/ksscore/zbdjhistory/{id}")
	ResponseMessage<String> zbdjhistory_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZbdjhistoryModel data);

	@DeleteMapping("/ksscore/zbdjhistory/{id}")
	ResponseMessage<Integer> zbdjhistory_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zbdjhistory/{id}")
	ResponseMessage<Kss_ZbdjhistoryModel> zbdjhistory_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zbdj")
	ResponseMessage<PagerResult<Kss_ZbdjModel>> zbdj_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zbdj")
	ResponseMessage<String> zbdj_save(@RequestBody Kss_ZbdjModel data);

	@PatchMapping("/ksscore/zbdj/{id}")
	ResponseMessage<String> zbdj_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZbdjModel data);

	@DeleteMapping("/ksscore/zbdj/{id}")
	ResponseMessage<Integer> zbdj_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zbdj/{id}")
	ResponseMessage<Kss_ZbdjModel> zbdj_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zbhgl")
	ResponseMessage<PagerResult<Kss_ZbhglModel>> zbhgl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zbhgl")
	ResponseMessage<String> zbhgl_save(@RequestBody Kss_ZbhglModel data);

	@PatchMapping("/ksscore/zbhgl/{id}")
	ResponseMessage<String> zbhgl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZbhglModel data);

	@DeleteMapping("/ksscore/zbhgl/{id}")
	ResponseMessage<Integer> zbhgl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zbhgl/{id}")
	ResponseMessage<Kss_ZbhglModel> zbhgl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zbkq")
	ResponseMessage<PagerResult<Kss_ZbkqModel>> zbkq_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zbkq")
	ResponseMessage<String> zbkq_save(@RequestBody Kss_ZbkqModel data);

	@PatchMapping("/ksscore/zbkq/{id}")
	ResponseMessage<String> zbkq_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZbkqModel data);

	@DeleteMapping("/ksscore/zbkq/{id}")
	ResponseMessage<Integer> zbkq_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zbkq/{id}")
	ResponseMessage<Kss_ZbkqModel> zbkq_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zdry")
	ResponseMessage<PagerResult<Kss_ZdryModel>> zdry_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zdry")
	ResponseMessage<String> zdry_save(@RequestBody Kss_ZdryModel data);

	@PatchMapping("/ksscore/zdry/{id}")
	ResponseMessage<String> zdry_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZdryModel data);

	@DeleteMapping("/ksscore/zdry/{id}")
	ResponseMessage<Integer> zdry_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zdry/{id}")
	ResponseMessage<Kss_ZdryModel> zdry_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zdzy")
	ResponseMessage<PagerResult<Kss_ZdzyModel>> zdzy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zdzy")
	ResponseMessage<String> zdzy_save(@RequestBody Kss_ZdzyModel data);

	@PatchMapping("/ksscore/zdzy/{id}")
	ResponseMessage<String> zdzy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZdzyModel data);

	@DeleteMapping("/ksscore/zdzy/{id}")
	ResponseMessage<Integer> zdzy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zdzy/{id}")
	ResponseMessage<Kss_ZdzyModel> zdzy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zrap")
	ResponseMessage<PagerResult<Kss_ZrapModel>> zrap_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zrap")
	ResponseMessage<String> zrap_save(@RequestBody Kss_ZrapModel data);

	@PatchMapping("/ksscore/zrap/{id}")
	ResponseMessage<String> zrap_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZrapModel data);

	@DeleteMapping("/ksscore/zrap/{id}")
	ResponseMessage<Integer> zrap_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zrap/{id}")
	ResponseMessage<Kss_ZrapModel> zrap_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyaf")
	ResponseMessage<PagerResult<Kss_ZyafModel>> zyaf_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyaf")
	ResponseMessage<String> zyaf_save(@RequestBody Kss_ZyafModel data);

	@PatchMapping("/ksscore/zyaf/{id}")
	ResponseMessage<String> zyaf_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyafModel data);

	@DeleteMapping("/ksscore/zyaf/{id}")
	ResponseMessage<Integer> zyaf_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyaf/{id}")
	ResponseMessage<Kss_ZyafModel> zyaf_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zydtfxhy")
	ResponseMessage<PagerResult<Kss_ZydtfxhyModel>> zydtfxhy_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zydtfxhy")
	ResponseMessage<String> zydtfxhy_save(@RequestBody Kss_ZydtfxhyModel data);

	@PatchMapping("/ksscore/zydtfxhy/{id}")
	ResponseMessage<String> zydtfxhy_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZydtfxhyModel data);

	@DeleteMapping("/ksscore/zydtfxhy/{id}")
	ResponseMessage<Integer> zydtfxhy_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zydtfxhy/{id}")
	ResponseMessage<Kss_ZydtfxhyModel> zydtfxhy_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyrybgcl")
	ResponseMessage<PagerResult<Kss_ZyrybgclModel>> zyrybgcl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyrybgcl")
	ResponseMessage<String> zyrybgcl_save(@RequestBody Kss_ZyrybgclModel data);

	@PatchMapping("/ksscore/zyrybgcl/{id}")
	ResponseMessage<String> zyrybgcl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyrybgclModel data);

	@DeleteMapping("/ksscore/zyrybgcl/{id}")
	ResponseMessage<Integer> zyrybgcl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyrybgcl/{id}")
	ResponseMessage<Kss_ZyrybgclModel> zyrybgcl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyrybxjd")
	ResponseMessage<PagerResult<Kss_ZyrybxjdModel>> zyrybxjd_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyrybxjd")
	ResponseMessage<String> zyrybxjd_save(@RequestBody Kss_ZyrybxjdModel data);

	@PatchMapping("/ksscore/zyrybxjd/{id}")
	ResponseMessage<String> zyrybxjd_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyrybxjdModel data);

	@DeleteMapping("/ksscore/zyrybxjd/{id}")
	ResponseMessage<Integer> zyrybxjd_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyrybxjd/{id}")
	ResponseMessage<Kss_ZyrybxjdModel> zyrybxjd_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyrycfjlFjcs")
	ResponseMessage<PagerResult<Kss_ZyrycfjlFjcsModel>> zyrycfjlFjcs_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyrycfjlFjcs")
	ResponseMessage<String> zyrycfjlFjcs_save(@RequestBody Kss_ZyrycfjlFjcsModel data);

	@PatchMapping("/ksscore/zyrycfjlFjcs/{id}")
	ResponseMessage<String> zyrycfjlFjcs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyrycfjlFjcsModel data);

	@DeleteMapping("/ksscore/zyrycfjlFjcs/{id}")
	ResponseMessage<Integer> zyrycfjlFjcs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyrycfjlFjcs/{id}")
	ResponseMessage<Kss_ZyrycfjlFjcsModel> zyrycfjlFjcs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyrycfjl")
	ResponseMessage<PagerResult<Kss_ZyrycfjlModel>> zyrycfjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyrycfjl")
	ResponseMessage<String> zyrycfjl_save(@RequestBody Kss_ZyrycfjlModel data);

	@PatchMapping("/ksscore/zyrycfjl/{id}")
	ResponseMessage<String> zyrycfjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyrycfjlModel data);

	@DeleteMapping("/ksscore/zyrycfjl/{id}")
	ResponseMessage<Integer> zyrycfjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyrycfjl/{id}")
	ResponseMessage<Kss_ZyrycfjlModel> zyrycfjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyryfyjl")
	ResponseMessage<PagerResult<Kss_ZyryfyjlModel>> zyryfyjl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyryfyjl")
	ResponseMessage<String> zyryfyjl_save(@RequestBody Kss_ZyryfyjlModel data);

	@PatchMapping("/ksscore/zyryfyjl/{id}")
	ResponseMessage<String> zyryfyjl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyryfyjlModel data);

	@DeleteMapping("/ksscore/zyryfyjl/{id}")
	ResponseMessage<Integer> zyryfyjl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyryfyjl/{id}")
	ResponseMessage<Kss_ZyryfyjlModel> zyryfyjl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyryjljl")
	ResponseMessage<PagerResult<Kss_ZyryjljlModel>> zyryjljl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyryjljl")
	ResponseMessage<String> zyryjljl_save(@RequestBody Kss_ZyryjljlModel data);

	@PatchMapping("/ksscore/zyryjljl/{id}")
	ResponseMessage<String> zyryjljl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyryjljlModel data);

	@DeleteMapping("/ksscore/zyryjljl/{id}")
	ResponseMessage<Integer> zyryjljl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyryjljl/{id}")
	ResponseMessage<Kss_ZyryjljlModel> zyryjljl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/ksscore/zyrytscl")
	ResponseMessage<PagerResult<Kss_ZyrytsclModel>> zyrytscl_query(QueryParam queryParam);
	
	@PostMapping("/ksscore/zyrytscl")
	ResponseMessage<String> zyrytscl_save(@RequestBody Kss_ZyrytsclModel data);

	@PatchMapping("/ksscore/zyrytscl/{id}")
	ResponseMessage<String> zyrytscl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Kss_ZyrytsclModel data);

	@DeleteMapping("/ksscore/zyrytscl/{id}")
	ResponseMessage<Integer> zyrytscl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/ksscore/zyrytscl/{id}")
	ResponseMessage<Kss_ZyrytsclModel> zyrytscl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	

	

}
