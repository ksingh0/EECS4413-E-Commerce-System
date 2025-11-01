package auctionapp;

import java.util.List;
import java.util.ArrayList;

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
		
		//get all auctions
		//should remove auctions that are ended otherwise checker will be redundantly checking a lot of auctions ...
		List<Auction> auctions = auctionController.getAllAuctions(); //could be a direct call to DAO instead ? idk what's better help
		
		for (Auction a : auctions) {
			if (a.isEnded()) {
				//ping Gateway app - send auction ID
				int auctionId = a.getId();
				int itemID = a.getItemID();
				// **make call to Gateway/DAO to remove item from CatalogueDB**
				
				Bid highestBid = bidController.getBid(a.getHighestBidID());
				
				List<Bid> allBids = bidController.getAllBids(auctionId); //get all bids from this auction
				List<Integer> allBidders = new ArrayList<Integer>(); // will have all bidders - errrrr consider using a set/other list type to prevent duplicates
				for (Bid b: allBids) {
					allBidders.add(b.getUserID());
				}
				// use list of all bidders to notify everyone who bidded on auction 'a'
				//..........................................................
				new AuctionPayment(a, highestBid); // for all auctions that have ended, serve payment page
			}
		}
		
	}

}
