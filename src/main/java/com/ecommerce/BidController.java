package auctionapp;

import java.util.ArrayList;
import java.util.List;

import auctionapp.Auction;
import auctionapp.Bid;

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
	@Path("/{bidID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bid getBid(@PathParam("bidID") int bidID) {
		//DAO; get a specific bid - e.g. the highest bid
		return new Bid();
	}
	
	@POST
	@Path("/bid")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void makeBid(Bid bid) {
		// DAO 
		// make bid based on auctionID ; update that auctionID to reflect new highest bid
		Auction a = dao.read(bid.getAuctionID());
		bid.verifyBid(a); //verify bid - this will throw an exception if bid is not higher than current highest
		dao.createBid(bid); 
		a.setHighestBidID(bid.getBidID());
		a.setHighestPrice(bid.getAmount());
		dao.updateAuction(a.getId(), a); //update auction with the new highestBidID and highestPrice
		
	}
	
	@PUT
	@Path("/{bidID}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	public void updateBid(@PathParam("bidID") int bidID, Bid bid) {
		// is there a need to update bids ... ? not really ?
		// maybe in some exceptional cases ??
	}
	
	@DELETE
	@Path("/{auctionID}")
	@Produces(MediaType.APPLICATION_JSON) 
	public void deleteAll(@PathParam("auctionID") int auctionID) {
		//delete bids - when associated auction gets removed
		dao.deleteBids(auctionID);

	}
	
}
