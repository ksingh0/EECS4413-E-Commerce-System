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

    public Payment getPayment(int id) {
        return paymentDAO.getPaymentById(id);
    }
	
    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public void createPayment(Payment payment) {
        paymentDAO.createPayment(payment);
    }

    public void updatePaymentStatus(int id, String status) {
        paymentDAO.updatePaymentStatus(id, status);
    }
}
