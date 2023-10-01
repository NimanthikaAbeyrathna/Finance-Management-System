package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.FixDepositAccountBO;
import xyz.nimanthikaabeyrathna.backend.dto.AccountDTO;
import xyz.nimanthikaabeyrathna.backend.dto.FixedDepositAccountDTO;
import xyz.nimanthikaabeyrathna.backend.entity.Account;
import xyz.nimanthikaabeyrathna.backend.entity.FixedDepositAccount;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/{id}")
    public ResponseEntity<?> getFixDepositAccountDetails(@PathVariable("id") Long id) throws Exception {
        FixedDepositAccount fixedDepositAccount = fixDepositAccountBO.findAccount(id);

        Calendar calendar = Calendar.getInstance();
        java.util.Date today = calendar.getTime();
        Date createDate = fixedDepositAccount.getCreateDate();
        long millisecondsBetweenDates = today.getTime() - createDate.getTime();
        BigDecimal daysBetweenDates = new BigDecimal(millisecondsBetweenDates).divide(BigDecimal.valueOf(86400000), 0, RoundingMode.HALF_UP);
        System.out.println(daysBetweenDates);


        BigDecimal rateFraction = fixedDepositAccount.getInterestRate().divide(BigDecimal.valueOf(100));
        BigDecimal ratePerDay = daysBetweenDates.divide(BigDecimal.valueOf(30), 6, RoundingMode.HALF_UP);


        BigDecimal interestEarned = fixedDepositAccount.getDepositAmount().multiply(rateFraction).multiply(ratePerDay).setScale(2, RoundingMode.HALF_UP);

        Map<String, Object> response = new HashMap<>();
        response.put("maturityDate", fixedDepositAccount.getMaturityDate());
        response.put("interestEarned", interestEarned);

        return ResponseEntity.ok(response);
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Long saveFixDepositAccount(@RequestBody FixedDepositAccountDTO fixedDepositAccountDTO) throws Exception {
        Long generatedId = fixDepositAccountBO.saveFixDepositAccount(fixedDepositAccountDTO);
        return generatedId;

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
