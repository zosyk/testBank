package com.west.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west.bank.entity.BankClient;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.Transaction;
import com.west.bank.service.BankClientService;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.UserRoleService;
import com.west.bank.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MainController {


    public static final int LIMIT = 3;
    private int offset = 0;
    private final long DEFAULT_CARD_NUMBER = 5463_4321_4356_0921L;


    @Autowired
    BankClientService bankClientService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    com.west.bank.service.TransactionService transactionService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage(final Map<String , Object> model){

        final BankClient bankClient = bankClientService.getClientByUsername(Utils.getAuth().getName());
        model.put("fullname", bankClient.getName() + " " + bankClient.getSurname());

        return "index";
    }

    @RequestMapping(value = "/customTest", method = RequestMethod.GET)
    public ModelAndView customInput(){

        return new ModelAndView("customInput");
    }


    @RequestMapping(value = "/getAllCards", params = {"page"},  method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public ResponseEntity<Model> getAllCards(@RequestParam(value = "page", required = true, defaultValue = "") String page, final Model model){

        int offset = Integer.valueOf(page)*LIMIT;
        List<CreditCard> creditCards = getClients(offset, LIMIT);
        int limit = LIMIT;
        int size = creditCardService.findAllByUserID(bankClientService.getClientByUsername(Utils.getAuth().getName()).getId()).size();

        model.addAttribute(ModelFields.OFFSET, offset);
        model.addAttribute(ModelFields.CREDIT_CARDS, creditCards);
        model.addAttribute(ModelFields.LIMIT, limit);
        model.addAttribute(ModelFields.SIZE, size);

        return new ResponseEntity<Model>(model, HttpStatus.OK);
    }


    private List<CreditCard> getClients(final int offset, final int limit){
        return creditCardService.getCreditCardByOffset(offset, limit, bankClientService.getClientByUsername(Utils.getAuth().getName()).getId());
    }

    @RequestMapping(value = "/getHistory", params = {"number"}, method = RequestMethod.GET)
    private ModelAndView getClient(@RequestParam(value = "number", required = true, defaultValue = "") String number){

        final ModelAndView model = new ModelAndView("getHistory");
        final ObjectMapper mapper = new ObjectMapper();

        String json = "";

        final List<Transaction> transactions = transactionService.findByNumber(Long.valueOf(number));
        final Set<CreditCard> cards = new HashSet<CreditCard>();

        for(Transaction transaction: transactions){
            cards.add(creditCardService.getByNumber(transaction.getFromNumber()));
            cards.add(creditCardService.getByNumber(transaction.getToNumber()));
        }

        final HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put(ModelFields.TRANSACTIONS, transactions);
        map.put(ModelFields.CREDIT_CARDS, cards);

        try{
            json = mapper.writeValueAsString(map);
        } catch (Exception e){
            e.printStackTrace();
        }

        model.addObject(ModelFields.TRANSACTIONS, json);

        return model;
    }

    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CreditCard> createCard(@RequestBody CreditCard creditCard) {

        final BankClient curClient = bankClientService.getClientByUsername(Utils.getAuth().getName());

        creditCard.setNumber(getCardNumber());
        creditCard.setOwnerID(curClient.getId());
        creditCard.setOwnerName(curClient.getName() + " " + curClient.getSurname());

        creditCardService.save(creditCard);

        final int size = creditCardService.findAllByUserID(bankClientService.getClientByUsername(Utils.getAuth().getName()).getId()).size();
        final long id = creditCardService.findAllByUserID(bankClientService.getClientByUsername(Utils.getAuth().getName()).getId()).get(size-1).getId();

        return new ResponseEntity<CreditCard>(creditCardService.getByID(id), HttpStatus.OK);
    }

    private long getCardNumber(){
        if(creditCardService.getCardCount() == 0)
            return DEFAULT_CARD_NUMBER;

        return creditCardService.getMaxCardNumber() + 1;

    }

    public interface ModelFields {
         String TRANSACTIONS = "transactions";

         String LIMIT = "limit";
         String SIZE = "size";
         String OFFSET = "offset";
         String CREDIT_CARDS = "creditCards";

    }

}
