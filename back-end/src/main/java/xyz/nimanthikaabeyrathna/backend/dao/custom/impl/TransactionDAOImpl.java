package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.TransactionDAO;
import xyz.nimanthikaabeyrathna.backend.entity.Transaction;

import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.SAVINGS_ACCOUNT_ROW_MAPPER;
import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.TRANSACTION_ROW_MAPPER;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Transaction", long.class);
    }

    @Override
    public Transaction save(Transaction entity) throws Exception {
        String sql = "INSERT INTO Transaction (id, transaction_type, amount, timestamp, account_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,entity.getId(), entity.getTransactionType(), entity.getAmount(), entity.getTimestamp(), entity.getAccountId());
        return entity;
    }

    @Override
    public void update(Transaction entity) throws Exception {

        jdbcTemplate.update("UPDATE Transaction SET transaction_type=?, amount=?, timestamp=?, account_id=? WHERE id=?",
                entity.getTransactionType(),
                entity.getAmount(),
                entity.getTimestamp(),
                entity.getAccountId(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM Transaction WHERE id=?", pk);
    }

    @Override
    public Optional<Transaction> findById(Long pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM Transaction WHERE id=?", TRANSACTION_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM Transaction", TRANSACTION_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
