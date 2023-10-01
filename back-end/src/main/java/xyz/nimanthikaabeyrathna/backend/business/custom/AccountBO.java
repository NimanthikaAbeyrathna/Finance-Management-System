package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;

import java.util.List;

public interface AccountBO {

    List<AccountDTO> getAllAccounts() throws Exception;

    Long saveAccount(AccountDTO accountDTO) throws Exception;

    void deleteAccount(Long id) throws Exception;

    void updateAccount(AccountDTO accountDTO) throws Exception;
    Account findAccount(Long id)throws Exception;
}
