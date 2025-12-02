package com.ecommerce;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/message") 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageController {
	private MessageDAO msgDao = new MessageDAO();
	
	//get all messages for specific user with id = userId
	@GET
	@Path("/all/{userId}")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages(@PathParam("userId") int userId) {
		//call DAO - return list of bids ...
		return msgDao.readAllMessages(userId);
	}
	
	//get a specific message with id = id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("id") int id) {
		return msgDao.readMessage(id);
	}
	
	@POST
	@Path("/post")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int postMessage(Message message) {
		return msgDao.createMessage(message); //return id of message that was created
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public void deleteAuction(@PathParam("id") int id) {
		msgDao.deleteMessage(id);
	}
}