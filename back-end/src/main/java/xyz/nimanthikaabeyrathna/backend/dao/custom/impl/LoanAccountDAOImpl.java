package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.LoanAccountDAO;
import xyz.nimanthikaabeyrathna.backend.entity.LoanAccount;

import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.FIX_DEPOSIT_ACCOUNT_ROW_MAPPER;
import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.LOAN_ACCOUNT_ROW_MAPPER;

@Repository
public class LoanAccountDAOImpl implements LoanAccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public LoanAccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM LoanAccount", long.class);
    }

    @Override
    public LoanAccount save(LoanAccount entity) throws Exception {
        String sql = "INSERT INTO LoanAccount (id, loan_amount, interest_rate, account_id) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getLoanAmount(), entity.getInterestRate(), entity.getAccountId());
        return entity;
    }

    @Override
    public void update(LoanAccount entity) throws Exception {

        jdbcTemplate.update("UPDATE LoanAccount SET loan_amount=?, interest_rate=?, account_id=? WHERE id=?",
                entity.getLoanAmount(),
                entity.getInterestRate(),
                entity.getAccountId(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM LoanAccount WHERE id=?", pk);
    }

    @Override
    public Optional<LoanAccount> findById(Long pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM LoanAccount WHERE id=?", LOAN_ACCOUNT_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<LoanAccount> findAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM LoanAccount", LOAN_ACCOUNT_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
