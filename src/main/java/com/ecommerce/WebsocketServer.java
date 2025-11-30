package com.ecommerce;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

@ServerEndpoint("/notif")
public class WebsocketServer {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private static ConcurrentHashMap<Long, Session> userSessions = new ConcurrentHashMap<Long,Session>();
    		
    @OnOpen
    public void onOpen(Session session) {
    	
    	String query = session.getQueryString();
    	Long userId = Long.parseLong(query.replace("userId=", ""));
    	
        userSessions.put(userId, session);
        session.getUserProperties().put("userId", userId);
        session.setMaxIdleTimeout(60*30*1000); // prevent session timeout
        
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
        // Handle incoming bid messages and then broadcast the update
        System.out.println("broadcasttext: " + message);
                try {
                	session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
    }
    
    public static void auctionEndedNotification(Long userId, String message) {
    	System.out.println("auctionEndedNotification triggered");
    	
    	for (Session s : sessions) {
    		//check sessions for associated userId and send notifs to only those that match
    		if ( (Long) s.getUserProperties().get("userId") == userId) {
    			onMessage(message, s);
    		} 
    		else {
    			System.out.println("User doesn't match");
    		}
    	}
    }
}