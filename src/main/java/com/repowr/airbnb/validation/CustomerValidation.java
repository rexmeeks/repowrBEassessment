package com.repowr.airbnb.validation;

import com.repowr.airbnb.dto.request.CreateCustomer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CustomerValidation {

    public ResponseEntity validateCreateCustomerRequest(CreateCustomer createCustomer) {
        if(createCustomer == null || createCustomer.equals(new CreateCustomer())) {
            return new ResponseEntity<>("Create customer request is empty", HttpStatus.BAD_REQUEST);
        }
        else if(StringUtils.isBlank(createCustomer.getEmail()) || !Pattern.compile("^(.+)@(\\S+)$").matcher(createCustomer.getEmail()).matches()) {
            return new ResponseEntity<>("Invalid email address", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createCustomer.getFirstName()) || createCustomer.getFirstName().length() > 150) {
            return new ResponseEntity<>("First name is blank or an impossible length", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createCustomer.getLastName()) || createCustomer.getLastName().length() > 150) {
            return new ResponseEntity<>("Last name is blank or an impossible length", HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
