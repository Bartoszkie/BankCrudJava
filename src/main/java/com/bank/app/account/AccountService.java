package com.bank.app.account;

import com.bank.app.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public void setUpEmptyAccountFor(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User account can not be null");
        }
        accountRepository.save(Account.emptyAccount(user));
    }

    @Transactional
    public void topUpAccount(String username, BigDecimal amount) {
        validateAmount(amount);
        Account account = findUserAccount(username);
        account.topUp(amount);
    }

    @Transactional
    public void cashInAccount(String username, BigDecimal amount) {
        validateAmount(amount);
        Account account = findUserAccount(username);
        account.cashIn(amount);
    }

    @Transactional
    public void transferMoneyFrom(String usernameFrom, String usernameTo, BigDecimal amount) {
        validateAmount(amount);
        Account fromAccount = findUserAccount(usernameFrom);
        Account toAccount = findUserAccount(usernameTo);

        fromAccount.transferTo(toAccount, amount);
    }

    public BigDecimal checkBalance(String ofUser) {
        return findUserAccount(ofUser).getBalance();
    }

    public Account findUserAccount(String username) {
        return this.accountRepository.findByUserUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Account not exists for this given username"));
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
}
