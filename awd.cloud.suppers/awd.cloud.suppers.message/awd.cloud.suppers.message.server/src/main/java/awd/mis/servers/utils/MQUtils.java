package awd.mis.servers.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.StringUtils;
import sun.misc.BASE64Encoder;


/**
 * @author WS
 * @Description: 消息体
 * @date 2019/11/14 下午2:18
 */
@Component
public class MQUtils {
    private static final Logger logger = LoggerFactory.getLogger(MQUtils.class);

    @Value("${spring.rabbitmq.host}")
    private String hostip;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    private static MQUtils instance;

    public MQUtils() {
        super();
    }


    public static MQUtils get() {
        if (instance == null) {
        	instance =  ApplicationContextHolder.get().getBean(MQUtils.class);
        }
        return instance;
    }
    

	private CloseableHttpClient getHttpClient() {
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    	credentialsProvider.setCredentials(new AuthScope(hostip,15672),
    			new UsernamePasswordCredentials(username,password));
    	CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
		return client;
	}
    
    
    public String getApiMessage(String queueName) throws IOException {
        //发送一个GET请求
        HttpURLConnection httpConn = null;
        BufferedReader in = null;

        String urlString = "http://" + hostip + ":" + port + "/api/queues/awd/" + queueName;
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = username + ":" + password;
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            return content.toString();
        } else {
            httpConn.disconnect();
            return "";
        }
    }
    
    public String getRabbitQueues(String vhost) throws ClientProtocolException, IOException {
    	return getByRabbitApi("/api/queues",vhost);
	}
    
    public String getVhosts() throws ClientProtocolException, IOException {
    	return getByRabbitApi("/api/vhosts","");
    }
    
    public String getExchanges() throws ClientProtocolException, IOException {
    	return getByRabbitApi("/api/exchanges","");
    }
    
    public String getByRabbitApi(String url,String param) throws ClientProtocolException, IOException {
    	if (!StringUtils.isNullOrEmpty(param)) {
    		param = URIEncoder.encodeURIComponent(param);
    		url = url + "/" + param;
		}
    	HttpHost host = new HttpHost(hostip, 15672);
    	HttpGet httpGet = new HttpGet(url);
    	CloseableHttpClient client = getHttpClient();
    	HttpResponse response = client.execute(host, httpGet);
    	String result = null;
    	if(response!=null && 200 == response.getStatusLine().getStatusCode()){
    		HttpEntity entity = response.getEntity();
    		result = EntityUtils.toString(entity);
    	}
    	return result;
    }

    public String postByRabbitApi(String url,String param) throws ClientProtocolException, IOException {
    	HttpHost host = new HttpHost(hostip, 15672);
    	HttpPost httpPost = new HttpPost(url);
    	CloseableHttpClient client = getHttpClient();
    	StringEntity paramEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
    	httpPost.setEntity(paramEntity);
    	HttpResponse response = client.execute(host, httpPost);
    	String result = null;
    	if(response!=null && 200 == response.getStatusLine().getStatusCode()){
    		HttpEntity entity = response.getEntity();
    		result = EntityUtils.toString(entity);
    	}
    	return result;
    }
    
    public String putByRabbitApi(String url,String param) throws ClientProtocolException, IOException {
    	HttpHost host = new HttpHost(hostip, 15672);
    	HttpPut httpPut = new HttpPut(url);
    	CloseableHttpClient client = getHttpClient();
    	StringEntity paramEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
    	httpPut.setEntity(paramEntity);
    	HttpResponse response = client.execute(host, httpPut);
    	String result = null;
    	if(response!=null && 200 == response.getStatusLine().getStatusCode()){
    		HttpEntity entity = response.getEntity();
    		result = EntityUtils.toString(entity);
    	}
    	return result;
    }
    
    public String deleteByRabbitApi(String url,String param) throws ClientProtocolException, IOException {
    	HttpHost host = new HttpHost(hostip, 15672);
    	String urlString = url +"/"+ param;
    	HttpDelete httpDelete = new HttpDelete(urlString);
    	
    	CloseableHttpClient client = getHttpClient();
    	
    	HttpResponse response = client.execute(host, httpDelete);
    	String result = null;
    	if(response!=null && 200 == response.getStatusLine().getStatusCode()){
    		HttpEntity entity = response.getEntity();
    		result = EntityUtils.toString(entity);
    	}
    	return result;
    }
}

