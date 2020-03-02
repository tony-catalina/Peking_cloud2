package awd.mis.desktop.api;

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

import awd.mis.desktop.api.hystrix.LogsFallBackFactory;
import awd.mis.desktop.model.CloudfileModel;
import awd.mis.desktop.model.DocumentAndDetailModel;
import awd.mis.desktop.model.DocumentDetailModel;
import awd.mis.desktop.model.DocumentModel;
import awd.mis.desktop.model.DocumentfileModel;
import awd.mis.desktop.model.LoginlogsModel;
import awd.mis.desktop.model.MsgboxModel;
import awd.mis.desktop.model.OperatelogsModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;


@FeignClient(name = "${awd.api.logs:awd-mis-logs-server}", fallbackFactory = LogsFallBackFactory.class,url="http://192.168.4.114:12101", configuration = LogsService.MultipartSupportConfig.class)
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
    public Map<String, Object> upload(@RequestPart("file") MultipartFile file,
                                      @RequestParam("jsbh") String jsbh,
                                      @RequestParam(value = "dir", required = false) String dir,
                                      @RequestParam(value = "rybh", required = false) String rybh,
                                      @RequestParam(value = "share", required = false) String share,
                                      @RequestParam("userid") String userid);

    @RequestMapping(value = "/msgbox", method = RequestMethod.POST)
    ResponseMessage<String> senderMsgOne(MsgboxModel msgboxModel);

    @RequestMapping(value = "/msgbox/updateAllMsgByJh", method = RequestMethod.POST)
    ResponseMessage<String> updateAllMsgByJh(@RequestParam("jh") String jh);

    @RequestMapping(value = "/msgbox", method = RequestMethod.GET)
    ResponseMessage<PagerResult<MsgboxModel>> queryMsg(QueryParam params);

    @PutMapping(value = "/msgbox/{id}")
    ResponseMessage<String> msgUpdate(@PathVariable("id") String id, @RequestBody MsgboxModel msgboxModel);

    @RequestMapping(value = "/loginlogs/login", method = RequestMethod.POST)
    ResponseMessage<String> loginlogsSave(@RequestBody LoginlogsModel loginlogs);

    @RequestMapping(value = "/loginlogs", method = RequestMethod.GET)
    ResponseMessage<PagerResult<LoginlogsModel>> loginlogsList(QueryParam param);

    @RequestMapping(value = "/loginlogs/logout/{id}", method = RequestMethod.PUT)
    ResponseMessage<String> loginout(@PathVariable("id") String id);

    @RequestMapping(value = "/operatelogs", method = RequestMethod.POST)
    ResponseMessage<String> operatelogsSave(@RequestBody OperatelogsModel operatelogs);

    @RequestMapping(value = "/cloudfile", method = RequestMethod.GET)
    ResponseMessage<PagerResult<CloudfileModel>> cloudFileQuery(QueryParam param);

    @RequestMapping(value = "/cloudfile/mkdir", method = RequestMethod.POST)
    ResponseMessage<String> mkdir(@RequestParam("jsbh") String jsbh, @RequestParam("userid") String id, @RequestParam("fdir") String fdir, @RequestParam("dir") String dir);

    @RequestMapping(value = "/cloudfile/update", method = RequestMethod.POST)
    ResponseMessage<String> cloudFileUpdate(@RequestBody CloudfileModel cloudfile);

    @RequestMapping(value = "/document", method = RequestMethod.POST)
    ResponseMessage<String> documentSave(@RequestBody DocumentModel data);

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    ResponseMessage<PagerResult<DocumentModel>> documentList(QueryParam param);

    @RequestMapping(value = "/documentdetail", method = RequestMethod.GET)
    ResponseMessage<PagerResult<DocumentDetailModel>> documentDetailList(QueryParam param);

    @RequestMapping(value = "/documentdetail/getDetail", method = RequestMethod.GET)
    ResponseMessage<String> getDetail(QueryParam param);

    @RequestMapping(value = "/documentdetail", method = RequestMethod.POST)
    ResponseMessage<String> documentDetailSave(@RequestBody DocumentAndDetailModel data);
    
    @RequestMapping(value="/documentdetail/updateDetail",method=RequestMethod.POST)
    ResponseMessage<String> updateDocumentdetailModel(@RequestBody DocumentDetailModel data);

    @RequestMapping(value = "/documentdetail/{id}", method = RequestMethod.PUT)
    ResponseMessage documentDetailUpdateByPrimaryKey(@RequestParam("id") String id, @RequestBody DocumentDetailModel data);

    @RequestMapping(value = "/documentfile/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> documentFileUpload(@RequestPart("file") MultipartFile file,
                                           @RequestParam("uuid") String uuid);

    @RequestMapping(value = "/documentfile", method = RequestMethod.GET)
    ResponseMessage<PagerResult<DocumentfileModel>> documentfileList(QueryParam param);

    @RequestMapping(value = "/documentfile/listExcludeFile", method = RequestMethod.GET)
    ResponseMessage<PagerResult<DocumentfileModel>> documentfileListExcludeFile(QueryParam param);
    
}
