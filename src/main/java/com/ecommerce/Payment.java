package com.ecommerce;

public class Payment {

	private int paymentId;
	private int userId;
	private int auctionId;
	private double amount;
	private String paymentTime;
	private String status; // e.g., PENDING, COMPLETED, FAILED

	// Billing info
	private String billingName;
	private String billingAddress;
	private String city;
	private String state;
	private String postalCode;
	private String country;

	// Credit card info 
	private String cardHolderName;
	private String cardNumber;
	private String cardType;
	private String cardToken; 
	private String expiryDate; 

	public Payment(
			int paymentId,
			int userId,
			int auctionId,
			double amount,
			String paymentTime,
			String status,
			String billingName,
			String billingAddress,
			String city,
			String state,
			String postalCode,
			String country,
			String cardHolderName,
			String cardNumber,
			String cardType,
			String cardToken,
			String expiryDate
	) {
		this.id = id;
		this.userId = userId;
		this.auctionId = auctionId;
		this.amount = amount;
		this.paymentTime = paymentTime;
		this.status = status;
		this.billingName = billingName;
		this.billingAddress = billingAddress;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.cardHolderName = cardHolderName;
		this.cardLastFourDigits = cardLastFourDigits;
		this.cardType = cardType;
		this.cardToken = cardToken;
		this.expiryDate = expiryDate;
	}
    
    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int id) { this.paymentId = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAuctionId() { return auctionId; }
    public void setAuctionId(int auctionId) { this.auctionId = auctionId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentTime() { return paymentTime; }
    public void setPaymentTime(String paymentTime) { this.paymentTime = paymentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBillingName() { return billingName; }
    public void setBillingName(String billingName) { this.billingName = billingName; }

    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }

    public String getCardToken() { return cardToken; }
    public void setCardToken(String cardToken) { this.cardToken = cardToken; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
}
