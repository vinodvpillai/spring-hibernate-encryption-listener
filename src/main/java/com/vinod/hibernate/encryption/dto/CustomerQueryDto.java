package com.vinod.hibernate.encryption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerQueryDto {
    private Long id;
    private String name;
    private String emailId;
    private String password;
}
