package awd.mis.desktop.socket;

import awd.framework.expands.rabbitmq.model.KssMessage;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{userJh}")
@Component
public class MyWebSocket {

    Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    @Autowired
    private EnvironmentManager environmentManager;

    private Session webSocketSession;

    private String userName = "";

    String jh = "";

    //无警号用只能用用户名匹配
    public static final String NOJHUSERS = "XXXXXXXXXXX";

    private Long TIMEOUT = Long.parseLong("9999999999900");

    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();


    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     **/
    @OnOpen
    public void onOpen(@PathParam("userJh") String userJh, Session session, EndpointConfig config) {

        try {
            this.userName = new String(Base64.getDecoder().decode(userJh.split("_")[1]), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.jh = userJh.split("_")[2];
        this.webSocketSession = session;
        logger.info("当前建立连接的用户名：{} ,警号是：{} ,sessionID：{}", userName, this.jh, session.getId());
        boolean flag = true;
        for (MyWebSocket myWebSocket : webSocketSet) {
            if (jh.equals(myWebSocket.jh)) {
                flag = false;
            }
        }
        if (flag) {
            webSocketSet.add(this);     //加入set中
            addOnlineCount();           //在线数加1
        }
        logger.info("当前连接的用户数量是：{}", webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        logger.error("用户强制关闭连接可能会抛出异常");
        logger.error("被关闭的sessionID：{}", session.getId());
        webSocketSet.remove(this);
        subOnlineCount();           //在线数减1
        logger.warn("有一连接关闭！当前在线人数为{}", getOnlineCount());
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("接受客户端消息！");
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("连接发生异常,消息通道已关闭!");
        //从set中删除
        webSocketSet.remove(this);
        subOnlineCount();

    }

    /**
     * 消息发放接口(指定用户)
     *
     * @param messageModel
     * @throws IOException
     */
    public boolean sendUserMessage(KssMessage messageModel) throws IOException {
        String userName = messageModel.getReceiver();
        String jh = messageModel.getReceiverIdentifier();
        String content = new String(messageModel.getContent(),"utf-8");
        logger.info("接收人警号：{}  消息：{}", jh, content);
        try {
            if (webSocketSet.size() != 0) {
                logger.info("当前连接数：{}", webSocketSet.size());
                boolean flag = false;
                for (MyWebSocket s : webSocketSet) {
                    if (jh.equals(s.jh)) {
                        s.webSocketSession.getBasicRemote().sendText(content);
                        return true;
                    }
                }
                return flag;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
