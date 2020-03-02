package awd.cloud.platform.webs.charts.config;

import awd.cloud.platform.webs.charts.modal.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator{
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 将用户信息存储到socket的配置里
        sec.getUserProperties().put("user", users);
        super.modifyHandshake(sec, request, response);
    }
    }

