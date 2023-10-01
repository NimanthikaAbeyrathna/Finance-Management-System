package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.SavingAccountBO;
import xyz.nimanthikaabeyrathna.backend.dto.LoanAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.SavingsAccountDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/savingaccount")
public class SavingAccountHTTPController {

    private final SavingAccountBO savingAccountBO;

    public SavingAccountHTTPController(SavingAccountBO savingAccountBO) {
        this.savingAccountBO = savingAccountBO;
    }


    @GetMapping
    public List<SavingsAccountDTO> getAllSavingAccounts() throws Exception {
        return savingAccountBO.getAllSavingAccounts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Long saveSavingAccount(@RequestBody SavingsAccountDTO savingsAccountDTO) throws Exception {
        Long generatedId =  savingAccountBO.saveSavingsAccount(savingsAccountDTO);
        return generatedId;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSavingAccount(@PathVariable("id") Long id) throws Exception {
        savingAccountBO.deleteSavingsAccount(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateSavingAccount(@PathVariable("id") Long id,
                                  @RequestBody @Valid SavingsAccountDTO savingsAccountDTO) throws Exception {
        savingAccountBO.updateSavingsAccount(savingsAccountDTO);
    }
}
