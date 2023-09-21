package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.nimanthikaabeyrathna.backend.business.custom.AccountBO;
import xyz.nimanthikaabeyrathna.backend.business.exception.DuplicateRecordException;
import xyz.nimanthikaabeyrathna.backend.business.exception.RecordNotFoundException;
import xyz.nimanthikaabeyrathna.backend.business.util.Transformer;
import xyz.nimanthikaabeyrathna.backend.dao.custom.AccountDAO;
import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class AccountBOImpl implements AccountBO {

    private final AccountDAO accountDAO;
    private final Transformer transformer;

    public AccountBOImpl(AccountDAO accountDAO, Transformer transformer) {
        this.accountDAO = accountDAO;
        this.transformer = transformer;
    }

    @Override
    public List<AccountDTO> getAllAccounts() throws Exception {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        List<Account> accounts = accountDAO.findAll();

        for (Account account : accounts) {
            accountDTOs.add(transformer.fromAccountEntity(account));
        }

        return accountDTOs;
    }

    @Override
    public void saveAccount(AccountDTO accountDTO) throws Exception {

        if (accountDAO.existsById(accountDTO.getId())){
            throw new DuplicateRecordException(accountDTO.getId()+" already exist");
        }
        accountDAO.save(transformer.toAccountEntity(accountDTO));
    }

    @Override
    public void deleteAccount(Long id) throws Exception {

        accountDAO.deleteById(id);
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) throws Exception {

        if (!accountDAO.existsById(accountDTO.getId())){
            throw new RecordNotFoundException(accountDTO.getId()+" does not exist");
        }
        accountDAO.update(transformer.toAccountEntity(accountDTO));
    }
}
