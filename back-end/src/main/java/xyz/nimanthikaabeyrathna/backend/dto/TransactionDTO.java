package xyz.nimanthikaabeyrathna.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.nimanthikaabeyrathna.backend.entity.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {


    private Long id;

    @NotBlank(message = "Transaction type is required")
    private String transactionType;

    @Positive(message = "Transaction amount must be a positive value")
    private BigDecimal amount;

    @NotNull
    private Timestamp timestamp;

    @NotNull
    private Long accountId;
}
