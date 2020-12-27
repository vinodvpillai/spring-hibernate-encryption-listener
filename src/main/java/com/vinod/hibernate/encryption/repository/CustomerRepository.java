package com.vinod.hibernate.encryption.repository;

import com.vinod.hibernate.encryption.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
