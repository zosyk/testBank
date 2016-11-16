package com.west.bank.service;


import com.west.bank.entity.BankClient;
import com.west.bank.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    void save(final UserRole userRole);
    List<UserRole> getAll();
}
