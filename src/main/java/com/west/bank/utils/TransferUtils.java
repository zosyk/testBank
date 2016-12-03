package com.west.bank.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.service.BankClientService;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.TransactionService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by alex on 12/3/16.
 */
public class TransferUtils {

    public static void calculate(final Transaction transaction, final CreditCardService creditCardService){
        final CreditCard fromCreditCard = creditCardService.getByID(transaction.getFromID());
        final CreditCard toCreditCard = creditCardService.getByID(transaction.getToID());
        final  int sum = transaction.getSum();
        fromCreditCard.setValue(fromCreditCard.getValue() - sum);
        creditCardService.save(fromCreditCard);

        toCreditCard.setValue(toCreditCard.getValue() + sum);
        creditCardService.save(toCreditCard);
    }

}
