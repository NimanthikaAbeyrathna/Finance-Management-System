package xyz.nimanthikaabeyrathna.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingsAccountDTO {


    private Long id;

    @PositiveOrZero(message = "Interest rate must be a positive or zero value")
    private BigDecimal interestRate;

    @PositiveOrZero(message = "Withdrawal limit must be a positive or zero value")
    private BigDecimal withdrawalLimit;

    @NotNull(message = "Account Id is required")
    private Long accountId;


}
