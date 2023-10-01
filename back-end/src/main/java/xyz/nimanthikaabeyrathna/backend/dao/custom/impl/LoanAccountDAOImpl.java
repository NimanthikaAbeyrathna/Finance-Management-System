package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.LoanAccountDAO;
import xyz.nimanthikaabeyrathna.backend.entity.LoanAccount;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sql = "INSERT INTO LoanAccount (loan_amount, create_date, interest_rate, account_id) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, entity.getLoanAmount());
            ps.setDate(2, entity.getCreateDate());
            ps.setBigDecimal(3, entity.getInterestRate());
            ps.setLong(4, entity.getAccountId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        entity.setId(id);
        return entity;
    }

    @Override
    public void update(LoanAccount entity) throws Exception {

        jdbcTemplate.update("UPDATE LoanAccount SET loan_amount=?,create_date=?, interest_rate=?, account_id=? WHERE id=?",
                entity.getLoanAmount(),
                entity.getCreateDate(),
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
