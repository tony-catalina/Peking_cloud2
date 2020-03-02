package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.kss_AqglService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/kss/aqgl")
@Api(tags = "kss-aqgl",description = "安全管理")
public class BjdpAqglController {

    @Autowired
    private kss_AqglService kssAqglService;
    //案由分析 拘留所
    @OpenAPI
    @ResponseBody
    @GetMapping("/aqglQuery")
    @ApiOperation("查询")
    @CrossOrigin
    public Map<String,Object> gylQuery(){
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        Map<String,Object> index=new HashMap<>();
        Map<String,Object> lt=new HashMap<>();
        Map<String,Object> b=new HashMap<>();
        Map<String,Object> listMap=new HashMap<>();
        Map<String,Object> aqglCount= kssAqglService.getAqglCount();
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        listMap.put("name1","加载械具");
        listMap.put("msg1",aqglCount.get("jzxj").toString());
        listMap.put("name2","严管对象");
        listMap.put("msg2",aqglCount.get("ygry").toString());
        listMap.put("class1","");
        listMap.put("class2","");
        list.add(listMap);
        Map<String,Object> listMap1=new HashMap<>();
        listMap1.put("name1","囚车使用情况");
        listMap1.put("msg1","6");
        listMap1.put("name2","电子脚镣使用情况");
        listMap1.put("msg2",aqglCount.get("dzjl").toString());
        listMap1.put("class1","");
        listMap1.put("class2","");
        list.add(listMap1);
        Map<String,Object> listMap2=new HashMap<>();
        listMap2.put("name1","单独关押");
        listMap2.put("msg1","60");
        listMap2.put("name2","涉恐");
        listMap2.put("msg2","2");
        listMap2.put("class1","");
        listMap2.put("class2","");
        list.add(listMap2);
        Map<String,Object> listMap3=new HashMap<>();
        listMap3.put("name1","涉黑");
        listMap3.put("msg1","2");
        listMap3.put("name2","涉恶");
        listMap3.put("msg2","2");
        listMap3.put("class1","");
        listMap3.put("class2","");
        list.add(listMap3);
        b.put("name","安全管理");
        b.put("list",list);
        lt.put("name","重大一、二、三级风险对象详情");
        lt.put("b",b);
        index.put("lt",lt);
        data.put("index",index);
        map.put("data",data);
        map.put("code","200");
        map.put("msg","查询成功");
        return map;
    }
}
