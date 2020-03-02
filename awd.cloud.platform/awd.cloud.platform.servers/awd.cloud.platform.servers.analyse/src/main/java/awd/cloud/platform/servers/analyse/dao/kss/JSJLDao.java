package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-12 15:53
 * Description：<描述>
 */
//今日监所警力部署

public interface JSJLDao extends Dao {

    @UseDataSource("kss_ds")
    ArrayList<Map<String,Object>> select_jSJL_sld(String jsbh);//所领导
    @UseDataSource("kss_ds")
    ArrayList<Map<String,Object>> select_jSJL_zbrs(String jsbh);//值班人数
    @UseDataSource("kss_ds")
    ArrayList<Map<String,Object>> select_jSJL_sjh(@Param("jsbh") String jsbh,@Param("xm") String xm);//手机号

    //2 jls
    @UseDataSource("jls_ds")
    ArrayList<Map<String,Object>> select_jls_jSJL_sld(String jsbh);//所领导
    @UseDataSource("jls_ds")
    ArrayList<Map<String,Object>> select_jls_jSJL_sjh(@Param("jsbh") String jsbh,@Param("xm") String xm);//手机号



}
