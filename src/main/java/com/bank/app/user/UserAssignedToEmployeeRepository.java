package com.bank.app.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAssignedToEmployeeRepository extends JpaRepository<UserAssignedToEmployee, String> {
}
