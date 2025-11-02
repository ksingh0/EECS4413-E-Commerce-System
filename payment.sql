CREATE TABLE payments (
    payment_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    auction_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    payment_time TEXT,
    status TEXT,
    billing_name TEXT,
    billing_address TEXT,
    city TEXT,
    state TEXT,
    postal_code TEXT,
    country TEXT,
    card_holder_name TEXT,
    card_number TEXT,
    card_type TEXT,
    expiry_date TEXT
);
