package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.FixDepositAccountBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.FixDepositAccountDAO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FixDepositBOImpl implements FixDepositAccountBO {

    private final FixDepositAccountDAO fixDepositAccountDAO;
    private final Transformer transformer;

    public FixDepositBOImpl(FixDepositAccountDAO fixDepositAccountDAO, Transformer transformer) {
        this.fixDepositAccountDAO = fixDepositAccountDAO;
        this.transformer = transformer;
    }

    @Override
    public List<FixedDepositAccountDTO> getAllFixDepositAccounts() throws Exception {
        List<FixedDepositAccountDTO> fixedDepositAccountDTOS = new ArrayList<>();
        List<FixedDepositAccount> fixedDepositAccounts = fixDepositAccountDAO.findAll();

        for (FixedDepositAccount fixedDepositAccount : fixedDepositAccounts) {
            fixedDepositAccountDTOS.add(transformer.fromFixDepositAccountEntity(fixedDepositAccount));
        }

        return fixedDepositAccountDTOS;
    }

    @Override
    public Long saveFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception {

        if (fixDepositAccountDAO.existsById(fixedDepositAccountDTO.getId())){
            throw new DuplicateRecordException(fixedDepositAccountDTO.getId()+" already exist");
        }
       FixedDepositAccount fixedDepositAccount = fixDepositAccountDAO.save(transformer.toFixDepositAccountEntity(fixedDepositAccountDTO));
        return fixedDepositAccount.getId();
    }

    @Override
    public void deleteFixDepositAccount(Long id) throws Exception {

        fixDepositAccountDAO.deleteById(id);
    }

    @Override
    public void updateFixDepositAccount(FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception {

        if (!fixDepositAccountDAO.existsById(fixedDepositAccountDTO.getId())){
            throw new RecordNotFoundException(fixedDepositAccountDTO.getId()+" does not exist");
        }
        fixDepositAccountDAO.update(transformer.toFixDepositAccountEntity(fixedDepositAccountDTO));
    }

    @Override
    public FixedDepositAccount findAccount(Long id) throws Exception {

        Optional<FixedDepositAccount> fixedDepositAccount = fixDepositAccountDAO.findById(id);
        return fixedDepositAccount.orElse(null);
    }
}
