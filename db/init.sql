-- Drop table if exists (useful for re-running script)
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  street_name VARCHAR(255) NOT NULL,
  street_number VARCHAR(10) NOT NULL,
  city VARCHAR(50) NOT NULL,
  country VARCHAR(50) NOT NULL,
  postal_code VARCHAR(10) NOT NULL,
  is_seller BOOLEAN DEFAULT FALSE
);

-- Insert sample users (passwords hashed using SHA-256)
INSERT INTO users (username, password, first_name, last_name, street_name, street_number, city, country, postal_code, is_seller)
VALUES 
('testuser', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Test', 'User', 'Main St', '123', 'Toronto', 'Canada', 'A1B2C3', 0);
-- Password is "password123" (SHA-256 hashed)
