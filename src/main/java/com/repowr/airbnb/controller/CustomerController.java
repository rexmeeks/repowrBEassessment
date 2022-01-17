package com.repowr.airbnb.controller;

import com.repowr.airbnb.dto.request.CreateCustomer;
import com.repowr.airbnb.service.CustomerService;
import com.repowr.airbnb.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    //TODO this is where we can put customer specific related endpoints, namely retrieving bookings associated with a
    // specific customer

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidation customerValidation;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getCustomerById (@PathVariable Integer id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping(path ="/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity createCustomer(@RequestBody CreateCustomer createCustomer) {
        ResponseEntity validation = customerValidation.validateCreateCustomerRequest(createCustomer);
        if(validation != null) {
            return validation;
        }
        return customerService.createCustomer(createCustomer);
    }
}
