package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.LoanAccountBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.CrudDAO;
import xyz.nimanthikaabeyrathna.backend.dao.custom.LoanAccountDAO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.LoanAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;
import xyz.nimanthikaabeyrathna.backend.entity.LoanAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanAccountBOImpl implements LoanAccountBO {
    private final LoanAccountDAO loanAccountDAO;
    private final Transformer transformer;
    private final JdbcTemplate jdbcTemplate;

    public LoanAccountBOImpl(LoanAccountDAO loanAccountDAO, Transformer transformer, JdbcTemplate jdbcTemplate) {
        this.loanAccountDAO = loanAccountDAO;
        this.transformer = transformer;
        this.jdbcTemplate = jdbcTemplate;
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
    public Long saveLoanAccount(LoanAccountDTO loanAccountDTO) throws Exception {

        if (loanAccountDAO.existsById(loanAccountDTO.getId())){
            throw new DuplicateRecordException(loanAccountDTO.getId()+" already exist");
        }
        LoanAccount loanAccount =  loanAccountDAO.save(transformer.toLoanAccountEntity(loanAccountDTO));
        return loanAccount.getId();


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

    @Override
    public void loanRepay(Long id, BigDecimal amount) throws Exception {
        Optional<LoanAccount> loanAccount = loanAccountDAO.findById(id);

        BigDecimal currentLoanAmount = loanAccount.get().getLoanAmount();
        BigDecimal updateLoanAmount = currentLoanAmount.subtract(amount).setScale(2, RoundingMode.HALF_UP);

        jdbcTemplate.update("UPDATE LoanAccount SET loan_amount=? WHERE id=?", updateLoanAmount, id);
        String sql = "SELECT account_id FROM LoanAccount WHERE id=?";
        String accountId = jdbcTemplate.queryForObject(sql, String.class,id);
        jdbcTemplate.update("UPDATE Account SET balance=? WHERE id=?", updateLoanAmount, accountId);



    }


}
