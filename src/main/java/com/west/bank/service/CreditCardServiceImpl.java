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


    public List<CreditCard> findAllByUserID(long userID) {
        return repository.findAllByUserID(userID);
    }

    public CreditCard getByID(long id) {
        return repository.findOne(id);
    }

    public List<CreditCard> getCreditCardByOffset(final int offset, final int limit, final long userID) {

        return repository.findAllByUserIDWithOffset(userID, new CustomPageable(offset, limit)).getContent();
    }

    public void save(CreditCard creditCard) {
        repository.saveAndFlush(creditCard);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public CreditCard findCardByNumber(long number) {

        return repository.findCardByNumber(number);
    }
}
