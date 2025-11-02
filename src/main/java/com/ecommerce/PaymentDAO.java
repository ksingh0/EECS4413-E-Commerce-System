package com.ecommerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ecommerce.Payment;

public class PaymentDAO {

	// Create new payment record
	public void createPayment(Payment payment) {
		String sql = """
				    INSERT INTO payments (
				        user_id, auction_id, amount, payment_time, status,
				        billing_name, billing_address, city, state, postal_code, country,
				        card_holder_name, card_number, card_type, expiry_date
				    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, payment.getUserId());
			pstmt.setInt(2, payment.getAuctionId());
			pstmt.setDouble(3, payment.getAmount());
			pstmt.setString(4, payment.getPaymentTime());
			pstmt.setString(5, payment.getStatus());
			pstmt.setString(6, payment.getBillingName());
			pstmt.setString(7, payment.getBillingAddress());
			pstmt.setString(8, payment.getCity());
			pstmt.setString(9, payment.getState());
			pstmt.setString(10, payment.getPostalCode());
			pstmt.setString(11, payment.getCountry());
			pstmt.setString(12, payment.getCardHolderName());
			pstmt.setString(13, payment.getCardNumber());
			pstmt.setString(14, payment.getCardType());
			pstmt.setString(15, payment.getExpiryDate());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error creating payment: " + e.getMessage());
		}
	}

	// Retrieve all payments
	public List<Payment> getAllPayments() {
		String sql = "SELECT * FROM payments";
		List<Payment> payments = new ArrayList<>();

		try (Connection conn = DatabaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Payment payment = new Payment(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("auction_id"),
						rs.getDouble("amount"), rs.getString("payment_time"), rs.getString("status"),
						rs.getString("billing_name"), rs.getString("billing_address"), rs.getString("city"),
						rs.getString("state"), rs.getString("postal_code"), rs.getString("country"),
						rs.getString("card_holder_name"), rs.getString("card_number"),
						rs.getString("card_type"), rs.getString("expiry_date"));
				payments.add(payment);
			}

		} catch (SQLException e) {
			System.err.println("Error fetching payments: " + e.getMessage());
		}

		return payments;
	}

	public void updatePaymentStatus(int id, String status) {
		String sql = "UPDATE payments SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, status);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error updating payment status: " + e.getMessage());
		}
	}

	public Payment getPaymentById(int id) {
		String sql = "SELECT * FROM payments WHERE id = ?";
		Payment payment = null;

		try (Connection conn = DatabaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				payment = new Payment(rs.getInt("payment_id"), rs.getInt("user_id"), rs.getInt("auction_id"),
						rs.getDouble("amount"), rs.getString("payment_time"), rs.getString("status"),
						rs.getString("billing_name"), rs.getString("billing_address"), rs.getString("city"),
						rs.getString("state"), rs.getString("postal_code"), rs.getString("country"),
						rs.getString("card_holder_name"), rs.getString("card_number"),
						rs.getString("card_type"),  rs.getString("expiry_date"));
			}

		} catch (SQLException e) {
			System.err.println("Error fetching payment by ID: " + e.getMessage());
		}

		return payment;
	}
}
