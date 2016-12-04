package com.west.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.CreditCard;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

        return TransferUtils.confirmTransaction(request, model, transactionService, creditCardService, bankClientService);
    }


    @RequestMapping(value = "/createTransactionToSomeOne", method = RequestMethod.POST)
    public ModelAndView createTransactionToSomeOne(HttpServletRequest request){

        final Long fromCardNumber = Long.valueOf(request.getParameter("fromCardNumber"));
        final Long toCardNumber = Long.valueOf(request.getParameter("toCardNumber"));

        final Integer sum = Integer.valueOf(request.getParameter("sum"));

        final ModelAndView modelAndView = new ModelAndView("transactionView");

        final CreditCard fromCreditCard = creditCardService.getByNumber(fromCardNumber);
        final CreditCard toCreditCard = creditCardService.getByNumber(toCardNumber);

        modelAndView.addObject("fromCard", fromCreditCard);
        modelAndView.addObject("toCard", toCreditCard);
        modelAndView.addObject("sum", sum);
        modelAndView.addObject("isBetween", false);

        return modelAndView;
    }

    @RequestMapping(value = "/checkCard", method = RequestMethod.POST)
    @ResponseBody
    public String checkCard(@RequestParam(value="number") long number){

        if(!isCardValidate(number)){
            return "{\"error\": \"This card wasn't found\"}";
        }

        return "{\"message\": \"the card is found\"}";
    }

    private boolean isCardValidate(final long number){

        final CreditCard creditCard = creditCardService.getByNumber(number);

        return creditCard != null;
    }


}
