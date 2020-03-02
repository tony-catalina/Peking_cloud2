package awd.mis.appstore.api;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import awd.mis.appstore.api.hystrix.LogsServiceHystrix;
import awd.mis.appstore.api.hystrix.ManagerServiceHystrix;
import awd.mis.appstore.model.PlModel;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name = "${awd.api.logs:AWD-MIS-LOGS-SERVER}",  configuration = LogsService.MultipartSupportConfig.class,fallbackFactory=LogsServiceHystrix.class)
public interface LogsService {

    public class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
                @Override
                public HttpMessageConverters getObject() throws BeansException {
                    return new HttpMessageConverters(new RestTemplate().getMessageConverters());
                }
            }));
        }
    }

    @RequestMapping(value = "/cloudfile/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> upload(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "jsbh") String jsbh, @RequestParam(value="dir",required=false)String dir,@RequestParam(value = "rybh", required = false) String rybh, @RequestParam(value = "share") String share, @RequestParam(value = "userid") String updator);


	/**
	 * 获取评论
	 * @param params
	 * @return
	 */
	@GetMapping("/pl/getPlEntyListByAppcode")
	public ResponseMessage<PagerResult<PlModel>> getPlEntyListByAppcode (QueryParam queryParam);
	
	/**
	 * 增加评论
	 * @param plModel
	 * @return
	 */
	@PostMapping("/pl/save") 
	public ResponseMessage<String> savePl(@RequestBody PlModel plModel);
	
	/**
	 * 修改评论状态
	 * @param id plModel
	 * @return
	 */
	@PostMapping("/pl/saveOrUpdate") 
	public ResponseMessage<String> updatePlById(@RequestParam("id") String id ,@RequestBody PlModel plModel);
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	@PostMapping("/pl/deleteById") 
	public ResponseMessage<String> deletePlById(@RequestParam("id") String id);
	
	/**
	 * 移除评论
	 * @param id
	 * @return
	 */
	@PostMapping("/pl/removeById") 
	public ResponseMessage<String> removePlById(@RequestParam("id") String id);
}
