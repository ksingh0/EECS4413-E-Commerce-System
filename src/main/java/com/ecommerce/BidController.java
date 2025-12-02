package com.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *  BidController makes calls to the DAO (GatewayApp) to get bids from DB
 *  REST API
 */

@Path("") 
public class BidController {
	//create instance of DAO / connect to GatewayApp
	
	private DAO dao = new DAO();
	
	@GET
	@Path("/{auctionID}/bids")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Bid> getAllBids(@PathParam("auctionID") int auctionID) {
		//call DAO - return list of bids ...
		return dao.readAllBids(auctionID);
	}
	
	@GET
	@Path("/bid/{bidID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bid getBid(@PathParam("bidID") int bidID) {
		//DAO; get a specific bid - e.g. the highest bid
		return dao.readBid(bidID);
	}
	
	@POST
	@Path("/bid")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeBid(Bid bid) {
		// DAO 
		// make bid based on auctionID ; update that auctionID to reflect new highest bid
		Auction a = dao.read(bid.getAuctionID());
		if (a == null) {
            return Response.status(400).entity(Map.of("error", "Auction does not exist.")).build();
		}
		int i = bid.verifyBid(a); //verify bid - this will throw an exception if bid is not higher than current highest
		if (i==1) {
            return Response.status(400).entity(Map.of("error", "Bid amount must be greater than the current highest bid!")).build();
		} else if (i==2) {
            return Response.status(400).entity(Map.of("error", "This auction has ended; bids are no longer accepted.")).build();
		}
		int bidID = dao.createBid(bid); 
		a.setHighestBidID(bidID);
		a.setHighestPrice(bid.getAmount());
		dao.updateAuction(a.getId(), a); //update auction with the new highestBidID and highestPrice
		return Response.status(201).build();
	}
	
	@DELETE
	@Path("/{auctionID}")
	@Produces(MediaType.APPLICATION_JSON) 
	public void deleteAll(@PathParam("auctionID") int auctionID) {
		//delete bids - when associated auction gets removed
		dao.deleteBids(auctionID);
	}
	
}



