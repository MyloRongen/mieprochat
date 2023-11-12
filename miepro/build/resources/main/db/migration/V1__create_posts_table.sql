CREATE TABLE Posts (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   description VARCHAR(255) UNIQUE NOT NULL,
   created_at DATETIME,
   updated_at DATETIME
);