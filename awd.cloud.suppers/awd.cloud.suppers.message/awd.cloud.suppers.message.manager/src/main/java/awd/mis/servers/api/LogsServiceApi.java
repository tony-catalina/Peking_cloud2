package awd.mis.servers.api;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.api.hystrix.LogsApiFallBackFactory;
import awd.mis.servers.vo.LoginlogsModel;
import feign.codec.Encoder;


@FeignClient(name = "AWD-MIS-LOGS-SERVER", fallbackFactory = LogsApiFallBackFactory.class)
public interface LogsServiceApi {

    @RequestMapping(value = "/loginlogs/login", method = RequestMethod.POST)
    ResponseMessage<String> loginlogsSave(@RequestBody LoginlogsModel loginlogs);

    @RequestMapping(value = "/loginlogs", method = RequestMethod.GET)
    ResponseMessage<PagerResult<LoginlogsModel>> loginlogsList(QueryParam param);

    @RequestMapping(value = "/loginlogs/logout/{id}", method = RequestMethod.PUT)
    ResponseMessage<String> loginout(@PathVariable("id") String id);
    
}
