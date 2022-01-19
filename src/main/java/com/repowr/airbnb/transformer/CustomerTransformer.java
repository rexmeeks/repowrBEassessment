package com.repowr.airbnb.transformer;


import com.repowr.airbnb.dto.response.Customer;
import com.repowr.airbnb.entity.CustomerEntity;
import org.springframework.stereotype.Component;

// writing actual transformer class for the sake of breaking things up for future scalability/readability
@Component
public class CustomerTransformer {

    public Customer customerEntityToCustomerDto(CustomerEntity customerEntity) {
        if(customerEntity == null) {
            return null;
        }
        return new Customer(customerEntity.getUserId(), customerEntity.getEmail(),
                customerEntity.getFirstName(), customerEntity.getLastName());
    }
}
