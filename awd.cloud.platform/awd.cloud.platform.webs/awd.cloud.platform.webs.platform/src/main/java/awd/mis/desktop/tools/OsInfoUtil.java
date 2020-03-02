package awd.mis.desktop.tools;

import java.util.HashMap;
import java.util.Map;

public class OsInfoUtil {
	
	private  Map<String, String>  infokey=new HashMap<>();
	
	
	public OsInfoUtil() {		
		infokey.put("java.version", "Java ：运行时环境版本");
		infokey.put("java.vendor","Java ：运行时环境供应商");
		infokey.put("java.vendor.url","Java 供应商的 URL");
		infokey.put("java.home","Java ：安装目录");
		infokey.put("java.vm.specification.version","Java 虚拟机规范版本");
		infokey.put("java.vm.specification.vendor","Java 虚拟机规范供应商");
		infokey.put("java.vm.specification.name","Java 虚拟机规范名称");
		infokey.put("java.vm.version","Java 虚拟机实现版本");
		infokey.put("java.vm.vendor","Java 虚拟机实现供应商");
		infokey.put("java.vm.name","Java 虚拟机实现名称");
		infokey.put("java.specification.version","Java 运行时环境规范版本");
		infokey.put("java.specification.vendor","Java 运行时环境规范供应商");
		infokey.put("java.specification.name","Java 运行时环境规范名称");
		infokey.put("java.class.version","Java 类格式版本号");
		infokey.put("java.class.path","Java 类路径");
		infokey.put("java.library.path","加载库时搜索的路径列表");
		infokey.put("java.io.tmpdir","默认的临时文件路径");
		infokey.put("java.compiler","要使用的 JIT 编译器的名称");
		infokey.put("java.ext.dirs","一个或多个扩展目录的路径");
		infokey.put("os.name","操作系统的名称");
		infokey.put("os.arch","操作系统的架构");
		infokey.put("os.version","操作系统的版本");
		infokey.put("file.separator","文件分隔符（在 UNIX 系统中是“/”）");
		infokey.put("path.separator","路径分隔符（在 UNIX 系统中是“:”）");
		infokey.put("line.separator","行分隔符（在 UNIX 系统中是“/n”）");
		infokey.put("user.name","用户的账户名称");
		infokey.put("user.home","用户的主目录");
		infokey.put("user.dir","用户的当前工作目录");
	}




	public  Map<String, String> getInfokey() {
		return infokey;
	}




	public  void setInfokey(Map<String, String> infokey) {
		this.infokey = infokey;
	}	
	
	

}
