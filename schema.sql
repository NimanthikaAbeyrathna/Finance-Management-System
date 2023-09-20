CREATE TABLE User (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL
);


CREATE TABLE Account (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         account_type VARCHAR(255) NOT NULL,
                         balance DOUBLE NOT NULL,
                         user_id INT,
                         FOREIGN KEY (user_id) REFERENCES User(id)
);


CREATE TABLE LoanAccount (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             loan_amount DOUBLE NOT NULL,
                             interest_rate DOUBLE NOT NULL,
                             account_id INT,
                             FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE FixedDepositAccount (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     deposit_amount DOUBLE NOT NULL,
                                     maturity_date DATE NOT NULL,
                                     interest_rate DOUBLE NOT NULL,
                                     account_id INT,
                                     FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE SavingsAccount (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                interest_rate DOUBLE NOT NULL,
                                withdrawal_limit DOUBLE NOT NULL,
                                account_id INT,
                                FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE Transaction (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             transaction_type VARCHAR(255) NOT NULL,
                             amount DOUBLE NOT NULL,
                             timestamp TIMESTAMP NOT NULL,
                             account_id INT,
                             FOREIGN KEY (account_id) REFERENCES Account(id)
);

