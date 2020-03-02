package awd.mis.servers.message;

import awd.mis.servers.config.RocketProperties;
import com.alibaba.fastjson.JSONObject;
import com.chinaoly.sjzyfwpt.SjzyfwServerUtil;
import com.chinaoly.sjzyfwpt.rockemq.consumer.RocketMQConsumerPull;
import com.chinaoly.sjzyfwpt.util.ServiceResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class ReceviceMessage extends RocketMQConsumerPull {

    @Autowired
    private RocketProperties rocketProperties;

    /**
     * 数据订阅服务调用
     *
     * @return
     */
    public ServiceResult<?> getDataForSubscribePull() {
        //当前类的实例,当前类需要继承RocketMQConsumerPull类,重写receiveData方法
        ReceviceMessage app = new ReceviceMessage();
        //数据筛选条件,若没有params传null
        Map<String, String> params = new HashMap<>();
        params.put("tag", "*");
        ServiceResult<?> result = SjzyfwServerUtil.getDataForSubscribePull(app, rocketProperties.getUrl(), params, rocketProperties.getSecretKeyMsg(), rocketProperties.getAccessKeyMsg());
        return result;
    }

    @Override
    public void receiveData(List<MessageExt> string) {
        try {
            for (MessageExt messageExt : string) {
                System.out.println("接收到消息:" + new String(messageExt.getBody(), "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}