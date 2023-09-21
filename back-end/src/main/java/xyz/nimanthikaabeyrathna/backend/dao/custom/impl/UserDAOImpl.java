package xyz.nimanthikaabeyrathna.backend.dao.custom.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.nimanthikaabeyrathna.backend.dao.custom.UserDAO;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import java.util.List;
import java.util.Optional;

import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.TRANSACTION_ROW_MAPPER;
import static xyz.nimanthikaabeyrathna.backend.dao.util.Mappers.USER_ROW_MAPPER;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM User", long.class);
    }

    @Override
    public User save(User entity) throws Exception {
        String sql = "INSERT INTO User (id, username, password, email) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getUsername(), entity.getPassword(), entity.getEmail());
        return entity;
    }

    @Override
    public void update(User entity) throws Exception {

        jdbcTemplate.update("UPDATE User SET username=?, password=?, email=? WHERE id=?",
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getId());
    }

    @Override
    public void deleteById(Long pk) throws Exception {

        jdbcTemplate.update("DELETE FROM User WHERE id=?", pk);
    }

    @Override
    public Optional<User> findById(Long pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM User WHERE id=?", USER_ROW_MAPPER, pk));
        } catch (DataAccessException exp) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM User", USER_ROW_MAPPER);
    }

    @Override
    public boolean existsById(Long pk) throws Exception {
        return findById(pk).isPresent();
    }
}
