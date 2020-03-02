package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_ZfzlkpService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-10 11:46
 * Description：<描述>
 */
@Controller
@RequestMapping("/Kss/Zfzlkp")
@Api(description = "执法质量靠评=封装的假数据")
public class Kss_ZfzlkpController {

    @Autowired
    private Kss_ZfzlkpService kss_zfzlkpService;

    //执法质量靠评
    @ResponseBody
    @GetMapping("/findZfzlk")
    @OpenAPI
    @CrossOrigin
    public HashMap<String, Object> findZfzlk(){

        ArrayList<Object> list = kss_zfzlkpService.selectZfzlkp();

        HashMap<String, Object> reMap = new HashMap<>();
        reMap.put("code",200);
        reMap.put("data",54);
        reMap.put("msg","成功");
        reMap.put("result",true);
        reMap.put("totalSize",null);
        reMap.put("kss",list);


        return reMap;
    }

}
