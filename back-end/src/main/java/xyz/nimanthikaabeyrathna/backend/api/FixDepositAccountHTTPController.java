package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.FixDepositAccountBO;
import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/fixdepositaccount")
public class FixDepositAccountHTTPController {

    private final FixDepositAccountBO fixDepositAccountBO;

    public FixDepositAccountHTTPController(FixDepositAccountBO fixDepositAccountBO) {
        this.fixDepositAccountBO = fixDepositAccountBO;
    }


    @GetMapping
    public List<FixedDepositAccountDTO> getAllFixDepositAccounts() throws Exception {
        return fixDepositAccountBO.getAllFixDepositAccounts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void saveFixDepositAccount(@RequestBody FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception {
        fixDepositAccountBO.saveFixDepositAccount(fixedDepositAccountDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFixDepositAccount(@PathVariable("id") Long id) throws Exception {
        fixDepositAccountBO.deleteFixDepositAccount(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateFixDepositAccount(@PathVariable("id") Long id,
                              @RequestBody @Valid FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception {
        fixDepositAccountBO.updateFixDepositAccount(fixedDepositAccountDTO);
    }
}
