package com.ecommerce;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Task to be run every hour to check for auctions ending on that hour. Pings GatewayApp for each auction that has ended, also creates
 * an AuctionPayment object for each ended auction so that payment can be handled.
 * 
 * Gets a list of all the users that have bidded on ended auctions so that these users can receive notification.
 */

public class AuctionCheckerTask implements Runnable {

	@Override
	public void run() {
		AuctionController auctionController = new AuctionController();
		BidController bidController = new BidController();
		MessageController msgController = new MessageController();
		
		System.out.println("Auction checker task ran");
		
		//get all auctions
		List<Auction> auctions = auctionController.getAllAuctions(); //could be a direct call to DAO instead ?
		
		for (Auction a : auctions) {
			if (a.isEnded() && a.getProcessed() != 1) { //check if auction has ended but is not yet processed
				int id = a.getId();
				System.out.println("Auction " + id + " has ended.");
				
				// make call to Gateway/DAO to remove item from CatalogueDB**
				Bid highestBid = bidController.getBid(a.getHighestBidID());
				
				List<Bid> allBids = bidController.getAllBids(id); //get all bids from this auction
				Set<Long> allBidderIds = new HashSet<Long>(); // set of all bidders
				
				for (Bid b: allBids) {
					allBidderIds.add(b.getUserID());
				}
				
				// use list of all bidders to notify everyone who bidded on auction 'a'
				String message = "An auction you bidded on has ended: Auction " + id;
				for (Long userId : allBidderIds) {
					WebsocketServer.auctionEndedNotification(userId, message);
					Message msg = new Message();
					msg.setAuctionId(id);
					msg.setUserId(userId);
					msg.setMessage(String.format("An auction you bidded on has ended: Auction %d", id));
					msgController.postMessage(msg);
				}

				a.setProcessed(1);
				auctionController.processAuction(id, a);
			}
		}
		
	}

}

