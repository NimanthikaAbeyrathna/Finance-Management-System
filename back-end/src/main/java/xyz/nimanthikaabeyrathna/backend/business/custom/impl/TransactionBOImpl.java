package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.TransactionBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.TransactionDAO;
import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;
import xyz.nimanthikaabeyrathna.backend.entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionBOImpl implements TransactionBO {

    private final TransactionDAO transactionDAO;
    private final Transformer transformer;
    private final JdbcTemplate jdbcTemplate;

    public TransactionBOImpl(TransactionDAO transactionDAO, Transformer transformer, JdbcTemplate jdbcTemplate) {
        this.transactionDAO = transactionDAO;
        this.transformer = transformer;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() throws Exception {

        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        List<Transaction> transactions = transactionDAO.findAll();

        for (Transaction transaction : transactions) {
            transactionDTOS.add(transformer.fromTransactionEntity(transaction));
        }

        return transactionDTOS;
    }

    @Override
    public void saveTransaction(TransactionDTO transactionDTO) throws Exception {

        if (transactionDAO.existsById(transactionDTO.getId())){
            throw new DuplicateRecordException(transactionDTO.getId()+" already exist");
        }
        transactionDAO.save(transformer.toTransactionEntity(transactionDTO));
    }

    @Override
    public void deleteTransaction(Long id) throws Exception {

        transactionDAO.deleteById(id);
    }

    @Override
    public void updateTransaction(TransactionDTO transactionDTO) throws Exception {

        if (!transactionDAO.existsById(transactionDTO.getId())){
            throw new RecordNotFoundException(transactionDTO.getId()+" does not exist");
        }
        transactionDAO.update(transformer.toTransactionEntity(transactionDTO));
    }

    @Override
    public void deposit(TransactionDTO transactionDTO) throws Exception {

        // Get the account type from transactionDTO
        String query1 = "SELECT Account.account_type\n" +
                "FROM Transaction\n" +
                "         JOIN Account ON Transaction.account_id = Account.id\n" +
                "WHERE Transaction.id = ?";
        String accountType = jdbcTemplate.queryForObject(query1,String.class,transactionDTO.getAccountId());

        //get account id
        String query2 = "SELECT Account.id\n" +
                "FROM Transaction\n" +
                "         JOIN Account ON Transaction.account_id = Account.id\n" +
                "WHERE Transaction.id = ?";
        Long accountId = jdbcTemplate.queryForObject(query2,Long.class,transactionDTO.getAccountId());

        // You may need to fetch the account details from the database first
        String query = "SELECT balance FROM Account WHERE account_type = ? AND id=?";
        BigDecimal currentBalance = jdbcTemplate.queryForObject(query, BigDecimal.class, accountType,accountId);

        // Update the account balance based on the deposit amount
        BigDecimal depositAmount = transactionDTO.getAmount();
        assert currentBalance != null;
        BigDecimal newBalance = currentBalance.add( depositAmount);

        // Update the balance in the database
        String updateQuery = "UPDATE Account SET balance = ? WHERE account_type = ? AND id=?";
        jdbcTemplate.update(updateQuery, newBalance, accountType,accountId);



    }

    @Override
    public void withdrawal(TransactionDTO transactionDTO) throws Exception {

        // Get the account type from transactionDTO
        String query1 = "SELECT Account.account_type\n" +
                "FROM Transaction\n" +
                "         JOIN Account ON Transaction.account_id = Account.id\n" +
                "WHERE Transaction.id = ?";
        String accountType = jdbcTemplate.queryForObject(query1,String.class,transactionDTO.getAccountId());

        //get account id
        String query2 = "SELECT Account.id\n" +
                "FROM Transaction\n" +
                "         JOIN Account ON Transaction.account_id = Account.id\n" +
                "WHERE Transaction.id = ?";
        Long accountId = jdbcTemplate.queryForObject(query2,Long.class,transactionDTO.getAccountId());

        // You may need to fetch the account details from the database first
        String query = "SELECT balance FROM Account WHERE account_type = ? AND id=?";
        BigDecimal currentBalance = jdbcTemplate.queryForObject(query, BigDecimal.class, accountType,accountId);

        // Update the account balance based on the deposit amount
        BigDecimal withdrawalAmount = transactionDTO.getAmount();
        assert currentBalance != null;
        BigDecimal newBalance = currentBalance.subtract( withdrawalAmount);

        // Update the balance in the database
        String updateQuery = "UPDATE Account SET balance = ? WHERE account_type = ? AND id=?";
        jdbcTemplate.update(updateQuery, newBalance, accountType,accountId);

    }
}
