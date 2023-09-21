package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.SavingAccountBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.SavingAccountDAO;
import xyz.nimanthikaabeyrathna.backend.dto.SavingsAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SavingAccountBOImpl implements SavingAccountBO {

    private final SavingAccountDAO savingAccountDAO;
    private final Transformer transformer;

    public SavingAccountBOImpl(SavingAccountDAO savingAccountDAO, Transformer transformer) {
        this.savingAccountDAO = savingAccountDAO;
        this.transformer = transformer;
    }

    @Override
    public List<SavingsAccountDTO> getAllSavingAccounts() throws Exception {
        List<SavingsAccountDTO> savingsAccountDTOS = new ArrayList<>();
        List<SavingsAccount> savingsAccounts = savingAccountDAO.findAll();

        for (SavingsAccount savingsAccount : savingsAccounts) {
            savingsAccountDTOS.add(transformer.fromSavingAccountEntity(savingsAccount));
        }

        return savingsAccountDTOS;
    }

    @Override
    public void saveSavingsAccount(SavingsAccountDTO savingsAccountDTO) throws Exception {

        if (savingAccountDAO.existsById(savingsAccountDTO.getId())){
            throw new DuplicateRecordException(savingsAccountDTO.getId()+" already exist");
        }
        savingAccountDAO.save(transformer.toSavingAccountEntity(savingsAccountDTO));
    }

    @Override
    public void deleteSavingsAccount(Long id) throws Exception {

        savingAccountDAO.deleteById(id);
    }

    @Override
    public void updateSavingsAccount(SavingsAccountDTO savingsAccountDTO) throws Exception {

        if (!savingAccountDAO.existsById(savingsAccountDTO.getId())){
            throw new RecordNotFoundException(savingsAccountDTO.getId()+" does not exist");
        }
        savingAccountDAO.update(transformer.toSavingAccountEntity(savingsAccountDTO));
    }
}
