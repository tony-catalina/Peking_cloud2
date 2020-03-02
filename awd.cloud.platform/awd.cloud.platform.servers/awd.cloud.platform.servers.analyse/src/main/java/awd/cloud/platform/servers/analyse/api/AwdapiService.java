package awd.cloud.platform.servers.analyse.api;

import awd.cloud.platform.servers.analyse.api.hystrix.AwdapiServiceBackFactory;
import awd.cloud.platform.servers.analyse.model.kss.*;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "${awd.api.manager:AWD-MIS-GATEWAY-SERVER}", fallbackFactory = AwdapiServiceBackFactory.class)
public interface AwdapiService {

    @RequestMapping(value="/kss/jq",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JqModel>> kss_jq_query(@RequestBody  QueryParamEntity param);

    @RequestMapping(value="/kss/jbxx",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JbxxModel>> kss_jbxx_query(QueryParamEntity param);

    @RequestMapping(value="/kss/tsdj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<TsdjModel>> kss_tsdj_query(QueryParamEntity tsParam);

    @RequestMapping(value="/kss/lshj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<TsdjModel>> kss_lshj_query(QueryParamEntity lsParam);

    /**
     * 谈话教育
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/thjy",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getThjyQuery(QueryParamEntity queryParamEntity);
    /**
     * 延期情况
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/yq",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getyqQuery(QueryParamEntity queryParamEntity);
    /**
     * 金币情况
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/jbqk",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getjbqkQuery(QueryParamEntity queryParamEntity);
    /**
     * 发生事故
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/fssg",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getfssgQuery(QueryParamEntity queryParamEntity);
    /**
     * 耳目登记
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/emdj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getemdjQuery(QueryParamEntity queryParamEntity);
    /**
     * 监事调整
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/jstz",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getjstzQuery(QueryParamEntity queryParamEntity);
    /**
     * 基本信息
     * @param queryParamEntity
     * @return
     */
    @RequestMapping(value="/kss/jbxx",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getjbxxQuery(QueryParamEntity queryParamEntity);


    //kss-server
    //家属会见
    @RequestMapping(value="/kss/jshj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<JshjModel>> getJshjQuery(QueryParamEntity queryParamEntity);

    // 律师会见
    @RequestMapping(value="/kss/lshj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<LshjModel>> getLshjQuery(QueryParamEntity queryParamEntity);

    // 判决登记
    @RequestMapping(value="/kss/pjdj",method = RequestMethod.GET)
    ResponseMessage<PagerResult<PjdjModel>> getPjdjQuery(QueryParamEntity queryParamEntity);

    //出所查询
    @RequestMapping(value="/kss/clcs",method = RequestMethod.GET)
    ResponseMessage<PagerResult<ClcsModel>> getClcsQuery(QueryParamEntity queryParamEntity);

    //同案犯查询
    @RequestMapping(value="/kss/taf",method = RequestMethod.GET)
    ResponseMessage<PagerResult<TafModel>> getTafQuery(QueryParamEntity queryParamEntity);

    // 重点人员分析
    @RequestMapping(value="/kss/zdry",method = RequestMethod.GET)
    ResponseMessage<PagerResult<ZdryModel>> getZdryQuery(QueryParamEntity queryParamEntity);


    //kss-server
    //加减刑情况
    @RequestMapping(value="/kss/jjx",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<JjxModel>> getJjxList(QueryParamEntity paramEntity);
    //社会关系
    @RequestMapping(value="/kss/shgx",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<ShgxModel>> getShgxList(QueryParamEntity paramEntity);


    //提审登记
    @RequestMapping(value="/kss/tsdj",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<TsdjModel>> getTsdjList(QueryParamEntity paramEntity);
    //环节变动
    @RequestMapping(value="/kss/hjbd",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<HjbdModel>> getHjbdList(QueryParamEntity paramEntity);
    //械具情况
    @RequestMapping(value="/kss/xjqk",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<XjModel>> getXjList(QueryParamEntity paramEntity);
    //超量关押分析
    @RequestMapping(value="/kss/clgyfx",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<KssModel>> getClgyfxList(QueryParamEntity paramEntity);
    //国籍分析
    @RequestMapping(value="/kss/gjfx",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<JbxxModel>> getGjfxList(QueryParamEntity paramEntity);
    //关押期限分析
    @RequestMapping(value="/kss/gyqxfx",method = RequestMethod.GET)
    public ResponseMessage<PagerResult<JbxxModel>> getGyqxfxList(QueryParamEntity paramEntity);
    //查询社会关系数量





}
