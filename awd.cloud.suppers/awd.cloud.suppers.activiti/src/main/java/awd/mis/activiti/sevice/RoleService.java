package awd.mis.activiti.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import awd.mis.activiti.entity.RoleEntity;
import awd.mis.activiti.mapper.RoleMapper;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	public List<RoleEntity> listRole(){
		return roleMapper.listRole();
	}
	public List<RoleEntity> listRoleByJSLX(String jslx){
		return roleMapper.listRoleByJSLX(jslx);
	}
	public List<RoleEntity> listJSLX(){
		return roleMapper.listJSLX();
	}
}
