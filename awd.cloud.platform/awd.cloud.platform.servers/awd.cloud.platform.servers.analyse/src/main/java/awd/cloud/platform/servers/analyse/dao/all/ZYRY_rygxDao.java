package awd.cloud.platform.servers.analyse.dao.all;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-20 19:40
 * Description：<描述>
 */
public interface ZYRY_rygxDao extends Dao {

    //本人姓名
    ArrayList<HashMap<String,Object>> selectBr(String rybh);

    //父子
    ArrayList<HashMap<String,Object>>selectFz(String rybh);


    //主管民警
    ArrayList<HashMap<String,Object>>selectzg(@Param("jsbh") String jsbh,   @Param("jsh") String jsh);
    //协管民警
    ArrayList<HashMap<String,Object>>selectxg(@Param("jsbh") String jsbh,   @Param("jsh") String jsh);
    //民警照片
    ArrayList<HashMap<String,Object>>selectMjZp(String mjid);

    //同案犯号  查下面的同案人
    ArrayList<HashMap<String,Object>>selectTafh( String rybh);
    //同案人 姓名  和 人员编号
    ArrayList<HashMap<String,Object>>selectTar( String tabh);
    //同案人或同监 照片 根据人员编号
    ArrayList<HashMap<String,Object>>selectImg( String rybh);


    //同监姓名
    ArrayList<HashMap<String,Object>>selectTj( @Param("jsbh") String jsbh,@Param("jsh") String jsh);




}
