package com.bank.app.user;

import com.bank.app.user.dto.AddUserDto;
import com.bank.app.user.dto.UpdateUserDto;
import com.bank.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController {

    private UserService userService;

    @GetMapping("/login")
    Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("")
    UserDto addUser(@RequestBody() AddUserDto addUserDto, Principal principal) {
        return userService.addUserAccount(addUserDto, principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("{username}")
    UserDto getUserData(@PathVariable("username") String username) {
        return userService.findByUserName(username);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("")
    List<UserDto> getUsers(Principal principal) {
        return userService.getAllOf(principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("{username}")
    void deleteUser(@PathVariable("username") String username) {
        this.userService.deleteUser(username);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PatchMapping("{username}")
    void updateUser(@RequestBody() UpdateUserDto updateUserDto, @PathVariable("username") String username) {
        this.userService.updateUser(username, updateUserDto);
    }
}