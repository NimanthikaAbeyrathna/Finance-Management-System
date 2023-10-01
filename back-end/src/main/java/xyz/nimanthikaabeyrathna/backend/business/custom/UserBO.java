package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserFinder;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import java.util.List;

public interface UserBO {

    List<UserDTO> getAllUsers() throws Exception;

    Long saveUser(UserDTO userDTO) throws Exception;

    void deleteUser(Long id) throws Exception;

    void updateUser(UserDTO userDTO) throws Exception;

    User findUser(UserFinder userFinder);
}
