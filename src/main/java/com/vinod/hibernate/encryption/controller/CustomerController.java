package com.vinod.hibernate.encryption.controller;

import com.vinod.hibernate.encryption.dto.CustomerQueryDto;
import com.vinod.hibernate.encryption.dto.CustomerRegisterDto;
import com.vinod.hibernate.encryption.service.ICustomerService;
import com.vinod.hibernate.encryption.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.vinod.hibernate.encryption.util.GlobalUtility.buildResponseForError;
import static com.vinod.hibernate.encryption.util.GlobalUtility.buildResponseForSuccess;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Response> addNewCustomer(@RequestBody CustomerRegisterDto customerRegisterDto) {
        try {
            log.trace("Request came to add new customer with following details: {}", customerRegisterDto);
            CustomerQueryDto persistedCustomer = customerService.addCustomer(customerRegisterDto);
            log.trace("Successfully save the customer object with following details: {}", customerRegisterDto);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully registered the customer", persistedCustomer);
        } catch (Exception e) {
            log.error("Exception occurred while adding the customer error msg: {}", e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Unable to register the customer.", null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        log.trace("Request came to get the customer details having the id: {}", id);
        CustomerQueryDto customerQueryDto = customerService.getCustomerById(id);
        if (null != customerQueryDto) {
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully fetched customer", customerQueryDto);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST), "No customer detail found for the given id.", null);
    }

}
