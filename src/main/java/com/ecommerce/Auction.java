package com.ecommerce;

public class Auction {
	
	private int auctionID, endtime, highBidID, itemID;
	private double highPrice;
	
	
	public Auction(int auctionID, int endtime, int highBidID, int itemID, double highPrice) {
		super();
		this.auctionID = auctionID;
		this.endtime = endtime;
		this.highBidID = highBidID;
		this.itemID = itemID;
		this.highPrice = highPrice;
	}


	public int getAuctionID() {
		return auctionID;
	}


	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}


	public int getEndtime() {
		return endtime;
	}


	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}


	public int getHighBidID() {
		return highBidID;
	}


	public void setHighBidID(int highBidID) {
		this.highBidID = highBidID;
	}


	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public double getHighPrice() {
		return highPrice;
	}


	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	
	
	

}
