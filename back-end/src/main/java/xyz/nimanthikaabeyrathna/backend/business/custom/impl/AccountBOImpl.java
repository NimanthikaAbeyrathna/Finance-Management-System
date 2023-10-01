package xyz.nimanthikaabeyrathna.backend.business.custom.impl;

import org.springframework.jdbc.core.JdbcTemplate;
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
import java.util.Optional;


@Service
@Transactional
public class AccountBOImpl implements AccountBO {

    private final AccountDAO accountDAO;
    private final Transformer transformer;
    private final JdbcTemplate jdbcTemplate;

    public AccountBOImpl(AccountDAO accountDAO, Transformer transformer, JdbcTemplate jdbcTemplate) {
        this.accountDAO = accountDAO;
        this.transformer = transformer;
        this.jdbcTemplate = jdbcTemplate;
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
    public Long saveAccount(AccountDTO accountDTO) throws Exception {

        if (accountDAO.existsById(accountDTO.getId())){
            throw new DuplicateRecordException(accountDTO.getId()+" already exist");
        }
        Account account = accountDAO.save(transformer.toAccountEntity(accountDTO));

        return account.getId();
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

    @Override
    public Account findAccount(Long id) throws Exception {
        Optional<Account> account = accountDAO.findById(id);
        return account.orElse(null);
    }
}
