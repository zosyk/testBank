package com.west.bank.service;


import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();
    Transaction getByID(final long id);
    List<Transaction> getClientByOffset(final int offset, final int limit);
    void save(final Transaction obj);
    void delete(final long id);
}
