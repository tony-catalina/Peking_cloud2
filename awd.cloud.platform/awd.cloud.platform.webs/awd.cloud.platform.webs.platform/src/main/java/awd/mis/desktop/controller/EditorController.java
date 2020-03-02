package awd.mis.desktop.controller;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.service.EditorService;
@RestController
public class EditorController {
	
	@Autowired
	private EditorService editorService;
	
	@GetMapping(value = "/editor", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap editor(HttpServletRequest request) {
		
		ResultMap result=new ResultMap();		
		result.setCode("true");		
		result.setInfo("修改已生效！");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());	
		
		return result;
	}
	/**
	 * 编辑器修改操作
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/editor/setConfig", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap setConfig(HttpServletRequest request) {
		
		String k = request.getParameter("font_size");
		
		String v = request.getParameter("13px");
		
		ResultMap result=new ResultMap();
		result.setCode("true");
		
		result.setData("修改已生效！");
		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());	
		
		return result;
	}
	/**
	 * 编辑
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/editor/fileGet", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap fileGet(HttpServletRequest request) {
		String filename = request.getParameter("/desktop/新建文件夹_project.oexe");
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData(editorService.getEditor());
		result.setUse_time(Calendar.getInstance().getTimeInMillis());	
		
		return result;
	}
	/**
	 * 保存
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/editor/fileSave", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap fileSave(HttpServletRequest request) {
		
		String path = request.getParameter("/desktop/计算器.oexe");
		String charset = request.getParameter("utf-8");
		String filestr = request.getParameter("u");
		String create_file = request.getParameter("1");
		ResultMap result=new ResultMap();	
		result.setCode("true");
		
		result.setData("保存成功");
		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());	
		
		return result;
	}
}
