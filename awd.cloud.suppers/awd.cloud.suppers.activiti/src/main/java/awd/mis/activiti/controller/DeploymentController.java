package awd.mis.activiti.controller;

import awd.mis.activiti.entity.AwdProcessDefinitionEntity;
import awd.mis.activiti.utils.Result;
import awd.mis.activiti.utils.ResultUtils;
import awd.mis.activiti.utils.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by watchdb on 2017/12/31.
 */

/**
 * 部署流程
 *
 * @author henryyan
 */
@Api(description = "流程步骤相关操作", tags = {"deploy"})
@Controller
@RequestMapping(value = "/deployment")
public class DeploymentController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RepositoryService repositoryService;

    /**
     * 流程定义列表
     */
    @ApiOperation("流程定义列表")
    @RequestMapping(value = "/process-list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result processList(
            @RequestParam(value = "rows", required = false, defaultValue = "10") String rows,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            HttpServletRequest request) {

        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String index = request.getParameter("pageIndex");
        String size = request.getParameter("pageSize");
        int pageSize = Integer.parseInt(rows);
        int pageNow = (Integer.parseInt(page)-1)*pageSize;

        if (StringUtil.isNotNullOrEmpty(index) && StringUtil.isNotNullOrEmpty(size)) {
            pageSize = Integer.parseInt(size);
            pageNow = (Integer.parseInt(index)-1)*pageSize;
        }

        ProcessDefinitionQuery processDefinitionQuery = repositoryService//与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc();
        if (!StringUtil.isNullOrEmpty(key)) {
            processDefinitionQuery = processDefinitionQuery.processDefinitionKeyLike(key);
        }
        if (!StringUtil.isNullOrEmpty(name)) {
            processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike("%" + name + "%");
        }
        List<ProcessDefinition> list = processDefinitionQuery.listPage(pageNow, pageSize);//返回一个集合列表，封装流程定义

        long count = processDefinitionQuery.count();
        List<AwdProcessDefinitionEntity> li = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (ProcessDefinition processDefinition : list) {
                /*System.out.println("流程定义ID:" + processDefinition.getId());//流程定义的key+版本+随机生成数
                System.out.println("流程定义名称:" + processDefinition.getName());//对应HelloWorld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + processDefinition.getKey());//对应HelloWorld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + processDefinition.getVersion());//当流程定义的key值相同的情况下，版本升级，默认从1开始
                System.out.println("资源名称bpmn文件:" + processDefinition.getResourceName());
                System.out.println("资源名称png文件:" + processDefinition.getDiagramResourceName());
                System.out.println("部署对象ID:" + processDefinition.getDeploymentId());
                System.out.println("################################");*/
                AwdProcessDefinitionEntity awdProcessDefinitionEntity = new AwdProcessDefinitionEntity();
                BeanUtils.copyProperties(processDefinition, awdProcessDefinitionEntity);
                awdProcessDefinitionEntity.setVersion(awdProcessDefinitionEntity.getId().split(":")[1]);
                li.add(awdProcessDefinitionEntity);
            }
        }

        return ResultUtils.ok(count, li);
    }

    /**
     * 部署流程资源
     */
    @ApiOperation("上传部署流程资源")
    @PostMapping(value = "/deploy")
    public String deploy(@RequestParam(value = "file", required = true) MultipartFile file) {

        // 获取上传的文件名
        String fileName = file.getOriginalFilename();

        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = file.getInputStream();

            // 文件的扩展名
            String extension = FilenameUtils.getExtension(fileName);

            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment();
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {
                // 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            //数据同步
            deployment.deploy();

        } catch (Exception e) {
            logger.error("error on deploy process, because of file input stream");
        }
        return "redirect:process-list";
    }

    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @ApiOperation("读取流程资源")
    @GetMapping(value = "/read-resource")
    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response)
            throws Exception {
        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

        // 通过接口读取
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @ApiOperation("删除部署的流程，级联删除流程实例")
    @RequestMapping(value = "/delete-deployment", method = RequestMethod.POST)
    public String deleteProcessDefinition(@RequestParam("deploymentId") String deploymentId, @RequestParam("processDefinitionId") String processDefinitionId) {

        return "redirect:process-list";
    }


}
