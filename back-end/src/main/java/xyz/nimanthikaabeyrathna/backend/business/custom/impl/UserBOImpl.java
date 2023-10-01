package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.UserBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.UserDAO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserFinder;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserBOImpl implements UserBO {

    private final UserDAO userDAO;
    private final Transformer transformer;
    private final JdbcTemplate jdbcTemplate;

    public UserBOImpl(UserDAO userDAO, Transformer transformer, JdbcTemplate jdbcTemplate) {
        this.userDAO = userDAO;
        this.transformer = transformer;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {

        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userDAO.findAll();

        for (User user : users) {
            userDTOS.add(transformer.fromUserEntity(user));
        }

        return userDTOS;
    }

    @Override
    public Long saveUser(UserDTO userDTO) throws Exception {

        if (userDAO.existsById(userDTO.getId())){
            throw new DuplicateRecordException(userDTO.getId()+" already exist");
        }
        User user = transformer.toUserEntity(userDTO);
        User savedUser = userDAO.save(user);

        String query = "SELECT id FROM User WHERE username = ? AND password = ?";
        Long id = jdbcTemplate.queryForObject(query,Long.class,userDTO.getUsername(),userDTO.getPassword());

        return id;
    }

    @Override
    public void deleteUser(Long id) throws Exception {

        userDAO.deleteById(id);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {

        if (!userDAO.existsById(userDTO.getId())){
            throw new RecordNotFoundException(userDTO.getId()+" does not exist");
        }
        userDAO.update(transformer.toUserEntity(userDTO));
    }

    @Override
    public User findUser(UserFinder userFinder) {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{userFinder.getUsername(), userFinder.getPassword()}, (resultSet, rowNum) -> {
                User user = new User();
                user.setId(resultSet.getLong("id")); // Populate the ID field
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email")); // Populate the email field
                // Populate other fields as needed

                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            // Handle the case when no user is found
            return null;
        }
    }

}
