package com.west.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class TransferController {

    public static final String CARDS = "cards";

    @Autowired
    CreditCardService creditCardService;


    @Autowired
    TransactionService transactionService;



    @RequestMapping(value = "/transferMoneyBetween", method = RequestMethod.GET)
    public String transferMoneyBetween(final Model model){
        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.getAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "transferBetween";
    }

    @RequestMapping(value = "/transferMoneyToSomeone", method = RequestMethod.GET)
    public String transferMoneyToSomeone(final Model model){
        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.getAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "transferToSomeOne";
    }

    @RequestMapping(value = "/confirmTransaction", method = RequestMethod.POST)
    public String confirmTransaction(HttpServletRequest request, final Model model) {

        final Long fromID = Long.valueOf(request.getParameter("fromID"));
        final Long toID = Long.valueOf(request.getParameter("toID"));
        final Integer sum = Integer.valueOf(request.getParameter("sum"));

        final Transaction transaction = new Transaction();

        transaction.setFromID(fromID);
        transaction.setToID(toID);
        transaction.setSum(sum);
        transaction.setTime(new Date().getTime());
        transactionService.save(transaction);
        calculate(transaction);

        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.getAll());
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "transferBetween";
    }

    @RequestMapping(value = "/createTransactionBetween", method = RequestMethod.POST)
    public ModelAndView createTransactionBetween(HttpServletRequest request){

        final Long fromID = Long.valueOf(request.getParameter("fromID"));
        final Long toID = Long.valueOf(request.getParameter("toID"));

        final Integer sum = Integer.valueOf(request.getParameter("sum"));

        final ModelAndView modelAndView = new ModelAndView("transactionView");

        final CreditCard fromCreditCard = creditCardService.getByID(fromID);
        final CreditCard toCreditCard = creditCardService.getByID(toID);

        modelAndView.addObject("fromCard", fromCreditCard);
        modelAndView.addObject("toCard", toCreditCard);
        modelAndView.addObject("sum", sum);

        return modelAndView;
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
