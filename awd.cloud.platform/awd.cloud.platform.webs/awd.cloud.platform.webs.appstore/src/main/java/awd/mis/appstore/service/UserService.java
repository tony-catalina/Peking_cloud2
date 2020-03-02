package awd.mis.appstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.model.UserinfoModel;
import awd.mis.appstore.tools.ResponseMessage;

@Service
public class UserService {
	
	@Autowired
	private ManagerService managerService;

	public UserinfoModel login(String jsbh,String name, String pwd) {
		ResponseMessage<UserinfoModel> userinfo=null;
		userinfo=managerService.findByNameAndPass(jsbh,name,pwd);
		if(userinfo.getStatus()==200&&userinfo.getResult()!=null) {					
			return userinfo.getResult();
		}else {
			return null;
		}
		
	}


}
