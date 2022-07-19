package com.wind.template.websocket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hsc
 */
@Slf4j
@Data
@Component
@ServerEndpoint("websocket/{id}")
public class WebSocket {

    /**
     * 与某个客户端的会话
     */
    private Session session;

    /**
     * 保存连接数
     */
    private final static ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        try {
            this.session = session;
            SESSION_POOL.put(id, session);
            log.info("WebSocket onOpen id:{}", id);
        } catch (Exception e) {
            log.error("WebSocket onOpen 异常：{}", e.getMessage(), e);
        }
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        try {
            SESSION_POOL.remove(id);
            log.info("WebSocket onClose id:{}", id);
        } catch (Exception e) {
            log.error("WebSocket onClose 异常：{}", e.getMessage(), e);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("WebSocket onMessage message:{}", message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket onError 异常:{}", throwable.getMessage(), throwable);
    }

    public void sendAllMessage(String message) {
        try {
            for (Session session : SESSION_POOL.values()) {
                session.getAsyncRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("WebSocket sendAllMessage 异常:{}", e.getMessage(), e);
        }
    }

    public void sendMessage(String id, String message) {
        try {
            SESSION_POOL.get(id).getAsyncRemote().sendText(message);
        } catch (Exception e) {
            log.error("WebSocket sendMessage 异常:{}", e.getMessage(), e);
        }
    }
}
