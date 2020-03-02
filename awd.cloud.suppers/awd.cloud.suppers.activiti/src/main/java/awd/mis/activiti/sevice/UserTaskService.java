package awd.mis.activiti.sevice;

import javacommon.xsqlbuilder.XsqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import awd.mis.activiti.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by watchdb on 2018/1/1.
 */
@Service
public class UserTaskService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void executeFlowTransaction(String sw,Map hm) throws Exception {
        if(StringUtil.isNullOrEmpty(sw)){
            return;
        }
        String[] arr = sw.split(";");
        for (int i = 0; i < arr.length; i++) {
            if (!StringUtil.isNullOrEmpty(arr[i]) && arr[i].length() > 10
                    && arr[i].toUpperCase().indexOf("DELETE") <= 0
                    && arr[i].toUpperCase().indexOf("TRANCATE") <= 0) {
                XsqlBuilder builder = new XsqlBuilder();
                String sql =  builder.generateHql(arr[i],hm).getXsql();
                //System.err.println("@@@@参数集合"+hm.toString());
                System.err.println("@@@@要执行的SQL语句" + sql);
                jdbcTemplate.execute(sql);
            }
        }
    }
    
    /*public static void main(String[] args) {
    	String sw ="update  soa_kss.lshj set  pastable = '02' and updatetime =sysdate /~ and fzmj = {fzmj} ~/ /~ and updator={updator} ~/ where pastable = '01' and /~ rybh = {rybh} ~/   ;update  soa_kss.jbxx set CRJBJ = '01' where /~ rybh = {rybh} ~/";
    	Map<String,String> hm =new HashMap<>();
    	hm.put("rybh", "310001111201801200021");
    	hm.put("jsbh", "310000111");
    	hm.put("fzmj", "xxxxx");
    	hm.put("updator", "updator");
    	try {
    		  String[] arr = sw.split(";");
    	        for (int i = 0; i < arr.length; i++) {
    	            if (!StringUtil.isNullOrEmpty(arr[i]) && arr[i].length() > 10
    	                    && arr[i].toUpperCase().indexOf("DELETE") <= 0
    	                    && arr[i].toUpperCase().indexOf("TRANCATE") <= 0) {
    	                XsqlBuilder builder = new XsqlBuilder();
    	                String sql =  builder.generateHql(arr[i],hm).getXsql();

    	                System.err.println("----" + sql);
    	                //jdbcTemplate.execute(sql);
    	            }
    	        }
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
}
