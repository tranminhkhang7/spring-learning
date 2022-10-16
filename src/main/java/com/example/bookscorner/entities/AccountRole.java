package com.example.bookscorner.entities;

import javax.persistence.*;

@Entity
public class AccountRole {
    @EmbeddedId
    AccountRoleKey id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;
}
