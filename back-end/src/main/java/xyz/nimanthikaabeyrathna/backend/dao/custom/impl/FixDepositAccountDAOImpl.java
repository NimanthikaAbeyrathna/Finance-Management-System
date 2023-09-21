package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.FixDepositAccountDAO;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;

import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.ACCOUNT_ROW_MAPPER;
import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.FIX_DEPOSIT_ACCOUNT_ROW_MAPPER;

@Repository
public class FixDepositAccountDAOImpl implements FixDepositAccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public FixDepositAccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {

        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FixedDepositAccount", long.class);
    }

    @Override
    public FixedDepositAccount save(FixedDepositAccount entity) throws Exception {
        String sql = "INSERT INTO FixedDepositAccount (id, deposit_amount, maturity_date, interest_rate, account_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getDepositAmount(), entity.getMaturityDate(), entity.getInterestRate(),entity.getAccountId());
        return entity;

    }

    @Override
    public void update(FixedDepositAccount entity) throws Exception {

        jdbcTemplate.update("UPDATE FixedDepositAccount SET deposit_amount=?, maturity_date=?, interest_rate=?, account_id=? WHERE id=?",
                entity.getDepositAmount(),
                entity.getMaturityDate(),
                entity.getInterestRate(),
                entity.getAccountId(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM FixedDepositAccount WHERE id=?", pk);
    }

    @Override
    public Optional<FixedDepositAccount> findById(Long pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM FixedDepositAccount WHERE id=?", FIX_DEPOSIT_ACCOUNT_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<FixedDepositAccount> findAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM FixedDepositAccount", FIX_DEPOSIT_ACCOUNT_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
