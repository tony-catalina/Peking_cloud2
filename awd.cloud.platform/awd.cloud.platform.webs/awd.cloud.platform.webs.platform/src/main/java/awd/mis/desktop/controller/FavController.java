package awd.mis.desktop.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.service.FavService;


@RestController
public class FavController {
	@Autowired
	private FavService favService;
	/**
	 * 添加文件夹
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/fav/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap add(HttpServletRequest request) {
		String path=request.getParameter("path");
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData("操作成功");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 添加到收藏夹
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/fav/edit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap edit(HttpServletRequest request) {
		String name=request.getParameter("");
		String name_to=request.getParameter("sad");
		String path_to=request.getParameter("das");
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData("操作成功");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 获取信息
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/fav/get", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap get(HttpServletRequest request) {
		ResultMap result=new ResultMap();
		
		result.setCode("true");
		
		result.setData(favService.get());
		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());
		
		return result;
	}
	/**
	 * 删除文件夹
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/fav/del", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap del(HttpServletRequest request) {
		ResultMap result=new ResultMap();
		String name = request.getParameter("新建文件夹");
		result.setCode("true");
		
		result.setData("操作成功！");
		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());
		
		return result;
	}
	
}
