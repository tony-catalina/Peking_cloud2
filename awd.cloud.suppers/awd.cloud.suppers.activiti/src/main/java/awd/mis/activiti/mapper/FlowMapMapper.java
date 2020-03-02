package awd.mis.activiti.mapper;


import org.apache.ibatis.annotations.*;

import awd.mis.activiti.entity.FlowMap;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/8/13 14:42
 *
 * @description:
 **/


@Mapper
public interface FlowMapMapper {

    @Select("SELECT * FROM FLOWMAP WHERE ID = #{id}")
    List<FlowMap> select(@Param("id") String id);
    
    @Select("SELECT * FROM FLOWMAP ")
    List<FlowMap> selectList();
    @Select("SELECT * FROM FLOWMAP WHERE MAPNAME = #{mapName}")
    FlowMap selectBymapName(@Param("mapName") String mapName);
    @Select("SELECT * FROM FLOWMAP WHERE ID = #{id}")
    FlowMap selectById(@Param("id") String id);
    @Select("SELECT * FROM FLOWMAP WHERE mapkey = #{mapkey}")
    FlowMap selectBymapkey(@Param("mapkey") String mapkey);
    @Select("SELECT * FROM FLOWMAP WHERE mapkey = #{mapkey}")
    List< FlowMap> selectByMapkey(@Param("mapkey") String mapkey);
    @Insert("INSERT INTO FLOWMAP(ID ,JSBH ,MAPNAME ,MAPKEY ,VERSION ,MAPMUTEX ,TIMELIMIT ,MONTHLYLIMIT ,MEMO ,FLAG ,CREATOR ,CREATETIME ,UPDATOR ,UPDATETIME ,MENU ) " +
            "values (#{id,jdbcType=VARCHAR},#{jsbh,jdbcType=VARCHAR},#{mapname,jdbcType=VARCHAR},#{mapkey,jdbcType=VARCHAR},#{version,jdbcType=VARCHAR},#{mapmutex,jdbcType=VARCHAR},#{timelimit,jdbcType=VARCHAR},#{monthlylimit,jdbcType=VARCHAR},#{memo,jdbcType=VARCHAR},#{flag,jdbcType=VARCHAR},#{creator,jdbcType=VARCHAR},#{createtime,jdbcType=TIMESTAMP},#{updator,jdbcType=VARCHAR},#{updatetime,jdbcType=TIMESTAMP},#{menu,jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")//加入该注解可以保持对象后，查看对象插入id
    int insert(FlowMap flowMap);

    @Update("UPDATE FLOWMAP "
    		+ "SET "
    		+ "ID = #{id ,jdbcType=VARCHAR} ,"
    		+ "JSBH = #{jsbh ,jdbcType=VARCHAR} ,"
    		+ "MAPNAME = #{mapname ,jdbcType=VARCHAR} ,"
    		+ "MAPKEY = #{mapkey ,jdbcType=VARCHAR} ,"
    		+ "VERSION = #{version ,jdbcType=VARCHAR} ,"
    		+ "MAPMUTEX = #{mapmutex ,jdbcType=VARCHAR} ,"
    		+ "TIMELIMIT = #{timelimit ,jdbcType=VARCHAR} ,"
    		+ "MONTHLYLIMIT = #{monthlylimit ,jdbcType=VARCHAR} ,"
    		+ "MEMO = #{memo ,jdbcType=VARCHAR} ,"
    		+ "FLAG = #{flag ,jdbcType=VARCHAR} ,"
    		+ "CREATOR = #{creator ,jdbcType=VARCHAR} ,"
    		+ "CREATETIME = #{createtime ,jdbcType=TIMESTAMP} ,"
    		+ "UPDATOR = #{updator ,jdbcType=VARCHAR} ,"
    		+ "UPDATETIME = #{updatetime ,jdbcType=TIMESTAMP} ,"
    		+ "MENU = #{menu ,jdbcType=VARCHAR} WHERE ID = #{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int update(FlowMap flowMap);

    @Delete("DELETE FROM FLOWMAP WHERE ID = #{id}")
    int delete(String id);
    


}
