package com.bank.app.user.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private boolean enabled;
    private String password;
}
