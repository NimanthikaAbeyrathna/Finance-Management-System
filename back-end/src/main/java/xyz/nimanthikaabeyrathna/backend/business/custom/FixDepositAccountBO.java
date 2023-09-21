package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;

import java.util.List;

public interface FixDepositAccountBO {

    List<FixedDepositAccountDTO> getAllFixDepositAccounts() throws Exception;

    void saveFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception;

    void deleteFixDepositAccount(Long id) throws Exception;

    void updateFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception;
}
