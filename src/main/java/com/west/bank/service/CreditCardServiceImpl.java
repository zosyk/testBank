package com.west.bank.service;


import com.west.bank.entity.CreditCard;
import com.west.bank.repository.CreditCardRepository;
import com.west.bank.utils.CustomPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    CreditCardRepository repository;


    public List<CreditCard> getAll() {
        return repository.findAll();
    }

    public CreditCard getByID(long id) {
        return repository.findOne(id);
    }

    public List<CreditCard> getClientByOffset(final int offset, final int limit) {

        return repository.findAll(new CustomPageable(offset, limit)).getContent();
    }

    public void save(CreditCard remind) {
        repository.saveAndFlush(remind);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
