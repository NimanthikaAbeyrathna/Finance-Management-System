package xyz.nimanthikaabeyrathna.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements SuperEntity{

    private Long id;
    private String transactionType;
    private BigDecimal amount;
    private Timestamp timestamp;
    private Long accountId;
}
