package com.west.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class TransferController {

    public static final String CARDS = "cards";

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    TransactionService transactionService;



    @RequestMapping(value = "/transferMoney", method = RequestMethod.GET)
    public String transferMoney(final Model model){
        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.getAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "transfer";
    }

    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CreditCard>> createTransaction(@RequestBody Transaction transaction) {
        transaction.setTime(new Date().getTime());
        transactionService.save(transaction);
        calculate(transaction);
        return new ResponseEntity<List<CreditCard>>(creditCardService.getAll(), HttpStatus.OK);
    }

    private void calculate(final Transaction transaction){
        final CreditCard fromCreditCard = creditCardService.getByID(transaction.getFromID());
        final CreditCard toCreditCard = creditCardService.getByID(transaction.getToID());
        final  int sum = transaction.getSum();
        fromCreditCard.setValue(fromCreditCard.getValue() - sum);
        creditCardService.save(fromCreditCard);

        toCreditCard.setValue(toCreditCard.getValue() + sum);
        creditCardService.save(toCreditCard);
    }
}
