package com.bank.app.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyTransferDto {

    private BigDecimal amount;
    private String fromUsername;
    private String toUsername;
}
