package com.west.bank.utils;

import com.west.bank.entity.BankClient;
import com.west.bank.entity.CreditCard;

import java.util.List;

public class RequestWrapper {

    private List<CreditCard> creditCards;

    private int offset;
    private int limit;
    private int size;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }
}
