package com.bank.app.account;

import com.bank.app.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(value = AccessLevel.PACKAGE)
    private Long id;

    @Getter(value = AccessLevel.PACKAGE)
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    private Account(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    static Account emptyAccount(User user) {
        return new Account(new BigDecimal("0"), user);
    }

    public void topUp(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void cashIn(BigDecimal amount) {
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalArgumentException("Amount is greater than balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(Account toAccount, BigDecimal amount) {
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalArgumentException("Amount is greater than balance");
        }
        toAccount.topUp(amount);
        this.cashIn(amount);
    }
}
