package com.ecommerce;
import java.util.TimerTask;

public class AuctionTimerTask extends TimerTask {
	private int auctionID;
	
	public AuctionTimerTask(int auctionID) {
		this.auctionID = auctionID;
	}

	@Override
	public void run() {
		// AuctionTimerTask executes when an auction has ended (its Timer's scheduled time)
		
		//Ping Gateway app to alert that auction has ended
		System.out.println(String.format("An auction has ended (auction ID: %d)", this.auctionID)); //debug
	}

}
