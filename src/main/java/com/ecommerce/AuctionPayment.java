package com.ecommerce;

/**
 * (UC4) - after auction ends, AuctionPayment class gets information about payment and shipping in order to deliver the "Pay now" page.
 */
public class AuctionPayment {
	private Auction auction;
	private Bid highestBid;
	private int winnerID;
	
	public AuctionPayment(Auction auction, Bid highestBid) {
		this.auction = auction;
		this.highestBid = highestBid;
		this.winnerID = highestBid.getUserID();
	}
	
	//method to notify...?
	//need to allow for payment/connect to Payment service
	
	//need to get Shipping info from CatalogueDB + Expedited shipping info
	public double getShipping() {
		return 0.0;
	}
	
	public double getExpeditedShipping() {
		return 0.0;
	}
	
	public boolean verifyWinner(int userID) {
		return (userID == this.winnerID); //elsewhere - serve users a FAILURE NOTICE (alert/webpage?) if they are not the winner and attempt to pay.
	}
	
	
}

