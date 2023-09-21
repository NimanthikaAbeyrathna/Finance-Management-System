package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.SavingsAccountDTO;

import java.util.List;

public interface SavingAccountBO {

    List<SavingsAccountDTO> getAllSavingAccounts() throws Exception;

    void saveSavingsAccount(SavingsAccountDTO savingsAccountDTO) throws Exception;

    void deleteSavingsAccount(Long id) throws Exception;

    void updateSavingsAccount(SavingsAccountDTO savingsAccountDTO) throws Exception;
}
