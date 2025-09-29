CREATE DATABASE IF NOT EXISTS commerce_clients_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE IF NOT EXISTS commerce_catalog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE IF NOT EXISTS commerce_sales_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE USER IF NOT EXISTS 'biztech_admin' @'localhost' IDENTIFIED BY 'secure123!';
GRANT ALL PRIVILEGES ON commerce_clients_db.* TO 'biztech_admin' @'localhost';
GRANT ALL PRIVILEGES ON commerce_catalog_db.* TO 'biztech_admin' @'localhost';
GRANT ALL PRIVILEGES ON commerce_sales_db.* TO 'biztech_admin' @'localhost';
FLUSH PRIVILEGES;
