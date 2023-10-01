package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;

import java.util.List;

public interface FixDepositAccountBO {

    List<FixedDepositAccountDTO> getAllFixDepositAccounts() throws Exception;

    Long saveFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception;

    void deleteFixDepositAccount(Long id) throws Exception;

    void updateFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception;

    FixedDepositAccount findAccount(Long id)throws Exception;
}
