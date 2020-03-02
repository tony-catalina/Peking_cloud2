package awd.mis.servers.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import awd.framework.common.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.ManagerServersApi;
import awd.mis.servers.vo.User;

public class MessageUserDetailsService implements UserDetailsService {

    @Autowired
    private ManagerServersApi managerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        if (username != null) {
            //获取用户详细信息
            String us[] = username.split("@");
            user.setLoginname(us[0]);
            user.setJsbh(us[1]);
            Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();

            if ("000000000".equals(user.getJsbh())) {
                user.setEnabled(true);
            } else {
                user.setEnabled(false);
                user.setAuthorities(authorities);
                return user;
            }
            try {
                ResponseMessage<Map<String, Object>> respone = managerService.getUserByName(us[1], us[0]);
                if (respone != null && respone.getStatus() == 200) {
                    Map<String, String> map = (Map<String, String>) respone.getResult().get("userinfo");
                    System.err.println(JSONUtil.toJson(map));
                    User getuser = JSONUtil.toBean(JSONUtil.toJson(map), User.class);
                    System.err.println("respone.getResult()===" + JSON.toJSONString(respone.getResult().get("userinfo")));
                    System.err.println("getuser===" + JSON.toJSONString(getuser));
                    if (getuser != null) {
                        user.setId(getuser.getId());
                        user.setAuthorities(authorities);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
            AuthorityInfo authorityInfo = new AuthorityInfo("NONE");
            authorities.add(authorityInfo);
        }
        System.out.println("----------------------------------获取用户权限信息-------------------------------------------");
        System.out.println(JSONUtil.toJson(user));
        System.out.println("-----------------------------------------------------------------------------");
        return user;
    }
}

