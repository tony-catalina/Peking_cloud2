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
import awd.mis.servers.service.MysqlService;
import awd.mis.servers.utils.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用来读取mysql数据库的数据信息
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/mysql")
public class MysqlController {
	
	@Autowired
	private MysqlService mysqlService;
	
	@Autowired
	private FileUtil file;
	
	@OpenAPI
	@GetMapping("/mysql_test")
	@HystrixCommand
	@ApiOperation("就是测试各种方法的接口")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<String> test() {
		mysqlService.readFtpFileToEntity();
		return ResponseMessage.ok();
	}
	
	@OpenAPI
	@PostMapping("/uploadJsonToFtp")
	@HystrixCommand
	@ApiOperation("把传入的数据上传到ftp服务器，成功返回ftp 文件路径")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> uploadJsonToFtp(@RequestParam("json") String jsonString) {
		return ResponseMessage.ok(mysqlService.uploadJsonToFtp(jsonString));
	}
	
	@OpenAPI
	@PostMapping("/readFtpFileToEntity")
	@HystrixCommand
	@ApiOperation("调用定时服务的方法，把ftp中的数据 保存到oracle 数据库中")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> readFtpFileToEntity() {
		return ResponseMessage.ok(mysqlService.readFtpFileToEntity());
	}
	
	@OpenAPI
	@GetMapping("/mysql_makedir")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<String> makedir() {
		file.makeDir();
		return ResponseMessage.ok();
	}
	
	@OpenAPI
	@GetMapping("/mysql_uploadFTP")
	@ApiOperation("测试从 mysql 数据库获取 对象 转成_mysql.json文件 存到 ftp 服务器")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> uploadFTP(@RequestParam("yyid")String yyid) {
		mysqlService.uploadFileToFtp(yyid);
		return ResponseMessage.ok();
	}
	
	@OpenAPI
	@GetMapping("/mysql_downloadFTP")
	@ApiOperation("测试从ftp 服务器 的 oracle 文件夹 获取 _oracle.json文件 ，转换成 java对象 更新 mysql 数据库")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<?> downloadFTP() {
		mysqlService.getJsonFileByFtp();
		return ResponseMessage.ok();
	}
	
	
	
}
