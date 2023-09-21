package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.UserBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.UserDAO;
import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Transaction;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserBOImpl implements UserBO {

    private final UserDAO userDAO;
    private final Transformer transformer;

    public UserBOImpl(UserDAO userDAO, Transformer transformer) {
        this.userDAO = userDAO;
        this.transformer = transformer;
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
    public void saveUser(UserDTO userDTO) throws Exception {

        if (userDAO.existsById(userDTO.getId())){
            throw new DuplicateRecordException(userDTO.getId()+" already exist");
        }
        userDAO.save(transformer.toUserEntity(userDTO));
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
}
