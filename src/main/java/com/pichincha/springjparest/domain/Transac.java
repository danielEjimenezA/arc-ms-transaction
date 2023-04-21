package com.pichincha.springjparest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transac")
public class Transac
{
    @Id
    @Column(
            name = "transac_id",
            nullable = false
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    @Column(
            name = "date",
            nullable = false
    )
    private LocalDate date;

    @Size(max = 30)
    @Column(
            name = "transaction_type",
            length = 30
    )
    private String transactionType;

    @Column(name = "value")
    private Double value;

    @Column(name = "balance")
    private Double balance;

}