package xyz.nimanthikaabeyrathna.backend.business.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import xyz.nimanthikaabeyrathna.backend.dto.*;
import xyz.nimanthikaabeyrathna.backend.entity.*;


@Component
public class Transformer {
    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User toUserEntity(UserDTO userDTO) {

        return mapper.map(userDTO, User.class);

    }

    public UserDTO fromUserEntity(User user) {
        return mapper.map(user, UserDTO.class);

    }

    public Account toAccountEntity(AccountDTO accountDTO) {

        return mapper.map(accountDTO, Account.class);

    }

    public AccountDTO fromAccountEntity(Account account) {
        return mapper.map(account, AccountDTO.class);

    }

    public FixedDepositAccount toFixDepositAccountEntity(FixedDepositAccountDTO fixedDepositAccountDTO) {

        return mapper.map(fixedDepositAccountDTO, FixedDepositAccount.class);

    }
    public FixedDepositAccountDTO fromFixDepositAccountEntity(FixedDepositAccount fixedDepositAccount) {
        return mapper.map(fixedDepositAccount, FixedDepositAccountDTO.class);

    }

    public LoanAccount toLoanAccountEntity(LoanAccountDTO loanAccountDTO) {

        return mapper.map(loanAccountDTO, LoanAccount.class);

    }
    public LoanAccountDTO fromLoanAccountEntity(LoanAccount loanAccount) {
        return mapper.map(loanAccount, LoanAccountDTO.class);

    }

    public SavingsAccount toSavingAccountEntity(SavingsAccountDTO savingsAccountDTO) {

        return mapper.map(savingsAccountDTO, SavingsAccount.class);

    }
    public SavingsAccountDTO fromSavingAccountEntity(SavingsAccount savingsAccount) {
        return mapper.map(savingsAccount, SavingsAccountDTO.class);

    }

    public Transaction toTransactionEntity(TransactionDTO transactionDTO) {

        return mapper.map(transactionDTO, Transaction.class);

    }
    public TransactionDTO fromTransactionEntity(Transaction transaction) {
        return mapper.map(transaction, TransactionDTO.class);

    }
}
