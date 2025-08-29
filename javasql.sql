-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS CityGuide1;

-- Step 2: Use the database
USE CityGuide1;

-- Step 3: Create the Restaurants table
CREATE TABLE IF NOT EXISTS Restaurants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cuisine_type VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    rating DECIMAL(2,1) NOT NULL CHECK (rating >= 0.0 AND rating <= 5.0)
);

-- Step 4: Insert sample data
INSERT INTO Restaurants (name, cuisine_type, address, rating) VALUES
('Sakura Sushi', 'Japanese', '123 Cherry Blossom St', 4.5),
('Green Leaf Café', 'Vegetarian', '45 Healthy Ave', 4.2),
('Pasta Palace', 'Italian', '9 Roma Street', 4.0),
('Curry Corner', 'Indian', '77 Spice Road', 3.8),
('Dragon Wok', 'Chinese', '56 Bamboo Lane', 4.7),
('Burger Hub', 'American', '12 Grill Ave', 3.5);
