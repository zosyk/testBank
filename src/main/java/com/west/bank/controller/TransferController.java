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
    public ModelAndView transferMoney(){
       return getAllCardsModelView("transferMoney");
    }

    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CreditCard>> createTransaction(@RequestBody Transaction transaction) {
        transaction.setTime(new Date().getTime());
        transactionService.save(transaction);
        calculate(transaction);
        return new ResponseEntity<List<CreditCard>>(creditCardService.getAll(), HttpStatus.OK);
    }

    private ModelAndView getAllCardsModelView(final String url){
        final ObjectMapper mapper = new ObjectMapper();
        final ModelAndView model = new ModelAndView(url);
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.getAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addObject("cards", json);
        return model;
    }

    private void calculate(final Transaction transaction){
        final CreditCard fromCreditCard = creditCardService.getByID(transaction.getFromID());
        final CreditCard toCreditCard = creditCardService.getByID(transaction.getToID());
        final  int sum = transaction.getSum();
        fromCreditCard.setCardValue(fromCreditCard.getCardValue() - sum);
        creditCardService.save(fromCreditCard);

        toCreditCard.setCardValue(toCreditCard.getCardValue() + sum);
        creditCardService.save(toCreditCard);
    }
}
