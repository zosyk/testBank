package com.west.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.service.BankClientService;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.TransactionService;
import com.west.bank.utils.TransferUtils;
import com.west.bank.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class TransferToSomeoneController {

    public static final String CARDS = "cards";

    @Autowired
    CreditCardService creditCardService;


    @Autowired
    TransactionService transactionService;


    @Autowired
    BankClientService bankClientService;




    @RequestMapping(value = "/transferMoneyToSomeone", method = RequestMethod.GET)
    public String transferMoneyToSomeone(final Model model){
        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(creditCardService.findAllByUserID(bankClientService.getClientByUsername(Utils.getAuth().getName()).getId()));
        } catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("cards", json);
        return "transferToSomeOne";
    }

    @RequestMapping(value = "/confirmTransactionToSomeone", method = RequestMethod.POST)
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


    @RequestMapping(value = "/createTransactionToSomeOne", method = RequestMethod.POST)
    public ModelAndView createTransactionToSomeOne(HttpServletRequest request){

        final Long fromID = Long.valueOf(request.getParameter("fromID"));
        final Long toCardNumber = Long.valueOf(request.getParameter("toCardNumber"));

        final Integer sum = Integer.valueOf(request.getParameter("sum"));

        final ModelAndView modelAndView = new ModelAndView("transactionView");

        final CreditCard fromCreditCard = creditCardService.getByID(fromID);
        final CreditCard toCreditCard = creditCardService.findCardByNumber(toCardNumber);

        modelAndView.addObject("fromCard", fromCreditCard);
        modelAndView.addObject("toCard", toCreditCard);
        modelAndView.addObject("sum", sum);
        modelAndView.addObject("isBetween", false);

        return modelAndView;
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String getSuccessPage(){

        return "index";
    }



}
