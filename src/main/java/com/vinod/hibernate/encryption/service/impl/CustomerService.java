package com.vinod.hibernate.encryption.service.impl;

import com.vinod.hibernate.encryption.dto.CustomerQueryDto;
import com.vinod.hibernate.encryption.dto.CustomerRegisterDto;
import com.vinod.hibernate.encryption.model.Customer;
import com.vinod.hibernate.encryption.repository.CustomerRepository;
import com.vinod.hibernate.encryption.service.ICustomerService;
import com.vinod.hibernate.encryption.util.ApplicationConstant.CustomerStatus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Add new customer.
     *
     * @param customerRegisterDto  - Customer object.
     * @return          - Persisted customer object.
     */
    @Override
    public CustomerQueryDto addCustomer(CustomerRegisterDto customerRegisterDto) {
        log.trace("Request came to add new customer with details: {}", customerRegisterDto);
        Customer customer = saveCustomer(customerRegisterDto);
        CustomerQueryDto customerQueryDto=mapCustomerToCustomerQueryDto(customer);
        log.trace("Successfully registered customer with details: {}",customerQueryDto);
        return customerQueryDto;
    }

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    @Override
    public CustomerQueryDto getCustomerById(Long id) {
        log.trace("Request came to fetch the customer details for customer id : {}",id);
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            CustomerQueryDto customerQueryDto=mapCustomerToCustomerQueryDto(optionalCustomer.get());
            log.trace("Successfully fetched customer details : {} for customer id: {}",customerQueryDto,id);
            return customerQueryDto;
        }
        return null;
    }

    /**
     * Save the customer object to database.
     *
     * @param customerRegisterDto - Customer register command dto object.
     * @return  - Customer object.
     */
    private Customer saveCustomer(CustomerRegisterDto customerRegisterDto) {
        log.trace("Request came to save the customer object with customer details: {}", customerRegisterDto);
        Customer customer = mapDataToCustomer(customerRegisterDto);
        return customerRepository.save(customer);
    }

    /**
     * Map CustomerRegisterCommandDto to Customer object.
     *
     * @param customerRegisterDto - CustomerRegisterCommandDto object.
     * @return  - Customer object.
     */
    private Customer mapDataToCustomer(CustomerRegisterDto customerRegisterDto) {
        modelMapper.typeMap(CustomerRegisterDto.class, Customer.class).addMappings(mapper -> mapper.skip(Customer::setId));
        Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
        customer.setStatus(CustomerStatus.REGISTERED.value());
        return customer;
    }

    /**
     * Map CustomerRegisterCommandDto to Customer object.
     *
     * @param customer - CustomerRegisterCommandDto object.
     * @return  - Customer Query object.
     */
    private CustomerQueryDto mapCustomerToCustomerQueryDto(Customer customer) {
        CustomerQueryDto customerQueryDto = modelMapper.map(customer, CustomerQueryDto.class);
        return customerQueryDto;
    }

}
