package awd.mis.activiti.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import awd.mis.activiti.entity.RoleEntity;

@Mapper
public interface RoleMapper {
	//查询所有角色
	@Select("select * from role")
	List<RoleEntity> listRole();
	//根据监所类型查询角色
	@Select("select * from role where jsls=#{jslx}")
	List<RoleEntity> listRoleByJSLX(@Param("jslx")String jslx);
	//查询所有监所类型
	@Select("select DISTINCT jslx from role")
	List<RoleEntity> listJSLX();
}
