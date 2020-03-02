package awd.cloud.platform.webs.charts.controller;

import awd.cloud.platform.webs.charts.utils.CacheUtils;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.JsbhUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class WelcomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (JsbhUtil.isZOND(users.getJsbh())) {
            return "zd/index";
        }
        if (JsbhUtil.isKss(users.getJsbh())) {
            return "kss/index";
        }
        if (JsbhUtil.isJls(users.getJsbh())) {
            return "jls/index";
        }
        if (JsbhUtil.isJds(users.getJsbh())) {
            return "jds/index";
        }
        if (JsbhUtil.isSjs(users.getJsbh())) {
            return "sjs/index";
        }
        if (JsbhUtil.isAkyy(users.getJsbh())) {
            return "akyy/index";
        }
        return "zd/index";
    }
    
    @RequestMapping(value = "/new.html", method = RequestMethod.GET)
    public String show() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/new";
    }
    @RequestMapping(value = "/ryjcxx.html", method = RequestMethod.GET)
    public String ryjcxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/ryxxyp/ryjcxx";
    }
    @RequestMapping(value = "/ryrsxx.html", method = RequestMethod.GET)
    public String ryrsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/ryxxyp/ryrsxx";
    }
    @RequestMapping(value = "/rygyxx.html", method = RequestMethod.GET)
    public String rygyxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/ryxxyp/rygyxx";
    }
    @RequestMapping(value = "/ajxbxx.html", method = RequestMethod.GET)
    public String ajxbxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/ajxxyp/ajxbxx";
    }
    @RequestMapping(value = "/rymjypxx.html", method = RequestMethod.GET)
    public String rymjypxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/mjypxx/rymjypxx";
    }
    @RequestMapping(value = "/ryfxxx.html", method = RequestMethod.GET)
    public String ryfxxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/fxqkyp/ryfxxx";
    }

    @RequestMapping(value = "/rycsxx.html", method = RequestMethod.GET)
    public String rycsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "zd/ryxxyp/rycsxx";
    }
    @RequestMapping(value = "/kssRyxx.html", method = RequestMethod.GET)
    public String kssRyxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/kssRyxx";
    }
    @RequestMapping(value = "/kssRyjcxx.html", method = RequestMethod.GET)
    public String kssRyjcxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/ryxxyp/kssRyjcxx";
    }
    @RequestMapping(value = "/kssRyrsxx.html", method = RequestMethod.GET)
    public String kssRyrsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/ryxxyp/kssRyrsxx";
    }
    @RequestMapping(value = "/kssRygyxx.html", method = RequestMethod.GET)
    public String kssRygyxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/ryxxyp/kssRygyxx";
    }
    @RequestMapping(value = "/kssRycsxx.html", method = RequestMethod.GET)
    public String kssRycsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/ryxxyp/kssRycsxx";
    }
    @RequestMapping(value = "/kssRyxbxx.html", method = RequestMethod.GET)
    public String kssRyxbxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/ajxxyp/kssRyxbxx";
    }
    @RequestMapping(value = "/kssRymjypxx.html", method = RequestMethod.GET)
    public String kssRymjypxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/mjypxx/kssRymjypxx";
    }
    @RequestMapping(value = "/kssRyfxxx.html", method = RequestMethod.GET)
    public String kssRyfxxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "kss/fxqkyp/kssRyfxxx";
    }


    @RequestMapping(value = "/jlsRyxx.html", method = RequestMethod.GET)
    public String jlsRyxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/jlsRyxx";
    }
    @RequestMapping(value = "/jlsRyjcxx.html", method = RequestMethod.GET)
    public String jlsRyjcxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/ryxxyp/jlsRyjcxx";
    }
    @RequestMapping(value = "/jlsRyrsxx.html", method = RequestMethod.GET)
    public String jlsRyrsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/ryxxyp/jlsRyrsxx";
    }
    @RequestMapping(value = "/jlsRygyxx.html", method = RequestMethod.GET)
    public String jlsRygyxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/ryxxyp/jlsRygyxx";
    }
    @RequestMapping(value = "/jlsRycsxx.html", method = RequestMethod.GET)
    public String jlsRycsxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/ryxxyp/jlsRycsxx";
    }
    @RequestMapping(value = "/jlsRyxbxx.html", method = RequestMethod.GET)
    public String jlsRyxbxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/ajxxyp/jlsRyxbxx";
    }
    @RequestMapping(value = "/jlsRymjypxx.html", method = RequestMethod.GET)
    public String jlsRymjypxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/mjypxx/jlsRymjypxx";
    }
    @RequestMapping(value = "/jlsRyfxxx.html", method = RequestMethod.GET)
    public String jlsRyfxxx() {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "jls/fxqkyp/jlsRyfxxx";
    }

    @RequestMapping(value = "/bj_jwp/timeTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List> timeTask(HttpServletRequest request) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.println("timeTask");
        Map<String, List> result = new HashMap<>();
        List<String> set = CacheUtils.get().getTempMessage(users);
        //set.add("查询成功");
        result.put("jieguo",set);
        System.err.println(result.toString());
        return result;
    }

    @RequestMapping(value = "/bj_dp/readRedis", method = RequestMethod.GET)
    @ResponseBody
    public String readRedis(String jsbh,String loginid) {
        String a="110000114哈哈哈哈";
        List<String> jsonObjectList=CacheUtils.get().getInfoListBykey(a);
        System.out.println(jsonObjectList);
        return null;
    }

}
