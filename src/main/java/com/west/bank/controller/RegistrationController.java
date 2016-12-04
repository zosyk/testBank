package com.west.bank.controller;


import com.west.bank.entity.BankClient;
import com.west.bank.entity.UserRole;
import com.west.bank.service.BankClientService;
import com.west.bank.service.UserRoleService;
import com.west.bank.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/registration")
@Controller
public class RegistrationController {

    @Autowired
    BankClientService bankClientService;

    @Autowired
    UserRoleService userRoleService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView registration(){
        return new ModelAndView("registrationView");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String saveOrUpdateUser(HttpServletRequest req, final Model model) {

        final BankClient bankClient = new BankClient();

        bankClient.setUsername(req.getParameter("email"));
        bankClient.setName(req.getParameter("name"));
        bankClient.setSurname(req.getParameter("surname"));
        bankClient.setPassword(req.getParameter("password"));

        bankClientService.save(bankClient);
        userRoleService.save(new UserRole(req.getParameter("email"), Role.USER.name()));

        return "login";
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmail(@RequestParam(value="email") String email){

        if(!isEmailValidate(email)){
            return "{\"error\": \"Email busy\"}";
        }

        return "{\"message\": \"email is valid\"}";
    }

    private boolean isEmailValidate(final String email){

        final BankClient bankClient = bankClientService.getClientByUsername(email);

        return bankClient == null;
    }

}
