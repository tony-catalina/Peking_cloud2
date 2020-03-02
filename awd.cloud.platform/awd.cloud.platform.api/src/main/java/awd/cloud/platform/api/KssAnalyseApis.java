package awd.cloud.platform.api;

import awd.cloud.platform.api.hystrix.KssAnalyseFallBackFactory;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "${bj.server.analyse:AWD-MIS-ANALYSE-SERVER}",fallbackFactory = KssAnalyseFallBackFactory.class)
@Component
public interface KssAnalyseApis {

    /**
     * 查询lsp
     */
    @GetMapping("/kss/lsp/queryLsp")
    Map<String, Object> queryLsp(String jsbh);


    /**
     * 收押入所业务动态
     */
    @GetMapping("/kss/jbxx/syrsYwdt")
    ResponseMessage<Map<String, Object>> syrsYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "startTime") String startTime,
                                                  @RequestParam(value = "endTime") String endTime);


    /**
     * 出所管理业务动态
     */
    @GetMapping("/kss/Clcs/clcsYwdt")
    ResponseMessage<Map<String, Object>> clcsYwdt(@RequestParam(value = "jsbh") String jsbh, @RequestParam(value = "startTime") String startTime,@RequestParam(value = "endTime")  String endTime);


    /**
     * 临时出所业务动态
     */
    @GetMapping("/kss/lscs/lscsYwdt")
    ResponseMessage<Map<String, Object>> lscsYwdt(String jsbh);

    /**
     * 监外执行业务动态
     */
    @GetMapping("/kss/Jwzxjl/jwzxjlYwdt")
    ResponseMessage<Map<String, Object>> jwzxjlYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                    @RequestParam(value = "startTime") String startTime,
                                                    @RequestParam(value = "endTime") String endTime);



    /**
     * 提解业务动态
     */
    @GetMapping("/kss/tj/tjYwdt")
    ResponseMessage<Map<String, Object>> tjYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                @RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime);


    /**
     * 环节变动业务动态
     */
    @GetMapping("/kss/hjbd/hjbdYwdt")
    ResponseMessage<Map<String, Object>> hjbdYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "startTime") String startTime,
                                                  @RequestParam(value = "endTime") String endTime);


    /**
     * 处理结果业务动态
     */
    @GetMapping("/kss/pjdj/pjdjYwdt")
    ResponseMessage<Map<String, Object>> pjdjYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "startTime") String starttime,
                                                  @RequestParam(value = "endTime") String endtime);


    /**
     * 延期业务动态
     */
    @GetMapping("/kss/yq/yqYwdt")
    ResponseMessage<Map<String , Object>> yqYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                 @RequestParam(value = "starttime") String starttime,
                                                 @RequestParam(value = "endtime") String endtime);

    /**
     * 外来人员登记业务动态
     */
    @GetMapping("/kss/wlry/wlryYwdt")
    ResponseMessage<Map<String, Object>> wlryYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "starttime") String starttime,
                                                  @RequestParam(value = "endtime") String endtime);

    /**
     *      * 领导接访业务动态
     */
    @GetMapping("/kss/szjdjl/ldjfYwdt")
    ResponseMessage<Map<String, Object>> ldjfYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "starttime") String starttime,
                                                  @RequestParam(value = "endtime") String endtime);



    /**
     * 奖惩记录业务动态
     */
    @GetMapping("/kss/jlgl/jlglYwdt")
    ResponseMessage<Map<String , Object>> jcjlYwdt(@RequestParam(value = "jsbh") String jsbh ,
                                                   @RequestParam(value = "starttime") String starttime,
                                                   @RequestParam(value = "endtime") String endtime);

    /**
     * 耳目业务动态
     */
    @GetMapping("/kss/emdj/emYwdt")
    ResponseMessage<Map<String , Object>> emYwdt(@RequestParam(value = "jsbh") String jsbh ,
                                                 @RequestParam(value = "jqh") String jqh);

    /**
     * 处罚管理业务动态
     */
    @GetMapping("/kss/cfgl/cfglYwdt")
    ResponseMessage<Map<String , Object>> cfglYwdt(@RequestParam(value = "jsbh") String jsbh ,
                                                   @RequestParam(value = "starttime") String starttime,
                                                   @RequestParam(value = "endtime") String endtime);

    /**
     * 重点人员业务动态
     */
    @GetMapping("/kss/zdry/zdryYwdt")
    ResponseMessage<Map<String, Object>> zdryYwdt(@RequestParam(value = "jsbh") String jsbh,
                                                  @RequestParam(value = "starttime") String starttime,
                                                  @RequestParam(value = "endtime") String endtime);

    /**
     * 协助破案业务动态
     */
    @GetMapping("/kss/xzpa/xzpaYwdt")
    ResponseMessage<Map<String , Object>> xzpaYwdt(@RequestParam(value = "jsbh") String jsbh ,
                                                   @RequestParam(value = "starttime") String starttime,
                                                   @RequestParam(value = "endtime") String endtime);

    /**
     * 械具业务动态
     */
    @GetMapping("/kss/xj/xjYwdt")
    ResponseMessage<Map<String , Object>> xjYwdt(@RequestParam(value = "jsbh") String jsbh ,
                                                 @RequestParam(value = "starttime") String starttime,
                                                 @RequestParam(value = "endtime") String endtime);


}
