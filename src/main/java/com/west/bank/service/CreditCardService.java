package com.west.bank.service;


import com.west.bank.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> findAllByUserID(long userID);

    CreditCard getByID(final long id);

    List<CreditCard> getCreditCardByOffset(final int offset, final int limit, final long userID);

    void save(final CreditCard clientId);

    void delete(final long id);

    CreditCard getByNumber(final long number);

    long getCardCount();

    long getMaxCardNumber();
}
