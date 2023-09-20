package xyz.nimanthikaabeyrathna.backend.entity;

import java.util.List;

public class User implements SuperEntity{

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Account> accounts;

}
