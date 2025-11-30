package com.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DAO {
	
	//get all bids for a particular auction
	public List<Bid> readAllBids(int auctionID) {
		String sql = "SELECT BidID, BidTime, BidAmount, UserID FROM Bids WHERE AuctionID = ?";
		List<Bid> bids = new ArrayList<Bid>();
		
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				//Set the corresponding parameter (AuctionID)
				pstmt.setInt(1,  auctionID);
				
				//Execute query + get result set
				try (ResultSet rs = pstmt.executeQuery()) {
					
					//Check if result was returned
					while (rs.next()) {
						Bid bid = new Bid();
						//set properties of the Bid object

						bid.setAuctionID(auctionID);
						bid.setBidID(rs.getInt("BidID"));				
						bid.setBidTime(rs.getInt("BidTime"));
						bid.setAmount(rs.getDouble("BidAmount"));
						bid.setUserID(rs.getInt("UserID"));
						
						//add to list
						bids.add(bid);

					}}}
			
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		
			return bids;
	}
	
	public int createBid(Bid bid) {
		String sql = "INSERT INTO Bids(AuctionID, UserID, BidTime, BidAmount) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1,  bid.getAuctionID());
				pstmt.setLong(2,  bid.getUserID());
				pstmt.setLong(3, System.currentTimeMillis()/1000);
				pstmt.setDouble(4,  bid.getAmount());
				pstmt.executeUpdate();
				
				//get id of the bid that was just added to the table
				String lastIdSql = "SELECT last_insert_rowid()";
	            try (Statement stmt = conn.createStatement();
	                 ResultSet rs = stmt.executeQuery(lastIdSql)) {
	                if (rs.next()) {
	                    int lastId = rs.getInt(1);
	                    return lastId;
	                }
	            }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return -1; // if not successful
	}
	
	public List<Auction> readAllAuctions() {
		String sql = "SELECT AuctionID, AuctionEndtime, HighestPrice, HighestBidID, Processed FROM Auction";
		List<Auction> auctions = new ArrayList<>();
		
		try (Connection conn = DatabaseConnection.connectAuction(); 
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) 
			{	
			while (rs.next()) {
				Auction auction = new Auction();
				auction.setID(rs.getInt("AuctionID"));
				auction.setEndTime(rs.getInt("AuctionEndtime"));
				auction.setHighestPrice(rs.getDouble("HighestPrice"));
				auction.setHighestBidID(rs.getInt("HighestBidID"));
				auction.setProcessed(rs.getInt("Processed"));
				auctions.add(auction); 
				} 
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return auctions;
	}
	
	//create a new auction
	public int create(Auction auction) {
		String sql = "INSERT INTO Auction(AuctionID, AuctionEndtime) VALUES (?,?)";
		try (Connection conn = DatabaseConnection.connectAuction();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, auction.getId());
			pstmt.setLong(2, auction.getEndTime());
			pstmt.executeUpdate();
			
			//get id of the auction that was just added to the table
			String lastIdSql = "SELECT last_insert_rowid()";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(lastIdSql)) {
                if (rs.next()) {
                    int lastId = rs.getInt(1);
                    return lastId;
                }
            }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return -1; //if auction successfully created in database
	}
	
	//get auction with AuctionID = id
	public Auction read(int id) {
		String sql = "SELECT AuctionID, AuctionEndtime, HighestPrice, HighestBidID, Processed FROM Auction WHERE AuctionID = ?";
		Auction auction = null;
		
		try (Connection conn = DatabaseConnection.connectAuction();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			//Set the corresponding parameter (AuctionID)
			pstmt.setInt(1,  id);
			
			//Execute query + get result set
			try (ResultSet rs = pstmt.executeQuery()) {
				
				//Check if result was returned
				if (rs.next()) {
					auction = new Auction();
					auction.setID(id);				
					auction.setEndTime(rs.getInt("AuctionEndtime"));
					auction.setHighestPrice(rs.getDouble("HighestPrice"));
					auction.setHighestBidID(rs.getInt("HighestBidID"));
					auction.setProcessed(rs.getInt("Processed"));
				}}}
		
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return auction;
	}

	//get Bid with BidID = id
	public Bid readBid(int id) {
		String sql = "SELECT BidID, AuctionID, UserID, BidTime, BidAmount FROM Bids WHERE BidID = ?";
		Bid bid = null;
		
		try (Connection conn = DatabaseConnection.connectAuction();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,  id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					bid = new Bid();
					bid.setBidID(id);			
					bid.setAuctionID(rs.getInt("AuctionID"));
					bid.setUserID(rs.getLong("UserID"));
					bid.setBidTime(rs.getLong("BidTime"));
					bid.setAmount(rs.getDouble("BidAmount"));
				}}}
		
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return bid;
	}
	
	//update auction when a bid is made to reflect NEW HIGHEST BID + PRICE***
	public void updateAuction(int id, Auction auction) {
		//use prepared statement
		String sql = "UPDATE Auction SET HighestPrice = ?, HighestBidID = ? WHERE AuctionID = ?";
		
		try (Connection conn = DatabaseConnection.connectAuction();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//set parameters
			pstmt.setDouble(1, auction.getHighestPrice());
			pstmt.setInt(2, auction.getHighestBidID());
			pstmt.setInt(3, id);
			//Update auction 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//mark an auction as processed - i.e. auction end notification has been handled
	public void updateAuctionProcess(int id, Auction auction) {
		String sql = "UPDATE Auction SET Processed = ? WHERE AuctionID = ?";
		
		try (Connection conn = DatabaseConnection.connectAuction();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			//set parameters
			pstmt.setInt(1, 1);
			pstmt.setInt(2, id);
			//Update auction 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//delete auction
	public void deleteAuction(int id) {
		String sql = "DELETE FROM Auction WHERE AuctionID = ?";
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			//set parameters
			pstmt.setInt(1,  id);
			//delete record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//automatically delete Bids for this auction as well
		this.deleteBids(id);
	}
	
	//delete all bids for a specified Auction - e.g. when auction is cancelled (possibly if auction has been completed for x amount of time)
	public void deleteBids(int id) {
		String sql = "DELETE FROM Bids WHERE AuctionID = ?";
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			//set parameters
			pstmt.setInt(1,  id);
			//delete record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}



