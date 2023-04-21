package com.pichincha.springjparest.service.impl;

import com.pichincha.springjparest.domain.Client;
import com.pichincha.springjparest.domain.Person;
import com.pichincha.springjparest.exception.ApiRequestException;
import com.pichincha.springjparest.repository.ClientRepository;
import com.pichincha.springjparest.repository.PersonRepository;
import com.pichincha.springjparest.service.ClientService;
import com.pichincha.springjparest.service.dto.ClientDto;
import com.pichincha.springjparest.service.mapper.ClientMapper;
import com.pichincha.springjparest.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl
        implements ClientService
{
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public ClientDto get(Long clientId)
    {
        return ClientMapper.INSTANCE.toClientDto(clientRepository
                                                         .findById(clientId)
                                                         .orElse(new Client()));
    }

    @Override
    @Transactional
    public ClientDto create(ClientDto clientDto) throws
                                                 NoSuchAlgorithmException
    {
        Person person = personRepository
                .findById(clientDto
                                  .getPersonDto()
                                  .getId())
                .orElse(null);
        if (person == null)
        {
            personRepository.save(PersonMapper.INSTANCE.toPerson(clientDto.getPersonDto()));
            Random rand = SecureRandom.getInstanceStrong();
            clientDto.setId(rand.nextLong());
            return ClientMapper.INSTANCE.toClientDto(clientRepository.save(ClientMapper.INSTANCE.toClient(clientDto)));
        }
        else
        {
            throw new ApiRequestException("Cliente ya se encuentra registrado");
        }
    }

    @Override
    @Transactional
    public ClientDto update(
            Long clientId,
            ClientDto clientDto
    )
    {
        Client client = clientRepository
                .findById(clientId)
                .orElse(null);
        if (client != null)
        {
            personRepository.save(PersonMapper.INSTANCE.toPerson(clientDto.getPersonDto()));
            client.setPassword(clientDto.getPassword());
            client.setPerson(PersonMapper.INSTANCE.toPerson(clientDto.getPersonDto()));
            client.setStatus(clientDto.getStatus());

            return ClientMapper.INSTANCE.toClientDto(clientRepository.save(client));
        }
        else
        {
            throw new ApiRequestException("Cliente no encontrado");
        }
    }

    @Override
    @Transactional
    public void delete(Long clientId)
    {
        Client client = clientRepository
                .findById(clientId)
                .orElse(null);
        if (client != null)
        {
            personRepository.deleteById(client
                                                .getPerson()
                                                .getId());
            clientRepository.deleteById(clientId);
        }
    }
}
