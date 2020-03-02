package awd.mis.activiti.mapper;

import org.apache.ibatis.annotations.*;

import awd.mis.activiti.entity.FlowNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/8/13 15:46
 *
 * @description:
 **/
@Mapper
public interface FlowNodeMapper {

	@Select("SELECT * FROM Flownode WHERE ID = #{id}")
    FlowNode selectById(@Param("id") String id);
	
    @Select("SELECT * FROM Flownode WHERE ID = #{id}")
    List<FlowNode> select(@Param("id") String id);
    
    @Select("SELECT * FROM Flownode ")
    List<FlowNode> selectAll();
    
    @Select("SELECT * FROM Flownode WHERE flowmapid = #{flowmapid}")
    List<FlowNode> selectByFlowMapId(@Param("flowmapid") String flowmapid);
    
    @Select("SELECT * FROM Flownode WHERE flowmapid = #{flowmapid} and nodecode = #{nodecode}")
    FlowNode selectByFlowMapIdAndNodeCode(@Param("flowmapid") String flowmapid,@Param("nodecode") String nodecode);
    @Insert("insert into Flownode"
    		+ "(ID ,JSBH ,NODECODE ,NODENAME ,FLOWMAPID ,JDLX ,ROLE ,MEMO ,CREATOR ,CREATETIME ,UPDATOR ,UPDATETIME ,MENU ) "
    		+ "values (#{id,jdbcType=VARCHAR},"
    		+ "#{jsbh,jdbcType=VARCHAR},"
    		+ "#{nodecode,jdbcType=VARCHAR},"
    		+ "#{nodename,jdbcType=VARCHAR},"
    		+ "#{flowmapid,jdbcType=VARCHAR},"
    		+ "#{jdlx,jdbcType=VARCHAR},"
    		+ "#{role,jdbcType=VARCHAR},"
    		+ "#{memo,jdbcType=VARCHAR},"
    		+ "#{creator,jdbcType=VARCHAR},"
    		+ "#{createtime,jdbcType=TIMESTAMP},"
    		+ "#{updator,jdbcType=VARCHAR},"
    		+ "#{updatetime,jdbcType=TIMESTAMP},"
    		+ "#{menu,jdbcType=VARCHAR})")
    int insert(FlowNode flowNode);

    @Update(
    		"UPDATE Flownode "
    		+ "SET"
    		+ " ID = #{id ,jdbcType=VARCHAR} ,"
    		+ "JSBH = #{jsbh ,jdbcType=VARCHAR} ,"
    		+ "NODECODE = #{nodecode ,jdbcType=VARCHAR} ,"
    		+ "NODENAME = #{nodename ,jdbcType=VARCHAR} ,"
    		+ "FLOWMAPID = #{flowmapid ,jdbcType=VARCHAR} ,"
    		+ "JDLX = #{jdlx ,jdbcType=VARCHAR} ,"
    		+ "ROLE = #{role ,jdbcType=VARCHAR} ,"
    		+ "MEMO = #{memo ,jdbcType=VARCHAR} ,"
    		+ "CREATOR = #{creator ,jdbcType=VARCHAR} ,"
    		+ "CREATETIME = #{createtime ,jdbcType=TIMESTAMP} ,"
    		+ "UPDATOR = #{updator ,jdbcType=VARCHAR} ,"
    		+ "UPDATETIME = #{updatetime ,jdbcType=TIMESTAMP} ,"
    		+ "MENU = #{menu ,jdbcType=VARCHAR} WHERE ID = #{id}")
    void update (FlowNode flowNode);
    @Update(
    		"UPDATE Flownode "
    		+ "SET"
    		+ " ID = #{id ,jdbcType=VARCHAR} ,"
    		+ "JSBH = #{jsbh ,jdbcType=VARCHAR} ,"
    		+ "NODECODE = #{nodecode ,jdbcType=VARCHAR} ,"
    		+ "NODENAME = #{nodename ,jdbcType=VARCHAR} ,"
    		+ "FLOWMAPID = #{flowmapid ,jdbcType=VARCHAR} ,"
    		+ "JDLX = #{jdlx ,jdbcType=VARCHAR} ,"
    		+ "ROLE = #{role ,jdbcType=VARCHAR} ,"
    		+ "MEMO = #{memo ,jdbcType=VARCHAR} ,"
    		+ "CREATOR = #{creator ,jdbcType=VARCHAR} ,"
    		+ "CREATETIME = #{createtime ,jdbcType=TIMESTAMP} ,"
    		+ "UPDATOR = #{updator ,jdbcType=VARCHAR} ,"
    		+ "UPDATETIME = #{updatetime ,jdbcType=TIMESTAMP} ,"
    		+ "MENU = #{menu ,jdbcType=VARCHAR} WHERE NODECODE = #{nodecode}")
    void updateByCODE (FlowNode flowNode);
    @Delete("DELETE FROM Flownode WHERE ID = #{id}")
    int delete(String id);
    
    @Select("SELECT * FROM Flownode WHERE nodecode = #{nodecode}")
    FlowNode selectNodeByCode(@Param("nodecode") String nodecode);
    
    @Delete("DELETE FROM Flownode WHERE FLOWMAPID = #{id}")
    int deleteForMapId(String id);
   /* ON DUPLICATE KEY UPDATE
    int saveOrUpdate()*/
}
