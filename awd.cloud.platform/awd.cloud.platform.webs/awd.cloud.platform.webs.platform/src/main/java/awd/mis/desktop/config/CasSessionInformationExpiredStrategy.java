package awd.mis.desktop.config;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.LoginlogsModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.TermType;

@Component
public class CasSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Autowired
	private LogsService logsService;

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		 HttpServletResponse response = event.getResponse();
		 HttpServletRequest request = event.getRequest();
		 User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			QueryParam param=new QueryParam();
			param.setPaging(false);
			param.and("jsbh", TermType.eq, currentUser.getJsbh())
			.and("loginname", TermType.eq, currentUser.getName())
			.and("logintime", TermType.notnull)
			.and("logouttime", TermType.isnull);
			ResponseMessage<PagerResult<LoginlogsModel>> respone=logsService.loginlogsList(param);
			if(respone!=null&&respone.getResult()!=null) {
				List<LoginlogsModel> list=respone.getResult().getData();
				for (LoginlogsModel loginlogsModel : list) {
					logsService.loginout(loginlogsModel.getId());
				}
			}
		 System.err.println("----------------------------------session 过期 回到登录页面-------------------------------------");
		 response.sendRedirect("/user/logout");
	}

}
