package awd.mis.desktop.controller;

import awd.framework.common.utils.StringUtils;

import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.DictionaryModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.tools.*;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典服务
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/kss_dictionary")
@RefreshScope
public class DictionaryController {
    @Autowired
    private ManagerService managerService;

    /**
     * 下拉框数据获取
     *
     * @param node
     * @return
     */
    @PostMapping("/getDictionary")
    @Cacheable(cacheNames = "web_cache_dictionary", key = "'web_cache_dictionary:kss:'+#node")
    public List<DictionaryModel> getDictionary(@RequestParam(value = "node", required = false, defaultValue = "NULL") String node) {
        try {
            return managerService.getByField(node).getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 下拉框数据分页获取
     *
     * @param request
     * @return
     */
    @PostMapping("/getDictionaryPage")
    @ResponseBody
    @Cacheable(cacheNames = "web_cache_dictionary", key = "'web_cache_dictionary:page:'+" +
            "#request.getParameter(\"pageIndex\")+" +
            "#request.getParameter(\"pageSize\")+" +
            "#request.getParameter(\"queryParams\")+" +
            "#request.getParameter(\"fieldname\")+" +
            "#request.getParameter(\"order\")+" +
            "#request.getParameter(\"sort\")")
    public PagerResult<DictionaryModel> getDictionaryByPage(HttpServletRequest request) {
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        int index = 0;
        int size = 12;
        try {
            index = Integer.valueOf(pageIndex).intValue();
            size = Integer.valueOf(pageSize).intValue();
        } catch (NumberFormatException e) {
            System.err.println("数字转型异常");
        }
        String queryParams = request.getParameter("queryParams");
        String fieldname = request.getParameter("fieldname");

        QueryParam qParam = new QueryParam();
        qParam.and("fieldname", TermType.eq, fieldname);

        qParam.setPageIndex(index);
        qParam.setPageSize(size);

        String name = "";
        String param = "";
        if (!StringUtils.isNullOrEmpty(queryParams)) {
            if (StringUtils.isNumber(queryParams)) {
                name = "code";
                param = "%" + queryParams + "%";
            }
            if (StringUtils.containsChineseChar(queryParams)) {
                name = "content";
                param = "%" + queryParams + "%";
            }
            if (StringUtils.isEnglishChars(queryParams)) {
                name = "py";
                char[] chars = queryParams.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                param = "%" + new String(chars) + "%";
            }
        }

        qParam.and(name, TermType.like, param);
        String sortName = request.getParameter("sort");
        String orderBy = request.getParameter("order");

        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        if (sortName != null && orderBy != null) {
            sort.setName(sortName);
            sort.setOrder(orderBy);
        } else {
            sort.setName("sypl");
            sort.setOrder("desc");
        }

        sorts.add(sort);
        qParam.setSorts(sorts);

        try {
            PagerResult<DictionaryModel> pagerResult = managerService.getPageByfields(qParam).getResult();
            return pagerResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PagerResult<DictionaryModel>();
        }
    }

    @RequestMapping(value = "/getJsbhList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<JSONObject> getJsbh() {

        return CacheUtils.get().getJsbhList();
    }

    @RequestMapping(value = "/getUserList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Map<String, String>> getUserList() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        QueryParam queryParam = new QueryParam();
        List<Map<String, String>> li = new ArrayList<>();
        queryParam.and("jsbh", user.getJsbh());
        queryParam.and("state", "R2");
        ResponseMessage<PagerResult<UserinfoModel>> userInfoList = managerService.userInfoQuery(queryParam);
        if (200 == userInfoList.getStatus()) {
            if (userInfoList.getResult() != null && userInfoList.getResult().getData() != null) {
                List<UserinfoModel> users = userInfoList.getResult().getData();
                for (UserinfoModel userinfoModel : users) {
                    Map<String, String> map = Maps.newHashMap();
                    map.put("jh", userinfoModel.getJh());
                    map.put("xm", userinfoModel.getLoginname());
                    li.add(map);
                }
            }
        }
        return li;
    }
}
