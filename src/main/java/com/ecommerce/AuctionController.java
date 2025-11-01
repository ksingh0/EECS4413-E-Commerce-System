package com.ecommerce;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *  AuctionController makes calls to the DAO (GatewayApp) to get auctions from DB
 *  REST API
 */

@Path("/auctions")
public class AuctionController {
	//create instance of DAO (GatewayApp)
	private DAO auctionDAO = new DAO();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public List<Auction> getAllAuctions() {
		return auctionDAO.readAllAuctions();
	}
	
	@GET
	@Path("/{AuctionID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Auction getAuction(@PathParam("AuctionID") int AuctionID) {
		//call to DAO to retrieve a specific auction by ID
		Auction auction =  auctionDAO.read(AuctionID);
		return auction;
	}
	
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createAuction(Auction auction) {
		//call to DAO to create new auction (e.g. newly updated item)
		auction.createTimer();
	}
	
	@PUT
	@Path("/{AuctionID}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	public void updateAuction(@PathParam("AuctionID") int AuctionID, Auction auction) {
		//call to DAO to update an auction 
		//must be updated every time a new bid is placed (highestBid, highestPrice)
		//and update when auction ends
		auctionDAO.updateAuction(AuctionID, auction);
	}
	
	//delete auction - e.g. if cancelled by seller ?
	@DELETE
	@Path("/{AuctionID}")
	@Produces(MediaType.APPLICATION_JSON) 
	public void deleteAuction(@PathParam("AuctionID") int AuctionID) {
		//call to DAO to remove auction from AuctionDB 
		//call to DAO to remove associated bids from BidDB
		auctionDAO.deleteAuction(AuctionID);
	}
	
}



