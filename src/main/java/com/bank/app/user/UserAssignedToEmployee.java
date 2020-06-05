package com.bank.app.user;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_users")
@NoArgsConstructor
@AllArgsConstructor
public class UserAssignedToEmployee {

    @Id
    @Column(name = "employee_username")
    @Getter(value = AccessLevel.PACKAGE)
    private String employeeUsername;

    @Column(name = "user_username")
    private String userUsername;
}
