package com.bank.app.user;

import com.bank.app.account.AccountService;
import com.bank.app.user.dto.AddUserDto;
import com.bank.app.user.dto.UpdateUserDto;
import com.bank.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AccountService accountService;
    private UserAssignedToEmployeeRepository employeeRepository;

    @PostConstruct()
    private void setUpFirstUser() {
        this.userRepository.save(new User("user", passwordEncoder.encode("12345"), Role.ROLE_EMPLOYEE, true));
    }

    @Transactional
    public UserDto addUserAccount(AddUserDto addUserDto, String employeeName) {
        if (userRepository.existsById(addUserDto.getUsername())) {
            throw new IllegalArgumentException("User with provided email exists");
        }
        User user = this.userRepository.save(new User(addUserDto.getUsername(), passwordEncoder.encode(addUserDto.getPassword()), Role.ROLE_USER, true));
        employeeRepository.save(new UserAssignedToEmployee(employeeName, addUserDto.getUsername()));
        accountService.setUpEmptyAccountFor(user);
        return user.toDto();
    }

    public List<UserDto> getAllOf(String employeeUsername) {
        return this.userRepository.findAllOfEmployee(employeeUsername)
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findByUserName(String name) {
        return userRepository.findByUsername(name).toDto();
    }

    @Transactional
    public void updateUser(String username, UpdateUserDto updateUserDto) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with provided email not exists");
        }
        user.updateBy(updateUserDto, passwordEncoder);
    }

    @Transactional
    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            this.userRepository.deleteById(username);
        }
    }
}
