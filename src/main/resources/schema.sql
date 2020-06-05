CREATE TABLE users (
  username VARCHAR(64) NOT NULL,
  password VARCHAR(128) NOT NULL,
  authority VARCHAR(32) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE employee_users (
  employee_username VARCHAR(64) NOT NULL,
  user_username VARCHAR(64) NOT NULL,
  PRIMARY KEY(employee_username, user_username),
  FOREIGN KEY (employee_username) REFERENCES users(username)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (user_username) REFERENCES users(username)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
);

CREATE TABLE accounts (
  id INTEGER(10) NOT NULL AUTO_INCREMENT,
  balance DECIMAL(10) NOT NULL,
  username VARCHAR(64) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (username) REFERENCES users(username)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
);