package xyz.nimanthikaabeyrathna.backend.entity;

import java.sql.Date;

public class FixedDepositAccount extends Account{
    private double depositAmount;
    private Date maturityDate;
    private double interestRate;
}
