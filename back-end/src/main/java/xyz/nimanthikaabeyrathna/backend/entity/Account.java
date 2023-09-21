package xyz.nimanthikaabeyrathna.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements SuperEntity{

    private Long id;
    private String accountType;
    private BigDecimal balance;
    private Long userId;


}
