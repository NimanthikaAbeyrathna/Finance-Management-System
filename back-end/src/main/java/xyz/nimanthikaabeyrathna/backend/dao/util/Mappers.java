package xyz.nimanthikaabeyrathna.backend.dao.util;


import org.springframework.jdbc.core.RowMapper;
import xyz.nimanthikaabeyrathna.backend.entity.*;


public class Mappers {

    public static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> {

        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email")


        );
    };

    public static final RowMapper<Account> ACCOUNT_ROW_MAPPER = (rs, rowNum) -> {

        return new Account(

                rs.getLong("id"),
                rs.getString("account_type"),
                rs.getBigDecimal("balance"),
                rs.getLong("user_id")

        );
    };

    public static final RowMapper<FixedDepositAccount> FIX_DEPOSIT_ACCOUNT_ROW_MAPPER = (rs, rowNum) -> {

        return new FixedDepositAccount(

                rs.getLong("id"),
                rs.getBigDecimal("deposit_amount"),
                rs.getDate("create_date"),
                rs.getDate("maturity_date"),
                rs.getBigDecimal("interest_rate"),
                rs.getLong("account_id")

        );
    };

    public static final RowMapper<LoanAccount> LOAN_ACCOUNT_ROW_MAPPER = (rs, rowNum) -> {

        return new LoanAccount(

                rs.getLong("id"),
                rs.getBigDecimal("loan_amount"),
                rs.getDate("create_date"),
                rs.getBigDecimal("interest_rate"),
                rs.getLong("account_id")

        );
    };

    public static final RowMapper<SavingsAccount> SAVINGS_ACCOUNT_ROW_MAPPER = (rs, rowNum) -> {

        return new SavingsAccount(

                rs.getLong("id"),
                rs.getDate("create_date"),
                rs.getBigDecimal("interest_rate"),
                rs.getBigDecimal("withdrawal_limit"),
                rs.getLong("account_id")

        );
    };

    public static final RowMapper<Transaction> TRANSACTION_ROW_MAPPER = (rs, rowNum) -> {

        return new Transaction(

                rs.getLong("id"),
                rs.getString("transaction_type"),
                rs.getBigDecimal("amount"),
                rs.getTimestamp("timestamp"),
                rs.getLong("account_id")

        );
    };

}
