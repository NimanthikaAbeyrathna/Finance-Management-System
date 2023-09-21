package xyz.nimanthikaabeyrathna.backend.business.custom;

import xyz.nimanthikaabeyrathna.backend.dto.SavingsAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;

import java.util.List;

public interface TransactionBO {

    List<TransactionDTO> getAllTransactions() throws Exception;

    void saveTransaction(TransactionDTO transactionDTO) throws Exception;

    void deleteTransaction(Long id) throws Exception;

    void updateTransaction(TransactionDTO transactionDTO) throws Exception;

    void deposit(TransactionDTO transactionDTO) throws Exception;

    void withdrawal(TransactionDTO transactionDTO) throws Exception;
}
