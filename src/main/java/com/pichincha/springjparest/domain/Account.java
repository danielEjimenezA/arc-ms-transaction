package com.pichincha.springjparest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account
{
    @Id
    @Column(
            name = "account_id",
            nullable = false
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "account_number")
    private Long accountNumber;

    @Size(max = 30)
    @Column(
            name = "account_type",
            length = 30
    )
    private String accountType;

    @Column(name = "initial_amount")
    private Double initialAmount;

    @Column(name = "status")
    private Boolean status;

}