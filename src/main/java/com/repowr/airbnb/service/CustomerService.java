package com.repowr.airbnb.service;

import com.repowr.airbnb.dto.request.CreateCustomer;
import com.repowr.airbnb.dto.response.Customer;
import com.repowr.airbnb.entity.CustomerEntity;
import com.repowr.airbnb.repository.CustomerRepository;
import com.repowr.airbnb.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransformer customerTransformer;

    public ResponseEntity<Object> findCustomerById(Integer id) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(id);
        // this is debatable, it'd either be a 200 with a blank body, or a 404, because this specific resource
        // doesn't exist, I went with 404
        return optionalCustomerEntity.<ResponseEntity<Object>>map(customerEntity -> new ResponseEntity<>(customerTransformer.customerEntityToCustomerDto(customerEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> createCustomer(CreateCustomer customer) {
        //todo write the .save method
        try {
            CustomerEntity customerEntity = customerRepository.save(new CustomerEntity(customer.getEmail(),
                    customer.getFirstName(), customer.getLastName()));
            //TODO make sure this works
            return new ResponseEntity<>(customerEntity.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            // I realize this is terrible, but I don't want to spend time catching every exception for this exercise,
            // so if it's not created, then that's not the ideal behavior and we'll return an internal server error
            // how to properly handle this would be to build out an error object that you can construct based on
            // whatever error is thrown to give a verbose error
            // OR throwing a built out error and handling it in the controller, there's a lot of different ways to do
            // it, I usually try to keep the controllers to initial logic mainly validation
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
