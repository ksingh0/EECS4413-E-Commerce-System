package com.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

	//get message by its id
	public Message readMessage(int id) {
		String sql = "SELECT id, userId, auctionId, message, opened FROM messages WHERE id = ?";
		Message msg = null;
		try (Connection conn = DatabaseConnection.connectUsers();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,  id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					msg = new Message();
					msg.setId(id);			
					msg.setUserId(rs.getLong("userId"));
					msg.setAuctionId(rs.getInt("auctionId"));
					msg.setMessage(rs.getString("message"));
					msg.setOpened(rs.getInt("opened"));
				}}}
		
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return msg;
	}

	//read all messages with specific userId
	public List<Message> readAllMessages(int userId) {
		String sql = "SELECT id, userId, auctionId, message, opened FROM messages WHERE userId = ?";
		List<Message> messages = new ArrayList<Message>();
		
		try (Connection conn = DatabaseConnection.connectUsers();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				//Set the corresponding parameter (userId)
				pstmt.setInt(1, userId);
				
				//Execute query + get result set
				try (ResultSet rs = pstmt.executeQuery()) {
					//Check if result was returned
					while (rs.next()) {
						Message msg = new Message();
						//set properties of the message object
						msg.setId(rs.getInt("id"));
						msg.setUserId(rs.getLong("userId"));				
						msg.setAuctionId(rs.getInt("auctionId"));
						msg.setMessage(rs.getString("message"));
						msg.setOpened(rs.getInt("opened"));
						
						//add to list
						messages.add(msg);
					}
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		
			return messages;
	}

	//create a new message
	public int createMessage(Message message) {
		String sql = "INSERT INTO messages(userId, auctionId, message) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnection.connectUsers();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setLong(1,  message.getUserId());
				pstmt.setInt(2,  message.getAuctionId());
				pstmt.setString(3, message.getMessage());
				pstmt.executeUpdate();
				
				//get id of the message that was just added to the table
				String lastIdSql = "SELECT last_insert_rowid()";
	            try (Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery(lastIdSql)) {
	                if (rs.next()) {
	                    int lastId = rs.getInt(1);
	                    return lastId;
	                }
	            }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return -1; // if not successful
	}
	
	//delete message by its id
	public void deleteMessage(int id) {
		String sql = "DELETE FROM messages WHERE id = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,  id);
			//delete record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
