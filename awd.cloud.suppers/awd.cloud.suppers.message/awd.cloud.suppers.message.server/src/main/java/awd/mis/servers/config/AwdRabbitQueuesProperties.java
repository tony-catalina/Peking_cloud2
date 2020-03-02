package awd.mis.servers.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

import awd.framework.common.utils.StringUtils;

/**
 * @author WS
 * @Description: rabbitMq队列配置信息
 * @date 2019/12/4 下午1:53
 */
@Configuration
@ConfigurationProperties(prefix = "message.queues", ignoreUnknownFields = false)
public class AwdRabbitQueuesProperties {

    List<QueueProperties> queuePropertiesList;

    public List<QueueProperties> getQueuePropertiesList() {
        return queuePropertiesList;
    }

    public void setQueuePropertiesList(List<QueueProperties> queuePropertiesList) {
        this.queuePropertiesList = queuePropertiesList;
    }

    public static class QueueProperties {
        String appname;
        String queue;
        String routingkey;
        String name;

		public String getAppname() {
			return appname;
		}

		public void setAppname(String appname) {
			this.appname = appname;
		}

		public String getQueue() {
            return queue;
        }

        public void setQueue(String queue) {
            this.queue = queue;
        }

        public String getRoutingkey() {
            return routingkey;
        }

        public void setRoutingkey(String routingkey) {
            this.routingkey = routingkey;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<Map<String, Object>> getAwdQueues(String appname) {
    	String appName;
    	if (StringUtils.isNullOrEmpty(appname)) {
    		appName = "";
		}else {
			appName = appname;
		}
    	List<Map<String, Object>> list = new ArrayList<>();
    	queuePropertiesList.forEach(queue->{
    		if (!"awd.msg.platform".equals(queue.getQueue())) {
    			Map<String, Object> map = Maps.newHashMap();
    			map.put("appname", queue.getAppname());
    			map.put("queuename", queue.getQueue());
    			map.put("vhost", AwdMqInit.get().getVirtualHost());
    			if (queue.getAppname().contains(appName)) {
    				list.add(map);
				}
			}
    	});
    	return list;
	}
    
    public String getAppnameByQueuename(String queuename) {
    	for (QueueProperties queue : queuePropertiesList) {
    		if (queue.getQueue().equals(queuename)) {
    			return queue.getAppname();
    		}
		}
    	return null;
    }
    
}