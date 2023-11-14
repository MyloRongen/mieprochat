CREATE TABLE Users (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      username VARCHAR(255),
      email VARCHAR(255),
      password VARCHAR(255)
);

CREATE TABLE RefreshTokens (
   id INT AUTO_INCREMENT PRIMARY KEY,
   token VARCHAR(255) NOT NULL,
   expiry_date DATETIME NOT NULL,
   user_id BIGINT,
   FOREIGN KEY (user_id) REFERENCES Users(id)
);