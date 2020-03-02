package awd.mis.servers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.mis.servers.service.PublicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用来读取mysql数据库的数据信息
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private PublicService publicService;
	
	@OpenAPI
	@PostMapping("/public_test")
	@ApiOperation("上传文本内容到ftp服务器")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> test(String param) {
		return ResponseMessage.ok(publicService.test(param));
	}
	
	@OpenAPI
	@GetMapping("/public_getFtpFileByName")
	@ApiOperation("根据文件名 获取ftp服务器的public文件夹的文件内容")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public String getFtpFileByName(@RequestParam("fileName") String fileName) {
		System.err.println("getFtpFileByName-------------"+fileName);
		//return ResponseMessage.ok(publicService.getFtpFileByName(fileName));
		String content = publicService.getFtpFileByName(fileName);
		System.err.println("content=="+content);
		return content;
	}
	
	@OpenAPI
	@PostMapping("/public_uploadPulicFileToFtp")
	@ApiOperation("上传文本内容到ftp服务器")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> uploadPulicFileToFtp(@RequestParam("fileName") String fileName,@RequestParam("fileContent") String fileContent) {
		return ResponseMessage.ok(publicService.uploadPulicFileToFtp(fileName, fileContent));
	}
	
	@OpenAPI
	@PostMapping("/public_deleteFtpFolder")
	@ApiOperation("删除ftp服务器上的文件夹")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> deleteFtpFolder(String remotePath) {
		publicService.deleteFtpFolder(remotePath);
		return ResponseMessage.ok();
	}
	
	
}
