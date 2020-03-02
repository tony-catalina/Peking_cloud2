package awd.mis.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.tools.ResponseMessage;


@Service("authorService")
public class AuthorService {
	@Autowired
	private  ManagerService managerService;
	@Autowired
	private LogsService  logsService;

	public UserinfoModel getme() {
		return null;
	}


	public Boolean changePassword(User currentUser, String password_now, String password_new) {
		User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserinfoModel userinfo=users.getUserinfo();
		userinfo.setLoginpass(password_new);
		ResponseMessage<String> respone=managerService.UserInfoSaveOrUpdate(userinfo);
		if(respone.getStatus()==200) {
			return true;
		}
		return false;
	}

}
