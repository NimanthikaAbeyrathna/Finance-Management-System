package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.SavingAccountDAO;
import xyz.nimanthikaabeyrathna.backend.entity.SavingsAccount;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.LOAN_ACCOUNT_ROW_MAPPER;
import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.SAVINGS_ACCOUNT_ROW_MAPPER;

@Repository
public class SavingAccountDAOImpl implements SavingAccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public SavingAccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM SavingsAccount", long.class);
    }

    @Override
    public SavingsAccount save(SavingsAccount entity) throws Exception {

        String sql = "INSERT INTO SavingsAccount (create_date, interest_rate, withdrawal_limit, account_id) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, entity.getCreateDate());
            ps.setBigDecimal(2,entity.getInterestRate());
            ps.setBigDecimal(3,entity.getWithdrawalLimit());
            ps.setLong(4,entity.getAccountId());
            return ps;
        },keyHolder );

        Long id = keyHolder.getKey().longValue();
        entity.setId(id);
        return entity;

    }

    @Override
    public void update(SavingsAccount entity) throws Exception {

        jdbcTemplate.update("UPDATE SavingsAccount SET create_date=?, interest_rate=?, withdrawal_limit=?, account_id=? WHERE id=?",
                entity.getCreateDate(),
                entity.getInterestRate(),
                entity.getWithdrawalLimit(),
                entity.getAccountId(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM SavingsAccount WHERE id=?", pk);
    }

    @Override
    public Optional<SavingsAccount> findById(Long pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM SavingsAccount WHERE id=?", SAVINGS_ACCOUNT_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<SavingsAccount> findAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM SavingsAccount", SAVINGS_ACCOUNT_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
