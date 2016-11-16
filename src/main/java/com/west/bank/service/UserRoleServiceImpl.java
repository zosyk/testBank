package com.west.bank.service;


import com.west.bank.entity.BankClient;
import com.west.bank.entity.UserRole;
import com.west.bank.repository.BankClientRepository;
import com.west.bank.repository.UserRoleRepository;
import com.west.bank.utils.CustomPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    UserRoleRepository repository;



    public void save(UserRole userRole) {
        repository.saveAndFlush(userRole);
    }

    public List<UserRole> getAll() {
        return repository.findAll();
    }

}
