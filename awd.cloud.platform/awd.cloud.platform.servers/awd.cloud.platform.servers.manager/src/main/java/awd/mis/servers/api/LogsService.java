package awd.mis.servers.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.hystrix.LogsFallBackFactory;
import awd.mis.servers.config.FeignSupportConfig;
import awd.mis.servers.model.HfModel;

import java.util.Map;


@FeignClient(name = "${awd.api.logs:AWD-MIS-LOGS-SERVER}",configuration = FeignSupportConfig.class, fallbackFactory = LogsFallBackFactory.class)
public interface LogsService {

    @RequestMapping(value = "/hf", method = RequestMethod.POST)
    ResponseMessage<String> saveHf(@RequestBody HfModel hf1);

    @PostMapping(value = "/cloudfile/uploadByte")
    ResponseMessage<String> upload(
            @RequestBody byte[] file,
            @RequestParam("fileName") String fileName,
            @RequestParam("ext") String ext);

    @PostMapping(value = "/cloudfile/uploadBytes")
    ResponseMessage<Map<String, String>> upload(
            @RequestBody Map<String, byte[]> file,
            @RequestParam("fileName") String fileName,
            @RequestParam("ext") String ext);
}
