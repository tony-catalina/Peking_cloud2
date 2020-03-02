package awd.mis.servers.service.imp;

import awd.mis.servers.service.MsgDistribution;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author WS
 * @Description:
 * @date 2019/11/19 上午11:36
 */
@Service("msgDistribution")
public class SimpleMsgDistribution implements MsgDistribution {

    @Override
    public List<String> getMethodName() {
        String clazz = this.getClass().getName();
        Method[] method = this.getClass().getMethods();
        List<String> li = Lists.newArrayList();
        for (Method m : method) {
            if (m.getName().startsWith("send")) {
                li.add(m.getName());
                System.err.println(m.getName());
            }
        }
        return null;
    }

    public void getxx() {

    }

    public String test() {

        return "";
    }

    public String send() {

        return "";
    }

    public static void main(String[] args) {
        SimpleMsgDistribution simpleMsgDistribution = new SimpleMsgDistribution();
        simpleMsgDistribution.getMethodName();
    }
}
