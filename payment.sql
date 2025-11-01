CREATE TABLE IF NOT EXISTS payments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    auction_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    payment_time TEXT NOT NULL,
    status TEXT NOT NULL, -- e.g. 'PENDING', 'COMPLETED', 'FAILED'

    -- Billing details
    billing_name TEXT NOT NULL,
    billing_address TEXT NOT NULL,
    city TEXT,
    state TEXT,
    postal_code TEXT,
    country TEXT,

    -- Credit card details
    card_holder_name TEXT,
    card_number TEXT, 
    card_type TEXT,
    card_token TEXT,
    expiry_date TEXT,

    -- Timestamps
    created_at TEXT DEFAULT (datetime('now')),
    updated_at TEXT DEFAULT (datetime('now')),

    -- (Foreign keys are supported in SQLite 3.6.19+)
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (auction_id) REFERENCES auctions(id) ON DELETE CASCADE
);
