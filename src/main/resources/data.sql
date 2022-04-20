INSERT INTO comment (name, message) VALUES ('Tom', 'Some default comment');

-- Demo table to show how SQL injection can affect any table, not just the one we think we are running the statement on
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    password VARCHAR(255)
);
INSERT INTO user (name, password) VALUES ('user1', 'pass1');
INSERT INTO user (name, password) VALUES ('user2', 'pass2');