package com.vinod.hibernate.encryption.model;

import com.vinod.hibernate.encryption.encryption.Encrypted;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
@Table(name="customer",schema = "spring-hibernate-customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String emailId;
    @Column
    @Encrypted
    private String password;
    @Column
    private String status;
}
