package com.west.bank.controller;

import com.west.bank.entity.BankClient;
import com.west.bank.entity.CreditCard;
import com.west.bank.entity.UserRole;
import com.west.bank.service.BankClientService;
import com.west.bank.service.CreditCardService;
import com.west.bank.service.UserRoleService;
import com.west.bank.utils.RequestWrapper;
import com.west.bank.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BankController {

    public static final String USER_LIST = "user_list";
    public static final String CLIENT = "client";

    private static final int LIMIT = 3;
    private int offset = 0;


    @Autowired
    BankClientService bankClientService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    CreditCardService creditCardService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage(final Map<String , Object> model){
        return "welcome";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String saveOrUpdateUser(HttpServletRequest req) {
        final BankClient bankClient = new BankClient();
        bankClient.setUsername(req.getParameter("email"));
        bankClient.setName(req.getParameter("name"));
        bankClient.setAge(req.getParameter("age"));
        bankClient.setPassword(req.getParameter("password"));

        bankClientService.save(bankClient);
        userRoleService.save(new UserRole(req.getParameter("email"), Role.USER.name()));
        return "login";
    }


    @RequestMapping(value = "/getAllCards", params = {"page"},  method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public ResponseEntity<RequestWrapper> getAllClients(@RequestParam(value = "page", required = true, defaultValue = "") String page, final ModelMap modelMap){
        modelMap.put("paginate", "testText");
        final RequestWrapper wrapper = new RequestWrapper();
        offset = Integer.valueOf(page)*LIMIT;
        wrapper.setCreditCards(getClients(offset, LIMIT));
        wrapper.setLimit(LIMIT);
        wrapper.setOffset(offset);
        wrapper.setSize(creditCardService.getAll().size());
        return new ResponseEntity<RequestWrapper>(wrapper, HttpStatus.OK);
    }


    private List<CreditCard> getClients(final int offset, final int limit){
        return creditCardService.getClientByOffset(offset, limit);
    }

    @RequestMapping(value = "/getClient", params = {"id"}, method = RequestMethod.GET)
    private ModelAndView getClient(@RequestParam(value = "id", required = true, defaultValue = "") String id){

        final BankClient client = bankClientService.getByID(Integer.valueOf(id));
        final ModelAndView model = new ModelAndView();
        model.addObject(CLIENT, client);
        model.setViewName("getClient");
        return model;
    }


    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CreditCard> saveOrUpdateUser(@RequestBody CreditCard creditCard) {
        creditCardService.save(creditCard);
        final int size = creditCardService.getAll().size();
        final long id = creditCardService.getAll().get(size-1).getId();
        return new ResponseEntity<CreditCard>(creditCardService.getByID(id), HttpStatus.OK);
//        return "redirect:/";
    }



}
