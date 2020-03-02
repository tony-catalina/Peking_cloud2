package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.mis.servers.entity.YyEntity;
import awd.mis.servers.service.OracleService;
import awd.mis.servers.service.YyService;
import awd.mis.servers.utils.FileUtil;

@Service
public class OracleServiceImpl implements OracleService {

	@Autowired
	private YyService yyService;
	
	@Autowired
	private FileUtil file;
	
	@Override
	public void uploadFileToFtp() {
		System.err.println("上传服务文件");
		//定义动态查询的查询实体
		QueryParamEntity oracleQuery = new QueryParamEntity();
		oracleQuery.setPaging(false);	//设置不分页了，查询全部数据
		oracleQuery.setPageIndex(0);
		oracleQuery.setPageSize(1000);
		// 接下来是查询条件，我想要查询的数据，最终查询条件可能会有变更，目前使用 测试的表模型
		oracleQuery.and("state", TermType.eq, "R2")	//state 为R2
			.and("shzt", TermType.notnull, "null") //查询shzt 不为null的
			//.and("shzt", TermType.eq, "")	//这个条件暂时 没考虑到
			;
		
		List<YyEntity> yyList = yyService.selectByOracle(oracleQuery);
		
		String jsonString = JSON.toJSONString(yyList);
		long timestamp = new Date().getTime();
		String fileName = String.valueOf(timestamp) + "_oracle.json";
		//file.makeDir();
		file.createOracleFile(fileName,jsonString);

	}
	
	@Override
	public Map<String, Object> uploadJsonToFtp(String jsonString) {
		System.err.println("把传入的数据上传到ftp服务器");
		
		long timestamp = new Date().getTime();
		String fileName = String.valueOf(timestamp) + "_oracle.json";
		//file.makeDir();
		Map<String, Object> result  = file.createOracleFile(fileName,jsonString);
		Map<String, Object> retMap = Maps.newHashMap();
		
		if ((boolean) result.get("flag")) {
			retMap.put("code", 200); 
			retMap.put("msg", "数据已存入ftp服务器，文件为："+result.get("path"));
		}else {
			retMap.put("code", 500); 
			retMap.put("msg", "数据未正确存入ftp服务器");
		}
		
		return retMap;
	}
	
	/**
	 * 从ftp服务器 下载文件到本地，并返回文件路径
	 */
	@Override
	public String downLoadFileByFtp() {
		//下载文件到本地，本地有文件就返回
		String localFilePath = file.downLoadFileByFtp();
		return localFilePath;
	}
	
	/**
	 * 从本地文件读取 数据，并转换成 List<YyEntity> 对象
	 */
	@Override
	public void getJsonFileByFtp() {
		//把文件读取成string数据,是list<object> 类型的
		String localFilePath = downLoadFileByFtp();
		List<YyEntity> yyList = new ArrayList<YyEntity>(0);
		if (localFilePath != "") {
			try {
				yyList = file.getJsonObjectList(localFilePath,YyEntity.class);
				for (YyEntity yyEntity : yyList) {
					yyService.saveOrUpdate(yyEntity);
					//yyService.updateByPk(yyEntity.getId(), yyEntity);
				}
			} catch (Exception e) {
				System.err.println("there are something wrong with saveOrUpdate List<YyEntity> !");
				System.err.println(e);
			}
		}else {
			System.err.println("not read file! because the filepath is empty!");
		}
	}
	
	/**
	 * 从ftp文件夹中读取多条。json文件，然后转乘list
	 */
	public String readFtpFileToEntity() {
		List<String> jsonObjList = file.getJsonObjList();
		String outmsg = "";
		for (String jsonStr : jsonObjList) {
			try {
				YyEntity yyEntity = file.jsonStrToBean(jsonStr, YyEntity.class);
				yyEntity.setId("");
				try {
					yyService.insert(yyEntity);
				} catch (Exception e) {
					String msg = "预约id为 "+yyEntity.getYyid()+" 的数据，保存失败！  ";
					outmsg = outmsg + msg;
				}
			} catch (Exception e) {
				System.err.println("这个 文件 转换bean失败啦！");
			}
		}
		System.err.println(outmsg);
		return outmsg;
	}
}
