package awd.mis.activiti.rest.editor.controller;

import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.activiti.mapper.FlowMapMapper;
import awd.mis.activiti.mapper.FlowNodeMapper;
import awd.mis.activiti.sevice.WorkflowService;
import awd.mis.activiti.utils.R;
import awd.mis.activiti.utils.Result;
import awd.mis.activiti.utils.ResultUtils;
import awd.mis.activiti.utils.StringUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.*;
import org.activiti.explorer.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.h2.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "流程模型Model操作相关", tags = {"modeler"})
@Controller
@RequestMapping("models")
@Slf4j
public class ModelController {
	Logger log=LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowMapMapper flowMapMapper;

    @Autowired
    private FlowNodeMapper flowNodeMapper;
    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 新建一个空模型
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value = "新建一个空模型")
    @GetMapping("/{id}")
    public String newModel(@PathVariable(value = "id", required = false) String id) throws UnsupportedEncodingException {
        if (!"0".equals(id)) {
            return "redirect:/modeler.html?modelId=" + id;
        } else {
            RepositoryService repositoryService = processEngine.getRepositoryService();
            //初始化一个空模型
            Model model = repositoryService.newModel();

            //设置一些默认信息，可以用参数接收
            String name = "未命名";
            String description = "";
            int revision = 1;
            String key = "process";

            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

            model.setName(name);
            model.setKey(key);
            model.setMetaInfo(modelNode.toString());

            repositoryService.saveModel(model);
            id = model.getId();

            //完善ModelEditorSource
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace",
                    "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
            return "redirect:/modeler.html?modelId=" + id;
        }

    }

    /**
     * 获取所有模型
     *
     * @return
     */
    @ApiOperation(value = "获取所有模型")
    @GetMapping("/list")
    @ResponseBody
    public List<Model> modelList() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Model> li = repositoryService.createModelQuery().orderByCreateTime().desc().list();
        return li;
    }

    /**
     * 获取所有模型
     *
     * @return
     */
    @ApiOperation(value = "分页获取所有model")
    @RequestMapping(value = "/list/page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result modelListTree(HttpServletRequest request) {
        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        if (StringUtil.isNullOrEmpty(rows) || StringUtil.isNullOrEmpty(page)) {
            return ResultUtils.error("分页参数为传递");
        }
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        int pageNow = Integer.parseInt(page);
        int pageSize = Integer.parseInt(rows);
        if (pageNow > 0) {
            pageNow = (pageNow - 1) * pageSize;
        }
        ModelQuery modelQuery = repositoryService.createModelQuery().orderByCreateTime().desc();
        if (!StringUtil.isNullOrEmpty(id)) {
            modelQuery = modelQuery.modelId(id);
        }
        if (!StringUtil.isNullOrEmpty(name)) {
            modelQuery = modelQuery.modelNameLike("%"+name+"%");
        }
        Long count = modelQuery.count();
        List<Model> list = modelQuery.listPage(pageNow, pageSize);

        return ResultUtils.ok(count, list);
    }

    /**
     * 删除模型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除模型")
    @DeleteMapping("{id}")
    @ResponseBody
    public Object deleteModel(@PathVariable("id") String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteModel(id);
        return success();
    }

    /**
     * 发布模型为流程定义
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发布模型为流程定义")
    @PostMapping("{id}/deployment")
    @ResponseBody
    public Object deploy(@PathVariable("id") String id) throws Exception {

        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return failed("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0) {
            return failed("数据模型不符要求，请至少设计一条主线流程。");
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"));

        Deployment deployment = deploymentBuilder.deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);


        try {
            List<Process> processes = model.getProcesses();
            String key = processes.get(0).getId();
            List<ProcessDefinition> list = processEngine.getRepositoryService()
                    .createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
            workflowService.flowSynchronization("", list);
        } catch (Exception e) {
            e.printStackTrace();
            return success();
        }
        return success();
    }

    @ApiOperation(value = "上传一个已有模型")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public void deployUploadedFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) {
        InputStreamReader in = null;
        try {
            try {
                boolean validFile = false;
                String fileName = uploadfile.getOriginalFilename();
                if (fileName.endsWith(".bpmn20.xml") || fileName.endsWith(".bpmn")) {
                    validFile = true;

                    XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
                    in = new InputStreamReader(new ByteArrayInputStream(uploadfile.getBytes()), "UTF-8");
                    XMLStreamReader xtr = xif.createXMLStreamReader(in);
                    BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

                    if (bpmnModel.getMainProcess() == null || bpmnModel.getMainProcess().getId() == null) {
//                        notificationManager.showErrorNotification(Messages.MODEL_IMPORT_FAILED,
//                                i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_BPMN_EXPLANATION));
                        System.out.println("err1");
                    } else {

                        if (bpmnModel.getLocationMap().isEmpty()) {
//                            notificationManager.showErrorNotification(Messages.MODEL_IMPORT_INVALID_BPMNDI,
//                                    i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_BPMNDI_EXPLANATION));
                            System.out.println("err2");
                        } else {

                            String processName = null;
                            if (StringUtils.isNotEmpty(bpmnModel.getMainProcess().getName())) {
                                processName = bpmnModel.getMainProcess().getName();
                            } else {
                                processName = bpmnModel.getMainProcess().getId();
                            }
                            Model modelData;
                            modelData = repositoryService.newModel();
                            ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
                            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
                            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
                            modelData.setMetaInfo(modelObjectNode.toString());
                            modelData.setName(processName);

                            repositoryService.saveModel(modelData);

                            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
                            ObjectNode editorNode = jsonConverter.convertToJson(bpmnModel);

                            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
                        }
                    }
                } else {
//                    notificationManager.showErrorNotification(Messages.MODEL_IMPORT_INVALID_FILE,
//                            i18nManager.getMessage(Messages.MODEL_IMPORT_INVALID_FILE_EXPLANATION));
                    System.out.println("err3");
                }
            } catch (Exception e) {
                String errorMsg = e.getMessage().replace(System.getProperty("line.separator"), "<br/>");
//                notificationManager.showErrorNotification(Messages.MODEL_IMPORT_FAILED, errorMsg);
                System.out.println("err4");
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
//                    notificationManager.showErrorNotification("Server-side error", e.getMessage());
                    System.out.println("err5");
                }
            }
        }
    }

    @RequestMapping(value = "export/{modelId}", method = RequestMethod.GET)
    public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            log.error("导出model的xml文件失败：modelId={}", modelId, e);
        }
    }

    private Map<String, Object> success() {
        Map<String, Object> map = new HashMap();
        map.put("status", true);
        map.put("reason", "操作成功");
        return map;
    }

    private Map<String, Object> failed(String reason) {
        Map<String, Object> map = new HashMap();
        map.put("status", false);
        map.put("reason", "操作失败：" + reason);
        return map;
    }

}
