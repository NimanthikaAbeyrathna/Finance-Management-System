package xyz.nimanthikaabeyrathna.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FixedDepositAccountDTO {


    private Long id;

    @Positive(message = "Deposit amount must be a positive value")
    private BigDecimal depositAmount;

    @NotNull(message = "Create date is required")
    private Date createDate;

    @NotNull(message = "Maturity date is required")
    private Date maturityDate;

    @PositiveOrZero(message = "Interest rate must be a positive or zero value")
    private BigDecimal interestRate;

    @NotNull(message = "Account Id is required")
    private Long accountId;
}
