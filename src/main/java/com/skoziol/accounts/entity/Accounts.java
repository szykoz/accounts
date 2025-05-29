package com.skoziol.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;
}
