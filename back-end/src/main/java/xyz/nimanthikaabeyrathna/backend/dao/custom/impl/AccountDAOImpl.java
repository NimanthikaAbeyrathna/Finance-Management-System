package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.AccountDAO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.*;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Account", long.class);

    }

    @Override
    public Account save(Account entity) throws Exception {
        String sql = "INSERT INTO Account (account_type, balance, user_id) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getAccountType());
            ps.setBigDecimal(2, entity.getBalance());
            ps.setLong(3, entity.getUserId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        entity.setId(id);
        return entity;

    }

    @Override
    public void update(Account entity) throws Exception {

        jdbcTemplate.update("UPDATE Account SET account_type=?, balance=?, user_id=? WHERE id=?",
                entity.getAccountType(),
                entity.getBalance(),
                entity.getUserId(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM Account WHERE id=?", pk);
    }

    @Override
    public Optional<Account> findById(Long pk) throws Exception {

        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM Account WHERE id=?", ACCOUNT_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<Account> findAll() throws Exception {

        return jdbcTemplate.query("SELECT * FROM Account", ACCOUNT_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
