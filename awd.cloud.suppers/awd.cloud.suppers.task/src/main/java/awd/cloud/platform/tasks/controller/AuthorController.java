package awd.cloud.platform.tasks.controller;

import awd.cloud.platform.tasks.api.AwdApi;
import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
