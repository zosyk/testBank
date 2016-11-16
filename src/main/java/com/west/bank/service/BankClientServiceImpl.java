package com.west.bank.service;


import com.west.bank.entity.BankClient;
import com.west.bank.repository.BankClientRepository;
import com.west.bank.utils.CustomPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankClientServiceImpl implements BankClientService{

    @Autowired
    BankClientRepository repository;


    public List<BankClient> getAll() {
        return repository.findAll();
    }

    public BankClient getByID(long id) {
        return repository.findOne(id);
    }

    public List<BankClient> getClientByOffset(final int offset, final int limit) {

        return repository.findAll(new CustomPageable(offset, limit)).getContent();
    }

    public void save(BankClient remind) {
        repository.saveAndFlush(remind);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
