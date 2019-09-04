package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer/registration")
@SessionAttributes("customer")
public class RegistrationController {


    // get request, get the info from .jsp
    @GetMapping
    public ModelAndView getRegistrationForm() {
        Customer customer = new Customer();
        return new ModelAndView("register", "customer", customer); // view name: register, find register.jsp; customer key in register.jsp
    }

    // post request
    @PostMapping
    public ModelAndView registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer,
                                         BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        // create fail, return to register page
        if (result.hasErrors()) {
            modelAndView.setViewName("register");
            return modelAndView;
        }
        // create success, go to login page
        // add customer to database
        // customerService.addCustomer(customer);
        modelAndView.setViewName("login");
        modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
        return modelAndView;
    }
}