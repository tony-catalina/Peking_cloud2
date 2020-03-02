package awd.mis.servers.utils;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import awd.mis.servers.entity.RabbitUsersEntity;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP.Queue.DeleteOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.config.CacheConfig;
import awd.mis.servers.config.RabbitMQConnection;
import awd.mis.servers.entity.RabbitQueuesEntity;


@Component
public class AwdMqUtils {
    private static final Logger logger = LoggerFactory.getLogger(AwdMqUtils.class);

    private static AwdMqUtils instance;

    public AwdMqUtils() {
        super();
    }

    public static AwdMqUtils get() {
        if (instance == null) {
        	instance =  ApplicationContextHolder.get().getBean(AwdMqUtils.class);
        }
        return instance;
    }
    
    /**
     * 发送消息
     * @throws IOException 
     */
    public void sendMessage(String exchange,String routingkey,String message) throws IOException {
    	if (StringUtils.isNullOrEmpty(exchange)) {
    		exchange = AwdMqInit.get().getDefaultfanout();
		}
    	if (StringUtils.isNullOrEmpty(routingkey)) {
    		routingkey = "";
    	}
    	Connection connection = null;
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
			channel.basicPublish(exchange,routingkey,MessageProperties.MINIMAL_PERSISTENT_BASIC, message.getBytes("UTF-8"));
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
    	}finally {
    		connection.close();
		}
    }
    
    /**
     * 创建rabbitmq 交换机
     * @param exchangename
     * @param type
     * @throws IOException 
     */
    public void addRabbitExchange(String exchangename,String type) throws IOException {
    	Connection connection = null;
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		channel.exchangeDeclare(exchangename, type);
    		getExchanges();	//刷新交换机缓存
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		connection.close();
    	}
    }
    
    /**
     * 创建rabbitmq 交换机
     * @param exchangename
     * @param type
     * @throws IOException 
     */
    public void bindExchange(String source,String destination,String routingkey) throws IOException {
    	Connection connection = null;
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		channel.exchangeBind(destination, source, routingkey);
    		getExchanges();	//刷新交换机缓存
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		connection.close();
    	}
    }
    
    /**
     * 创建rabbitmq的消息对列
     * @param rabbitQueue
     * @throws IOException 
     */
    public void addRabbitQueue(RabbitQueuesEntity rabbitQueue) throws IOException {
    	Connection connection = null;
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		boolean durable = true; 
    		boolean autoDelete = false; 
    		if ("1".equals(rabbitQueue.getIstemp())) {
    			durable = false;
			}
    		if ("1".equals(rabbitQueue.getAutodelete())) {
    			autoDelete = true;
    		}
    		if (!queueIsExsit(connection,rabbitQueue.getQueuename())) {
    			channel.queueDeclare(rabbitQueue.getQueuename(), durable, false, autoDelete, null);
			}
    		//getQueues();	//刷新队列缓存
    		channel.close();
		} catch (Exception e) {
			e.printStackTrace();
    	}finally {
    		connection.close();
		}
	}
    
    /**
     * 删除rabbitmq的消息对列
     * @param queuename
     * @throws IOException 
     */
    public Map<String, Object> deleteQueue(String vhost,String queuename) throws IOException {
    	Connection connection = null;
    	Map<String, Object> map = Maps.newHashMap();
    	String code = "500";
    	String msg = "队列删除失败";
    	try {
    		if (!StringUtils.isNullOrEmpty(vhost)) {
    			connection = RabbitMQConnection.get().getConnection(vhost);
			}else {
				connection = RabbitMQConnection.get().getConnection();
			}
    		Channel channel = connection.createChannel();
    		if (queueIsExsit(connection,queuename)) {
    			DeleteOk deleteOk = channel.queueDelete(queuename);
    			if (deleteOk.getMessageCount() == 0) {
    				//getQueues();	//刷新队列缓存
    				code = "200";
    				msg = "队列删除成功";
				}
    		}else {
    			code = "300";
    			msg = "删除失败，队列不存在";
			}
    		channel.close();
    	} catch (Exception e) {
    		//e.printStackTrace();
    		String ex = e.getCause().getLocalizedMessage();
    		msg = ex;
    	}finally {
    		connection.close();
    	}
    	map.put("code", code);
    	map.put("msg", msg);
    	return map;
    }
    
    /**
     * 删除rabbitmq的交换机
     * @param queuename
     * @throws IOException 
     */
    public Map<String, Object> deleteExchange(String vhost,String exchange) throws IOException {
    	Connection connection = null;
    	Map<String, Object> map = Maps.newHashMap();
    	String code = "500";
    	String msg = "删除失败";
    	try {
    		if (!StringUtils.isNullOrEmpty(vhost)) {
    			connection = RabbitMQConnection.get().getConnection(vhost);
    		}else {
    			connection = RabbitMQConnection.get().getConnection();
    		}
    		Channel channel = connection.createChannel();
    		if (exchangeIsExsit(connection,exchange)) {
    			channel.exchangeDelete(exchange);
    			getExchanges();	//刷新交换机缓存
    			code = "200";
    			msg = "删除成功";
    		}else {
    			code = "300";
    			msg = "删除失败，交换机不存在";
			}
    		channel.close();
    	} catch (Exception e) {
    		String ex = e.getCause().getLocalizedMessage();
    		msg = ex;
    	}finally {
    		connection.close();
		}
    	map.put("code", code);
    	map.put("msg", msg);
    	return map;
    }
    
    /**
     * 	添加rabbitmq的消息对列与交换机绑定
     * @param queuename
     * @throws IOException 
     */
    public void addQueueAndBindExchange(String queuename,String exchange,String routingkey) throws IOException {
    	Connection connection = null;
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		if (!queueIsExsit(connection,queuename)) {
    			channel.queueDeclare(queuename, true, false, false, null);
    		}
    		channel.queueBind(queuename, exchange, routingkey);
    		
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		connection.close();
    	}
    }
    
    /**
     * 获取rabbitmq的所有队列
     */
    public List<?> getQueues(Map<String, String> params) {
    	List<Map<String,Object>> queueList = new ArrayList<Map<String,Object>>();
    	try {
    		String vhost = params.get("vhost")==null?"":params.get("vhost");
    		String jsonList = MQUtils.get().getRabbitQueues(vhost);
			List<Map> list = JSONUtil.toList(jsonList, Map.class);
	    	
	    	String queuename = params.get("name")==null?"":params.get("name");
			list.forEach(map->{
		    	if (((String) map.get("name")).contains(queuename)) {
		    		Map<String,Object> tmp = new HashMap<>();
		    		tmp.put("vhost", map.get("vhost"));
		    		tmp.put("name", map.get("name"));
		    		tmp.put("type", map.get("type"));
		    		tmp.put("state", map.get("state"));
		    		tmp.put("consumers", map.get("consumers"));
		    		tmp.put("message_stats", map.get("message_stats"));
		    		tmp.put("ready", map.get("messages_ready"));
		    		tmp.put("unacknowledged", map.get("messages_unacknowledged"));
		    		tmp.put("persistent", map.get("messages_persistent"));
		    		tmp.put("messages_paged_out", map.get("messages_paged_out"));
		    		queueList.add(tmp);
		    	}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return queueList;
    }
    
    /**
     * 根据vhost 和 exchangname 获取绑定队列
     */
    public List<?> getQueuesByExchange(Map<String, String> params) {
    	List<Map<String,Object>> queueList = new ArrayList<Map<String,Object>>();
    	try {
    		String vhost = params.get("vhost")==null?"":params.get("vhost");
    		String exchangname = params.get("exchangname")==null?"":params.get("exchangname");
    		String queuename = params.get("queuename")==null?"":params.get("queuename");
    		String jsonList = getQueuesByExchange(vhost,exchangname);
    		List<Map> list = JSONUtil.toList(jsonList, Map.class);
    		list.forEach(map->{
    			if (((String) map.get("destination")).contains(queuename) && "queue".equals(map.get("destination_type"))) {
    				Map<String,Object> tmp = new HashMap<>();
    				tmp.put("vhost", map.get("vhost"));
    				tmp.put("exchangname", map.get("source"));
    				tmp.put("queuename", map.get("destination"));
    				tmp.put("routingkey", map.get("routing_key"));
    				tmp.put("propertieskey", map.get("properties_key"));
    				queueList.add(tmp);
    			}
    		});
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return queueList;
    }
    
    /**
     * 获取rabbitmq的所所有交换机
     */
    public List<?> getVhosts() {
    	List<? extends Map> vhostList = new ArrayList<Map<String,Object>>();
    	try {
    		Map<String,Object> map = Maps.newHashMap();
    		String jsonList = MQUtils.get().getVhosts();
    		vhostList = JSONUtil.toList(jsonList, map.getClass());
    		CacheConfig.get().setVhostsCache(vhostList);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return vhostList;
    }
    
    
    /**
     * 获取rabbitmq的所所有交换机
     */
    public List<?> getExchanges() {
    	List<? extends Map> exchangeList = new ArrayList<Map<String,Object>>();
    	try {
    		Map<String,Object> map = Maps.newHashMap();
    		String jsonList = MQUtils.get().getExchanges();
    		exchangeList = JSONUtil.toList(jsonList, map.getClass());
    		exchangeList.removeIf(exchange->"".equals(exchange.get("name")));
    		CacheConfig.get().setExchangesCache(exchangeList);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return exchangeList;
    }
    
    
    /**
     * 从缓存中获取queue
     */
	/*
	 * public List<?> getQueuesByCache() { List<?> queueList =
	 * CacheConfig.get().getQueuesCache(); if (StringUtils.isNullOrEmpty(queueList)
	 * || queueList.size() == 0) { queueList = getQueues(); } return queueList; }
	 */
    
    /**
     * 从缓存中获取vhost
     */
    public List<?> getVhostsByCache() {
    	List<?> vhostList = CacheConfig.get().getVhostsCache();
    	if (StringUtils.isNullOrEmpty(vhostList) || vhostList.size() == 0) {
    		vhostList = getVhosts();
		}
    	return vhostList;
    }
    
    /**
     * 从缓存中获取exchange
     */
    public List<?> getExchangesByCache(Map<String, String> params) {
    	List<?> exchangeList = CacheConfig.get().getExchangesCache();
    	if (StringUtils.isNullOrEmpty(exchangeList) || exchangeList.size() == 0) {
    		exchangeList = getExchanges();
		}
    	List<Map<String,Object>> reList = new ArrayList<>();
    	String vhost = params.get("vhost")==null?"":params.get("vhost");
    	String name = params.get("name")==null?"":params.get("name");
    	exchangeList.forEach(exchange->{
    		Map<String,Object> map = JSONUtil.toMap(JSONObject.toJSONString(exchange));
    		if ("".equals(vhost)?true:vhost.equals(map.get("vhost"))) {
    			if (((String) map.get("name")).contains(name)) {
    				reList.add(map);
    			}
    		}
    	});
    	return reList;
    }
    
    /**
     * 从缓存中获取exchange
     */
    public List<?> getExchangesGroupByVhost(String vhost) {
    	if (StringUtils.isNullOrEmpty(vhost)) {
    		vhost = "awd";
		}
    	List<?> exchangeList = CacheConfig.get().getExchangesCache();
    	if (StringUtils.isNullOrEmpty(exchangeList) || exchangeList.size() == 0) {
    		exchangeList = getExchanges();
    	}
    	List<Map<String,Object>> reList = new ArrayList<>();
    	for (Object obj : exchangeList) {
    		Map<String,Object> map = JSONUtil.toMap(JSONObject.toJSONString(obj));
    		if (vhost.equals(map.get("vhost"))) {
    			reList.add(map);
			}
    	};
    	return reList;
    }
    
    /**
     *	 单个绑定
     * @param queuename
     * @param exchange
     * @param routingkey
     * @return
     * @throws IOException
     */
    public Map<String, Object> bindQueueAndExchange(String queuename,String exchange,String routingkey) throws IOException {
    	Connection connection = null;
    	Map<String, Object> map = Maps.newHashMap();
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		if (!queueIsExsit(connection,queuename)) {
    			map.put("msg", "队列 "+queuename+" 不存在");
    			map.put("code", 500);
    		}else if (!exchangeIsExsit(connection,exchange)) {
    			map.put("msg", "交换机 "+exchange+" 不存在");
    			map.put("code", 500);
    		}else {
    			channel.queueBind(queuename, exchange, routingkey);
    			map.put("msg", "队列 "+exchange+" 与交换机 "+queuename+" 绑定成功，秘钥为："+routingkey);
    			map.put("code", 200);
			}
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		connection.close();
    	}
    	return map;
    }
    
    /**
     * 	批量绑定，需要手动关闭连接
     * @param connection	
     * @param returnList	用来在循环时记录所有的绑定结果
     * @param queuename
     * @param exchange
     * @param routingkey
     * @return
     */
    public List<Map<String, Object>> bindQueueAndExchange(Connection connection,
										    		List<Map<String, Object>> returnList,
										    		String queuename,
										    		String exchange,
										    		String routingkey) {
    	Map<String, Object> map = Maps.newHashMap();
    	try {
    		Channel channel = connection.createChannel();
    		if (!queueIsExsit(connection,queuename)) {
    			map.put("msg", "队列 "+queuename+" 不存在");
    			map.put("code", 500);
    		}else if (!exchangeIsExsit(connection,exchange)) {
    			map.put("msg", "交换机 "+exchange+" 不存在");
    			map.put("code", 500);
    		}else {
    			channel.queueBind(queuename, exchange, routingkey);
    			map.put("msg", "队列 "+exchange+" 与交换机 "+queuename+" 绑定成功，秘钥为："+routingkey);
    			map.put("code", 200);
			}
    		returnList.add(map);
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return returnList;
    }
    
    /**
     * 	单个队列解绑
     * @param queuename
     * @param exchange
     * @param routingkey
     * @return
     * @throws IOException
     */
    public Map<String, Object> unbindQueueAndExchange(String queuename,String exchange,String routingkey) throws IOException {
    	Connection connection = null;
    	Map<String, Object> map = Maps.newHashMap();
    	try {
    		connection = RabbitMQConnection.get().getConnection();
    		Channel channel = connection.createChannel();
    		if (!queueIsExsit(connection,queuename)) {
    			map.put("msg", "队列 "+queuename+" 不存在");
    			map.put("code", 500);
    		}else if (!exchangeIsExsit(connection,exchange)) {
    			map.put("msg", "交换机 "+exchange+" 不存在");
    			map.put("code", 500);
    		}else {
    			channel.queueUnbind(queuename, exchange, routingkey);
    			map.put("msg", "队列 "+exchange+" 与交换机 "+queuename+" 解绑成功，秘钥为："+routingkey);
    			map.put("code", 200);
			}
    		channel.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		connection.close();
    	}
    	return map;
    }
    
    
    /**
     * 判断对列是否存在
     * @param channel
     * @param queuename
     * @return
     */
    public boolean queueIsExsit(Connection connection ,String queuename) {
    	try {
    		Channel channel = connection.createChannel();
			channel.queueDeclarePassive(queuename);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
     * 判断交换机是否存在
     * @param channel
     * @param exchange
     * @return
     */
    public boolean exchangeIsExsit(Connection connection ,String exchange) {
    	try {
    		Channel channel = connection.createChannel();
    		channel.exchangeDeclarePassive(exchange);
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    /**
     * 增加rabbitmq 的用户——默认无访问管理页面的权限
     * @param rabbirUser
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void addRabbitUser(RabbitUsersEntity rabbirUser) throws ClientProtocolException, IOException {
		String username = rabbirUser.getUsername();
		String url = "/api/users/" + username;
		String param = "{\"password\":\""+rabbirUser.getPassword()+"\",\"tags\":\"none\"}";
		MQUtils.get().putByRabbitApi(url, param);
	}
    
    /**
     * 设置rabbitmq用户的权限
     * @param rabbirUser
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void setRabbitUserPermission(RabbitUsersEntity rabbirUser) throws ClientProtocolException, IOException {
    	String vhost = rabbirUser.getVhost();
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
		}
    	String username = rabbirUser.getUsername();
    	String url = "/api/permissions/"+vhost + "/" + username;
		String param = "{\"configure\":\"\",\"write\":\"" + rabbirUser.getWritrpermission() + "\",\"read\":\""
				+ rabbirUser.getReadpermission() + "\"}";
		MQUtils.get().putByRabbitApi(url, param);
    }
    
    /**
     * 根据用户名删除rabbit用户
     * @param username
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void deleteRabbitUser(String username) throws ClientProtocolException, IOException {
    	String url = "/api/users";
    	String param = username;
    	MQUtils.get().deleteByRabbitApi(url,param);
    }
    
    /**
     *	直接通过http 添加队列
     * @param vhost
     * @param queuename
     * @param type
     * @param durable
     * @param auto_delete
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String addRabbitQueue(String vhost,String queuename,String type,String durable,String auto_delete) throws ClientProtocolException, IOException {
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
    	}
    	String url = "/api/queues/"+vhost+"/"+queuename;
    	String param = "{\"auto_delete\":"+auto_delete+",\"durable\":"+durable+"}";
    	System.err.println(url);
    	System.err.println(param);
    	return MQUtils.get().putByRabbitApi(url, param);
    }
    
    /**
     * 直接通过http 添加交换机
     * @param vhost
     * @param name
     * @param type
     * @param durable
     * @param auto_delete
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String addRabbitExchange(String vhost,String name,String type,String durable,String auto_delete) throws ClientProtocolException, IOException {
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
		}
    	String url = "/api/exchanges/"+vhost+"/"+name;
		String param = "{\"type\":\"" + type + "\","
						+ "\"auto_delete\":" + false + ","
						+ "\"durable\":" + durable + ","
						+ "\"internal\":" + false + "}";
		return MQUtils.get().putByRabbitApi(url, param);
	}
    
    /**
     * 根据交换机名获取绑定的队列
     * @param vhost
     * @param exchangename
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String getQueuesByExchange(String vhost,String exchangename) throws ClientProtocolException, IOException {
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
    	}
    	String url = "/api/exchanges/"+vhost+"/"+exchangename+"/bindings/source";
    	return MQUtils.get().getByRabbitApi(url, "");
    }
    
    /**
     * 	绑定交换机与队列
     * @param vhost
     * @param exchange
     * @param queue
     * @param routingkey
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String bindQueueAndExchange(String vhost,String exchange,String queue,String routingkey) throws ClientProtocolException, IOException {
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
    	}
    	String url = "/api/bindings/"+vhost+"/e/"+exchange+"/q/"+queue;
    	String param = "{\"routing_key\":\""+routingkey+"\"}";
    	return MQUtils.get().putByRabbitApi(url, param);
    }
    
    /**
     * 	解绑交换机与队列
     * @param vhost
     * @param exchange
     * @param queue
     * @param routingkey
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String unbindQueueAndExchange(String vhost,String exchange,String queue,String propertieskey) throws ClientProtocolException, IOException {
    	if ("/".equals(vhost)) {
    		vhost = URIEncoder.encodeURIComponent(vhost);
    	}
    	propertieskey = URIEncoder.encodeURIComponent(propertieskey);
    	String url = "/api/bindings/"+vhost+"/e/"+exchange+"/q/"+queue+"/"+propertieskey;
    	System.err.println(url);
    	return MQUtils.get().deleteByRabbitApi(url, "");
    }
    
    
}

