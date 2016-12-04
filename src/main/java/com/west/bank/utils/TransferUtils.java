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
        final CreditCard fromCreditCard = creditCardService.getByNumber(transaction.getFromNumber());
        final CreditCard toCreditCard = creditCardService.getByNumber(transaction.getToNumber());
        final  int sum = transaction.getSum();
        fromCreditCard.setValue(fromCreditCard.getValue() - sum);
        creditCardService.save(fromCreditCard);

        toCreditCard.setValue(toCreditCard.getValue() + sum);
        creditCardService.save(toCreditCard);
    }

    public static String confirmTransaction(final HttpServletRequest request,
                                     final Model model,
                                     final TransactionService transactionService,
                                     final CreditCardService creditCardService,
                                     final BankClientService bankClientService) {

        final Long fromNumber = Long.valueOf(request.getParameter("fromNumber"));
        final Long toNumber = Long.valueOf(request.getParameter("toNumber"));
        final Integer sum = Integer.valueOf(request.getParameter("sum"));

        final Transaction transaction = new Transaction();

        transaction.setFromNumber(fromNumber);
        transaction.setToNumber(toNumber);
        transaction.setSum(sum);
        transaction.setTime(new Date().getTime());
        transactionService.save(transaction);
        TransferUtils.calculate(transaction, creditCardService);

        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.findAllByUserID(bankClientService.getClientByUsername(Utils.getAuth().getName()).getId()));
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "successPage";
    }

}
