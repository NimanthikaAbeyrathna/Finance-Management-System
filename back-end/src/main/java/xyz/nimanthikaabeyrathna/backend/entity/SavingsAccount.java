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
public class SavingsAccount implements SuperEntity{

    private Long id;
    private Date createDate;
    private BigDecimal interestRate;
    private BigDecimal withdrawalLimit;
    private Long accountId;

}
