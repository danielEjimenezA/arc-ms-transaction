package com.pichincha.springjparest.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDto
{
    private Long      id;
    private ClientDto clientDto;
    private Long      accountNumber;
    private String    accountType;
    private Double     initialAmount;
    private Boolean    status;
}
