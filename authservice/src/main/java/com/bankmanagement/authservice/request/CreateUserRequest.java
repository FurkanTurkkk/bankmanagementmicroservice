package com.bankmanagement.authservice.request;


import com.bankmanagement.authservice.model.Role;
import com.bankmanagement.authservice.validation.ValidName;
import com.bankmanagement.authservice.validation.ValidPassword;

import java.util.Set;

public record CreateUserRequest(
       String name,
       @ValidName
       String username,
       @ValidPassword
       String password,
       Set<Role> roles
) {
}
