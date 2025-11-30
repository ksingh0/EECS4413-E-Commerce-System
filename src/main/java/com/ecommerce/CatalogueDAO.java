package com.ecommerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CatalogueDAO {
	
	public List<Catalogue> readAllItems() {
		//Columns in items table from Catalogue Database
		String sql = "SELECT item_id, item_name, current_bid, auction_type, remaining_time, item_description, shipping_time, end_date, initial_price, shipping_cost, expedited_shipping FROM items";
		List<Catalogue> items = new ArrayList<>();

		try (Connection conn = DatabaseConnection.connectCatalogue();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){

			while (rs.next()) {
				Catalogue item = new Catalogue();
				item.setItemID(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setCurrentBid(rs.getDouble("current_bid"));
				item.setAuctionType(rs.getString("auction_type"));
				item.setRemainingTime(rs.getString("remaining_time"));
				item.setItemDescription(rs.getString("item_description"));
				item.setShippingTime(rs.getString("shipping_time"));
				item.setEndDate(rs.getString("end_date"));
				item.setInitialPrice(rs.getDouble("initial_price"));
				item.setShippingCost(rs.getDouble("shipping_cost"));
				item.setExpeditedShipping(rs.getDouble("expedited_shipping"));
				items.add(item);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return items;
	}
	public Catalogue createItem(Catalogue item) {
		//Insert new item into database
		String sql = "INSERT INTO items (item_name, current_bid, auction_type, remaining_time, item_description, shipping_time, end_date, initial_price, shipping_cost, expedited_shipping) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.connectCatalogue();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item.getItemName());
			pstmt.setDouble(2, item.getCurrentBid());
			pstmt.setString(3, item.getAuctionType());
			pstmt.setString(4, item.getRemainingTime());
			pstmt.setString(5, item.getItemDescription());
			pstmt.setString(6, item.getShippingTime());
			pstmt.setString(7, item.getEndDate());
			pstmt.setDouble(8, item.getInitialPrice());
			pstmt.setDouble(9, item.getShippingCost());
			pstmt.setDouble(10, item.getExpeditedShipping());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return item;
	}
	
	public Catalogue readItem(int id) {
		// Read specific item by ID from Catalogue
		String sql = "SELECT item_name, current_bid, auction_type, remaining_time, item_description, shipping_time, end_date, initial_price, shipping_cost, expedited_shipping FROM items WHERE item_id = ?";
		Catalogue item = null;
		
		try (Connection conn = DatabaseConnection.connectCatalogue();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameter
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				// Check if a result was returned
				if (rs.next()) {
					item = new Catalogue();
					item.setItemID(id);
					item.setItemName(rs.getString("item_name"));
					item.setCurrentBid(rs.getDouble("current_bid"));
					item.setAuctionType(rs.getString("auction_type"));
					item.setRemainingTime(rs.getString("remaining_time"));
					item.setItemDescription(rs.getString("item_description"));
					item.setShippingTime(rs.getString("shipping_time"));
					item.setEndDate(rs.getString("end_date"));
					item.setInitialPrice(rs.getDouble("initial_price"));
					item.setShippingCost(rs.getDouble("shipping_cost"));
					item.setExpeditedShipping(rs.getDouble("expedited_shipping"));
					
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return item;
	}
	public Catalogue updateItem(int id, Catalogue item) {
		//update specific item by ID from Catalogue
		String sql = "UPDATE items SET item_name = ?, current_bid = ?, auction_type = ?, remaining_time = ?, item_description = ?, shipping_time = ?, end_date = ?, initial_price = ?, shipping_cost = ?, expedited_shipping = ? WHERE item_id =?";

						try (Connection conn = DatabaseConnection.connectCatalogue();
							PreparedStatement pstmt = conn.prepareStatement(sql)) {
							pstmt.setString(1, item.getItemName());
							pstmt.setDouble(2, item.getCurrentBid());
							pstmt.setString(3, item.getAuctionType());
							pstmt.setString(4, item.getRemainingTime());
							pstmt.setString(5, item.getItemDescription());
							pstmt.setString(6, item.getShippingTime());
							pstmt.setString(7, item.getEndDate());
							pstmt.setDouble(8, item.getInitialPrice());
							pstmt.setDouble(9, item.getShippingCost());
							pstmt.setDouble(10, item.getExpeditedShipping());
							pstmt.setInt(11, id);
							//update the item record
							pstmt.executeUpdate();
						} catch (SQLException e) {
							System.out.println(e.getMessage());
						}
						return item;
	}
	public void deleteItem(int id) {
		//Delete a specific item by ID from Catalogue
		String sql = "DELETE FROM items WHERE item_id = ?";

		try (Connection conn = DatabaseConnection.connectCatalogue();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set the corresponding parameter
			pstmt.setInt(1, id);
			// Delete the item record
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Catalogue> searchItems(String keyword) {
		//Columns in items table from Catalogue Database
		//search for items with specific keyword in catalogue.db
		List<Catalogue> items = new ArrayList<>();
		String sql = "SELECT * FROM items where item_name LIKE ?";

		try (Connection conn = DatabaseConnection.connectCatalogue();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
				

			String pattern = "%" + keyword + "%";
			stmt.setString(1, pattern);
			
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Catalogue item = new Catalogue();
				item.setItemID(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setCurrentBid(rs.getDouble("current_bid"));
				item.setAuctionType(rs.getString("auction_type"));
				item.setRemainingTime(rs.getString("remaining_time"));
				item.setItemDescription(rs.getString("item_description"));
				item.setShippingTime(rs.getString("shipping_time"));
				item.setEndDate(rs.getString("end_date"));
				item.setInitialPrice(rs.getDouble("initial_price"));
				item.setShippingCost(rs.getDouble("shipping_cost"));
				item.setExpeditedShipping(rs.getDouble("expedited_shipping"));
				items.add(item);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return items;
	}
}
