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
    		
    @Context
    HttpServletRequest request;
    Long userId;
    
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        
        //how to get userId from here ???
        userSessions.put(userId, session);
        session.getUserProperties().put("userId", userId);
        
        System.out.println("New bidder connected: " + session.getId() + "Userid: " + userId);
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
    public void onMessage(String message, Session session) {
        // Handle incoming bid messages and then broadcast the update
        for (Session s : sessions) {
	        if (!s.equals(session)) { //don't send update to the session the message is coming from
	            try {
	                s.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } 
       };
    }
    
    public static void auctionEndedNotification(Long userId, String message) {
    	System.out.println("auctionEndedNotification triggered");
    	
    	for (Session s : sessions) {
    		try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		//check sessions for associated userId and send notifs to only those that match
    		if ( (Long) s.getUserProperties().get("userId") == userId) {
    			try {
    				s.getBasicRemote().sendText(message);
    			} catch (Exception e) {
    				System.err.println("Error sending auction end notif to user" + userId);
    			}
    		} else {
    			System.out.println("Session not found");
    		}
    	}
    }
}