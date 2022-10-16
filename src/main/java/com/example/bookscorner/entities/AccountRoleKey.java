package com.example.bookscorner.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountRoleKey implements Serializable {
    @Column(name = "account_id")
    int accountId;
    @Column(name = "role_id")
    int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountRoleKey that)) return false;
        return accountId == that.accountId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }
}
