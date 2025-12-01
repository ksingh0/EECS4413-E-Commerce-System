package com.ecommerce;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WishlistDAO {
	
	//get all wishlisted auctions for a particular user, should be pulled from the session.
	public List<Auction> readAllWishlists(long userid) {
		String sql = """
		        SELECT A.AuctionID, A.AuctionEndtime, A.HighestPrice,
		               A.HighestBidID, A.ItemID
		        FROM Wishlist W
		        JOIN Auction A ON W.auctionid = A.AuctionID
		        WHERE W.UserID = ?
		        """;
        List<Auction> auctions = new ArrayList<>();
		
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				//Set the corresponding parameter
				pstmt.setLong(1,  userid);
				
				//Execute query + get result set
				try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    Auction auction = new Auction();
	                    auction.setID(rs.getInt("AuctionID"));
	                    auction.setEndTime(rs.getInt("AuctionEndTime"));
	                    auction.setHighestPrice(rs.getDouble("HighestPrice"));
	                    auction.setHighestBidID(rs.getInt("HighestBidID"));
	                    auction.setItemID(rs.getInt("ItemID"));

	                    auctions.add(auction);
	                }
	            }} catch (SQLException e) {
	            	System.out.println(e.getMessage());
	                return null;
	            }
		
			return auctions;
		
	}
	
	public boolean createWishlist(long userid, int auctionid) {
		String sql = "INSERT OR IGNORE INTO Wishlist(userid, auctionid) VALUES (?, ?)";
		try (Connection conn = DatabaseConnection.connectAuction();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setLong(1,  userid);
				pstmt.setInt(2,  auctionid);
				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
    public boolean deleteWishlist(long userid, int auctionid) {
        String sql = "DELETE FROM Wishlist WHERE userid = ? AND auctionid = ?";

        try (Connection conn = DatabaseConnection.connectAuction();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userid);
            pstmt.setInt(2, auctionid);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
