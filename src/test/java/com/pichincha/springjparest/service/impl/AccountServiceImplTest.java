package com.pichincha.springjparest.service.impl;

import com.pichincha.springjparest.domain.Account;
import com.pichincha.springjparest.domain.Client;
import com.pichincha.springjparest.domain.Person;
import com.pichincha.springjparest.repository.AccountRepository;
import com.pichincha.springjparest.repository.ClientRepository;
import com.pichincha.springjparest.repository.PersonRepository;
import com.pichincha.springjparest.service.AccountService;
import com.pichincha.springjparest.service.dto.AccountDto;
import com.pichincha.springjparest.service.dto.ClientDto;
import com.pichincha.springjparest.service.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest
{
    private AccountService accountService;
    private AccountRepository accountRepository;
    Account entity;
    AccountDto accountDto;
    long id = 1345L;
    
    @BeforeEach
    void setUp()
    {
        accountRepository = Mockito.mock(AccountRepository.class);
        
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        
        this.accountService = new AccountServiceImpl(this.accountRepository);
        
        Person person = new Person();
        person.setId("1725374134");
        person.setAddress("Carcelen");
        person.setName("Daniel Jimenez");
        person.setAge(25);
        person.setPhone("0998320367");
        person.setGender("Masculino");
        
        Client client = new Client();
        client.setId(id);
        client.setPassword("Abc123");
        client.setPerson(person);
        client.setStatus(Boolean.TRUE);
        
        this.entity = new Account();
        this.entity.setId(id);
        this.entity.setAccountType("Ahorros");
        this.entity.setAccountNumber(3454563L);
        this.entity.setInitialAmount(2000.0);
        this.entity.setStatus(Boolean.TRUE);
        this.entity.setClient(client);
        
        PersonDto personDto = PersonDto
                .builder()
                .id("1725374134")
                .name("Daniel Jimenez")
                .gender("Masculino")
                .age(25)
                .address("Carcelen")
                .phone("0998320367")
                .build();
        ClientDto clientDto = ClientDto
                .builder()
                .id(id)
                .password("Abc123")
                .status(true)
                .personDto(personDto)
                .build();
        this.accountDto = AccountDto
                .builder()
                .id(id)
                .clientDto(clientDto)
                .accountNumber(98223L)
                .accountType("Ahorros")
                .initialAmount(2000.0)
                .status(true)
                .build();
    }
    
    @Test
    void get()
    {
        when(this.accountRepository.findById(id)).thenReturn(Optional.of(entity));
        AccountDto result = accountService.get(id);
        assertNotNull(result);
        assertEquals(
                id,
                result.getId()
        );
    }
    
    @Test
    void create() throws NoSuchAlgorithmException
    {
        when(this.accountRepository.save(any())).thenReturn(entity);
        AccountDto result = this.accountService.create(accountDto);
        assertNotNull(result);
        assertEquals(
                id,
                result.getId()
        );
    }
}