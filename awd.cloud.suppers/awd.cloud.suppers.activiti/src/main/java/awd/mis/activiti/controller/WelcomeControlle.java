package awd.mis.activiti.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import awd.mis.activiti.utils.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Api(description = "页面", tags = {"pages"})
@Controller
public class WelcomeControlle {
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index1(Model model, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String process(Model model, HttpServletRequest request) {
        return "process/process";
    }

    @RequestMapping(value = "/deployedProcess", method = RequestMethod.GET)
    public String deployedProcess(Model model, HttpServletRequest request) {
        return "deployedProcess/deployedProcess";
    }

    @RequestMapping(value = "/taskProcess", method = RequestMethod.GET)
    public String index3(Model model, HttpServletRequest request) {

        String processId = request.getParameter("processId");
        model.addAttribute("processId", processId);

        return "taskProcess/taskProcess";
    }
    @RequestMapping(value = "/flowElement", method = RequestMethod.GET)
    public String flowelement(Model model, HttpServletRequest request) {
    	String processId = request.getParameter("processId");
    	System.err.println(processId);
    	model.addAttribute("processId", processId);
        return "flowelement/flowelement";
    }
    @RequestMapping(value ="/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestBody Map<String,String > map){
        System.err.println("测试----");
        return JSON.toJSONString(map);
    }

    @RequestMapping(value ="/test_03", method = RequestMethod.GET)
    @ResponseBody
    public String  test1(@RequestBody Map<String,String > map){
        System.err.println("测试----");
        String json ="{" +
                "\"code\": 0," +
                "\"info\": \"succesful\"," +
                "\"totalPages\": 1," +
                "\"totalCount\": 5," +
                "\"data\": [{" +
                "\"number\": \"111001\"," +
                "\"name\": \"张三\"," +
                "\"heartrate\": \"65\"," +
                "\"happen_time\": \"2018-06-07 12:00:00\"" +
                "}," +
                "{" +
                "\"number\": \"111001\"," +
                "\"name\": \"张三\"," +
                "\"heartrate\": \"68\"," +
                "\"happen_time\": \"2018-06-07 12:30:00\"" +
                "}" +
                "" +
                "]" +
                "}";

        return json;
    }
}
