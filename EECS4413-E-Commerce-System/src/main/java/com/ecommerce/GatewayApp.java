package com.ecommerce;

public class GatewayApp {

	
	//To be implemented when databases are added
	// Also need to add new dbs to context for DatabaseConncetion.java
	public Auction getAuctions() {
		return new Auction(0,0,0,0,0);
	}
	
	public Bid getBids() {
		
		return new Bid(0,0,0,0,0);
	}
}
