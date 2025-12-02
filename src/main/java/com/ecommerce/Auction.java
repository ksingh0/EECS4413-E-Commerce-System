package com.ecommerce;

import java.util.Timer;

/**
 * Auction model class - Transfer object between DAO (accessing db) and Business object(s)
 *
 */
 
public class Auction {
	private int id;
	private int highestBidID;
	private double highestPrice;
	private long endTime;
	private int processed;
	
	public Auction() {
		//constructor
	}
	
	//check if auction is over - (elsewhere) if true, then users are served a "Pay Now" page.
	public boolean isEnded() {
		return (this.endTime*1000 <= System.currentTimeMillis());
	}
	
	//setters
	public void setID(int id) {
		this.id = id;
	}
	
	public void setHighestBidID(int highestBidID) {
		this.highestBidID = highestBidID;
	}
	
	public void setHighestPrice(double price) {
		this.highestPrice = price;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public void setProcessed(int processed) {
		this.processed = processed;
	}
	
	//getters
	public int getId() {
		return this.id;
	}
	
	public int getHighestBidID() {
		return this.highestBidID;
	}
	
	public double getHighestPrice() {
		return this.highestPrice;
	}
	
	public long getEndTime() {
		return this.endTime;
	}	
	
	public int getProcessed() {
		return this.processed;
	}
}

