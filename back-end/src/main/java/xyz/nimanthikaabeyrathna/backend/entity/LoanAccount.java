package xyz.nimanthikaabeyrathna.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanAccount implements SuperEntity{

    private Long id;
    private BigDecimal loanAmount;
    private Date createDate;
    private BigDecimal interestRate;
    private Long accountId;

}
