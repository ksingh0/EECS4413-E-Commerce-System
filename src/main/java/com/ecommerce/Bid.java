package com.ecommerce;

/**
 * Bid model class - Transfer Object
 */

public class Bid {
	private int bidID;
	private int auctionID;
	private int userID; //String or int? 
	private long bidTime;
	private double amount;
	
	
	public Bid() {
		
	}
	
	//verify
	public void verifyBid(Auction auction) {
		if (this.getAmount() <= auction.getHighestPrice()) {
			throw new IllegalArgumentException("Bid amount must be greater than the current highest bid!");
		}
		if (auction.isEnded()) {
			throw new RuntimeException("This auction has ended; bids are no longer accepted.");
		}
		
	}
	
	//setters
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}
	
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setBidTime(long bidTime) {
		this.bidTime = bidTime;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	//getters
	public int getBidID() {
		return this.bidID;
	}
	
	public int getAuctionID() {
		return this.auctionID;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public long getBidTime() {
		return this.bidTime;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
}

