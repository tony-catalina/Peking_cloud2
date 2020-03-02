package awd.mis.desktop.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awd.mis.desktop.bean.User;
import awd.mis.desktop.socket.MyWebSocket;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.model.SettingModel;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.views.GlobalVar;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Controller
public class DesktopController {

    private String appcode = "eyJ0eXBlIjoiYXBwIiwid2lkdGgiOiIiLCJoZWlnaHQiOiIiLCJjb250ZW50IjoiY29yZS5kZXNrdG9wKCk7In0=";

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/desktop", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String printWelcome(ModelMap model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.println(currentUser);
        //获取菜单配置
        ResponseMessage<List<SettingModel>> system_menu = managerService.getSettingByGroup(appcode, "SYSTEM_MENU");
        if (system_menu != null && system_menu.getStatus() == 200) {
            List<SettingModel> menulist = system_menu.getResult();
            Map<String, Object> menus_hide = new HashMap<String, Object>();
            for (SettingModel settingModel : menulist) {
                JSONObject object = JSONUtil.parseObj(settingModel.getProvalue());
                menus_hide.put(settingModel.getProname(), object.get("use"));
            }
            GlobalVar.getConfig().put("menu", menus_hide);
        }
        GlobalVar.getConfig().put("my_desktop", "\\/desktop\\/");
        String jsbh = currentUser.getJsbh();
        String jh = currentUser.getUserinfo().getJh();
        String userName = null;
        try {
            userName = Base64.getEncoder().encodeToString(currentUser.getUsername().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNullOrEmpty(jh)) {
            jh = MyWebSocket.NOJHUSERS;
        }
        //userName="";
        model.addAttribute("userJh", jsbh + "_" + userName + "_" + jh);
        return "desktop/desktop";
    }
}
