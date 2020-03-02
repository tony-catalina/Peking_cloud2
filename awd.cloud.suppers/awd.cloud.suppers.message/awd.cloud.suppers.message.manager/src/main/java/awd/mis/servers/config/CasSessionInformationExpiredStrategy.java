package awd.mis.servers.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.api.LogsServiceApi;
import awd.mis.servers.vo.LoginlogsModel;
import awd.mis.servers.vo.User;


@Component
public class CasSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Autowired
	private LogsServiceApi logsService;

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();
		HttpServletRequest request = event.getRequest();
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		QueryParam param=new QueryParam();
		param.setPaging(false);
		param.and("jsbh", TermType.eq, currentUser.getJsbh())
		.and("loginname", TermType.eq, currentUser.getUsername())
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
