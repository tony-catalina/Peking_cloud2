package awd.mis.desktop.api;

import awd.mis.desktop.api.hystrix.KssServerFallBackFactory;
import awd.mis.desktop.model.FjszModel;
import awd.mis.desktop.model.JqModel;
import awd.mis.desktop.model.JsModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author WS
 * @Description: 看守所服务接口
 * @date 2020/1/6 下午1:42
 */
@FeignClient(name = "${awd.api.kss.base:AWD-KSS-BASE-SERVER}",fallbackFactory= KssServerFallBackFactory.class)
public interface KssServerService {
    /*********************************************************************/
    @RequestMapping(value = "/fjsz", method = RequestMethod.GET)
    ResponseMessage<PagerResult<FjszModel>> fjszQuery(QueryParam params);

    @RequestMapping(value = "/fjsz", method = RequestMethod.POST)
    ResponseMessage<String> fjszSave(@RequestBody FjszModel fjsz);

    @RequestMapping(value = "/fjsz/{id}", method = RequestMethod.PUT)
    ResponseMessage<String> fjszUpdate(@PathVariable("id") String id, @RequestBody FjszModel fjsz);

    @RequestMapping(value = "/fjsz/{id}", method = RequestMethod.DELETE)
    ResponseMessage<String> fjszDelete(@PathVariable("id") String id);

    @RequestMapping(value = "/jq", method = RequestMethod.GET)
    ResponseMessage<PagerResult<JqModel>> jqQuery(QueryParam params);

    @RequestMapping(value = "/jq", method = RequestMethod.POST)
    ResponseMessage<String> jqSave(@RequestBody JqModel jq);

    @PutMapping(value = "/jq/{id}")
    ResponseMessage<String> jqUpdate(@PathVariable("id") String id, @RequestBody JqModel jq);

    @RequestMapping(value = "/jq/{id}", method = RequestMethod.DELETE)
    ResponseMessage<String> jqDelete(@PathVariable("id") String id);

    /*********************************************************************/

    @RequestMapping(value = "/js", method = RequestMethod.GET)
    ResponseMessage<PagerResult<JsModel>> jsQuery(QueryParam params);

    @PutMapping(value = "/js/{id}")
    ResponseMessage<String> jsUpdate(@PathVariable("id") String id, @RequestBody JsModel js);

    @RequestMapping(value = "/js", method = RequestMethod.POST)
    ResponseMessage<String> jsSave(@RequestBody JsModel js);

    @RequestMapping(value = "/js/{id}", method = RequestMethod.DELETE)
    ResponseMessage<String> jsDelete(@PathVariable("id") String id);


}
