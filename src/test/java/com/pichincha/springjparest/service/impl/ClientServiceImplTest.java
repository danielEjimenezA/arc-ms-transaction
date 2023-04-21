package com.pichincha.springjparest.service.impl;

import com.pichincha.springjparest.domain.Client;
import com.pichincha.springjparest.domain.Person;
import com.pichincha.springjparest.repository.ClientRepository;
import com.pichincha.springjparest.repository.PersonRepository;
import com.pichincha.springjparest.service.ClientService;
import com.pichincha.springjparest.service.dto.ClientDto;
import com.pichincha.springjparest.service.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClientServiceImplTest
{
    private ClientService clientService;
    private ClientRepository clientRepository;
    Client entity;
    ClientDto clientDto;
    long id = 1324L;
    
    @BeforeEach
    void setUp()
    {
        clientRepository = Mockito.mock(ClientRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        
        this.clientService = new ClientServiceImpl(
                this.clientRepository,
                personRepository
        );
        
        Person person = new Person();
        person.setId("1725374134");
        person.setAddress("Carcelen");
        person.setName("Daniel Jimenez");
        person.setAge(25);
        person.setPhone("0998320367");
        person.setGender("Masculino");
        
        this.entity = new Client();
        this.entity.setId(id);
        this.entity.setPassword("Abc123");
        this.entity.setPerson(person);
        this.entity.setStatus(Boolean.TRUE);
        
        PersonDto personDto = PersonDto
                .builder()
                .id("1725374134")
                .name("Daniel Jimenez")
                .gender("Masculino")
                .age(25)
                .address("Carcelen")
                .phone("0998320367")
                .build();
        this.clientDto = ClientDto
                .builder()
                .id(id)
                .password("Abc123")
                .status(true)
                .personDto(personDto)
                .build();
    }
    
    @Test
    void get()
    {
        when(this.clientRepository.findById(id)).thenReturn(Optional.of(entity));
        ClientDto result = clientService.get(id);
        assertNotNull(result);
        assertEquals(
                id,
                result.getId()
        );
    }
    
    @Test
    void create() throws NoSuchAlgorithmException
    {
        when(this.clientRepository.save(any())).thenReturn(entity);
        ClientDto result = this.clientService.create(clientDto);
        assertNotNull(result);
        assertEquals(
                id,
                result.getId()
        );
    }
}