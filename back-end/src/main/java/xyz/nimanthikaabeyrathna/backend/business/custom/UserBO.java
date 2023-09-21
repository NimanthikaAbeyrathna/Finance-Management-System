package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;

import java.util.List;

public interface UserBO {

    List<UserDTO> getAllUsers() throws Exception;

    void saveUser(UserDTO userDTO) throws Exception;

    void deleteUser(Long id) throws Exception;

    void updateUser(UserDTO userDTO) throws Exception;
}
