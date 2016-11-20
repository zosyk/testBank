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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    BankClientService bankClientService;

    @Autowired
    UserRoleService userRoleService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        return new ModelAndView("registerUser");
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

}
