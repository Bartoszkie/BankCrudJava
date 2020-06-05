package com.bank.app.user;

import com.bank.app.user.dto.UpdateUserDto;
import com.bank.app.user.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    private Role authority;

    private boolean enabled;

    public User(String username, String password, Role role, boolean enabled) {
        this.username = username;
        this.password = password;
        this.authority = role;
        this.enabled = enabled;
    }

    UserDto toDto() {
        return UserDto.builder()
                .username(username)
                .authority(authority.name())
                .enabled(enabled)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authority.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateBy(UpdateUserDto updateUserDto, PasswordEncoder encoder) {
        this.enabled = updateUserDto.isEnabled();
        this.password = encoder.encode(updateUserDto.getPassword());
    }
}
