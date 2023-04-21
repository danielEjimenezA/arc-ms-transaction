package com.pichincha.springjparest.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto
{
    private Long      id;
    private String    password;
    private Boolean    status;
    private PersonDto personDto;
}
