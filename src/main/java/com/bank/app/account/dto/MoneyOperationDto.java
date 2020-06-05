package com.bank.app.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyOperationDto {

    private BigDecimal amount;
    private String toUsername;
}
