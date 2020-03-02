package awd.cloud.platform.servers.analyse.controller.jls;


import awd.cloud.platform.servers.analyse.service.jls.Jls_JbdjService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_jbdj")
@RefreshScope
@Api(tags = "jls_jbdj",description = "交班登记")
public class Jls_JbdjController {
    @Autowired
    private Jls_JbdjService jls_jbdjService;

    @GetMapping("/Jbdj")
    @ApiOperation("业务台账")
    @OpenAPI
    public ResponseMessage<?>  jlglYwdt(@RequestParam(value="starttime",required = false) String starttime,
                                        @RequestParam(value="endtime",required = false) String endtime,
                                        @RequestParam(value="jsbh",required = false) String jsbh){

        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        try {
            List<Map<String,Object>>   jlglYwdt=jls_jbdjService.jbdjCx(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdta=jls_jbdjService.jbdjCxa(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtb=jls_jbdjService.jbdjCxb(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtc=jls_jbdjService.jbdjCxc(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtd=jls_jbdjService.jbdjCxd(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdte=jls_jbdjService.jbdjCxe(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtf=jls_jbdjService.jbdjCxf(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtg=jls_jbdjService.jbdjCxg(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdth=jls_jbdjService.jbdjCxh(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdti=jls_jbdjService.jbdjCxi(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtj=jls_jbdjService.jbdjCxj(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtk=jls_jbdjService.jbdjCxk(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtl=jls_jbdjService.jbdjCxl(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtm=jls_jbdjService.jbdjCxm(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtn=jls_jbdjService.jbdjCxn(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdto=jls_jbdjService.jbdjCxo(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtp=jls_jbdjService.jbdjCxp(starttime, endtime, jsbh);
            List<Map<String,Object>>   jlglYwdtq=jls_jbdjService.jbdjCxq(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("jlglYwdt",jlglYwdt);
            map.put("jlglYwdta",jlglYwdta);
            map.put("jlglYwdtb",jlglYwdtb);
            map.put("jlglYwdtc",jlglYwdtc);
            map.put("jlglYwdtd",jlglYwdtd);
            map.put("jlglYwdte",jlglYwdte);
            map.put("jlglYwdtf",jlglYwdtf);
            map.put("jlglYwdtg",jlglYwdtg);
            map.put("jlglYwdth",jlglYwdth);
            map.put("jlglYwdti",jlglYwdti);
            map.put("jlglYwdtj",jlglYwdtj);
            map.put("jlglYwdtk",jlglYwdtk);
            map.put("jlglYwdtl",jlglYwdtl);
            map.put("jlglYwdtm",jlglYwdtm);
            map.put("jlglYwdtn",jlglYwdtn);
            map.put("jlglYwdto",jlglYwdto);
            map.put("jlglYwdtp",jlglYwdtp);
            map.put("jlglYwdtq",jlglYwdtq);
            return     ResponseMessage.ok(map);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}