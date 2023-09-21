package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.TransactionBO;
import xyz.nimanthikaabeyrathna.backend.dto.SavingsAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.TransactionDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/transaction")
public class TransactionHTTPController {

    private final TransactionBO transactionBO;

    public TransactionHTTPController(TransactionBO transactionBO) {
        this.transactionBO = transactionBO;
    }


    @GetMapping
    public List<TransactionDTO> getAllTransactions() throws Exception {
        return transactionBO.getAllTransactions();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void saveTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        transactionBO.saveTransaction(transactionDTO);

        if ("DEPOSIT".equals(transactionDTO.getTransactionType())) {
            transactionBO.deposit(transactionDTO);
        } else if ("WITHDRAWAL".equals(transactionDTO.getTransactionType())) {
            transactionBO.withdrawal(transactionDTO);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") Long id) throws Exception {
        transactionBO.deleteTransaction(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateTransaction(@PathVariable("id") Long id,
                                    @RequestBody @Valid TransactionDTO transactionDTO) throws Exception {
        transactionBO.updateTransaction(transactionDTO);
    }
}
