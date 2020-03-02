package awd.mis.desktop.socket;

import awd.framework.expands.rabbitmq.send.SendMessage;
import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.MsgboxModel;
import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;



@Controller
@RequestMapping("/kss_process")
public class ProcessController {
	@Autowired
	private LogsService logsService;
	@Autowired
    private SendMessage sendMessage;
	@Autowired
    private Message message;
	@PostMapping("/send")
	@ResponseBody
	public boolean Send(@RequestParam(value = "msg") String msg){
		User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Base64 base64 = new Base64();
			MsgboxModel msgboxModel = JSONObject.parseObject(msg,MsgboxModel.class);
			String nrs = msgboxModel.getFsnr();
			byte[] context = msgboxModel.getFsnr().getBytes("utf-8");
			String nr = base64.encode(context);
			String xm = msgboxModel.getJsrxm();
			String[] xms = xm.split(",");
			String jsrjhString = msgboxModel.getJsrjh();
			String[] jsrjh = jsrjhString.split(",");
			String jsbhString = msgboxModel.getJsbh();
			String[] jsbh = jsbhString.split(",");
			for(int i=0;i<xms.length;i++) {
			msgboxModel.setJsrxm(xms[i]);
			msgboxModel.setJsrjh(jsrjh[i]);
			msgboxModel.setFssj(new Date());
			msgboxModel.setJsbh(jsbh[i]);
			msgboxModel.setState("R2");
			msgboxModel.setFsnr(nr);
			msgboxModel.setFsrxm(users.getName());
			msgboxModel.setFsrjh(users.getJh());
			msgboxModel.setCreator(users.getName());
			logsService.senderMsgOne(msgboxModel);
			if(msgboxModel.getXxjb().equals("2")) {
			for(int j=0;j<3;j++) {
				//message.sendInstancyMessage(jsbh[i], xms[i], jsrjh[i], nrs);
			}
			}
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
