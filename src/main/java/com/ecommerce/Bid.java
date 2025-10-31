package com.ecommerce;

public class Bid {
	
	private int bidID, auctionID, userID, bidTime, bidAmount;

	public Bid(int bidID, int auctionID, int userID, int bidTime, int bidAmount) {
		super();
		this.bidID = bidID;
		this.auctionID = auctionID;
		this.userID = userID;
		this.bidTime = bidTime;
		this.bidAmount = bidAmount;
	}

	public int getBidID() {
		return bidID;
	}

	public void setBidID(int bidID) {
		this.bidID = bidID;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBidTime() {
		return bidTime;
	}

	public void setBidTime(int bidTime) {
		this.bidTime = bidTime;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	
	
}
