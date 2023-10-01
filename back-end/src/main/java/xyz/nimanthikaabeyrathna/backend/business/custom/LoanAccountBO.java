package xyz.nimanthikaabeyrathna.backend.business.custom;


import xyz.nimanthikaabeyrathna.backend.dto.LoanAccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface LoanAccountBO {

    List<LoanAccountDTO> getAllLoanAccounts() throws Exception;

    Long saveLoanAccount(LoanAccountDTO loanAccountDTO) throws Exception;

    void deleteLoanAccount(Long id) throws Exception;

    void updateLoanAccount(LoanAccountDTO loanAccountDTO) throws Exception;
    void loanRepay(Long id, BigDecimal amount)throws Exception;
}
