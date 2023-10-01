package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.LoanAccountBO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.LoanAccountDTO;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/loanaccount")
public class LoanAccountHTTPController {

    private final LoanAccountBO loanAccountBO;

    public LoanAccountHTTPController(LoanAccountBO loanAccountBO) {
        this.loanAccountBO = loanAccountBO;
    }


    @GetMapping
    public List<LoanAccountDTO> getAllLoanAccounts() throws Exception {
        return loanAccountBO.getAllLoanAccounts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Long saveLoanAccount(@RequestBody LoanAccountDTO loanAccountDTO) throws Exception {
        Long generatedId = loanAccountBO.saveLoanAccount(loanAccountDTO);
        return generatedId;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteLoanAccount(@PathVariable("id") Long id) throws Exception {
        loanAccountBO.deleteLoanAccount(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateLoanAccount(@PathVariable("id") Long id,
                                        @RequestBody @Valid LoanAccountDTO loanAccountDTO) throws Exception {
        loanAccountBO.updateLoanAccount(loanAccountDTO);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/loanrepay/{id}")
    public void loanRepayment(@PathVariable("id") Long id,
                              @RequestBody BigDecimal amount)throws Exception{
        loanAccountBO.loanRepay(id,amount);

    }
}
