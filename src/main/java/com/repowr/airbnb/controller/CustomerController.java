package com.repowr.airbnb.controller;

import com.repowr.airbnb.dto.request.CreateCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    //TODO this is where we can put customer specific related endpoints, namely retrieving bookings associated with a
    // specific customer

    @PostMapping(path ="/create", consumes = "application/json")
    public ResponseEntity createCustomer(@RequestBody RequestEntity<CreateCustomer> createCustomer) {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
