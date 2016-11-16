package com.west.bank.service;


import com.west.bank.entity.BankClient;

import java.util.List;

public interface BankClientService {

    List<BankClient> getAll();
    BankClient getByID(final long id);
    List<BankClient> getClientByOffset(final int offset, final int limit);
    void save(final BankClient clientId);
    void delete(final long id);
}
