CREATE TABLE IF NOT EXISTS catalog_items (
  item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  item_name VARCHAR(300) NOT NULL,
  unit_price DECIMAL(12,2) NOT NULL,
  category VARCHAR(100) NOT NULL,
  stock_quantity INT DEFAULT 0
);
