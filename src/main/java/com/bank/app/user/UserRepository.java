package com.bank.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    @Query("SELECT user FROM User user, " +
            "UserAssignedToEmployee ATE " +
            "WHERE ATE.employeeUsername = :employeeUsername")
    List<User> findAllOfEmployee(@Param("employeeUsername") String employeeUsername);
}
