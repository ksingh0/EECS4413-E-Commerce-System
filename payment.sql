CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    auction_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_time DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL, -- e.g. 'PENDING', 'COMPLETED', 'FAILED'

    -- Billing details
    billing_name VARCHAR(100) NOT NULL,
    billing_address VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),

    -- Credit card details
    card_holder_name VARCHAR(100),
    card_number CHAR(4),
    card_type VARCHAR(20), 
    card_token VARCHAR(255),
    expiry_date CHAR(5), 

    -- Timestamps
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign key constraints 
    CONSTRAINT fk_payments_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_payments_auction FOREIGN KEY (auction_id)
        REFERENCES auctions(id) ON DELETE CASCADE
);
