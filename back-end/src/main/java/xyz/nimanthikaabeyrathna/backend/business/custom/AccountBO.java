package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;

import java.util.List;

public interface AccountBO {

    List<AccountDTO> getAllAccounts() throws Exception;

    void saveAccount(AccountDTO accountDTO) throws Exception;

    void deleteAccount(Long id) throws Exception;

    void updateAccount(AccountDTO accountDTO) throws Exception;
}
