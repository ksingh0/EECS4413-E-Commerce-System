package com.ksingh.studentapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Catalogue {
	//model class for items in Catalogue
	//Naming from JSON requests
	@JsonProperty("item_id")
	private int item_id; 
	@JsonProperty("item_name")
	private String item_name;
	@JsonProperty("current_bid")
	private double current_bid;
	@JsonProperty("auction_type")
	private String auction_type;
	@JsonProperty("remaining_time")
	private String remaining_time;
	@JsonProperty("item_description")
	private String item_description;
	@JsonProperty("shipping_time")
	private String shipping_time;
	@JsonProperty("end_date")
	private String end_date;
	@JsonProperty("initial_price")
	private double initial_price;
	
	//getters and setters
	public int getItemID() {
		return item_id;
	}
	
	public void setItemID(int item_id) {
		this.item_id = item_id;
	}
	
	public String getItemName() {
		return item_name;
	}
	
	public void setItemName(String item_name) {
		this.item_name = item_name;
	}
	
	public double getCurrentBid() {
		return current_bid;
	}
	
	public void setCurrentBid(double current_bid) {
		this.current_bid = current_bid;
	}
	
	public String getAuctionType() {
		return auction_type;
	}
	
	public void setAuctionType(String auction_type) {
		this.auction_type = auction_type;
	}
	
	public String getRemainingTime() {
		return remaining_time;
	}
	
	public void setRemainingTime(String remaining_time) {
		this.remaining_time = remaining_time;
	}
	
	public String getItemDescription() {
		return item_description;
	}
	
	public void setItemDescription(String item_description) {
		this.item_description = item_description;
	}
	
	public String getShippingTime() {
		return shipping_time;
	}
	
	public void setShippingTime(String shipping_time) {
		this.shipping_time = shipping_time;
	}
	
	public String getEndDate() {
		return end_date;
	}
	
	public void setEndDate(String end_date) {
		this.end_date = end_date;
	}
	
	public double getInitialPrice() {
		return initial_price;
	}
	
	public void setInitialPrice(double initial_price) {
		this.initial_price = initial_price;
	}
}









