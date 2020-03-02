package awd.cloud.platform.webs.charts.controller;

import awd.cloud.platform.webs.charts.api.AwdApi;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    private AwdApi awdApi;

    @RequestMapping(value="/getuser",method = RequestMethod.GET)
    public ResponseMessage<User>  getUser(){
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return awdApi.getUserByName(users.getJsbh(),users.getName());
    }

    @RequestMapping(value="/getusers",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUsers(HttpServletRequest request){
        String jsbh  = request.getParameter("jsbh");
        System.err.println(jsbh);
        Map<String,Object> map=new HashMap<>();
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if("110000000".equals(users.getJsbh())){
            System.err.println(users.getJsbh()+"===");
            map.put("jsbh",jsbh);
            map.put("yjsbh", users.getJsbh());
        }else{
            map.put("jsbh",users.getJsbh());
        }
        
        return  map;
}
}
