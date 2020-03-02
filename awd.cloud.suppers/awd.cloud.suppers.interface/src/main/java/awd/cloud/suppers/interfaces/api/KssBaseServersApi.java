package awd.cloud.suppers.interfaces.api;

import java.util.Map;

import awd.cloud.suppers.interfaces.api.hystrix.KssBaseServersApiFallBackFactory;
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

@FeignClient(name = "${awd.api.kss.base:AWD-KSS-BASE-SERVER}", fallbackFactory = KssBaseServersApiFallBackFactory.class)

@Component
public interface KssBaseServersApi {
    @RequestMapping(path = {"/kss/test"})
    ResponseMessage<String> test();

    //通知公告
    @GetMapping("/kss_tzgg")
    ResponseMessage<PagerResult<Map<String, Object>>> getTzgg(@RequestBody QueryParam param);

    @PostMapping("/kss_tzgg")
    ResponseMessage<String> saveTzgg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_tzgg/{id}"})
    ResponseMessage<String> updateTzggById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //监室监规
    @GetMapping("/kss_jsjg")
    ResponseMessage<PagerResult<Map<String, Object>>> getJsjg(@RequestBody QueryParam param);

    @PostMapping("/kss_jsjg")
    ResponseMessage<String> saveJsjg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_jsjg/{id}"})
    ResponseMessage<String> updateJsjgById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //一日作息
    @GetMapping("/kss_yrzx")
    ResponseMessage<PagerResult<Map<String, Object>>> getYrzx(@RequestBody QueryParam param);

    @PostMapping("/kss_yrzx")
    ResponseMessage<String> saveYrzx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_yrzx/{id}"})
    ResponseMessage<String> updateYrzxById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //权利义务
    @GetMapping("/kss_qlywgz")
    ResponseMessage<PagerResult<Map<String, Object>>> queryQlywgz(@RequestBody QueryParam param);

    @PostMapping("/kss_qlywgz/saveGZ")
    ResponseMessage<String> saveQlyw(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_qlywgz/{id}"})
    ResponseMessage<String> updateQlywById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //监室 js
    @GetMapping("/js")
    ResponseMessage<PagerResult<Map<String, Object>>> queryJs(@RequestBody QueryParam param);

    //值日表
    @GetMapping("/kss_zrb")
    ResponseMessage<PagerResult<Map<String, Object>>> getZrb(@RequestBody QueryParam param);

    @PostMapping("/kss_zrb")
    ResponseMessage<String> saveZrb(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_zrb/{id}"})
    ResponseMessage<String> updateZrbById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //床位表
    @GetMapping("/kss_cwb")
    ResponseMessage<PagerResult<Map<String, Object>>> getCwb(@RequestBody QueryParam param);

    @PostMapping("/kss_cwb")
    ResponseMessage<String> saveCwb(@RequestBody Map<String,Object> map);

    @PutMapping(path = {"/kss_cwb/{id}"})
    ResponseMessage<String> updateCwbById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //基本信息
    @GetMapping("/jbxx")
    ResponseMessage<PagerResult<Map<String, Object>>> queryJbxx(@RequestBody QueryParam param);

    //每周菜谱
    @GetMapping("/kss_mzcp")
    ResponseMessage<PagerResult<Map<String, Object>>> getMzcp(@RequestBody QueryParam param);

    @PostMapping("/kss_mzcp")
    ResponseMessage<String> saveMzcp(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_mzcp/{id}"})
    ResponseMessage<String> updateMzcpById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //律师会见
    @GetMapping("/lshj")
    ResponseMessage<PagerResult<Map<String, Object>>> getLshj(@RequestBody QueryParam param);

    @PostMapping("/lshj/addLshjDjcb")
    ResponseMessage<String> saveLshj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/lshj/{id}"})
    ResponseMessage<String> updateLshjById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //提讯记录
    @GetMapping("/tsdj")
    ResponseMessage<PagerResult<Map<String, Object>>> getTsdj(@RequestBody QueryParam param);

    @PostMapping("/tsdj/addflow")
    ResponseMessage<String> saveTsdj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/tsdj/{id}"})
    ResponseMessage<String> updateTsdjById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //消费记录
    @GetMapping("/kss_xfjl")
    ResponseMessage<PagerResult<Map<String, Object>>> getXfjl(@RequestBody QueryParam param);

    @PostMapping("/kss_xfjl")
    ResponseMessage<String> saveXfjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_xfjl/{id}"})
    ResponseMessage<String> updateXfjlById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //充值记录
    @GetMapping("/kss_czjl")
    ResponseMessage<PagerResult<Map<String, Object>>> getCzjl(@RequestBody QueryParam param);

    @PostMapping("/kss_czjl")
    ResponseMessage<String> saveCzjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_czjl/{id}"})
    ResponseMessage<String> updateCzjlById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //预约用水
    @GetMapping("/kss_yyys")
    ResponseMessage<PagerResult<Map<String, Object>>> getYyys(@RequestBody QueryParam param);

    @PostMapping("/kss_yyys")
    ResponseMessage<String> saveYyys(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_yyys/{id}"})
    ResponseMessage<String> updateYyysById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //紧急就医
    @GetMapping("/kss_jjjy")
    ResponseMessage<PagerResult<Map<String, Object>>> getJjjy(@RequestBody QueryParam param);

    @PostMapping("/kss_jjjy")
    ResponseMessage<String> saveJjjy(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_jjjy/{id}"})
    ResponseMessage<String> updateJjjyById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //本人预约
    @GetMapping("/kss_bryy")
    ResponseMessage<PagerResult<Map<String, Object>>> getBryy(@RequestBody QueryParam param);

    @PostMapping("/kss_bryy")
    ResponseMessage<String> saveBryy(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_bryy/{id}"})
    ResponseMessage<String> updateBryyById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //可选商品信息
    @GetMapping("/kss_ksxspxx")
    ResponseMessage<PagerResult<Map<String, Object>>> getKxspxx(@RequestBody QueryParam param);

    @PostMapping("/kss_ksxspxx")
    ResponseMessage<String> saveKxspxx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_ksxspxx/{id}"})
    ResponseMessage<String> updateKxspxxById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //自动点名
    @GetMapping("/kss_zddm")
    ResponseMessage<PagerResult<Map<String, Object>>> getZddm(@RequestBody QueryParam param);

    @PostMapping("/kss_zddm")
    ResponseMessage<String> saveZddm(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_zddm/{id}"})
    ResponseMessage<String> updateZddmById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //自动点名时间
    @GetMapping("/kss_zddmsj")
    ResponseMessage<PagerResult<Map<String, Object>>> getZddmsj(@RequestBody QueryParam param);

    @PostMapping("/kss_zddmsj")
    ResponseMessage<String> saveZddmsj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_zddmsj/{id}"})
    ResponseMessage<String> updateZddmsjById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //可视对讲
    @GetMapping("/kss_ksdj")
    ResponseMessage<PagerResult<Map<String, Object>>> getKsdj(@RequestBody QueryParam param);

    @PostMapping("/kss_ksdj")
    ResponseMessage<String> saveKsdj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_ksdj/{id}"})
    ResponseMessage<String> updateKsdjById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //值班签到
    @GetMapping("/kss_zbqd")
    ResponseMessage<PagerResult<Map<String, Object>>> getZbqd(@RequestBody QueryParam param);

    @PostMapping("/kss_zbqd")
    ResponseMessage<String> saveZbqd(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_zbqd/{id}"})
    ResponseMessage<String> updateZbqdById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //收物确认
    @GetMapping("/kss_swqr")
    ResponseMessage<PagerResult<Map<String, Object>>> getSwqr(@RequestBody QueryParam param);

    @PostMapping("/kss_swqr")
    ResponseMessage<String> saveSwqr(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_swqr/{id}"})
    ResponseMessage<String> updateSwqrById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //服药确认
    @GetMapping("/kss_fyqr")
    ResponseMessage<PagerResult<Map<String, Object>>> getFyqr(@RequestBody QueryParam param);

    @PostMapping("/kss_fyqr")
    ResponseMessage<String> saveFyqr(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_fyqr/{id}"})
    ResponseMessage<String> updateFyqrById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //管教谈话
    @GetMapping("/kss_gjth")
    ResponseMessage<PagerResult<Map<String, Object>>> getGjth(@RequestBody QueryParam param);

    @PostMapping("/kss_gjth")
    ResponseMessage<String> saveGjth(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_gjth/{id}"})
    ResponseMessage<String> updateGjthById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //测评试卷
    @GetMapping("/kss_cpsj")
    ResponseMessage<PagerResult<Map<String, Object>>> getCpsj(@RequestBody QueryParam param);

    @PostMapping("/kss_cpsj")
    ResponseMessage<String> saveCpsj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_cpsj/{id}"})
    ResponseMessage<String> updateCpsjById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);

    //测评结果
    @GetMapping("/kss_cpjg")
    ResponseMessage<PagerResult<Map<String, Object>>> getCpjg(@RequestBody QueryParam param);

    @PostMapping("/kss_cpjg")
    ResponseMessage<String> saveCpjg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/kss_cpjg/{id}"})
    ResponseMessage<String> updateCpjgById(@RequestParam("id") String id,@RequestBody Map<String, Object> map);


}
