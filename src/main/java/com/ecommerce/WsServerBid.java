package com.ecommerce;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnError;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.ws.rs.core.Context;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/bidNotif")
public class WsServerBid {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    		
    @OnOpen
    public void onOpen(Session session) {
    	String query = session.getQueryString();
    	Long userId = Long.parseLong(query.replace("userId=", ""));
        session.getUserProperties().put("userId", userId);
        session.setMaxIdleTimeout(60*30*1000); // prevent session timeout while user is logged in
        
        sessions.add(session);
        System.out.println("New bidder connected: " + session.getId() + " Userid: " + userId);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Bidder disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
        System.err.println("Error on session " + session.getId() + ": " + throwable.getMessage());
    }

    @OnMessage
    public static void onMessage(String message, Session session) {
       for (Session s : sessions) {
    	   if (!s.equals(session)) { // send notification to other bidders - do not include session that bidded
                try {
                	session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	   }
       }
    }
  
}
   