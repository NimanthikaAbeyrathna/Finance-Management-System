package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.AccountBO;
import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/account")
public class AccountHTTPController {

    private final AccountBO accountBO;

    public AccountHTTPController(AccountBO accountBO) {
        this.accountBO = accountBO;
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() throws Exception {
        return accountBO.getAllAccounts();
    }

    @GetMapping("/{id}")
    public BigDecimal findBalance(@PathVariable("id") Long id) throws Exception {
        Account account = accountBO.findAccount(id);
        return account.getBalance();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Long> saveAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        Long generatedId = accountBO.saveAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) throws Exception {
        accountBO.deleteAccount(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateAccount(@PathVariable("id") Long id,
                              @RequestBody @Valid AccountDTO accountDTO) throws Exception {
        accountBO.updateAccount(accountDTO);
    }
}
