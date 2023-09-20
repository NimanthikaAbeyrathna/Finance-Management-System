package xyz.nimanthikaabeyrathna.backend.entity;

import java.util.List;

public class Account implements SuperEntity{
    private Long id;
    private String accountType;
    private double balance;
    private User owner;
    private List<Transaction> transactions;

}
