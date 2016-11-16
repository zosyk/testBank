package com.west.bank.service;


import com.west.bank.entity.BankClient;
import com.west.bank.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> getAll();
    CreditCard getByID(final long id);
    List<CreditCard> getClientByOffset(final int offset, final int limit);
    void save(final CreditCard clientId);
    void delete(final long id);
}
