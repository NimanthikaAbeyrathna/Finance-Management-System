package xyz.nimanthikaabeyrathna.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.nimanthikaabeyrathna.backend.entity.Transaction;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {


    private Long id;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @PositiveOrZero(message = "Balance must be a positive or zero value")
    private BigDecimal balance;

    @NotNull(message = "Owner is required")
    private Long userId;


}
