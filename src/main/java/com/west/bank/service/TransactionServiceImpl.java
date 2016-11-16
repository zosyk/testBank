package com.west.bank.service;


import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.repository.CreditCardRepository;
import com.west.bank.repository.TransactionRepository;
import com.west.bank.utils.CustomPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository repository;


    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction getByID(long id) {
        return repository.findOne(id);
    }

    public List<Transaction> getClientByOffset(final int offset, final int limit) {

        return repository.findAll(new CustomPageable(offset, limit)).getContent();
    }

    public void save(Transaction remind) {
        repository.saveAndFlush(remind);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
