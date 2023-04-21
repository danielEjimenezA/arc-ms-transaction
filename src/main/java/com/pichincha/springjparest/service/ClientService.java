package com.pichincha.springjparest.service;

import com.pichincha.springjparest.service.dto.ClientDto;

import java.security.NoSuchAlgorithmException;

public interface ClientService
{
    ClientDto get(Long clientId);

    ClientDto create(ClientDto clientDto) throws
                                          NoSuchAlgorithmException;

    ClientDto update(
            Long clientId,
            ClientDto clientDto
    );

    void delete(
            Long clientId
    );
}
