package com.vinod.hibernate.encryption.service;

import com.vinod.hibernate.encryption.dto.CustomerQueryDto;
import com.vinod.hibernate.encryption.dto.CustomerRegisterDto;

public interface ICustomerService {

    /**
     * Add new customer.
     *
     * @param customerRegisterDto  - Customer object.
     * @return          - Persisted customer object.
     */
    CustomerQueryDto addCustomer(CustomerRegisterDto customerRegisterDto);

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    CustomerQueryDto getCustomerById(Long id);
}
