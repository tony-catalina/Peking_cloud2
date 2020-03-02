package awd.mis.servers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WS
 * @Description:
 * @date 2019/12/27 上午11:26
 */
@Component
@ConfigurationProperties(prefix = "rocket", ignoreUnknownFields = false)
public class RocketProperties {

    private String url;
    private String secretKeyMsg;
    private String accessKeyMsg;
    private String secretKeyApi;
    private String accessKeyApi;
    private String producerGroup;
    private String topic;
    private String nameServer;

    public RocketProperties() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecretKeyMsg() {
        return secretKeyMsg;
    }

    public void setSecretKeyMsg(String secretKeyMsg) {
        this.secretKeyMsg = secretKeyMsg;
    }

    public String getAccessKeyMsg() {
        return accessKeyMsg;
    }

    public void setAccessKeyMsg(String accessKeyMsg) {
        this.accessKeyMsg = accessKeyMsg;
    }

    public String getSecretKeyApi() {
        return secretKeyApi;
    }

    public void setSecretKeyApi(String secretKeyApi) {
        this.secretKeyApi = secretKeyApi;
    }

    public String getAccessKeyApi() {
        return accessKeyApi;
    }

    public void setAccessKeyApi(String accessKeyApi) {
        this.accessKeyApi = accessKeyApi;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    @Override
    public String toString() {
        return "RocketProperties{" +
                "url='" + url + '\'' +
                ", secretKeyMsg='" + secretKeyMsg + '\'' +
                ", accessKeyMsg='" + accessKeyMsg + '\'' +
                ", secretKeyApi='" + secretKeyApi + '\'' +
                ", accessKeyApi='" + accessKeyApi + '\'' +
                ", producerGroup='" + producerGroup + '\'' +
                ", topic='" + topic + '\'' +
                ", nameServer='" + nameServer + '\'' +
                '}';
    }
}
