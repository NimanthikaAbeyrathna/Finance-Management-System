CREATE DATABASE finance_management_system;


CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL
);


CREATE TABLE Account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_type VARCHAR(255) NOT NULL,
                         balance DECIMAL(10, 2) NOT NULL,
                         user_id BIGINT,
                         FOREIGN KEY (user_id) REFERENCES User(id)
);


CREATE TABLE LoanAccount (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             loan_amount DECIMAL(10, 2) NOT NULL,
                             create_date DATE NOT NULL ,
                             interest_rate DECIMAL(10, 2) NOT NULL,
                             account_id BIGINT,
                             FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE FixedDepositAccount (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     deposit_amount DECIMAL (10, 2) NOT NULL,
                                     create_date DATE NOT NULL ,
                                     maturity_date DATE NOT NULL,
                                     interest_rate DECIMAL (10, 2) NOT NULL,
                                     account_id BIGINT,
                                     FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE SavingsAccount (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                create_date DATE NOT NULL ,
                                interest_rate DECIMAL(10, 2) NOT NULL,
                                withdrawal_limit DECIMAL(10, 2) NOT NULL,
                                account_id BIGINT,
                                FOREIGN KEY (account_id) REFERENCES Account(id)
);


CREATE TABLE Transaction (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             transaction_type VARCHAR(255) NOT NULL,
                             amount DECIMAL(10, 2) NOT NULL,
                             timestamp TIMESTAMP NOT NULL,
                             account_id BIGINT,
                             FOREIGN KEY (account_id) REFERENCES Account(id)
);

