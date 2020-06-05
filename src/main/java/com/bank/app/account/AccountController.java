package com.bank.app.account;

import com.bank.app.account.dto.MoneyOperationDto;
import com.bank.app.account.dto.MoneyTransferDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController()
@RequestMapping("/accounts")
@AllArgsConstructor
class AccountController {

    private AccountService accountService;

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/topUp")
    public void topUp(Principal principal, @RequestBody() MoneyOperationDto moneyOperationDto) {
        accountService.topUpAccount(moneyOperationDto.getToUsername(), moneyOperationDto.getAmount());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/cashIn")
    public void cashIn(Principal principal, @RequestBody() MoneyOperationDto moneyOperationDto) {
        accountService.cashInAccount(moneyOperationDto.getToUsername(), moneyOperationDto.getAmount());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/transfer")
    public void transferMoney(Principal principal, @RequestBody() MoneyTransferDto moneyOperationDto) {
        accountService.transferMoneyFrom(moneyOperationDto.getFromUsername(), moneyOperationDto.getToUsername(), moneyOperationDto.getAmount());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{username}/balance")
    public BigDecimal checkBalance(@PathVariable("username") String username, Principal principal) {
        return accountService.checkBalance(username);
    }
}
