package awd.mis.desktop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import awd.mis.desktop.tools.*;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.DocumentAndDetailModel;
import awd.mis.desktop.model.DocumentDetailModel;
import awd.mis.desktop.model.DocumentModel;
import awd.mis.desktop.model.DocumentfileModel;
import awd.mis.desktop.model.GroupsModel;

/**
 * @Description 公文
 * @Author WS
 * @Date 2019-05-08 19:14
 */

@Controller
@RequestMapping("/document")
public class DocumentController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private LogsService logsService;
    
	@Autowired
	private ManagerService managerService;

    /**
     * 公文查询列表（总队用户）
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getZdDocument", method = RequestMethod.GET)
    @ResponseBody
    public PagerResult<DocumentModel> documentModelPagerResult(@RequestParam("page") String page,
                                                               @RequestParam("limit") String limit,
                                                               @RequestParam("csdw") String csdw,
                                                               @RequestParam("title")String title) {
        QueryParam queryParam = new QueryParam();

        queryParam.and("state", "R2");
        if(!StringUtils.isNullOrEmpty(csdw)) {
        	queryParam.and("csdw",TermType.like ,"%"+csdw+"%");
        }
        if(!StringUtils.isNullOrEmpty(title)) {
            queryParam.and("title",TermType.like , "%"+title+"%");
        }
        queryParam.setPageIndex(Integer.parseInt(page)-1);
        queryParam.setPageSize(Integer.parseInt(limit));
        ResponseMessage<PagerResult<DocumentModel>> resultResponseMessage = logsService.documentList(queryParam);

        return resultResponseMessage.getResult();
    }

    /**
     * 获取单个公文的所有回复信息（总队用户）
     *
     * @param uuid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getAllSjDocument", method = RequestMethod.GET)
    @ResponseBody
    public PagerResult<DocumentDetailModel> documentAllDetailModelPagerResult(@RequestParam("uuid") String uuid,
                                                                              @RequestParam("page") String page,
                                                                              @RequestParam("limit") String limit) {
        QueryParam queryParam = new QueryParam();

        queryParam.and("state", "R2");
        queryParam.and("uuid", uuid);
        queryParam.setPageIndex(Integer.parseInt(page) - 1);
        queryParam.setPageSize(Integer.parseInt(limit));
        ResponseMessage<PagerResult<DocumentDetailModel>> resultResponseMessage = logsService.documentDetailList(queryParam);

        return resultResponseMessage.getResult();
    }

    /**
     * 获取用户接收到的公文信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getSjDocument", method = RequestMethod.GET)
    @ResponseBody
    public PagerResult<DocumentAndDetailModel> documentDetailModelPagerResult(@RequestParam("page") String page,
                                                                              @RequestParam("limit") String limit,
                                                                              @RequestParam("id") String id,
                                                                              @RequestParam("title") String title) {

        QueryParam queryParam = new QueryParam();
        QueryParam document = new QueryParam();
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userJh = users.getUserinfo().getJh();
        String jsbh = users.getJsbh();
        int total = 0;
        ResponseMessage<PagerResult<DocumentModel>> models = new ResponseMessage<PagerResult<DocumentModel>>();
        if (!StringUtils.isNullOrEmpty(title)){
            document.and("title",TermType.like,"%"+title+"%");
            document.and("csdw",TermType.like,"%"+jsbh+"%");
            document.setPageIndex(Integer.parseInt(page)-1);
            document.setPageSize(Integer.parseInt(limit));
            models = logsService.documentList(document);
            total = models.getResult().getTotal();
        }else{
            queryParam.setPageIndex(Integer.parseInt(page)-1);
            queryParam.setPageSize(Integer.parseInt(limit));
        }
        if(!StringUtils.isNullOrEmpty(id)) {
        	queryParam.and("id", id);
        }
        queryParam.and("state", "R2");
        queryParam.and("jsbh", jsbh);
        queryParam.and("hfrjh",userJh);
        ResponseMessage<String> resultResponseMessage = logsService.getDetail(queryParam);
        List<DocumentAndDetailModel> li = new ArrayList<>();
        PagerResult p = JSONUtil.toBean(resultResponseMessage.getResult(), PagerResult.class);
        for (Object o : p.getData()) {
            DocumentAndDetailModel d = new DocumentAndDetailModel();
            d = JSONUtil.toBean(JSONUtil.toJson(o), DocumentAndDetailModel.class);
            String titles = d.getDocumentEntity().getTitle();
            if(!StringUtils.isNullOrEmpty(title)) {
                for(DocumentModel model: models.getResult().getData()){
                    if (model.getTitle().equals(titles)){
                        d.setDocumentEntity(model);
                        li.add(d);
                    }
                }
                p.setTotal(total);
            }else{
                li.add(d);
            }
        }
        p.setData(li);
        return p;
    }

    /**
     * 获取文件列表
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    @ResponseBody
    public List<DocumentfileModel> getFile(@RequestParam("uuid") String uuid) {
        QueryParam queryParam = new QueryParam();
        queryParam.and("uuid", uuid);
        ResponseMessage<PagerResult<DocumentfileModel>> resultResponseMessage = logsService.documentfileListExcludeFile(queryParam);
        if (resultResponseMessage.getStatus() == 200) {
            return resultResponseMessage.getResult().getData();
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("/saveDocument")
    @ResponseBody
    public ResponseMessage<String> saveDocument(@Validated() DocumentModel documentModel, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return ResponseMessage.error(error.getDefaultMessage());
            }
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        documentModel.setCreator(user.getUsername());
        ResponseMessage<String> re = logsService.documentSave(documentModel);
        if (re.getStatus() == 200) {
            return ResponseMessage.ok("公文发送成功");
        }
        return re;
    }

    /**
     * 文件单独保存
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public synchronized ResponseMessage<Object> saveFile(@RequestParam(value = "file") MultipartFile file, @RequestParam("uuid") String uuid, HttpServletRequest request) {

        Map<String, Object> add = logsService.documentFileUpload(file, uuid);
        if (add != null && (Integer) add.get("code") == 200) {
            return ResponseMessage.ok(add.get("msg"));
        } else if (add != null && (Integer) add.get("code") == 400) {
            return ResponseMessage.error((String) add.get("msg"));

        }
        return ResponseMessage.error("保存失败！");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object downloadModel(@RequestParam("url") String netUrl) {
        ResponseEntity<InputStreamResource> response = null;

        if (StringUtils.isNullOrEmpty(netUrl)) {
            return ResponseMessage.error("地址为空");
        }
        try {
            response = DownloadFileUtil.download(netUrl);
        } catch (Exception e) {
            logger.error("下载失败");
        }
        return response;
    }

    @RequestMapping(value = "/replyDocument", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> replyDocument(@RequestParam("id") String id, @RequestParam("hfsj") String hfsj, @RequestParam("hfnr") String hfnr) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DocumentDetailModel doc = new DocumentDetailModel();
        doc.setHfnr(hfnr);
        doc.setId(id);
        try {
            Date time = s.parse(hfsj);
            doc.setHfsj(time);
            logsService.documentDetailUpdateByPrimaryKey(id, doc);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseMessage.error("时间格式不对");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败");
        }

        return ResponseMessage.ok();
    }

    private List<Sort> getSorts() {
        Sort sort = new Sort();
        List<Sort> sortList = Lists.newArrayList();
        sort.setName("qfrq");
        sort.setOrder("desc");
        sortList.add(sort);
        return sortList;
    }
    
    
	@RequestMapping(value ="/getJsbhByJsName",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserSetting(HttpServletRequest request){
		String zsdwtip = request.getParameter("zsdwtip");
		String cbdwtip = request.getParameter("cbdwtip");
		String ngdwtip = request.getParameter("ngdwtip");
		QueryParam qParam = new QueryParam();
		qParam.and("groupname", zsdwtip);
		qParam.and("groupname", cbdwtip);
		qParam.and("groupname", ngdwtip);
		qParam.and("fgroup", "0");
		ResponseMessage<PagerResult<GroupsModel>> rm = managerService.groupsQuery(qParam);
		return rm;
	}
	
    @RequestMapping(value = "/setDocumentDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> setGwhfValue(	@RequestParam("id") String id,
    												@RequestParam("hfsj") String hfsj,
    												@RequestParam("hfnr") String hfnr,
    												@RequestParam("flag") String flag) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DocumentDetailModel doc = new DocumentDetailModel();
        doc.setHfnr(hfnr);
        doc.setId(id);
        doc.setFlag(flag);
        try {
            Date time = s.parse(hfsj);
            doc.setHfsj(time);
            logsService.updateDocumentdetailModel(doc);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseMessage.error("时间格式不对");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败");
        }

        return ResponseMessage.ok();
    }
}
