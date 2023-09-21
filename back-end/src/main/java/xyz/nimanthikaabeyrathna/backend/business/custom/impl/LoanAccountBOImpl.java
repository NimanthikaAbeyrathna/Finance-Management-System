package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.LoanAccountBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.LoanAccountDAO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.LoanAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;
import xyz.nimanthikaabeyrathna.backend.entity.LoanAccount;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoanAccountBOImpl implements LoanAccountBO {
    private final LoanAccountDAO loanAccountDAO;
    private final Transformer transformer;

    public LoanAccountBOImpl(LoanAccountDAO loanAccountDAO, Transformer transformer) {
        this.loanAccountDAO = loanAccountDAO;
        this.transformer = transformer;
    }

    @Override
    public List<LoanAccountDTO> getAllLoanAccounts() throws Exception {
        List<LoanAccountDTO> loanAccountDTOS = new ArrayList<>();
        List<LoanAccount> loanAccounts = loanAccountDAO.findAll();

        for (LoanAccount loanAccount : loanAccounts) {
            loanAccountDTOS.add(transformer.fromLoanAccountEntity(loanAccount));
        }

        return loanAccountDTOS;
    }

    @Override
    public void saveLoanAccount(LoanAccountDTO loanAccountDTO) throws Exception {

        if (loanAccountDAO.existsById(loanAccountDTO.getId())){
            throw new DuplicateRecordException(loanAccountDTO.getId()+" already exist");
        }
        loanAccountDAO.save(transformer.toLoanAccountEntity(loanAccountDTO));
    }

    @Override
    public void deleteLoanAccount(Long id) throws Exception {

        loanAccountDAO.deleteById(id);
    }

    @Override
    public void updateLoanAccount(LoanAccountDTO loanAccountDTO) throws Exception {

        if (!loanAccountDAO.existsById(loanAccountDTO.getId())){
            throw new RecordNotFoundException(loanAccountDTO.getId()+" does not exist");
        }
        loanAccountDAO.update(transformer.toLoanAccountEntity(loanAccountDTO));
    }
}
