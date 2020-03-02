package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index.html")
    public String homePage(Model model, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/index2.html")
    public String index2(Model model, HttpServletRequest request) {
        return "index2";
    }

    /**
     * 消息类型
     */
    @RequestMapping("/message/messageType.html")
    public String messageType(Model model, HttpServletRequest request) {
        return "message/messageType";
    }

    @RequestMapping("/message/addmessageType.html")
    public String addmessageType(Model model, HttpServletRequest request) {
        return "message/addmessageType";
    }

    /**
     * 交换机管理
     */
    @RequestMapping("/exchanges/exchanges.html")
    public String exchanges(Model model, HttpServletRequest request) {
        return "exchanges/exchanges";
    }

    @RequestMapping("/exchanges/exchange.html")
    public String exchange(Model model, HttpServletRequest request) {
        return "exchanges/exchange";
    }

    @RequestMapping("/exchanges/exchangeadd.html")
    public String exchangeadd(Model model, HttpServletRequest request) {
        return "exchanges/exchangeadd";
    }


    /**
     * 队列管理
     */
    @RequestMapping("/queues/queues.html")
    public String queues(Model model, HttpServletRequest request) {
        return "queues/queues";
    }

    @RequestMapping("/queues/queueadd.html")
    public String queue(Model model, HttpServletRequest request) {
        return "queues/queueadd";
    }

    /**
     * 消息用户管理
     */
    @RequestMapping("/rabbitUsers/rabbitUsers.html")
    public String rabbitUsers_rabbitUsers(Model model, HttpServletRequest request) {
        return "rabbitUsers/rabbitUsers";
    }

    @RequestMapping("/rabbitUsers/updateRabbitUser.html")
    public String rabbitUsers_updateRabbitUser(Model model, HttpServletRequest request) {
        return "rabbitUsers/updateRabbitUser";
    }

    @RequestMapping("/rabbitUsers/addUserAndQueue.html")
    public String rabbitUsers_addUserAndQueue(Model model, HttpServletRequest request) {
        return "rabbitUsers/addUserAndQueue";
    }

    @RequestMapping("/rabbitUsers/rabbitUserBinding.html")
    public String rabbitUsers_rabbitUserBinding(Model model, HttpServletRequest request) {
        return "rabbitUsers/rabbitUserBinding";
    }


    /**
     * 第三方消息队列
     */
    @RequestMapping("/appmsg/appQueueList.html")
    public String appmsg_queueList(Model model, HttpServletRequest request) {
        return "appmsg/appQueueList";
    }

    @RequestMapping("/appmsg/addAppQueue.html")
    public String appmsg_addAppQueues(Model model, HttpServletRequest request) {
        return "appmsg/addAppQueue";
    }


    /**
     * 安威德消息队列
     */
    @RequestMapping("/awdmsg/awdQueueList.html")
    public String awdmsg_queueList(Model model, HttpServletRequest request) {
        return "awdmsg/awdQueueList";
    }

    /**
     * 消息订阅页面
     */
    @RequestMapping("/subscript/messagebinding.html")
    public String subscript_messagebinding(Model model, HttpServletRequest request) {
        return "subscript/messagebinding";
    }

    @RequestMapping("/subscript/bindingGroup.html")
    public String subscript_bindingGroup(Model model, HttpServletRequest request) {
        return "subscript/bindingGroup";
    }

    @RequestMapping("/subscript/messageType.html")
    public String subscript_messageType(Model model, HttpServletRequest request) {
        return "subscript/messageType";
    }

    @RequestMapping(value = {"/error/404","/error/404.html"})
    public String notFound() {
        return "error/404";
    }

    @RequestMapping(value = {"/error/401","/error/401.html"})
    public String enable() {
        return "error/401";
    }

}
