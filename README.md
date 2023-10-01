# Finance Management System

The Finance Management System is a web-based application designed to help users manage their financial accounts effectively. This system allows users to create and manage Loan Accounts, Fixed Deposit Accounts, and Savings Accounts, perform various financial transactions, and obtain account-related information.

## version 1.0.0

## Features

The Finance Management System offers the following key features:

### User Registration and Login
- Users can register for an account by providing essential details.
- Registered users can log in securely to access their accounts.

### Account Creation
- Users can create different types of financial accounts, including Loan Accounts, Fixed Deposit Accounts, and Savings Accounts.

### Transaction Operations
- Users can perform financial transactions such as deposit and withdrawal.
- Transaction processing varies based on the account type.

### Balance Inquiry
- Users can check the balance of their accounts at any time.

### Loan Repayment
- Users with Loan Accounts can make repayments, and the system calculates the remaining loan balance.

### View Fixed Deposit Details
- Users with Fixed Deposit Accounts can view details like maturity date, interest earned, and more.

### Interest Calculation
- The system can calculate interest for Savings and Fixed Deposit Accounts.


## Used tech stacks

- front-end - angular,angural materials,Bootstrap
- back-end - spring boot


##Design Choices
1. Class Structure

    - We chose a modular class structure with separate classes for User, Account, LoanAccount, FixedDepositAccount, SavingsAccount, and Transaction.
    - The Finance System class acts as a high-level controller to manage user registration, login, and the creation of accounts.
    
2. Relationships

    - I established relationships between entities: Users can have multiple accounts, and accounts can have multiple transactions.
    - The User class has a one-to-many relationship with the Account class.
    - The Transaction class is associated with the Account class to record financial transactions.
    
3. Methods

    - I implemented methods in the User class for registration, login, creating accounts, making transactions, and loan repayment.
    - The Account class contains methods for deposit, withdrawal, and calculating balance.
    - Specialized account classes (LoanAccount, FixedDepositAccount, SavingsAccount) have unique methods such as interest calculation and account details.
    
4. Database Integration

    - We assumed the use of a relational database (MySQL) to store user and account data.
    - We used foreign keys to establish relationships between User-Account and Account-Transaction tables.


## Setup Instructions

To run the Finance Management System locally, follow these steps:

1. **Clone the Repository**
   ```bash
   git clone <repository-url>

2. **Database Setup**

1. **Install and configure MySQL** or another relational database.
2. **Create the necessary database tables** based on the provided schema.

3. **Environment Configuration**

- **Configure the database connection details**, such as the database URL, username, and password, in the application configuration files.

4. **Build and Run**

- **Build the application** using your preferred build tool (e.g., Maven or Gradle).
- **Run the application**.

5. **Access the Application**

- Open a web browser and navigate to `http://localhost:<port>` to access the Finance Management System.

## Usage Guidelines

### User Registration and Login

- **Register for a new account** with valid user details.
- Log in to your account securely using your username and password.

### Account Creation

- Create Loan, Fixed Deposit, and Savings Accounts as needed.

### Transaction Operations

- Perform financial transactions such as deposits and withdrawals.
- Transactions are processed differently based on the type of account.

### Balance Inquiry

- Check the balance of your accounts at any time.

### Loan Repayment

- If you have a Loan Account, make repayments as necessary.
- The system calculates the remaining loan balance.

### View Fixed Deposit Details

- If you have a Fixed Deposit Account, view details such as maturity date and interest earned.

### Interest Calculation

- The system automatically calculates interest for Savings and Fixed Deposit Accounts.

## Security and Compliance

- Ensure the security of your account by choosing a strong password and keeping it confidential.
- Adhere to any financial regulations and best practices relevant to your region.




## Contact

contact [Nimanthika Abeyrathna] at [nimanthikaabeyrathna@gmail.com].


