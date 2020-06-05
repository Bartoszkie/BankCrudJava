package com.bank.app.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    String username;
    String authority;
    boolean enabled;
}
