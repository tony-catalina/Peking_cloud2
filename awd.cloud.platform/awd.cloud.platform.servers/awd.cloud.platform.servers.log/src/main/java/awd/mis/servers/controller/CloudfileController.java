/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import awd.framework.common.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.CloudfileEntity;
import awd.mis.servers.service.CloudfileService;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.FastDFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/cloudfile")
@AccessLogger("云文件")
@Api(tags = "awd-file-manager", description = "提供基本文件管理功能")
public class CloudfileController implements GenericEntityController<CloudfileEntity, String, QueryParamEntity, CloudfileEntity> {

    private static Logger logger = LoggerFactory.getLogger(CloudfileController.class);
    private CloudfileService cloudfileService;


    @Override
    public CloudfileEntity modelToEntity(CloudfileEntity model, CloudfileEntity entity) {
        return model;
    }

    @Autowired
    public void setCloudfileService(CloudfileService cloudfileService) {
        this.cloudfileService = cloudfileService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CrudService<CloudfileEntity, String> getService() {
        return cloudfileService;
    }


    @Override
    @OpenAPI
    public ResponseMessage<PagerResult<CloudfileEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return GenericEntityController.super.list(arg0, arg1);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("文件上传")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @ResponseBody
    @OpenAPI
    public Map<String, Object> upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("jsbh") String jsbh,
            @RequestParam(value = "dir", required = false) String dir,
            @RequestParam(value = "rybh", required = false) String rybh,
            @RequestParam(value = "share", required = false) String share,
            @RequestParam("userid") String userid) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        int len1 = 0;
        InputStream inputStream = file.getInputStream();
        if (inputStream != null) {
            len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
            inputStream.close();
        }
        FastDFSFile fastfile = new FastDFSFile(fileName, file_buff, ext);
        try {
            if (StringUtils.isNullOrEmpty(dir)) {
                dir = "文档";
            }
            if (StringUtils.isNullOrEmpty(share)) {
                share = "0";
            }
            CloudfileEntity fileEntity = new CloudfileEntity();
            fileEntity.setJsbh(jsbh);
            fileEntity.setFdir(getFdir(dir));
            fileEntity.setDir(dir);
            fileEntity.setFiletype(ext);
            fileEntity.setFileicon(ext + ".png");
            fileEntity.setScbz("0");
            fileEntity.setShare(share);
            fileEntity.setIsdir("0");
            fileEntity.setUploader(userid);
            fileEntity.setUptime(Calendar.getInstance().getTime());
            fileEntity.setCreator(userid);
            fileEntity.setBz(String.valueOf(len1));
            fileEntity.setUpdator(fileName);
            String path = cloudfileService.saveOrUpdate(fileEntity, fastfile);
            result.put("code", 200);
            result.put("data", path);
            result.put("msg", "文件保存成功！");
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
            result.put("code", 400);
            result.put("data", "");
            result.put("msg", "文件保存失败！");
        }

        return result;
    }


    private String getFdir(String dir) {
        if (dir.indexOf("/") == -1) {
            return "";
        } else {
            int pos = dir.lastIndexOf("/");
            return dir.substring(0, pos);
        }
    }

    @PostMapping(value = "/uploadByte")
    @ApiOperation("文件上传<数组>")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @ResponseBody
    @OpenAPI
    public ResponseMessage<String> upload(
            @RequestBody byte[] file,
            @RequestParam("fileName") String fileName,
            @RequestParam("ext") String ext) throws Exception {
        FastDFSFile fastfile = new FastDFSFile(fileName, file, ext);
        try {
            String path = cloudfileService.uploadByte(fastfile);
            return ResponseMessage.ok(path);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    @PostMapping(value = "/uploadBytes")
    @ApiOperation("文件上传(Map<String, byte[]>)")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @ResponseBody
    @OpenAPI
    public ResponseMessage<Map<String, String>> upload(
            @RequestBody Map<String, byte[]> files,
            @RequestParam("fileName") String fileName,
            @RequestParam("ext") String ext) throws Exception {
        Map<String, String> result = new HashMap<>();
        try {
            for (String s : files.keySet()) {
                FastDFSFile fastfile = new FastDFSFile(fileName + RandomUtil.uuid(), files.get(s), ext);
                try {
                    String path = cloudfileService.uploadByte(fastfile);
                    result.put(s, path);
                } catch (Exception e) {
                    result.put("s", "");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败!");
        }
        return ResponseMessage.ok(result);
    }

    @ApiOperation("根据ID进行文件更新")
    @HystrixCommand
    @PostMapping("/update")
    @OpenAPI
    public ResponseMessage<Integer> update(@RequestBody CloudfileEntity cloudfileEntity) {
        return ResponseMessage.ok(cloudfileService.updateByPk(cloudfileEntity.getId(), cloudfileEntity));
    }

    @ApiOperation("根据ID进行文件删除")
    @HystrixCommand
    @PostMapping("/delete")
    @OpenAPI
    public ResponseMessage<Integer> delete(@RequestParam(value = "id") String id) {
        return ResponseMessage.ok(cloudfileService.deleteByPk(id));
    }

    @ApiOperation("创建文件夹")
    @HystrixCommand
    @PostMapping("/mkdir")
    @OpenAPI
    public ResponseMessage<String> mkdir(@RequestParam("jsbh") String jsbh, @RequestParam("userid") String userid, @RequestParam(value = "fdir", required = false) String fdir, @RequestParam("dir") String dir) {
        return ResponseMessage.ok(cloudfileService.mkdir(jsbh, userid, fdir, dir));
    }

    @Override
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody CloudfileEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<CloudfileEntity> getByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.getByPrimaryKey(id);
    }

    @Override
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody CloudfileEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody CloudfileEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }


}
