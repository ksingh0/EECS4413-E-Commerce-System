package com.ecommerce;

//Data transfer object for messages table in userDB
public class Message {
	private int id;
	private long userId;
	private int auctionId;
	private String message;
	private int opened;
	
	//getters
	public int getId() {
		return this.id;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public int getAuctionId() {
		return this.auctionId;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int gettOpened() {
		return this.opened;
	}
	
	//setters
	public void setId(int id) { this.id = id; }
	
	public void setUserId(long userId) {this.userId = userId; }
	
	public void setAuctionId(int auctionId) {this.auctionId = auctionId;}
	
	public void setMessage(String message) {this.message = message;}
	
	public void setOpened(int i) {this.opened = i;}
}
