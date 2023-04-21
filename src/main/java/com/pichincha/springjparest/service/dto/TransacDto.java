package com.pichincha.springjparest.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransacDto
{
    private Long       id;
    private AccountDto accountDto;
    private LocalDate  date;
    private String     transactionType;
    private Double      value;
    private Double    balance;
}
