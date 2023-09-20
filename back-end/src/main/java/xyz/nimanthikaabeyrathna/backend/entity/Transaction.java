package xyz.nimanthikaabeyrathna.backend.entity;

import java.sql.Date;

public class Transaction implements SuperEntity{
    private Long id;
    private String transactionType;
    private double amount;
    private Date timestamp;
    private Account account;
}
