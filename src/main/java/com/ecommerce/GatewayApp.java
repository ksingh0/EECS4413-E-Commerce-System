package com.ecommerce;

public class GatewayApp {
	private PaymentDAO paymentDAO = new PaymentDAO();
	
	//To be implemented when databases are added
	// Also need to add new dbs to context for DatabaseConncetion.java
	public Auction getAuctions() {
		return new Auction(0,0,0,0,0);
	}
	
	public Bid getBids() {
		return new Bid(0,0,0,0,0);
	}

	public Payment getPayment(){
    return Payment payment = new Payment(
    1, 101, 12, 299.99, "2025-11-01 10:00:00", "COMPLETED",
    "John Doe", "123 Main St", "Toronto", "ON", "M4B1B3", "Canada",
    "John Doe", "1234567890", "VISA", "tok_abc123xyz", "11/26");
	}
}
