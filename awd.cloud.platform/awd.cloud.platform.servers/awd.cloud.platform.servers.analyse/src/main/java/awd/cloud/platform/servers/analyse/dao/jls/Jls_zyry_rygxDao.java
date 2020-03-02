package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;


public interface Jls_zyry_rygxDao extends Dao {

    //本人照片地址
    ArrayList<HashMap<String,Object>> selectBr(String rybh);

    //父子
    ArrayList<HashMap<String,Object>>selectFz(String rybh);


    //主管民警
    ArrayList<HashMap<String,Object>>selectzg(@Param("jsbh") String jsbh,   @Param("jsh") String jsh);
    //协管民警
    ArrayList<HashMap<String,Object>>selectxg(@Param("jsbh") String jsbh,   @Param("jsh") String jsh);

    //同案人
    ArrayList<HashMap<String,Object>>selectTar( String rybh);

    //同监
    ArrayList<HashMap<String,Object>>selectTj( @Param("rybh") String rybh);

   //风险等级
    ArrayList<HashMap<String, Object>> selectFxdj(String rybh);
}
