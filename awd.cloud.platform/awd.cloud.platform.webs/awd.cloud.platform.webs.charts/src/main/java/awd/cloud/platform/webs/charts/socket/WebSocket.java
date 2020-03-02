package awd.cloud.platform.webs.charts.socket;

import awd.cloud.platform.webs.charts.config.WebSocketConfig;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.CacheUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zp
 * @Date: 2019-09-20 15:12
 * @Description:
 */
@Component
@ServerEndpoint(value = "/webSocket/{page}",configurator = WebSocketConfig.class)
public class WebSocket {

    static Logger log=LoggerFactory.getLogger(WebSocket.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        User ss=(User)session.getUserProperties().get("user");

        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+ss+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        try {
            String keys=ss.getJsbh()+""+ss.getName();
            System.out.println("===================+++"+keys);
            List<String> jsonObjectList=CacheUtils.get().getInfoListBykey(keys);
            for (int i=0;i<jsonObjectList.size();i++) {
                System.out.println("============json" + jsonObjectList.get(i));
                    System.out.println("---------zoulema");
                    sendMessage(jsonObjectList.get(i));

            }
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }



    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (WebSocket item : webSocketSet) {
            try {
                    item.sendMessage(message);

            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送告警消息
     * */
    public void sendMessage(Map<String,Object> dataMap)throws IOException{
        try{if(dataMap!=null){
            String JsString=JSONObject.toJSONString(dataMap);
            JSONObject jsonObject = JSONObject.parseObject(JsString);
            StringBuilder builder = new StringBuilder(jsonObject.toString());
            //发送告警到前台
            this.session.getBasicRemote().sendText(builder.toString());
            log.info("发送成功");
        }
        }catch(Exception e) {
            log.error(e.toString());
        }
    }
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message, String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocket item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                log.error(e.toString());
                continue;
            }
        }
    }
    /**
     * 群发自定义消息
     * */
    public static void sendInfoMap(Map<String,Object> dataMap, String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+dataMap.toString());
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(dataMap);
                //这里可以设定只推送给这个sid的，为null则全部推送暂时全部放开
            	/*if(sid==null) {
            		item.sendMessage(dataMap);
            	}else if(item.sid.equals(sid)){
            		item.sendMessage(dataMap);
            	}*/
            } catch (IOException e) {
                log.error(e.toString());
                continue;
            }
        }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

}
