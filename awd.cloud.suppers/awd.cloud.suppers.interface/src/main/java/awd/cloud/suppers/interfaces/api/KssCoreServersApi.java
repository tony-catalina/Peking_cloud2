package awd.cloud.suppers.interfaces.api;

import java.util.Map;

import awd.cloud.suppers.interfaces.api.hystrix.KssCoreServersApiFallBackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import awd.cloud.suppers.interfaces.api.hystrix.JlsServersApiFallBackFactory;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

/**
 * @Description
 * @Date 2019-11-15
 **/

@FeignClient(name = "${awd.api.kss.core:AWD-KSS-CORE-SERVER}", fallbackFactory = KssCoreServersApiFallBackFactory.class)

@Component
public interface KssCoreServersApi {

    @GetMapping(path = {"/kss/test"})
    ResponseMessage<String> test();

    //值日安排
    @GetMapping("/zrap")
    ResponseMessage<PagerResult<Map<String, Object>>> queryZrap(@RequestBody QueryParam param);

    //铺位管理
    @GetMapping("/kss_pwgl")
    ResponseMessage<PagerResult<Map<String, Object>>> queryPwgl(@RequestBody QueryParam param);

    //谈话记录
    @GetMapping("/thjy")
    ResponseMessage<PagerResult<Map<String, Object>>> getThjl(@RequestBody QueryParam param);

    @PostMapping("/thjy")
    ResponseMessage<String> saveThjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/thjy/{id}"})
    ResponseMessage<String> updateThjlById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //就医记录
    @GetMapping("/jy")
    ResponseMessage<PagerResult<Map<String, Object>>> getJyjl(@RequestBody QueryParam param);

    @PostMapping("/jy")
    ResponseMessage<String> saveJyjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jy/{id}"})
    ResponseMessage<String> updateJyjlById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //订单明细表
    @GetMapping("/spdetailorder")
    ResponseMessage<PagerResult<Map<String, Object>>> getSpdetailorder(@RequestBody QueryParam param);

    @PostMapping("/spdetailorder")
    ResponseMessage<String> saveSpdetailorder(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/spdetailorder/{id}"})
    ResponseMessage<String> updateSpdetailorderById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //购物订单
    @GetMapping("/sporder")
    ResponseMessage<PagerResult<Map<String, Object>>> getSporder(@RequestBody QueryParam param);

    @PostMapping("/sporder")
    ResponseMessage<String> saveSporder(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/sporder/{id}"})
    ResponseMessage<String> updateSporderById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //商品信息
    @GetMapping("/spxx")
    ResponseMessage<PagerResult<Map<String, Object>>> getSpxx(@RequestBody QueryParam param);

    @PostMapping("/spxx")
    ResponseMessage<String> saveSpxx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/spxx/{id}"})
    ResponseMessage<String> updateSpxxById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //预约
    @GetMapping("/yy")
    ResponseMessage<PagerResult<Map<String, Object>>> getYy(@RequestBody QueryParam param);

    @PostMapping("/yy")
    ResponseMessage<String> saveYy(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/yy/{id}"})
    ResponseMessage<String> updateYyById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //违规事件处理
    @GetMapping("/wggl")
    ResponseMessage<PagerResult<Map<String, Object>>> getWgjl(@RequestBody QueryParam param);

    @PostMapping("/wggl")
    ResponseMessage<String> saveWgjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/wggl/{id}"})
    ResponseMessage<String> updateWgjlById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

}
