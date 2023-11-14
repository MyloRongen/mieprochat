CREATE TABLE Posts (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   description VARCHAR(255) UNIQUE NOT NULL,
   image_url VARCHAR(255) null,
   created_at DATETIME,
   updated_at DATETIME
);